package br.com.constran.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.mail.EmailException;

import br.com.constran.dao.ExportMobileMobileDateDAO;
import br.com.constran.dao.UsuarioDAO;
import br.com.constran.email.Email;
import br.com.constran.mobile.persistence.vo.ManutencaoEquipamentoServicosVO;
import br.com.constran.mobile.persistence.vo.ManutencaoEquipamentoVO;
import br.com.constran.mobile.persistence.vo.aprop.ApropriacaoVO;
import br.com.constran.mobile.persistence.vo.aprop.eqp.ApropriacaoEquipamentoVO;
import br.com.constran.mobile.persistence.vo.aprop.eqp.EventoVO;
import br.com.constran.mobile.persistence.vo.aprop.maodeobra.ApropriacaoMaoObraVO;
import br.com.constran.mobile.persistence.vo.aprop.maodeobra.ApropriacaoServicoVO;
import br.com.constran.mobile.persistence.vo.aprop.maodeobra.ParalisacaoEquipeVO;
import br.com.constran.mobile.persistence.vo.aprop.maodeobra.ParalisacaoMaoObraVO;
import br.com.constran.mobile.persistence.vo.aprop.mov.ApropriacaoMovimentacaoVO;
import br.com.constran.mobile.persistence.vo.aprop.mov.ViagemVO;
import br.com.constran.mobile.persistence.vo.imp.json.ExportMobileDate;
import br.com.constran.mobile.persistence.vo.rae.RaeVO;
import br.com.constran.mobile.persistence.vo.rae.abs.AbastecimentoPostoVO;
import br.com.constran.mobile.persistence.vo.rae.abs.AbastecimentoVO;
import br.com.constran.mobile.persistence.vo.rae.abs.LubrificacaoDetalheVO;
import br.com.constran.mobile.persistence.vo.rae.abs.SaldoPostoVO;
import br.com.constran.model.ApropriacaoPorDataDispositivo;
import br.com.constran.model.ExportMobileDateReportWrapper;
import br.com.constran.model.RelatorioProcessamento;
import br.com.constran.model.paramswrapper.to_import_tabs.ApropMovimEquipParams;
import br.com.constran.model.paramswrapper.to_mobile_tabs.AbastecimentoParams;
import br.com.constran.model.paramswrapper.to_mobile_tabs.ApropriacaoParams;
import br.com.constran.model.paramswrapper.to_mobile_tabs.LubrificacaoDetalheParams;
import br.com.constran.model.paramswrapper.to_mobile_tabs.ManutencaoEquipamentoParams;
import br.com.constran.model.paramswrapper.to_mobile_tabs.ManutencaoServicosParams;
import br.com.constran.model.paramswrapper.to_mobile_tabs.MaoDeObraParams;
import br.com.constran.model.paramswrapper.to_mobile_tabs.MovimentacaoParams;
import br.com.constran.model.paramswrapper.to_mobile_tabs.MovimentacaoViagemParams;
import br.com.constran.model.paramswrapper.to_mobile_tabs.ParalisacaoEquipeParams;
import br.com.constran.model.paramswrapper.to_mobile_tabs.ParalisacaoMaoDeObraParams;
import br.com.constran.model.paramswrapper.to_mobile_tabs.ParteDiariaEqpmEventoParams;
import br.com.constran.model.paramswrapper.to_mobile_tabs.ParteDiariaEquipamentoParams;
import br.com.constran.model.paramswrapper.to_mobile_tabs.RAEParams;
import br.com.constran.model.paramswrapper.to_mobile_tabs.SaldoPostoParams;
import br.com.constran.model.paramswrapper.to_mobile_tabs.ServicoParams;
import br.com.constran.system.AppDirectory;
import br.com.constran.util.AbstractController;
import br.com.constran.util.FileUtil;
import br.com.constran.util.ServerResponse;

/*
		{
		 }   
		\=/>
	MOISES SANTANA moises.santana@contran.com.br - 09/06/2015
*/

