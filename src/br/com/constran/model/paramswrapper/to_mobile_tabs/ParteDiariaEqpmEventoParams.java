package br.com.constran.model.paramswrapper.to_mobile_tabs;

import br.com.constran.mobile.persistence.vo.aprop.ApropriacaoVO;
import br.com.constran.mobile.persistence.vo.aprop.eqp.ApropriacaoEquipamentoVO;
import br.com.constran.mobile.persistence.vo.aprop.eqp.EventoVO;

public class ParteDiariaEqpmEventoParams {
	
	private String idApropriacao=null;
	private String dataApontamento=null;
	private String horaApontamento=null;
	private String idEquipamento=null;
	private String horaInicio=null;
	private String horaTermino=null;
	private String estaca=null;
	private String idServico=null;
	private String idParalisacao=null;
	private String idComponente=null;
	private String observacaoEvento=null;
	private String apropriar=null;
	private String dataHoraCadastro=null;
	private String dataHoraAtualizacao=null;
	private String descServico=null;
	private String descParalisacao=null;
	private String descComponente=null;
	
	public ParteDiariaEqpmEventoParams() {
		
	}
	
	public ParteDiariaEqpmEventoParams(ApropriacaoVO itemApropriacao, ApropriacaoEquipamentoVO itemEqpParteDiaria,EventoVO itemEvento) {
		
		setIdApropriacao(String.valueOf(itemApropriacao.getId()));
		
		setHoraApontamento(itemApropriacao.getDataHoraApontamento().split(" ")[1]);
		
		setDataApontamento(itemApropriacao.getDataHoraApontamento());
		
		setIdEquipamento(String.valueOf(itemEqpParteDiaria.getEquipamento().getId()));
		
		if(itemEvento.getHoraTermino() != null) {
			setHoraTermino(itemEvento.getHoraTermino());
		}
		
		if(itemEvento.getHoraInicio() != null){
			setHoraInicio(itemEvento.getHoraInicio());
		}
		
		
		if(itemEvento.getServico() != null){
			setIdServico(String.valueOf(itemEvento.getServico().getId()));
			setDescServico(itemEvento.getServico().getDescricao());
		}
		
		if(itemEvento.getParalisacao() != null) {
			setIdParalisacao(itemEvento.getParalisacao().getCodigo());
			setDescParalisacao(itemEvento.getParalisacao().getDescricao());
		}
		
		if(itemEvento.getComponente() != null) {
			setIdComponente(String.valueOf(itemEvento.getComponente().getId()));
			setDescComponente(itemEvento.getComponente().getDescricao());
		}
		
		if(itemEvento.getEstaca() != null) {
			setEstaca(itemEvento.getEstaca());
		}
		
		if(itemEvento.getObservacoes() != null) {
			setObservacaoEvento(itemEvento.getObservacoes().trim());
		}
		
		if(itemEvento.getApropriar() != null) {
			setApropriar(itemEvento.getApropriar());
		}
		else{
			setApropriar("N");
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
		
		if(dataApontamento == null || dataApontamento.length() == 0) return;
		
		String[] arrData = dataApontamento.split(" ")[0].split("/");
		this.dataApontamento = arrData[2]+"-"+arrData[1]+"-"+arrData[0]+" "+getHoraApontamento();
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
	public String getHoraTermino() {
		return horaTermino;
	}
	public void setHoraTermino(String horaTermino) {
		this.horaTermino = horaTermino;
	}
	public String getEstaca() {
		return estaca;
	}
	public void setEstaca(String estaca) {
		this.estaca = estaca;
	}
	public String getIdServico() {
		return idServico;
	}
	public void setIdServico(String idServico) {
		this.idServico = idServico;
	}
	public String getIdParalisacao() {
		return idParalisacao;
	}
	public void setIdParalisacao(String idParalisacao) {
		this.idParalisacao = idParalisacao;
	}
	public String getIdComponente() {
		return idComponente;
	}
	public void setIdComponente(String idComponente) {
		this.idComponente = idComponente;
	}
	public String getObservacaoEvento() {
		return observacaoEvento;
	}
	public void setObservacaoEvento(String observacaoEvento) {
		this.observacaoEvento = observacaoEvento;
	}
	public String getApropriar() {
		return apropriar;
	}
	public void setApropriar(String apropriar) {
		this.apropriar = apropriar;
	}
	public String getDataHoraCadastro() {
		return dataHoraCadastro;
	}
	public void setDataHoraCadastro(String dataHoraCadastro) {
		this.dataHoraCadastro = dataHoraCadastro;
	}
	public String getDataHoraAtualizacao() {
		return dataHoraAtualizacao;
	}
	public void setDataHoraAtualizacao(String dataHoraAtualizacao) {
		this.dataHoraAtualizacao = dataHoraAtualizacao;
	}

	public String getDescServico() {
		return descServico;
	}

	public void setDescServico(String descServico) {
		this.descServico = descServico;
	}

	public String getDescParalisacao() {
		return descParalisacao;
	}

	public void setDescParalisacao(String descParalisacao) {
		this.descParalisacao = descParalisacao;
	}

	public String getDescComponente() {
		return descComponente;
	}

	public void setDescComponente(String descComponente) {
		this.descComponente = descComponente;
	}

	
	

}
