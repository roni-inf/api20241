package br.com.serratec.dto;

import br.com.serratec.entity.Funcionario;

public class FuncionarioResponseDTO {
	private String nome;
	private String email;
	private String url;

	public FuncionarioResponseDTO() {
	}

	public FuncionarioResponseDTO(Funcionario funcionario) {
		this.nome = funcionario.getNome();
		this.email = funcionario.getEmail();
		this.url = url;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
