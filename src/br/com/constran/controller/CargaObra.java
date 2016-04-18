package br.com.constran.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.constran.dao.CombustivelLubrificacaoDAO;
import br.com.constran.dao.ContratoDAO;
import br.com.constran.dao.EquipamentoDAO;
import br.com.constran.dao.FornecedorDAO;
import br.com.constran.dao.PessoalDAO;
import br.com.constran.dao.PostoDAO;
import br.com.constran.dao.UsuarioDAO;
import br.com.constran.model.carga.CategoriaEquipamento;
import br.com.constran.model.carga.CombustivelLubrificante;
import br.com.constran.model.carga.CombustivelPosto;
import br.com.constran.model.carga.Contrato;
import br.com.constran.model.carga.ContratoAlocacaoEquipamento;
import br.com.constran.model.carga.ContratoAlocacaoPessoal;
import br.com.constran.model.carga.ContratoItensEquipamento;
import br.com.constran.model.carga.Equipamento;
import br.com.constran.model.carga.Fabricante;
import br.com.constran.model.carga.Fornecedor;
import br.com.constran.model.carga.Funcao;
import br.com.constran.model.carga.Pessoal;
import br.com.constran.model.carga.PostoAbastecimento;
import br.com.constran.model.carga.Usuario;
import br.com.constran.util.AbstractController;
import br.com.constran.util.ServerResponse;
import br.com.constran.util.TextUtil;


@Path("/restrito/carga")
public class CargaObra extends AbstractController {

	
	@Context
	HttpServletRequest request;
	
	@Context
	HttpServletResponse response;
	
	Usuario u;
	
	@POST
	@Path("/fornecedor/cadastra")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response cadastrarFornecedor(
			@DefaultValue("") @FormParam("cnpj")String cnpj,
			@DefaultValue("") @FormParam("razao")String razao,
			@FormParam("fantasia")String fantasia,
			@FormParam("endereco")String endereco,
			@FormParam("incricaoEst")String incricaoEst,
			@FormParam("contatos")String contatos,
			@FormParam("indicacao")String indicacao,
			@FormParam("responsavelLegal")String responsavelLegal,
			@FormParam("responsavelTecnico")String responsavelTecnico) {
		
		if (noSession()) return redirectTo();
		
		Response response = null;
		
		if(cnpj.length() == 0) erroMsg.add("CNPJ é um campo obrigatório.");

			if(razao.length() == 0) erroMsg.add("Razão social é um campo obrigatório.");
	
				if(erroMsg.size() > 0) return Response.status(Response.Status.BAD_REQUEST).entity(new ServerResponse(getErroMsg())).build();
		
		try {
			abreConexaoBD();
			Fornecedor fornecedor = new Fornecedor(cnpj,razao,fantasia,endereco,incricaoEst,contatos,indicacao,responsavelLegal,responsavelTecnico);
			FornecedorDAO dao = new FornecedorDAO(conn);			
			dao.cadastraFornecedor(fornecedor,getObraUsuario());
			response = Response.status(Status.OK).entity(new ServerResponse("Cadastrado com sucesso!")).build();
		} 
		catch (SQLException e) {
			e.printStackTrace();
			response = Response.status(Status.INTERNAL_SERVER_ERROR).entity(new ServerResponse("Ocorreu um erro ao cadastrar o fornecedor: \n\n"+e.getMessage())).build();
		}
		finally {
			fechaConexaoBD();
		}
		return response;
	}	

	
	@GET
	@Path("/contrato/itensContrato/equipamento/listar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarItensContratoEquipa(){
		
		if (noSession()) return redirectTo();
		
		Response response = null;
		
		try {
			abreConexaoBD();
			
			ContratoDAO dao = new ContratoDAO(conn);
			List<ContratoItensEquipamento> lista = dao.listarItensEquipamentos(getObraUsuario());
			response = Response.status(Status.OK).entity(lista).build();
		} 
		catch (SQLException e) {
			e.printStackTrace();
			response = Response.status(Status.INTERNAL_SERVER_ERROR).entity(new ServerResponse("Ocorreu um erro ao listar os itens de contrato: \n\n"+e.getMessage())).build();
		}
		finally{
			fechaConexaoBD();
		}
		return response;
	}
	
	@GET
	@Path("/pessoal/listar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarPessoalCadastrado(){
		
		if (noSession()) return redirectTo();
		
		Response response = null;
		
		try {
			
			abreConexaoBD();
			PessoalDAO dao = new PessoalDAO(conn);
			List<Pessoal> lista = dao.listarPessoasCadastradas(getObraUsuario());
			response = Response.status(Status.OK).entity(lista).build();
			
		} catch (SQLException e) {
			e.printStackTrace();
			response = Response.status(Status.INTERNAL_SERVER_ERROR).entity(new ServerResponse("Ocorreu um erro ao listar as pessoas cadastradas.\n\n"+ e.getClass().getName()+"\n"+e.getMessage())).build();
		}
		finally{
			fechaConexaoBD();
		}			
		
		return response;
	}
	
