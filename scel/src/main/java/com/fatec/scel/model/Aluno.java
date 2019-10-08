package com.fatec.scel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;

@Entity

public class Aluno {
	@Id
	
	String RA;
	String Nome;
	String Email;

	public String getRA() {
		return RA;
	}
	public void setRA(String RA) {
		RA = ra;
	}
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NaturalId
	@Column(nullable = false, length = 13)
	@NotEmpty(message="O RA deve ser preenchido") //o atributo nao pode ser nulo e o tamanho deve ser maior que zero
	private String ra ;
	
	@Column(nullable = false, length = 100)
	@NotEmpty(message="O Nome deve ser preenchido")
	private String nome;
	
	@Column(nullable = false)
	@NotNull(message="Email invalido")
    @Size(min = 1, max = 50, message="Email deve ter entre 1 e 50 caracteres")
	private String autor;
	
	public Aluno() {
	}

	public Aluno( String r, String n, String e) {
		this.ra = r;
		this.nome = n;
		this.Email = e;
	}
	public void setId(Long id2) {
		
	}
	public Aluno get() {
		return null;
	}
}
