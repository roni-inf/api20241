package br.com.serratec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.entity.Funcionario;
import br.com.serratec.service.FuncionarioService;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

	@Autowired
	private FuncionarioService service;

	@GetMapping
	public List<Funcionario> listar() {
		return service.listar();
	}

	@GetMapping("/pagina")
	public Page<Funcionario> listarPorPagina(@PageableDefault(sort = "nome", size = 10) Pageable pageable) {
		return service.listarPorPagina(pageable);
	}

	@GetMapping("/salario")
	public ResponseEntity<Page<Funcionario>> buscarPorFaixaSalarial(
			@RequestParam(defaultValue = "0") Double valorInicial,
			@RequestParam(defaultValue = "100000") Double valorFinal, Pageable pageable) {

		Page<Funcionario> funcionarios = service.buscarPorFaixaSalarial(valorInicial, valorFinal, pageable);
		return ResponseEntity.ok(funcionarios);
	}

	@GetMapping("/buscarPorNome")
	public Page<Funcionario> buscarPorNome(@RequestParam String paramNome, Pageable pageable) {
		return service.buscarPorNome(paramNome, pageable);
	}

}
