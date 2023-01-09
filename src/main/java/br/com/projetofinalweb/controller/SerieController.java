package br.com.projetofinalweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetofinalweb.beans.Serie;
import br.com.projetofinalweb.dao.SerieDAO;

@RestController
@CrossOrigin("*")
public class SerieController {

	@Autowired
	private SerieDAO dao;

	@GetMapping("/serie")
	public ResponseEntity<List<Serie>> listarSeries() {
		List<Serie> resultado = (List<Serie>) dao.findAll();

		if (resultado.size() == 0) {
			return ResponseEntity.status(404).build();
		}
		return ResponseEntity.ok(resultado);

	}

	@GetMapping("/serie/{codigo}")
	public ResponseEntity<Serie> buscarPorId(@PathVariable int codigo) {
		Serie serieBuscado = dao.findById(codigo).orElse(null);

		if (serieBuscado == null) {
			return ResponseEntity.status(404).build();
		}
		return ResponseEntity.ok(serieBuscado);
	}

	@PostMapping("/serie")
	public ResponseEntity<Serie> criarSerie(@RequestBody Serie serie) {
		try {
			dao.save(serie);
			return ResponseEntity.ok(serie);
		} catch (Exception excecao) {
			excecao.printStackTrace();
			return ResponseEntity.status(500).build();
		}
	}
}
