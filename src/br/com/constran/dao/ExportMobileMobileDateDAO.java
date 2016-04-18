/*
		{
		 }   
		\=/>
	MOISES SANTANA moises.santana@contran.com.br - 09/06/2015
*/

package br.com.constran.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.constran.mobile.persistence.vo.aprop.ApropriacaoVO;
import br.com.constran.model.ApropriacaoPorDataDispositivo;
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
import br.com.constran.util.dao.AbstractDAO;

public class ExportMobileMobileDateDAO extends AbstractDAO{

	public ExportMobileMobileDateDAO(Connection conn) {
		super(conn);
	}
	
	
	public ApropriacaoPorDataDispositivo getApropriacaoPorDataDispositivo(ApropriacaoVO itemApropriacao, String ccObra, String nmDispositivo) throws SQLException {
		
		//strSQL = "EXEC totalApropMobileObraTablet ccObra,strDataApontamento,dispositivo"
		StringBuilder exec = new StringBuilder(Procedures.PROC_TOTAL_APROP_MOBILE_OBRA_TABLET).append("?,?,?");
		ApropriacaoPorDataDispositivo aprop = new ApropriacaoPorDataDispositivo();
		
		String []data = itemApropriacao.getDataHoraApontamento().split(" ")[0].split("/"); 
		
		prepStmt = conn.prepareStatement(exec.toString());
		prepStmt.setString(1,ccObra);
		prepStmt.setString(2,data[2]+"-"+data[1]+"-"+data[0]);
        //prepStmt.setString(3,itemApropriacao.getDataHoraApontamento().split(" ")[1];);
        prepStmt.setString(3,nmDispositivo);
        rs = prepStmt.executeQuery();		

		if(rs.next()){
			aprop = new ApropriacaoPorDataDispositivo();
			aprop.setTotalMov(rs.getInt("totalMov"));
			aprop.setTotalEqp(rs.getInt("totalEqp"));
			aprop.setTotalSrv(rs.getInt("totalSrv"));
			aprop.setTotalMaoObra(rs.getInt("totalMaoObra"));
			aprop.setTotalParalisacaoEquipe(rs.getInt("totalParalisacaoEquipe"));
			aprop.setTotalParalisacaoMaoObra(rs.getInt("totalParalisacaoMaoObra"));
		}

		liberarRecursosBD();
		return aprop;
	}
	
	
	public int insereApropriacao(ApropriacaoParams params, String ccObra, String nmDispositivo, String idUsuario) throws SQLException {

		/*EXEC insertApropriacaoMobile 1:ccObra,
									 * 2:dataApontamento,
									 * 3:frenteObra,
									 * 4:idAtividade,
									 * 5:dispositivo,
									 * 6:idApropriacao,
									 * 7:tipoApropriacao,
									 * 8:observacoes,
									 * 9:idUsuario
		 * */		
		StringBuilder exec = new StringBuilder(Procedures.PROC_INSERT_APROP_MOBILE).append("?,?,?,?,?,?,?,?,?");
		
		//StringBuilder exec = new StringBuilder("insert into insereApropriacao values").append("(?,?,?,?,?,?,?,?,?)");
		
		prepStmt = conn.prepareStatement(exec.toString());
		prepStmt.setString(1,ccObra);
		prepStmt.setString(2,params.getDataApontamento());
        prepStmt.setString(3,params.getIdFrenteObra());
        prepStmt.setString(4,params.getIdAtividade());
        prepStmt.setString(5,nmDispositivo);
        prepStmt.setString(6,params.getIdApropriacao());
        prepStmt.setString(7,params.getTipoApropriacao());
        prepStmt.setString(8,params.getObservacao());
        prepStmt.setString(9,idUsuario);
        
       
        int inseridos = prepStmt.executeUpdate();
        liberarRecursosBD();
        return inseridos;
	}
	
