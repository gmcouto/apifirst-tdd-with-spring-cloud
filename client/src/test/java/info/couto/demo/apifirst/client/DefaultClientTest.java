package info.couto.demo.apifirst.client;

import static java.util.Objects.requireNonNull;
import static org.assertj.core.api.Assertions.assertThat;

import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ServerList;
import info.couto.demo.apifirst.client.TestConfiguration.TestServerList;
import info.couto.demo.apifirst.openapi.model.ActionRequest;
import info.couto.demo.apifirst.openapi.model.ActionResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.cloud.cloudfoundry.discovery.CloudFoundryServer;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfiguration.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class DefaultClientTest {

  @Autowired DefaultClient defaultClient;

  @LocalServerPort int port;

  @Autowired ServerList<Server> ribbonServerList;

  @Before
  public void setServer() {
    if (ribbonServerList instanceof TestServerList
        && ribbonServerList.getInitialListOfServers().isEmpty()) {
      CloudFoundryServer cloudFoundryServer =
          new CloudFoundryServer(DefaultClient.SERVICE_ID, "localhost", port);
      cloudFoundryServer.setAlive(true);
      cloudFoundryServer.setSchemea("http");
      ((TestServerList) ribbonServerList).list.clear();
      ((TestServerList) ribbonServerList).list.add(cloudFoundryServer);
    }
  }

  @Test
  public void testPostHello() {
    ResponseEntity<ActionResponse> hello =
        defaultClient.postHello(new ActionRequest().to("Gabriel"));
    assertThat(requireNonNull(hello.getBody()).getMessage()).isEqualTo("Hello Gabriel, I'm World");
  }

  @Test
  public void testPostBye() {
    ResponseEntity<ActionResponse> hello = defaultClient.postBye(new ActionRequest().to("Gabriel"));
    assertThat(requireNonNull(hello.getBody()).getMessage()).isEqualTo("Bye Gabriel, I'm World");
  }

  @Test
  public void testToUppercase() {
    assertThat(defaultClient.postToUppercase("some msg").getBody()).isEqualTo("SOME MSG");
  }
}
