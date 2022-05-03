package com.crud.CrudApp.form;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.crud.CrudApp.validations.OneOf;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class EtudiantForm {
	
	private Long id;
	@NotBlank(message = "Nom est requis")
	private String nom;
	@NotBlank(message = "Prénom est requis")
	private String prenom;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "Date de naissance est requis")
	private Date dateNaissance;
	@NotBlank(message = "Lieu de naissance est requis")
	private String lieuNaissance;
	@OneOf(values = { 0, 1 }, message = "Sexe n'est pas valide")
	private int sexe;

}
