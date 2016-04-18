
	function salvar(){
		
		var data = $("#frmCombustiveisLubrificantes").serialize();
		
		ajxRequestStd({returnType:"json", async:true, timeout:10000, method:"POST", url:"../../ws/restrito/carga/postosAbastecimento/adicionarCombustiveisLubrificantes",param:data}, 
			function(response){
				alert(response.serverMessageUser);
				$("#frmCombustiveisLubrificantes").trigger("reset");
				listarCadastrados();				
			}
		);	
	}
	
	function listarPostos(){
		
		ajxRequestStd({returnType:"json", async:false, timeout:10000, method:"GET", url:"../../ws/restrito/carga/postosAbastecimento/listar"}, 
				
			function(response){
			
				var html='<option value="0">Selecione</option>';
				for(var i = 0; i < response.length; i++){
					html+='<option value="'+response[i].id+'">'+response[i].equipamento.prefixo+" "+response[i].descricao+" ("+response[i].equipamento.descricao+')</option>';
				}
				$("#listaPosto").html(html);
			}
		);		
	}
	
	function listarCombustiveisLubrificantes(){
		
		ajxRequestStd({returnType:"json", async:false, timeout:10000, method:"GET", url:"../../ws/restrito/carga/combustiveis_e_lubrificantes/listar"}, 
				
			function(response){
				var html='';
				for(var i = 0; i < response.length; i++){
					html+='<option value="'+response[i].id+'">'+response[i].descricao+'</option>';
				}
				$("#listaCombustiveis").html(html);
			}
		);
	}
	
	function listarCadastrados(){
		
		ajxRequestStd({returnType:"json", async:true, timeout:10000, method:"GET", url:"../../ws/restrito/carga/postosAbastecimento/combustiveisLubrificantes/listar"}, 
				
			function(response){
		
				var html='<table border="1">';
						html+="<thead>";
							html+="<tr>";
								html+="<th>Equipamento Posto</th>";
								html+="<th>Combustíveis e Lubrificantes</th>";
							html+="</tr>";
						html+="</thead>";
						html+="<tbody>";
						
						for(var i = 0; i < response.length; i++){
							html+="<tr>";
								html+="<td>"+response[i].posto.equipamento.prefixo+" "+response[i].posto.descricao+" "+"("+response[i].posto.equipamento.descricao+")"+"</td>";
								html+="<td>"+response[i].combustivelLubrif.descricao+"</td>";
								html+='<td><button type="button" onclick="excluir('+response[i].combustivelLubrif.id+","+response[i].posto.id+')">Excluir</button></td>';
							html+="</tr>";
						}
						
						html+="</tbody>";
					html+="</table>";
					$("#listaCadastrados").html(html);	
			}
		);
	}


	function excluir(idCombust, idPosto){
		
		var path = '../../ws/restrito/carga/postosAbastecimento/combustiveisLubrificantes/remove/'+idCombust+'/'+idPosto;
		
		if(confirm("Deseja realmente excluir o registro atual?")){
			ajxRequestStd({returnType:"json", async:true, timeout:10000, method:"DELETE", url:path}, 
				function(response){
					listarCadastrados();
					alert(response.serverMessageUser);
				}
			);
		}
	}
	
	