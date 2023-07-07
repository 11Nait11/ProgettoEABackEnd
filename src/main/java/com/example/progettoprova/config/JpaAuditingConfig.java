package com.example.progettoprova.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaAuditingConfig {

  @Bean // restituisce oggetto AudutoreAware per usare le annotazioni @CreateBY @CreateDate etc.
  public AuditorAware<Long> auditorProvider()
  {
    return new UserAuditorAware();
  }
}
