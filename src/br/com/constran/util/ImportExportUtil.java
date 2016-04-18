package br.com.constran.util;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.com.constran.exception.IncorrectNameFileException;

/**
 * Created by mateus_vitali on 31/10/2014.
 * 


	improved by:
		
	{
	 }   
	\=/>
	MOISES SANTANA moises.santana@contran.com.br - 23/10/2015
 *
 * 
 */


public class ImportExportUtil {

    private static final String TYPE_FILE = ".json";
    private static final String SEPARATOR = "_";
    private static final SimpleDateFormat fileNameDateFormat = new SimpleDateFormat("ddMMyyyy");
    private static final SimpleDateFormat foldersFormat = new SimpleDateFormat("yyyy/MM/dd");
    
    
    
    public static String getFoldersByDate(String date) {
    	
        DateFormat formatter ;
        Date d = new Date();
        formatter = new SimpleDateFormat("dd/MM/yyyy");

        try {
            d = formatter.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return foldersFormat.format(d);
    }

    
    
    public static String getFileExportFormated(String idObra, String dispositivo, String date) {
    	
        DateFormat formatter;
        Date d = new Date();
        formatter = new SimpleDateFormat("dd/MM/yyyy");
        String hourMinSec = new SimpleDateFormat("HHmmss").format(d);

        try {
            d = formatter.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return idObra + SEPARATOR + fileNameDateFormat.format(d) + SEPARATOR + hourMinSec + SEPARATOR +  dispositivo + TYPE_FILE;
    }

    
    /*
    public static String getFileExportFormatedBKP(String idObra, String dispositivo, String date) {
    	
        DateFormat formatter;
        Date d = new Date();
        formatter = new SimpleDateFormat("dd/MM/yyyy");
        String hourMinSec = new SimpleDateFormat("HHmmss").format(d);
        
        try {
            d = formatter.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return idObra + SEPARATOR + fileNameDateFormat.format(d) + SEPARATOR + hourMinSec + SEPARATOR + dispositivo + TYPE_FILE;
    }
    */
    
    
   // public static void main(String[] args) {
    	
    	 //System.out.println();
	//}
    
    
    
    /**
     * Created by Moises Santana on 09/06/2015.
     *
     */
    
    
    //public static void main(String[] args) {
		//System.out.println("ABS_303599_05102015_094536_T33.json".replace("2nd_", ""));
	//}
    
    public static String getFileParticularDirectory(String nmFile) throws IncorrectNameFileException {

    	StringBuilder part = new StringBuilder();
    	
    	String prefix_file = null;
    	String dirObra = null;
    	String dirYear = null;
    	String dirMonthOfYear = null;
    	String dirDayOfMonth = null;
    	String deviceName = null;
    	String hourmMinSec= null;
    	String deviceNumber = null;
    	String fileExt = null;
    	
    	try{
    		
    		//if(nmFile.split("_")[3].equals("2nd")){
    			//nmFile = nmFile.replace("2nd_", "");
    		//}

    		prefix_file    = nmFile.split(SEPARATOR)[0];
	    	dirObra 	   = nmFile.split(SEPARATOR)[1];
	    	dirYear 	   = nmFile.split(SEPARATOR)[2].substring(4,8);
	    	dirMonthOfYear = nmFile.split(SEPARATOR)[2].substring(2,4);
	    	dirDayOfMonth  = nmFile.split(SEPARATOR)[2].substring(0,2);
    		hourmMinSec	   = nmFile.split(SEPARATOR)[3];
	    	deviceName 	   = nmFile.split(SEPARATOR)[4].substring(0,3);
	    	deviceNumber   = nmFile.split(SEPARATOR)[4].substring(1,3);
	    	
	      /*dirObra 	   = nmFile.split(SEPARATOR)[0];
	    	dirYear 	   = nmFile.split(SEPARATOR)[1].substring(4,8);
	    	dirMonthOfYear = nmFile.split(SEPARATOR)[1].substring(2,4);
	    	dirDayOfMonth  = nmFile.split(SEPARATOR)[1].substring(0,2);
    		deviceName 	   = nmFile.split(SEPARATOR)[2].substring(0,3);
	    	deviceNumber   = nmFile.split(SEPARATOR)[2].substring(1,3);*/
	    	
	    	
	    	fileExt 	   = nmFile.substring(nmFile.length()-4, nmFile.length());
   	
	    	if( !(TYPE_FILE.substring(1,TYPE_FILE.length()).equals(fileExt))) {
	    		
	    		throw new IncorrectNameFileException("Extensão do arquivo é inválida. Extensão deve ser "+TYPE_FILE+".");
	    	}
	    	
	    	if( !deviceName.contains("T")) {
	    		
	    		throw new IncorrectNameFileException("Nome do dispositivo é inválido.");
	    		
	    	}
	    	
	    	if( !(Integer.valueOf(deviceNumber) > 0 && Integer.valueOf(deviceNumber) <= 99)) {
	    		
	    		throw new IncorrectNameFileException("Nome do dispositivo é inválido. Verifique numeração.");
	    	}	    	
	    	
	    	if(dirObra.length() != 6) {
	    		
	    		throw new IncorrectNameFileException("Centro de custo da obra precisa ter 6 caracteres.");
	    	}
	    	
	    	if(Integer.valueOf(dirYear) > Calendar.getInstance().get(Calendar.YEAR)) {
	    		
	    		throw new IncorrectNameFileException("O ano indentificado no nome do arquivo é maior do que o ano atual.");
	    	}
	    	
	    	if( !(Integer.valueOf(dirMonthOfYear) >= 1 && Integer.valueOf(dirMonthOfYear) <= 12)) {
	    		
	    		throw new IncorrectNameFileException("Mês inválido.");
	    	}
	    	
	    	if(Integer.valueOf(dirDayOfMonth) > 31) {
	    		
	    		throw new IncorrectNameFileException("Dia do mês inválido.");
	    	}    	
    	
    	} catch (NumberFormatException e) {
    		
    		throw new IncorrectNameFileException("Algum caracter é inválido para conversão em ano ou mês ou dia ou número do nome do dispositivo.");
    	
    	} catch (IndexOutOfBoundsException e) {
    		
    		throw new IncorrectNameFileException("Nome do arquivo não contém a quantidade correta de caracteres.");
    		
    	} catch (NullPointerException e) {
    		
    		throw new IncorrectNameFileException("Conjunto de caracteres para nome do arquivo é inválido");
    	}
    	
    	return part.append(File.separator)
    			
    			.append(dirObra)
    			.append(File.separator)
       			.append("web")
    			.append(File.separator)
    			.append(dirYear)
    			.append(File.separator)
    			.append(dirMonthOfYear)
    			.append(File.separator)
    			.append(dirDayOfMonth)
    			.append(File.separator).toString();
    }
}
