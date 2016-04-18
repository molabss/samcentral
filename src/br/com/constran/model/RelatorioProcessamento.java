package br.com.constran.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import br.com.constran.mobile.persistence.vo.ManutencaoEquipamentoVO;
import br.com.constran.mobile.persistence.vo.aprop.ApropriacaoVO;
import br.com.constran.mobile.persistence.vo.aprop.eqp.ApropriacaoEquipamentoVO;
import br.com.constran.mobile.persistence.vo.aprop.mov.ApropriacaoMovimentacaoVO;
import br.com.constran.mobile.persistence.vo.imp.json.ExportMobileDate;
import br.com.constran.mobile.persistence.vo.rae.RaeVO;
import br.com.constran.mobile.persistence.vo.rae.abs.AbastecimentoVO;

public class RelatorioProcessamento {
	
	List <ExportMobileDateReportWrapper> listaExmdCW;
	ExportMobileDate exmd = null;
	StringBuilder html;
	
	private int errosImport_MovimentacaoParteDiariaEqpm;
	private int errosImport_ServMaoObraParalisEquipe;
	private int errosImport_AbastecimentoLubrificacao;
	private String imagemAssinatura;
	
	private boolean errosImpTabs = false;
	
	
	public RelatorioProcessamento() {
		listaExmdCW = new ArrayList<ExportMobileDateReportWrapper>();
	}
	
	
	public void addExmdRWProcessado(ExportMobileDateReportWrapper exmdRW) {
		listaExmdCW.add(exmdRW);
	}
	
	
	public List<ExportMobileDateReportWrapper> getExmdRWProcessado() {
		 return listaExmdCW;
	}
	
	
	private void contarObjetosArquivo(){
		
		for(ExportMobileDateReportWrapper exmdCW : listaExmdCW){
			
			if(exmdCW == null || exmdCW.getExmd() == null) continue;
			
			exmd = exmdCW.getExmd();
		
			for(ApropriacaoVO aprop : exmdCW.getExmd().getApropriacoes()) {
				
				if(aprop.getMovimentacoes()!= null){
					
					exmdCW.addMovimentacoes(aprop.getMovimentacoes().size());
					
					for(ApropriacaoMovimentacaoVO mov : aprop.getMovimentacoes()) {
						
						if(mov.getViagens() == null) continue;
						
						exmdCW.addQtdMovimentacaoViagens(mov.getViagens().size());
					}
				}
				
				if(aprop.getPartesDiarias() != null)
					exmdCW.addQtdPartesDiarias(aprop.getPartesDiarias().size());
				
				if(aprop.getPartesDiarias() != null){
				
					for(ApropriacaoEquipamentoVO eqpm : aprop.getPartesDiarias()) {
						
						if(eqpm.getEventos() == null) continue;
						
						exmdCW.addQtdParteDiariasEventos(eqpm.getEventos().size());
					}
				}
				
				if(aprop.getServicos() != null)				
					exmdCW.addQtdServicos(aprop.getServicos().size());
				
				if(aprop.getMaosObras() != null)
					exmdCW.addQtdMaoDeObra(aprop.getMaosObras().size());
				
				if(aprop.getParalisacoesEquipe() != null)
					exmdCW.addQtdParalisacaoEquipe(aprop.getParalisacoesEquipe().size());
				
				if(aprop.getParalisacoesMaoObra() != null)
					exmdCW.addQtdParalisacaoMaoDeObra(aprop.getParalisacoesMaoObra().size());
			}
			
			if(exmdCW.getExmd().getRaes() != null){
				
				exmdCW.addQtdRAE(exmdCW.getExmd().getRaes().size());
			
				for(RaeVO rae : exmdCW.getExmd().getRaes()){
					
					if(rae.getAbastecimentos() == null) continue;
					
					exmdCW.addQtdAbastecimento(rae.getAbastecimentos().size());

					for(AbastecimentoVO abast : rae.getAbastecimentos()){
						
						if(abast.getLubrificacaoDetalhes() == null) continue;
						
						exmdCW.addQtdLubrificacaoDetalhe(abast.getLubrificacaoDetalhes().size());
					}
				}
				
				if(exmdCW.getExmd().getSaldoPosto() != null && exmdCW.getExmd().getSaldoPosto().getSaldos() != null) {
					
					exmdCW.addQtdSaldoPosto(exmdCW.getExmd().getSaldoPosto().getSaldos().size());
				}
			}
			
			if(exmdCW.getExmd().getManutencaoEquipamentos() != null){
				
				exmdCW.addQtdManutencoesEquipamentos(exmdCW.getExmd().getManutencaoEquipamentos().size());
				
				for(ManutencaoEquipamentoVO itemEquipamento : exmdCW.getExmd().getManutencaoEquipamentos()) {
					
					if(itemEquipamento.getServicos() == null) continue;
	
					exmdCW.addQtdManutencoesServicos(itemEquipamento.getServicos().size());
				}
			}
		}
	}
	
	
	public String obterRelatorioHTML() {
		
		contarObjetosArquivo();
		html = new StringBuilder();
		
		html.append("<!DOCTYPE html>");
		html.append("<html>");
		html.append(	"<head>");
		html.append(		"<meta charset=\"UTF-8\">");
		html.append(		"<style>");
		html.append(			"body {font-family: Arial, Helvetica, sans-serif;}");
		html.append(			"table, th, td {border: 1px solid black;border-collapse: collapse;}");
		html.append(			"th, td {padding: 5px;}");
		html.append(		"</style>");
		html.append(	"</head>");
		html.append(	"<body>");
		html.append(		"<h3>Este é um e-mail automático. Por favor, não responda.</h3>");
		html.append(		"<br/><br/>");		
		
		for(ExportMobileDateReportWrapper exmdCW : listaExmdCW) {
			
			if(listaExmdCW.indexOf(exmdCW) > 0) html.append("<br/><br/><br/>");
			
			if(exmdCW.isHouveErro() == true){ //CASO HOUVE ERRO NA LEITURA/ PROCESSAMENTO DO ARQUIVO PARA TABELAS MOBILE

				html.append(		"<table style=\"width:50%\">");
				html.append(			"<tr>");
				html.append(				"<th>Arquivo</th>");
				html.append(				"<th>Tentou processar em</th>");
				html.append(				"<th>Log de sistema</th>");
				html.append(			"</tr>");
				html.append(			"<tr>");
				html.append(				"<td>").append(exmdCW.getNmArquivo()).append("</td>");
				html.append(				"<td>").append(exmdCW.getDataProcessado()).append("</td>");
				html.append(				"<td>").append(exmdCW.getMsgErroSistema()).append("</td>");
				html.append(			"</tr>");
				html.append(		"</table>");
				html.append(		"<br/><br/>");				
				html.append(		"<h3>Ocorreu um erro ao tentar processar este arquivo.<br/>Entre em contato com o suporte técnico.</h3>");
				
				html.append(		"<br/><br/>");
				
				//html.append(addAssinatura());
				
				//html.append(	"</body>");
				//html.append("</html>");
				
				//return html.toString();
			}
			else{
	
				html.append(		"<table style=\"width:50%\">");
				html.append(			"<tr>");
				html.append(				"<th>Arquivo</th>");
				html.append(				"<th>Obra</th>");
				html.append(				"<th>Dispositivo</th>");
				html.append(				"<th>Processado</th>");
				html.append(				"<th>Início</th>");
				html.append(				"<th>Termino</th>");
				html.append(			"</tr>");
				html.append(			"<tr>");
				html.append(				"<td>").append(exmdCW.getNmArquivo()).append("</td>");
				html.append(				"<td>").append(exmdCW.getCcObra()).append("</td>");
				html.append(				"<td>").append(exmdCW.getIdDispositivo()).append("</td>");
				html.append(				"<td>").append(exmdCW.getDataProcessado()).append("</td>");
				html.append(				"<td>").append(exmdCW.getHoraInicio()).append("</td>");
				html.append(				"<td>").append(exmdCW.getHoraTermino()).append("</td>");
				html.append(			"</tr>");
				html.append(		"</table>");
				html.append(		"<br/><br/>");
				
				html.append(		"<table style=\"width:60%\">");
				html.append(			"<tr>");
				html.append(				"<th colspan=\"2\" bgcolor=\"#FFCC66\">").append("Movimentações").append("</th>");
				html.append(			"</tr>");
				html.append(			"<tr>");
				html.append(				"<th>").append("Enviado").append("</th>");
				html.append(				"<th>").append("Cadastrado").append("</th>");
				html.append(			"</tr>");
				html.append(			"<tr>");
				html.append(				"<td>").append(exmdCW.getQtdMovimentacoes()).append("</td>");
				html.append(				"<td>").append(exmdCW.getQtdInseridoMovimentacao()).append("</td>");
				html.append(			"</tr>");
				html.append(		"</table>");
				html.append(		"<br/>");
				
				html.append(		"<table style=\"width:60%\">");
				html.append(			"<tr>");
				html.append(				"<th colspan=\"2\" bgcolor=\"#FFCC66\">").append("Movimentações Viagens").append("</th>");
				html.append(			"</tr>");
				html.append(			"<tr>");
				html.append(				"<th>").append("Enviado").append("</th>");
				html.append(				"<th>").append("Cadastrado").append("</th>");
				html.append(			"</tr>");
				html.append(			"<tr>");
				html.append(				"<td>").append(exmdCW.getQtdMovimentacaoViagens()).append("</td>");
				html.append(				"<td>").append(exmdCW.getQtdInseridoMovimentacaoViagens()).append("</td>");
				html.append(			"</tr>");
				html.append(		"</table>");
	  	        html.append(		"<br/>");
	  	        
				html.append(		"<table style=\"width:60%\">");
				html.append(			"<tr>");
				html.append(				"<th colspan=\"2\" bgcolor=\"#FFCC66\">").append("Parte diárias").append("</th>");
				html.append(			"</tr>");
	     		html.append(			"<tr>");
				html.append(				"<th>").append("Enviado").append("</th>");
				html.append(				"<th>").append("Cadastrado").append("</th>");
				html.append(			"</tr>");
				html.append(			"<tr>");
				html.append(				"<td>").append(exmdCW.getQtdPartesDiarias()).append("</td>");
				html.append(				"<td>").append(exmdCW.getQtdInseridoParteDiariaEquipamentos()).append("</td>");
				html.append(			"</tr>");
				html.append(		"</table>");
				html.append(		"<br/>");
				
				html.append(		"<table style=\"width:60%\" bgcolor=\"#FFCC66\">");
				html.append(			"<tr>");
				html.append(				"<th colspan=\"2\">").append("Parte diária Eventos").append("</th>");
				html.append(			"</tr>");
				html.append(			"<tr>");
				html.append(				"<th>").append("Enviado").append("</th>");
				html.append(				"<th>").append("Cadastrado").append("</th>");
				html.append(			"</tr>");
				html.append(			"<tr>");
				html.append(				"<td>").append(exmdCW.getQtdParteDiariasEventos()).append("</td>");
				html.append(				"<td>").append(exmdCW.getQtdInseridoEquipamentosEventos()).append("</td>");
				html.append(			"</tr>");
				html.append(		"</table>");
				html.append(		"<br/>");
				
				html.append(		"<table style=\"width:60%\">");
				html.append(			"<tr>");
				html.append(				"<th colspan=\"2\" bgcolor=\"#FFCC66\">").append("Serviços").append("</th>");
				html.append(			"</tr>");
				html.append(			"<tr>");
				html.append(				"<th>").append("Enviado").append("</th>");
				html.append(				"<th>").append("Cadastrado").append("</th>");
				html.append(			"</tr>");
				html.append(			"<tr>");
				html.append(				"<td>").append(exmdCW.getQtdServicos()).append("</td>");
				html.append(				"<td>").append(exmdCW.getQtdInseridoServico()).append("</td>");
				html.append(			"</tr>");
				html.append(		"</table>");
				html.append(		"<br/>");
				
				html.append(		"<table style=\"width:60%\">");
				html.append(			"<tr>");
				html.append(				"<th colspan=\"2\" bgcolor=\"#FFCC66\">").append("Mão de Obra").append("</th>");
				html.append(			"</tr>");
				html.append(			"<tr>");
				html.append(				"<th>").append("Enviado").append("</th>");
				html.append(				"<th>").append("Cadastrado").append("</th>");
				html.append(			"</tr>");
				html.append(			"<tr>");
				html.append(				"<td>").append(exmdCW.getQtdMaoDeObra()).append("</td>");
				html.append(				"<td>").append(exmdCW.getQtdInseridoMaoDeObra()).append("</td>");
				html.append(			"</tr>");
				html.append(		"</table>");
				html.append(		"<br/>");
				
				html.append(		"<table style=\"width:60%\">");
				html.append(			"<tr>");
				html.append(				"<th colspan=\"2\" bgcolor=\"#FFCC66\">").append("Paralização Equipe").append("</th>");
				html.append(			"</tr>");
				html.append(			"<tr>");
				html.append(				"<th>").append("Enviado").append("</th>");
				html.append(				"<th>").append("Cadastrado").append("</th>");
				html.append(			"</tr>");
				html.append(			"<tr>");
				html.append(				"<td>").append(exmdCW.getQtdParalisacaoEquipe()).append("</td>");
				html.append(				"<td>").append(exmdCW.getQtdInseridoParalisacaoEquipe()).append("</td>");
				html.append(			"</tr>");
				html.append(		"</table>");
				html.append(		"<br/>");
				
				html.append(		"<table style=\"width:60%\">");
				html.append(			"<tr>");
				html.append(				"<th colspan=\"2\" bgcolor=\"#FFCC66\">").append("Paralização Mão de Obra").append("</th>");
				html.append(			"</tr>");
				html.append(			"<tr>");
				html.append(				"<th>").append("Enviado").append("</th>");
				html.append(				"<th>").append("Cadastrado").append("</th>");
				html.append(			"</tr>");
				html.append(			"<tr>");
				html.append(				"<td>").append(exmdCW.getQtdParalisacaoMaoDeObra()).append("</td>");
				html.append(				"<td>").append(exmdCW.getQtdInseridoParalisacaoMaoDeObra()).append("</td>");
				html.append(			"</tr>");
				html.append(		"</table>");
				html.append(		"<br/>");
				
				html.append(		"<table style=\"width:60%\">");
				html.append(			"<tr>");
				html.append(				"<th colspan=\"2\" bgcolor=\"#FFCC66\">").append("Abastecimento/ Lubrificação").append("</th>");
				html.append(			"</tr>");
				html.append(			"<tr>");
				html.append(				"<th>").append("Enviado").append("</th>");
				html.append(				"<th>").append("Cadastrado").append("</th>");
				html.append(			"</tr>");
				html.append(			"<tr>");
				html.append(				"<td>").append(exmdCW.getQtdAbastecimento()).append("</td>");
				html.append(				"<td>").append(exmdCW.getQtdInseridoAbastecimento()).append("</td>");
				html.append(			"</tr>");
				html.append(		"</table>");
				html.append(		"<br/>");
				
				html.append(		"<table style=\"width:60%\">");
				html.append(			"<tr>");
				html.append(				"<th colspan=\"2\" bgcolor=\"#FFCC66\">").append("Lubrificação detalhes").append("</th>");
				html.append(			"</tr>");
				html.append(			"<tr>");
				html.append(				"<th>").append("Enviado").append("</th>");
				html.append(				"<th>").append("Cadastrado").append("</th>");
				html.append(			"</tr>");
				html.append(			"<tr>");
				html.append(				"<td>").append(exmdCW.getQtdLubrificacaoDetalhe()).append("</td>");
				html.append(				"<td>").append(exmdCW.getQtdInseridoLubrificacaoDetalhe()).append("</td>");
				html.append(			"</tr>");
				html.append(		"</table>");
				html.append(		"<br/>");			
				
				html.append(		"<table style=\"width:60%\">");
				html.append(			"<tr>");
				html.append(				"<th colspan=\"2\" bgcolor=\"#FFCC66\">").append("RAEs").append("</th>");
				html.append(			"</tr>");
				html.append(			"<tr>");
				html.append(				"<th>").append("Enviado").append("</th>");
				html.append(				"<th>").append("Cadastrado").append("</th>");
				html.append(			"</tr>");
				html.append(			"<tr>");
				html.append(				"<td>").append(exmdCW.getQtdRAE()).append("</td>");
				html.append(				"<td>").append(exmdCW.getQtdInseridoRAE()).append("</td>");
				html.append(			"</tr>");
				html.append(		"</table>");
				html.append(		"<br/>");
				
				html.append(		"<table style=\"width:60%\">");
				html.append(			"<tr>");
				html.append(				"<th colspan=\"2\" bgcolor=\"#FFCC66\">").append("Saldo Posto").append("</th>");
				html.append(			"</tr>");
				html.append(			"<tr>");
				html.append(				"<th>").append("Enviado").append("</th>");
				html.append(				"<th>").append("Cadastrado").append("</th>");
				html.append(			"</tr>");
				html.append(			"<tr>");
				html.append(				"<td>").append(exmdCW.getQtdSaldoPosto()).append("</td>");
				html.append(				"<td>").append(exmdCW.getQtdInseridoSaldoPosto()).append("</td>");
				html.append(			"</tr>");
				html.append(		"</table>");
				html.append(		"<br/>");
				
				html.append(		"<table style=\"width:60%\">");
				html.append(			"<tr>");
				html.append(				"<th colspan=\"2\" bgcolor=\"#FFCC66\">").append("Manutenções equipamentos").append("</th>");
				html.append(			"</tr>");
				html.append(			"<tr>");
				html.append(				"<th>").append("Enviado").append("</th>");
				html.append(				"<th>").append("Cadastrado").append("</th>");
				html.append(			"</tr>");
				html.append(			"<tr>");
				html.append(				"<td>").append(exmdCW.getQtdManutencoesEquipamentos()).append("</td>");
				html.append(				"<td>").append(exmdCW.getQtdInseridoManutencoes()).append("</td>");
				html.append(			"</tr>");
				html.append(		"</table>");
				html.append(		"<br/>");
				
				html.append(		"<table style=\"width:60%\">");
				html.append(			"<tr>");
				html.append(				"<th colspan=\"2\" bgcolor=\"#FFCC66\">").append("Manutenções serviços realizados").append("</th>");
				html.append(			"</tr>");
				html.append(			"<tr>");
				html.append(				"<th>").append("Enviado").append("</th>");
				html.append(				"<th>").append("Cadastrado").append("</th>");
				html.append(			"</tr>");
				html.append(			"<tr>");
				html.append(				"<td>").append(exmdCW.getQtdManutencoesServicos()).append("</td>");
				html.append(				"<td>").append(exmdCW.getQtdInseridoManutencoesServicos()).append("</td>");
				html.append(			"</tr>");
				html.append(		"</table>");
			}
		}
		
		if(errosImpTabs == true){//CASO HOUVER ERRO AO TRANSFERIR OS DADOS DAS TABELAS MOBILE PARA AS TABELAS IMPORT
		
			html.append(		"<br/><br/>");
			
			html.append(		"<table style=\"width:50%\">");
			html.append(			"<tr>");
			html.append(				"<th colspan=\"3\">Erros de importação</th>");
			html.append(			"</tr>");
			html.append(			"<tr>");
			html.append(				"<th>Movimentações e <br/>Parte Diária de Equipamentos</th>");
			html.append(				"<th>Serviços/ Mão de Obra/<br/> Paralisação Equipe</th>");
			html.append(				"<th>Abastecimento e<br/> Lubrificaçãpo</th>");
			html.append(			"</tr>");
			html.append(			"<tr>");
			html.append(				"<td>").append(errosImport_MovimentacaoParteDiariaEqpm).append("</td>");
			html.append(				"<td>").append(errosImport_ServMaoObraParalisEquipe).append("</td>");
			html.append(				"<td>").append(errosImport_AbastecimentoLubrificacao).append("</td>");
			html.append(			"</tr>");
			html.append(		"</table>");
		}
		
		html.append(		"<br/><br/>");
		
		html.append(addAssinatura());
		
		html.append(	"</body>");
		html.append("</html>");
		
		return html.toString();
	}
	
	
	public String addAssinatura(){
		
		StringBuilder html = new StringBuilder();
		html.append(		"#ASSINATURA#");
		html.append(		"<br/>");
		html.append(		"<p>Sistema de Apropriações Mobile</p>");
		html.append(		"<p>Tecnologia da Informação</p>");
		html.append(		"<p><a href=\"www.constran.com.br\">www.constran.com.br</a></p>");
		
		return html.toString();
		
	}
	