//http://192.168.1.64:8080/samcentral/ws/processa/dadosDoArquivo

@Path("/processa")
public class Processador extends AbstractController 
{
	
	ExportMobileMobileDateDAO exmdDAO = null;
	String ccObra = null;
	String idDispositivo = null;
	String idUsuario = null;
	RelatorioProcessamento relatorio = null;
	String emailDestinatario = "";
	String idUsuarioLOGADO = null;
	ExportMobileDateReportWrapper exmdRW = null;
	
	int errosMobTabs = 0;
	int errosImpTabs = 0;
	
	
	
	/*	
	 * ESTE METODO É CHAMADO POR UMA REQUISICAO AJAX CROSS DOMAIN UTILIZANDO JSONP EM UMA PAGINA ASP DA APLICAÇÃO 
	 * PLANEJAMENTO (SAM WEB). 
	 * COMO JSONP SO TRABALHA COM METODO GET NAO PODE SER UTILIZADO METODO POST QUE SERIA O MAIS CORRETO.
	 * */
	
	//http://192.168.1.67:8080/samcentral/ws/cross/processa/dadosDoArquivo?login=moises.santana&nomeArquivos=APR_430000_28012016_090927_T65.json
	
	@GET
	@Path("/dadosDoArquivo")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response enviarDadosParaTabelasMobile(
			@QueryParam("nomeArquivos")String nomeArquivos, @DefaultValue("@")@QueryParam("login")String login) {
		
		Response response = null;
		ApropriacaoPorDataDispositivo apropPorDt = null;
		ExportMobileDate exmd = null;
		List<String> arquivosLista = null;
		
		
		try 
		{
			arquivosLista = Arrays.asList(nomeArquivos.split(";"));
		}
		catch (Exception e) 
		{
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(new ServerResponse("Caso esteja enviando vários arquivos os mesmos precisam estar separados por ponto e vígula \";\"")).build();
		}
		
		
		try 
		{
			abreConexaoBD();
			
			if(usuarioNAOautentico(login))
			{
				fechaConexaoBD();
				return Response.status(Status.UNAUTHORIZED).entity(new ServerResponse("Login inv\u00e1lido", ServerResponse.E25)).build();
			}
			
			UsuarioDAO usuDAO = new UsuarioDAO(conn);
			emailDestinatario = usuDAO.obterEmailLogin(login);
			idUsuarioLOGADO = usuDAO.obterIDusuario(login);
			
			desabilitarBDautoCommit();
			isolarTransacao(Connection.TRANSACTION_READ_COMMITTED);
			
			exmdDAO = new ExportMobileMobileDateDAO(conn);
		} 
		catch (SQLException e) 
		{
			fechaConexaoBD();
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(new ServerResponse("Ocorreu um erro ao interagir com a base de dados.", ServerResponse.E15)).build();
		}
		
		
		/*Transferindo dados do arquivo para as tabelas mobile*/
		try 
		{
			relatorio = new RelatorioProcessamento();
			
			for(String arquivo : arquivosLista) 
			{
				errosMobTabs = 0;
				
				try 
				{
					exmdRW = FileUtil.deserializeSingleJsonFile(arquivo,AppDirectory.DIR_ARQUIVO_EXPORTADO);
					exmd = exmdRW.getExmd();
			
					init(exmd);
			
					exmdRW.marcarDataProcessado();
					exmdRW.marcarHoraInicio();
					exmdRW.setCcObra(ccObra);
					exmdRW.setIdDispositivo(idDispositivo);			
					
					/*** INFORMACOES DAS APROPRIACOES (MOVIMENTACAO E PARTE DIARIA) ***/
					if(existemApropriacoes(exmd)) 
					{
						for(ApropriacaoVO itemApropriacao : exmd.getApropriacoes()) 
						{
							apropPorDt = verificarApropriacaoPorDataDispositivo(itemApropriacao);
							
							if(possoSalvarApropriacoes(apropPorDt)) 
							{
								/*** MOVIMENTACAO DE MATERIAIS ***/	
								
								salvarApropriacoes(itemApropriacao,exmdRW);
								
								salvarMovimentacoes(itemApropriacao,exmdRW);
								
								/*** PARTE DIARIA DE EQUIPAMENTOS ***/
								salvarParteDiariaEquipamentos(itemApropriacao,exmdRW);
		
								/*** SERVICOS / MAO-DE-OBRA / PARALISACAO EQUIPE / PARALISACAO MAO-DE-OBRA ***/
								if(apropriacoesTemServicos(itemApropriacao)) 
								{
									salvarServicos(itemApropriacao,exmdRW);	
									
									salvarMaoDeObra(itemApropriacao,exmdRW);
									
									salvarParalisacaoEquipe(itemApropriacao,exmdRW);
									
									salvarParalisacaoMaoDeObra(itemApropriacao,exmdRW);
								}
							}
						}
					}
	
					if(existemRAEs(exmd))  //SALVAR DADOS DE ABASTECIMENTO
					{	
						for(RaeVO itemRAE : exmd.getRaes()) 
						{	
							for(AbastecimentoVO itemAbastecimento : itemRAE.getAbastecimentos()) 
							{
								salvarRAE(itemRAE,itemAbastecimento,exmdRW);
								
								salvarAbastecimento(itemRAE,itemAbastecimento,exmdRW);
								
								salvarLubrificacaoDetalhes(itemRAE,itemAbastecimento,exmdRW);
							}
						}
					}
			
					salvarSaldoPostos(exmd.getSaldoPosto(),exmdRW);
					
					if(existemManutencoes(exmd)) 
					{
						salvarManutencoes(exmd.getManutencaoEquipamentos(),exmdRW);
					}
				
					exmdRW.marcarHoraTermino();
					dbCommit();
					exmdRW.setHouveErro(false);
					relatorio.addExmdRWProcessado(exmdRW);
					
				}
				catch (Exception e) 
				{
					e.printStackTrace();
					errosMobTabs++;
					
					exmdRW = new ExportMobileDateReportWrapper(arquivo);
					exmdRW.marcarDataProcessado();
					exmdRW.setHouveErro(true);
					exmdRW.marcarDataProcessado();
					
					try
					{
						exmdRW.setMsgErroSistema(e.getMessage().split(":")[1]+"\n\n"+"("+e.getClass().getName()+")");
					}
					catch (ArrayIndexOutOfBoundsException e1)
					{
						exmdRW.setMsgErroSistema(e.getMessage()+"\n\n"+"("+e.getClass().getName()+")");
					}
					dbRollback();
					relatorio.addExmdRWProcessado(exmdRW);
				} 		
			}

			/*Tranferindo dados das tabelas mobile para as tabelas Import*/
			
			if(errosMobTabs == 0) 
			{
				try 
				{
					enviarDadosParaTabelasImport();
					
					if(errosImpTabs == 0)
					{
						dbCommit();
						relatorio.setErrosImpTabs(false);
					}
					else
					{
						dbRollback();
						relatorio.setErrosImpTabs(true);
					}
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
					response = Response.status(Status.INTERNAL_SERVER_ERROR).entity(new ServerResponse("Dados cadastrados nas tabelas Mobile mas houve erro ao salvar nas tabelas Import.")).build();
					relatorio.setErrosImpTabs(true);
					dbRollback();
				}
			}
			//*************************************************************
			response = Response.status(Status.OK).entity(new ServerResponse("O relat\u00f3rio de processamento ser\u00e1 enviado para seu e-mail. Por favor aguarde.")).build();
		}
		finally 
		{
			fechaConexaoBD();
			
			renomearArquivoProcessado();

			try 
			{
				enviarEmail();
			} 
			catch (EmailException e) 
			{
				e.printStackTrace();
				response = Response.status(Status.INTERNAL_SERVER_ERROR).entity(new ServerResponse("Dados cadastrados nas tabelas Mobile e Import mas houve um erro ao enviar o relat\u00f3rio de processamento por e-mail. Por favor entre em contato com equipe de suporte.")).build();
			}
		}
		return response;
	}
	
	
	/*Envia email com o relatório do processamento em formato HTML, mesmo que houver erro o relatório é 
	 * enviado e as informações de processamento são apresentadas ao usuário informado no parâmetro "login"
	 * 
	 * */
	private void enviarEmail() throws EmailException 
	{
		if(emailDestinatario.contains("@")) new Email(emailDestinatario, relatorio.obterRelatorioHTML()).enviar();
	}
	
	
	/*Verifica se o login recebido na requisição não existe 
	 * */
	private boolean usuarioNAOautentico(String login) throws SQLException 
	{
		return !(new UsuarioDAO(conn).compararLoginCadastrado(login).equals(login) ? true : false);
	}
	
