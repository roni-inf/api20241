package br.com.serratec.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

import br.com.serratec.entity.Pessoa;
import br.com.serratec.repository.PessoaRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {
	@Autowired
	private PessoaRepository repository;

	@GetMapping
	public List<Pessoa> listar() {
		return repository.findAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Pessoa inserir(@Valid @RequestBody Pessoa pessoa) {
		return repository.save(pessoa);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Pessoa> buscar(@PathVariable UUID id) {
	Optional<Pessoa> pessoa =	repository.findById(id);
		if (pessoa.isPresent()) {
			return ResponseEntity.ok(pessoa.get());
		}
			return ResponseEntity.notFound().build();
	}
	
	
}
