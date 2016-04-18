
	function salvarFornecedor(){
		
		var data = $("#frmFornecedor").serialize();
		
		ajxRequestStd({returnType:"json", async:true, timeout:10000, method:"POST", url:"../../ws/restrito/carga/fornecedor/cadastra",param:data}, 
			function(response){
				alert(response.serverMessageUser);
				$("#frmFornecedor").trigger("reset");
				listarCadastrados();
			}
		);
	}

	function listarCadastrados(){
		
		ajxRequestStd({returnType:"json", async:true, timeout:10000, method:"GET", url:"../../ws/restrito/carga/fornecedor/listar"}, 
				
				function(response){
		
					var html='<table border="1">';
							html+="<thead>";
								html+="<tr>";
									html+="<th>CNPJ</th>";
									html+="<th>Razão Social</th>";
									html+="<th>Nome Fantasia</th>";
									html+="<th>Endereço</th>";
									html+="<th>Inscrição Estadual</th>";
									html+="<th>Contatos</th>";
									html+="<th>Indicação</th>";
									html+="<th>Responsável Legal</th>";
									html+="<th>Responsável Técnico</th>";
								html+="</tr>";
							html+="</thead>";
							html+="<tbody>";
							
							for(var i = 0; i < response.length; i++){
								
								html+="<tr>";
								
									html+="<td>"+response[i].cnpj+"</td>";
									html+="<td>"+response[i].razao+"</td>";
									html+="<td>"+response[i].fantasia+"</td>";
									html+="<td>"+response[i].endereco+"</td>";
									html+="<td>"+response[i].inscEstadual+"</td>";
									html+="<td>"+response[i].contatos+"</td>";
									html+="<td>"+response[i].indicacao+"</td>";
									html+="<td>"+response[i].responsavelLegal+"</td>";
									html+="<td>"+response[i].responsavelTecnico+"</td>";
									html+='<td><button type="button" onclick="excluir('+response[i].id+','+response[i].codigoDeq+')">Excluir</button></td>';
								
								html+="</tr>";
							}
							html+="</tbody>";
						html+="</table>";
				
						$("#listaCadastrados").html(html);		
				}
			);
	}

	function excluir(id,codigoDeq){
		
		var path = '../../ws/restrito/carga/fornecedor/remove/'+id+'/'+codigoDeq;
		
		if(confirm("Deseja realmente excluir o registro atual?")){
			ajxRequestStd({returnType:"json", async:true, timeout:10000, method:"DELETE", url:path}, 
				function(response){
					listarCadastrados();
					alert(response.serverMessageUser);
				}
			);
		}
	}