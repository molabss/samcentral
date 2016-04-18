package br.com.constran.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.constran.model.carga.CategoriaEquipamento;
import br.com.constran.model.carga.Contrato;
import br.com.constran.model.carga.ContratoAlocacaoEquipamento;
import br.com.constran.model.carga.ContratoAlocacaoPessoal;
import br.com.constran.model.carga.ContratoItensEquipamento;
import br.com.constran.model.carga.Equipamento;
import br.com.constran.model.carga.Fornecedor;
import br.com.constran.model.carga.Funcao;
import br.com.constran.model.carga.Pessoal;
import br.com.constran.util.TextUtil;
import br.com.constran.util.dao.AbstractDAO;

public class ContratoDAO extends AbstractDAO {

	public ContratoDAO(Connection conn) {
		super(conn);
	}

	
	public void cadastrarContrato(Contrato contrato) throws SQLException{
		
		StringBuilder insert = new StringBuilder();
		insert.append("INSERT INTO CONTRATOS (CCOBRA,NUMEROCONTRATO,TIPO,FORNECEDOR,OBJETO,TIPOPERIODO,PRAZO,INICIO,TERMINO, ");
		insert.append("VALORGLOBAL) ");
		insert.append("VALUES (?,?,?,?,?,?,?,?,?,?)");
		
		prepStmt = conn.prepareStatement(insert.toString());
		
		prepStmt.setString(1,contrato.getCcObra());
		prepStmt.setString(2,contrato.getNumero());
		prepStmt.setString(3,contrato.getTipo());
		prepStmt.setInt(4,contrato.getFornecedor().getId());
		prepStmt.setString(5,contrato.getObjeto());
		prepStmt.setString(6,contrato.getTipoPeriodo());
		prepStmt.setInt(7, contrato.getPrazo());

		prepStmt.setString(8, contrato.getInicio());
		//prepStmt.setString(8, null);
		
		prepStmt.setString(9,contrato.getTermino());
		//prepStmt.setString(9, null);

		//prepStmt.setString(10,contrato.getValorGlobal());
		prepStmt.setBigDecimal(10,TextUtil.toENmoney(contrato.getValorGlobal()));
		
		prepStmt.executeUpdate();
		
		liberarRecursosBD();		
	}

	
	