	public int insereMovimentacao(MovimentacaoParams params, String ccObra, String idDispositivo) throws SQLException {
		
		/*EXEC insertApropMovimentacaoMobile 1:ccObra,
											 * 2:apropriacao,
											 * 3:strDataApontamento,
											 * 4:dispositivo,
											 * 5:equipamento,
											 * 6:horaInicio,
											 * 7:horaTermino,
											 * 8:estacaOrigemInicial,
											 * 9:estacaOrigemFinal,
											 * 10:estacaDestinoInicial,
											 * 11:estacaDestinoFinal,
											 * 12:origem,
											 * 13:destino,
											 * 14:qtdeViagens,
											 * 15:percentualCarga,
											 * 16:material"
	    */
		StringBuilder exec = new StringBuilder(Procedures.PROC_INSERT_APROP_MOVIMENTACAO_MOBILE).append("?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?");
		
		//StringBuilder exec = new StringBuilder("insert into insereApropriacaoMovimentacao values").append("(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		
		prepStmt = conn.prepareStatement(exec.toString());
		prepStmt.setString(1, ccObra);
		prepStmt.setString(2, params.getIdApropriacao());
		prepStmt.setString(3, params.getDataApontamento());
		prepStmt.setString(4, idDispositivo);
		prepStmt.setString(5, params.getIdEquipamento());
		prepStmt.setString(6, params.getHoraInicio());
		prepStmt.setString(7, params.getHoraTermino());
		prepStmt.setString(8, params.getEstacaOrigemInicial());
		prepStmt.setString(9, params.getEstacaOrigemFinal());
		prepStmt.setString(10, params.getEstacaDestinoInicial());
		prepStmt.setString(11, params.getEstacaDestinoFinal());
		prepStmt.setString(12, params.getIdOrigem());
		prepStmt.setString(13, params.getIdDestino());
		prepStmt.setString(14, params.getQtdViagem());
		prepStmt.setString(15, params.getPrcCarga());
		prepStmt.setString(16, params.getIdMatMovimentacao());
		
		int inseridos = prepStmt.executeUpdate();
		liberarRecursosBD();
		return inseridos;
	}
	
