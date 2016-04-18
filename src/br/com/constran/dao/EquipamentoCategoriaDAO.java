package br.com.constran.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.constran.mobile.persistence.vo.EquipamentoCategoriaVO;
import br.com.constran.util.dao.AbstractDAO;

public class EquipamentoCategoriaDAO extends AbstractDAO {

	public EquipamentoCategoriaDAO(Connection conn) {
		super(conn);
	}

	
    public List<EquipamentoCategoriaVO> getEquipamentoCategorias(String ccObra) throws SQLException {

    	StringBuilder exec = new StringBuilder(Procedures.PROC_GERACAO_MOBILE).append("?,?");
        List<EquipamentoCategoriaVO> list = new ArrayList<EquipamentoCategoriaVO>();
        EquipamentoCategoriaVO vo = null;
        
        prepStmt = conn.prepareStatement(exec.toString());
        prepStmt.setString(1,ccObra);
        prepStmt.setString(2,"CE");
        rs = prepStmt.executeQuery();

        while (rs.next()) {
        	vo =  new EquipamentoCategoriaVO();
            vo.setId(rs.getInt("idCategoria"));
            vo.setDescricao(rs.getString("descricao"));
            list.add(vo);
        }

        liberarRecursosBD();
        return list;
    }
}
