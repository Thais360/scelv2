package com.fatec.scel.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.persistence.Column;
// data do emprestimo - data atual do sistema
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

	@Entity
	public class Emprestimo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	 private Long id;
	
	@Column(name="isbn", nullable = false, length=4)
	@NotEmpty(message="O isbn deve ser preenchido")
	 private String isbn;
	
	 @Column(name="ra", nullable = false, length=4)
	 @NotEmpty(message="O RA deve ser preenchido")
	 private String ra;
	 
	 private String usuario; 
	 private String dataEmprestimo;
	 private String dataDevolucao;
	 private String dataDevolucaoPrevista;
	public void setRA(String i) {
		setIsbn(i);
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

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}



	public String getUsuario() {
		return usuario;
	}



	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public void setDataEmprestimo( ) {
		 DateTime dataAtual = new DateTime();
		 DateTimeFormatter fmt = DateTimeFormat.forPattern("YYYY/MM/dd");
		 this.dataEmprestimo = dataAtual.toString(fmt);
		 setDataDevolucaoPrevista();
		}
	private void setDataDevolucaoPrevista() {
		 DateTimeFormatter fmt = DateTimeFormat.forPattern("YYYY/MM/dd");
		 DateTime data = fmt.parseDateTime(getDataEmprestimo());
		 this.dataDevolucaoPrevista = data.plusDays(8).toString(fmt);
		}
	public boolean ehDomingo(String data) {
		 boolean isValida = false;
		 DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy/MM/dd");
		 if (validaData(data) == true) {
		 DateTime umaData = fmt.parseDateTime(data);
		 if (umaData.dayOfWeek().getAsText().equals("Domingo")) {
		 isValida = true;
		 }
		 }
		 return isValida;
		}
	public boolean validaData(String data) {
		 DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		 df.setLenient(false); //
		 try {
		 df.parse(data); // data válida
		 return true;
		 } catch (ParseException ex) {
		 return false;
		 }
		}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getRa() {
		return ra;
	}



	public void setRa(String ra) {
		this.ra = ra;
	}
	public Emprestimo(String isbn, String ra) {
		 this.isbn = isbn;
		 this.ra = ra;
		 setDataEmprestimo();
		}
}