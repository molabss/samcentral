package br.com.constran.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.constran.mobile.persistence.vo.rae.abs.JustificativaOperadorVO;
import br.com.constran.util.dao.AbstractDAO;

public class JustificativaOperadorDAO extends AbstractDAO {

	public JustificativaOperadorDAO(Connection conn) {
		super(conn);
	}
	
	
    public List<JustificativaOperadorVO> getJustificativaOperador(String ccObra) throws SQLException {

    	StringBuilder exec = new StringBuilder(Procedures.PROC_GERACAO_MOBILE).append("?,?");
        List<JustificativaOperadorVO> list = new ArrayList<JustificativaOperadorVO>();
        JustificativaOperadorVO vo = null;

        prepStmt = conn.prepareStatement(exec.toString());
        prepStmt.setString(1,ccObra);
        prepStmt.setString(2,"J");
        rs = prepStmt.executeQuery();

        while (rs.next()){
            vo =  new JustificativaOperadorVO(rs.getInt(1), rs.getString(2));
            list.add(vo);
        }

        liberarRecursosBD();
        return list;
    }

}
