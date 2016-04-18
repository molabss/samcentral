package br.com.constran.model;

import java.util.Calendar;

import br.com.constran.mobile.persistence.vo.imp.json.ExportMobileDate;

public class ExportMobileDateReportWrapper {
	
	private String nmArquivo;
	//private boolean processamentoOK = false;
	
	private String msgErroSistema;
	
	private ExportMobileDate exmd;
	private String dataProcessado;
	private String horaInicio;
	private String horaTermino;
	
	private String ccObra;
	private String idDispositivo;
	private String tolerancia;
	
	private int qtdMovimentacoes;
	private int qtdMovimentacaoViagens;
	private int qtdPartesDiarias;
	private int qtdParteDiariasEventos;
	private int qtdServicos;
	private int qtdMaoDeObra;
	private int qtdParalisacaoEquipe;
	private int qtdParalisacaoMaoDeObra;
	private int qtdAbastecimento;
	private int qtdRAE;
	private int qtdLubrificacaoDetalhe;
	private int qtdSaldoPosto;
	private int qtdManutencoesEquipamentos;
	private int qtdManutencoesServicos;
	
	private int qtdInseridoApropriacoes;
	private int qtdInseridoMovimentacao;
	private int qtdInseridoMovimentacaoViagens;
	private int qtdInseridoParteDiariaEquipamentos;
	private int qtdInseridoEquipamentosEventos;
	private int qtdInseridoServico;
	private int qtdInseridoMaoDeObra;
	private int qtdInseridoParalisacaoEquipe;
	private int qtdInseridoParalisacaoMaoDeObra;
	private int qtdInseridoRAE;
	private int qtdInseridoAbastecimento;
	private int qtdInseridoLubrificacaoDetalhe;
	private int qtdInseridoSaldoPosto = 0;
	private int qtdInseridoManutencoes;
	private int qtdInseridoManutencoesServicos;
	
	private boolean houveErro = false;
	
	//private boolean processamentoOK_mobTabs = false;
	//private boolean processamentoOK_importTabs = false;
	
	

	
	
	//public boolean isProcessamentoOK_mobTabs() {
		//return processamentoOK_mobTabs;
	//}

	//public void setProcessamentoOK_mobTabs(boolean processamentoOK_mobTabs) {
		//this.processamentoOK_mobTabs = processamentoOK_mobTabs;
	//}

	//public boolean isProcessamentoOK_importTabs() {
		//return processamentoOK_importTabs;
	//}

	//public void setProcessamentoOK_importTabs(boolean processamentoOK_importTabs) {
		//this.processamentoOK_importTabs = processamentoOK_importTabs;
	//}

	public ExportMobileDateReportWrapper(String nmArquivo){
		this.nmArquivo = nmArquivo;
	}
	
	public ExportMobileDateReportWrapper(){
		
	}
	
	

	public String getNmArquivo() {
		return nmArquivo;
	}

	public void setNmArquivo(String nmArquivo) {
		this.nmArquivo = nmArquivo;
	}

	public ExportMobileDate getExmd() {
		return exmd;
	}

	public String getDataProcessado() {
		return dataProcessado;
	}
	public void marcarDataProcessado() {
		this.dataProcessado = getDateNow();
	}
	public String getHoraInicio() {
		return horaInicio;
	}
	
	public void marcarHoraInicio() {
		this.horaInicio = getHourNow();
	}
	
	public String getHoraTermino() {
		return horaTermino;
	}
	
	public void marcarHoraTermino() {
		this.horaTermino = getHourNow();
	}
	
	
	private String getHourNow(){
		
		Calendar calendar = Calendar.getInstance();
		int hours = calendar.get(Calendar.HOUR_OF_DAY);
		int minutes = calendar.get(Calendar.MINUTE);
		int seconds = calendar.get(Calendar.SECOND);
		int milli = calendar.get(Calendar.MILLISECOND);
		return hours+":"+minutes+":"+seconds+":"+milli;
	}
	
	private String getDateNow(){
		
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		return (day < 10 ? "0".concat(String.valueOf(day)) : day) 
				+"/"+ 
			   (month+1 < 10 ? "0".concat(String.valueOf(month+1)) : month+1) 
				+"/"+year;		
	}
	
	
	public void addMovimentacoes(int size){
		this.qtdMovimentacoes+=size;
	}
	
	
	public void setExmd(ExportMobileDate exmd) {
		this.exmd = exmd;
	}

	
	public void setCcObra(String ccObra) {
		this.ccObra = ccObra;
	}


	public void setIdDispositivo(String idDispositivo) {
		this.idDispositivo = idDispositivo;
	}


