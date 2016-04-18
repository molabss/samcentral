package br.com.constran.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.constran.mobile.persistence.vo.imp.EquipamentoVO;
import br.com.constran.model.carga.CategoriaEquipamento;
import br.com.constran.model.carga.Equipamento;
import br.com.constran.model.carga.Fabricante;
import br.com.constran.model.carga.Fornecedor;
import br.com.constran.util.dao.AbstractDAO;

public class EquipamentoDAO extends AbstractDAO{

   public EquipamentoDAO(Connection conn) {
		super(conn);
   }
	
   public List<EquipamentoVO> getEquipamentos(String ccObra) throws SQLException {
	   
	    StringBuilder exec = new StringBuilder(Procedures.PROC_GERACAO_MOBILE).append("?,?");
	    
        List<EquipamentoVO> list = new ArrayList<EquipamentoVO>();
        EquipamentoVO vo = null;
        
        prepStmt = conn.prepareStatement(exec.toString());
        prepStmt.setString(1,ccObra);
        prepStmt.setString(2,"E");
        rs = prepStmt.executeQuery();
        rsmd = rs.getMetaData();
        
        while (rs.next()){
            vo =  new EquipamentoVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10));
            list.add(vo);
        }

        liberarRecursosBD();
        return list;
    }
   
   
   
   public void cadastrarEquipamento(Equipamento equipamento,String cargaParaObra) throws SQLException {
	   
		StringBuilder insert = new StringBuilder();
		insert.append("INSERT INTO EQUIPAMENTOS (PREFIXO,DESCRICAO,IDCATEGORIA,PROPRIETARIO,TIPO, ");
		insert.append("UNIDADEMEDIDA,CHASSISERIE,PLACA,FABRICANTE,ANO,CONTROLELUBRIFICACAO,cargaParaObra,qrCode) ");
		insert.append("VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
		
		prepStmt = conn.prepareStatement(insert.toString());
		
		prepStmt.setString(1,equipamento.getPrefixo());
		prepStmt.setString(2,equipamento.getDescricao());
		prepStmt.setInt(3,equipamento.getIdCategoria());
		prepStmt.setInt(4,equipamento.getFornecedor().getId());
		prepStmt.setString(5,equipamento.getTipo());
		prepStmt.setString(6,equipamento.getUnidadeMedida());
		prepStmt.setString(7, equipamento.getChassi());
		prepStmt.setString(8, equipamento.getPlaca());
		prepStmt.setInt(9,equipamento.getFabricante().getId());
		prepStmt.setInt(10, equipamento.getAno());
		prepStmt.setString(11, equipamento.getControleLubrificacao());
		prepStmt.setString(12, cargaParaObra);
		prepStmt.setString(13, equipamento.getQrCode());
		
		prepStmt.executeUpdate();
		
		liberarRecursosBD();
   }
   
   
   public List<CategoriaEquipamento> listarCategoriasEquipamentos() throws SQLException{
	   
	   List<CategoriaEquipamento> lista  = new ArrayList<CategoriaEquipamento>();
	   
	   CategoriaEquipamento c = null;
	   
	   rs = (prepStmt = conn.prepareStatement("exec carga_listarCategoriasEquipamentos")).executeQuery();
	   
	   while(rs.next()){
		   
		   c = new CategoriaEquipamento(rs.getInt("idCategoria"), rs.getString("descricao"));
		   
		   lista.add(c);
	   }
   
	   liberarRecursosBD();
	   
	   return lista;
   }
   
   public List<Fabricante> listarFabricanteEquipamentos() throws SQLException{
	   List<Fabricante> lista  = new ArrayList<Fabricante>();
	   
	   Fabricante f = null;
	   
	   rs = (prepStmt = conn.prepareStatement("select * from fabricantes order by descricao")).executeQuery();
	   
	   while(rs.next()){
		   
		   f = new Fabricante(rs.getInt("idFabricante"), rs.getString("descricao"));
		   
		   lista.add(f);
	   }
   
	   liberarRecursosBD();
	   
	   return lista;
   }
   
   public List<Equipamento> listar(String cargaParaObra) throws SQLException{
	   
	   StringBuilder select = new StringBuilder();
	   List<Equipamento> lista  = new ArrayList<Equipamento>();
	   Equipamento e = null;
	   
	   select.append("select eq.idEquipamento");
	   select.append(",eq.prefixo");
	   select.append(",eq.descricao as eQdesc");
	   select.append(",ca.descricao as caDesc");
	   select.append(",fo.razao");
	   select.append(",eq.tipo");
	   select.append(",eq.unidadeMedida");
	   select.append(",eq.chassiSerie");
	   select.append(",eq.placa");
	   select.append(",fa.descricao as descFabri");
	   select.append(",eq.ano");
	   select.append(",eq.controleLubrificacao");
	   select.append(",eq.qrCode ");
	   select.append("from equipamentos eq "); 
	   select.append("inner join categoria ca ");
	   select.append("on eq.idCategoria = ca.idCategoria ");
	   select.append("inner join fornecedores fo ");
	   select.append("on eq.proprietario = fo.idFornecedor ");
	   select.append("inner join fabricantes fa ");
	   select.append("on eq.fabricante = fa.idFabricante ");    
	   select.append("where eq.cargaParaObra = ? order by eq.descricao");
	
	   prepStmt = conn.prepareStatement(select.toString());
	   
	   prepStmt.setString(1, cargaParaObra);
	   rs = prepStmt.executeQuery();
	   
	   while(rs.next()){
		   
		   /*Integer id, String descricao, CategoriaEquipamento categoria,
			String qrCode,Fornecedor fornecedor, String tipo,
			String unidadeMedida, String chassi, Fabricante fabricante,
			String placa, String prefixo, Integer ano, String volume,
			String controleLubrificacao)*/
		   
		   e = new Equipamento(
				     rs.getInt("idEquipamento")
				    ,rs.getString("eQdesc")
				    ,new CategoriaEquipamento(null, rs.getString("caDesc"))
				    ,rs.getString("qrCode")
				    ,new Fornecedor(rs.getString("razao"))
				    ,rs.getString("tipo")
				    ,rs.getString("unidadeMedida")
				    ,rs.getString("chassiSerie")
				    ,new Fabricante(null, rs.getString("descFabri"))
				    ,rs.getString("placa")
				    ,rs.getString("prefixo")
				    ,rs.getInt("ano")
				    ,rs.getString("controleLubrificacao"));
		   lista.add(e);
	   }
	   liberarRecursosBD();
	   return lista;
   }
   
   
   /*	   public void removeFornecedor(String ccObra,String id) throws SQLException{
		   
		   StringBuilder deleteCheck = new StringBuilder ();
		   deleteCheck.append("select * from equipamentos where proprietario in ");
		   deleteCheck.append("(select idFornecedor from fornecedores where idFornecedor = ?) and cargaParaObra = ?");
		   
		   prepStmt = conn.prepareStatement(deleteCheck.toString()); 
		   prepStmt.setString(1, id);
		   prepStmt.setString(2, ccObra);

		   if(prepStmt.executeQuery().next()){
			   throw new SQLException("Este Fornecedor não pode ser removido.\n Existem equipamentos vinculados a ele.");
		   }
		   
		   prepStmt = conn.prepareStatement("delete from fornecedores where idFornecedor = ?");
		   prepStmt.setString(1, id);
		   prepStmt.executeUpdate();
		   liberarRecursosBD();
	   }*/
   
   public void removeEquipamento(String ccObra,String id) throws SQLException{
	   
	   StringBuilder deleteCheck = new StringBuilder ();
	   
	   System.out.println(ccObra+" "+id);
	   
	   deleteCheck.append("select * from equipamentosAlocadosContrato where equipamento in ");
	   deleteCheck.append("(select idEquipamento from equipamentos where idEquipamento = ?) and ccObra = ?");
	   prepStmt = conn.prepareStatement(deleteCheck.toString());
	   prepStmt.setString(1,id);
	   prepStmt.setString(2,ccObra);
	   
	   if(prepStmt.executeQuery().next()){
		   liberarRecursosBD();
		   throw new SQLException("Este Equipamento não pode ser removido.\n Ele está vinculado a um contrato.");
	   }
	   
	   deleteCheck.delete(0, deleteCheck.length());
	   deleteCheck.append("select * from postosAbastecimento where equipamento in ");
	   deleteCheck.append("(select idEquipamento from equipamentos where idEquipamento = ?) and ccObra = ?");
	   prepStmt = conn.prepareStatement(deleteCheck.toString());
	   prepStmt.setString(1, id);
	   prepStmt.setString(2, ccObra);
	   
	   if(prepStmt.executeQuery().next()){
		   liberarRecursosBD();
		   throw new SQLException("Este Equipamento não pode ser removido.\n Ele está vinculado a Postos de Abastecimento.");
	   }
	   
	   prepStmt = conn.prepareStatement("delete from equipamentos where idEquipamento = ? and cargaParaObra = ?");
	   prepStmt.setString(1, id);
	   prepStmt.setString(2, ccObra);
	   prepStmt.executeUpdate();
	   liberarRecursosBD();
   }
   
   
}
