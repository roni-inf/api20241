package br.com.serratec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.serratec.entity.Funcionario;
import br.com.serratec.repository.FuncionarioRepository;

@Service
public class FuncionarioService {
	@Autowired
	private FuncionarioRepository repository;

	public List<Funcionario> listar() {
		return repository.findAll();
	}

	public Page<Funcionario> listarPorPagina(Pageable pageable) {
		return repository.findAll(pageable);
	}

	public Page<Funcionario> buscarPorFaixaSalarial(Double valorInicial, Double valorFinal, Pageable pageable) {
		Page<Funcionario> funcionarios = repository.findBySalarioBetween(valorInicial, valorFinal, pageable);
		return funcionarios;
	}
	
	public Page<Funcionario> buscarPorNome(String nome, Pageable pageable){
		Page<Funcionario> funcionarios = repository.findByNomeContainingIgnoreCase(nome, pageable);
		return funcionarios;
	}

}
