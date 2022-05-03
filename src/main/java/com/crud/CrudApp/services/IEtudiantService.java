package com.crud.CrudApp.services;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.crud.CrudApp.entities.Etudiant;
import com.crud.CrudApp.form.EtudiantForm;

@Service
public interface IEtudiantService {
	
	Etudiant getByNomAndPrenom(String nom, String prenom);
	Page<Etudiant> getAll(int page, int size);
	Page<Etudiant> getBy(String q, int page, int size);
	
	Etudiant save(EtudiantForm etudiantForm);
	Etudiant update(EtudiantForm etudiantForm);
	
	void deleteById(Long id);

}
