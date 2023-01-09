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

import br.com.projetofinalweb.beans.Provedora;
import br.com.projetofinalweb.dao.ProvedoraDAO;
import br.com.projetofinalweb.dto.ProvedoraFiltro;

@RestController
@CrossOrigin("*")
public class ProvedoraController {

	@Autowired
	private ProvedoraDAO dao;

	@GetMapping("/provedora")
	public ResponseEntity<List<Provedora>> listarProvedoras() {

		List<Provedora> resultado = (List<Provedora>) dao.findAll();

		if (resultado.size() == 0) {
			return ResponseEntity.status(404).build();

		}
		return ResponseEntity.ok(resultado);
	}

	@GetMapping("/provedora/{codigo}")
	public ResponseEntity<Provedora> buscarPorId(@PathVariable int codigo) {
		Provedora provedoraBuscado = dao.findById(codigo).orElse(null);

		if (provedoraBuscado == null) {
			return ResponseEntity.status(404).build();
		}

		return ResponseEntity.ok(provedoraBuscado);
	}

	@PostMapping("/provedora")
	public ResponseEntity<Provedora> criarProvedora(@RequestBody Provedora provedora) {
		try {
			dao.save(provedora);
			return ResponseEntity.ok(provedora);
		} catch (Exception excecao) {
			excecao.printStackTrace();
			return ResponseEntity.status(500).build();
		}
	}

	@PostMapping("/provedora/filter")
	public ResponseEntity<List<Provedora>> filtrarProvedora(@RequestBody ProvedoraFiltro filtro) {

		if (filtro.isFiltroDataFundacao() && filtro.getDataInicialFundacao() == null
				|| filtro.getDataFinalFundacao() == null) {
			return ResponseEntity.status(400).build();
		}
		if (filtro.isFiltroNomeProvedora() && filtro.getNomeProvedora() == null) {
			return ResponseEntity.status(400).build();
		}
		if (filtro.isFiltroDataFundacao() && !filtro.isFiltroNomeProvedora()) {
			List<Provedora> resultado = (List<Provedora>) dao.findByFundacaoBetween(filtro.getDataInicialFundacao(),
					filtro.getDataFinalFundacao());
			return ResponseEntity.ok(resultado);
		}
		else if (filtro.isFiltroNomeProvedora() && !filtro.isFiltroDataFundacao()) {
			List<Provedora> resultado = (List<Provedora>) dao.findByNomeStartingWith(filtro.getNomeProvedora());
			return ResponseEntity.ok(resultado);
		} 
		else if (filtro.isFiltroDataFundacao() && filtro.isFiltroNomeProvedora()) {
			List<Provedora> resultado = (List<Provedora>) dao.findByFundacaoBetweenAndNomeStartingWith(
					filtro.getDataInicialFundacao(), filtro.getDataFinalFundacao(), filtro.getNomeProvedora());
			return ResponseEntity.ok(resultado);
		}
		else {
			return ResponseEntity.status(400).build();
		}
	}
}
