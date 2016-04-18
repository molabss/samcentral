
	function salvarContrato(){
		
		var data = $("#frmContrato").serialize();
		
		ajxRequestStd({returnType:"json", async:true, timeout:10000, method:"POST", url:"../../ws/restrito/carga/contrato/cadastra",param:data}, 
			function(response){
				alert(response.serverMessageUser);
				$("#frmContrato").trigger("reset");
				listarCadastrados();				
			}
		);
	}

	function listarFornecedores(){
	
		ajxRequestStd({returnType:"json", async:true, timeout:10000, method:"GET", url:"../../ws/restrito/carga/fornecedor/listar"}, 
			
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
		
		ajxRequestStd({returnType:"json", async:false, timeout:10000, method:"GET", url:"../../ws/restrito/carga/contrato/listar"}, 
				
			function(response){
	
			console.log(response);
		
				var html='<table border="1">';
						html+="<thead>";
							html+="<tr>";
								html+="<th>Número Contrato</th>";
								html+="<th>Tipo</th>";
								html+="<th>Fornecedor</th>";
								html+="<th>Objeto do Contrato</th>";
								html+="<th>Tipo Período</th>";
								html+="<th>Prazo</th>";
								html+="<th>Data Início</th>";
								html+="<th>Data Término</th>";
								html+="<th>Valor Global</th>";
							html+="</tr>";
						html+="</thead>";
						html+="<tbody>";
					
				
						for(var i = 0; i < response.length; i++){
							
							html+="<tr>";
							
								html+='<td style="text-align:left;">'+response[i].numero+"</td>";
								html+='<td style="text-align:center;">'+response[i].tipo+"</td>";
								html+="<td>"+response[i].fornecedor.razao+"</td>";
								html+="<td>"+response[i].objeto+"</td>";
								html+="<td>"+response[i].tipoPeriodo+"</td>";
								html+="<td>"+response[i].prazo+"</td>";
								html+='<td style="text-align:center;">'+response[i].inicio+"</td>";
								html+='<td style="text-align:center;">'+response[i].termino+"</td>";
								html+='<td><input type="text" disabled="disabled" id="vlrGlob_'+i+'" value="'+response[i].valorGlobal+'" style="text-align:right;"></td>';
								html+='<td><button type="button" onclick="excluir('+"'"+response[i].numero+"'"+')">Excluir</button></td>';
							
							html+="</tr>";
						}
						html+="</tbody>";
					html+="</table>";
				
					$("#listaCadastrados").html(html);
					
					$('input[id^="vlrGlob"]').mask('000.000.000.000.000,00', {reverse: true});
			}
		);
	}


	function excluir(numeroContrato){
		
		var path = '../../ws/restrito/carga/contrato/remove/'+numeroContrato;
		
		if(confirm("Deseja realmente excluir o registro atual?")){
			ajxRequestStd({returnType:"json", async:true, timeout:10000, method:"DELETE", url:path}, 
				function(response){
					listarCadastrados();
					alert(response.serverMessageUser);
				}
			);
		}
	}
