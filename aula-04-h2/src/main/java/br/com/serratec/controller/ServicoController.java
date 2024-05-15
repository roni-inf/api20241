package br.com.serratec.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.entity.Servico;
import br.com.serratec.repository.ServicoRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/servicos")
public class ServicoController {
	@Autowired
	private ServicoRepository repository;

	@GetMapping
	public List<Servico> listar() {
		return repository.findAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Servico inserir(@Valid @RequestBody Servico servico) {
		return repository.save(servico);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Servico> buscar(@PathVariable Long id) {
	Optional<Servico> servico =	repository.findById(id);
		if (servico.isPresent()) {
			return ResponseEntity.ok(servico.get());
		}
			return ResponseEntity.notFound().build();
	}
	
	
}
