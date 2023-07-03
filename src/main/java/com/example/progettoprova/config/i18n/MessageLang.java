//package com.example.progettoprova.config.i18n;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.i18n.LocaleContextHolder;
//import org.springframework.context.support.ResourceBundleMessageSource;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//public class MessageLang {
//
//    //restituisce il bean settato in internationalization
//    private final ResourceBundleMessageSource messageSource;
//
//    public String getMessage(String code) {
//        return messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
//    }
//
//    public String getMessage(String code, Object... args) {
//        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
//    }
//}