	public int insereMovimentacaoViagens(String ccObra, String idDispositivo, String idUsuario, MovimentacaoViagemParams params) throws SQLException {
		/*
		EXEC insertApropViagemMobile    1:apropriacao,ok
										2:strDataApontamento,ok
										3:dispositivo,ok
										4:equipamento,ok
										5:horaInicio,ok
										6:horaViagem,
										7:ccObra,
										8:equipamentoCarga,
										9:material,
										10:estacaInicial,
										11:estacaFinal,
										12:numeroVale,
										13:strNroFormulario,
										14:strNroQrCode,
										15:strCodSeguranca,
										16:percentualCarga,
										17:viraVira,
										18:strDataHoraCadastro,
										19:strDataHoraAtualizacao,
										20:origemDestino,
										21:apropriar,
										22:observacoes,
										23:peso,
										24:usuario"
		 * */
		StringBuilder exec = new StringBuilder(Procedures.PROC_INSERT_APROP_VIAGEM_MOBILE).append("?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?");
		
		//StringBuilder exec = new StringBuilder("insert into insereApropriacaoMovimentacaoViagens values").append("(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		
		prepStmt = conn.prepareStatement(exec.toString());
		prepStmt.setString(1, params.getIdApropriacao());
		prepStmt.setString(2, params.getDataApontamento());
		prepStmt.setString(3, idDispositivo);
		prepStmt.setString(4, params.getIdEquipamento());
		prepStmt.setString(5, params.getHoraInicio());
		prepStmt.setString(6, params.getHoraViagem());
		prepStmt.setString(7, ccObra);
		prepStmt.setString(8, params.getIdEquipamentoCarga());
		prepStmt.setString(9, params.getIdMaterialViagem());
		prepStmt.setString(10, params.getEstacaInicialViagem());
		prepStmt.setString(11, params.getEstacaFinalViagem());
		prepStmt.setString(12, params.getNumeroVale());
		prepStmt.setString(13, params.getNumeroFormulario());
		prepStmt.setString(14, params.getNumeroQRcode());
		prepStmt.setString(15, params.getCodSeguranca());
		prepStmt.setString(16, params.getPrcCargaViagem());
		prepStmt.setString(17, params.getViraVira());
		prepStmt.setString(18, params.getDataHoraCadastro());
		prepStmt.setString(19, params.getDataHoraAtualoizado());
		prepStmt.setString(20, params.getOrigemDestino()); 
		prepStmt.setString(21, params.getApropriar());
		prepStmt.setString(22, params.getObsViagem());
		prepStmt.setString(23, params.getPeso());
		prepStmt.setString(24, idUsuario);
		
		int inseridos = prepStmt.executeUpdate();
		liberarRecursosBD();
		return inseridos;
	}
	
	
	public int insereParteDiariaEquipamentos(String ccObra, String idDispositivo, ParteDiariaEquipamentoParams params) throws SQLException {
		
		/*EXEC insertParteDiariaEquipMobile 1:apropriacao,
											 * 2:strDataApontamento,
											 * 3:dispositivo,
											 * 4:ccObra,
											 * 5:equipamento,
											 * 6:horimetroInicial,
											 * 7:horimetroFinal,
											 * 8:producao,
											 * 9:operador1,
											 * 10:operador2,
											 * 11:observacoes
		 * */		
		StringBuilder exec = new StringBuilder(Procedures.PROC_INSERT_PARTE_DIARIA_EQUIP_MOBILE).append("?,?,?,?,?,?,?,?,?,?,?");
		
		//StringBuilder exec = new StringBuilder("insert into insereApropriacaoParteDiariaEquipamentos values").append("(?,?,?,?,?,?,?,?,?,?,?)");
		
		prepStmt = conn.prepareStatement(exec.toString());
		prepStmt.setString(1, params.getIdApropriacao());
		prepStmt.setString(2, params.getDataApontamento());
		prepStmt.setString(3, idDispositivo);
		prepStmt.setString(4, ccObra);
		prepStmt.setString(5, params.getIdEquipamento());
		prepStmt.setString(6, params.getHorimetroInicial());
		prepStmt.setString(7, params.getHorimetroFinal());
		prepStmt.setString(8, params.getPercProducao());
		prepStmt.setString(9, params.getOperador1());
		prepStmt.setString(10, params.getOperador2());
		prepStmt.setString(11, params.getObservacoes());
		
		int inseridos = prepStmt.executeUpdate();
		liberarRecursosBD();
		return inseridos;
	}
	
	
	public int insereParteDiariaEquipamentosEventos(String ccObra, String idDispositivo, String idUsuario, ParteDiariaEqpmEventoParams params) throws SQLException {
		/*
		EXEC insertParteDiariaEquipEventosMobile 1:apropriacao,
												2:strDataApontamento,
												3:dispositivo,
												4:ccObra,
												5:equipamento,
												6:horaInicio,
												7:horaTermino,
												8:estaca,
												9:strIdServico,
												10:strIdParalisacao,
												11:strIdComponente,
												12:observacoes,
												13:apropriar,
												14:usuario  
		 * */
		StringBuilder exec = new StringBuilder(Procedures.PROC_INSERT_PARTE_DIARIA_EQUIP_EVENTOS_MOBILE).append("?,?,?,?,?,?,?,?,?,?,?,?,?,?");
		
		//StringBuilder exec = new StringBuilder("insert into values insereApropriacaoParteDiariaEquipamentosEventos").append("(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		
		prepStmt = conn.prepareStatement(exec.toString());
		prepStmt.setString(1, params.getIdApropriacao());
		prepStmt.setString(2, params.getDataApontamento());
		prepStmt.setString(3, idDispositivo);
		prepStmt.setString(4, ccObra);
		prepStmt.setString(5, params.getIdEquipamento());
		prepStmt.setString(6, params.getHoraInicio());
		prepStmt.setString(7, params.getHoraTermino());
		prepStmt.setString(8, params.getEstaca());
		prepStmt.setString(9, params.getIdServico());
		prepStmt.setString(10, params.getIdParalisacao());
		prepStmt.setString(11, params.getIdComponente());
		prepStmt.setString(12, params.getObservacaoEvento());
		prepStmt.setString(13, params.getApropriar());
		prepStmt.setString(14, idUsuario);
		
		int inseridos = prepStmt.executeUpdate();
		liberarRecursosBD();
		return inseridos;
		
	}
	
