
	function salvarItem(){
		
		var data = $("#frmItemEqpmContrato").serialize();
		
		ajxRequestStd({returnType:"json", async:true, timeout:10000, method:"POST", url:"../../ws/restrito/carga/contrato/itensContrato/equipamento",param:data}, 
			function(response){
				alert(response.serverMessageUser);
				$("#frmItemEqpmContrato").trigger("reset");
				listarCadastrados();				
			}
		);
	}

	function listarContratos(){
	
		ajxRequestStd({returnType:"json", async:true, timeout:10000, method:"GET", url:"../../ws/restrito/carga/contrato/listar"}, 
			
			function(response){
	
				var html='<option value="0">Selecione</option>';
				
				for(var i = 0; i < response.length; i++){
					html+='<option value="'+response[i].numero+'">'+response[i].numero+'</option>';
				}
			
				$("#listaContratos").html(html);
			}
		);
	}

	function listarCategorias(){
	
		ajxRequestStd({returnType:"json", async:false, timeout:10000, method:"GET", url:"../../ws/restrito/carga/equipamento/categoria/listar"}, 
				
			function(response){
				var html='<option value="0">Selecione</option>';
				for(var i = 0; i < response.length; i++){
					html+='<option value="'+response[i].id+'">'+response[i].descricao+'</option>';
				}
				$("#listaCategorias").html(html);
			}
		);
	}

	function listarCadastrados(){
	
		ajxRequestStd({returnType:"json", async:true, timeout:10000, method:"GET", url:"../../ws/restrito/carga/contrato/itensContrato/equipamento/listar"}, 
			
			function(response){
		
				var html='<table border="1">';
						html+="<thead>";
							html+="<tr>";
								html+="<th>Número Contrato</th>";
								html+="<th>Categoria</th>";
								html+="<th>Quantidade</th>";
								html+="<th>Valor Mensal R$</th>";
								html+="<th>Valor Hora Normal R$</th>";
								html+="<th>Valor Hora Extra R$</th>";
								html+="<th>Horas Mínimas</th>";
								html+="<th>Fornece Operador</th>";
							html+="</tr>";
						html+="</thead>";
						html+="<tbody>";
						
						for(var i = 0; i < response.length; i++){
							
							html+="<tr>";
								html+="<td>"+response[i].contrato.numero+"</td>";
								html+="<td>"+response[i].descCategoria+"</td>";
								html+='<td style="text-align:right;">'+response[i].quantidade+"</td>";
								html+='<td><input type="text" disabled="disabled" id="vlrMens_'+i+'" value="'+response[i].valorMensal+'" style="text-align:right;"></td>';
								html+='<td><input type="text" disabled="disabled" id="vlrNorm_'+i+'" value="'+response[i].valorNormal+'" style="text-align:right;"></td>';
								html+='<td><input type="text" disabled="disabled" id="vlrHoEx_'+i+'" value="'+response[i].valorHoraExtra+'" style="text-align:right;"></td>';
								html+='<td style="text-align:right;">'+response[i].horasMinimas+"</td>";
								html+='<td style="text-align:center;">'+response[i].forneceOperador+"</td>";
								html+='<td><button type="button" onclick="excluir('+"'"+response[i].contrato.numero+"'"+","+response[i].categoria+')">Excluir</button></td>';
							html+="</tr>";
						}
						html+="</tbody>";
					html+="</table>";
					$("#listaCadastrados").html(html);
					
					$('input[id^="vlrMens_"]').mask('000.000.000,00', {reverse: true});
					$('input[id^="vlrNorm_"]').mask('000.000.000,00', {reverse: true});
					$('input[id^="vlrHoEx_"]').mask('000.000.000,00', {reverse: true});
			}
		);
	}

	function excluir(numeroContrato,categoria){
		
		var path = '../../ws/restrito/carga/contrato/itensContrato/equipamento/remove/'+numeroContrato+'/'+categoria;
		
		if(confirm("Deseja realmente excluir o registro atual?")){
			ajxRequestStd({returnType:"json", async:true, timeout:10000, method:"DELETE", url:path}, 
				function(response){
					listarCadastrados();
					alert(response.serverMessageUser);
				}
			);
		}
	}

