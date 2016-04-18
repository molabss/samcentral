
function salvarPessoa(){
	
	var data = $("#frmPessoal").serialize();
	
	ajxRequestStd({returnType:"json", async:true, timeout:10000, method:"POST", url:"../../ws/restrito/carga/pessoal/cadastra",param:data}, 
		function(response){
			alert(response.serverMessageUser);
			$("#frmPessoal").trigger("reset");
			listarCadastrados();				
		}
	);
}



function listarCadastrados(){
	
	ajxRequestStd({returnType:"json", async:true, timeout:10000, method:"GET", url:"../../ws/restrito/carga/pessoal/listar"}, 
			
			function(response){
	
			console.log(response);
		
				var html='<table border="1">';
						html+="<thead>";
							html+="<tr>";
								html+="<th>Origem</th>";
								html+="<th>Nome</th>";
								html+="<th>Matrícula</th>";
								html+="<th>Senha</th>";
								html+="<th>Data de Nascimento</th>";
								html+="<th>Sexo</th>";
								html+="<th>RG</th>";
								html+="<th>CPF</th>";
								html+="<th>QR CODE</th>";
							html+="</tr>";
						html+="</thead>";
						html+="<tbody>";
						
						
						for(var i = 0; i < response.length; i++){
							
							html+="<tr>";
							
								html+="<td>"+response[i].origem+"</td>";
								html+="<td>"+response[i].nome+"</td>";
								html+='<td style="text-align:center;">'+response[i].matricula+"</td>";
								html+="<td>"+response[i].senha+"</td>";
								html+='<td style="text-align:center;">'+response[i].dataNascimento+"</td>";
								html+='<td style="text-align:center;">'+response[i].sexo+"</td>";
								html+="<td>"+response[i].rg+"</td>";
								html+="<td>"+response[i].cpf+"</td>";
								html+='<td style="text-align:right;">'+response[i].qrCode+"</td>";
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
	
	var path = '../../ws/restrito/carga/pessoal/remove/'+id;
	
	if(confirm("Deseja realmente excluir o registro atual?")){
		ajxRequestStd({returnType:"json", async:true, timeout:10000, method:"DELETE", url:path}, 
			function(response){
				listarCadastrados();
				alert(response.serverMessageUser);
			}
		);
	}
}

