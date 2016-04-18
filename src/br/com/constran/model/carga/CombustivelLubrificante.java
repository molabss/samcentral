package br.com.constran.model.carga;

public class CombustivelLubrificante {

	private Integer id;
	private String descricao;
	private String unidadeMedida;
	private String tipo;
	
	
	
	
	
	
	public CombustivelLubrificante(Integer id, String descricao,
			String unidadeMedida, String tipo) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.unidadeMedida = unidadeMedida;
		this.tipo = tipo;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getUnidadeMedida() {
		return unidadeMedida;
	}
	public void setUnidadeMedida(String unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	
}
