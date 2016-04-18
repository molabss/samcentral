package br.com.constran.model.paramswrapper.to_mobile_tabs;

import br.com.constran.mobile.persistence.vo.aprop.ApropriacaoVO;
import br.com.constran.mobile.persistence.vo.aprop.mov.ApropriacaoMovimentacaoVO;
import br.com.constran.mobile.persistence.vo.aprop.mov.ViagemVO;

public class MovimentacaoViagemParams {
	
	private String idApropriacao=null;
	private String dataApontamento=null;
	private String idEquipamentoCarga=null;
	private String idMaterialViagem=null;
	private String descMaterialViagem=null;
	private String estacaInicialViagem=null;
	private String estacaFinalViagem=null;
	private String numeroVale=null;
	private String prcCargaViagem=null;
	private String apropriar=null;
	private String obsViagem=null;
	private String peso=null;
	private String numeroFormulario=null;
	private String numeroQRcode=null;
	private String codSeguranca=null;
	private String idEquipamento=null;
	private String dataHoraCadastro=null;
	private String dataHoraAtualoizado=null;
	private String horaApontamento=null;
	private String horaInicio=null;
	private String horaViagem=null;
	private String origemDestino=null;
	
	//EM DESUSO
	private String viraVira =null;
	
	
	public MovimentacaoViagemParams(){
		
	}
	
	
	public MovimentacaoViagemParams(ApropriacaoVO itemApropriacao, ApropriacaoMovimentacaoVO itemMovimentacao, ViagemVO itemViagem){
		
		setIdApropriacao(String.valueOf(itemApropriacao.getId()));
	
		setDataApontamento(itemApropriacao.getDataHoraApontamento());
		
		setHoraApontamento(itemApropriacao.getDataHoraApontamento().split(" ")[1]);
		
		
		if(itemMovimentacao.getEquipamento() != null){
			setIdEquipamento(String.valueOf(itemMovimentacao.getEquipamento().getId()));
		}
		
		if(itemViagem.getIdEquipamentoCarga() != null && itemViagem.getIdEquipamentoCarga() > 0) {
			setIdEquipamentoCarga(String.valueOf(itemViagem.getIdEquipamentoCarga()));
		}
		
		if(itemViagem.getMaterial() != null){
			setIdMaterialViagem(String.valueOf(itemViagem.getMaterial().getId()));
			setDescMaterialViagem(itemViagem.getMaterial().getDescricao());
		}
		
		if(itemViagem.getEstacaIni() != null){
			setEstacaInicialViagem(itemViagem.getEstacaIni());
		}
		
		if(itemViagem.getEstacaFim() != null){
			setEstacaFinalViagem(itemViagem.getEstacaFim());
		}
		
		if(itemViagem.getEticket() != null){
			setNumeroVale(itemViagem.getEticket());
		}
		
		if(itemViagem.getPrcCarga() != null){
			setPrcCargaViagem(itemViagem.getPrcCarga());
		}
		
		if(itemViagem.getApropriar() != null){
			setApropriar(itemViagem.getApropriar());
		}
		else{
			setApropriar("N");
		}
		
		if(itemViagem.getObservacoes() != null && itemViagem.getObservacoes().length() > 0){
			setObsViagem(itemViagem.getObservacoes());
		}
		
		if(itemViagem.getPeso() != null && itemViagem.getPeso().length() > 0){
			setPeso(itemViagem.getPeso());
		}
		
		if(itemViagem.getNroFormulario() != null) {
			if(itemViagem.getNroFormulario().equals("0")) {
				if(itemViagem.getNroFicha() != null) {
					setNumeroFormulario(itemViagem.getNroFicha());
				}
			}
			else{
				setNumeroFormulario(String.valueOf(itemViagem.getNroFormulario()));
			}
		}
		
		if(itemViagem.getNroQRCode() != null){
			setNumeroQRcode(String.valueOf(itemViagem.getNroQRCode()));
		}
		
		if(itemViagem.getCodSeguranca() != null){
			setCodSeguranca(String.valueOf(itemViagem.getCodSeguranca()));
		}
		
		if(itemViagem.getDataHoraCadastro() != null){
			setDataHoraCadastro(itemViagem.getDataHoraCadastro());
		}
		
		if(itemViagem.getDataHoraAtualizacao() != null){
			setDataHoraAtualizado(itemViagem.getDataHoraAtualizacao());
		}
		
		if(itemMovimentacao.getHoraInicio() != null && itemMovimentacao.getHoraInicio().length() > 0){
			setHoraInicio(itemMovimentacao.getHoraInicio());
		}
		
		if(itemViagem.getHoraViagem() != null && itemViagem.getHoraViagem().length() > 0){
			setHoraViagem(itemViagem.getHoraViagem());
		}
		
		if(itemViagem.getTipo() != null && itemViagem.getTipo().length() > 0){
			setOrigemDestino(itemViagem.getTipo().substring(0,1));// mid(request("origemDestino"), 1, 1)
		}
	}
	
	
	
