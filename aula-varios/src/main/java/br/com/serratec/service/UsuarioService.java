package br.com.serratec.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.serratec.dto.UsuarioResponseDTO;
import br.com.serratec.entity.Usuario;
import br.com.serratec.exception.EmailException;
import br.com.serratec.repository.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public List<UsuarioResponseDTO> listar(){
		
		List<Usuario> usuarios=repository.findAll();
/*		List<UsuarioResponseDTO> listaDTO = new ArrayList<>();
		
		for (Usuario usuario : usuarios) {
			listaDTO.add(new UsuarioResponseDTO(usuario.getId(),
					usuario.getNome(),
					usuario.getEmail()));
		}
		return listaDTO;
		
	*/
		return usuarios.stream().map( (u)-> new UsuarioResponseDTO(u.getId(),
				u.getNome(),u.getEmail())).collect(Collectors.toList());
	}
	
	
	
	public Usuario inserir(Usuario usuario) throws EmailException {
		if (repository.findByEmail(usuario.getEmail())!=null) {
			throw new EmailException("Email Já Existe na Base");
		}	
		usuario.setSenha(encoder.encode(usuario.getSenha()));
		return repository.save(usuario);
		
	}
	
	
}