	/* Ao terminar o processameto do arquivo este método é invocado para adicionar 
	 * um texto no início do arquivo. Se o arquivo for processado adicionar SUCESSO
	 * se houve erro durante o processamento para as tabelas "mobile" adicionar
	 * FALHA_MOB_TABS se houve erro durante o processamento para as tabelas "import"
	 * adicionar FALHA_MOB_TABS_
	 * */
	private void renomearArquivoProcessado() 
	{
		String strPreppend = "";
		
		for(ExportMobileDateReportWrapper exmdRWinst : relatorio.getExmdRWProcessado())
		{
			if(exmdRWinst.isHouveErro() == false)
			{
				if(relatorio.isErrosImpTabs() == false)
				{
					strPreppend = "SUCESSO_";
				}
				else
				{
					strPreppend = "FALHA_IMP_TABS_";
				}
			}
			else
			{
				strPreppend = "FALHA_MOB_TABS_";
			}
			
			try 
			{
				FileUtil.adicionarTextoNomeArquivo(strPreppend,exmdRWinst.getNmArquivo());
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
				dbRollback();
			}			
		}
	}
	
	
	/*Inicializa variáveis globais mais usadas durante todo o ciclo de processamento
	 * */
	private void init(ExportMobileDate exmd) throws SQLException 
	{
		ccObra = String.valueOf(exmd.getConfiguracoes().getIdObra());
		idDispositivo = exmd.getConfiguracoes().getDispositivo();
		idUsuario = String.valueOf(exmd.getConfiguracoes().getIdUsuario());
	}
	
	
	private boolean existemManutencoes(ExportMobileDate exmd) 
	{
		return exmd.getManutencaoEquipamentos() != null && exmd.getManutencaoEquipamentos().size() > 0; 
	}
	