	@GET
	@Path("/fornecedor/listar")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarFornecedores(){
		
		if (noSession()) return redirectTo();
		
		Response response = null;
		
		try {
			abreConexaoBD();
			FornecedorDAO dao = new FornecedorDAO(conn);
			List<Fornecedor> lista = dao.listar(getObraUsuario());
			response = Response.status(Status.OK).entity(lista).build();
		} 
		catch (SQLException e) {
			e.printStackTrace();
			response = Response.status(Status.INTERNAL_SERVER_ERROR).entity(new ServerResponse("Ocorreu um erro ao listar Fornecedores: \n\n"+e.getMessage())).build();
		}
		finally {
			fechaConexaoBD();
		}		
	
		return response;
	}

	
	@GET
	@Path("/contrato/listar")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarContratos(){
		
		if (noSession()) return redirectTo();
		
		Response response = null;
		
		try {
			abreConexaoBD();
			ContratoDAO dao = new ContratoDAO(conn);
			List<Contrato> lista = dao.listar(getObraUsuario());
			response = Response.status(Status.OK).entity(lista).build();
		} 
		catch (SQLException e) {
			e.printStackTrace();
			response = Response.status(Status.INTERNAL_SERVER_ERROR).entity(new ServerResponse("Ocorreu um erro ao listar Contratos: \n\n"+e.getMessage())).build();
		}
		finally {
			fechaConexaoBD();
		}		
	
		return response;
	}	
	
	
	@GET
	@Path("/contrato/alocacao/equipamento/listar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarAlocacoesEquipamentos(){
		
		if (noSession()) return redirectTo();
		
		Response response = null;
		
		try {
			abreConexaoBD();
			
			ContratoDAO dao = new ContratoDAO(conn);
			List<ContratoAlocacaoEquipamento> lista = dao.listarEquipamentosAlocados(getObraUsuario());
			response = Response.status(Status.OK).entity(lista).build();
		} 
		catch (SQLException e) {
			e.printStackTrace();
			response = Response.status(Status.INTERNAL_SERVER_ERROR).entity(new ServerResponse("Ocorreu um erro ao listar os equipamentos alocados no contrato: \n\n"+e.getMessage())).build();
		}
		finally{
			fechaConexaoBD();
		}
		return response;
	}
	
	
	
	@GET
	@Path("/contrato/alocacao/pessoal/listar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarAlocacoesPessoas(){
		
		if (noSession()) return redirectTo();
		
		Response response = null;
		
		try {
			abreConexaoBD();
			
			ContratoDAO dao = new ContratoDAO(conn);
			List<ContratoAlocacaoPessoal> lista = dao.listarPessoasAlocados(getObraUsuario());
			response = Response.status(Status.OK).entity(lista).build();
		} 
		catch (SQLException e) {
			e.printStackTrace();
			response = Response.status(Status.INTERNAL_SERVER_ERROR).entity(new ServerResponse("Ocorreu um erro ao listar os equipamentos alocados no contrato: \n\n"+e.getMessage())).build();
		}
		finally{
			fechaConexaoBD();
		}
		return response;
	}	
	
	@GET
	@Path("/contrato/listarCategoriaEquipamento")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarCategoriaEquipamento(@QueryParam("numeroContrato")String numeroContrato){
		
		if (noSession()) return redirectTo();
		
		Response response = null;
		
		try {
			abreConexaoBD();
			ContratoDAO dao = new ContratoDAO(conn);
			List<CategoriaEquipamento> lista = dao.listarCategoriaEquipamento(numeroContrato);
			response = Response.status(Status.OK).entity(lista).build();
		} 
		catch (SQLException e) {
			e.printStackTrace();
			response = Response.status(Status.INTERNAL_SERVER_ERROR).entity(new ServerResponse("Ocorreu um erro ao listar as categorias de equipamento: \n\n"+e.getMessage())).build();
		}
		finally {
			fechaConexaoBD();
		}		
		
		return response;
	}
	
	
	@GET
	@Path("/contrato/listarEquipamentoPorCategoria")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarEquipamentoPorCategoriao(@QueryParam("idCategoria")Integer idCategoria){
		
		if (noSession()) return redirectTo();
		
		Response response = null;
		
		try {
			abreConexaoBD();
			ContratoDAO dao = new ContratoDAO(conn);
			List<Equipamento> lista = dao.listarEquipamentoPorCategoria(idCategoria,getObraUsuario());
			response = Response.status(Status.OK).entity(lista).build();
		} 
		catch (SQLException e) {
			e.printStackTrace();
			response = Response.status(Status.INTERNAL_SERVER_ERROR).entity(new ServerResponse("Ocorreu um erro ao listar equipamentos por categoria: \n\n"+e.getMessage())).build();
		}
		finally {
			fechaConexaoBD();
		}		
		
		return response;
	}
	
	
	@GET
	@Path("/equipamento/fabricante/listar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarFabricantesEquipamentos(){
		
		if (noSession()) return redirectTo();
		
		Response response = null;
		
		try {
			abreConexaoBD();
			EquipamentoDAO dao = new EquipamentoDAO(conn);
			List<Fabricante> lista = dao.listarFabricanteEquipamentos();
			response = Response.status(Status.OK).entity(lista).build();
		} 
		catch (SQLException e) {
			e.printStackTrace();
			response = Response.status(Status.INTERNAL_SERVER_ERROR).entity(new ServerResponse("Ocorreu um erro ao listar fabricantes de equipamentos: \n\n"+e.getMessage())).build();
		}
		finally {
			fechaConexaoBD();
		}		
		return response;
	}
	
	
	@GET
	@Path("/pessoal/funcao/listar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarFuncoesPessoal(){
		
		if (noSession()) return redirectTo();
		
		Response response = null;
		
		try {
			abreConexaoBD();
			PessoalDAO dao = new PessoalDAO(conn);
			List<Funcao> lista = dao.listarFuncoes();
			response = Response.status(Status.OK).entity(lista).build();
		} 
		catch (Exception e) {
			e.printStackTrace();
			response = Response.status(Status.INTERNAL_SERVER_ERROR).entity(new ServerResponse("Ocorreu um erro ao listar as funções.\n\n"+e.getMessage())).build();
		}
		finally {
			fechaConexaoBD();
		}		
		return response;
	}
	
	
	@GET
	@Path("/equipamento/categoria/listar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarCategoriasEquipamentos(){
		
		if (noSession()) return redirectTo();
		
		Response response = null;
		
		try {
			abreConexaoBD();
			EquipamentoDAO dao = new EquipamentoDAO(conn);
			List<CategoriaEquipamento> lista = dao.listarCategoriasEquipamentos();
			response = Response.status(Status.OK).entity(lista).build();
		} 
		catch (SQLException e) {
			e.printStackTrace();
			response = Response.status(Status.INTERNAL_SERVER_ERROR).entity(new ServerResponse("Ocorreu um erro ao listar categorias de equipamentos: \n\n"+e.getMessage())).build();
		}
		finally {
			fechaConexaoBD();
		}		
		return response;
	}	
	
	
	@GET
	@Path("/equipamento/listar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarEquipamentos() {
		
		if (noSession()) return redirectTo();
		
		Response response = null;
		
		try {
			abreConexaoBD();
			EquipamentoDAO dao = new EquipamentoDAO(conn);
			List<Equipamento> lista =  dao.listar(getObraUsuario());
			response = Response.status(Status.OK).entity(lista).build();
		} 
		catch (Exception e) {
			e.printStackTrace();
			response = Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity(new ServerResponse("Ocorreu um erro ao listar os equipamentos: \n\n"+ e.getClass().getName()+"\n"+e.getMessage())).build();
		}
		finally{
			fechaConexaoBD();
		}		
		return response;
	}
	

