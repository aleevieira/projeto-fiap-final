package br.com.projetofinalweb.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public class ProvedoraFiltro {

	private boolean filtroNomeProvedora;
	
	private boolean filtroDataFundacao;
	
	private String nomeProvedora;
	
	@JsonFormat (shape = Shape.STRING, pattern = "dd/mm/yyyy")
	private Date dataInicialFundacao;
	
	@JsonFormat (shape = Shape.STRING, pattern = "dd/mm/yyyy")
	private Date dataFinalFundacao;

	public boolean isFiltroNomeProvedora() {
		return filtroNomeProvedora;
	}

	public void setFiltroNomeProvedora(boolean filtroNomeProvedora) {
		this.filtroNomeProvedora = filtroNomeProvedora;
	}

	public boolean isFiltroDataFundacao() {
		return filtroDataFundacao;
	}

	public void setFiltroDataFundacao(boolean filtroDataFundacao) {
		this.filtroDataFundacao = filtroDataFundacao;
	}

	public String getNomeProvedora() {
		return nomeProvedora;
	}

	public void setNomeProvedora(String nomeProvedora) {
		this.nomeProvedora = nomeProvedora;
	}

	public Date getDataInicialFundacao() {
		return dataInicialFundacao;
	}

	public void setDataInicialFundacao(Date dataInicialFundacao) {
		this.dataInicialFundacao = dataInicialFundacao;
	}

	public Date getDataFinalFundacao() {
		return dataFinalFundacao;
	}

	public void setDataFinalFundacao(Date dataFinalFundacao) {
		this.dataFinalFundacao = dataFinalFundacao;
	}
	

}
