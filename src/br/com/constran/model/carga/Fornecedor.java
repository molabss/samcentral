package br.com.constran.model.carga;

public class Fornecedor {

	private Integer id;
	private String razao;
	private String fantasia;
	private String endereco;
	private String cnpj;
	private String inscEstadual;
	private String contatos;
	private String indicacao;
	private String dataCadastro;
	private String responsavelLegal;
	private String responsavelTecnico;
	private String codigoDeq;
	
	public Fornecedor(Integer id){
		this.id = id;
	}
	
	public Fornecedor(Integer id,String razao){
		this.razao = razao;
		this.id = id;
	}
	
	public Fornecedor(String razao){
		this.razao = razao;
	}
	
	public Fornecedor(){
		
	}
	
	public Fornecedor(
	 String cnpj,
	 String razao,
	 String fantasia,
	 String endereco,
	 String inscEstadual,
	 String contatos,
	 String indicacao,
	 String responsavelLegal,
	 String responsavelTecnico
){
		this.razao = razao;
		this.fantasia = fantasia;
		this.endereco = endereco;
		this.cnpj = cnpj;
		this.inscEstadual = inscEstadual;
		this.contatos = contatos;
		this.indicacao = indicacao;
		this.responsavelLegal = responsavelLegal;
		this.responsavelTecnico = responsavelTecnico;		
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRazao() {
		return razao;
	}

	public void setRazao(String razao) {
		this.razao = razao;
	}

	public String getFantasia() {
		return fantasia;
	}

	public void setFantasia(String fantasia) {
		this.fantasia = fantasia;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getInscEstadual() {
		return inscEstadual;
	}

	public void setInscEstadual(String inscEstadual) {
		this.inscEstadual = inscEstadual;
	}

	public String getContatos() {
		return contatos;
	}

	public void setContatos(String contatos) {
		this.contatos = contatos;
	}

	public String getIndicacao() {
		return indicacao;
	}

	public void setIndicacao(String indicacao) {
		this.indicacao = indicacao;
	}

	public String getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getResponsavelLegal() {
		return responsavelLegal;
	}

	public void setResponsavelLegal(String responsavelLegal) {
		this.responsavelLegal = responsavelLegal;
	}

	public String getResponsavelTecnico() {
		return responsavelTecnico;
	}

	public void setResponsavelTecnico(String responsavelTecnico) {
		this.responsavelTecnico = responsavelTecnico;
	}

	public String getCodigoDeq() {
		return codigoDeq;
	}

	public void setCodigoDeq(String codigoDeq) {
		this.codigoDeq = codigoDeq;
	}


	
	
	
	
	
}
