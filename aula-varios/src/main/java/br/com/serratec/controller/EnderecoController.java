package br.com.serratec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.dto.EnderecoResponseDTO;
import br.com.serratec.service.EnderecoService;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {
	@Autowired
	private EnderecoService service;

	@GetMapping("{cep}")
	public ResponseEntity<EnderecoResponseDTO> buscarCep(@PathVariable String cep) {
		EnderecoResponseDTO dto = service.buscar(cep);
		if (dto == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(dto);
	}

}
