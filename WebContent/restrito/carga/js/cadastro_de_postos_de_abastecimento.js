
	function listarEquipamentos(){
		
		ajxRequestStd({returnType:"json", async:false, timeout:10000, method:"GET", url:"../../ws/restrito/carga/equipamento/listar"}, 
				
			function(response){
				var html='<option value="0">Selecione</option>';
				for(var i = 0; i < response.length; i++){
					html+='<option value="'+response[i].id+'">'+response[i].prefixo+" "+response[i].descricao+'</option>';
				}
				$("#listaEquipamento").html(html);
			}
		);
	}
	
	function salvarPosto(){
		
		var data = $("#frmPostoAbastecimento").serialize();
		
		ajxRequestStd({returnType:"json", async:true, timeout:10000, method:"POST", url:"../../ws/restrito/carga/postosAbastecimento/cadastrar",param:data}, 
			function(response){
				alert(response.serverMessageUser);
				$("#frmPostoAbastecimento").trigger("reset");
				listarCadastrados();					
			}
		);		
	}
	
	
	function listarCadastrados(){
		
		ajxRequestStd({returnType:"json", async:true, timeout:10000, method:"GET", url:"../../ws/restrito/carga/postosAbastecimento/listar"}, 
				
				function(response){
		
					console.log(response);
			
					var html='<table border="1">';
							html+="<thead>";
								html+="<tr>";
									html+="<th>Prefixo/ Equipamento</th>";
									html+="<th>Descrição Posto</th>";
									html+="<th>Tipo</th>";
								html+="</tr>";
							html+="</thead>";
							html+="<tbody>";
							
							
							for(var i = 0; i < response.length; i++){
								
								html+="<tr>";
								
									html+="<td>"+response[i].equipamento.prefixo+" - "+response[i].equipamento.descricao+"</td>";
									html+="<td>"+response[i].descricao+"</td>";
									html+='<td style="text-align:center;">'+response[i].tipo+"</td>";
									html+='<td><button type="button" onclick="excluir('+response[i].id+')">Excluir</button></td>';
								
								html+="</tr>";
							}
							html+="</tbody>";
						html+="</table>";
				
						$("#listaCadastrados").html(html);
				}
			);
	}


	function excluir(id){
		
		var path = '../../ws/restrito/carga/postosAbastecimento/remove/'+id;
		
		if(confirm("Deseja realmente excluir o registro atual?")){
			ajxRequestStd({returnType:"json", async:true, timeout:10000, method:"DELETE", url:path}, 
				function(response){
					listarCadastrados();
					alert(response.serverMessageUser);
				}
			);
		}
	}
	

