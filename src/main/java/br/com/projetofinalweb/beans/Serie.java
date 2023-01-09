package br.com.projetofinalweb.beans;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table (name="TB_SERIE")
public class Serie {
	
	
	@Column(name="id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="titulo", length = 70)
	private String titulo;
	
	@Column(name="genero", length = 50)
	private String genero;
	
	@Column(name="sinopse", length = 200)
	private String sinopse;
	
	@Column(name="lancamento")
	private boolean lancamento;
	
	@ManyToOne 
	@JsonIgnoreProperties ("series")
	private Provedora provedora;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getSinopse() {
		return sinopse;
	}
	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}
	public boolean isLancamento() {
		return lancamento;
	}
	public void setLancamento(boolean lancamento) {
		this.lancamento = lancamento;
	}
	public Provedora getProvedora() {
		return provedora;
	}
	public void setProvedora(Provedora provedora) {
		this.provedora = provedora;
	}

}
