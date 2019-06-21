package info.couto.demo.apifirst.client;

import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ServerList;
import info.couto.demo.apifirst.OpenAPI2SpringBoot;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients
@EnableAutoConfiguration
@ComponentScan(basePackageClasses = {OpenAPI2SpringBoot.class})
public class TestConfiguration {
  @Configuration
  public static class LocalRibbonClientConfiguration {
    @Bean
    public ServerList<Server> ribbonServerList() {
      return new TestServerList();
    }
  }

  public static class TestServerList implements ServerList<Server> {
    ArrayList<Server> list = new ArrayList<>();

    @Override
    public List<Server> getInitialListOfServers() {
      return list;
    }

    @Override
    public List<Server> getUpdatedListOfServers() {
      return list;
    }
  }
}
