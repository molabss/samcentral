package br.com.constran.model.paramswrapper.to_mobile_tabs;

import br.com.constran.mobile.persistence.vo.aprop.ApropriacaoVO;
import br.com.constran.mobile.persistence.vo.aprop.maodeobra.ParalisacaoMaoObraVO;

public class ParalisacaoMaoDeObraParams {
	
	private String idApropriacao = null;
	private String dataApontamento = null;
	private String horaApontamento = null;
	private String idPessoa = null;
	private String horaInicio = null;
	private String horaTermino = null;
	private String codParalisacao = null;
	private String idServico = null;
	private String idEquipe = null;
	private String observacoes = null;

	
    public ParalisacaoMaoDeObraParams() {
		
	}
    
    public ParalisacaoMaoDeObraParams(ApropriacaoVO itemApropriacao, ParalisacaoMaoObraVO itemParalisacao){
		
    	
    	setIdApropriacao(String.valueOf(itemApropriacao.getId()));
    	
    	setHoraApontamento(itemApropriacao.getDataHoraApontamento().split(" ")[1]);
    	
    	setDataApontamento(itemApropriacao.getDataHoraApontamento());
    	
    	
    	
    	if(itemParalisacao.getPessoa() != null){
    		setIdPessoa(String.valueOf(itemParalisacao.getPessoa().getId()));
    	}

    	
    	if(itemParalisacao.getHoraInicio() != null && itemParalisacao.getHoraInicio().length() > 0){
    		setHoraInicio(itemParalisacao.getHoraInicio());
    	}
    	
    	
    	if(itemParalisacao.getHoraTermino() != null && itemParalisacao.getHoraTermino().length() > 0){
    		setHoraTermino(itemParalisacao.getHoraTermino());
    	}
    	
    	
    	if(itemParalisacao.getParalisacao() != null){
    		setCodParalisacao(itemParalisacao.getParalisacao().getCodigo());
    	}
    	
    	
    	if(itemParalisacao.getServico() != null){
    		setIdServico(String.valueOf(itemParalisacao.getServico().getId()));
    	}
    	
    	
    	if(itemParalisacao.getEquipe() != null){
    		setIdEquipe(String.valueOf(itemParalisacao.getEquipe().getId()));
    	}
    	
    	
    	if(itemParalisacao.getObservacoes() != null && itemParalisacao.getObservacoes().length() > 0){
    		setObservacoes(itemParalisacao.getObservacoes());
    	}
    	
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
		String[] arrData = dataApontamento.split(" ")[0].split("/");
		this.dataApontamento = arrData[2]+"-"+arrData[1]+"-"+arrData[0]+" "+getHoraApontamento();
	}

	public String getHoraApontamento() {
		return horaApontamento;
	}

	public void setHoraApontamento(String horaApontamento) {
		this.horaApontamento = horaApontamento;
	}

	public String getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(String idPessoa) {
		this.idPessoa = idPessoa;
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

	public String getCodParalisacao() {
		return codParalisacao;
	}

	public void setCodParalisacao(String codParalisacao) {
		this.codParalisacao = codParalisacao;
	}

	public String getIdServico() {
		return idServico;
	}

	public void setIdServico(String idServico) {
		this.idServico = idServico;
	}

	public String getIdEquipe() {
		return idEquipe;
	}

	public void setIdEquipe(String idEquipe) {
		this.idEquipe = idEquipe;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	
    
    
    
    
}
