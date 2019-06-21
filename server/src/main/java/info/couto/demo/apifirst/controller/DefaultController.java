package info.couto.demo.apifirst.controller;

import info.couto.demo.apifirst.openapi.DefaultApi;
import info.couto.demo.apifirst.openapi.model.ActionRequest;
import info.couto.demo.apifirst.openapi.model.ActionResponse;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

@Controller
public class DefaultController implements DefaultApi {
  private static final String HELLO_PREFIX = "Hello ";
  private static final String BYE_PREFIX = "Bye ";
  private static final String FROM_PREFIX = ", I'm ";

  @Override
  public ResponseEntity<ActionResponse> postBye(@Valid ActionRequest actionRequest) {
    return ResponseEntity.ok()
        .body(generateResponse(BYE_PREFIX, actionRequest.getTo(), actionRequest.getFrom()));
  }

  @Override
  public ResponseEntity<ActionResponse> postHello(@Valid ActionRequest actionRequest) {
    return ResponseEntity.ok()
        .body(generateResponse(HELLO_PREFIX, actionRequest.getTo(), actionRequest.getFrom()));
  }

  @Override
  public ResponseEntity<String> postToDowncase(@Valid String body) {
    return ResponseEntity.ok().body(body.toLowerCase());
  }

  @Override
  public ResponseEntity<String> postToUppercase(@Valid String body) {
    return ResponseEntity.ok().body(body.toUpperCase());
  }

  private ActionResponse generateResponse(String action, String to, String from) {
    if (StringUtils.isEmpty(from)) {
      from = "World";
    }
    ActionResponse response = new ActionResponse();
    response.setMessage(action + to + FROM_PREFIX + from);
    return response;
  }
}
