package br.com.constran.model.paramswrapper.to_mobile_tabs;

import br.com.constran.mobile.persistence.vo.rae.abs.AbastecimentoPostoVO;

public class SaldoPostoParams {
	
	
	private String idCombustivelLubrificante = null;
	private String data = null;
	private String hora = null;
	private String idPosto = null;
	private String idPostoOrigem = null;
	private String quantidade = null;
	
	
	@Override
	public String toString() {
		
		return "Combustivel lubrificante: "+getIdCombustivelLubrificante()
				+"Data: "+getData()
				+"Hora: "+getHora()
				+"Data/Hora: "+getDataHora()
				+"Posto: "+getIdPosto()
				+"Posto Origem: "+getIdPostoOrigem()
				+"Quantidade: "+getQuantidade();
		
	}
	
	public SaldoPostoParams(){
		
	}
	
	public SaldoPostoParams(AbastecimentoPostoVO itemSaldo){
		

		setIdCombustivelLubrificante(String.valueOf(itemSaldo.getCombustivelLubrificante().getId()));
		
		setData(itemSaldo.getData());
		
		setHora(itemSaldo.getHora());

		setIdPosto(String.valueOf(itemSaldo.getPosto().getId()));
		
		if(itemSaldo.getPosto2() != null){
			setIdPostoOrigem(String.valueOf(itemSaldo.getPosto2().getId()));
		}
		
		setQuantidade(itemSaldo.getQtd());
		
	}

	public String getIdCombustivelLubrificante() {
		return idCombustivelLubrificante;
	}

	public void setIdCombustivelLubrificante(String idCombustivelLubrificante) {
		this.idCombustivelLubrificante = idCombustivelLubrificante;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		if(data.length() == 0)return;
		
		String[] arrData = data.split("/");
		this.data = arrData[2]+"-"+arrData[1]+"-"+arrData[0];
	}

	
	
	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getIdPosto() {
		return idPosto;
	}

	public void setIdPosto(String idPosto) {
		this.idPosto = idPosto;
	}

	public String getIdPostoOrigem() {
		return idPostoOrigem;
	}

	public void setIdPostoOrigem(String idPostoOrigem) {
		this.idPostoOrigem = idPostoOrigem;
	}

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

	//CHAMAR SOMENTE APOS setData() e setHora()
	public String getDataHora() {
		return getData()+" "+getHora();
	}


	
	
	

}
