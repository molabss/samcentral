package br.com.constran.util.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class AbstractDAO {
	
	public Connection conn = null;
	public PreparedStatement prepStmt = null;
	public Statement stmt = null;
	public ResultSet rs = null;
	public ResultSetMetaData rsmd = null;
	public CallableStatement cStmt = null;
	
	
	public AbstractDAO(Connection conn) {
		this.conn = conn;
	}
	
	public void liberarRecursosBD() throws SQLException {
		
		if(rs != null){
			rs.close();
			rs = null;
		}
		
		if(rsmd != null){
			rsmd = null;
		}
		
		if(stmt != null){
			stmt.close();
			stmt = null;
		}
		
		if(prepStmt != null){
			prepStmt.clearParameters();
			prepStmt.close();
			prepStmt = null;
		}
		
		if(cStmt != null){
			cStmt.close();
			cStmt = null;
		}
	}
	
	public static final class Procedures {
		
		//PARA TABELAS MOBILE -->
		public static String PROC_GERACAO_MOBILE  = "EXEC geracaoArquivoMobile ";
		public static String PROC_TOTAL_APROP_MOBILE_OBRA_TABLET  = "EXEC totalApropMobileObraTablet ";
		public static String PROC_INSERT_APROP_MOBILE  = "EXEC insertApropriacaoMobile ";
		public static String PROC_INSERT_APROP_MOVIMENTACAO_MOBILE  = "EXEC insertApropMovimentacaoMobile ";
		public static String PROC_INSERT_APROP_VIAGEM_MOBILE  = "EXEC insertApropViagemMobile ";
		public static String PROC_INSERT_PARTE_DIARIA_EQUIP_MOBILE  = "EXEC insertParteDiariaEquipMobile ";
		public static String PROC_INSERT_PARTE_DIARIA_EQUIP_EVENTOS_MOBILE  = "EXEC insertParteDiariaEquipEventosMobile ";
		public static String PROC_INSERT_APROPRIACAO_SERVICO_MOBILE  = "EXEC insertApropriacaoServicoMobile ";
		public static String PROC_INSERT_APROPRIACAO_MAODEOBRA_MOBILE  = "EXEC insertApropriacoesMaoObraMobile ";
		public static String PROC_INSERT_PARALISACOES_EQUIPE_MOBILE  = "EXEC insertParalisacoesEquipeMobile ";
		public static String PROC_INSERT_PARALISACOES_MAO_OBRA_MOBILE = "EXEC insertParalisacoesMaoObraMobile ";
		public static String PROC_INSERT_RAE_MOBILE = "EXEC insertRAEMobile ";
		public static String PROC_INSERT_ABASTECIMENTO_MOBILE = "EXEC insertAbastecimentosMobile ";
		public static String PROC_INSERT_LUBRIFICACAO_DETALHE_MOBILE = "EXEC insertLubrificacoesDetalhesMobile ";
		public static String PROC_INSERT_SALDOS_POSTOS_MOBILE = "EXEC insertSaldosPostosMobile ";
		//<-----------------------
		
		
		//PARA TABELAS IMPORT -->
		public static String PROC_READ_DADOS_APROPRIADOS_MOBILE = "EXEC readDadosApropriadosMobile ";
		public static String PROC_READ_DADOS_ARQUIVO_PARA_VALIDACAO = "EXEC readDadosArquivoParaValidacao ";
		public static String PROC_READ_DADOS_SERVICO_MAO_OBRA_PARALISACAO_MOBILE = "EXEC readDadosServicoMaoObraParalisacaoMobile ";
		public static String PROC_READ_DADOS_ABASTECIMENTO_MOBILE = "EXEC readDadosAbastecimentoMobile ";
		//<-----------------------
		
		
		//PARA SISTEMA DE CARGA DE DADOS SAMCENTRAL (REPLICA NA BASE DO SAT DEC)-->
		public static String PROC_INSERT_FORNECEDORES = "EXEC insertFornecedores_sam_carga ";
		public static String PROC_DELETE_FORNECEDORES = "EXEC deleteFornecedores ";
		//<-----------------------
		
	}
	
	public abstract class Tabelas{
		
	}

}
