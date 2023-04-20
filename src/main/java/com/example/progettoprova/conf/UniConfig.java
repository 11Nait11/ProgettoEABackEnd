package com.example.progettoprova.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
//abilita' audting, nome del bean auditorProvider
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class UniConfig {

//  crea un bean e lo ignetta nel codice utilizzando questo metodo
  @Bean//il bean che crea e' un oggetto scritto da me
      //che restituisce oggetto AudutoreAware per usare le annotazioni @CreateBY @CreateDate etc.
  public AuditorAware<Long> auditorProvider()
  {
    return new UserAuditorAware();//Classe Creata manualmente da me
  }
}