	public int insereServico(String ccObra, String idDispositivo, String idUsuario, ServicoParams params) throws SQLException {
		/*EXEC insertApropriacaoServicoMobile 1:ccObra,
											 * 2:apropriacao,
											 * 3:strDataApontamento,
											 * 4:dispositivo,
											 * 5:equipe,
											 * 6:servico,
											 * 7:strQtdeProduzida,
											 * 8:strObservacoes,
											 * 9:idUsuario,
											 * 10:horaInicioServico,
											 * 11:strHoraTerminoServico
		 * */
		StringBuilder exec = new StringBuilder(Procedures.PROC_INSERT_APROPRIACAO_SERVICO_MOBILE).append("?,?,?,?,?,?,?,?,?,?,?");
		
		//StringBuilder exec = new StringBuilder("insert into insereApropriacaoServico values").append("(?,?,?,?,?,?,?,?,?,?,?)");
		
		prepStmt = conn.prepareStatement(exec.toString());
		prepStmt.setString(1, ccObra);
		prepStmt.setString(2, params.getIdApropriacao());
		prepStmt.setString(3, params.getDataApontamento());
		prepStmt.setString(4, idDispositivo);
		prepStmt.setString(5, params.getIdEquipe());
		prepStmt.setString(6, params.getIdServico());
		prepStmt.setString(7, params.getQtdProduzida());
		prepStmt.setString(8, params.getObservacao());
		prepStmt.setString(9, idUsuario);
		prepStmt.setString(10, params.getHoraInicioServico());
		prepStmt.setString(11, params.getHoraTerminoServico());
		
		int inseridos = prepStmt.executeUpdate();
		liberarRecursosBD();
		return inseridos;
	}
	
	
	public int insereMaoDeObra(String ccObra, String idDispositivo, String idUsuario, MaoDeObraParams params) throws SQLException {
		/*
		"EXEC insertApropriacoesMaoObraMobile  1:ccObra,
												2:apropriacao,
												3:strDataApontamento,
												4:dispositivo,
												5:equipe,
												6:pessoa,
												7:servico,
												8:horaInicio,
												9:strHoraTermino,
												10:strObservacoes,
												11:idUsuario
		 * */
		StringBuilder exec = new StringBuilder(Procedures.PROC_INSERT_APROPRIACAO_MAODEOBRA_MOBILE).append("?,?,?,?,?,?,?,?,?,?,?");
		
		//StringBuilder exec = new StringBuilder("insert into insereApropriacaoMaoDeObra values").append("(?,?,?,?,?,?,?,?,?,?,?)");
		
		prepStmt = conn.prepareStatement(exec.toString());
		prepStmt.setString(1, ccObra);
		prepStmt.setString(2, params.getIdApropriacao());
		prepStmt.setString(3, params.getDataApontamento());
		prepStmt.setString(4, idDispositivo);
		prepStmt.setString(5, params.getIdEquipe());
		prepStmt.setString(6, params.getIdPessoa());
		prepStmt.setString(7, params.getIdServico());
		prepStmt.setString(8, params.getHoraInicio());
		prepStmt.setString(9, params.getHoraTermino());
		prepStmt.setString(10, params.getObservacao());
		prepStmt.setString(11, idUsuario);	

		
		int inseridos = prepStmt.executeUpdate();
		liberarRecursosBD();
		return inseridos;
		
	}
	
	
	public int insereParalisacaoEquipe(String ccObra, String idDispositivo, String idUsuario, ParalisacaoEquipeParams params) throws SQLException {

		/*
		EXEC insertParalisacoesEquipeMobile 1:ccObra,
											2:apropriacao,
											3:strDataApontamento,
											4:dispositivo,
											5:equipe,
											6:paralisacao,
											7:horaInicio,
											8:strHoraTermino,
											9:strObservacoes,
											10:idUsuario,
											11:strIdServico
		 * */
		StringBuilder exec = new StringBuilder(Procedures.PROC_INSERT_PARALISACOES_EQUIPE_MOBILE).append("?,?,?,?,?,?,?,?,?,?,?");
		
		//StringBuilder exec = new StringBuilder("insert into insereApropriacaoParalisacaoEquipe values").append("(?,?,?,?,?,?,?,?,?,?,?)");
		
		prepStmt = conn.prepareStatement(exec.toString());
		prepStmt.setString(1, ccObra);
		prepStmt.setString(2, params.getIdApropriacao());
		prepStmt.setString(3, params.getDataApontamento());
		prepStmt.setString(4, idDispositivo);
		prepStmt.setString(5, params.getIdEquipe());
		prepStmt.setString(6, params.getCodParalisacao());
		prepStmt.setString(7, params.getHoraInicio());
		prepStmt.setString(8, params.getHoraTermino());
		prepStmt.setString(9, params.getObservacao());
		prepStmt.setString(10, idUsuario);
		prepStmt.setString(11, params.getIdServico());
		
		int inseridos = prepStmt.executeUpdate();
		liberarRecursosBD();
		return inseridos;
	}
	
	
	public int insereParalisacaoMaoDeObra(String ccObra, String idDispositivo, String idUsuario, ParalisacaoMaoDeObraParams params) throws SQLException {
		
		/*
		EXEC insertParalisacoesMaoObraMobile 1:ccObra,
											2:apropriacao,
											3:strDataApontamento,
											4:dispositivo,
											5:pessoa,
											6:strHoraInicio,
											7:strHoraTermino,
											8:paralisacao,
											9:equipe,
											10:strObservacoes,
											11:idUsuario,
											12:strIdServico
		 * */
		StringBuilder exec = new StringBuilder(Procedures.PROC_INSERT_PARALISACOES_MAO_OBRA_MOBILE).append("?,?,?,?,?,?,?,?,?,?,?,?");
		
		//StringBuilder exec = new StringBuilder("insert into insereApropriacaoParalisacaoMaoDeObra values").append("(?,?,?,?,?,?,?,?,?,?,?,?)");
		
		prepStmt = conn.prepareStatement(exec.toString());
		prepStmt.setString(1, ccObra);
		prepStmt.setString(2, params.getIdApropriacao());
		prepStmt.setString(3, params.getDataApontamento());
		prepStmt.setString(4, idDispositivo);
		prepStmt.setString(5, params.getIdPessoa());
		prepStmt.setString(6, params.getHoraInicio());
		prepStmt.setString(7, params.getHoraTermino());
		prepStmt.setString(8, params.getCodParalisacao());
		prepStmt.setString(9, params.getIdEquipe());
		prepStmt.setString(10, params.getObservacoes());
		prepStmt.setString(11, idUsuario);
		prepStmt.setString(12, params.getIdServico());
		
		
		int inseridos = prepStmt.executeUpdate();
		liberarRecursosBD();
		return inseridos;
	}

