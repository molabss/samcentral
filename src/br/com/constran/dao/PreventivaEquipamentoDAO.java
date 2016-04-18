package br.com.constran.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.constran.mobile.persistence.vo.imp.PreventivaEquipamentoVO;
import br.com.constran.util.dao.AbstractDAO;

public class PreventivaEquipamentoDAO extends AbstractDAO {

	public PreventivaEquipamentoDAO(Connection conn) {
		super(conn);
	}

    public List<PreventivaEquipamentoVO> getPreventivas(String ccObra) throws SQLException {

    	StringBuilder exec = new StringBuilder(Procedures.PROC_GERACAO_MOBILE).append("?,?");
        List<PreventivaEquipamentoVO> list = new ArrayList<PreventivaEquipamentoVO>();
        PreventivaEquipamentoVO vo = null;
        
        prepStmt = conn.prepareStatement(exec.toString());
        prepStmt.setString(1,ccObra);
        prepStmt.setString(2,"PV");
        rs = prepStmt.executeQuery();

        while (rs.next()){
            vo =  new PreventivaEquipamentoVO(rs.getInt(1), rs.getString(2), rs.getString(3));
            list.add(vo);
        }

        liberarRecursosBD();
        return list;
    }
}
