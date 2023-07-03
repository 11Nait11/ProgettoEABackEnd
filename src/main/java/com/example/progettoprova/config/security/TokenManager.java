package com.example.progettoprova.config.security;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.nimbusds.jose.proc.BadJOSEException;
import com.nimbusds.jose.proc.JWSKeySelector;
import com.nimbusds.jose.proc.JWSVerificationKeySelector;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.text.ParseException;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public abstract class TokenManager {

    private static SecretKey SECRET = new SecretKeySpec(Base64.getDecoder().decode(SecurityConstants.JWT_SECRET), "HmacSHA256");
    static Long userId=0L;
    public static String createAccessToken(String username, String issuer, List<String> roles, Long userId) {
        try {
            JWTClaimsSet claims = new JWTClaimsSet.Builder()
                    .subject(username)
                    .issuer(issuer)
                    .claim("roles", roles)
                    .claim("userId", userId)
                    .expirationTime(Date.from(Instant.now().plusSeconds(SecurityConstants.EXPIRATION_TIME)))
                    .issueTime(new Date())
                    .build();

            Payload payload = new Payload(claims.toJSONObject());

            JWSObject jwsObject = new JWSObject(new JWSHeader(JWSAlgorithm.HS256),
                    payload);

            jwsObject.sign(new MACSigner(SECRET));
            return jwsObject.serialize();
        }
        catch (JOSEException e) {
            throw new RuntimeException("Error to create JWT", e);
        }
    }

    public static String createRefreshToken(String username) {
        //like createAccessToken method, but without issuer, roles...

        try {
            JWTClaimsSet claims = new JWTClaimsSet.Builder()
                .subject(username)
                .expirationTime(Date.from(Instant.now().plusSeconds(SecurityConstants.EXPIRATION_REFRESH_TOKEN_TIME)))
                .issueTime(new Date())
                .build();

            Payload payload = new Payload(claims.toJSONObject());

            JWSObject jwsObject = new JWSObject(new JWSHeader(JWSAlgorithm.HS256),
                payload);

            jwsObject.sign(new MACSigner(SECRET));
            return jwsObject.serialize();
        }
        catch (JOSEException e) {
            throw new RuntimeException("Error to create JWT", e);
        }
    }

    public static UsernamePasswordAuthenticationToken parseToken(String token) throws JOSEException, BadJOSEException, ParseException {
        // Passo 1: Analizza il token firmato
        SignedJWT signedJWT = SignedJWT.parse(token);

        // Passo 2: Verifica la firma del token utilizzando un verificatore MAC con una chiave segreta nota come SECRET
        //qui potrebbe generare espressione di token scaduto
        signedJWT.verify(new MACVerifier(SECRET));

        // Passo 3: Crea un processore JWT configurabile con un contesto di sicurezza
        ConfigurableJWTProcessor<SecurityContext> jwtProcessor = new DefaultJWTProcessor<>();

        // Passo 4: Seleziona il selettore chiave JWS per la verifica con l'algoritmo di firma HS256 e la chiave segreta SECRET
        JWSKeySelector<SecurityContext> keySelector = new JWSVerificationKeySelector<>(JWSAlgorithm.HS256, new ImmutableSecret<>(SECRET));
        jwtProcessor.setJWSKeySelector(keySelector);

        // Passo 5: Processa il token firmato utilizzando il processore JWT configurato e il selettore chiave
        jwtProcessor.process(signedJWT, null);

        // Passo 6: Ottiene le affermazioni JWT dal token firmato
        JWTClaimsSet claims = signedJWT.getJWTClaimsSet();

        // Passo 7: Ottiene il nome utente dall'oggetto JWTClaimsSet
        String username = claims.getSubject();

        // Passo 8: Ottiene i ruoli dall'oggetto JWTClaimsSet
        var roles = (List<String>) claims.getClaim("roles");

        // Passo 9: Converte i ruoli in autorizzazioni (SimpleGrantedAuthority) utilizzando la programmazione funzionale
        var authorities = roles == null ? null : roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        // Passo 10: Ottiene l'ID utente dall'oggetto JWTClaimsSet
        userId = claims.getLongClaim("userId");

        // Passo 11: Restituisce un oggetto UsernamePasswordAuthenticationToken con il nome utente, nessuna password e le autorizzazioni ottenute dai ruoli
        return new UsernamePasswordAuthenticationToken(username, null, authorities);
    }

    public static String[] decodedBase64(String token) {

        byte[] decodedBytes = Base64.getDecoder().decode(token);
        String pairedCredentials = new String(decodedBytes);
        String[] credentials = pairedCredentials.split(":", 2);

        return credentials;
    }
    public static boolean checkId(Long id){return id==userId;}
}