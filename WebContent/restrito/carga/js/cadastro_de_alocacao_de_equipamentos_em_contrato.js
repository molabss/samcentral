
	function listarContratos(){
	
		ajxRequestStd({returnType:"json", async:false, timeout:10000, method:"GET", url:"../../ws/restrito/carga/contrato/listar"}, 
			
			function(response){
	
				var html='<option value="0" selected="selected">Selecione</option>';
				for(var i = 0; i < response.length; i++){
					html+='<option value="'+response[i].numero+'">'+response[i].numero+'</option>';
				}
				$("#listaContrato").html(html);
			}
		);
	}
	
	function listarCategoriasEquipamento(){
		
		var data = "numeroContrato="+$("#listaContrato :selected").text();
		
		ajxRequestStd({returnType:"json", async:false, timeout:10000, method:"GET", url:"../../ws/restrito/carga/contrato/listarCategoriaEquipamento",param:data}, 
				
			function(response){
	
				var html='<option value="0" selected="selected">Selecione</option>';
				for(var i = 0; i < response.length; i++){
					html+='<option value="'+response[i].id+'">'+response[i].descricao+'</option>';
				}
				$("#listaCategoriaEquipamento").html(html);
			}
		);		
	}
	
	function listarEquipamentosPorCategoria(){
		
		var data = "idCategoria="+$("#listaCategoriaEquipamento :selected").val();
		
		ajxRequestStd({returnType:"json", async:false, timeout:10000, method:"GET", url:"../../ws/restrito/carga/contrato/listarEquipamentoPorCategoria",param:data}, 
				
			function(response){
	
				var html='<option value="0" selected="selected">Selecione</option>';
				for(var i = 0; i < response.length; i++){
					html+='<option value="'+response[i].id+'">'+response[i].prefixo+" "+response[i].descricao+'</option>';
				}
				$("#listaEquipamento").html(html);
			}
		);
	}
	
	function salvarAlocacao(){
		
		var data = $("#frmAlocacaoEquipamento").serialize();
		
		ajxRequestStd({returnType:"json", async:true, timeout:10000, method:"POST", url:"../../ws/restrito/carga/contrato/alocar/equipamento",param:data}, 
			function(response){
				alert(response.serverMessageUser);
				$("#frmAlocacaoEquipamento").trigger("reset");
				listarCadastrados();				
			}
		);
	}
	
	
	
	function listarCadastrados(){
		
		ajxRequestStd({returnType:"json", async:true, timeout:10000, method:"GET", url:"../../ws/restrito/carga/contrato/alocacao/equipamento/listar"}, 
				
				function(response){

				console.log(response);
			
					var html='<table border="1">';
							html+="<thead>";
								html+="<tr>";
									html+="<th>Número Contrato</th>";
									html+="<th>Categoria</th>";
									html+="<th>Prefixo/ Equipamento</th>";
									html+="<th>Data Ingresso</th>";
									html+="<th>KM de Etrada</th>";
									html+="<th>Horímetro de Entrada</th>";
									html+="<th>NF de Entrada</th>";
									html+="<th>Tipo de Medição</th>";
									html+="<th>Realiza Manutenção</th>";
									html+="<th>Tipo Controle Consumo</th>";
								html+="</tr>";
							html+="</thead>";
							html+="<tbody>";
							
							
							for(var i = 0; i < response.length; i++){
								
								html+="<tr>";
								
									html+="<td>"+response[i].contrato.numero+"</td>";
									html+="<td>"+response[i].descCategoria+"</td>";
									html+="<td>"+response[i].equipamento.prefixo+' - '+response[i].equipamento.descricao+"</td>";
									html+='<td style="text-align:center;">'+response[i].dataIngresso+"</td>";
									html+='<td style="text-align:right;">'+response[i].kmEntrada+"</td>";
									html+='<td style="text-align:right;">'+response[i].horimetroEntrada+"</td>";
									html+="<td>"+response[i].nfEntrada+"</td>";
									html+='<td style="text-align:center;">'+response[i].tipoMedicao+"</td>";
									html+='<td style="text-align:center;">'+response[i].realizaManutencao+"</td>";
									html+='<td style="text-align:center;">'+response[i].tipoControleConsumo+"</td>";
									html+='<td><button type="button" onclick="excluir('+"'"+response[i].contrato.numero+"'"+','+response[i].categoria+','+response[i].equipamento.id+','+"'"+response[i].dataIngresso+"'"+')">Excluir</button></td>';
								
								html+="</tr>";
							}
							html+="</tbody>";
						html+="</table>";
				
						$("#listaCadastrados").html(html);
				}
			);
	}


	function excluir(numeroContrato,categoria,equipamento,dataIngresso){
		
		var path = '../../ws/restrito/carga/contrato/alocacao/equipamento/remove/'+numeroContrato+'/'+categoria+'/'+equipamento+'/'+dataIngresso;
		
		
		if(confirm("Deseja realmente excluir o registro atual?")){
			
			ajxRequestStd({returnType:"json", async:true, timeout:10000, method:"DELETE", url:path}, 
					function(response){
						listarCadastrados();
						alert(response.serverMessageUser);
					}
				);	
		}
	}
	