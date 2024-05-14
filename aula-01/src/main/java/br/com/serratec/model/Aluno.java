package br.com.serratec.model;

public class Aluno {
	private Long id;
	private String nome;
	private String email;
	private Integer idade;

	public Aluno() {
	}
	
	public Aluno(Long id, String nome, String email, Integer idade) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.idade = idade;
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

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

}
