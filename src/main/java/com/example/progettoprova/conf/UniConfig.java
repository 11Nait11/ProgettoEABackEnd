package com.example.progettoprova.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
//abilita' auditing, nome del bean auditorProvider
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class UniConfig {

//  crea un bean e lo inietta nel codice utilizzando questo metodo
  @Bean// restituisce oggetto AudutoreAware per usare le annotazioni @CreateBY @CreateDate etc.
  public AuditorAware<Long> auditorProvider()
  {
    return new UserAuditorAware();//Classe Creata manualmente da me
  }
}
