package br.com.constran.model.reports;



import java.util.List;

public class ReportVo {
	
	private String nomeArquivo;
	private String tipo;
	private List<String> parametros;
	
	public String getNomeArquivo() {
		return nomeArquivo;
	}
	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}
	public List<String> getParametros() {
		return parametros;
	}
	public void setParametros(List<String> parametros) {
		this.parametros = parametros;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	
}
