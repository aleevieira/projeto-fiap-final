package br.com.projetofinalweb.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.projetofinalweb.beans.Usuario;

public interface UsuarioDAO extends CrudRepository<Usuario, Integer> {
	
	public Usuario findByEmailAndSenha (String email, String senha);

	
	@Query(
			value= "SELECT id, email, nome, foto, senha FROM tb_usuario WHERE nome LIKE %:nome%", // pq esse dois pontos?
			nativeQuery = true
			)
	public List <Usuario> listByNomeLike (String nome);
}
