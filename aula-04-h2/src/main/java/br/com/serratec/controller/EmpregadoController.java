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

import br.com.serratec.entity.Empregado;
import br.com.serratec.repository.EmpregadoRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/empregados")
public class EmpregadoController {
	@Autowired
	private EmpregadoRepository repository;

	@GetMapping
	public List<Empregado> listar() {
		return repository.findAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Empregado inserir(@Valid @RequestBody Empregado empregado) {
		return repository.save(empregado);
	}
	
	//CRIAR UM MÉTODO PARA INSERIR VÁRIOS EMPREGADOS AO MESMO TEMPO
	
	@PostMapping("/varios")
	@ResponseStatus(HttpStatus.CREATED)
	public List<Empregado> inserirEmpregado (@Valid @RequestBody List<Empregado> empregados)
	{
		return repository.saveAll(empregados);
	}
	
}
