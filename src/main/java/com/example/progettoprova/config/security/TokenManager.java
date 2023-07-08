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
import io.jsonwebtoken.ExpiredJwtException;
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


    public static UsernamePasswordAuthenticationToken parseToken(String token) throws JOSEException, BadJOSEException, ParseException {

        // Analizza il token firmato
        SignedJWT signedJWT = SignedJWT.parse(token);
        // Verifica la firma del token utilizzando  MAC con una chiave segreta
        signedJWT.verify(new MACVerifier(SECRET));


        Date expirationTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        Date currentTime = new Date();
        if (expirationTime != null && currentTime.after(expirationTime)) {
            throw new ExpiredJwtException(null,null,"Token Scaduto");
        }

        ConfigurableJWTProcessor<SecurityContext> jwtProcessor = new DefaultJWTProcessor<>();
        JWSKeySelector<SecurityContext> keySelector = new JWSVerificationKeySelector<>(JWSAlgorithm.HS256, new ImmutableSecret<>(SECRET));
        jwtProcessor.setJWSKeySelector(keySelector);
        jwtProcessor.process(signedJWT, null);


        JWTClaimsSet claims = signedJWT.getJWTClaimsSet();
        // username
        String username = claims.getSubject();
        // role
        var roles = (List<String>) claims.getClaim("roles");
        // role->authorities
        var authorities = roles == null ? null : roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        userId = claims.getLongClaim("userId");

        // oggetto UsernamePasswordAuthenticationToken con nome utente, nessuna password
        return new UsernamePasswordAuthenticationToken(username, null, authorities);
    }



    public static String createAccessToken(String username, String issuer, List<String> roles, Long userId) {//rimuovere userId
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

    public static String[] decodedBase64(String token) {

        byte[] decodedBytes = Base64.getDecoder().decode(token);
        String pairedCredentials = new String(decodedBytes);
        String[] credentials = pairedCredentials.split(":", 2);

        return credentials;
    }
    public static boolean checkId(Long id){
        log.info("id "+id);
        log.info("userID "+userId);

        if(id==userId)
            log.info("true");
        else
            log.info("false");

        return id==userId;}
}