package br.com.serratec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.entity.Veiculo;
import br.com.serratec.repository.VeiculoRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {
	@Autowired
	private VeiculoRepository repository;

	@GetMapping
	public List<Veiculo> listar() {
		return repository.findAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Veiculo inserir(@Valid @RequestBody Veiculo veiculo) {
		return repository.save(veiculo);
	}
	
}
