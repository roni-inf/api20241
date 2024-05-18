package br.com.serratec.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.entity.Perfil;
import br.com.serratec.exception.ResourceNotFoundException;
import br.com.serratec.repository.PerfilRepository;

@Service
public class PerfilService {
	@Autowired
	private PerfilRepository repository;
	
	public Perfil buscar(Long id) {
	Perfil perfil=repository.findById(id).
			orElseThrow(()-> new ResourceNotFoundException("Perfil não encontrado!"));
		return perfil;
		
	}
	
}
