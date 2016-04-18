package br.com.constran.util;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

/**
 * Classe com m�todos utilit�rios para gerar relat�rios.
 *
 * @author Igor de Almeida
 */
public class ReportUtils {

	/**
	 * Gera o relat�rio em PDF / XLS a partir de uma conex�o de banco de dados.
	 *
	 * @param inputStream InputStream que cont�m o relat�rio.
	 * @param parametros Par�metros utilizados pelo relat�rio.
	 * @param conexao Conex�o utilizada para a execu��o da query.
	 * @param tipo PDF ou XLS.
	 * @param response HttpServletResponse que ser� usado como base para gerar o relat�rio.
	 * @throws JRException Caso ocorra algum problema na gera��o do relat�rio.
	 * @throws IOException Caso ocorra algum problema na obten��o do
	 * OutputStream.
	 */

	public static void createReport(
			InputStream inputStream,
			Map<String, Object> parametros,
			Connection conexao,
			String tipo,
			HttpServletResponse response ) throws JRException, IOException {

		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros, conexao );

		OutputStream out = response.getOutputStream();

		if(tipo.equals("PDF")){

			response.setContentType( "application/pdf" );

			out = response.getOutputStream();

			JRExporter exporter = new JRPdfExporter();

			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint );
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out );

			exporter.exportReport();

		}else{

			response.setContentType("application/vnd.ms-excel");   

			out = response.getOutputStream();

			JRExporter exporter = new JRXlsExporter();

			exporter.setParameter(JRExporterParameter.JASPER_PRINT,jasperPrint );
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
			exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);  
			exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);  
			exporter.setParameter(JRXlsExporterParameter.MAXIMUM_ROWS_PER_SHEET,Integer.decode("65000"));  
			exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);  
			exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,Boolean.FALSE);  
			
			exporter.exportReport();

			out.close();
		}

	}

	public static void createReport(
			InputStream inputStream,
			Map<String, Object> parametros,
			JRBeanCollectionDataSource dataSource,
			HttpServletResponse response ) throws JRException, IOException {

		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros, dataSource);

		OutputStream out = response.getOutputStream();

		response.setContentType( "application/pdf" );

		out = response.getOutputStream();

		JRExporter exporter = new JRPdfExporter();

		exporter.setParameter( JRExporterParameter.JASPER_PRINT, jasperPrint );
		exporter.setParameter( JRExporterParameter.OUTPUT_STREAM, out );

		exporter.exportReport();

		out.close();

	}


	/*
    public static void createReport(InputStream inputStream,Map<String, Object> parametros,List<CVCqrCodeVO> listaQRcodes,String tipo,
            HttpServletResponse response ) throws JRException, IOException {

        JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros, new JRBeanCollectionDataSource(listaQRcodes));

        OutputStream out = response.getOutputStream();

        response.setContentType( "application/pdf" );

        out = response.getOutputStream();

        JRExporter exporter = new JRPdfExporter();

        exporter.setParameter( JRExporterParameter.JASPER_PRINT, jasperPrint );
        exporter.setParameter( JRExporterParameter.OUTPUT_STREAM, out );

        exporter.exportReport();

        out.close();
    }
    */

}