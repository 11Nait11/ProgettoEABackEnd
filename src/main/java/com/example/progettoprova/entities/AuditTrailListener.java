package com.example.progettoprova.entities;

import jakarta.persistence.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Slf4j
public class AuditTrailListener {


    @PostPersist
    @PostUpdate
    @PostRemove
    private void afterAnyUpdate(Object object) {
        if (object instanceof Utente) {
            Utente utente = (Utente) object;
            log.info("[AUDIT] inserisci/aggiorna/cancella completata per Utente: " + utente.getId());
        } else if (object instanceof Prodotto) {
            Prodotto prodotto = (Prodotto) object;
            log.info("[AUDIT] inserisci/aggiorna/cancella completata per Prodotto: " + prodotto.getId());
        } else if (object instanceof Recensione) {
            Recensione recensione = (Recensione) object;
            log.info("[AUDIT] inserisci/aggiorna/cancella completata per Recensione: " + recensione.getId());
        }
    }

    @PostLoad
    private void afterLoad(Object object) {
        if (object instanceof Utente) {
            Utente u = (Utente) object;
            log.info("[AUDIT] utente caricato nel database: " + u.getId());
        } else if (object instanceof Prodotto) {
            Prodotto p = (Prodotto) object;
            log.info("[AUDIT] prodotto caricato nel database: " + p.getId());
        } else if (object instanceof Recensione) {
            Recensione r = (Recensione) object;
            log.info("[AUDIT] recensione caricata nel database: " + r.getId());
        }
    }
}