	@GET
	@Path("/combustiveis_e_lubrificantes/listar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarCombustiveisLubrificantes() {
		
		if (noSession()) return redirectTo();
		
		Response response = null;
		
		try {
			abreConexaoBD();
			CombustivelLubrificacaoDAO dao = new CombustivelLubrificacaoDAO(conn);
			List<CombustivelLubrificante>  lista = dao.listarCombustiveisElubrificantes();
			response = Response.status(Status.OK).entity(lista).build();
		} 
		catch (Exception e) {
			e.printStackTrace();
			response = Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity(new ServerResponse("Ocorreu um erro ao listar Combustíveis e Lubrificantes: \n\n"+ e.getClass().getName()+"\n"+e.getMessage())).build();
		}
		finally{
			fechaConexaoBD();
		}		
		return response;
	}
	
	
	@GET
	@Path("/postosAbastecimento/listar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarPostos() {
		
		if (noSession()) return redirectTo();
		
		Response response = null;
		
		try {
			abreConexaoBD();
			PostoDAO dao = new PostoDAO(conn);
			List<PostoAbastecimento> lista = dao.listar(getObraUsuario());
			response = Response.status(Status.OK).entity(lista).build();
		} 
		catch (Exception e) {
			e.printStackTrace();
			response = Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity(new ServerResponse("Ocorreu um erro ao listar os postos: \n\n"+ e.getClass().getName()+"\n"+e.getMessage())).build();
		}
		finally{
			fechaConexaoBD();
		}		
		return response;
	}
	
	
	
	
	@GET
	@Path("/postosAbastecimento/combustiveisLubrificantes/listar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarCombustiveisLubrificantesDoPosto() {
		
		if (noSession()) return redirectTo();
		
		Response response = null;
		
		try {
			abreConexaoBD();
			PostoDAO dao = new PostoDAO(conn);
			List<CombustivelPosto> lista = dao.listarCombustiveisLubrificantesDoPosto(getObraUsuario());
			response = Response.status(Status.OK).entity(lista).build();
		} 
		catch (Exception e) {
			e.printStackTrace();
			response = Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity(new ServerResponse("Ocorreu um erro ao listar os combustiveis e lubrificantes do posto: \n\n"+ e.getClass().getName()+"\n"+e.getMessage())).build();
		}
		finally{
			fechaConexaoBD();
		}		
		return response;
	}


	
	//EXISTE UM BUG NA API JERSEY AO FAZER PARSE DE VALORES STRING 
	//PARA OUTROS TIPOS COMO INTEGER POR EXEMPLO
	//O QUE ACABA CAUSANDO UM RETORNO 400 BAD REQUEST POR ISSO TODOS 
	//OS GET PARAMETERS DO METODO ESTAO EM TIPO STRING E O PARSE É FEITO
	//POSETERIORMENTE
	@POST
	@Path("/equipamento/cadastra")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response cadastrarEquipamento(
			@FormParam("prefixo")String prefixo,
			@FormParam("descricao")String descricao,
			@FormParam("categoria")String categoria,
			@FormParam("fornecedor")String fornecedor,
			@FormParam("tipo")String tipo,
			@FormParam("unidadeMedida")String unidadeMedida,
			@FormParam("chassi")String chassi,
			@FormParam("placa")String placa,
			@FormParam("fabricante")String fabricante,
			@FormParam("ano")String ano,
			@FormParam("controleLubrificacao")String controleLubrificacao,
		@FormParam("qrCode")String qrCode) 
	{
		if (noSession()) return redirectTo();
		
		if(tipo.length() == 0) erroMsg.add("Tipo é um campo obrigatório.");
		
			if(prefixo.length() == 0) erroMsg.add("Prefixo é um campo obrigatório.");
		
				if(descricao.length() == 0) erroMsg.add("Descrição é um campo obrigatório.");
		
					if(categoria.endsWith("0")) erroMsg.add("Categoria precisa ser informada.");
		
						if(fornecedor.equals("0")) erroMsg.add("Fornecedor precisa ser informado.");
		
							if(fabricante.equals("0")) erroMsg.add("Fabricante precisa ser informado.");
		
								if(unidadeMedida.equals("0")) erroMsg.add("Unidade de medida precisa ser informado.");
		
									if(erroMsg.size() > 0){
										return Response.status(Response.Status.BAD_REQUEST).entity(new ServerResponse(getErroMsg())).build();
									}
									
		Response response = null;

		if(ano == null || ano.length() == 0) ano = "0";
		
		try {
			abreConexaoBD();
			EquipamentoDAO dao = new EquipamentoDAO(conn);
			
			Equipamento equipamento = new Equipamento(
					descricao
					,Integer.parseInt(categoria)
					,qrCode
					,new Fornecedor(Integer.parseInt(fornecedor))
					,tipo
					,unidadeMedida
					,chassi
					,new Fabricante(Integer.parseInt(fabricante))
					,placa
					,prefixo
					,Integer.parseInt(ano)
					,controleLubrificacao				
					);
		
			dao.cadastrarEquipamento(equipamento,getObraUsuario());
			response = Response.status(Status.OK).entity(new ServerResponse("Cadastrado com sucesso!")).build();
		} 
		catch (SQLException e) {
			e.printStackTrace();
			response = Response.status(Status.INTERNAL_SERVER_ERROR).entity(new ServerResponse("Ocorreu um erro ao cadastrar o Equipamento: \n\n"+e.getMessage())).build();
		}
		finally {
			fechaConexaoBD();
		}
		return response;
	}
	
	
	@POST
	@Path("/pessoal/cadastra")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response cadastrarPessoal(
			@DefaultValue("") @FormParam("origem") String origem,
			@DefaultValue("") @FormParam("nome") String nome,
			@DefaultValue("") @FormParam("matricula") String matricula,
			@FormParam("senha") String senha,
			@FormParam("dataNascimento") String dataNascimento,
			@FormParam("sexo") String sexo,
			@FormParam("rg") String rg,
			@FormParam("cpf") String cpf,
			@FormParam("qrCode") String qrCode)
	{
		if (noSession()) return redirectTo();
		
		if(origem.length() == 0) erroMsg.add("Origem é um campo obrigatório.");
		
			if(nome.length() == 0) erroMsg.add("Nome é um campo obrigatório.");
		
				if(matricula.length() == 0) erroMsg.add("Matrícula é um campo obrigatório.");
		
					if(erroMsg.size() > 0){
						return Response.status(Response.Status.BAD_REQUEST).entity(new ServerResponse(getErroMsg())).build();
					}
		Response response = null;					
		
		try {
			abreConexaoBD();
			PessoalDAO dao = new PessoalDAO(conn);
			Pessoal pessoa = new Pessoal(null,origem,nome,matricula,senha,dataNascimento,sexo,rg,cpf,qrCode);
			dao.cadastrarPessoa(pessoa, getObraUsuario());
			response = Response.status(Status.OK).entity(new ServerResponse("Cadastrado com sucesso!")).build();
		}
		catch (SQLException e) {
			e.printStackTrace();
			response = Response.status(Status.INTERNAL_SERVER_ERROR).entity(new ServerResponse("Ocorreu um erro ao cadastrar a Pessoa: \n\n"+e.getMessage())).build();
		}
		finally {
			fechaConexaoBD();
		}
		return response;
	}
	
	
	@POST
	@Path("/contrato/cadastra")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response cadastrarContrato(
			@FormParam("numero") String numero,
			@FormParam("tipo") String tipo,
			@FormParam("fonecedor") String fonecedor,
			@FormParam("objeto") String objeto,
			@FormParam("tipoPeriodo") String tipoPeriodo,
			@FormParam("prazo") String prazo,
			@FormParam("dataInicio") String dataInicio,
			@FormParam("dataTermino") String dataTermino,
			@FormParam("valorGlobal") String valorGlobal)
	{
		if (noSession()) return redirectTo();
		
		if(numero.length() == 0) erroMsg.add("Número Contrato é um campo obrigatório.");
		
			if(fonecedor.equals("0")) erroMsg.add("Fornecedor é um campo obrigatório.");
		
				if(prazo.equals("0")) erroMsg.add("Prazo é um campo obrigatório.");
		
					if(erroMsg.size() > 0){
						return Response.status(Response.Status.BAD_REQUEST).entity(new ServerResponse(getErroMsg())).build();
					}
					
		Response response = null;
					
		try {
			abreConexaoBD();
			ContratoDAO dao = new ContratoDAO(conn);
			Contrato contrato = new Contrato(numero,getObraUsuario(),tipo,new Fornecedor(Integer.parseInt(fonecedor)),objeto,tipoPeriodo,Integer.parseInt(prazo),dataInicio,dataTermino,valorGlobal);
			dao.cadastrarContrato(contrato);
			response = Response.status(Status.OK).entity(new ServerResponse("Cadastrado com sucesso!")).build();
		}
		catch (SQLException e) {
			e.printStackTrace();
			response = Response.status(Status.INTERNAL_SERVER_ERROR).entity(new ServerResponse("Ocorreu um erro ao cadastrar o Contrato: \n\n"+e.getMessage())).build();
		}
		finally{
			fechaConexaoBD();
		}
		return response;
	}

	
	@POST
	@Path("/contrato/itensContrato/equipamento")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response cadastrarItensDeContrato_Equipamento(
			@FormParam("numero") String numero,
			@FormParam("categoria") String categoria,
			@FormParam("quantidade") String quantidade,
			@FormParam("valorMensal") String valorMensal,
			@FormParam("valorHoraNormal") String valorHoraNormal,
			@FormParam("valorHoraExtra") String valorHoraExtra,
			@FormParam("horaMinima") String horaMinima,
			@FormParam("foneceOperador") String foneceOperador)
	{
		if (noSession()) return redirectTo();
		
		if(numero.equals("0")) erroMsg.add("Número do contrato é um campo obrigatório.");
		
			if(categoria.equals("0")) erroMsg.add("Categoria de equipamento é um campo obrigatório.");
		
				if(erroMsg.size() > 0){
					return Response.status(Response.Status.BAD_REQUEST).entity(new ServerResponse(getErroMsg())).build();
				}
				
				
		Response response = null;
		
		try {
			abreConexaoBD();
			ContratoDAO dao = new ContratoDAO(conn);
			
			ContratoItensEquipamento cItemEqpmt = 
					new ContratoItensEquipamento(
							getObraUsuario()
							,new Contrato(numero)
							,Integer.parseInt(categoria)
							,Integer.parseInt(quantidade)
							,TextUtil.toENmoney(valorMensal)
							,TextUtil.toENmoney(valorHoraNormal)
							,TextUtil.toENmoney(valorHoraExtra)
							,Integer.parseInt(horaMinima)
							,foneceOperador);
			
			dao.cadastrar_item_Equipamento(cItemEqpmt);
			response = Response.status(Status.OK).entity(new ServerResponse("Cadastrado com sucesso!")).build();
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
			response = Response.status(Status.INTERNAL_SERVER_ERROR).entity(new ServerResponse("Ocorreu um erro ao cadastrar o item no Contrato: \n\n"+e.getMessage())).build();
		}
		catch (java.lang.NumberFormatException e) {
			
			response = Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity(new ServerResponse("Verifique os campos Valor Mensal, Valor Hora Normal, Valor Hora Extra, Horas Mínimas, se não há valores favor informar ZERO. \n\n"+e.getMessage())).build();
		}
		finally{
			fechaConexaoBD();
		}		
		return response;
	}
	

