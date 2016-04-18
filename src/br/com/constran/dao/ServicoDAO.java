package br.com.constran.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.constran.mobile.persistence.vo.imp.ServicoVO;
import br.com.constran.util.dao.AbstractDAO;

public class ServicoDAO extends AbstractDAO {

	public ServicoDAO(Connection conn) {
		super(conn);
	}
	
    public List<ServicoVO> getServicos(String ccObra) throws SQLException{

    	StringBuilder exec = new StringBuilder(Procedures.PROC_GERACAO_MOBILE).append("?,?");
        List<ServicoVO> list = new ArrayList<ServicoVO>();
        ServicoVO servicoVO = null;
        String tipo;
        
        prepStmt = conn.prepareStatement(exec.toString());
        prepStmt.setString(1,ccObra);
        prepStmt.setString(2,"S");
        rs = prepStmt.executeQuery();

        while(rs.next()){
            servicoVO = new ServicoVO(
                    rs.getInt("idServico"),
                    rs.getString("descricao"),
                    rs.getInt("categoria")
            );

            tipo = rs.getString("tipo").trim();

            if(tipo != null && tipo.length() > 0) servicoVO.setTipoServico(Integer.parseInt(rs.getString("tipo")));

            servicoVO.setUnidadeMedida(rs.getString("unidadeMedida"));
            list.add(servicoVO);
        }
        
        liberarRecursosBD();
        return list;
    }

}
