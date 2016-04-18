package br.com.constran.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.constran.mobile.persistence.vo.rae.abs.PostoVO;
import br.com.constran.model.carga.CombustivelLubrificante;
import br.com.constran.model.carga.CombustivelPosto;
import br.com.constran.model.carga.Equipamento;
import br.com.constran.model.carga.PostoAbastecimento;
import br.com.constran.util.dao.AbstractDAO;

public class PostoDAO extends AbstractDAO {

	public PostoDAO(Connection conn) {
		super(conn);
	}
	
	
    public List<PostoVO> getPostos(String ccObra) throws SQLException {

    	StringBuilder exec = new StringBuilder(Procedures.PROC_GERACAO_MOBILE).append("?,?");
        List<PostoVO> list = new ArrayList<PostoVO>();
        PostoVO vo = null;
        
        prepStmt = conn.prepareStatement(exec.toString());
        prepStmt.setString(1,ccObra);
        prepStmt.setString(2,"PT");
        rs = prepStmt.executeQuery();

        while (rs.next()){
            vo =  new PostoVO(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4));
            list.add(vo);
        }

        liberarRecursosBD();
        return list;
    }
    
    
    public List<PostoAbastecimento> listar(String cargaParaObra) throws SQLException {
    	
    	List<PostoAbastecimento> lista = new ArrayList<PostoAbastecimento>();
    	StringBuilder select = new StringBuilder();
    	select.append("select pa.idPostoAbastecimento, pa.tipo, pa.equipamento, eq.prefixo, eq.descricao as eQdesc, pa.descricao as pAdesc from postosAbastecimento pa ");
    	select.append("inner join equipamentos eq ");
    	select.append("on pa.equipamento = eq.idEquipamento ");
    	select.append("where pa.ccObra = ?");
    	
    	prepStmt = conn.prepareStatement(select.toString());
    	prepStmt.setString(1, cargaParaObra);
    	
    	rs = prepStmt.executeQuery();
    	while(rs.next()){
    		
    		lista.add(new PostoAbastecimento(
    				 rs.getInt("idPostoAbastecimento")
    				,cargaParaObra
    				,rs.getString("tipo")
    				,new Equipamento(rs.getString("eQdesc"), rs.getString("prefixo"), rs.getInt("equipamento"))
    				,rs.getString("pAdesc"))
    		);
    		
    	}
    	
    	liberarRecursosBD();
    	return lista;
    }
    
    
    public void cadastra(PostoAbastecimento pa) throws SQLException {
    	
    	StringBuilder insert = new StringBuilder();
    	insert.append("insert into postosAbastecimento (ccObra,tipo,equipamento,descricao) values (?,?,?,?)");
    	
    	prepStmt = conn.prepareStatement(insert.toString());
    	prepStmt.setString(1,pa.getCcObra());
    	prepStmt.setString(2,pa.getTipo());
    	prepStmt.setInt(3,pa.getEquipamento().getId());
    	prepStmt.setString(4, pa.getDescricao());
    	
    	prepStmt.executeUpdate();
    	
    	liberarRecursosBD();   	
    }
    
    
    public void adicionarCombustiveisLubrificantes(String ccObra, Integer postoAbastecimento, List<String> combustivelLubrificante) throws SQLException {
    	
    	StringBuilder insert = new StringBuilder();
    	insert.append("insert into combustiveisPostos (ccObra,postoAbastecimento,combustivel) values (?,?,?)");
    	prepStmt = conn.prepareStatement(insert.toString());
    	
    	
    	for(String id : combustivelLubrificante){
    		
        	prepStmt.setString(1, ccObra);
        	prepStmt.setInt(2, postoAbastecimento);
        	prepStmt.setInt(3, Integer.parseInt(id));
    		
        	prepStmt.executeUpdate();
    	}
    	
    	liberarRecursosBD();
    }
    
    public List<CombustivelPosto> listarCombustiveisLubrificantesDoPosto(String ccObra) throws SQLException {
    	
    	List<CombustivelPosto> l = new ArrayList<CombustivelPosto>();
    	CombustivelPosto c;
    	StringBuilder select = new StringBuilder();
   	
    	select.append("select cb.ccObra, pa.tipo, pa.equipamento,eqp.prefixo, pa.idPostoAbastecimento ,pa.descricao as descPosto, ");
    	select.append("eqp.descricao as descEquipa,com.descricao as descCombus,com.idCombustivel,com.unidadeMedida, com.tipo as tipoCombustLub ");
    	select.append("from combustiveisPostos cb ");
    	select.append("inner join postosAbastecimento pa ");
    	select.append("on cb.postoAbastecimento = pa.idPostoAbastecimento ");
    	select.append("inner join combustiveisLubrificantes com ");
    	select.append("on cb.combustivel = com.idCombustivel ");
    	select.append("inner join equipamentos eqp ");
    	select.append("on pa.equipamento = eqp.idEquipamento where cb.ccObra = ?");
    	
    	prepStmt = conn.prepareStatement(select.toString());
    	prepStmt.setString(1, ccObra);
    	rs = prepStmt.executeQuery();
    	
    	while(rs.next()){
    		
    		c = new CombustivelPosto();
    		c.setCcObra(rs.getString("ccObra"));
    		c.setCombustivelLubrif(new CombustivelLubrificante(rs.getInt("idCombustivel"), rs.getString("descCombus"), rs.getString("unidadeMedida"), rs.getString("tipoCombustLub")));
    		c.setPosto(new PostoAbastecimento(rs.getInt("idPostoAbastecimento"), ccObra, rs.getString("tipo"), new Equipamento(rs.getString("descEquipa"),rs.getString("prefixo"),rs.getInt("equipamento") ), rs.getString("descPosto")));
    		
    		l.add(c);
    	}
    	
    	liberarRecursosBD();
    	return l;
    }
    
    public void removePosto(String ccObra, String id) throws SQLException {
    	
  	   StringBuilder deleteCheck = new StringBuilder ();
  	   deleteCheck.append("select * from combustiveisPostos where postoAbastecimento in ");
  	   deleteCheck.append("(select idPostoAbastecimento from postosAbastecimento where idPostoAbastecimento = ?) and ccObra = ?");    	
    	
 	   prepStmt = conn.prepareStatement(deleteCheck.toString()); 
 	   prepStmt.setString(1, id);
 	   prepStmt.setString(2, ccObra);
	   if(prepStmt.executeQuery().next()){
		   throw new SQLException("\nEste Posto não pode ser removido.\nExistem Combustíveis/ Lubrificantes vinculados a ele.");
	   }
  	    
 	   prepStmt = conn.prepareStatement("delete from postosAbastecimento where idPostoAbastecimento = ?");
 	   prepStmt.setString(1, id);
 	   prepStmt.executeUpdate();
 	   liberarRecursosBD();
    }
    
    
    public void removeCombustivelLubrificante(String idCombustivel,String idPosto) throws SQLException {
       prepStmt = conn.prepareStatement("delete from combustiveisPostos where combustivel = ? and postoAbastecimento = ?");
	   prepStmt.setString(1,idCombustivel);
	   prepStmt.setString(2,idPosto);
	   prepStmt.executeUpdate();
    }
    
}