	public int insereRAE(String idDispositivo, RAEParams params) throws SQLException {
		/*
		EXEC insertRAEMobile 1:ccObra,
							2:numeroRAE,
							3:strData,
							4:dispositivo,
							5:postoAbastecimento,
							6:abastecedor,
							7:idUsuario,
							8:strTotalizadorInicial,
							9:strTotalizadorFinal
		 * */
		StringBuilder exec = new StringBuilder(Procedures.PROC_INSERT_RAE_MOBILE).append("?,?,?,?,?,?,?,?,?");
		
		//StringBuilder exec = new StringBuilder("insert into insereRAE values").append("(?,?,?,?,?,?,?,?,?)");
		
		prepStmt = conn.prepareStatement(exec.toString());
		prepStmt.setString(1, params.getCcObra());
		prepStmt.setString(2, params.getIdRAE());
		prepStmt.setString(3, params.getData());
		prepStmt.setString(4, idDispositivo);
		prepStmt.setString(5, params.getIdPosto());
		prepStmt.setString(6, params.getIdAbastecedor());
		prepStmt.setString(7, params.getIdUsuario());
		prepStmt.setString(8, params.getTotalizadorInicial());
		prepStmt.setString(9, params.getTotalizadorFinal());
		
		int inseridos = prepStmt.executeUpdate();
		liberarRecursosBD();
		return inseridos;
	}
	
