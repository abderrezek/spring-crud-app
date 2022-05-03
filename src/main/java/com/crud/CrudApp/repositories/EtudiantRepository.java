package com.crud.CrudApp.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crud.CrudApp.entities.Etudiant;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
	
	Optional<Etudiant> findByNomAndPrenom(String nom, String prenom);
	Page<Etudiant> findByNomContaining(String nom, Pageable pageable);
	
}
