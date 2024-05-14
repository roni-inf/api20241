package br.com.serratec.enums;

public enum Combustivel {
	FLEX(1, "Flex"), GASOLINA(2, "Gasolina"), ELETRICO(3, "Eletrico"), ETANOL(4, "Etanol");

	private Integer codigo;
	private String tipo;

	private Combustivel(Integer codigo, String tipo) {
		this.codigo = codigo;
		this.tipo = tipo;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
