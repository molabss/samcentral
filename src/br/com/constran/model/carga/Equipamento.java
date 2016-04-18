package br.com.constran.model.carga;

public class Equipamento {

	private Integer id;
	private String descricao;
	private Integer idCategoria;
	private CategoriaEquipamento categoria;
	private String qrCode;
	private Integer modelo;
	private Fornecedor fornecedor;
	private String tipo;
	private String unidadeMedida;
	private String chassi;
	private Fabricante fabricante;
	private String placa;
	private String prefixo;
	private Integer ano;
	private String volume;
	private String postoMovel;
	private String observacoes;
	private String controleLubrificacao;
	
	
	
	
	public Equipamento(Integer id, String descricao, CategoriaEquipamento categoria,
			String qrCode,Fornecedor fornecedor, String tipo,
			String unidadeMedida, String chassi, Fabricante fabricante,
			String placa, String prefixo, Integer ano,
			String controleLubrificacao) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.categoria = categoria;
		this.qrCode = qrCode;
		this.fornecedor = fornecedor;
		this.tipo = tipo;
		this.unidadeMedida = unidadeMedida;
		this.chassi = chassi;
		this.fabricante = fabricante;
		this.placa = placa;
		this.prefixo = prefixo;
		this.ano = ano;
		this.controleLubrificacao = controleLubrificacao;
	}

	public Equipamento(Integer id){
		this.id = id;
	}
	
	public Equipamento(String descricao, String prefixo,Integer id){
		this.descricao = descricao;
		this.prefixo = prefixo;
		this.id = id;
	}
	
	public Equipamento(String descricao, Integer idCategoria, String qrCode,
			Fornecedor fornecedor, String tipo, String unidadeMedida,
			String chassi, Fabricante fabricante, String placa, String prefixo,
			Integer ano, String controleLubrificacao) {
		super();
		this.descricao = descricao;
		this.idCategoria = idCategoria;
		this.qrCode = qrCode;
		this.fornecedor = fornecedor;
		this.tipo = tipo;
		this.unidadeMedida = unidadeMedida;
		this.chassi = chassi;
		this.fabricante = fabricante;
		this.placa = placa;
		this.prefixo = prefixo;
		this.ano = ano;
		this.controleLubrificacao = controleLubrificacao;
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
	public Integer getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}
	public String getQrCode() {
		return qrCode;
	}
	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}
	public Integer getModelo() {
		return modelo;
	}
	public void setModelo(Integer modelo) {
		this.modelo = modelo;
	}
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getUnidadeMedida() {
		return unidadeMedida;
	}
	public void setUnidadeMedida(String unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}
	public String getChassi() {
		return chassi;
	}
	public void setChassi(String chassi) {
		this.chassi = chassi;
	}
	public Fabricante getFabricante() {
		return fabricante;
	}
	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getPrefixo() {
		return prefixo;
	}
	public void setPrefixo(String prefixo) {
		this.prefixo = prefixo;
	}
	public Integer getAno() {
		return ano;
	}
	public void setAno(Integer ano) {
		this.ano = ano;
	}
	public String getVolume() {
		return volume;
	}
	public void setVolume(String volume) {
		this.volume = volume;
	}
	public String getPostoMovel() {
		return postoMovel;
	}
	public void setPostoMovel(String postoMovel) {
		this.postoMovel = postoMovel;
	}
	public String getObservacoes() {
		return observacoes;
	}
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	public String getControleLubrificacao() {
		return controleLubrificacao;
	}
	public void setControleLubrificacao(String controleLubrificacao) {
		this.controleLubrificacao = controleLubrificacao;
	}

	public CategoriaEquipamento getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaEquipamento categoria) {
		this.categoria = categoria;
	}
	
	
	
	
	
}
