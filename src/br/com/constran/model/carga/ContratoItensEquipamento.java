package br.com.constran.model.carga;

import java.math.BigDecimal;

public class ContratoItensEquipamento {

	// Chave composta----------
	private String ccObra;
	private Contrato contrato;
	private Integer categoria;
	// -------------------------

	
	private String descCategoria;
	private Integer quantidade;
	private BigDecimal valorMensal;
	private BigDecimal valorNormal;
	private BigDecimal valorHoraExtra;
	private Integer horasMinimas;
	private String forneceOperador;

	public ContratoItensEquipamento() {
	}

	
	
	public ContratoItensEquipamento(String ccObra, Contrato contrato,
			Integer categoria, String descCategoria, Integer quantidade, BigDecimal valorMensal,
			BigDecimal valorNormal, BigDecimal valorHoraExtra,
			Integer horasMinimas, String forneceOperador) {
		super();
		this.ccObra = ccObra;
		this.contrato = contrato;
		this.categoria = categoria;
		this.quantidade = quantidade;
		this.valorMensal = valorMensal;
		this.valorNormal = valorNormal;
		this.valorHoraExtra = valorHoraExtra;
		this.horasMinimas = horasMinimas;
		this.forneceOperador = forneceOperador;
		this.descCategoria = descCategoria;
	}
	
	public ContratoItensEquipamento(String ccObra, Contrato contrato,
			Integer categoria, Integer quantidade, BigDecimal valorMensal,
			BigDecimal valorNormal, BigDecimal valorHoraExtra,
			Integer horasMinimas, String forneceOperador) {
		super();
		this.ccObra = ccObra;
		this.contrato = contrato;
		this.categoria = categoria;
		this.quantidade = quantidade;
		this.valorMensal = valorMensal;
		this.valorNormal = valorNormal;
		this.valorHoraExtra = valorHoraExtra;
		this.horasMinimas = horasMinimas;
		this.forneceOperador = forneceOperador;
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

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValorMensal() {
		return valorMensal;
	}

	public void setValorMensal(BigDecimal valorMensal) {
		this.valorMensal = valorMensal;
	}

	public BigDecimal getValorNormal() {
		return valorNormal;
	}

	public void setValorNormal(BigDecimal valorNormal) {
		this.valorNormal = valorNormal;
	}

	public BigDecimal getValorHoraExtra() {
		return valorHoraExtra;
	}

	public void setValorHoraExtra(BigDecimal valorHoraExtra) {
		this.valorHoraExtra = valorHoraExtra;
	}

	public Integer getHorasMinimas() {
		return horasMinimas;
	}

	public void setHorasMinimas(Integer horasMinimas) {
		this.horasMinimas = horasMinimas;
	}

	public String getForneceOperador() {
		return forneceOperador;
	}

	public void setForneceOperador(String forneceOperador) {
		this.forneceOperador = forneceOperador;
	}

	public String getDescCategoria() {
		return descCategoria;
	}

	public void setDescCategoria(String descCategoria) {
		this.descCategoria = descCategoria;
	}

}
