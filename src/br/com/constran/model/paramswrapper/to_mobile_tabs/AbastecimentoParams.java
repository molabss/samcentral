package br.com.constran.model.paramswrapper.to_mobile_tabs;

import br.com.constran.mobile.persistence.vo.rae.RaeVO;
import br.com.constran.mobile.persistence.vo.rae.abs.AbastecimentoVO;

public class AbastecimentoParams {
	
	private String ccObra = null;
	private String idRAE = null;
	private String data = null;
	private String idPosto = null;
	private String idAbastecedor = null;
	private String idUsuario = null;
	private String totalizadorInicial = "0";
	private String totalizadorFinal = "0";
	private String horaInicio = null;
	private String horaTermino = null;
	private String horimetro = "0.00";
	private String quilometragem = "0";
	private String quantidade = "0";
	private String observacoes = null;
	private String idOperador = null;
	private String idFrenteObra = null;
	private String idAtividade = null;
	private String idJustificativa = null;
	private String obsJustificativa = null;
	private String idEquipamento = null;
	private String idCombustivelLubrificante = null;
	
	public AbastecimentoParams(){
		
	}
	
	public AbastecimentoParams(RaeVO itemRae, AbastecimentoVO itemAbastecimento) {
		
		setCcObra(String.valueOf(itemAbastecimento.getIdObra()));
		
		setIdRAE(String.valueOf(itemRae.getId()));
		
		setData(itemRae.getData());
		
		setIdPosto(String.valueOf(itemRae.getPosto().getId()));
		
		setIdAbastecedor(String.valueOf(itemAbastecimento.getAbastecedor().getIdUsuarioPessoal()));
		
		setIdUsuario(String.valueOf(itemAbastecimento.getAbastecedor().getIdUsuarioPessoal()));
		
		setTotalizadorInicial(itemRae.getTotalizadorInicial());
		
		setTotalizadorFinal(itemRae.getTotalizadorFinal());
		
		setIdCombustivelLubrificante(String.valueOf(itemAbastecimento.getCombustivelLubrificante().getId()));
		
		
		if(itemAbastecimento.getHoraInicio() != null && itemAbastecimento.getHoraInicio().length() > 0){
			setHoraInicio(itemAbastecimento.getHoraInicio());
		}
		
		if(itemAbastecimento.getHoraTermino() != null && itemAbastecimento.getHoraTermino().length() > 0){
			setHoraTermino(itemAbastecimento.getHoraTermino());
		}
		
		
		if(itemAbastecimento.getHorimetro() != null && itemAbastecimento.getHorimetro().length() > 0){
			setHorimetro(itemAbastecimento.getHorimetro());
		}
		
		
		if(itemAbastecimento.getQuilometragem() != null && itemAbastecimento.getQuilometragem().length() > 0){
			setQuilometragem(itemAbastecimento.getQuilometragem());
		}
		
		
		
		if(itemAbastecimento.getQtd() != null && itemAbastecimento.getQtd().length() > 0){
			setQuantidade(itemAbastecimento.getQtd());
		}
		
		
		if(itemAbastecimento.getObservacao() != null && itemAbastecimento.getObservacao().length() > 0){
			setObservacoes(itemAbastecimento.getObservacao());
		}
		
		if(itemAbastecimento.getAtividade().getFrenteObra().getId() != null && itemAbastecimento.getAtividade().getFrenteObra().getId() > 0){
			setIdFrenteObra(String.valueOf(itemAbastecimento.getAtividade().getFrenteObra().getId()));
		}
		
		if(itemAbastecimento.getAtividade().getIdAtividade() != null && itemAbastecimento.getAtividade().getIdAtividade() > 0){
			setIdAtividade(String.valueOf(itemAbastecimento.getAtividade().getIdAtividade()));
		}
		
		
		if(itemAbastecimento.getOperador() != null && itemAbastecimento.getOperador().getIdUsuarioPessoal() > 0){
			setIdOperador(String.valueOf(itemAbastecimento.getOperador().getIdUsuarioPessoal()));
		}
		
		
		if(itemAbastecimento.getJustificativa() != null && itemAbastecimento.getJustificativa().getId() > 0){
			setIdJustificativa(String.valueOf(itemAbastecimento.getJustificativa().getId()));
		}
		
		if(itemAbastecimento.getObservacaoJustificativa() != null && itemAbastecimento.getObservacaoJustificativa().length() > 0){
			setObsJustificativa(itemAbastecimento.getObservacaoJustificativa());
		}
		
		
		if(itemAbastecimento.getEquipamento() != null && itemAbastecimento.getEquipamento().getId() > 0){
			setIdEquipamento(String.valueOf(itemAbastecimento.getEquipamento().getId()));
		}
	}
	
	
	public String getQuantidade() {
		return quantidade;
	}


	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}


	public String getObservacoes() {
		return observacoes;
	}


	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}


	public String getIdOperador() {
		return idOperador;
	}


	public void setIdOperador(String idOperador) {
		this.idOperador = idOperador;
	}


	public String getIdFrenteObra() {
		return idFrenteObra;
	}


	public void setIdFrenteObra(String idFrenteObra) {
		this.idFrenteObra = idFrenteObra;
	}


	public String getIdAtividade() {
		return idAtividade;
	}


	public void setIdAtividade(String idAtividade) {
		this.idAtividade = idAtividade;
	}


	public String getIdJustificativa() {
		return idJustificativa;
	}


	public void setIdJustificativa(String idJustificativa) {
		this.idJustificativa = idJustificativa;
	}


	public String getObsJustificativa() {
		return obsJustificativa;
	}


	public void setObsJustificativa(String obsJustificativa) {
		this.obsJustificativa = obsJustificativa;
	}


	public String getIdEquipamento() {
		return idEquipamento;
	}


	public void setIdEquipamento(String idEquipamento) {
		this.idEquipamento = idEquipamento;
	}


	public String getIdCombustivelLubrificante() {
		return idCombustivelLubrificante;
	}


	public void setIdCombustivelLubrificante(String idCombustivelLubrificante) {
		this.idCombustivelLubrificante = idCombustivelLubrificante;
	}


	public String getHorimetro() {
		return horimetro;
	}


	public void setHorimetro(String horimetro) {
		this.horimetro = horimetro;
	}

	
	public String getHoraInicio() {
		return horaInicio;
	}


	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}


	public String getHoraTermino() {
		return horaTermino;
	}


	public void setHoraTermino(String horaTermino) {
		this.horaTermino = horaTermino;
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
		String[] arrData = data.split("/");
		this.data = arrData[2]+"-"+arrData[1]+"-"+arrData[0];
	}

	public String getIdPosto() {
		return idPosto;
	}

	public void setIdPosto(String idPosto) {
		this.idPosto = idPosto;
	}

	public String getIdAbastecedor() {
		return idAbastecedor;
	}

	public void setIdAbastecedor(String idAbastecedor) {
		this.idAbastecedor = idAbastecedor;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getTotalizadorInicial() {
		return totalizadorInicial;
	}

	public void setTotalizadorInicial(String totalizadorInicial) {
		this.totalizadorInicial = totalizadorInicial.replace(",", ".");
	}

	public String getTotalizadorFinal() {
		return totalizadorFinal;
	}

	public void setTotalizadorFinal(String totalizadorFinal) {
		this.totalizadorFinal = totalizadorFinal.replace(",", ".");
	}

	public String getCcObra() {
		return ccObra;
	}

	public void setCcObra(String ccObra) {
		this.ccObra = ccObra;
	}


	public String getQuilometragem() {
		return quilometragem;
	}


	public void setQuilometragem(String quilometragem) {
		this.quilometragem = quilometragem;
	}
	
}
