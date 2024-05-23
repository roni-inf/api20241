package br.com.serratec.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.serratec.entity.Usuario;
import br.com.serratec.exception.ResourceNotFoundException;
import br.com.serratec.repository.UsuarioRepository;

@Service
public class UsuarioDetailsImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("Buscar o username (email)");
		Usuario usuario = repository.findByEmail(username);
		if (usuario == null) {
			throw new ResourceNotFoundException("Usuário não encontrado!");
		}
		return usuario;

	}

}
