	
	function salvarAlocacao(){
		var data = $("#frmAlocacaoPessoal").serialize();
		
		ajxRequestStd({returnType:"json", async:true, timeout:10000, method:"POST", url:"../../ws/restrito/carga/contrato/alocar/pessoa",param:data}, 
			function(response){
				alert(response.serverMessageUser);
				$("#frmAlocacaoPessoal").trigger("reset");
				listarCadastrados();					
			}
		);		
	}
	
	function listarPessoas(){
		
		ajxRequestStd({returnType:"json", async:false, timeout:10000, method:"GET", url:"../../ws/restrito/carga/pessoal/listar"}, 
			
			function(response){
	
				var html='<option value="0" selected="selected">Selecione</option>';
				for(var i = 0; i < response.length; i++){
					html+='<option value="'+response[i].id+'">'+response[i].nome+'</option>';
				}
				$("#listaPessoa").html(html);
			}
		);
	}	
	
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

	function listarFuncoes(){
		
		ajxRequestStd({returnType:"json", async:false, timeout:10000, method:"GET", url:"../../ws/restrito/carga/pessoal/funcao/listar"}, 
				
			function(response){
	
				var html='<option value="0" selected="selected">Selecione</option>';
				for(var i = 0; i < response.length; i++){
					html+='<option value="'+response[i].id+'">'+response[i].descricao+'</option>';
				}
				$("#listaFuncoes").html(html);
			}
		);
	}
	
	
	function listarCadastrados(){
		
		ajxRequestStd({returnType:"json", async:true, timeout:10000, method:"GET", url:"../../ws/restrito/carga/contrato/alocacao/pessoal/listar"}, 
				
				function(response){
		
			
				console.log(response);
			
					var html='<table border="1">';
							html+="<thead>";
								html+="<tr>";
									html+="<th>Número Contrato</th>";
									html+="<th>Nome</th>";
									html+="<th>Função</th>";
									html+="<th>Tipo de Medição</th>";
									html+="<th>Data Ingresso</th>";
								html+="</tr>";
							html+="</thead>";
							html+="<tbody>";
							
							
							for(var i = 0; i < response.length; i++){
								
								html+="<tr>";
								
									html+="<td>"+response[i].contrato.numero+"</td>";
									html+="<td>"+response[i].pessoa.nome+"</td>";
									html+="<td>"+response[i].funcao.descricao+"</td>";
									html+='<td style="text-align:center;">'+response[i].tipoMedicao+"</td>";
									html+='<td style="text-align:center;">'+response[i].dataIngresso+"</td>";
									html+='<td><button type="button" onclick="excluir('+"'"+response[i].contrato.numero+"'"+','+response[i].pessoa.id+','+"'"+response[i].dataIngresso+"'"+')">Excluir</button></td>';
								
								html+="</tr>";
							}
							html+="</tbody>";
						html+="</table>";
				
						$("#listaCadastrados").html(html);		
				}
			);
	}

	function excluir(contratoNum,nome,dataIngresso){
		
		var path = '../../ws/restrito/carga/contrato/alocacao/pessoal/remove/'+contratoNum+'/'+nome+'/'+dataIngresso;
		
		if(confirm("Deseja realmente excluir o registro atual?")){
			ajxRequestStd({returnType:"json", async:true, timeout:10000, method:"DELETE", url:path}, 
				function(response){
					listarCadastrados();
					alert(response.serverMessageUser);
				}
			);
		}
	}
	