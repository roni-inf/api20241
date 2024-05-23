package br.com.serratec.service;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.serratec.dto.FuncionarioResponseDTO;
import br.com.serratec.entity.Funcionario;
import br.com.serratec.repository.FuncionarioRepository;
import jakarta.transaction.Transactional;

@Service
public class FuncionarioService {
	@Autowired
	private FuncionarioRepository repository;

	@Autowired
	private FotoService fotoService;

	public List<FuncionarioResponseDTO> listar() {
		return repository.findAll().stream().map((f) -> adicionarLinkImagem(f)).collect(Collectors.toList());
	}

	public FuncionarioResponseDTO buscar(Long id) {
		Optional<Funcionario> funcionario = repository.findById(id);
		return adicionarLinkImagem(funcionario.get());
	}

	public FuncionarioResponseDTO adicionarLinkImagem(Funcionario funcionario) {
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/funcionarios/{id}/foto")
				.buildAndExpand(funcionario.getId()).toUri();
		FuncionarioResponseDTO dto = new FuncionarioResponseDTO();
		dto.setNome(funcionario.getNome());
		dto.setEmail(funcionario.getEmail());
		dto.setUrl(uri.toString());
		return dto;
	}

	@Transactional
	public FuncionarioResponseDTO inserir(Funcionario funcionario, MultipartFile file) throws IOException {
		funcionario = repository.save(funcionario);
		fotoService.inserir(funcionario, file);
		return adicionarLinkImagem(funcionario);
	}

	public Page<Funcionario> listarPorPagina(Pageable pageable) {
		return repository.findAll(pageable);
	}

	public Page<Funcionario> buscarPorFaixaSalarial(Double valorInicial, Double valorFinal, Pageable pageable) {
		Page<Funcionario> funcionarios = repository.findBySalarioBetween(valorInicial, valorFinal, pageable);
		return funcionarios;
	}

	public Page<Funcionario> buscarPorNome(String nome, Pageable pageable) {
		Page<Funcionario> funcionarios = repository.findByNomeContainingIgnoreCase(nome, pageable);
		return funcionarios;
	}

}
