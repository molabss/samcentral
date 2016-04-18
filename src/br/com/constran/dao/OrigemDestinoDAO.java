package br.com.constran.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.constran.mobile.persistence.vo.imp.OrigemDestinoVO;
import br.com.constran.util.dao.AbstractDAO;

public class OrigemDestinoDAO extends AbstractDAO{

	public OrigemDestinoDAO(Connection conn) {
		super(conn);
	}
	
    public List<OrigemDestinoVO> getOrigensDestinos(String ccObra) throws SQLException{

       List<OrigemDestinoVO> list = new ArrayList<OrigemDestinoVO>();
       OrigemDestinoVO vo = null;

 	   prepStmt = conn.prepareStatement("EXEC geracaoArquivoMobile ?,?");
 	   prepStmt.setString(1,ccObra);
 	   prepStmt.setString(2,"O");
 	   rs = prepStmt.executeQuery();

        while (rs.next()){
            vo =  new OrigemDestinoVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6));
            list.add(vo);
        }

        liberarRecursosBD();
        return list;
    }
	

}
