package br.com.serratec.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Veiculo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String placa;
	private String marca;
	private String modelo;

	@Embedded
	private Caracteristica caracteristica;
	
	@OneToOne
	@JoinColumn(name = "id_empregado")
	private Empregado empregado;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "veiculo")
	private List<Manutencao> manutencoes = new ArrayList<>();
	
	
	
	public List<Manutencao> getManutencoes() {
		return manutencoes;
	}

	public Empregado getEmpregado() {
		return empregado;
	}

	public void setEmpregado(Empregado empregado) {
		this.empregado = empregado;
	}

	public Caracteristica getCaracteristica() {
		return caracteristica;
	}

	public void setCaracteristica(Caracteristica caracteristica) {
		this.caracteristica = caracteristica;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

}