	public int insereAbastecimento(String idDispositivo, AbastecimentoParams params) throws SQLException {
		/*
		EXEC insertAbastecimentosMobile 1:ccObra,
										2:numeroRAE,
										3:strData,
										4:dispositivo,
										5:equipamento,
										6:strData & " " & strHoraInicio,
										7:combustivelLubrificante,
										8:horimetro,
										9:quilometragem,
										10:qtde,
										11:abastecedor,
										12:observacoes,
										13:strData & " " & strHoraTermino,
										14:strOperador,
										15:idUsuario,
										16:strFrenteObra,
										17:strAtividade,
										18:strIdJustificativa,
										19:strObservacaoJustificativa 
		 * */
		StringBuilder exec = new StringBuilder(Procedures.PROC_INSERT_ABASTECIMENTO_MOBILE).append("?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?");
		
		//StringBuilder exec = new StringBuilder("insert into insereAbastecimento values").append("(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
	
		prepStmt = conn.prepareStatement(exec.toString());
		prepStmt.setString(1, params.getCcObra());
		prepStmt.setString(2, params.getIdRAE());
		prepStmt.setString(3, params.getData());
		prepStmt.setString(4, idDispositivo);
		prepStmt.setString(5, params.getIdEquipamento());
		prepStmt.setString(6, params.getData()+" "+params.getHoraInicio());
		prepStmt.setString(7, params.getIdCombustivelLubrificante());
		prepStmt.setString(8, params.getHorimetro());
		prepStmt.setString(9, params.getQuilometragem());
		prepStmt.setString(10, params.getQuantidade());
		prepStmt.setString(11, params.getIdAbastecedor());
		prepStmt.setString(12, params.getObservacoes());
		prepStmt.setString(13, params.getData()+" "+params.getHoraTermino());
		prepStmt.setString(14, params.getIdOperador());
		prepStmt.setString(15, params.getIdUsuario());
		prepStmt.setString(16, params.getIdFrenteObra());
		prepStmt.setString(17, params.getIdAtividade());
		prepStmt.setString(18, params.getIdJustificativa());
		prepStmt.setString(19, params.getObsJustificativa());
		
		int inseridos = prepStmt.executeUpdate();
		liberarRecursosBD();
		return inseridos;
	}
	
	public int insereLubrificacaoDetalhe(String idDispositivo, LubrificacaoDetalheParams params) throws SQLException {
		/*
			EXEC insertLubrificacoesDetalhesMobile 1:ccObra,
													2:numeroRAE,
													3:strData,
													4:dispositivo,
													5:equipamento,
													6:strData & " " & strHoraInicio,
													7:combustivelLubrificante,
													8:compartimento,
													9:qtde,
													10:observacoes,
													11:idUsuario
		 * */
		StringBuilder exec = new StringBuilder(Procedures.PROC_INSERT_LUBRIFICACAO_DETALHE_MOBILE).append("?,?,?,?,?,?,?,?,?,?,?");
		
		//StringBuilder exec = new StringBuilder("insert into insereLubrificacaoDetalhe values").append("(?,?,?,?,?,?,?,?,?,?,?)");
		
		prepStmt = conn.prepareStatement(exec.toString());
		prepStmt.setString(1, params.getCcObra());
		prepStmt.setString(2, params.getIdRAE());
		prepStmt.setString(3, params.getData());
		prepStmt.setString(4, idDispositivo);
		prepStmt.setString(5, params.getIdEquipamento());
		prepStmt.setString(6, params.getData()+" "+params.getHoraInicio());
		prepStmt.setString(7, params.getIdCombustivelLubrificante());
		prepStmt.setString(8, params.getIdCompartimento());
		prepStmt.setString(9, params.getQuantidade());
		prepStmt.setString(10, params.getObservacao());
		prepStmt.setString(11, params.getIdUsuario());
		
		int inseridos = prepStmt.executeUpdate();
		liberarRecursosBD();
		return inseridos;
		
	}
	
