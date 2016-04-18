package br.com.constran.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.constran.util.dao.ConnectionFactory;

public abstract class AbstractController {

	public Connection conn = null; 
	
	public List<String> erroMsg = new ArrayList<String>();
	
	public void isolarTransacao(int nivel) throws SQLException {
		conn.setTransactionIsolation(nivel);
	}
	
	public void abreConexaoBD() throws SQLException{
		conn = ConnectionFactory.getConnection();

	}
	
	public void fechaConexaoBD(){
		ConnectionFactory.closeConnection(conn);
	}

	
	public void desabilitarBDautoCommit() throws SQLException{
		conn.setAutoCommit(false);
	}
	
	public void habilitarBDautoCommit() throws SQLException{
		conn.setAutoCommit(true);
	}
	
	public void dbCommit() throws SQLException{
		System.out.println("Comitando dados inseridos na base....");
		conn.commit();
		System.out.println("Commit OK!");
	}
	
	
	public String getErroMsg(){
		
		StringBuilder msg = new StringBuilder();
		
		for(String m : erroMsg){
			
			msg.append(m);
			
			if( (erroMsg.indexOf(m) == erroMsg.lastIndexOf(m))) msg.append("\n");
		}
		
		System.out.println(msg.toString());
		
		return msg.toString();
		
	}
	
	
	public void dbRollback(){
		
		try {
			System.out.println("Realizando rollback de dados inseridos na base...");
			if(conn != null && !conn.isClosed()){
				conn.rollback();
				System.out.println("Rollback OK!");
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao realizar rollback: "+e.getMessage());
			e.printStackTrace();
		}
	}
}
