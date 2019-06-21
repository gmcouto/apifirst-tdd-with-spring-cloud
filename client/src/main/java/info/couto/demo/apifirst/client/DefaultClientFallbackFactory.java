package info.couto.demo.apifirst.client;

import feign.hystrix.FallbackFactory;
import info.couto.demo.apifirst.openapi.model.ActionResponse;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DefaultClientFallbackFactory implements FallbackFactory<DefaultClient> {

  @Override
  public DefaultClient create(Throwable cause) {
    return new DefaultClient() {
      @Override
      public ResponseEntity<ActionResponse> postBye(
          @Valid info.couto.demo.apifirst.openapi.model.ActionRequest actionRequest) {
        System.out.println("FALLBACK CODE CALLED");
        return ResponseEntity.ok(new ActionResponse().message("FALLBACK"));
      }

      @Override
      public ResponseEntity<ActionResponse> postHello(
          @Valid info.couto.demo.apifirst.openapi.model.ActionRequest actionRequest) {
        System.out.println("FALLBACK CODE CALLED");
        return ResponseEntity.ok(new ActionResponse().message("FALLBACK"));
      }

      @Override
      public ResponseEntity<String> postToDowncase(@Valid String body) {
        System.out.println("FALLBACK CODE CALLED");
        return ResponseEntity.ok("FALLBACK");
      }

      @Override
      public ResponseEntity<String> postToUppercase(@Valid String body) {
        System.out.println("FALLBACK CODE CALLED");
        return ResponseEntity.ok("FALLBACK");
      }
    };
  }
}
