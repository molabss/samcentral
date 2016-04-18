package br.com.constran.controller;

import java.io.File;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.constran.mobile.persistence.vo.imp.json.ExportMobileDate;
import br.com.constran.system.AppDirectory;
import br.com.constran.util.FileUtil;
import br.com.constran.util.ServerResponse;
import br.com.constran.util.ImportExportUtil;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;


@Path("exporta")
public class Exportador {

	
	//RECEBENDO DADOS ENVIADOS PELO TABLET EM FORMATO 
	//JSON E SALVANDO COMO ARQUIVO PARA POSTERIOR PROCESSAMENTO
	
	@POST
	@Path("/arquivoParaServidor")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response exportarArquivoParaServidor(String json){
		
		Response response = null;
		boolean dadosArmazenados = false;
		boolean diretorioFoiCriado = true;
		ExportMobileDate exmd = null;
		String salvarEm = null;
		String nomeArquivo = null;
		
		try {
			Gson gJson = new Gson();
			exmd = gJson.fromJson(json, ExportMobileDate.class);
		}
		catch (JsonParseException e) {
			response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ServerResponse("Não foi possível desserializar os dados enviados.\n "+e.getMessage())).build();
			return response;
		}
        
		salvarEm = AppDirectory.DIR_ARQUIVO_EXPORTADO;
		salvarEm = salvarEm.concat(exmd.getCcObra()).concat("/web/").concat(ImportExportUtil.getFoldersByDate(exmd.getDate()));
        File file = new File(salvarEm);
       
        if (FileUtil.arquivoNAOexiste(file) && FileUtil.diretorioInvalido(file)) {
        	
        	diretorioFoiCriado = file.mkdir();
        	
            if(!diretorioFoiCriado) {
    			response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ServerResponse("Não foi possível criar um diretório para armezear os dados enviados.")).build();
    			return response;        	
            }        	
        }
        
        
        nomeArquivo = ImportExportUtil.getFileExportFormated(exmd.getCcObra(), exmd.getDispositivo(), exmd.getDate());
        
        nomeArquivo = getTipoArquivoModulo(exmd).concat(nomeArquivo);//DETERMINANDO DE QUAL MODULO SAO OS DADOS NESTE ARQUIVO
        
        dadosArmazenados = FileUtil.save(exmd, salvarEm, nomeArquivo);
        
        if(!dadosArmazenados) {
			response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ServerResponse("Não foi possível armazenar os dados enviados.")).build();
			return response;         	
        }
         
        response = Response.status(Response.Status.OK).entity(new ServerResponse("Arquivo enviado com sucesso!")).build();
		return response;
	}
	
	
	private String getTipoArquivoModulo(ExportMobileDate dataFile){
		
		String prefixo = "";
		
		if(dataFile.getApropriacoes() != null && dataFile.getApropriacoes().size() > 0){
			
			prefixo = "APR_";
			
		}else if( (dataFile.getRaes() != null && dataFile.getRaes().size() > 0) || (dataFile.getSaldoPosto() != null &&  dataFile.getSaldoPosto().getSaldos().size() > 0) ){
			
			prefixo = "ABS_";
			
		}else if(dataFile.getManutencaoEquipamentos() != null && dataFile.getManutencaoEquipamentos().size() > 0){
			
			prefixo = "MAN_";
			
		}else{
			
			//QUANDO NAO FOI POSSIVEL DETERMINAR DE QUE MODULO OS DADOS NO ARQUIVO PERTENCEM
			prefixo = "NDA_"; 
		}
		
		return prefixo;
	}	
	
}