	public List<Contrato> listar(String cargaParaObra) throws SQLException {
		
		List<Contrato> lista = new ArrayList<Contrato>();
		Contrato c;
		StringBuilder select = new StringBuilder();
		select.append("select c.numeroContrato,c.ccObra,c.tipo,c.fornecedor,f.razao,c.objeto,c.tipoPeriodo,c.prazo,Convert(varchar(10),CONVERT(datetime,c.inicio,106),103) as inicio,Convert(varchar(10),CONVERT(datetime,c.termino,106),103) as termino, ");
		select.append("CONVERT(varchar, CONVERT(money, c.valorGlobal), 1) as valorGlobal  from contratos c inner join fornecedores f on c.fornecedor = f.idFornecedor where ccObra = ?");
		
		prepStmt = conn.prepareStatement(select.toString());
		
		prepStmt.setString(1,cargaParaObra);
		rs = prepStmt.executeQuery();
		while(rs.next()){
			
			c = new Contrato();
			c.setCcObra(rs.getString("ccObra"));
			c.setNumero(rs.getString("numeroContrato"));
			c.setTipo(rs.getString("tipo"));
			c.setFornecedor(new Fornecedor(rs.getInt("fornecedor"),rs.getString("razao")));
			c.setObjeto(rs.getString("objeto"));
			c.setTipoPeriodo(rs.getString("tipoPeriodo"));
			c.setPrazo(rs.getInt("prazo"));
			c.setInicio(rs.getString("inicio"));
			c.setTermino(rs.getString("termino"));
			c.setValorGlobal(rs.getString("valorGlobal"));
		
			lista.add(c);
		}
		
		liberarRecursosBD();
		return lista;
	}
	
	
	public void cadastrar_item_Equipamento(ContratoItensEquipamento cie) throws SQLException{
		StringBuilder insert = new StringBuilder();
		insert.append("insert into itensContratoEquipamentos (ccObra, numeroContrato, categoria, qtde, valorMensal, ");
		insert.append("valorHoraNormal, valorHoraExtra, horasMinimas, forneceOperador) ");
		insert.append("values (?,?,?,?,?,?,?,?,?)");
		
		prepStmt = conn.prepareStatement(insert.toString());
		
		prepStmt.setString(1, cie.getCcObra());
		prepStmt.setString(2, cie.getContrato().getNumero());
		prepStmt.setInt(3, cie.getCategoria());
		prepStmt.setInt(4, cie.getQuantidade());
		prepStmt.setBigDecimal(5, TextUtil.toENmoney(cie.getValorMensal().toString()));
		prepStmt.setBigDecimal(6, TextUtil.toENmoney(cie.getValorNormal().toString()));
		prepStmt.setBigDecimal(7, TextUtil.toENmoney(cie.getValorHoraExtra().toString()));
		prepStmt.setInt(8, cie.getHorasMinimas());
		prepStmt.setString(9, cie.getForneceOperador());
		
		prepStmt.executeUpdate();
		liberarRecursosBD();
	}
	
	
	public List<CategoriaEquipamento> listarCategoriaEquipamento (String numeroContrato) throws SQLException {
		
		List<CategoriaEquipamento> lProc = new ArrayList<CategoriaEquipamento>();
		CategoriaEquipamento cProc;
		
		List<CategoriaEquipamento> lJoin = new ArrayList<CategoriaEquipamento>();
		CategoriaEquipamento cJoin;
		
		List<CategoriaEquipamento> lreturn = new ArrayList<CategoriaEquipamento>();		
		
		cStmt = conn.prepareCall("exec carga_listarCategoriasEquipamentos");
		rs = cStmt.executeQuery();
		while(rs.next()){
			cProc = new CategoriaEquipamento(rs.getInt("idCategoria"),rs.getString("descricao"));
			lProc.add(cProc);
		}
		
		StringBuilder select = new StringBuilder();
		select.append("select cat.idCategoria,cat.descricao ");
		select.append("from categoria cat inner join itensContratoEquipamentos ic ");
		select.append("on ic.categoria = cat.idCategoria and ic.numeroContrato = ?");
		
		prepStmt  = conn.prepareStatement(select.toString());
		prepStmt.setString(1, numeroContrato);
		rs = prepStmt.executeQuery();
		while(rs.next()){
			cJoin = new CategoriaEquipamento(rs.getInt("idCategoria"),rs.getString("descricao"));
			lJoin.add(cJoin);
		}
		
		for(CategoriaEquipamento catA : lJoin){
			for(CategoriaEquipamento catB : lProc){
				if(catA.getId().equals(catB.getId())){
					lreturn.add(catB);
					continue;
				}				
			}
		}
		liberarRecursosBD();
		return lreturn;
	}
	