	private boolean existemApropriacoes(ExportMobileDate exmd) 
	{
		return exmd.getApropriacoes() != null && exmd.getApropriacoes().size() > 0;
	}
	
	private boolean existemRAEs(ExportMobileDate exmd)
	{
		return exmd.getRaes() != null && exmd.getRaes().size() > 0;
	}
	
	private boolean apropriacoesTemServicos(ApropriacaoVO itemApropriacao)
	{
		return "SRV".equals(itemApropriacao.getTipoApropriacao());
	}
	
	private boolean possoSalvarApropriacoes(ApropriacaoPorDataDispositivo apropPorDt) 
	{
		return (apropPorDt.getTotalMov() == 0 || apropPorDt.getTotalEqp() == 0 || 
				apropPorDt.getTotalSrv() == 0 || apropPorDt.getTotalMaoObra() == 0 || 
				apropPorDt.getTotalParalisacaoEquipe() == 0 || apropPorDt.getTotalParalisacaoMaoObra() == 0);
	}
	
	
	private ApropriacaoPorDataDispositivo verificarApropriacaoPorDataDispositivo(ApropriacaoVO itemApropriacao) throws SQLException 
	{
		return exmdDAO.getApropriacaoPorDataDispositivo(itemApropriacao, ccObra, idDispositivo);
	}
	
	
	private void salvarApropriacoes(ApropriacaoVO itemApropriacao, ExportMobileDateReportWrapper exmdRWInstance) throws SQLException 
	{
		int inseridos = exmdDAO.insereApropriacao(new ApropriacaoParams(itemApropriacao), ccObra, idDispositivo, idUsuario);
		exmdRWInstance.addQtdInseridoApropriacoes(inseridos);
	}
	
