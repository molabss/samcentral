package br.com.constran.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.constran.mobile.persistence.vo.rae.abs.CombustivelLubrificanteVO;
import br.com.constran.model.carga.CombustivelLubrificante;
import br.com.constran.util.dao.AbstractDAO;

public class CombustivelLubrificacaoDAO extends AbstractDAO {

	public CombustivelLubrificacaoDAO(Connection conn) {
		super(conn);
	}
	
    public List<CombustivelLubrificanteVO> getCombustiveis(String ccObra) throws SQLException{

    	StringBuilder exec = new StringBuilder(Procedures.PROC_GERACAO_MOBILE).append("?,?");
        List<CombustivelLubrificanteVO> list = new ArrayList<CombustivelLubrificanteVO>();
        CombustivelLubrificanteVO vo = null;

        prepStmt = conn.prepareStatement(exec.toString());
        prepStmt.setString(1,ccObra);
        prepStmt.setString(2,"CL");
        rs = prepStmt.executeQuery();

        while (rs.next()){
            vo =  new CombustivelLubrificanteVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
            list.add(vo);
        }

        liberarRecursosBD();
        return list;
    }
    
    
    
    public List<CombustivelLubrificante> listarCombustiveisElubrificantes() throws SQLException{
    	
    	List<CombustivelLubrificante> lista = new ArrayList<CombustivelLubrificante>();
    	
    	StringBuilder select = new StringBuilder();
    	select.append("select idCombustivel, descricao, tipo, unidadeMedida from combustiveisLubrificantes order by descricao");
    	
    	prepStmt = conn.prepareStatement(select.toString());
    	rs = prepStmt.executeQuery();
    	while(rs.next()){
    		lista.add(new CombustivelLubrificante(rs.getInt("idCombustivel"), rs.getString("descricao"), rs.getString("unidadeMedida"), rs.getString("tipo")));
    	}
    	
    	liberarRecursosBD();
    	return lista;
    }
}
