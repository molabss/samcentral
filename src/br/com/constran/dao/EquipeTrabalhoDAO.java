package br.com.constran.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.constran.mobile.persistence.vo.ObraVO;
import br.com.constran.mobile.persistence.vo.aprop.maodeobra.EquipeTrabalhoVO;
import br.com.constran.mobile.persistence.vo.aprop.maodeobra.HorarioTrabalhoVO;
import br.com.constran.mobile.persistence.vo.aprop.maodeobra.PessoalVO;
import br.com.constran.util.dao.AbstractDAO;

public class EquipeTrabalhoDAO extends AbstractDAO {

	public EquipeTrabalhoDAO(Connection conn) {
		super(conn);
	}
	
    public List<EquipeTrabalhoVO> getEquipesTrabalho(String ccObra) throws SQLException {
        
    	StringBuilder exec = new StringBuilder(Procedures.PROC_GERACAO_MOBILE).append("?,?");
    	List<EquipeTrabalhoVO> list = new ArrayList<EquipeTrabalhoVO>();
    	EquipeTrabalhoVO vo = null;
    	
        prepStmt = conn.prepareStatement(exec.toString());
        prepStmt.setString(1,ccObra);
        prepStmt.setString(2,"ET");
        rs = prepStmt.executeQuery();

        while (rs.next()) {
        	vo =  new EquipeTrabalhoVO();
            vo.setId(rs.getInt("idEquipe"));
            vo.setObra(new ObraVO(rs.getInt("ccObra"), null));
            vo.setNomeEquipe(rs.getString("nomeEquipe"));
            vo.setApelido(rs.getString("apelido"));
            vo.setFormacao(rs.getInt("formacao"));
            vo.setDataCriacao(rs.getString("dataCriacao"));
            vo.setResponsavel(new PessoalVO(rs.getInt("encarregado")));
            vo.setAtiva(rs.getString("ativa"));
            vo.setHorarioTrabalho(new HorarioTrabalhoVO(rs.getInt("horarioTrabalho")));
            vo.setApropriavel(rs.getString("apropriavel"));
            list.add(vo);
        }

        liberarRecursosBD();
        return list;
    }

}
