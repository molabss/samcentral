package br.com.constran.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.constran.mobile.persistence.vo.ObraVO;
import br.com.constran.util.dao.AbstractDAO;

public class ObraDAO extends AbstractDAO {

	public ObraDAO(Connection conn) {
		super(conn);
	}
	
	
    public List<ObraVO> getObras(String ccObra) throws SQLException{

        List<ObraVO> list = new ArrayList<ObraVO>();
        ObraVO obraVo = null;
        String usaCVC = "N";
        
        prepStmt = conn.prepareStatement("EXEC geracaoArquivoMobile ?,?");
        prepStmt.setString(1,ccObra);
        prepStmt.setString(2,"CC");
        rs = prepStmt.executeQuery();
        rsmd = rs.getMetaData();
        
        while (rs.next()){
        	
            obraVo = new ObraVO();

            //usaCVC = rs.getString("usaCVC");
            //if(usaCVC == null)usaCVC = "N";

            usaCVC = "N";
            
            obraVo.setUsaCVC(usaCVC);
            obraVo.setId(rs.getInt("ccObra"));
            obraVo.setExibirHorimetro(rs.getString("exibirHorimetro"));
            obraVo.setHorimetroObrigatorio(rs.getString("horimetroObrigatorio"));
            obraVo.setDescricao(rs.getString("nomeObra"));

            //manter compatibilidade com modulos antigos
            if(rsmd.getColumnCount() > 4) {
                obraVo.setUsaOrigemDestino(rs.getString("usaOrigemDestino"));
                obraVo.setUsaQRCode(rs.getString("usaQRCode"));
                obraVo.setUsaQRCodePessoal(rs.getString("usaQRCodePessoal"));
            }
            list.add(obraVo);
        }
        liberarRecursosBD();
        return list;
    }
    
    public boolean naoExisteObra(String ccObra) throws SQLException{
    	boolean naoExiste = true;
        prepStmt = conn.prepareStatement("SELECT ccObra from obras WHERE ccObra = ?");
        prepStmt.setString(1,ccObra);
        rs = prepStmt.executeQuery();
        if(rs.next()){
        	naoExiste = false;
        }
    	liberarRecursosBD();
    	return naoExiste;
    }
}