	//SETTERS----------------------------------------------------
	public void setIdEquipamentoCarga(String idEquipamentoCarga) {
		this.idEquipamentoCarga = idEquipamentoCarga;
	}
	public void setIdMaterialViagem(String idMaterialViagem) {
		this.idMaterialViagem = idMaterialViagem;
	}
	public void setDescMaterialViagem(String descMaterialViagem) {
		this.descMaterialViagem = descMaterialViagem;
	}
	public void setEstacaInicialViagem(String estacaInicialViagem) {
		this.estacaInicialViagem = estacaInicialViagem;
	}
	public void setEstacaFinalViagem(String estacaFinalViagem) {
		this.estacaFinalViagem = estacaFinalViagem;
	}
	public void setNumeroVale(String numeroVale) {
		this.numeroVale = numeroVale;
	}
	public void setPrcCargaViagem(String prcCargaViagem) {
		this.prcCargaViagem = prcCargaViagem;
	}
	public void setApropriar(String apropriar) {
		this.apropriar = apropriar;
	}
	public void setObsViagem(String obsViagem) {
		this.obsViagem = obsViagem;
	}
	public void setPeso(String peso) {
		this.peso = peso;
	}
	public void setNumeroFormulario(String numeroFormulario) {
		this.numeroFormulario = numeroFormulario;
	}
	public void setNumeroQRcode(String numeroQRcode) {
		this.numeroQRcode = numeroQRcode;
	}
	public void setCodSeguranca(String codSeguranca) {
		this.codSeguranca = codSeguranca;
	}
	
	
	
	
	public void setDataHoraAtualizado(String dataHoraAtualoizado) {
		
		String[] arrData = dataHoraAtualoizado.split(" ")[0].split("/");
		
		if(dataHoraAtualoizado.split(" ")[1].length() > 0){ //TEM HORA ?
			
			this.dataHoraAtualoizado = arrData[2]+"-"+arrData[1]+"-"+arrData[0]+" "+dataHoraAtualoizado.split(" ")[1];
		
		}else{ // ENTAO POE SO A DATA !
			
			this.dataHoraAtualoizado = arrData[2]+"-"+arrData[1]+"-"+arrData[0];
		}
	}
	
	
	
	
	public void setViraVira(String viraVira) {
		this.viraVira = viraVira;
	}
	
	public void setDataHoraCadastro(String dataHoraCadastro) {
		
		String[] arrData = dataHoraCadastro.split(" ")[0].split("/");
		
		if(dataHoraCadastro.split(" ")[1].length() > 0){ //TEM HORA ?
			
			this.dataHoraCadastro = arrData[2]+"-"+arrData[1]+"-"+arrData[0]+" "+dataHoraCadastro.split(" ")[1];
		
		}else{ // ENTAO POE SO A DATA !
			
			this.dataHoraCadastro = arrData[2]+"-"+arrData[1]+"-"+arrData[0];
		}
	}
	
	
	//GETTERS misturados...----------------------------------------------------
	public String getIdEquipamentoCarga() {
		return idEquipamentoCarga;
	}
	public String getIdMaterialViagem() {
		return idMaterialViagem;
	}
	public String getDescMaterialViagem() {
		return descMaterialViagem;
	}
	public String getEstacaInicialViagem() {
		return estacaInicialViagem;
	}
	public String getEstacaFinalViagem() {
		return estacaFinalViagem;
	}
	public String getNumeroVale() {
		return numeroVale;
	}
	public String getPrcCargaViagem() {
		return prcCargaViagem;
	}
	public String getApropriar() {
		return apropriar;
	}
	public String getObsViagem() {
		return obsViagem;
	}
	public String getPeso() {
		return peso;
	}
	public String getNumeroFormulario() {
		return numeroFormulario;
	}
	public String getNumeroQRcode() {
		return numeroQRcode;
	}
	public String getCodSeguranca() {
		return codSeguranca;
	}
	public String getViraVira() {
		return viraVira;
	}
	public String getDataHoraCadastro() {
		return dataHoraCadastro;
	}
	public String getDataHoraAtualoizado() {
		return dataHoraAtualoizado;
	}
	public String getIdApropriacao() {
		return idApropriacao;
	}
	public void setIdApropriacao(String idApropriacao) {
		this.idApropriacao = idApropriacao;
	}
	public String getDataApontamento() {
		return dataApontamento;
	}
	public void setDataApontamento(String dataApontamento) {
		
		//String[] arrData = dataApontamento.split(" ")[0].split("/");
		//this.dataApontamento = arrData[2]+"-"+arrData[1]+"-"+arrData[0];
		
		
		String[] arrData = dataApontamento.split(" ")[0].split("/");
		
		if(dataApontamento.split(" ")[1].length() > 0) { //TEM HORA ?
			
			this.dataApontamento = arrData[2]+"-"+arrData[1]+"-"+arrData[0]+" "+dataApontamento.split(" ")[1];
		
		} else { // ENTAO POE SO A DATA !
			
			this.dataApontamento = arrData[2]+"-"+arrData[1]+"-"+arrData[0];
		}			
		
		
	}
	public String getHoraApontamento() {
		return horaApontamento;
	}
	public void setHoraApontamento(String horaApontamento) {
		this.horaApontamento = horaApontamento;
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
	public String getHoraViagem() {
		return horaViagem;
	}
	public void setHoraViagem(String horaViagem) {
		this.horaViagem = horaViagem;
	}
	public String getOrigemDestino() {
		return origemDestino;
	}
	public void setOrigemDestino(String origemDestino) {
		this.origemDestino = origemDestino;
	}

}
