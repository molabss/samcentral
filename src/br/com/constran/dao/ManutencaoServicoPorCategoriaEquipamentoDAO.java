package br.com.constran.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.constran.mobile.persistence.vo.EquipamentoCategoriaVO;
import br.com.constran.mobile.persistence.vo.ManutencaoServicoPorCategoriaEquipamentoVO;
import br.com.constran.mobile.persistence.vo.ManutencaoServicosVO;
import br.com.constran.util.dao.AbstractDAO;

public class ManutencaoServicoPorCategoriaEquipamentoDAO extends AbstractDAO {

	public ManutencaoServicoPorCategoriaEquipamentoDAO(Connection conn) {
		super(conn);
	}
	
	
    public List<ManutencaoServicoPorCategoriaEquipamentoVO> getManutencaoServicosPorCategoriaEquipamento(String ccObra) throws SQLException {

    	StringBuilder exec = new StringBuilder(Procedures.PROC_GERACAO_MOBILE).append("?,?");
       List<ManutencaoServicoPorCategoriaEquipamentoVO> list = new ArrayList<ManutencaoServicoPorCategoriaEquipamentoVO>();

       ManutencaoServicosVO manutencaoServico = null;
       EquipamentoCategoriaVO equipamentoCategoria = null;
       ManutencaoServicoPorCategoriaEquipamentoVO vo = null;        
        
	   prepStmt = conn.prepareStatement(exec.toString());
	   prepStmt.setString(1,ccObra);
	   prepStmt.setString(2,"SM");
	   rs = prepStmt.executeQuery();

       while (rs.next()) {
        	
			vo = new ManutencaoServicoPorCategoriaEquipamentoVO();
			vo.setId(rs.getInt("idServicoCategoriaEquipamento"));
			
			manutencaoServico = new ManutencaoServicosVO();
			manutencaoServico.setId(rs.getInt("idManutencaoServico"));
			
			vo.setManutencaoServico(manutencaoServico);
			
			equipamentoCategoria = new EquipamentoCategoriaVO();
			equipamentoCategoria.setId(rs.getInt("idCategoriaEquipamento"));
			
			vo.setEquipamentoCategoria(equipamentoCategoria);
			
			list.add(vo);
        }

        liberarRecursosBD();
        return list;
    }
}
