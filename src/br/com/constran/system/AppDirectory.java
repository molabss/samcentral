package br.com.constran.system;

import java.io.File;


/*
	{
	 }   
	\=/>
	MOISES SANTANA moises.santana@contran.com.br - 09/06/2015
*/


public class AppDirectory {
	
	private static final String SEP = File.separator;
	
	public static final String DIR_ARQUIVO_EXPORTADO = (System.getProperty("os.name").contains("Windows")) 
			
			? "C:"+SEP+"tomcat7"+SEP+"webapps"+SEP+"Files"+SEP
			
			: SEP+"opt"+SEP+"tomcat7"+SEP+"webapps"+SEP+"Files"+SEP;
	
	
	
	public static final String DIR_IMG_ASSINATURA = (System.getProperty("os.name").contains("Windows")) 
			
			? "C:"+SEP+"tomcat7"+SEP+"webapps"+SEP+"samcentral"+SEP+"img"+SEP+"logo_assinatura_email.png"
			
			: SEP+"opt"+SEP+"tomcat7"+SEP+"webapps"+SEP+"samcentral"+SEP+"img"+SEP+"logo_assinatura_email.png";

}


