package com.example.progettoprova.handler;

import com.example.progettoprova.dto.ServiceError;
import com.example.progettoprova.exception.ImageException;
import com.example.progettoprova.exception.MessaggioException;
import com.example.progettoprova.exception.ProdottoException;
import com.example.progettoprova.exception.UtenteException;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.stream.Collectors;

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


    @ExceptionHandler(MessaggioException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ServiceError notFound(WebRequest req, ImageException ex){
        return errorResponse(req,ex.getMessage());
    }


    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public ServiceError onResourceNotFoundException(WebRequest req, NullPointerException ex){
        return errorResponse(req, "NULL POINTER!!!");
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ServiceError defaultErrorHandler(WebRequest req ,Exception ex){
        return errorResponse(req, ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ServiceError onMethodArgumentNotValid(WebRequest req, MethodArgumentNotValidException ex){

        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(viol -> viol.getField().concat(" : ")
                        .concat(viol.getDefaultMessage()))
                .collect(Collectors.joining(" , "));
        return errorResponse(req, message);
    }

    //rateLimiter ex
    @ExceptionHandler({ RequestNotPermitted.class })
    @ResponseStatus(HttpStatus.TOO_MANY_REQUESTS)
    public void handleRequestNotPermitted() {
    }

    //circuitBreaker ex
    @ExceptionHandler({CallNotPermittedException.class})
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public void handleCallNotPermittedException() {
    }

    //dto
    private ServiceError errorResponse (WebRequest req, String message) {
        HttpServletRequest httpreq = (HttpServletRequest) req.resolveReference("request");
        final ServiceError output = new ServiceError(new Date(), httpreq.getRequestURI(), message);
        log.error("Exception handler :::: {}", output.toString());
        return output;
    }
}
