package br.com.serratec.entity;

import java.time.LocalDate;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class UsuarioPerfil {

	@EmbeddedId
	private UsuarioPerfilPK id = new UsuarioPerfilPK();
	
	private LocalDate dataCriacao;
	
	public UsuarioPerfil() {
		// TODO Auto-generated constructor stub
	}

	public UsuarioPerfil(Usuario usuario,Perfil perfil, LocalDate dataCriacao) {
		this.id.setUsuario(usuario);
		this.id.setPerfil(perfil);
		this.dataCriacao = dataCriacao;
	}

	public UsuarioPerfilPK getId() {
		return id;
	}

	public void setUsuario(Usuario usuario) {
		this.id.setUsuario(usuario);
	}
	
	public void setPerfil(Perfil perfil) {
		this.id.setPerfil(perfil);
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
	
	
	
}
