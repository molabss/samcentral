package br.com.constran.model.carga;

public class ContratoAlocacaoPessoal {
	
	private String ccObra;
	private Contrato contrato;
	private Pessoal pessoa;
	private String dataIngresso;
	private String tipoMedicao;
	private Funcao funcao;
	private String dataSaida;
	
	
	
	
	public ContratoAlocacaoPessoal(String ccObra, Contrato contrato,
			Pessoal pessoa, String dataIngresso, String tipoMedicao,
			Funcao funcao) {
		super();
		this.ccObra = ccObra;
		this.contrato = contrato;
		this.pessoa = pessoa;
		this.dataIngresso = dataIngresso;
		this.tipoMedicao = tipoMedicao;
		this.funcao = funcao;
	}
	public String getCcObra() {
		return ccObra;
	}
	public void setCcObra(String ccObra) {
		this.ccObra = ccObra;
	}
	public Contrato getContrato() {
		return contrato;
	}
	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}
	public Pessoal getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoal pessoa) {
		this.pessoa = pessoa;
	}
	public String getDataIngresso() {
		return dataIngresso;
	}
	public void setDataIngresso(String dataIngresso) {
		this.dataIngresso = dataIngresso;
	}
	public String getTipoMedicao() {
		return tipoMedicao;
	}
	public void setTipoMedicao(String tipoMedicao) {
		this.tipoMedicao = tipoMedicao;
	}
	public Funcao getFuncao() {
		return funcao;
	}
	public void setFuncao(Funcao funcao) {
		this.funcao = funcao;
	}
	public String getDataSaida() {
		return dataSaida;
	}
	public void setDataSaida(String dataSaida) {
		this.dataSaida = dataSaida;
	}

	
	
}
