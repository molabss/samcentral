package br.com.constran.model.carga;

public class CombustivelPosto {
	
	private String ccObra;
	private PostoAbastecimento posto;
	private CombustivelLubrificante combustivelLubrif;
	
	
	
	public String getCcObra() {
		return ccObra;
	}
	public void setCcObra(String ccObra) {
		this.ccObra = ccObra;
	}
	public PostoAbastecimento getPosto() {
		return posto;
	}
	public void setPosto(PostoAbastecimento posto) {
		this.posto = posto;
	}
	public CombustivelLubrificante getCombustivelLubrif() {
		return combustivelLubrif;
	}
	public void setCombustivelLubrif(CombustivelLubrificante combustivelLubrif) {
		this.combustivelLubrif = combustivelLubrif;
	}
	
	

}
