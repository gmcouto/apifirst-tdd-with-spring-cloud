package info.couto.demo.apifirst.client;

import info.couto.demo.apifirst.openapi.DefaultApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = DefaultClient.SERVICE_ID, fallbackFactory = DefaultClientFallbackFactory.class)
interface DefaultClient extends DefaultApi {
  String SERVICE_ID = "APIFIRST-MICROSERVICE";
}