	private void salvarMovimentacoes(ApropriacaoVO itemApropriacao, ExportMobileDateReportWrapper exmdRWInstance) throws SQLException 
	{
		if(!"MOV".equals(itemApropriacao.getTipoApropriacao())) return;
		
		if(itemApropriacao.getMovimentacoes() == null) return;
		
		int inseridos = 0;
		
		for(ApropriacaoMovimentacaoVO itemMovimentacao : itemApropriacao.getMovimentacoes()) 
		{
			inseridos+= exmdDAO.insereMovimentacao(new MovimentacaoParams(itemMovimentacao, itemApropriacao), ccObra, idDispositivo);
			
			if(itemMovimentacao.getViagens() == null) continue;
			
			//SALVANDO AS VIAGENS
			for(ViagemVO itemViagem : itemMovimentacao.getViagens())
			{
				salvarMovimentacaoViagens(itemApropriacao,itemMovimentacao,itemViagem,exmdRWInstance);
			}
		}
		exmdRWInstance.addQtdInseridoMovimentacao(inseridos);
	}
		
	private void salvarMovimentacaoViagens(ApropriacaoVO itemApropriacao, ApropriacaoMovimentacaoVO itemMovimentacao, ViagemVO itemViagem,ExportMobileDateReportWrapper exmdRWInstance) throws SQLException 
	{
		int inseridos = exmdDAO.insereMovimentacaoViagens(ccObra, idDispositivo, idUsuario, new MovimentacaoViagemParams(itemApropriacao, itemMovimentacao, itemViagem));
		
		exmdRWInstance.addQtdInseridoMovimentacaoViagens(inseridos);
	}
		
	private void salvarParteDiariaEquipamentos(ApropriacaoVO itemApropriacao,ExportMobileDateReportWrapper exmdRWInstance) throws SQLException 
	{
		if(!"EQP".equals(itemApropriacao.getTipoApropriacao())) return;
		
		if(itemApropriacao.getPartesDiarias() == null) return;
		
		int inseridos = 0;
		
		for(ApropriacaoEquipamentoVO itemEqpParteDiaria : itemApropriacao.getPartesDiarias()) 
		{
			inseridos+=exmdDAO.insereParteDiariaEquipamentos(ccObra, idDispositivo, new ParteDiariaEquipamentoParams(itemEqpParteDiaria,itemApropriacao));
			
			for(EventoVO itemEvento : itemEqpParteDiaria.getEventos())
			{
				salvarParteDiariaEquipamentosEventos(itemApropriacao,itemEqpParteDiaria,itemEvento,exmdRWInstance);
			}
		}
		
		exmdRWInstance.addQtdInseridoParteDiariaEquipamentos(inseridos);
	}
		
	private void salvarParteDiariaEquipamentosEventos(ApropriacaoVO itemApropriacao, ApropriacaoEquipamentoVO itemEqpParteDiaria, EventoVO itemEvento,ExportMobileDateReportWrapper exmdRWInstance) throws SQLException 
	{
		int inseridos = exmdDAO.insereParteDiariaEquipamentosEventos(ccObra, idDispositivo, idUsuario, new ParteDiariaEqpmEventoParams(itemApropriacao,itemEqpParteDiaria,itemEvento));
		
		exmdRWInstance.addQtdInseridoEquipamentosEventos(inseridos);
	}
	