	public int insereSaldoPosto(String ccObra, String idDispositivo, String idUsuario, SaldoPostoParams params) throws SQLException {
		
		/*"EXEC insertSaldosPostosMobile 1:ccObra,
		 * 								 2:postoAbastecimento,
		 * 								 3:strData,
		 * 								 4:dispositivo,
		 * 								 5:strDataHora,
		 * 								 6:combustivelLubrificante,
		 * 								 7:strQtde,
		 * 								 8:strPostoOrigem 
		 * */								
		StringBuilder exec = new StringBuilder(Procedures.PROC_INSERT_SALDOS_POSTOS_MOBILE).append("?,?,?,?,?,?,?,?");
		
		//StringBuilder exec = new StringBuilder("insert into insereSaldoPosto values").append("(?,?,?,?,?,?,?,?)");
		
		prepStmt = conn.prepareStatement(exec.toString());
		prepStmt.setString(1, ccObra);
		prepStmt.setString(2, params.getIdPosto());
		prepStmt.setString(3, params.getData());
		prepStmt.setString(4, idDispositivo);
		//prepStmt.setString(5, params.getHora());
		prepStmt.setString(5, params.getDataHora());
		prepStmt.setString(6, params.getIdCombustivelLubrificante());
		prepStmt.setString(7, params.getQuantidade());
		prepStmt.setString(8, params.getIdPostoOrigem());
		
		System.out.println(params.toString());
		
		int inseridos = prepStmt.executeUpdate();
		liberarRecursosBD();
		return inseridos;
	}
	
	public int insereManutencoesEquipamento(String ccObra, String idDispositivo, String idUsuario, ManutencaoEquipamentoParams params) throws SQLException {
		
		//DESATIVADO POR QUE AINDA NAO EXISTE A PROCEDURE PARA RECEBER ESSES DADOS.
		
		/*
		StringBuilder exec = new StringBuilder("insert into insereManutencaoEquipamentos2 values").append("(?,?,?,?,?,?,?,?,?)");
		
		prepStmt = conn.prepareStatement(exec.toString());
		prepStmt.setString(1, ccObra);
		prepStmt.setString(2, params.getData());
		prepStmt.setString(3, params.getIdEquipamento());
		prepStmt.setString(4, params.getDescEquipamento());
		prepStmt.setString(5, params.getHoraInicio());
		prepStmt.setString(6, params.getHoraTermino());
		prepStmt.setString(7, params.getHorimetro());
		prepStmt.setString(8, params.getHodometro());
		prepStmt.setString(9, params.getObservacao());
		
		int inseridos = prepStmt.executeUpdate();
		liberarRecursosBD();
		return inseridos;
		*/
		return 0;
		
	}
	
	public int insereManutencoesServicosRealizados(String ccObra, String idDispositivo, String idUsuario, ManutencaoServicosParams params) throws SQLException {
		
		//DESATIVADO POR QUE AINDA NAO EXISTE A PROCEDURE PARA RECEBER ESSES DADOS.		
		
		/*
		StringBuilder exec = new StringBuilder("insert into insereManutencoesServicosRealizados2 values").append("(?,?,?,?,?,?,?,?,?)");
		
		prepStmt = conn.prepareStatement(exec.toString());
		//prepStmt.setString(1, ccObra);
		prepStmt.setString(1, params.getData());
		prepStmt.setString(2, params.getIdServicoCategoriaEquipamento());
		prepStmt.setString(3, params.getIdEquipamento());
		prepStmt.setString(4, params.getDescricaoEquipamento());
		prepStmt.setString(5, params.getDescricaoServico());
		prepStmt.setString(6, params.getHoraInicio());
		prepStmt.setString(7, params.getHoraTermino());
		prepStmt.setString(8, params.getObservacao());
		prepStmt.setString(9, idDispositivo);
		
		int inseridos = prepStmt.executeUpdate();
		liberarRecursosBD();
		return inseridos;
		*/
		return 0;
	}
	
	
	//------------------------------------------------------------
	
