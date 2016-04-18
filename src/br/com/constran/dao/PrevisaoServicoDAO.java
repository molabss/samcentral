package br.com.constran.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.constran.mobile.persistence.vo.ObraVO;
import br.com.constran.mobile.persistence.vo.aprop.PrevisaoServicoVO;
import br.com.constran.mobile.persistence.vo.imp.AtividadeVO;
import br.com.constran.mobile.persistence.vo.imp.FrenteObraVO;
import br.com.constran.mobile.persistence.vo.imp.ServicoVO;
import br.com.constran.util.dao.AbstractDAO;

public class PrevisaoServicoDAO extends AbstractDAO {

	public PrevisaoServicoDAO(Connection conn) {
		super(conn);
	}
	
    public List<PrevisaoServicoVO> getPrevisaoServicos(String ccObra) throws SQLException {
        
    	StringBuilder exec = new StringBuilder(Procedures.PROC_GERACAO_MOBILE).append("?,?");
    	List<PrevisaoServicoVO> list = new ArrayList<PrevisaoServicoVO>();
    	PrevisaoServicoVO vo = null;
    	
        prepStmt = conn.prepareStatement(exec.toString());
        prepStmt.setString(1,ccObra);
        prepStmt.setString(2,"PS");
        rs = prepStmt.executeQuery();

        while (rs.next()) {
        	vo = new PrevisaoServicoVO();
            vo.setObra(new ObraVO(rs.getInt("ccObra")));
            vo.setFrenteObra(new FrenteObraVO(rs.getInt("frenteObra")));
            vo.setAtividade(new AtividadeVO(rs.getInt("atividade"), ""));
            vo.setServico(new ServicoVO(rs.getInt("servico")));
            list.add(vo);
        }

        liberarRecursosBD();
        return list;
    }
}
