package br.com.constran.controller;
	
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import br.com.constran.dao.ReportDAO;
import br.com.constran.model.reports.ReportVo;
import br.com.constran.util.AbstractController;
import br.com.constran.util.ReportUtils;
	
	
@Path("relatorios")
public class RelatorioExport extends AbstractController {
	
	
	@Context
	HttpServletRequest request;
	
	
	@Context
	HttpServletResponse response;
	
	
	@GET
	@Path("/requisicaoTotvs")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void requisicaoTotvs(@QueryParam("ccObra")String ccObra, @QueryParam("dataInicio")String dataInicio, @QueryParam("dataTermino")String dataTermino) {
		
		
		ReportVo report = null;
		Connection conn2 = null;
		
		response.setContentType("text/html");
		PrintWriter writer = null;
		
		try {
			
			if( (ccObra == null || ccObra.length() == 0) ||	(dataInicio == null || dataInicio.length() == 0 || dataTermino == null || dataTermino.length() == 0)){

				writer = response.getWriter();
		
				writer.print("<!DOCTYPE html>");
					writer.print("<title>Page Title</title>");
						writer.print("<html>");
						writer.print("<style>body {font-family: \"Calibri\", Times, serif;}</style>");
							writer.print("<body>");
								writer.print("<h1>Relatório de Requisição de Materiais</h1>");
								writer.print("<h2>Por favor, forneceça todas as seguintes informações:</h2>");
								writer.print("<form method=\"get\" action=\"requisicaoTotvs\">");
									writer.print("<label>Obra</label><br/>");
									writer.print("<input type=\"text\" name=\"ccObra\" required=\"required\"><br/><br/>");
									writer.print("<label>Data Início</label><br/>");
									writer.print("<input type=\"text\" name=\"dataInicio\" required=\"required\"><br/><br/>");
									writer.print("<label>Data Término</label><br/>");
									writer.print("<input type=\"text\" name=\"dataTermino\" required=\"required\"><br/><br/>");
									writer.print("<button type=\"submit\">Enviar</button>");
								writer.print("</form>");
							writer.print("</body>");
				writer.print("</html>");
				writer.close();
				return;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
			
		
		try {
			
			Map<String, Object> parametros = new HashMap<String, Object>();
			abreConexaoBD();

			String path = (System.getProperty("os.name").contains("Windows")) ? "C:/Reports/" : "/opt/tomcat7/webapps/Reports/";
			
			parametros.put("ccObra", ccObra);
			parametros.put("dataInicio", dataInicio);
			parametros.put("dataTermino", dataTermino);
			
			ReportDAO dao = new ReportDAO(conn);
			report = dao.getDadosReport("96");
			
			File filePath = new File(path);
			File file = new File(filePath, report.getNomeArquivo());
			
			InputStream in = new FileInputStream(file);
		
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			conn2 = DriverManager.getConnection("jdbc:jtds:sqlserver://constran16;DatabaseName=Constran","sa","S@tg3r!41");
			ReportUtils.createReport(in, parametros, conn2, report.getTipo(), response);
			
		} catch (Exception e) {
			e.printStackTrace();

		}finally{
			try {conn2.close();} catch (SQLException e) {e.printStackTrace();}
			fechaConexaoBD();
		}
	}
	
	
	
	
	public static void main(String[] args) {
		
        //int qtd = 0;

        try{
            //qtd  = Integer.parseInt("7500.");
        } catch (NumberFormatException e){

            if("7500.".contains(".")){
                //qtd = Integer.parseInt(("7500.00".split(".")[0]));
            }
        };
        System.out.println( "7500.".replaceAll("\\.0*$", ""));
	}
}
