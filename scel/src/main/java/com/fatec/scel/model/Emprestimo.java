package com.fatec.scel.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.persistence.*;

@Entity
public class Emprestimo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "isbn", nullable = false, length = 4)
	@NotEmpty(message = "O isbn deve ser preenchido")
	private String isbn;
	@Column(name = "ra", nullable = false, length = 4)
	@NotEmpty(message = "O RA deve ser preenchido")
	private String ra;
	private String usuario;
	private String dataEmprestimo;
	private String dataDevolucao;
	private String dataDevolucaoPrevista;
	
	// data do emprestimo - data atual do sistema
	public void setDataEmprestimo( ) {
	
		DateTime dataAtual = new DateTime();
		DateTimeFormatter fmt = DateTimeFormatter.forPattern("YYYY/MM/dd");
		this.dataEmprestimo = dataAtual.toString(fmt);
		public String setdataDevolucaoPrevista() {
			return null;
		
	}
	public void setRA(String i) {
		isbn = i;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setDataEmprestimo(String dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}

	public String getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(String dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public String getDataDevolucaoPrevista() {
		return dataDevolucaoPrevista;
	}

	public void setDataDevolucaoPrevista(String dataDevolucaoPrevista) {
		this.dataDevolucaoPrevista = dataDevolucaoPrevista;
	}
}