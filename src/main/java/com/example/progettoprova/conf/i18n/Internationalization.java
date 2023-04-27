package com.example.progettoprova.conf.i18n;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Arrays;
import java.util.Locale;

@Configuration
    public class Internationalization /*extends WebMvcConfigurerAdapter*/ {

        @Bean
        public AcceptHeaderLocaleResolver localeResolver() {
            //nuova instanza di resolver inoltre setto supportedLocales in modo
            //che quando faccio getSupportedLocales(); mi resistuisce lingue supportate
            final LanguageResolver resolver = new LanguageResolver();
            resolver.setSupportedLocales(Arrays.asList(Locale.ITALY, Locale.US,Locale.UK));
            resolver.setDefaultLocale(Locale.ITALY); //Default ita
            return resolver;
        }

        @Bean//questo bean restituisce un ResourceBundleMessageSource una volta instaziato
        public ResourceBundleMessageSource messageSource() {
            final ResourceBundleMessageSource source = new ResourceBundleMessageSource();

            // il nome base è "language/messages", il che significa che Spring cercherà i file
            // delle proprietà di messaggio in una sottocartella "language" del classpath dell'applicazione.
            source.setBasename("language/messages");
            source.setDefaultEncoding("UTF-8");
            return source;
        }
    }  