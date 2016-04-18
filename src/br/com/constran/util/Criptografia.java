package br.com.constran.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import sun.misc.BASE64Encoder;
import br.com.constran.mobile.enums.TipoModulo;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;

public class Criptografia {

    private static final String TOKEN = "c0n5tran";
    private static final String SPLIT_TOKEN = ";";
	
    
	public static String produzirParaEquipamento(String etiquetaStr) {
		return criptografar(TOKEN + SPLIT_TOKEN + TipoModulo.EQUIPAMENTO + SPLIT_TOKEN + etiquetaStr);
	}
	
	public static String produzirParaMaoDeObra(String etiquetaStr) {
		return criptografar(TOKEN + SPLIT_TOKEN + TipoModulo.MAO_DE_OBRA + SPLIT_TOKEN + etiquetaStr);
	}
	
	public static String produzirParaFichaMotorista(String etiquetaStr) {
		return criptografar(TOKEN + SPLIT_TOKEN + TipoModulo.FICHA_MOTORISTA + SPLIT_TOKEN + etiquetaStr);
	}
	
	
	public static String produzirArquivo(String json) {
		return criptografar(TOKEN + SPLIT_TOKEN + TipoModulo.ARQUIVO_TABLET + SPLIT_TOKEN + json);
	}    
    
    public static final String criptografar(String chave) {
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(chave.getBytes());
    }
	
    
    
    public static String descriptografar(String dataCripto) {
        if (dataCripto != null) {
            try {
                byte[] data = Base64.decode(dataCripto);
                return new String(data,"UTF-8");
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }    
    
    
    
    public static String descriptografarArquivo(String path) throws IOException, Base64DecodingException {
        
		String arqConteudo = "";
		File f = new File(path);
		FileReader arq = new FileReader(f);
		BufferedReader lerArq = new BufferedReader(arq);
		
		String linha = lerArq.readLine();
		
		while (linha != null) {
			arqConteudo+= produzirArquivo(linha);
			linha = lerArq.readLine(); // lê da segunda até a última linha
		}

		lerArq.close();
    	
        byte[] data = Base64.decode(arqConteudo);
        return new String(data,"UTF-8").replace(TOKEN+SPLIT_TOKEN+TipoModulo.ARQUIVO_TABLET+SPLIT_TOKEN,"");
    } 
}
