package com.example.progettoprova.handler;

import com.example.progettoprova.dto.ServiceError;
import com.example.progettoprova.exception.ImageException;
import com.example.progettoprova.exception.ProdottoException;
import com.example.progettoprova.exception.UtenteException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(UtenteException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ServiceError notFound(WebRequest req, UtenteException ex){
        return errorResponse(req,ex.getMessage());
    }


    @ExceptionHandler(ProdottoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ServiceError notFound(WebRequest req, ProdottoException ex){
        return errorResponse(req,ex.getMessage());
    }


    @ExceptionHandler(ImageException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ServiceError notFound(WebRequest req, ImageException ex){
        return errorResponse(req,ex.getMessage());
    }









    private ServiceError errorResponse (WebRequest req, String message) {
        HttpServletRequest httpreq = (HttpServletRequest) req.resolveReference("request");
        final ServiceError output = new ServiceError(new Date(), httpreq.getRequestURI(), message);
            //"{}" per indicare il posto in cui verranno inseriti i valori dinamici.
        log.error("Exception handler :::: {}", output.toString());
        return output;

    }
}
