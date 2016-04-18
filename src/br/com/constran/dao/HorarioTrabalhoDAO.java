package br.com.constran.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.constran.mobile.persistence.vo.ObraVO;
import br.com.constran.mobile.persistence.vo.aprop.maodeobra.HorarioTrabalhoVO;
import br.com.constran.util.dao.AbstractDAO;

public class HorarioTrabalhoDAO extends AbstractDAO{

	public HorarioTrabalhoDAO(Connection conn) {
		super(conn);
	}
	
    public List<HorarioTrabalhoVO> getHorariosTrabalho(String ccObra) throws SQLException {
        
    	StringBuilder exec = new StringBuilder(Procedures.PROC_GERACAO_MOBILE).append("?,?");
    	List<HorarioTrabalhoVO> list = new ArrayList<HorarioTrabalhoVO>();
    	HorarioTrabalhoVO vo = null;

        prepStmt = conn.prepareStatement(exec.toString());
        prepStmt.setString(1,ccObra);
        prepStmt.setString(2,"HT");
        rs = prepStmt.executeQuery();

        while (rs.next()) {
        	vo = new HorarioTrabalhoVO();
            vo.setId(rs.getInt("idHorario"));
            vo.setObra(new ObraVO(rs.getInt("ccObra"), null));
            vo.setDescricao(rs.getString("descricao"));
            vo.setHoraInicio(rs.getString("horaInicio"));
            vo.setHoraTermino(rs.getString("horaTermino"));
            list.add(vo);
        }

        liberarRecursosBD();
        return list;
    }
}
