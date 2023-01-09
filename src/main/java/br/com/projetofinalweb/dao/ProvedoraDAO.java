package br.com.projetofinalweb.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.projetofinalweb.beans.Provedora;

public interface ProvedoraDAO extends CrudRepository<Provedora, Integer> {

	public List<Provedora> findByNomeStartingWith (String nome);
	//select * from tb_provedora
	//where nome like 'disney%'
	
	public List<Provedora> findByFundacaoBetween(Date dataInicio, Date dataFim);
	
	public List<Provedora> findByFundacaoBetweenAndNomeStartingWith (Date dataInicio, Date dataFim, String nome );
}
