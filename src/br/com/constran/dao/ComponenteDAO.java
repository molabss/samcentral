package br.com.constran.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.constran.mobile.persistence.vo.imp.ComponenteVO;
import br.com.constran.util.dao.AbstractDAO;

public class ComponenteDAO extends AbstractDAO{

	public ComponenteDAO(Connection conn) {
		super(conn);
	}
	
	
    public List<ComponenteVO> getComponentes(String ccObra) throws SQLException{

    	StringBuilder exec = new StringBuilder(Procedures.PROC_GERACAO_MOBILE).append("?,?");
        List<ComponenteVO> list = new ArrayList<ComponenteVO>();
        ComponenteVO vo = null;
        
        prepStmt = conn.prepareStatement(exec.toString());
        prepStmt.setString(1,ccObra);
        prepStmt.setString(2,"C");
        rs = prepStmt.executeQuery();

        while (rs.next()){
            vo =  new ComponenteVO(rs.getInt(1), rs.getString(2), rs.getInt(3));
            list.add(vo);
        }

        liberarRecursosBD();
        return list;
    }
}
