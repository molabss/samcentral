package br.com.constran.model.paramswrapper.to_mobile_tabs;

import br.com.constran.mobile.persistence.vo.rae.RaeVO;
import br.com.constran.mobile.persistence.vo.rae.abs.AbastecimentoVO;

public class RAEParams {
	
	
	private String idRAE = null;
	private String data = null;
	private String idPosto = null;
	private String idAbastecedor = null;
	private String idUsuario = null;
	private String totalizadorInicial = "0";
	private String totalizadorFinal = "0";
	private String ccObra = null;

	
	
	
	public RAEParams() {
		
	}
	
	
	public RAEParams(RaeVO itemRae, AbastecimentoVO itemAbastecimento) {
		
		setCcObra(String.valueOf(itemAbastecimento.getIdObra()));
		
		setIdRAE(String.valueOf(itemRae.getId()));
		
		setData(itemRae.getData());
		
		setIdPosto(String.valueOf(itemRae.getPosto().getId()));
		
		setIdAbastecedor(String.valueOf(itemAbastecimento.getAbastecedor().getIdUsuarioPessoal()));
		
		setIdUsuario(String.valueOf(itemAbastecimento.getAbastecedor().getIdUsuarioPessoal()));		
		
		if(itemRae.getTotalizadorInicial() != null){
			setTotalizadorInicial(itemRae.getTotalizadorInicial());
		}
		
		if(itemRae.getTotalizadorFinal() != null){
			setTotalizadorFinal(itemRae.getTotalizadorFinal());
		}
					
	}


	public String getIdRAE() {
		return idRAE;
	}


	public void setIdRAE(String idRAE) {
		this.idRAE = idRAE;
	}


	public String getData() {
		return data;
	}


	public void setData(String data) {
		
		if(data == null || data.length() == 0)return;
		
		String[] arrData = data.split("/");
		this.data = arrData[2]+"-"+arrData[1]+"-"+arrData[0];
	}


	public String getIdPosto() {
		return idPosto;
	}


	public void setIdPosto(String idPosto) {
		this.idPosto = idPosto;
	}


	public String getIdAbastecedor() {
		return idAbastecedor;
	}


	public void setIdAbastecedor(String idAbastecedor) {
		this.idAbastecedor = idAbastecedor;
	}


	public String getIdUsuario() {
		return idUsuario;
	}


	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}


	public String getTotalizadorInicial() {
		return totalizadorInicial;
	}


	public void setTotalizadorInicial(String totalizadorInicial) {
		
		if(totalizadorInicial == null || totalizadorInicial.length() == 0) return;
		
		this.totalizadorInicial = totalizadorInicial.replace(",", ".");
	}


	public String getTotalizadorFinal() {
		return totalizadorFinal;
	}


	public void setTotalizadorFinal(String totalizadorFinal) {
		
		if(totalizadorFinal == null || totalizadorFinal.length() == 0) return;
		
		this.totalizadorFinal = totalizadorFinal.replace(",", ".");
	}


	public String getCcObra() {
		return ccObra;
	}


	public void setCcObra(String ccObra) {
		this.ccObra = ccObra;
	}

	
	
	
	
}