	@POST
	@Path("/contrato/alocar/equipamento")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response cadastrarAlocacao_Equipamentos(
			@FormParam("numero") String numero,
			@FormParam("categoria") String categoria,
			@FormParam("equipamento") String equipamento,
			@FormParam("dataIngresso") String dataIngresso,
			@FormParam("nfEntrada") String nfEntrada,
			@FormParam("kmEntrada") String kmEntrada,
			@FormParam("horimetroEntrada") String horimetroEntrada,
			@FormParam("tipoMedicao") String tipoMedicao,
			@FormParam("realizaManutencao") String realizaManutencao,
			@FormParam("tipoControleConsumo") String tipoControleConsumo)
	{
		if (noSession()) return redirectTo();
		
		if(numero.equals("0")) erroMsg.add("Número do contrato é um campo obrigatório.");
		
			if(categoria.equals("0")) erroMsg.add("Categoria de equipamento é um campo obrigatório.");
		
				if(equipamento.equals("0")) erroMsg.add("Equipamento é um campo obrigatório.");
		
					if(dataIngresso.equals("0")) erroMsg.add("Data de ingresso é um campo obrigatório.");
		
						if(tipoMedicao.equals("0")) erroMsg.add("Tipo de Medição é um campo obrigatório.");
		
							if(kmEntrada == null || kmEntrada.equals("")) kmEntrada = "0";
		
								if(horimetroEntrada == null || horimetroEntrada.equals("")) horimetroEntrada = "0.00";
		
									if(erroMsg.size() > 0){
										return Response.status(Response.Status.BAD_REQUEST).entity(new ServerResponse(getErroMsg())).build();
									}
		Response response = null;									
		
		try {
			
			abreConexaoBD();
			ContratoDAO dao = new ContratoDAO(conn);
			
			ContratoAlocacaoEquipamento cae = new ContratoAlocacaoEquipamento(
					getObraUsuario()
					,new Contrato(numero)
					,new Integer(categoria)
					,new Equipamento(new Integer(equipamento))
					,dataIngresso
					,new Integer(kmEntrada)
					,new BigDecimal(horimetroEntrada)
					,nfEntrada
					,tipoMedicao
					,realizaManutencao
					,tipoControleConsumo
			);
			
			dao.cadastrar_alocacao_Equipamento(cae);
			response = Response.status(Status.OK).entity(new ServerResponse("Cadastrado com sucesso!")).build();

		} catch (Exception e) {
			
			String msgExtra = "";
			if(e instanceof NumberFormatException) msgExtra="Você deve ter digitado informações não numéricas em campos que somente aceita valores numéricos.";
			
			e.printStackTrace();
			response = Response.status(Status.INTERNAL_SERVER_ERROR).entity(new ServerResponse("Ocorreu um erro ao alocar o equipamento no Contrato: \n\n"+ e.getClass().getName()+"\n"+e.getMessage()+"\n"+msgExtra)).build();
		}
		finally{
			fechaConexaoBD();
		}			
		
		
		return response;
	}
	
	

	
	
