package br.com.constran.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.constran.mobile.persistence.vo.ManutencaoServicosVO;
import br.com.constran.util.dao.AbstractDAO;

public class ManutencaoServicoDAO extends AbstractDAO{

	public ManutencaoServicoDAO(Connection conn) {
		super(conn);
	}
	
    public List<ManutencaoServicosVO> getManutencaoServicos(String ccObra) throws SQLException {

    	StringBuilder exec = new StringBuilder(Procedures.PROC_GERACAO_MOBILE).append("?,?");
    	List<ManutencaoServicosVO> list = new ArrayList<ManutencaoServicosVO>();
        ManutencaoServicosVO vo = null;

        prepStmt = conn.prepareStatement(exec.toString());
        prepStmt.setString(1,ccObra);
        prepStmt.setString(2,"SC");
        rs = prepStmt.executeQuery();

        while (rs.next()) {
        	vo = new ManutencaoServicosVO();
            vo.setId(rs.getInt("idManutencaoServico"));
            vo.setDescricao(rs.getString("descricaoServico"));
            list.add(vo);
        }

        liberarRecursosBD();

        return list;
    }
	

}
