package br.com.constran.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.constran.mobile.persistence.vo.rae.abs.CompartimentoVO;
import br.com.constran.util.dao.AbstractDAO;

public class CompartimentoDAO extends AbstractDAO{

	public CompartimentoDAO(Connection conn) {
		super(conn);
	}
	
	
    public List<CompartimentoVO> getCompartimentos(String ccObra) throws SQLException{

    	StringBuilder exec = new StringBuilder(Procedures.PROC_GERACAO_MOBILE).append("?,?");
    	List<CompartimentoVO> list = new ArrayList<CompartimentoVO>();
        CompartimentoVO vo = null;
       
        prepStmt = conn.prepareStatement(exec.toString());
        prepStmt.setString(1,ccObra);
        prepStmt.setString(2,"CT");
        rs = prepStmt.executeQuery();

        while (rs.next()){
        	vo =  new CompartimentoVO(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4));
            list.add(vo);
        }

        liberarRecursosBD();
        return list;
    }
}
