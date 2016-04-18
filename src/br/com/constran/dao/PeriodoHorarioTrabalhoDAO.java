package br.com.constran.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.constran.mobile.persistence.vo.aprop.maodeobra.HorarioTrabalhoVO;
import br.com.constran.mobile.persistence.vo.aprop.maodeobra.PeriodoHorarioTrabalhoVO;
import br.com.constran.util.dao.AbstractDAO;

public class PeriodoHorarioTrabalhoDAO extends AbstractDAO {

	public PeriodoHorarioTrabalhoDAO(Connection conn) {
		super(conn);
	}
	
	
    public List<PeriodoHorarioTrabalhoVO> getPeriodosHorarioTrabalho(String ccObra) throws SQLException {
        
    	StringBuilder exec = new StringBuilder(Procedures.PROC_GERACAO_MOBILE).append("?,?");
    	List<PeriodoHorarioTrabalhoVO> list = new ArrayList<PeriodoHorarioTrabalhoVO>();
    	PeriodoHorarioTrabalhoVO vo = null;
    	
        prepStmt = conn.prepareStatement(exec.toString());
        prepStmt.setString(1,ccObra);
        prepStmt.setString(2,"PH");
        rs = prepStmt.executeQuery();

        while (rs.next()) {
        	vo = new PeriodoHorarioTrabalhoVO();
            vo.setHorario(new HorarioTrabalhoVO(rs.getInt("horario")));
            vo.setDiaSemana(rs.getString("diaSemana"));
            vo.setHoraInicio(rs.getString("horaInicio"));
            vo.setHoraTermino(rs.getString("horaTermino"));
            vo.setProdutivo(rs.getString("produtivo"));
            vo.setCobraHoraExtra(rs.getString("cobraHoraExtra"));
            vo.setCodigoParalisacao(rs.getString("codigoParalisacao"));
            list.add(vo);
        }

        liberarRecursosBD();
        return list;
    }
}
