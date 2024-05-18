package br.com.serratec.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.serratec.dto.UsuarioRequestDTO;
import br.com.serratec.dto.UsuarioResponseDTO;
import br.com.serratec.entity.Usuario;
import br.com.serratec.exception.ConfirmaSenhaException;
import br.com.serratec.exception.EmailException;
import br.com.serratec.repository.UsuarioRepository;
import jakarta.transaction.Transactional;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private BCryptPasswordEncoder encoder;

	public List<UsuarioResponseDTO> listar() {

		List<Usuario> usuarios = repository.findAll();
		/*
		 * List<UsuarioResponseDTO> listaDTO = new ArrayList<>();
		 * 
		 * for (Usuario usuario : usuarios) { listaDTO.add(new
		 * UsuarioResponseDTO(usuario.getId(), usuario.getNome(), usuario.getEmail()));
		 * } return listaDTO;
		 * 
		 */
		return usuarios.stream().map((u) -> new UsuarioResponseDTO(u))
				.collect(Collectors.toList());
	}

	@Transactional
	public UsuarioResponseDTO inserir(UsuarioRequestDTO usuario){
		if (!usuario.getSenha().equals(usuario.getConfirmaSenha())) {
			throw new ConfirmaSenhaException("Confirma de senha não confere!");
		}

		if (repository.findByEmail(usuario.getEmail()) != null) {
			throw new EmailException("Email Já Existe na Base");
		}
		Usuario u = new Usuario();
		u.setNome(usuario.getNome());
		u.setEmail(usuario.getEmail());
		u.setSenha(encoder.encode(usuario.getSenha()));
		repository.save(u);
		return new UsuarioResponseDTO(u);

	}

}
