package br.com.constran.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.output.FileWriterWithEncoding;

import br.com.constran.exception.IncorrectNameFileException;
import br.com.constran.mobile.persistence.vo.imp.json.ExportMobileDate;
import br.com.constran.model.ExportMobileDateReportWrapper;
import br.com.constran.system.AppDirectory;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

/**
 * Created by mateus_vitali on 30/10/2014.
 *
 *
 * Improved by moises_santana on 23/10/2015.
 */
public class FileUtil 
{
	
	private static final String SEP = File.separator;
	
    public static boolean save(ExportMobileDate objJSon, String pathFile, String nameFile) 
    {
        try 
        {
            //String ccObra = objJSon.getCcObra();
            //String date = objJSon.getDate();
            //String dispositivo = objJSon.getDispositivo();

            //objJSon.setCcObra(null);
            //objJSon.setDate(null);
            //objJSon.setDispositivo(null);
            
            

            Gson gson = new Gson();
            String strJson = gson.toJson(objJSon);

            File path = new File(pathFile);
            File file = new File(path, nameFile);

            /*
            if(arquivoJAexiste(file)) 
            {
            	
            	System.out.println("SIM ARQUIVO EXISTE!");
            	
                String strFile = FileUtils.readFileToString(file,"UTF-8");
                
                if(arquivosSaoDiferentes(strJson, strFile)) 
                {
                	
                	System.out.println("OS ARQUIVOS SAO DIFERENTES!");
                	
                    nameFile = ImportExportUtil.getFileExportFormatedBKP(ccObra,dispositivo,date);
                    file = new File(path, nameFile);
                } 
                else 
                {
                	
                	System.out.println("OS ARQUIVOS SAO IGUAIS");
                	
                    return true;
                }
            }
            */

            FileWriterWithEncoding fw = new FileWriterWithEncoding(file, "UTF-8");

            fw.write(strJson);
            //fw.write(Criptografia.produzirArquivo(strJson));
            fw.flush();
            fw.close();

            return true;
        } 
        catch (IOException e) 
        {
        	e.printStackTrace();
        	return false;
        }
    }

    public static boolean isEqualsFile(String file1, String file2) 
    {
        return file1.equalsIgnoreCase(file2);
    }
    
    public static boolean arquivosSaoDiferentes(String file1, String file2) 
    {
        return file1.equalsIgnoreCase(file2);
    }
    
    public static boolean arquivoJAexiste(File file){
    	return file.exists();
    }
    
    public static boolean arquivoNAOexiste(File file){
    	return !file.exists();
    }
    
    public static boolean diretorioInvalido(File file){
    	return !file.isDirectory();
    }
    
    
    public static List<ExportMobileDateReportWrapper> deserializeJsonFile(String nmsFiles, String pathFiles) throws IOException, JsonParseException, IncorrectNameFileException {
    	
    	//NOVO
    	List<ExportMobileDateReportWrapper> listaExmdCW = new ArrayList<ExportMobileDateReportWrapper>();
    	ExportMobileDateReportWrapper exmdCW = null;
    	//--------
    	
    	//List<ExportMobileDate> listaExmd = new ArrayList<ExportMobileDate>();
    	List<String>listNmFile = Arrays.asList(nmsFiles.split(";"));
    	
    	
    	StringBuilder strJsonContent = new StringBuilder();
    	ExportMobileDate exmd = null;
    	File fileINjsonFormat = null;
    	List<String> lines = null;
    	Gson gJson = null;
      	
    	
    	for(String nm : listNmFile) {
    		
    		strJsonContent.delete(0, strJsonContent.length());
  		
    		fileINjsonFormat = new File(pathFiles.concat(ImportExportUtil.getFileParticularDirectory(nm)), nm);
    		
    		lines =  FileUtils.readLines(fileINjsonFormat, "UTF-8");
    		
        	for(String line : lines){
        		strJsonContent.append(line);
        	}
        	
        	gJson = new Gson();
        	exmd = gJson.fromJson(strJsonContent.toString(), ExportMobileDate.class);
        	
        	//NOVO
        	exmdCW = new ExportMobileDateReportWrapper();
        	exmdCW.setExmd(exmd);
        	exmdCW.setNmArquivo(nm);
        	listaExmdCW.add(exmdCW);
        	//-----
        	
        	//listaExmd.add(exmd);
    	}
    	
    	return listaExmdCW;
    }
    
    
    
    
    public static ExportMobileDateReportWrapper deserializeSingleJsonFile(String nmFile, String pathFile) throws IOException, JsonParseException, IncorrectNameFileException {
    	

    	ExportMobileDateReportWrapper exmdCW = null;
    	
    	StringBuilder strJsonContent = new StringBuilder();
    	ExportMobileDate exmd = null;
    	File fileINjsonFormat = null;
    	List<String> lines = null;
    	Gson gJson = null;
   		
		strJsonContent.delete(0, strJsonContent.length());
	
		fileINjsonFormat = new File(pathFile.concat(ImportExportUtil.getFileParticularDirectory(nmFile)), nmFile);
		
		lines =  FileUtils.readLines(fileINjsonFormat, "UTF-8");
		
    	for(String line : lines){
    		strJsonContent.append(line);
    	}
    	
    	gJson = new Gson();
    	exmd = gJson.fromJson(strJsonContent.toString(), ExportMobileDate.class);
    	
    	//NOVO
    	exmdCW = new ExportMobileDateReportWrapper();
    	exmdCW.setExmd(exmd);
    	exmdCW.setNmArquivo(nmFile);
    	
    	return exmdCW;
    }    
    
    
    
