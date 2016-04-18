package br.com.constran.model.carga;

import java.math.BigDecimal;

public class ContratoAlocacaoEquipamento {

	//CHAVE COMPOSTA-----------------
	private String ccObra;
	private Contrato contrato;
	private Integer categoria;
	private Equipamento equipamento;
	private String dataIngresso;
	//--------------------------------
	
	private String descCategoria;
	private Integer kmEntrada;
	private BigDecimal horimetroEntrada;
	private String nfEntrada;
	private String tipoMedicao;
	private String dataSaida;
	private Integer kmSaida;
	private Integer horimetroSaida;
	private String nfSaida;
	private String realizaManutencao;
	private String tipoControleConsumo;

	
	
	public ContratoAlocacaoEquipamento(){
		
	}
	
	
	
	public ContratoAlocacaoEquipamento(String ccObra, Contrato contrato,
			Integer categoria, String descCategoria,Equipamento equipamento, String dataIngresso,
			Integer kmEntrada, BigDecimal horimetroEntrada, String nfEntrada,
			String tipoMedicao, String realizaManutencao,
			String tipoControleConsumo) {
		super();
		this.ccObra = ccObra;
		this.contrato = contrato;
		this.categoria = categoria;
		this.equipamento = equipamento;
		this.dataIngresso = dataIngresso;
		this.kmEntrada = kmEntrada;
		this.horimetroEntrada = horimetroEntrada;
		this.nfEntrada = nfEntrada;
		this.tipoMedicao = tipoMedicao;
		this.realizaManutencao = realizaManutencao;
		this.tipoControleConsumo = tipoControleConsumo;
		this.descCategoria = descCategoria;
	}
	
	
	
	public ContratoAlocacaoEquipamento(String ccObra, Contrato contrato,
			Integer categoria, Equipamento equipamento, String dataIngresso,
			Integer kmEntrada, BigDecimal horimetroEntrada, String nfEntrada,
			String tipoMedicao, String realizaManutencao,
			String tipoControleConsumo) {
		super();
		this.ccObra = ccObra;
		this.contrato = contrato;
		this.categoria = categoria;
		this.equipamento = equipamento;
		this.dataIngresso = dataIngresso;
		this.kmEntrada = kmEntrada;
		this.horimetroEntrada = horimetroEntrada;
		this.nfEntrada = nfEntrada;
		this.tipoMedicao = tipoMedicao;
		this.realizaManutencao = realizaManutencao;
		this.tipoControleConsumo = tipoControleConsumo;
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
	public Integer getCategoria() {
		return categoria;
	}
	public void setCategoria(Integer categoria) {
		this.categoria = categoria;
	}
	public Equipamento getEquipamento() {
		return equipamento;
	}
	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}
	public String getDataIngresso() {
		return dataIngresso;
	}
	public void setDataIngresso(String dataIngresso) {
		this.dataIngresso = dataIngresso;
	}
	public Integer getKmEntrada() {
		return kmEntrada;
	}
	public void setKmEntrada(Integer kmEntrada) {
		this.kmEntrada = kmEntrada;
	}
	public BigDecimal getHorimetroEntrada() {
		return horimetroEntrada;
	}
	public void setHorimetroEntrada(BigDecimal horimetroEntrada) {
		this.horimetroEntrada = horimetroEntrada;
	}
	public String getNfEntrada() {
		return nfEntrada;
	}
	public void setNfEntrada(String nfEntrada) {
		this.nfEntrada = nfEntrada;
	}
	public String getTipoMedicao() {
		return tipoMedicao;
	}
	public void setTipoMedicao(String tipoMedicao) {
		this.tipoMedicao = tipoMedicao;
	}
	public String getDataSaida() {
		return dataSaida;
	}
	public void setDataSaida(String dataSaida) {
		this.dataSaida = dataSaida;
	}
	public Integer getKmSaida() {
		return kmSaida;
	}
	public void setKmSaida(Integer kmSaida) {
		this.kmSaida = kmSaida;
	}
	public Integer getHorimetroSaida() {
		return horimetroSaida;
	}
	public void setHorimetroSaida(Integer horimetroSaida) {
		this.horimetroSaida = horimetroSaida;
	}
	public String getNfSaida() {
		return nfSaida;
	}
	public void setNfSaida(String nfSaida) {
		this.nfSaida = nfSaida;
	}
	public String getRealizaManutencao() {
		return realizaManutencao;
	}
	public void setRealizaManutencao(String realizaManutencao) {
		this.realizaManutencao = realizaManutencao;
	}
	public String getTipoControleConsumo() {
		return tipoControleConsumo;
	}
	public void setTipoControleConsumo(String tipoControleConsumo) {
		this.tipoControleConsumo = tipoControleConsumo;
	}





	public String getDescCategoria() {
		return descCategoria;
	}





	public void setDescCategoria(String descCategoria) {
		this.descCategoria = descCategoria;
	}
	
	
	
}
