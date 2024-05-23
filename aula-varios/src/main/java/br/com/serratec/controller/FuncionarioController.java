package br.com.serratec.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.serratec.dto.FuncionarioResponseDTO;
import br.com.serratec.entity.Foto;
import br.com.serratec.entity.Funcionario;
import br.com.serratec.service.FotoService;
import br.com.serratec.service.FuncionarioService;


@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

	@Autowired
	private FuncionarioService service;

	@Autowired
	private FotoService fotoService;

	@GetMapping
	public List<FuncionarioResponseDTO> listar() {
		return service.listar();
	}

	@GetMapping("/{id}/foto")
	public ResponseEntity<byte[]> buscarFoto(@PathVariable Long id) {
		Foto foto = fotoService.buscar(id);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", foto.getTipo());
		headers.add("Content-length", String.valueOf(foto.getDados().length));
		return new ResponseEntity<>(foto.getDados(), headers, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public FuncionarioResponseDTO buscar (@PathVariable Long id) {
		return service.buscar(id);
	}
	
	@PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public FuncionarioResponseDTO inserir(@RequestPart Funcionario funcionario,@RequestPart MultipartFile file) throws IOException {
		return service.inserir(funcionario, file);
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
