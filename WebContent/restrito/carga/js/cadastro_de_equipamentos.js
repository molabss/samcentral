	function salvarEquipamento(){
		
		var data = $("#frmEquipamento").serialize();
	
		ajxRequestStd({returnType:"json", async:true, timeout:10000, method:"POST", url:"../../ws/restrito/carga/equipamento/cadastra",param:data}, 
			function(response){
				alert(response.serverMessageUser);
				$("#frmEquipamento").trigger("reset");
				listarCadastrados();				
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
	
	function listarFabricantes(){
		
		ajxRequestStd({returnType:"json", async:false, timeout:10000, method:"GET", url:"../../ws/restrito/carga/equipamento/fabricante/listar"}, 
				
			function(response){
				var html='<option value="0">Selecione</option>';
				for(var i = 0; i < response.length; i++){
					html+='<option value="'+response[i].id+'">'+response[i].descricao+'</option>';
				}
				$("#listaFabricante").html(html);
			}
		);
	}
	
	function listarFornecedores(){
	
		ajxRequestStd({returnType:"json", async:false, timeout:10000, method:"GET", url:"../../ws/restrito/carga/fornecedor/listar"}, 
			
			function(response){
				var html='<option value="0">Selecione</option>';
				for(var i = 0; i < response.length; i++){
					html+='<option value="'+response[i].id+'">'+response[i].razao+'</option>';
				}
				$("#listaFornecedores").html(html);
			}
		);
	}
	
	
	function listarCadastrados(){
		
		ajxRequestStd({returnType:"json", async:true, timeout:10000, method:"GET", url:"../../ws/restrito/carga/equipamento/listar"}, 
				
			function(response){

				var html='<table border="1">';
						html+="<thead>";
							html+="<tr>";
								html+="<th>Prefixo</th>";
								html+="<th>Descrição</th>";
								html+="<th>Categoria</th>";
								html+="<th>Fornecedor</th>";
								html+="<th>Tipo</th>";
								html+="<th>Unidade de Medida</th>";
								html+="<th>Chassi/ Série</th>";
								html+="<th>Placa</th>";
								html+="<th>Fabricante</th>";
								html+="<th>Ano</th>";
								html+="<th>Controle Lubrificação</th>";
								html+="<th>QR Code</th>";
							html+="</tr>";
						html+="</thead>";
						html+="<tbody>";
						
						for(var i = 0; i < response.length; i++){
							
							html+="<tr>";
								html+="<td>"+response[i].prefixo+"</td>";
								html+="<td>"+response[i].descricao+"</td>";
								html+="<td>"+response[i].categoria.descricao+"</td>";
								html+="<td>"+response[i].fornecedor.razao+"</td>";
								html+="<td>"+response[i].tipo+"</td>";
								html+="<td>"+response[i].unidadeMedida+"</td>";
								html+="<td>"+response[i].chassi+"</td>";
								html+="<td>"+response[i].placa+"</td>";
								html+="<td>"+response[i].fabricante.descricao+"</td>";
								html+="<td>"+response[i].ano+"</td>";
								html+="<td>"+response[i].controleLubrificacao+"</td>";
								html+="<td>"+response[i].qrCode+"</td>";
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
		
		var path = '../../ws/restrito/carga/equipamento/remove/'+id;
		
		if(confirm("Deseja realmente excluir o registro atual?")){
			ajxRequestStd({returnType:"json", async:true, timeout:10000, method:"DELETE", url:path}, 
				function(response){
					listarCadastrados();
					alert(response.serverMessageUser);
				}
			);
		}
	}	
	
	