	private void salvarServicos(ApropriacaoVO itemApropriacao,ExportMobileDateReportWrapper exmdRWInstance) throws SQLException 
	{
		if(itemApropriacao.getServicos() == null) return;
		
		int inseridos = 0;
		
		for(ApropriacaoServicoVO itemApropServico : itemApropriacao.getServicos()) 
		{
			inseridos+= exmdDAO.insereServico(ccObra, idDispositivo, idUsuario, new ServicoParams(itemApropriacao,itemApropServico));
		}
		
		exmdRWInstance.addQtdInseridoServico(inseridos);
	}

	private void salvarMaoDeObra(ApropriacaoVO itemApropriacao,ExportMobileDateReportWrapper exmdRWInstance) throws SQLException 
	{
		if(itemApropriacao.getMaosObras() == null) return;
		
		int inseridos = 0;
		
		for(ApropriacaoMaoObraVO itemMaoObra : itemApropriacao.getMaosObras())
		{
			inseridos+=exmdDAO.insereMaoDeObra(ccObra, idDispositivo, idUsuario, new MaoDeObraParams(itemApropriacao,itemMaoObra));
		}
		
		exmdRWInstance.addQtdInseridoMaoDeObra(inseridos);
	}
	
	private void salvarParalisacaoEquipe(ApropriacaoVO itemApropriacao,ExportMobileDateReportWrapper exmdRWInstance) throws SQLException 
	{
		if(itemApropriacao.getParalisacoesEquipe() == null) return;
		
		int inseridos = 0;
		
		for(ParalisacaoEquipeVO itemParaliEquipe : itemApropriacao.getParalisacoesEquipe()) 
		{
			inseridos+=exmdDAO.insereParalisacaoEquipe(ccObra, idDispositivo, idUsuario, new ParalisacaoEquipeParams(itemApropriacao, itemParaliEquipe));
		}
		
		exmdRWInstance.addQtdInseridoParalisacaoEquipe(inseridos);
	}
	
	private void salvarParalisacaoMaoDeObra(ApropriacaoVO itemApropriacao,ExportMobileDateReportWrapper exmdRWInstance) throws SQLException 
	{
		if(itemApropriacao.getParalisacoesMaoObra() == null) return;
		
		int inseridos = 0;
		
		for(ParalisacaoMaoObraVO itemParalisacao : itemApropriacao.getParalisacoesMaoObra()) 
		{
			inseridos+=exmdDAO.insereParalisacaoMaoDeObra(ccObra, idDispositivo, idUsuario, new ParalisacaoMaoDeObraParams(itemApropriacao, itemParalisacao));
		}
		
		exmdRWInstance.addQtdInseridoParalisacaoMaoDeObra(inseridos);
	}
	
	private void salvarRAE(RaeVO itemRAE, AbastecimentoVO itemAbastecimento,ExportMobileDateReportWrapper exmdRWInstance) throws SQLException 
	{
		int inseridos = exmdDAO.insereRAE(idDispositivo, new RAEParams(itemRAE, itemAbastecimento));

		exmdRWInstance.addQtdInseridoRAE(inseridos);
	}
	
	private void salvarAbastecimento(RaeVO itemRAE, AbastecimentoVO itemAbastecimento,ExportMobileDateReportWrapper exmdRWInstance) throws SQLException 
	{
		int inseridos = exmdDAO.insereAbastecimento(idDispositivo, new AbastecimentoParams(itemRAE,itemAbastecimento));

		exmdRWInstance.addQtdInseridoAbastecimento(inseridos);
	}
	
