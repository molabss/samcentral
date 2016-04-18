package br.com.constran.model.carga;

public class Pessoal {

	private Integer id;
	private String origem;
	private String nome;
	private String matricula;
	private Fornecedor fornecedor;
	private String senha;
	private String dataNascimento;
	private String sexo;
	private String rg;
	private String cpf;
	private String qrCode;
	
	public Pessoal(Integer id, String nome){
		this.id = id;
		this.nome = nome;
	}
	
	
	public Pessoal(Integer id,String origem, String nome, String matricula, String senha,
			String dataNascimento, String sexo, String rg, String cpf,
			String qrCode) {
		
		super();
		this.id = id;
		this.origem = origem;
		this.nome = nome;
		this.matricula = matricula;
		this.senha = senha;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
		this.rg = rg;
		this.cpf = cpf;
		this.qrCode = qrCode;
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOrigem() {
		return origem;
	}
	public void setOrigem(String origem) {
		this.origem = origem;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getQrCode() {
		return qrCode;
	}
	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}
	
	
	
}
