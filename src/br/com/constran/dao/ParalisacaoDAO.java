package br.com.constran.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.constran.mobile.persistence.vo.imp.ParalisacaoVO;
import br.com.constran.util.dao.AbstractDAO;

public class ParalisacaoDAO extends AbstractDAO {

	public ParalisacaoDAO(Connection conn) {
		super(conn);
	}
	
	
    public List<ParalisacaoVO> getParalisacoes(String ccObra) throws SQLException{

    	StringBuilder exec = new StringBuilder(Procedures.PROC_GERACAO_MOBILE).append("?,?");
        List<ParalisacaoVO> list = new ArrayList<ParalisacaoVO>();
        ParalisacaoVO vo = null;
        
        prepStmt = conn.prepareStatement(exec.toString());
        prepStmt.setString(1,ccObra);
        prepStmt.setString(2,"P");
        rs = prepStmt.executeQuery();
        rsmd = rs.getMetaData();

        while (rs.next()){
            vo =  new ParalisacaoVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(1));

            if(rsmd.getColumnCount() > 3) {
                vo.setAplicacao(rs.getString("aplicacao"));
            }

            list.add(vo);
        }

       liberarRecursosBD();
       return list;
    }
}
