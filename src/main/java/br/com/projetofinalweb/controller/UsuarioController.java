package br.com.projetofinalweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetofinalweb.beans.Usuario;
import br.com.projetofinalweb.dao.UsuarioDAO;

@RestController
@CrossOrigin("*")
public class UsuarioController {

	@Autowired
	private UsuarioDAO dao;

	@GetMapping("/usuario")
	public ResponseEntity<List<Usuario>> listarTodos(@RequestParam(required = false) String nome,
			@RequestParam(required = false) String email) {

		System.out.println(nome);
		System.out.println(email);
		
		//se não veio parametros: lista todos os usuarios
		//se veio parametro, faço o filtro

		if (nome == null | nome.isEmpty()) {

			List<Usuario> lista = (List<Usuario>) dao.findAll();
			if (lista.size() == 0) {
				return ResponseEntity.status(404).build();
			
		}
		return ResponseEntity.ok(lista);
		
		} else {
			List <Usuario> lista = (List<Usuario>) dao.listByNomeLike(nome);
			if (lista.size() == 0) {
				return ResponseEntity.status(404).build();
			
		}
			return ResponseEntity.ok(lista);
		}
	}

	@GetMapping("/usuario/{codigo}")
	public ResponseEntity<Usuario> buscarPorId(@PathVariable int codigo) {

		Usuario usuarioPesquisado = dao.findById(codigo).orElse(null);

		if (usuarioPesquisado == null) {
			return ResponseEntity.status(404).build();
		}
		return ResponseEntity.ok(usuarioPesquisado);
	}

	@PostMapping("/usuario")
	public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario) {
		try {
			dao.save(usuario);
			return ResponseEntity.ok(usuario);
		} catch (Exception excecao) {
			excecao.printStackTrace();
			return ResponseEntity.status(500).build();
		}
	}

	@PostMapping("/login")
	public ResponseEntity<Usuario> efetuarLogin(@RequestBody Usuario usuario) {
		Usuario usuarioPesquisado = (Usuario) dao.findByEmailAndSenha(usuario.getEmail(), usuario.getSenha());

		if (usuarioPesquisado == null) {
			return ResponseEntity.status(404).build();
		}
		return ResponseEntity.ok(usuarioPesquisado);
	}

	@PutMapping("/usuario/{codigo}")
	public ResponseEntity<Usuario> atualizarUsuario(@PathVariable int codigo, @RequestBody Usuario usuario) {

		try {
			Usuario usuarioBuscado = dao.findById(codigo).orElse(null);

			if (usuarioBuscado == null) {
				return ResponseEntity.status(404).build();
			}

			usuario.setId(codigo); // salva no usuario o que vem no path variable, pq os dois não estão conectados
									// até agora
			dao.save(usuario);
			return ResponseEntity.ok(usuario);

		} catch (Exception excecao) {
			excecao.printStackTrace();
			return ResponseEntity.status(500).build();
		}

	}
	
	@DeleteMapping ("/usuario/{codigo}")
	public ResponseEntity<Usuario> deletarUsuario(@PathVariable int codigo){
		try {
			Usuario usuarioBuscado = dao.findById(codigo).orElse(null);
			
			if (usuarioBuscado == null) {
				return ResponseEntity.status(404).build();
			}
			dao.delete(usuarioBuscado);
			return ResponseEntity.ok(usuarioBuscado);
		}
		catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(500).build();
		}
	}
	
}
