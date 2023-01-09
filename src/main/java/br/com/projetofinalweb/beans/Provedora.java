package br.com.projetofinalweb.beans;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table (name="TB_PROVEDORA")
public class Provedora {
	
	@Column (name="id")
	@Id
	@GeneratedValue (strategy= GenerationType.IDENTITY)
	private int id;
	
	@Column (name="nome", length = 50)
	private String nome;
	
	@Column (name="site", length = 50)
	private String site;
	
	@Column (name="dt_fundacao" )
	@Temporal (TemporalType.DATE)
	@JsonFormat (shape= Shape.STRING, pattern = "dd/mm/yyyy")
	private Date fundacao;
	
	@OneToMany (mappedBy = "provedora", cascade = CascadeType.ALL)
	@JsonIgnoreProperties ("provedora")
	private List <Serie> series;
	
	public void setSeries(List<Serie> series) {
		this.series = series;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public Date getFundacao() {
		return fundacao;
	}
	public void setFundacao(Date fundacao) {
		this.fundacao = fundacao;
	}
	public List<Serie> getSeries() {
		return series;
	}

}
