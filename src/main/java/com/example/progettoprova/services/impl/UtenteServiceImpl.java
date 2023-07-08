package com.example.progettoprova.services.impl;


import com.example.progettoprova.config.security.TokenManager;
import com.example.progettoprova.config.security.UserDetailsImpl;
import com.example.progettoprova.dao.MessaggioDao;
import com.example.progettoprova.dao.UtenteDao;
import com.example.progettoprova.dto.ProdottoDto;
import com.example.progettoprova.dto.RecensioneDto;
import com.example.progettoprova.dto.UtenteDto;
import com.example.progettoprova.entities.Messaggio;
import com.example.progettoprova.entities.Prodotto;
import com.example.progettoprova.entities.Recensione;
import com.example.progettoprova.entities.Utente;
import com.example.progettoprova.exception.ProdottoException;
import com.example.progettoprova.exception.UtenteException;
import com.example.progettoprova.config.MessagesConfig;
import com.example.progettoprova.services.UtenteService;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.proc.BadJOSEException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UtenteServiceImpl implements UtenteService {

    private final UtenteDao utenteDao;
    private final MessaggioDao messaggioDao;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    @SneakyThrows//ok
    public List<UtenteDto> dammiUtenti() {
        List<Utente> utenti = utenteDao.findAll();
        if (utenti.isEmpty())
            throw new UtenteException(MessagesConfig.UTENTI_NON_TROVATI);
        List<UtenteDto> utendto = utenti.stream().map(u -> modelMapper.map(u, UtenteDto.class)).collect(Collectors.toList());
        return utendto;
    }

    @Override
    @SneakyThrows//ok
    public UtenteDto dammiUtente(Long id) {
        log.info("dammi utente id "+id);
        if (!TokenManager.checkId(id))
            throw new UtenteException(MessagesConfig.UTENTE_NON_AUTORIZZATO);
        log.info("cechkl ok ");
        Utente utente = utenteDao.findById(id).orElseThrow(() -> new UtenteException(MessagesConfig.UTENTE_NON_TROVATO_ID + id));
        return modelMapper.map(utente, UtenteDto.class);
    }

    @Override
    @SneakyThrows
    public Utente dammiEntityUtente(Long id) {
        Utente utente = utenteDao.findById(id).orElseThrow(() -> new UtenteException(MessagesConfig.UTENTE_NON_TROVATO_ID + id));
        return utente;
    }

    @Override
    @SneakyThrows
    public UtenteDto dammiUtenteByUsername(String username) {
        return modelMapper.map(
                utenteDao.dammiUtenteByUsername(username)
                        .orElseThrow(() -> new UtenteException(MessagesConfig.UTENTE_NON_TROVATO_USERNAME + username)),
                UtenteDto.class);
    }

    @Override
    @SneakyThrows
    public List<ProdottoDto> dammiProdottiUtente(Long id) {

        List<Prodotto> prodotti = utenteDao.cercaProdottiByIdUtente(id);
        if (prodotti.isEmpty())
            throw new ProdottoException(MessagesConfig.PRODOTTI_NON_TROVATI);

        return prodotti.stream().map(prodotto -> modelMapper.map(prodotto, ProdottoDto.class)).collect(Collectors.toList());

    }

    @Override
    @SneakyThrows
    public List<RecensioneDto> dammiRecensioniUtente(Long id) {

        List<Recensione> recensioni = utenteDao.dammiRecensioni(id);
        if (recensioni.isEmpty())
            throw new UtenteException(MessagesConfig.UTENTE_RECENSIONE_ID + id);

        return recensioni.stream().map(recensione -> modelMapper.map(recensione, RecensioneDto.class)).collect(Collectors.toList());
    }


    @Override
    public void salva(Utente u) {
        u.setPassword(passwordEncoder.encode(u.getPassword()));
        if(u.getRoles()==null)
            u.setRoles("BASIC");

        utenteDao.save(u);
        log.info(MessagesConfig.UTENTE_SALVATO_NOME_LOG, u.getCognome());
    }


    @Override
    public void salvaDto(UtenteDto uD) {
        String username= uD.getEmail();
        uD.setPassword(passwordEncoder.encode(uD.getPassword()));
        if(uD.getRoles()==null)
            uD.setRoles("BASIC");
        Utente u = modelMapper.map(uD, Utente.class);
        utenteDao.save(u);

        Utente admin = modelMapper.map(dammiUtenteByUsername("admin@email.it"),Utente.class);
        Utente utente = modelMapper.map(dammiUtenteByUsername(username),Utente.class);//necessario richiamarlo dal db

        Messaggio benvenuto=new Messaggio();
        benvenuto.setMittente(admin);
        benvenuto.setDestinatario(utente);
        benvenuto.setTesto(MessagesConfig.WELCOME);
        benvenuto.setDataInvio(LocalDateTime.now());
        messaggioDao.save(benvenuto);

        log.info(MessagesConfig.UTENTE_SALVATO_NOME_LOG, u.getCognome());
    }


    @Override
    @SneakyThrows
    public void cancella(Long id) {
        Optional<Utente> u = utenteDao.findById(id);
        if (u.isEmpty())
            throw new UtenteException(MessagesConfig.UTENTE_NON_TROVATO_ID + id);
        utenteDao.delete(u.get());
        log.info(MessagesConfig.UTENTE_CANCELLATO_ID_LOG, id);

    }


    @Override
    @SneakyThrows
    public UtenteDto aggiorna(Long id, UtenteDto utente) {
        Optional<Utente> u = utenteDao.findById(id);
        if (u.isEmpty())
            throw new UtenteException(MessagesConfig.UTENTE_NON_TROVATO_ID + id);

//        u.get().setNome(utente.getNome());
//        u.get().setCognome(utente.getCognome());
        log.info(MessagesConfig.UTENTE_AGGIORNATO_ID_LOG, id);
        return modelMapper.map(utenteDao.save(u.get()), UtenteDto.class);
    }


    @Transactional(readOnly = true)
    @SneakyThrows
    public Map<String, String> refreshToken(String authorizationHeader, String issuer) {
        String refreshToken = authorizationHeader.substring("Bearer ".length());
        UsernamePasswordAuthenticationToken authenticationToken = TokenManager.parseToken(refreshToken);//se il token e' valido
        String username = authenticationToken.getName();//prendo user dal db
        Utente user = utenteDao.dammiUtenteByUsername(username).orElseThrow(() -> new UtenteException(MessagesConfig.UTENTE_NON_TROVATO_USERNAME + username));
        UserDetailsImpl loggedUserDetails = new UserDetailsImpl(modelMapper.map(user, UtenteDto.class));
        String accessToken =
                TokenManager.createAccessToken(
                        loggedUserDetails.getUsername(), issuer,
                        loggedUserDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()), 0L
                );
        return Map.of(
                "access_token", accessToken,
                "refresh_token", refreshToken
        );

    }
}