	@POST
	@Path("/contrato/alocar/pessoa")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response cadastrarAlocacao_Pessoal(
			@FormParam("numero")String numero,
			@FormParam("idPessoa") String idPessoa,
			@FormParam("funcao")String funcao,
			@FormParam("tipoMedicao")String tipoMedicao,
			@FormParam("dataIngresso")String dataIngresso) 
	{
		if (noSession()) return redirectTo();
		
		if(numero.equals("0")) erroMsg.add("Número do contrato é um campo obrigatório.");
		
			if(idPessoa.equals("0")) erroMsg.add("Nome do funcionário é um campo obrigarório.");
		
				if(funcao.equals("0")) erroMsg.add("Funcão é um campo obrigarório.");
		
					if(tipoMedicao.equals("0")) erroMsg.add("Tipo medição é um campo obrigatório.");
		
						if(dataIngresso.length() == 0) erroMsg.add("Data de ingresso é um campo obrigatório.");
		
							if(erroMsg.size() > 0){
								return Response.status(Response.Status.BAD_REQUEST).entity(new ServerResponse(getErroMsg())).build();
							}
							
		Response response = null;
		
		try {
			abreConexaoBD();
			ContratoDAO dao = new ContratoDAO(conn);
			ContratoAlocacaoPessoal cap = new ContratoAlocacaoPessoal(
					getObraUsuario()
					,new Contrato(numero)
					,new Pessoal(new Integer(idPessoa),"")
					,dataIngresso
					,tipoMedicao
					,new Funcao(new Integer(funcao), "")
					);

			dao.cadastrar_alocacao_Pessoa(cap,getObraUsuario());
			response = Response.status(Status.OK).entity(new ServerResponse("Cadastrado com sucesso!")).build();
		} 
		catch (Exception e) {
			e.printStackTrace();
			String msgExtra ="";
			if(e instanceof NumberFormatException) msgExtra="Você deve ter digitado informações não numéricas em campos que somente aceita valores numéricos.";
			e.printStackTrace();
			response = Response.status(Status.INTERNAL_SERVER_ERROR).entity(new ServerResponse("Ocorreu um erro ao alocar a pessoa no Contrato: \n\n"+ e.getClass().getName()+"\n"+e.getMessage()+"\n"+msgExtra)).build();			
		}
		finally{
			fechaConexaoBD();
		}			
		return response;
	}

	

	
	
