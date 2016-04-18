package br.com.constran.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.constran.mobile.persistence.vo.aprop.maodeobra.EquipeTrabalhoVO;
import br.com.constran.mobile.persistence.vo.aprop.maodeobra.IntegranteEquipeVO;
import br.com.constran.mobile.persistence.vo.aprop.maodeobra.PessoalVO;
import br.com.constran.util.dao.AbstractDAO;

public class IntegranteEquipeDAO extends AbstractDAO {

	public IntegranteEquipeDAO(Connection conn) {
		super(conn);
	}
	
    public List<IntegranteEquipeVO> getIntegrantesEquipe(String ccObra) throws SQLException {
        
    	StringBuilder exec = new StringBuilder(Procedures.PROC_GERACAO_MOBILE).append("?,?");
    	List<IntegranteEquipeVO> list = new ArrayList<IntegranteEquipeVO>();
        IntegranteEquipeVO vo = null;
        
        prepStmt = conn.prepareStatement(exec.toString());
        prepStmt.setString(1,ccObra);
        prepStmt.setString(2,"IE");
        rs = prepStmt.executeQuery();

        while (rs.next()) {
        	vo = new IntegranteEquipeVO();
            vo.setEquipe(new EquipeTrabalhoVO(rs.getInt("equipe")));
            vo.setPessoa(new PessoalVO(rs.getInt("pessoa")));
            vo.setDataIngresso(rs.getString("dataIngresso"));
            vo.setDataSaida(rs.getString("dataSaida"));
            list.add(vo);
        }

        liberarRecursosBD();
        return list;
    }
}