	public void setTolerancia(String tolerancia) {
		this.tolerancia = tolerancia;
	}


	public void setQtdMovimentacoes(int qtdMovimentacoes) {
		this.qtdMovimentacoes = qtdMovimentacoes;
	}




	
	public void addQtdMovimentacaoViagens(int size) {
		this.qtdMovimentacaoViagens+=size;
	}
	


	
	
	public void addQtdPartesDiarias(int size) {
		this.qtdPartesDiarias+=size;
	}


	
	
	public void addQtdParteDiariasEventos(int size) {
		this.qtdParteDiariasEventos+=size;
	}


	
	
	public void addQtdServicos(int size) {
		this.qtdServicos+=size;
	}	



	
	
	public void addQtdMaoDeObra(int size) {
		this.qtdMaoDeObra+=size;
	}


	
	
	public void addQtdParalisacaoEquipe(int size) {
		this.qtdParalisacaoEquipe+=size;
	}



	
	public void addQtdParalisacaoMaoDeObra(int size) {
		this.qtdParalisacaoMaoDeObra+=size;
	}
	

	
	
	public void addQtdAbastecimento(int size) {
		this.qtdAbastecimento+=size;
	}



	
	public void addQtdRAE(int size) {
		this.qtdRAE+=size;
	}
	

	public void addQtdSaldoPosto(int qtdSaldoPosto) {
		this.qtdSaldoPosto+= qtdSaldoPosto;
	}


	public void addQtdManutencoesEquipamentos(int qtdManutencoesEquipamentos) {
		this.qtdManutencoesEquipamentos+= qtdManutencoesEquipamentos;
	}



	public String getCcObra() {
		return ccObra;
	}


	public String getIdDispositivo() {
		return idDispositivo;
	}


	public String getTolerancia() {
		return tolerancia;
	}


	public int getQtdMovimentacoes() {
		return qtdMovimentacoes;
	}


	public int getQtdMovimentacaoViagens() {
		return qtdMovimentacaoViagens;
	}


	public int getQtdPartesDiarias() {
		return qtdPartesDiarias;
	}


	public int getQtdParteDiariasEventos() {
		return qtdParteDiariasEventos;
	}


	public int getQtdServicos() {
		return qtdServicos;
	}


	public int getQtdMaoDeObra() {
		return qtdMaoDeObra;
	}


	public int getQtdParalisacaoEquipe() {
		return qtdParalisacaoEquipe;
	}


	public int getQtdParalisacaoMaoDeObra() {
		return qtdParalisacaoMaoDeObra;
	}


	public int getQtdAbastecimento() {
		return qtdAbastecimento;
	}


	public int getQtdRAE() {
		return qtdRAE;
	}


	public int getQtdSaldoPosto() {
		return qtdSaldoPosto;
	}


	public int getQtdManutencoesEquipamentos() {
		return qtdManutencoesEquipamentos;
	}

	public void addQtdInseridoApropriacoes(int inserido) {
		this.qtdInseridoApropriacoes+= inserido;
	}

	public void addQtdInseridoMovimentacao(int inserido) {
		this.qtdInseridoMovimentacao+= inserido;
	}

	public void addQtdInseridoMovimentacaoViagens(int inserido) {
		this.qtdInseridoMovimentacaoViagens+= inserido;
	}

	public void addQtdInseridoParteDiariaEquipamentos(int inserido) {
		this.qtdInseridoParteDiariaEquipamentos+= inserido;
	}

	public void addQtdInseridoEquipamentosEventos(int inserido) {
		this.qtdInseridoEquipamentosEventos+= inserido;
	}

	public void addQtdInseridoServico(int inserido) {
		this.qtdInseridoServico+= inserido;
	}

	public void addQtdInseridoMaoDeObra(int inserido) {
		this.qtdInseridoMaoDeObra+= inserido;
	}

	public void addQtdInseridoParalisacaoEquipe(int inserido) {
		this.qtdInseridoParalisacaoEquipe+= inserido;
	}

	public void addQtdInseridoParalisacaoMaoDeObra(int inserido) {
		this.qtdInseridoParalisacaoMaoDeObra+= inserido;
	}

	public void addQtdInseridoRAE(int inserido) {
		this.qtdInseridoRAE+= inserido;
	}

	public void addQtdInseridoAbastecimento(int inserido) {
		this.qtdInseridoAbastecimento+= inserido;
	}

	public void addQtdInseridoLubrificacaoDetalhe(int inserido) {
		this.qtdInseridoLubrificacaoDetalhe+= inserido;
	}

	public int getQtdInseridoApropriacoes() {
		return qtdInseridoApropriacoes;
	}


