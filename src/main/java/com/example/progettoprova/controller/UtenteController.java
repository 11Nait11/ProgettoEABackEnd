package com.example.progettoprova.controller;



import com.example.progettoprova.config.i18n.MessageLang;
import com.example.progettoprova.dto.ProdottoDto;
import com.example.progettoprova.dto.RecensioneDto;
import com.example.progettoprova.dto.UtenteDto;
import com.example.progettoprova.services.UtenteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
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
import java.util.Locale;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/utente-api/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Utente", description = "Esplora e gestisci gli utenti del sistema")
public class UtenteController {

    private final UtenteService utenteService;
    private final MessageLang messageLang;


    @Operation(description = "Restituisce tutti gli utenti")
    @ApiResponse(description = "Elenco degli utenti restituito con successo", responseCode = "200")

    @GetMapping("utenti")
//    @RateLimiter(name = "rateLimiterApi")
    public ResponseEntity<List<UtenteDto>> dammiUtenti() {
        return ResponseEntity.ok(utenteService.dammiUtenti());
    }


    @Operation(description = "Restituisce un utente dato il suo ID")
    @ApiResponse(description = "Utente restituito con successo", responseCode = "200")
    @Parameter(name = "idUtente", description = "ID dell'utente", required = true, example = "1")

    @GetMapping("utenti/{idUtente}")
    public ResponseEntity<UtenteDto> dammiUtenteById(@PathVariable("idUtente") Long idUtente) {
        return ResponseEntity.ok(utenteService.dammiUtente(idUtente));
    }


    @Operation(description = "Restituisce un utente dato il suo username")
    @ApiResponse(description = "Utente restituito con successo", responseCode = "200")
    @Parameter(name = "username", description = "Username dell'utente", required = true, example = "Nait")

    @GetMapping("utente/{username}")
//    @PreAuthorize("#username.equals(authentication.getPrincipal())")
    public ResponseEntity<UtenteDto> dammiUtenteByUsername(@PathVariable("username") String username) {
        return ResponseEntity.ok(utenteService.dammiUtenteByUsername(username));
    }


    @Operation(description = "Aggiorna un utente dato il suo ID")
    @ApiResponse(description = "Utente aggiornato con successo", responseCode = "200")
    @Parameter(name = "idUtente", description = "ID dell'utente", required = true, example = "1")

    @PutMapping("utenti/{idUtente}")
    public ResponseEntity<UtenteDto> aggiorna(@PathVariable("idUtente") Long id, @RequestBody UtenteDto utente) {
        return ResponseEntity.ok(utenteService.aggiorna(id, utente));
    }


    @Operation(description = "Cancella un utente dato il suo ID")
    @ApiResponse(description = "Utente cancellato con successo", responseCode = "200")
    @Parameter(name = "idUtente", description = "ID dell'utente", required = true, example = "1")

    @DeleteMapping("utenti/{idUtente}")
    public HttpStatus cancella(@PathVariable("idUtente") Long id){
        utenteService.cancella(id);
        return HttpStatus.OK;
    }


    @Operation(description = "Restituisce le recensioni di un utente dato il suo ID")
    @ApiResponse(description = "Elenco delle recensioni dell'utente restituito con successo", responseCode = "200")
    @Parameter(name = "idUtente", description = "ID dell'utente", required = true, example = "1")

    @GetMapping("utenti/{idUtente}/recensioni")
    public ResponseEntity<List<RecensioneDto>> dammiRecensioniUtente(@PathVariable("idUtente") Long id){
        return ResponseEntity.ok(utenteService.dammiRecensioniUtente(id));
    }


    @Operation(description = "Restituisce i prodotti di un utente dato il suo ID")
    @ApiResponse(description = "Elenco dei prodotti dell'utente restituito con successo", responseCode = "200")
    @Parameter(name = "idUtente", description = "ID dell'utente", required = true, example = "1")

    @GetMapping("utenti/{idUtente}/prodotti")//ok
    public ResponseEntity<List<ProdottoDto>> dammiProdottiVenditore(@PathVariable("idUtente") Long idUtente){
        return ResponseEntity.ok(utenteService.dammiProdottiUtente(idUtente));
    }


    @Operation(description = "Salva un nuovo utente")
    @ApiResponse(description = "Utente salvato con successo", responseCode = "200")

    @PostMapping("/salva")
    public HttpStatus salva(@RequestBody /*@Valid*/ UtenteDto u){
        utenteService.salvaDto(u);
        return HttpStatus.OK;
    }


    @Operation(description = "Aggiorna il token di accesso")

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

    @GetMapping("/test-lang")
    public ResponseEntity<String> testLang() {

        return ResponseEntity.ok("Ciao");
    }



}
