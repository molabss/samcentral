package br.com.constran.controller;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.constran.dao.AtividadeDAO;
import br.com.constran.dao.CombustivelLubrificacaoDAO;
import br.com.constran.dao.CombustivelPostoDAO;
import br.com.constran.dao.CompartimentoDAO;
import br.com.constran.dao.ComponenteDAO;
import br.com.constran.dao.EquipamentoCategoriaDAO;
import br.com.constran.dao.EquipamentoDAO;
import br.com.constran.dao.EquipeTrabalhoDAO;
import br.com.constran.dao.FrenteObraDAO;
import br.com.constran.dao.HorarioTrabalhoDAO;
import br.com.constran.dao.IntegranteEquipeDAO;
import br.com.constran.dao.JustificativaOperadorDAO;
import br.com.constran.dao.LubrificacaoEquipamentoDAO;
import br.com.constran.dao.ManutencaoServicoDAO;
import br.com.constran.dao.ManutencaoServicoPorCategoriaEquipamentoDAO;
import br.com.constran.dao.MaterialDAO;
import br.com.constran.dao.ObraDAO;
import br.com.constran.dao.OrigemDestinoDAO;
import br.com.constran.dao.ParalisacaoDAO;
import br.com.constran.dao.PeriodoHorarioTrabalhoDAO;
import br.com.constran.dao.PostoDAO;
import br.com.constran.dao.PreventivaEquipamentoDAO;
import br.com.constran.dao.PrevisaoServicoDAO;
import br.com.constran.dao.ServicoDAO;
import br.com.constran.dao.UsuarioDAO;
import br.com.constran.mobile.persistence.vo.imp.json.ImportMobile;
import br.com.constran.util.AbstractController;
import br.com.constran.util.ServerResponse;

//http://192.168.1.64:8080/samcentral/ws/importa/dadosParaOtablet?ccObra=303599
@Path("importa")
public class Importador extends AbstractController{
	
	@GET
	@Path("/dadosParaOtablet")
	@Produces(MediaType.APPLICATION_JSON)
	public Response importarDadosParaOtablet(@QueryParam("ccObra")String ccObra){

		ImportMobile impMobile = null;
		Response response = null;
		
		if (ccObra == null || ccObra.isEmpty()){
			response = Response.status(Response.Status.NOT_FOUND).entity(new ServerResponse("Centro de custo da obra não foi informado.")).build();
			return response;
		}

		try {
			
			abreConexaoBD();
			
			ObraDAO obraDAO = new ObraDAO(conn);
			if(obraDAO.naoExisteObra(ccObra)){
				response = Response.status(Response.Status.NOT_FOUND).entity(new ServerResponse("Nenhuma obra foi encontrada com o centro de custo ".concat(ccObra))).build();
				return response;
			}
			
			impMobile = new ImportMobile();
				impMobile.setObras(obraDAO.getObras(ccObra));
					impMobile.setEquipamentos(new EquipamentoDAO(conn).getEquipamentos(ccObra));
						impMobile.setMateriais(new MaterialDAO(conn).getMateriais(ccObra));
							impMobile.setFrentesObra(new FrenteObraDAO(conn).getFrentesObra(ccObra));
								impMobile.setOrigensDestinos(new OrigemDestinoDAO(conn).getOrigensDestinos(ccObra));
									impMobile.setUsuarios(new UsuarioDAO(conn).getUsuarios(ccObra,"U"));
										impMobile.setUsuariosPessoal(new UsuarioDAO(conn).getUsuarios(ccObra,"UP"));
											impMobile.setAtividades(new AtividadeDAO(conn).getAtividades(ccObra));
												impMobile.setComponentes(new ComponenteDAO(conn).getComponentes(ccObra));
													impMobile.setServicos(new ServicoDAO(conn).getServicos(ccObra));
														impMobile.setParalisacoes(new ParalisacaoDAO(conn).getParalisacoes(ccObra));
															impMobile.setCompartimentos(new CompartimentoDAO(conn).getCompartimentos(ccObra));
																impMobile.setPostos(new PostoDAO(conn).getPostos(ccObra));
																	impMobile.setCombustiveis(new CombustivelLubrificacaoDAO(conn).getCombustiveis(ccObra));
																impMobile.setCombustiveisPostos(new CombustivelPostoDAO(conn).getCombustivelPostos(ccObra));
															impMobile.setLubrificacoesEquipamento(new LubrificacaoEquipamentoDAO(conn).getLubrificacaoEquipamento(ccObra));
														impMobile.setPreventivas(new PreventivaEquipamentoDAO(conn).getPreventivas(ccObra));
													impMobile.setJustificativasOperador(new JustificativaOperadorDAO(conn).getJustificativaOperador(ccObra));
												impMobile.setEquipesTrabalhos(new EquipeTrabalhoDAO(conn).getEquipesTrabalho(ccObra));
											impMobile.setPeriodoHorarioTrabalhos(new PeriodoHorarioTrabalhoDAO(conn).getPeriodosHorarioTrabalho(ccObra));
										impMobile.setHorariosTrabalhos(new HorarioTrabalhoDAO(conn).getHorariosTrabalho(ccObra));
									impMobile.setIntegrantesEquipe(new IntegranteEquipeDAO(conn).getIntegrantesEquipe(ccObra));
								impMobile.setPrevisaoServicos(new PrevisaoServicoDAO(conn).getPrevisaoServicos(ccObra));
							impMobile.setManutencaoServicos(new ManutencaoServicoDAO(conn).getManutencaoServicos(ccObra));
						impMobile.setEquipamentoCategorias(new EquipamentoCategoriaDAO(conn).getEquipamentoCategorias(ccObra));
					
						//---Desativado pois em produção não está construída a estrutura de procedures para suportar este novo módulo.
						//***Reativado em 13-04-2016
						impMobile.setServicosPorCategoriaEquipamento(new ManutencaoServicoPorCategoriaEquipamentoDAO(conn).getManutencaoServicosPorCategoriaEquipamento(ccObra));
			
					response = Response.status(Response.Status.OK).entity(impMobile).build();
		} 
		catch (SQLException e) {
			e.printStackTrace();
			response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ServerResponse("Não foi possível recuperar os dados armazenados no banco de dados.\n"+e.getMessage())).build();
		}
		finally{
			fechaConexaoBD();
		}
		
		return response;
	}
}
