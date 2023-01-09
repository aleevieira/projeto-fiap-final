package br.com.projetofinalweb.dao;

import org.springframework.data.repository.CrudRepository;

import br.com.projetofinalweb.beans.Serie;

public interface SerieDAO extends CrudRepository<Serie, Integer> {

}
