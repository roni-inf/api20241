package br.com.serratec.dto;

import br.com.serratec.entity.Usuario;

public class UsuarioRequestDTO {
	private String nome;
	private String email;
	private String senha;
	private String confirmaSenha;

	public UsuarioRequestDTO() {
	}

	public UsuarioRequestDTO(Usuario usuario) {
		nome = usuario.getNome();
		email = usuario.getEmail();
		senha = usuario.getSenha();
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getConfirmaSenha() {
		return confirmaSenha;
	}

	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}

}
