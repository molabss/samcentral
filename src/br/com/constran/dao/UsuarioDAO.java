package br.com.constran.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.constran.mobile.persistence.vo.imp.UsuarioVO;
import br.com.constran.model.carga.Usuario;
import br.com.constran.util.dao.AbstractDAO;

public class UsuarioDAO extends AbstractDAO{

	
	public UsuarioDAO(Connection conn) {
		super(conn);
	}
	
	
    public List<UsuarioVO> getUsuarios(String ccObra, String op) throws SQLException{ 

    	StringBuilder exec = new StringBuilder(Procedures.PROC_GERACAO_MOBILE).append("?,?");
        List<UsuarioVO> list = new ArrayList<UsuarioVO>();
        UsuarioVO vo = null;
        
        prepStmt = conn.prepareStatement(exec.toString());
        prepStmt.setString(1,ccObra);
        prepStmt.setString(2,op);
        rs = prepStmt.executeQuery();

        rsmd = rs.getMetaData();

        while (rs.next()){
        	
            vo =  new UsuarioVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));

            if(rsmd.getColumnCount() == 7) {
            	
                vo.setCcObra(rs.getString("ccobra"));
            } 
            else if(rsmd.getColumnCount() == 8) {
            	
                vo.setMatricula(rs.getString("matricula"));
                vo.setCcObra(rs.getString("ccobra"));
            } 
            else if(rsmd.getColumnCount() == 9) {
            	
                vo.setMatricula(rs.getString("matricula"));
                vo.setCcObra(rs.getString("ccobra"));
                vo.setQrcode(rs.getString("qrCode"));
            }

            list.add(vo);
        }

        liberarRecursosBD();

        return list;
    }
    
    public String compararLoginCadastrado(String login) throws SQLException{
    	
    	String select = "select login from usuarios where login = ?";
    	prepStmt = conn.prepareStatement(select);
    	prepStmt.setString(1, login);
    	rs = prepStmt.executeQuery();
    	
    	if(rs.next())
    		return rs.getString("login");
    	else
    		return "NAO_CADASTRADO";
    }
    
    
    public String obterEmailLogin(String login) throws SQLException{
    	
    	String select = "select email from usuarios where login = ?";
    	prepStmt = conn.prepareStatement(select);
    	prepStmt.setString(1, login);
    	rs = prepStmt.executeQuery();
    	
    	if(rs.next())
    		return rs.getString("email");
    	else
    		return "NENHUM_EMAIL_ENCONTRADO";
    }
    
    public String obterIDusuario(String login) throws SQLException{
    	
    	String select = "select idUsuario from usuarios where login = ?";
    	prepStmt = conn.prepareStatement(select);
    	prepStmt.setString(1, login);
    	rs = prepStmt.executeQuery();
    	
    	if(rs.next())
    		return rs.getString("idUsuario");
    	else
    		return "-1";
    	
    }
    
    public Usuario usuarioAutentico(String login, String senha) throws SQLException{
    	
    	StringBuilder select = new StringBuilder();
    	select.append("select u.idUsuario,u.nome,ob.ccObra,sc.cargaFinalizada,u.login,u.grupo,u.senha from usuarios u ");
    	select.append("inner join obrasUsuarios ou ");
    	select.append("on u.idUsuario = ou.idUsuario ");
    	select.append("inner join obras ob ");
    	select.append("on ou.ccObra = ob.ccObra ");
    	select.append("inner join situacaoCargaSistema sc ");
    	select.append("on sc.ccObra = ob.ccObra ");
    	select.append("where u.login = ? and PWDCOMPARE(?, senha) = 1 and ou.cargaSAM = ?");
    	
    	prepStmt = conn.prepareStatement(select.toString());
    	prepStmt.setString(1,login);
    	prepStmt.setString(2,senha);
    	prepStmt.setString(3,"S");
    	
    	rs = prepStmt.executeQuery();
    	
    	if(rs.next()){
   		
    		return new Usuario(
	    				 rs.getInt("idUsuario")
	    				,rs.getString("nome")
	    				,rs.getString("login")
	    				,"*****"
	    				,rs.getString("grupo")
	    				,rs.getString("ccObra")
	    				,rs.getString("cargaFinalizada")
    				);
    	}else{
    		return null;
    	}
    }
    
    public void encerrarCarga(String ccObra) throws SQLException{
    	prepStmt = conn.prepareStatement("update situacaoCargaSistema set cargaFinalizada = 'S' where ccObra = ?");
    	prepStmt.setString(1, ccObra);
    	prepStmt.executeUpdate();
    	liberarRecursosBD();
    }
}
