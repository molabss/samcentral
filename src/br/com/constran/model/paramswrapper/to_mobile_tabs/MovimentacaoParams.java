package br.com.constran.model.paramswrapper.to_mobile_tabs;

import br.com.constran.mobile.persistence.vo.aprop.ApropriacaoVO;
import br.com.constran.mobile.persistence.vo.aprop.mov.ApropriacaoMovimentacaoVO;

public class MovimentacaoParams {
	
	private String idApropriacao = null;
	private String idMatMovimentacao=null;
	private String descMatMovimentacao=null;
	private String idOrigem=null;
	private String descOrigem=null;
	private String idDestino=null;
	private String descDestino=null;
	private String estacaOrigemInicial=null;
	private String estacaOrigemFinal=null;
	private String estacaDestinoInicial=null;
	private String estacaDestinoFinal=null;
	private String horaInicio=null;
	private String horaTermino=null;
	private String qtdViagem=null;
	private String prcCarga=null;
	private String dataApontamento=null;
	private String idEquipamento=null;
	
	
	
	public MovimentacaoParams(){
		
	}
	
	
	public MovimentacaoParams(ApropriacaoMovimentacaoVO itemApropriacaoMov, ApropriacaoVO itemApropriacao){
		
		setIdApropriacao(String.valueOf(itemApropriacao.getId()));
		setIdEquipamento(String.valueOf(itemApropriacaoMov.getEquipamento().getId()));
	
		
		if(itemApropriacao.getDataHoraApontamento() != null && itemApropriacao.getDataHoraApontamento().length() > 0){
			setDataApontamento(itemApropriacao.getDataHoraApontamento());
		}
		
		if(itemApropriacaoMov.getMaterial() != null){
			setIdMatMovimentacao(String.valueOf(itemApropriacaoMov.getMaterial().getId()));
			setDescMatMovimentacao(itemApropriacaoMov.getMaterial().getDescricao());
		}
		
		if(itemApropriacaoMov.getOrigem() != null){
			setIdOrigem(String.valueOf(itemApropriacaoMov.getOrigem().getId()));
			setDescOrigem(itemApropriacaoMov.getOrigem().getDescricao());
		}
		
		if(itemApropriacaoMov.getDestino() != null){
			setIdDestino(String.valueOf(itemApropriacaoMov.getDestino().getId()));
			setDescDestino(itemApropriacaoMov.getDestino().getDescricao());
		}
		
		if(itemApropriacaoMov.getEstacaOrigemInicial() != null){
			setEstacaOrigemInicial(itemApropriacaoMov.getEstacaOrigemInicial());
		}
		
		if(itemApropriacaoMov.getEstacaOrigemFinal() != null){
			setEstacaOrigemFinal(itemApropriacaoMov.getEstacaOrigemFinal());
		}
		
		if(itemApropriacaoMov.getEstacaDestinoInicial() != null){
			setEstacaDestinoInicial(itemApropriacaoMov.getEstacaDestinoInicial());
		}
		
		if(itemApropriacaoMov.getEstacaDestinoFinal() != null){
			setEstacaDestinoFinal(itemApropriacaoMov.getEstacaDestinoFinal());
		}
		
		if(itemApropriacaoMov.getHoraInicio() != null && itemApropriacaoMov.getHoraInicio().length() > 0) {
			setHoraInicio(itemApropriacaoMov.getHoraInicio());
		}
		
		if(itemApropriacaoMov.getHoraTermino() != null && itemApropriacaoMov.getHoraTermino().length() > 0) {
			setHoraTermino(itemApropriacaoMov.getHoraTermino());
		}
		
		if(itemApropriacaoMov.getQtdViagens() != null){
			setQtdViagem(itemApropriacaoMov.getQtdViagens());
		}
		
		if(itemApropriacaoMov.getPrcCarga() != null){
			setPrcCarga(itemApropriacaoMov.getPrcCarga());
		}
	}
	
	//SETTERS-----------------------------------------------------
	public void setIdMatMovimentacao(String idMatMovimentacao) {
		this.idMatMovimentacao = idMatMovimentacao;
	}
	public void setDescMatMovimentacao(String descMatMovimentacao) {
		this.descMatMovimentacao = descMatMovimentacao;
	}
	public void setIdOrigem(String idOrigem) {
		this.idOrigem = idOrigem;
	}
	public void setDescOrigem(String descOrigem) {
		this.descOrigem = descOrigem;
	}
	public void setIdDestino(String idDestino) {
		this.idDestino = idDestino;
	}
	public void setDescDestino(String descDestino) {
		this.descDestino = descDestino;
	}
	public void setEstacaOrigemInicial(String estacaOrigemInicial) {
		this.estacaOrigemInicial = estacaOrigemInicial;
	}
	public void setEstacaOrigemFinal(String estacaOrigemFinal) {
		this.estacaOrigemFinal = estacaOrigemFinal;
	}
	public void setEstacaDestinoInicial(String estacaDestinoInicial) {
		this.estacaDestinoInicial = estacaDestinoInicial;
	}
	public void setEstacaDestinoFinal(String estacaDestinoFinal) {
		this.estacaDestinoFinal = estacaDestinoFinal;
	}
	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}
	public void setHoraTermino(String horaTermino) {
		this.horaTermino = horaTermino;
	}
	public void setQtdViagem(String qtdViagem) {
		this.qtdViagem = qtdViagem;
	}
	public void setPrcCarga(String prcCarga) {
		this.prcCarga = prcCarga;
	}
	public void setIdApropriacao(String idApropriacao) {
		this.idApropriacao = idApropriacao;
	}
	
	public void setDataApontamento(String dataApontamento) {
		
		String[] arrData = dataApontamento.split(" ")[0].split("/");
			
		if(dataApontamento.split(" ")[1].length() > 0) { //TEM HORA ?
			
			this.dataApontamento = arrData[2]+"-"+arrData[1]+"-"+arrData[0]+" "+dataApontamento.split(" ")[1];
		
		} else { // ENTAO POE SO A DATA !
			
			this.dataApontamento = arrData[2]+"-"+arrData[1]+"-"+arrData[0];
		}
	}
	
	public void setIdEquipamento(String idEquipamento) {
		this.idEquipamento = idEquipamento;
	}	
	
	
	//GETTERS-----------------------------------------------------
	public String getIdMatMovimentacao() {
		return idMatMovimentacao;
	}
	public String getDescMatMovimentacao() {
		return descMatMovimentacao;
	}
	public String getIdOrigem() {
		return idOrigem;
	}
	public String getDescOrigem() {
		return descOrigem;
	}
	public String getIdDestino() {
		return idDestino;
	}
	public String getDescDestino() {
		return descDestino;
	}
	public String getEstacaOrigemInicial() {
		return estacaOrigemInicial;
	}
	public String getEstacaOrigemFinal() {
		return estacaOrigemFinal;
	}
	public String getEstacaDestinoInicial() {
		return estacaDestinoInicial;
	}
	public String getEstacaDestinoFinal() {
		return estacaDestinoFinal;
	}
	public String getHoraInicio() {
		return horaInicio;
	}
	public String getHoraTermino() {
		return horaTermino;
	}
	public String getQtdViagem() {
		return qtdViagem;
	}
	public String getPrcCarga() {
		return prcCarga;
	}
	public String getDataApontamento() {
		return dataApontamento;
	}
	public String getIdApropriacao() {
		return idApropriacao;
	}
	public String getIdEquipamento() {
		return idEquipamento;
	}
}