	@POST
	@Path("/postosAbastecimento/cadastrar")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response cadastrarPostoAbastecimento(
			@FormParam("equipamento") String equipamento,
			@FormParam("descricao") String descricao,
			@FormParam("tipo") String tipo) {
		
		if (noSession()) return redirectTo();
		
		if(equipamento.equals("0")) erroMsg.add("Prefixo/ Equipamento é um campo obrigatório.");
		
			if(tipo.equals("0")) erroMsg.add("Tipo é um campo obrigatório.");
		
				if(descricao.length() == 0) erroMsg.add("Descrição é um campo obrigatório.");
		
					if(erroMsg.size() > 0){
						return Response.status(Response.Status.BAD_REQUEST).entity(new ServerResponse(getErroMsg())).build();
					}
					
		Response response = null;					
		
		try {
			abreConexaoBD();
			PostoDAO dao = new PostoDAO(conn);
			PostoAbastecimento pa = new PostoAbastecimento(getObraUsuario(),tipo,new Equipamento(new Integer(equipamento)),descricao);
			dao.cadastra(pa);
			response = Response.status(Status.OK).entity(new ServerResponse("Cadastrado com sucesso!")).build();
		} 
		catch (SQLException e) {
			e.printStackTrace();
			response = Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity(new ServerResponse("Ocorreu um erro ao salvar o posto: \n\n"+ e.getClass().getName()+"\n"+e.getMessage())).build();			
		}
		finally{
			fechaConexaoBD();
		}		
		return response;
	}

	
	@POST
	@Path("/postosAbastecimento/adicionarCombustiveisLubrificantes")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response adicionarCombustiveisLubrificantes(
			@FormParam("equipamentoPosto")String equipamentoPosto,
			@FormParam("combustivelLubrificante")List<String> combustivelLubrificante)
	{
		if (noSession()) return redirectTo();
		
		if(equipamentoPosto.equals("0")) erroMsg.add("Equipamento Posto é um campo obrigatório.");
			
			if(combustivelLubrificante == null || combustivelLubrificante.size() == 0) erroMsg.add("Combustível/ Lubrificante é um campo obrigatório.");
		
				if(erroMsg.size() > 0){
					return Response.status(Response.Status.BAD_REQUEST).entity(new ServerResponse(getErroMsg())).build();
				}
				
		Response response = null;
		
		try {
			
			abreConexaoBD();
			PostoDAO dao = new PostoDAO(conn);
			dao.adicionarCombustiveisLubrificantes(getObraUsuario(), new Integer(equipamentoPosto), combustivelLubrificante);
			response = Response.status(Status.OK).entity(new ServerResponse("Cadastrado com sucesso!")).build();
		} 
		catch (SQLException e) {
			e.printStackTrace();
			response = Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity(new ServerResponse("Ocorreu um erro ao salvar o posto: \n\n"+ e.getClass().getName()+"\n"+e.getMessage())).build();				
		}
		finally{
			fechaConexaoBD();
		}		
				
		return response;
	}
	
	//DELETES ------------------------------------------------------------------------------------------
	@DELETE
	@Path("/equipamento/remove/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeEquipamento(@PathParam("id")String id){
		
		if (noSession()) return redirectTo();
		
		Response response = null;
		try {
			abreConexaoBD();
			EquipamentoDAO dao = new EquipamentoDAO(conn);
			dao.removeEquipamento(getObraUsuario(),id);
			response = Response.status(Status.OK).entity(new ServerResponse("Equipamento removido com sucesso!")).build();
		} 
		catch (SQLException e) {
			e.printStackTrace();
			response = Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity(new ServerResponse("Ocorreu um erro ao deletar o equipamento: \n\n"+ e.getClass().getName()+"\n"+e.getMessage())).build();				
		}
		finally{
			
			fechaConexaoBD();
		}		
		return response;
	}	
	
	
	
