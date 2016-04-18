package br.com.constran.util.dao;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class IniciaDS implements ServletContextListener {


	
    public IniciaDS() {

    }
    
    
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		
		ConnectionFactory.init();
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}
	
	

	/*
	public static void limpaTudo(){
		
		
		try {
			
			Connection conn = ConnectionFactory.getConnection();
		
		
			conn.prepareStatement("delete from insereApropriacao").executeUpdate();
			conn.prepareStatement("delete from insereApropriacaoMovimentacao").executeUpdate();
			conn.prepareStatement("delete from insereApropriacaoMovimentacaoViagens").executeUpdate();
			conn.prepareStatement("delete from insereApropriacaoParteDiariaEquipamentos").executeUpdate();
			conn.prepareStatement("delete from insereApropriacaoParteDiariaEquipamentosEventos").executeUpdate();
			conn.prepareStatement("delete from insereApropriacaoServico").executeUpdate();
			conn.prepareStatement("delete from insereApropriacaoMaoDeObra").executeUpdate();
			conn.prepareStatement("delete from insereApropriacaoParalisacaoEquipe").executeUpdate();
			conn.prepareStatement("delete from insereApropriacaoParalisacaoMaoDeObra").executeUpdate();
			conn.prepareStatement("delete from insereRAE").executeUpdate();
			conn.prepareStatement("delete from insereAbastecimento").executeUpdate();
			conn.prepareStatement("delete from insereLubrificacaoDetalhe").executeUpdate();
			conn.prepareStatement("delete from insereSaldoPosto").executeUpdate();
			conn.prepareStatement("delete from insereManutencaoEquipamentos2").executeUpdate();
			conn.prepareStatement("delete from insereManutencoesServicosRealizados2").executeUpdate();
			
			ConnectionFactory.closeConnection(conn);
			
		} catch (SQLException e) {

			e.printStackTrace();
		}		
	}
	*/
	
}