	public int getErrosImport_MovimentacaoParteDiariaEqpm() {
		return errosImport_MovimentacaoParteDiariaEqpm;
	}


	public void setErrosImport_MovimentacaoParteDiariaEqpm(
			int errosImport_MovimentacaoParteDiariaEqpm) {
		this.errosImport_MovimentacaoParteDiariaEqpm = errosImport_MovimentacaoParteDiariaEqpm;
	}


	public int getErrosImport_ServMaoObraParalisEquipe() {
		return errosImport_ServMaoObraParalisEquipe;
	}


	public void setErrosImport_ServMaoObraParalisEquipe(
			int errosImport_ServMaoObraParalisEquipe) {
		this.errosImport_ServMaoObraParalisEquipe = errosImport_ServMaoObraParalisEquipe;
	}


	public int getErrosImport_AbastecimentoLubrificacao() {
		return errosImport_AbastecimentoLubrificacao;
	}


	public void setErrosImport_AbastecimentoLubrificacao(
			int errosImport_AbastecimentoLubrificacao) {
		this.errosImport_AbastecimentoLubrificacao = errosImport_AbastecimentoLubrificacao;
	}


	public String obterRelatorio() {
		
		return null;
	}


	public File getImagemAssinatura() {
		return new File(imagemAssinatura);
	}


	public void setImagemAssinatura(String fullPath) {
		this.imagemAssinatura = fullPath;
	}


	public boolean isErrosImpTabs() {
		return errosImpTabs;
	}


	public void setErrosImpTabs(boolean errosImpTabs) {
		this.errosImpTabs = errosImpTabs;
	}
}
