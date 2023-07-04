package com.example.progettoprova.controller;



import com.example.progettoprova.dto.ProdottoDto;
import com.example.progettoprova.dto.RecensioneDto;
import com.example.progettoprova.dto.UtenteDto;
import com.example.progettoprova.services.UtenteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/utente-api/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
@Slf4j
public class UtenteController {

    private final UtenteService utenteService;


    @GetMapping("utenti")//ok
    public ResponseEntity<List<UtenteDto>> dammiUtenti(){
        return ResponseEntity.ok(utenteService.dammiUtenti());
    }

    @Operation(description = "restituisce utente by id")
    @ApiResponse(description = "Resti", responseCode = "200")
    @Parameter(name ="idUtente",description = "id Utente",required = true, example = "1")
    @GetMapping("utenti/{idUtente}")
    public ResponseEntity<UtenteDto> dammiUtenteById(@PathVariable("idUtente") Long idUtente){
            return ResponseEntity.ok(utenteService.dammiUtente(idUtente));
    }

    @GetMapping("utente/{username}")
    @PreAuthorize("#username.equals(authentication.getPrincipal())")
    public ResponseEntity<UtenteDto> dammiUtenteByUsername(@PathVariable("username") String username) {
            return ResponseEntity.ok(utenteService.dammiUtenteByUsername(username));
    }

    @PutMapping("utenti/{idUtente}")
    public ResponseEntity<UtenteDto> aggiorna(@PathVariable("idUtente") Long id, @RequestBody UtenteDto utente) {
        return ResponseEntity.ok(utenteService.aggiorna(id, utente));
    }

    @DeleteMapping("utenti/{idUtente}")
    public HttpStatus cancella(@PathVariable("idUtente") Long id){
        utenteService.cancella(id);
        return HttpStatus.OK;
    }

    @GetMapping("utenti/{idUtente}/recensioni")
    public ResponseEntity<List<RecensioneDto>> dammiRecensioniUtente(@PathVariable("idUtente") Long id){
        return ResponseEntity.ok(utenteService.dammiRecensioniUtente(id));
    }

    @GetMapping("utenti/{idUtente}/prodotti")//ok
    public ResponseEntity<List<ProdottoDto>> dammiProdottiVenditore(@PathVariable("idUtente") Long idUtente){
        return ResponseEntity.ok(utenteService.dammiProdottiUtente(idUtente));
    }

    @PostMapping("/salva")
    public HttpStatus salva(@RequestBody UtenteDto u){
        utenteService.salvaDto(u);
        return HttpStatus.OK;
    }

    @GetMapping("/refreshToken")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                Map<String, String> tokenMap = utenteService.refreshToken(authorizationHeader, request.getRequestURL().toString());
                response.addHeader("access_token", tokenMap.get("access_token"));
                response.addHeader("refresh_token", tokenMap.get("refresh_token"));
                log.info("Refresh ok");
            }
            catch (Exception e) {
                log.error(String.format("Error refresh token: %s", authorizationHeader), e);
                response.setStatus(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("errorMessage", e.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }



//    non riesce ad prendere requestHeader?
//    private final MessageLang messageLang;
//    @GetMapping("/test-lang")
//    public ResponseEntity<String> testLang(@RequestHeader(name = "Accept-Language", required = false) final Locale locale) {
//        System.out.println("Valore di Locale "+locale);
//        return ResponseEntity.ok(messageLang.getMessage("welcome"));
//    }


}
