package info.couto.demo.apifirst.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {
  @Bean
  public SecurityWebFilterChain springSecurityFilterChain() {
    ServerHttpSecurity http = ServerHttpSecurity.http();
    http.authorizeExchange().anyExchange().permitAll();
    http.csrf().disable();
    return http.build();
  }
}