	public int getQtdInseridoMovimentacao() {
		return qtdInseridoMovimentacao;
	}

	public void setQtdInseridoMovimentacao(int qtdInseridoMovimentacao) {
		this.qtdInseridoMovimentacao = qtdInseridoMovimentacao;
	}

	public int getQtdInseridoMovimentacaoViagens() {
		return qtdInseridoMovimentacaoViagens;
	}

	public void setQtdInseridoMovimentacaoViagens(int qtdInseridoMovimentacaoViagens) {
		this.qtdInseridoMovimentacaoViagens = qtdInseridoMovimentacaoViagens;
	}

	public int getQtdInseridoParteDiariaEquipamentos() {
		return qtdInseridoParteDiariaEquipamentos;
	}

	public void setQtdInseridoParteDiariaEquipamentos(
			int qtdInseridoParteDiariaEquipamentos) {
		this.qtdInseridoParteDiariaEquipamentos = qtdInseridoParteDiariaEquipamentos;
	}

	public int getQtdInseridoEquipamentosEventos() {
		return qtdInseridoEquipamentosEventos;
	}

	public void setQtdInseridoEquipamentosEventos(int qtdInseridoEquipamentosEventos) {
		this.qtdInseridoEquipamentosEventos = qtdInseridoEquipamentosEventos;
	}

	public int getQtdInseridoServico() {
		return qtdInseridoServico;
	}

	public void setQtdInseridoServico(int qtdInseridoServico) {
		this.qtdInseridoServico = qtdInseridoServico;
	}

	public int getQtdInseridoMaoDeObra() {
		return qtdInseridoMaoDeObra;
	}

	public void setQtdInseridoMaoDeObra(int qtdInseridoMaoDeObra) {
		this.qtdInseridoMaoDeObra = qtdInseridoMaoDeObra;
	}

	public int getQtdInseridoParalisacaoEquipe() {
		return qtdInseridoParalisacaoEquipe;
	}

	public void setQtdInseridoParalisacaoEquipe(int qtdInseridoParalisacaoEquipe) {
		this.qtdInseridoParalisacaoEquipe = qtdInseridoParalisacaoEquipe;
	}

	public int getQtdInseridoParalisacaoMaoDeObra() {
		return qtdInseridoParalisacaoMaoDeObra;
	}


	public int getQtdInseridoRAE() {
		return qtdInseridoRAE;
	}

	public void setQtdInseridoRAE(int qtdInseridoRAE) {
		this.qtdInseridoRAE = qtdInseridoRAE;
	}

	public int getQtdInseridoLubrificacaoDetalhe() {
		return qtdInseridoLubrificacaoDetalhe;
	}


	public int getQtdInseridoAbastecimento() {
		return qtdInseridoAbastecimento;
	}

	public int getQtdInseridoSaldoPosto() {
		return qtdInseridoSaldoPosto;
	}

	public void addQtdInseridoSaldoPosto(int inseridos) {
		this.qtdInseridoSaldoPosto+= inseridos;
	}

	public int getQtdManutencoesServicos() {
		return qtdManutencoesServicos;
	}

	public void addQtdManutencoesServicos(int qtdManutencoesServicos) {
		this.qtdManutencoesServicos += qtdManutencoesServicos;
	}

	public int getQtdInseridoManutencoes() {
		return qtdInseridoManutencoes;
	}

	public void addQtdInseridoManutencoes(int inseridos) {
		this.qtdInseridoManutencoes += inseridos;
	}

	public int getQtdInseridoManutencoesServicos() {
		return qtdInseridoManutencoesServicos;
	}

	public void addQtdInseridoManutencoesServicos(int inseridos) {
		this.qtdInseridoManutencoesServicos+= inseridos;
	}

	public int getQtdLubrificacaoDetalhe() {
		return qtdLubrificacaoDetalhe;
	}

	public void addQtdLubrificacaoDetalhe(int qtdLubrificacaoDetalhe) {
		this.qtdLubrificacaoDetalhe+= qtdLubrificacaoDetalhe;
	}

	//public boolean isProcessamentoOK() {
		//return processamentoOK;
	//}

	//public void setProcessamentoOK(boolean processamentoOK) {
		//this.processamentoOK = processamentoOK;
	//}

	public String getMsgErroSistema() {
		return msgErroSistema;
	}

	public void setMsgErroSistema(String msgErroSistema) {
		this.msgErroSistema = msgErroSistema;
	}

	public boolean isHouveErro() {
		return houveErro;
	}

	public void setHouveErro(boolean houveErro) {
		this.houveErro = houveErro;
	}
}
