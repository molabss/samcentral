package br.com.constran.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.constran.mobile.persistence.vo.imp.FrenteObraVO;
import br.com.constran.util.dao.AbstractDAO;


public class FrenteObraDAO extends AbstractDAO{
	
    public FrenteObraDAO(Connection conn) {
		super(conn);
	}

   
   public List<FrenteObraVO> getFrentesObra(String ccObra) throws SQLException {

       List<FrenteObraVO> list = new ArrayList<FrenteObraVO>();
       FrenteObraVO vo = null;
       
       prepStmt = conn.prepareStatement("EXEC geracaoArquivoMobile ?,?");
       prepStmt.setString(1,ccObra);
       prepStmt.setString(2,"F");
       rs = prepStmt.executeQuery();
       
       while (rs.next()){
           vo =  new FrenteObraVO(rs.getInt(1), rs.getInt(3), rs.getString(2));
           list.add(vo);
       }

       liberarRecursosBD();
       return list;
   }
   

}