	public List<Equipamento> listarEquipamentoPorCategoria (Integer idCategoria, String cargaParaObra) throws SQLException {
		
		List<Equipamento> l = new ArrayList<Equipamento>();
		StringBuilder select = new StringBuilder();
		select.append("select idEquipamento, prefixo,descricao from equipamentos where idCategoria = ? and cargaParaObra = ?");
	
		prepStmt  = conn.prepareStatement(select.toString());
		prepStmt.setInt(1, idCategoria);
		prepStmt.setString(2, cargaParaObra);
		rs = prepStmt.executeQuery();
		while(rs.next()){
			l.add(new Equipamento(rs.getString("descricao"), rs.getString("prefixo"),rs.getInt("idEquipamento")));
		}
		liberarRecursosBD();
		return l;
	}
	
	
	public void cadastrar_alocacao_Equipamento(ContratoAlocacaoEquipamento cae) throws SQLException{
		StringBuilder insert = new StringBuilder();
		insert.append("insert into equipamentosAlocadosContrato (ccObra, numeroContrato, categoria, equipamento,");
		insert.append("dataIngresso, kmEntrada, horimetroEntrada, nfEntrada, tipoMedicao, realizaManutencao, tipoControleConsumo) ");
		insert.append("values (?,?,?,?,?,?,?,?,?,?,?)");
		
		prepStmt  = conn.prepareStatement(insert.toString());
		
		prepStmt.setString(1, cae.getCcObra());
		prepStmt.setString(2, cae.getContrato().getNumero());
		prepStmt.setInt(3, cae.getCategoria());
		prepStmt.setInt(4, cae.getEquipamento().getId());
		prepStmt.setString(5, cae.getDataIngresso());
		//prepStmt.setString(5, "2015-11-06 00:00:00.000");
		prepStmt.setInt(6, cae.getKmEntrada());
		prepStmt.setBigDecimal(7,cae.getHorimetroEntrada());
		prepStmt.setString(8, cae.getNfEntrada());
		prepStmt.setString(9, cae.getTipoMedicao());
		prepStmt.setString(10, cae.getRealizaManutencao());
		prepStmt.setString(11, cae.getTipoControleConsumo());

		prepStmt.executeUpdate();

		liberarRecursosBD();
	}
	
	
	public void cadastrar_alocacao_Pessoa(ContratoAlocacaoPessoal cap, String cargaParaObra) throws SQLException {
		
		StringBuilder insert = new StringBuilder();
		insert.append("insert into pessoasAlocadasContratos (ccObra, numeroContrato,pessoa,dataIngresso,tipoMedicao,funcao)");
		insert.append("values (?,?,?,?,?,?)");
		prepStmt = conn.prepareStatement(insert.toString());
		prepStmt.setString(1, cargaParaObra);
		prepStmt.setString(2, cap.getContrato().getNumero());
		prepStmt.setInt(3, cap.getPessoa().getId());
		prepStmt.setString(4, cap.getDataIngresso());
		prepStmt.setString(5, cap.getTipoMedicao());
		prepStmt.setInt(6, cap.getFuncao().getId());
		
		prepStmt.executeUpdate();
		
		liberarRecursosBD();
	}
	
	
	public List<ContratoItensEquipamento> listarItensEquipamentos(String ccObra) throws SQLException{
		
		List<ContratoItensEquipamento> l = new ArrayList<ContratoItensEquipamento>();
		StringBuilder select = new StringBuilder();
		ContratoItensEquipamento c;
		
		select.append("select ie.ccObra, ie.numeroContrato, ie.categoria, ca.descricao, ie.qtde, ");
		select.append("ie.valorMensal, ie.valorHoraNormal, ie.valorHoraExtra, ie.horasMinimas, ie.forneceOperador ");
		select.append("from itensContratoEquipamentos ie ");
		select.append("inner join categoria ca ");
		select.append("on ie.categoria = ca.idCategoria ");
		select.append("where ie.ccObra = ?");
		
		prepStmt = conn.prepareStatement(select.toString());
		prepStmt.setString(1,ccObra);
		rs = prepStmt.executeQuery();
		
		while(rs.next()){
			c = new ContratoItensEquipamento(
					  rs.getString("ccObra")
					, new Contrato( rs.getString("numeroContrato"))
					, rs.getInt("categoria")
					, rs.getString("descricao")
					, rs.getInt("qtde")
					, rs.getBigDecimal("valorMensal")
					, rs.getBigDecimal("valorHoraNormal")
					, rs.getBigDecimal("valorHoraExtra")
					, rs.getInt("horasMinimas")
					, rs.getString("forneceOperador")
				);
			l.add(c);
		}
		
		liberarRecursosBD();
		return l;
	}
	
	
	public List<ContratoAlocacaoEquipamento> listarEquipamentosAlocados(String ccObra) throws SQLException{
		
		List<ContratoAlocacaoEquipamento> l = new ArrayList<ContratoAlocacaoEquipamento>();
		
		ContratoAlocacaoEquipamento cae;
		
		StringBuilder select = new StringBuilder();
		select.append("select eac.ccObra, eac.numeroContrato,eac.categoria,eac.equipamento,eqp.prefixo,eqp.descricao as eqpDesc,ca.descricao as categDesc,eac.horimetroEntrada,eac.tipoControleConsumo, ");
		select.append("Convert(varchar(10),CONVERT(datetime,eac.dataIngresso,106),103) as dataIngresso,eac.kmEntrada,eac.nfEntrada,eac.tipoMedicao,eac.dataSaida,");
		select.append("eac.kmSaida,eac.horimetroSaida,eac.nfSaida,eac.realizaManutencao ");
		select.append("from equipamentosAlocadosContrato eac ");
		select.append("inner join equipamentos eqp ");
		select.append("on eac.equipamento = eqp.idEquipamento ");
		select.append("inner join categoria ca ");
		select.append("on eac.categoria = ca.idCategoria ");
		select.append("where eac.ccObra = ?");
		
		
		prepStmt = conn.prepareStatement(select.toString());
		prepStmt.setString(1,ccObra);
		rs = prepStmt.executeQuery();
		
		while(rs.next()){

			cae = new ContratoAlocacaoEquipamento(
					  rs.getString("ccObra")
					, new Contrato(rs.getString("numeroContrato"))
					, rs.getInt("categoria")
				  //, rs.getString("categDesc")
					, obterCategoriaCompletaEqpmt(rs.getString("categoria"))
					, new Equipamento(rs.getString("eqpDesc"), rs.getString("prefixo"), rs.getInt("equipamento"))
					, rs.getString("dataIngresso")
					, rs.getInt("kmEntrada")
					, rs.getBigDecimal("horimetroEntrada")
					, rs.getString("nfEntrada")
					, rs.getString("tipoMedicao")
					, rs.getString("realizaManutencao")
					, rs.getString("tipoControleConsumo")
				);
			
			l.add(cae);
		}
		
		liberarRecursosBD();
		return l;
	}
	
	
	private String obterCategoriaCompletaEqpmt(String idCatFilho) throws SQLException{
		
		List<CategoriaEquipamento> lProc = new ArrayList<CategoriaEquipamento>();
		CategoriaEquipamento cProc;		
		
		cStmt = conn.prepareCall("exec carga_listarCategoriasEquipamentos");
		ResultSet rs = cStmt.executeQuery();
		while(rs.next()){
			cProc = new CategoriaEquipamento(rs.getInt("idCategoria"),rs.getString("descricao"));
			lProc.add(cProc);
		}

		String desc = null;
		
		for(CategoriaEquipamento cat : lProc){
		
			if(cat.getId().toString().equals(idCatFilho)){
				desc = cat.getDescricao();
				break;
			}				
		}
		return desc;
	}
	
	
	public List<ContratoAlocacaoPessoal> listarPessoasAlocados(String ccObra) throws SQLException{
		
		List<ContratoAlocacaoPessoal> l = new ArrayList<ContratoAlocacaoPessoal>();
		ContratoAlocacaoPessoal c;
		StringBuilder select = new StringBuilder();
		
		select.append("select pac.ccObra, pac.numeroContrato, pac.pessoa, pe.nome,fu.descricaoFuncao, pac.tipoMedicao, pac.funcao, Convert(varchar(10),CONVERT(datetime,pac.dataIngresso,106),103) as dataIngresso ");
		select.append("from pessoasAlocadasContratos pac ");
		select.append("inner join pessoal pe ");
		select.append("on pac.pessoa = pe.idPessoal ");
		select.append("inner join  funcoes fu ");
		select.append("on pac.funcao = fu.idFuncao ");
		select.append("where pac.ccObra = ?");
		
		prepStmt = conn.prepareStatement(select.toString());
		prepStmt.setString(1, ccObra);
		
		rs = prepStmt.executeQuery();
				
		
		while(rs.next()){
			c = new ContratoAlocacaoPessoal(
					rs.getString("ccObra")
					, new Contrato(rs.getString("numeroContrato"))
					, new Pessoal(rs.getInt("pessoa")
							, rs.getString("nome"))
					, rs.getString("dataIngresso")
					, rs.getString("tipoMedicao")
					, new Funcao(rs.getInt("funcao"), rs.getString("descricaoFuncao"))
				);
			l.add(c);
		}
		liberarRecursosBD();
		return l;
	}
	
	
	public void removeContrato(String ccObra, String numeroContrato) throws SQLException{
		   
		   StringBuilder deleteCheck = new StringBuilder();
		   deleteCheck.append("select * from itensContratoEquipamentos where numeroContrato in ");
		   deleteCheck.append("(select numeroContrato from contratos where numeroContrato = ?) and ccObra = ?");
		   prepStmt = conn.prepareStatement(deleteCheck.toString()); 
		   prepStmt.setString(1, numeroContrato);
		   prepStmt.setString(2, ccObra);
		   
		   if(prepStmt.executeQuery().next()){
			   liberarRecursosBD();
			   throw new SQLException("Este Contrato não pode ser removido.\nEle contém itens/ categorias de equipamentos vinculadas.");
		   }
		   //-----------------------------------------------------------------------------------
		   
		   deleteCheck.delete(0, deleteCheck.length());
		   deleteCheck.append("select * from equipamentosAlocadosContrato where numeroContrato in ");
		   deleteCheck.append("(select numeroContrato from contratos where numeroContrato = ?) and ccObra = ?");
		   prepStmt = conn.prepareStatement(deleteCheck.toString()); 
		   prepStmt.setString(1, numeroContrato);
		   prepStmt.setString(2, ccObra);

		   if(prepStmt.executeQuery().next()){
			   liberarRecursosBD();
			   throw new SQLException("Este Contrato não pode ser removido.\nEle contém equipamentos vinculados.");
		   }
		   //-----------------------------------------------------------------------------------
		   
		   deleteCheck.delete(0, deleteCheck.length());
		   deleteCheck.append("select * from pessoasAlocadasContratos where numeroContrato in ");
		   deleteCheck.append("(select numeroContrato from contratos where numeroContrato = ?) and ccObra = ?");
		   prepStmt = conn.prepareStatement(deleteCheck.toString()); 
		   prepStmt.setString(1, numeroContrato);
		   prepStmt.setString(2, ccObra);

		   if(prepStmt.executeQuery().next()){
			   liberarRecursosBD();
			   throw new SQLException("Este Contrato não pode ser removido.\nEle contém Pessoas vinculadas.");
		   }
		   //-----------------------------------------------------------------------------------
		   
		   prepStmt = conn.prepareStatement("delete from contratos where ccObra = ? and numeroContrato = ?");
		   prepStmt.setString(1, ccObra);
		   prepStmt.setString(2, numeroContrato);
		   prepStmt.executeUpdate();
		   liberarRecursosBD();
	   }
	   
	   
	   
