package com.example.progettoprova.conf.i18n;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.List;
import java.util.Locale;

/*
La classe "LanguageResolver" è una classe Java che estende
la classe "AcceptHeaderLocaleResolver" di Spring e sovrascrive
il metodo "resolveLocale"per determinare la lingua richiesta dal client HTTP.
 */
@Component
public class LanguageResolver extends AcceptHeaderLocaleResolver {


   // codice per determinare la lingua richiesta
  // dal client sulla base dell'header "Accept-Language" inviato nella richiesta HTTP.
   @Override
  public Locale resolveLocale(HttpServletRequest request) {
    String language = request.getHeader("Accept-Language");//attengo accept-language da http header

     //lista di lingue supportate legge da resource bundle. il nome file deve seguire
     // sintassi standard "Messages_en_US.properties" (per l'inglese), "Messages_it.IT.properties" (per l'italiano),
     List<Locale> supportedLocales = getSupportedLocales();

     Locale defaultLocale = getDefaultLocale();//lingue di default
    if (StringUtils.isEmpty(language)) {//se accept-language vuoto restituisci lingua default
      return defaultLocale;}


    Locale requestLocale = Locale.forLanguageTag(language);//oggetto locale con valore della lingua
    if (supportedLocales.contains(requestLocale)) {//se la lingua e' in lista supportata
      return requestLocale;
    } else {//altrimenti lingua di default
      return defaultLocale;
    }
  }

}