	private void salvarLubrificacaoDetalhes(RaeVO itemRAE, AbastecimentoVO itemAbastecimento,ExportMobileDateReportWrapper exmdRWInstance) throws SQLException 
	{
		if(itemAbastecimento.getLubrificacaoDetalhes() == null) return;
		
		int inseridos = 0;
		
		for(LubrificacaoDetalheVO itemLubriDetalhe : itemAbastecimento.getLubrificacaoDetalhes())
		{
			inseridos+=exmdDAO.insereLubrificacaoDetalhe(idDispositivo, new LubrificacaoDetalheParams(itemRAE,itemAbastecimento,itemLubriDetalhe));
		}
		
		exmdRWInstance.addQtdInseridoLubrificacaoDetalhe(inseridos);
	}

	
	//-------------------------------------------------------------------------------------------------------------------------
	private void salvarSaldoPostos(SaldoPostoVO saldoPosto,ExportMobileDateReportWrapper exmdRWInstance) throws SQLException 
	{
		System.out.println("Saldo postos: "+saldoPosto.getSaldos().size());
		
		if(saldoPosto == null || saldoPosto.getSaldos() == null) return;
		
		int inseridos = 0;
		
		for(AbastecimentoPostoVO itemSaldo : saldoPosto.getSaldos())
		{
			new SaldoPostoParams(itemSaldo).toString();
			
			inseridos += exmdDAO.insereSaldoPosto(ccObra, idDispositivo, idUsuario, new SaldoPostoParams(itemSaldo));
		}
		
		exmdRWInstance.addQtdInseridoSaldoPosto(inseridos);
	}
	//-------------------------------------------------------------------------------------------------------------------------
	
	
	
	
	private void salvarManutencoes(List<ManutencaoEquipamentoVO> listaEquipamentos,ExportMobileDateReportWrapper exmdRWInstance) throws SQLException 
	{
		if(listaEquipamentos == null || listaEquipamentos.size() == 0) return;
		
		int inseridos = 0;
		
		for(ManutencaoEquipamentoVO itemEquipamento : listaEquipamentos) 
		{
			inseridos+= exmdDAO.insereManutencoesEquipamento(ccObra, idDispositivo, idUsuario, new ManutencaoEquipamentoParams(itemEquipamento));
		
			salvarServicosManutencoes(itemEquipamento.getServicos(),exmdRWInstance);
		}
		
		exmdRWInstance.addQtdInseridoManutencoes(inseridos);
	}
	
	private void salvarServicosManutencoes(List<ManutencaoEquipamentoServicosVO> listaServicos,ExportMobileDateReportWrapper exmdRWInstance) throws SQLException 
	{
		if(listaServicos == null || listaServicos.size() == 0) return;
		
		int inseridos = 0;
		
		for(ManutencaoEquipamentoServicosVO itemServico : listaServicos)
		{
			inseridos+= exmdDAO.insereManutencoesServicosRealizados(ccObra, idDispositivo, idUsuario, new ManutencaoServicosParams(itemServico));
		}

		exmdRWInstance.addQtdInseridoManutencoesServicos(inseridos);
	}
	


	private void enviarDadosParaTabelasImport()  
	{
		int erros_MovPartDiaria = transfereMovPartDiaria();
		int erros_ServMaoObraParaEqp = transfereServMaoObraParalEqp();
		int erros_AbastLubrif = transfereAbastecimentoLubri();
	
		if(erros_MovPartDiaria > 0 || erros_ServMaoObraParaEqp > 0 || erros_AbastLubrif > 0) {
			errosImpTabs = erros_MovPartDiaria + erros_ServMaoObraParaEqp + erros_AbastLubrif;
			exmdRW.setMsgErroSistema("Ocorreram erros ao transferir os dados para as tabelas Import. Favor entrar em contato com a equipe de suporte.");
		}
		else
		{
			errosImpTabs = 0;
		}
		
		relatorio.setErrosImport_MovimentacaoParteDiariaEqpm(erros_MovPartDiaria);
		relatorio.setErrosImport_ServMaoObraParalisEquipe(erros_ServMaoObraParaEqp);
		relatorio.setErrosImport_AbastecimentoLubrificacao(erros_AbastLubrif);
	}

