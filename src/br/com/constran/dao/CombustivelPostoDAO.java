package br.com.constran.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.constran.mobile.persistence.vo.rae.abs.CombustivelPostoVO;
import br.com.constran.util.dao.AbstractDAO;

public class CombustivelPostoDAO extends AbstractDAO {

	public CombustivelPostoDAO(Connection conn) {
		super(conn);
	}
	
    public List<CombustivelPostoVO> getCombustivelPostos(String ccObra) throws SQLException{

    	StringBuilder exec = new StringBuilder(Procedures.PROC_GERACAO_MOBILE).append("?,?");
        List<CombustivelPostoVO> list = new ArrayList<CombustivelPostoVO>();
        CombustivelPostoVO vo = null;
        
        prepStmt = conn.prepareStatement(exec.toString());
        prepStmt.setString(1,ccObra);
        prepStmt.setString(2,"CP");
        rs = prepStmt.executeQuery();

        while (rs.next()){
            vo =  new CombustivelPostoVO(rs.getInt(1), rs.getInt(2));
            list.add(vo);
        }

        liberarRecursosBD();
        return list;
    }
    
    

    
}
