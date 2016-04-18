package br.com.constran.model.paramswrapper.to_mobile_tabs;

import br.com.constran.mobile.persistence.vo.rae.RaeVO;
import br.com.constran.mobile.persistence.vo.rae.abs.AbastecimentoVO;
import br.com.constran.mobile.persistence.vo.rae.abs.LubrificacaoDetalheVO;

public class LubrificacaoDetalheParams {
	
	private String ccObra = null;
	private String idRAE = null;
	private String data = null;
	private String idEquipamento = null;
	private String horaInicio = null;
	private String idCombustivelLubrificante = null;
	private String idCompartimento = null;
	private String quantidade = null;
	private String observacao = null;
	private String idUsuario = null;

	
	public LubrificacaoDetalheParams() {
		
	}
	
	public LubrificacaoDetalheParams(RaeVO itemRae, AbastecimentoVO itemAbastecimento, LubrificacaoDetalheVO itemLubriDetalhe) {
		
		setCcObra(String.valueOf(itemAbastecimento.getIdObra()));
		
		setIdRAE(String.valueOf(itemRae.getId()));
		
		setData(itemRae.getData());
		
		if(itemAbastecimento.getEquipamento() != null){
			setIdEquipamento(String.valueOf(itemAbastecimento.getEquipamento().getId()));
		}
		
		if(itemAbastecimento.getHoraInicio() != null){
			setHoraInicio(itemAbastecimento.getHoraInicio());
		}
		
		
		if(itemAbastecimento.getCombustivelLubrificante() != null){
			setIdCombustivelLubrificante(String.valueOf(itemAbastecimento.getCombustivelLubrificante().getId()));
		}
		
		
		if(itemLubriDetalhe.getCompartimento() != null){
			setIdCompartimento(String.valueOf(itemLubriDetalhe.getCompartimento().getId()));
		}
		
		
		if(itemLubriDetalhe.getQtd() != null && itemLubriDetalhe.getQtd().length() > 0) {
			setQuantidade(itemLubriDetalhe.getQtd());
		}
		
		if(itemLubriDetalhe.getObservacao() != null && itemLubriDetalhe.getObservacao().length() > 0) {
			setObservacao(itemLubriDetalhe.getObservacao());
		}
		
		
		setIdUsuario(String.valueOf(itemAbastecimento.getAbastecedor().getIdUsuarioPessoal()));
		
	}


	public String getCcObra() {
		return ccObra;
	}


	public void setCcObra(String ccObra) {
		this.ccObra = ccObra;
	}


	public String getIdRAE() {
		return idRAE;
	}

	public void setIdRAE(String idRAE) {
		this.idRAE = idRAE;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		
		if(data == null || data.length() == 0)return;
		
		String[] arrData = data.split("/");
		this.data = arrData[2]+"-"+arrData[1]+"-"+arrData[0];
	}

	public String getIdEquipamento() {
		return idEquipamento;
	}

	public void setIdEquipamento(String idEquipamento) {
		this.idEquipamento = idEquipamento;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getIdCombustivelLubrificante() {
		return idCombustivelLubrificante;
	}

	public void setIdCombustivelLubrificante(String idCombustivelLubrificante) {
		this.idCombustivelLubrificante = idCombustivelLubrificante;
	}

	public String getIdCompartimento() {
		return idCompartimento;
	}

	public void setIdCompartimento(String idCompartimento) {
		this.idCompartimento = idCompartimento;
	}

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
}
