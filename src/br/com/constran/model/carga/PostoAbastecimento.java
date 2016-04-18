package br.com.constran.model.carga;

public class PostoAbastecimento {
	
	private Integer id;
	private String ccObra;
	private String tipo;
	private Equipamento equipamento;
	private String descricao;
	
	
	
	
	public PostoAbastecimento(Integer id,String ccObra, String tipo,
			Equipamento equipamento, String descricao) {
		super();
		this.id = id;
		this.ccObra = ccObra;
		this.tipo = tipo;
		this.equipamento = equipamento;
		this.descricao = descricao;
	}
	
	public PostoAbastecimento(String ccObra, String tipo,
			Equipamento equipamento, String descricao) {
		super();
		this.ccObra = ccObra;
		this.tipo = tipo;
		this.equipamento = equipamento;
		this.descricao = descricao;
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCcObra() {
		return ccObra;
	}
	public void setCcObra(String ccObra) {
		this.ccObra = ccObra;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Equipamento getEquipamento() {
		return equipamento;
	}
	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	
	

}