	   public void removeItensContrato_equipamento(String ccObra, String numeroContrato, String categoria) throws SQLException{
		   
		   StringBuilder deleteCheck = new StringBuilder();
		   deleteCheck.append("select * from equipamentosAlocadosContrato where numeroContrato in ");
		   deleteCheck.append("(select numeroContrato from contratos where numeroContrato = ?) and ccObra = ?");
		   prepStmt = conn.prepareStatement(deleteCheck.toString()); 
		   prepStmt.setString(1, numeroContrato);
		   prepStmt.setString(2, ccObra);

		   if(prepStmt.executeQuery().next()){
			   liberarRecursosBD();
			   throw new SQLException("Este item não pode ser removido.\n Ele contém equipamentos vinculados.");
		   }		   
		   
		   prepStmt = conn.prepareStatement("delete from itensContratoEquipamentos where ccObra = ? and numeroContrato = ? and categoria = ?");
		   prepStmt.setString(1, ccObra);
		   prepStmt.setString(2, numeroContrato);
		   prepStmt.setString(3, categoria);
		   prepStmt.executeUpdate();
		   liberarRecursosBD();
	   }
	   
	   
	   public void removeAlocadoEquipamento_contrato(String ccObra, String numeroContrato, String categoria,String equipamento, String dataIngresso) throws SQLException{
		   prepStmt = conn.prepareStatement("delete from equipamentosAlocadosContrato where ccObra = ? and numeroContrato = ? and categoria = ? and equipamento = ? and dataIngresso = ?");
		   prepStmt.setString(1, ccObra);
		   prepStmt.setString(2, numeroContrato);
		   prepStmt.setString(3, categoria);
		   prepStmt.setString(4, equipamento);
		   prepStmt.setString(5, dataIngresso);
		   prepStmt.executeUpdate();
		   liberarRecursosBD();
	   }
	   
	   
	   public void removeAlocadoPessoa_contrato(String ccObra, String numeroContrato,String pessoa, String dataIngresso) throws SQLException{
		   prepStmt = conn.prepareStatement("delete from pessoasAlocadasContratos where ccObra = ? and numeroContrato = ? and pessoa = ? and dataIngresso = ?");
		   prepStmt.setString(1, ccObra);
		   prepStmt.setString(2, numeroContrato);
		   prepStmt.setInt(3, Integer.parseInt(pessoa));
		   prepStmt.setString(4, dataIngresso);
		   prepStmt.executeUpdate();
		   liberarRecursosBD();
	   }	   
}