	//MOVIMENTACAO E PARTE DIARIA DE EQUIPAMENTO OK
	public int transfereMovPartDiaria() 
	{
		int erro = 0;
		
		List<ApropMovimEquipParams> listaAprop = new ArrayList<ApropMovimEquipParams>();
		
		try 
		{
			listaAprop = exmdDAO.obterApropriacoesEmMobileTabs(ccObra, idUsuarioLOGADO);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			erro = 1;
		}
		
		for(ApropMovimEquipParams itemParam : listaAprop) 
		{
			erro+= exmdDAO.transferirDados_Apropriacoes_Mov_Eqp(ccObra, "W", idUsuarioLOGADO, itemParam);
		}
		
		return erro;
	}
	
	//SERVICO / MAO DE OBRA / PARALISAÇÃO
	public int transfereServMaoObraParalEqp() 
	{
		int erro = 0;
		erro+= exmdDAO.transferirDados_Servico_MaoDeObra_Paralisacao(ccObra,"W",idUsuarioLOGADO);
		return erro;
	}	
	
	//ABASTECIMENTO E LUBRIFICACAO
	public int transfereAbastecimentoLubri() 
	{
		int erro = 0;
		erro+= exmdDAO.transferirDados_Abastecimento_Lubrificacao(ccObra, "W", idUsuarioLOGADO);
		return erro;
	}
	
	//http://192.168.1.64:8080/samcentral/ws/processa/mostrarArquivos/303599/2015/07/08
	@GET
	@Path("/mostrarArquivos/{grupoUsuario}/{ccObra}/{yyyy}/{mm}/{dd}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarArquivos(
			 @DefaultValue("_") @PathParam("grupoUsuario")String grupoUsuario
			,@DefaultValue("0") @PathParam("ccObra")String ccObra
			,@DefaultValue("0") @PathParam("yyyy")Integer ano
			,@DefaultValue("0") @PathParam("mm")Integer mes
			,@DefaultValue("0") @PathParam("dd")Integer dia) 
	{
		Response response = null;

		if(grupoUsuario.equals("_"))
		{
			return Response.status(Status.NOT_FOUND).entity(new ServerResponse("Grupo de usuário é inválido")).build();
		}
		
		
		if(ccObra.equals("0")) 
		{
			return Response.status(Status.NOT_FOUND).entity(new ServerResponse("Obra inv\u00e1lida", ServerResponse.E20)).build();
		}		
		
		if(ano < 2015)
		{
			return Response.status(Status.NOT_FOUND).entity(new ServerResponse("Ano informado não \u00e9 aceito. Informe um ano superior a 2014.", ServerResponse.E20)).build();
		}
		if(mes < 1 || mes > 12) 
		{
			return Response.status(Status.NOT_FOUND).entity(new ServerResponse("Mês informado \u00e9 inv\u00e1lido.", ServerResponse.E20)).build();
		}
		if(dia < 1 || dia > 31) 
		{
			return Response.status(Status.NOT_FOUND).entity(new ServerResponse("Dia informado \u00e9 inv\u00e1lido.", ServerResponse.E20)).build();
		}
		
		try 
		{
			List<String> arquivos = FileUtil.listarArquivosParaProcessar(grupoUsuario,ccObra,dia,mes,ano);
			
			if(arquivos.size() == 0) 
			{
				response = Response.status(Status.OK).entity(new ServerResponse("Nenhum arquivo para processar em "+dia+"/"+mes+"/"+ano)).build();
			}
			else 
			{
				response = Response.status(Status.OK).entity(arquivos).build();
			}
			
		} catch(NullPointerException e) 
		{
			e.printStackTrace();
			response = Response.status(Status.NOT_FOUND).entity(new ServerResponse("Não foi poss\u00edvel encontrar o diret\u00f3rio do arquivo. Verifique a data Informada.", ServerResponse.E20)).build();
		}
		return response;
	}	
}
