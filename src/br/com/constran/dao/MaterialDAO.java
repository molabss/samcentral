package br.com.constran.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.constran.mobile.persistence.vo.imp.MaterialVO;
import br.com.constran.util.dao.AbstractDAO;

public class MaterialDAO extends AbstractDAO{

	public MaterialDAO(Connection conn) {
		super(conn);
	}
	
	
    public List<MaterialVO> getMateriais(String ccObra) throws SQLException {
    	
    	StringBuilder exec = new StringBuilder(Procedures.PROC_GERACAO_MOBILE).append("?,?");
        List<MaterialVO> list = new ArrayList<MaterialVO>();
        MaterialVO vo = null;
        
        prepStmt = conn.prepareStatement(exec.toString());
        prepStmt.setString(1,ccObra);
        prepStmt.setString(2,"M");
        rs = prepStmt.executeQuery();

        while (rs.next()){
            vo =  new MaterialVO(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4));
            list.add(vo);
        }

        liberarRecursosBD();

        return list;
    }	
	
	
	
	

}