	@DELETE
	@Path("/fornecedor/remove/{id}/{codigoDeq}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeFornecedor(@PathParam("id")String id,@PathParam("codigoDeq")String codigoDeq){
		
		if (noSession()) return redirectTo();
		
		Response response = null;
		
		if(("null").equals(codigoDeq)) codigoDeq = null;
		
		try {
			abreConexaoBD();
			
			FornecedorDAO dao = new FornecedorDAO(conn);
			dao.removeFornecedor(getObraUsuario(),id,codigoDeq);
			response = Response.status(Status.OK).entity(new ServerResponse("Fornecedor removido com sucesso!")).build();
		} 
		catch (SQLException e) {
			e.printStackTrace();
			response = Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity(new ServerResponse("Ocorreu um erro ao deletar o fornecedor: \n\n"+ e.getClass().getName()+"\n"+e.getMessage())).build();				
		}
		finally{
			
			fechaConexaoBD();
		}		
		return response;
	}
	
	
	@DELETE
	@Path("/pessoal/remove/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response removePessoal(@PathParam("id")String id){
		
		if (noSession()) return redirectTo();
		
		Response response = null;
		
		try {
			abreConexaoBD();
			PessoalDAO dao = new PessoalDAO(conn);
			dao.removePessoa(getObraUsuario(),id);
			response = Response.status(Status.OK).entity(new ServerResponse("Pessoa foi removida com sucesso!")).build();
		} 
		catch (SQLException e) {
			e.printStackTrace();
			response = Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity(new ServerResponse("Ocorreu um erro ao deletar a Pessoa: \n\n"+ e.getClass().getName()+"\n"+e.getMessage())).build();				
		}
		finally{
			fechaConexaoBD();
		}		
		return response;
	}
	
	
	@DELETE
	@Path("/contrato/remove/{numeroContrato  : .+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeContrato(@PathParam("numeroContrato")String numeroContrato){
		
		if (noSession()) return redirectTo();
		
		Response response = null;
		
		try {
			abreConexaoBD();
			
			ContratoDAO dao = new ContratoDAO(conn);
			dao.removeContrato(getObraUsuario(),numeroContrato);
			response = Response.status(Status.OK).entity(new ServerResponse("Contrato foi removido com sucesso!")).build();
		} 
		catch (SQLException e) {
			e.printStackTrace();
			response = Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity(new ServerResponse("Ocorreu um erro ao deletar o Contrato: \n\n"+ e.getClass().getName()+"\n"+e.getMessage())).build();				
		}
		finally{
			
			fechaConexaoBD();
		}		
		
		return response;
	}
	
	
	
	@DELETE
	@Path("/contrato/itensContrato/equipamento/remove/{numeroContrato  : .+}/{categoria}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeItensContratoEquipamento(@PathParam("numeroContrato")String numeroContrato, @PathParam("categoria")String categoria){
		
		if (noSession()) return redirectTo();
		
		Response response = null;
		
		try {
			abreConexaoBD();
			
			ContratoDAO dao = new ContratoDAO(conn);
			dao.removeItensContrato_equipamento(getObraUsuario(),numeroContrato,categoria);
			response = Response.status(Status.OK).entity(new ServerResponse("Item foi removido com sucesso!")).build();
		} 
		catch (SQLException e) {
			e.printStackTrace();
			response = Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity(new ServerResponse("Ocorreu um erro ao deletar o item: \n\n"+ e.getClass().getName()+"\n"+e.getMessage())).build();				
		}
		finally{
			
			fechaConexaoBD();
		}		
		
		return response;
	}
	
	
	
	
	@DELETE
	@Path("/contrato/alocacao/equipamento/remove/{numeroContrato  : .+}/{categoria}/{equipamento}/{dataIngresso}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeAlocacaoContratoEquipamento(
			  @PathParam("numeroContrato")String numeroContrato
			, @PathParam("categoria")String categoria
			, @PathParam("equipamento")String equipamento
			, @PathParam("dataIngresso")String dataIngresso)
	{
		
		if (noSession()) return redirectTo();
		
		Response response = null;
		
		try {
			abreConexaoBD();
			
			ContratoDAO dao = new ContratoDAO(conn);
			dao.removeAlocadoEquipamento_contrato(getObraUsuario(),numeroContrato,categoria,equipamento,dataIngresso);
			response = Response.status(Status.OK).entity(new ServerResponse("Equipamento foi removido do contrato com sucesso!")).build();
		} 
		catch (SQLException e) {
			e.printStackTrace();
			response = Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity(new ServerResponse("Ocorreu um erro ao remover o Equipamento do contrato: \n\n"+ e.getClass().getName()+"\n"+e.getMessage())).build();				
		}
		finally{
			fechaConexaoBD();
		}		
		return response;
	}
	
	
	
	@DELETE
	@Path("/contrato/alocacao/pessoal/remove/{numeroContrato  : .+}/{pessoa}/{dataIngresso}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeAlocacaoContratoEquipamento(
			 @PathParam("numeroContrato")String numeroContrato
			,@PathParam("pessoa")String pessoa
			,@PathParam("dataIngresso")String dataIngresso)
	{
		if (noSession()) return redirectTo();
		
		Response response = null;
		
		try {
			abreConexaoBD();
			ContratoDAO dao = new ContratoDAO(conn);
			dao.removeAlocadoPessoa_contrato(getObraUsuario(),numeroContrato,pessoa,dataIngresso);
			response = Response.status(Status.OK).entity(new ServerResponse("Pessoa foi removida do contrato com sucesso!")).build();
		} catch (SQLException e) {
			e.printStackTrace();
			response = Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity(new ServerResponse("Ocorreu um erro ao remover a Pessoa do contrato: \n\n"+ e.getClass().getName()+"\n"+e.getMessage())).build();			
		}
		finally{
			fechaConexaoBD();
		}		
		return response;
	}
	
	
	@DELETE
	@Path("/postosAbastecimento/remove/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response removePostoAbastecimento(@PathParam("id")String id) {
		
		if (noSession()) return redirectTo();
		
		Response response = null;
		
		try {
			abreConexaoBD();
			PostoDAO dao = new PostoDAO(conn);
			dao.removePosto(getObraUsuario(),id);
			response = Response.status(Status.OK).entity(new ServerResponse("Posto foi removido com sucesso!")).build();
		} 
		catch (SQLException e) {
			e.printStackTrace();
			response = Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity(new ServerResponse("Ocorreu um erro ao deletar o Posto: \n\n"+ e.getClass().getName()+"\n"+e.getMessage())).build();			
		}
		finally{
			fechaConexaoBD();
		}		
		return response;
	}
	