	public List<ApropMovimEquipParams> obterApropriacoesEmMobileTabs(String ccObra, String idUsuarioLOGADO) throws SQLException {
		
	
		List<ApropMovimEquipParams> listaParams = new ArrayList<ApropMovimEquipParams>();
		ApropMovimEquipParams params = null;
		
		                                 //EXEC readDadosApropriadosMobile 303599,null,247,null
		StringBuilder exec = new StringBuilder(Procedures.PROC_READ_DADOS_APROPRIADOS_MOBILE).append("?,?,?,?");
		
		prepStmt = conn.prepareStatement(exec.toString());
		prepStmt.setString(1, ccObra);
		prepStmt.setString(2, null);
		prepStmt.setString(3, idUsuarioLOGADO);
		prepStmt.setString(4, null);
		rs = prepStmt.executeQuery();
		
		while(rs.next()){
			
			params = new ApropMovimEquipParams();
			
			params.setDataHoraApontamento(rs.getString("dataHoraApontamento"));
			params.setIdEquipamento(rs.getString("equipamento"));
			params.setDescricao(rs.getString("descricao"));
			params.setObservacao(rs.getString("observacoes"));
			params.setTipoApropriacao(rs.getString("tipoApropriacao"));
			
			listaParams.add(params);
		}
		
		liberarRecursosBD();
		return listaParams;
	}
	
	
	public int transferirDados_Apropriacoes_Mov_Eqp(String ccObra, String opcao, String idUsuarioSessionASP, ApropMovimEquipParams params) {
		
		//"readDadosArquivoParaValidacao ccObra,strDataApontamento,equipamento,frenteObra,atividade,tipoApropriacao,opcao
		
		int erro =  0;
		
		StringBuilder exec = new StringBuilder(Procedures.PROC_READ_DADOS_ARQUIVO_PARA_VALIDACAO).append("?,?,?,?,?,?,?");
		
		try {
			prepStmt = conn.prepareStatement(exec.toString());
			
			prepStmt.setString(1, ccObra);
			prepStmt.setString(2,params.getDataHoraApontamento());
			prepStmt.setString(3,params.getIdEquipamento());
			prepStmt.setString(4, params.getIdFrenteObra());
			prepStmt.setString(5,params.getIdAtividade());
			prepStmt.setString(6,params.getTipoApropriacao());
			prepStmt.setString(7,opcao);
			
			prepStmt.execute();
		
			liberarRecursosBD();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			erro = 1;
		}
		
		return erro;
	}
	
	//------------------------------------------------------------
	
	public int transferirDados_Servico_MaoDeObra_Paralisacao(String ccObra, String opcao, String idUsuarioSessionASP)  {

		//EXEC readDadosServicoMaoObraParalisacaoMobile ccObra, null, idUsuario, equipe, opcao		
		
		int erro =  0;
		
		StringBuilder exec = new StringBuilder(Procedures.PROC_READ_DADOS_SERVICO_MAO_OBRA_PARALISACAO_MOBILE).append("?,?,?,?,?");
		
		try {
			prepStmt = conn.prepareStatement(exec.toString());
			prepStmt.setString(1, ccObra);
			prepStmt.setString(2,null);
			prepStmt.setString(3,idUsuarioSessionASP);
			prepStmt.setString(4, null);
			prepStmt.setString(5,opcao);
			
			
			prepStmt.execute();
			
			liberarRecursosBD();
		
		} catch (SQLException e) {
			e.printStackTrace();
			erro = 1;
		}
		
		return erro;		
	}
	
	
	public int transferirDados_Abastecimento_Lubrificacao(String ccObra, String opcao, String idUsuarioLOGADO) {
		
		//EXEC readDadosAbastecimentoMobile ccObra,null,idUsuario,dispositivo,opcao
		
		int erro =  0;
		
		StringBuilder exec = new StringBuilder(Procedures.PROC_READ_DADOS_ABASTECIMENTO_MOBILE).append("?,?,?,?,?");
		
		try {
			
			prepStmt = conn.prepareStatement(exec.toString());
			prepStmt.setString(1, ccObra);
			prepStmt.setString(2, null);
			prepStmt.setString(3, idUsuarioLOGADO);
			prepStmt.setString(4, null);
			prepStmt.setString(5, opcao);
			
			prepStmt.execute();
			
			liberarRecursosBD();
			
		} catch (SQLException e) {
			e.printStackTrace();
			erro = 1;
		}

		return erro;
	}
	
	
	public int transferirDados_IndicePluviometrico() {
		
		return 0;
	}
	
	
	public int transferirDados_Manutencao() {
		
		return 0;
	}
}
