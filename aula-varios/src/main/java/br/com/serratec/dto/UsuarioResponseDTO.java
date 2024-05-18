package br.com.serratec.dto;

import java.util.HashSet;
import java.util.Set;

import br.com.serratec.entity.Perfil;
import br.com.serratec.entity.Usuario;
import br.com.serratec.entity.UsuarioPerfil;

public class UsuarioResponseDTO {
	private Long id;
	private String nome;
	private String email;
	
	private Set<Perfil> perfis;
	

	public UsuarioResponseDTO(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
		this.perfis = new HashSet<>();
		for (UsuarioPerfil up : usuario.getUsuarioPerfis()) {
			this.perfis.add(up.getId().getPerfil());
		}
	}

	
	
	public Set<Perfil> getPerfis() {
		return perfis;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
