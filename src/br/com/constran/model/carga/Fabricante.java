package br.com.constran.model.carga;

public class Fabricante {
	
	public Fabricante(Integer id){
		this.id = id;
	}
	
	
	public Fabricante(Integer id,String descricao){
		this.id = id;
		this.descricao = descricao;
	}
	
	
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
	
	
}
