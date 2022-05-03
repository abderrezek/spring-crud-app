package com.crud.CrudApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.crud.CrudApp.entities.Etudiant;
import com.crud.CrudApp.entities.Sexe;
import com.crud.CrudApp.form.EtudiantForm;
import com.crud.CrudApp.repositories.EtudiantRepository;

@Service
public class EtudiantService implements IEtudiantService {

	private final EtudiantRepository etudiantRepository;
	
	@Autowired
	public EtudiantService(EtudiantRepository etudiantRepository) {
		this.etudiantRepository = etudiantRepository;
	}

	@Override
	public Page<Etudiant> getAll(int page, int size) {
		return etudiantRepository.findAll(PageRequest.of(page, size));
	}
	
	@Override
	public Page<Etudiant> getBy(String q, int page, int size) {
		return etudiantRepository.findByNomContaining(q, PageRequest.of(page, size));
	}

	@Override
	public Etudiant save(EtudiantForm etf) {
		Etudiant et = new Etudiant();
		et.setNom(etf.getNom());
		et.setPrenom(etf.getPrenom());
		et.setDateNaissance(etf.getDateNaissance());
		et.setLieuNaissance(etf.getLieuNaissance());
		Sexe s = etf.getSexe() == 0 ? Sexe.homme : Sexe.femme;
		et.setSexe(s);
		Etudiant persistEtudiant = etudiantRepository.save(et);
		return persistEtudiant;
	}

	@Override
	public Etudiant getByNomAndPrenom(String nom, String prenom) {
		return etudiantRepository.findByNomAndPrenom(nom, prenom)
				.orElse(null);
	}

	@Override
	public Etudiant update(EtudiantForm etf) {
		Etudiant et = new Etudiant();
		et.setId(etf.getId());
		et.setNom(etf.getNom());
		et.setPrenom(etf.getPrenom());
		et.setDateNaissance(etf.getDateNaissance());
		et.setLieuNaissance(etf.getLieuNaissance());
		Sexe s = etf.getSexe() == 0 ? Sexe.homme : Sexe.femme;
		et.setSexe(s);
		Etudiant persistEtudiant = etudiantRepository.save(et);
		return persistEtudiant;
	}

	@Override
	public void deleteById(Long id) {
		etudiantRepository.deleteById(id);
	}

}
