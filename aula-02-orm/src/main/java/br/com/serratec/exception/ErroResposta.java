package br.com.serratec.exception;

import java.time.LocalDate;
import java.util.List;

public class ErroResposta {
	private Integer status;
	private String titulo;
	private LocalDate dataHora;
	private List<String> erros;

	public ErroResposta(Integer status, String titulo, LocalDate dataHora, List<String> erros) {
		this.status = status;
		this.titulo = titulo;
		this.dataHora = dataHora;
		this.erros = erros;
	}

	
	
	public List<String> getErros() {
		return erros;
	}



	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public LocalDate getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDate dataHora) {
		this.dataHora = dataHora;
	}

	
	
}
