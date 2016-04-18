package br.com.constran.model.carga;

public class CategoriaEquipamento {

	private Integer id;
	private String descricao;
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
	public CategoriaEquipamento(Integer id, String descricao) {
		super();
		this.id = id;
		this.descricao = descricao;
	}
	
	
	
	
}