    public File findANDgetSerializedFile() {
    	File file = null;
    	return file;
    }
    
   
    public static List<String> listarArquivosParaProcessar(String grupoUsuario, String ccObra, Integer dia, Integer mes, Integer ano) {

    	
    	String[] listarComSigla = null;
    	
    	List<String> arquivos = new ArrayList<String>();
    	
    	
    	if("admApropriacao".equals(grupoUsuario) || "apropriacao".equals(grupoUsuario)) {
    		
    		//LISTAR ARQUIVOS DE APROPRIACAO
    		listarComSigla = new String[]{"APR","*","*"};
    		
    	} else if("abastecimento".equals(grupoUsuario) || "admAbastecimento".equals(grupoUsuario)) {
    		
    		//LISTAR ARQUIVOS DE ABASTECIMENTO
    		listarComSigla = new String[]{"ABS","*","*"};
    		
    	} else if("administrador".equals(grupoUsuario)) {
    		
    		
    		//LISTAR TODOS OS ARQUIVOS
    		listarComSigla = new String[]{"ABS","APR","MAN"};
    	}
    	
    	
    	
    	String dir = AppDirectory.DIR_ARQUIVO_EXPORTADO + ccObra +SEP+ "web" +SEP+ ano +SEP+ (mes < 10 ? ("0".concat(mes.toString())) : mes) +SEP+ (dia < 10 ? ("0".concat(dia.toString())) : dia) +SEP;
    	File folder = new File(dir);
    	File[] listaDeArquivos = folder.listFiles();

    	    for (int i = 0; i < listaDeArquivos.length; i++) {
   	      
	    	      if (listaDeArquivos[i].isFile() 
	    	    		  && ((!listaDeArquivos[i].getName().contains("SUCESSO_") 
	    	    				  && !listaDeArquivos[i].getName().contains("FALHA_")))) {
	    	    	  
	    	        
	    	    	  if(listaDeArquivos[i].getName().contains(listarComSigla[0]) 
	    	    			  || listaDeArquivos[i].getName().contains(listarComSigla[1]) 
	    	    			  		|| listaDeArquivos[i].getName().contains(listarComSigla[2])) {
	    	    		  
	    	    		  
	    	    		  arquivos.add(listaDeArquivos[i].getName());
	    	    	  }
	    	      } 
    	    }    	
    	return arquivos;
    }
    
    
    public static void adicionarTextoNomeArquivo(String strAppend, String nmArquivo) throws IOException, IncorrectNameFileException {
    	
    	String particularDir = ImportExportUtil.getFileParticularDirectory(nmArquivo);
    	
    	particularDir = particularDir.substring(1, particularDir.length());
    	
    	System.out.println(AppDirectory.DIR_ARQUIVO_EXPORTADO+particularDir+strAppend+nmArquivo);
    	
        FileUtils.moveFile(
        	      FileUtils.getFile(AppDirectory.DIR_ARQUIVO_EXPORTADO+particularDir+nmArquivo),
        	      FileUtils.getFile(AppDirectory.DIR_ARQUIVO_EXPORTADO+particularDir+strAppend+nmArquivo)
        );    	
    }
    
    /*
    public static void main(String[] args) {
    	String fil = "abc";
    	List<String> listCa = Arrays.asList(fil.split(";"));
    	for(String a : listCa){
    		System.out.println(a);
    	}
    	StringBuilder string = new StringBuilder("abcdefg");
    	System.out.println(string);
    	string.delete(0, string.length());
    	System.out.println(string.toString().isEmpty());
    	System.out.println(string);
	}
	*/
	
}
