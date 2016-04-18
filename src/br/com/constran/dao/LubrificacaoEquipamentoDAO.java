package br.com.constran.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.constran.mobile.persistence.vo.imp.LubrificacaoEquipamentoVO;
import br.com.constran.util.dao.AbstractDAO;


public class LubrificacaoEquipamentoDAO extends AbstractDAO {

	public LubrificacaoEquipamentoDAO(Connection conn) {
		super(conn);
	}
	
	
    public List<LubrificacaoEquipamentoVO> getLubrificacaoEquipamento(String ccObra) throws SQLException{

    	StringBuilder exec = new StringBuilder(Procedures.PROC_GERACAO_MOBILE).append("?,?");
        List<LubrificacaoEquipamentoVO> list = new ArrayList<LubrificacaoEquipamentoVO>();
        LubrificacaoEquipamentoVO vo = null;
        
        prepStmt = conn.prepareStatement(exec.toString());
        prepStmt.setString(1,ccObra);
        prepStmt.setString(2,"LE");
        rs = prepStmt.executeQuery();

        while (rs.next()){
            vo =  new LubrificacaoEquipamentoVO(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6));
            list.add(vo);
        }

        liberarRecursosBD();
        return list;
    }
}
