package com.sb.formation.service;

import com.sb.formation.entities.Utilisateur;
import com.sb.formation.reponses.MessageResponse;
import com.sb.formation.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UtilisateurServiceImpl implements UtilisateurService{
    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Transactional
    @Override
    public MessageResponse save(Utilisateur utilisateur) {
        boolean existe = utilisateurRepository.existsByLogin(utilisateur.getLogin());
        if (existe){
            return new MessageResponse(false,"Echec !","Cette utilisateur existe déja !");
        }
        utilisateurRepository.save(utilisateur);
        return new MessageResponse(true,"Succès","Opération réalisée avec succès.");
    }

    @Transactional
    @Override
        public MessageResponse update(Utilisateur utilisateur) {
        boolean existe = utilisateurRepository.existsById(utilisateur.getCode());
        if (!existe){
            boolean existe1 = utilisateurRepository.existsByLogin(utilisateur.getLogin());
            return new MessageResponse(false,"Echec !","Cette utilisateur existe déja !");
        }
        utilisateurRepository.save(utilisateur);
        return new MessageResponse(true,"Succès","Opération réalisée avec succès.");
    }

    @Transactional
    @Override
    public MessageResponse delete(Long code) {
        Utilisateur utilisateur = findByCode(code);
        if (utilisateur==null){
            return new MessageResponse(false,"Echec","Cet enregistrement n'existe pas !");
        }
        utilisateurRepository.delete(utilisateur);
        return new MessageResponse(true,"Succès", "L'enregistrement à été supprimé avec succès.");
    }

    @Transactional
    @Override
    public List<Utilisateur> findAll() {
        return utilisateurRepository.findAll();
    }

    @Transactional
    @Override
    public Utilisateur findByCode(Long code) {
        Utilisateur utilisateur = utilisateurRepository.findById(code).orElse(null);
        return utilisateur;
    }
}