	@DELETE
	@Path("/postosAbastecimento/combustiveisLubrificantes/remove/{idCombustivel}/{idPosto}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeCombustivelLubrificantePosto(@PathParam("idCombustivel")String idCombustivel, @PathParam("idPosto")String idPosto) {
		
		if (noSession()) return redirectTo();
		
		Response response = null;
		
		try {
			abreConexaoBD();
			PostoDAO dao = new PostoDAO(conn);
			dao.removeCombustivelLubrificante(idCombustivel,idPosto);
			response = Response.status(Status.OK).entity(new ServerResponse("Combustível/ Lubrificante foi removido com sucesso!")).build();
		} 
		catch (SQLException e) {
			e.printStackTrace();
			response = Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity(new ServerResponse("Ocorreu um erro ao deletar Combustível/ Lubrificante: \n\n"+ e.getClass().getName()+"\n"+e.getMessage())).build();			
		}
		finally{
			fechaConexaoBD();
		}		
		return response;
	}
	
	
	
	@PUT
	@Path("/finalizar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response finalizarCarga() {
		
		if (noSession()) return redirectTo();
		
		Response response = null;		
		
		try {
			abreConexaoBD();
			UsuarioDAO dao = new UsuarioDAO(conn);
			dao.encerrarCarga(getObraUsuario());

			ServerResponse sr = new ServerResponse("Carga encerrada com sucesso!\nVocê não terá mais acesso ao sistema.",request.getContextPath()+"/index.html");
			sr.setRedirect(true);			
			
			response = Response.status(Status.OK).entity(sr).build();
		} 
		catch (SQLException e) {
			e.printStackTrace();
			response = Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity(new ServerResponse("Ocorreu um erro ao encerrar a carga: \n\n"+ e.getClass().getName()+"\n"+e.getMessage())).build();				
		}
		finally{
			fechaConexaoBD();
		}			
		
		return response;
	}
	
	//---------------------------------------------------------------------------------------------------------------------------
	
	public boolean noSession(){

		if(request.getSession() != null && request.getSession().getAttribute("usuario") == null){
			return true;
		}
		else{
			return false;
		}
	}	
	
	public Response redirectTo(){
		ServerResponse sr = new ServerResponse("Sua sessão expirou.",request.getContextPath()+"/index.html");
		sr.setRedirect(true);
		return Response.status(Status.UNAUTHORIZED).entity(sr).build();
	}
	
	
	@GET
	@Path("/logoff")
	@Produces(MediaType.APPLICATION_JSON)
	public Response sair() throws IOException {
		request.getSession().invalidate();
		return Response.status(Status.OK).entity(new ServerResponse(request.getContextPath()+"/index.html")).build();
	}
	
	

	
	@POST
	@Path("/autenticar")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response autenticarUsuario(@FormParam("login")String login, @FormParam("senha") String senha) {
		
		Response response = null;
		request.getSession().invalidate();
		
		if(login == null || login.length() == 0) erroMsg.add("Usuário não foi informado.");
		
			if(senha == null || senha.length() == 0) erroMsg.add("Senha não foi informado.");
		
				if(erroMsg.size() > 0) return Response.status(Response.Status.BAD_REQUEST).entity(new ServerResponse(getErroMsg())).build();		
		
		try {
			
			abreConexaoBD();
			UsuarioDAO dao = new UsuarioDAO(conn);
			u = dao.usuarioAutentico(login, senha);
			if(u == null) {
				fechaConexaoBD(); 
				return Response.status(Response.Status.UNAUTHORIZED).entity(new ServerResponse("Login ou senha inválidos.")).build();
			}
			if(u.getCargaFinalizada().equals("S")){ 
				fechaConexaoBD(); 
				return Response.status(Response.Status.UNAUTHORIZED).entity(new ServerResponse("Você não tem mais acesso pois a carga foi finalizada.")).build();
			}
			request.getSession().setAttribute("usuario", u);
			request.getSession().setMaxInactiveInterval(60*20);
			response = Response.status(Status.OK).entity(new ServerResponse("restrito/carga/inicio.html")).build();
		} 
		catch (SQLException e) {
			e.printStackTrace();
			response = Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity(new ServerResponse("Ocorreu um erro ao salvar o posto: \n\n"+ e.getClass().getName()+"\n"+e.getMessage())).build();			
		}
		finally{
			
			fechaConexaoBD();
		}		
		return response;
	}
	
	
	private String getObraUsuario(){
		String ccObra = "0";

		try{
			ccObra = ((Usuario)request.getSession().getAttribute("usuario")).getCcObra();
		}catch (Exception e){
			request.getSession().invalidate();
			System.err.println(e.getMessage());
		}
		return ccObra;
	}	
	
}