package br.com.constran.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.constran.mobile.persistence.vo.imp.AtividadeVO;
import br.com.constran.util.dao.AbstractDAO;



public class AtividadeDAO extends AbstractDAO {

	public AtividadeDAO(Connection conn) {
		super(conn);
	}
	
    public List<AtividadeVO> getAtividades(String ccObra) throws SQLException {

    	StringBuilder exec = new StringBuilder(Procedures.PROC_GERACAO_MOBILE).append("?,?");
        List<AtividadeVO> list = new ArrayList<AtividadeVO>();
        AtividadeVO vo = null;
        
        prepStmt = conn.prepareStatement(exec.toString());
        prepStmt.setString(1,ccObra);
        prepStmt.setString(2,"A");
        rs = prepStmt.executeQuery();

        while (rs.next()){
            vo =  new AtividadeVO(rs.getInt(2), rs.getInt(1), rs.getString(3));
            list.add(vo);
        }

        liberarRecursosBD();
        return list;
    }
}
