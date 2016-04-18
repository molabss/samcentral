package br.com.constran.mobile.persistence.vo.imp.json;

import br.com.constran.mobile.persistence.vo.EquipamentoCategoriaVO;
import br.com.constran.mobile.persistence.vo.ManutencaoServicoPorCategoriaEquipamentoVO;
import br.com.constran.mobile.persistence.vo.ManutencaoServicosVO;
import br.com.constran.mobile.persistence.vo.ObraVO;
import br.com.constran.mobile.persistence.vo.aprop.PrevisaoServicoVO;
import br.com.constran.mobile.persistence.vo.aprop.maodeobra.*;
import br.com.constran.mobile.persistence.vo.imp.*;
import br.com.constran.mobile.persistence.vo.rae.abs.*;

import java.util.Collection;

public class ImportMobile {

    private Collection<ObraVO> obras;
    private Collection<FrenteObraVO> frentesObra;
    private Collection<EquipamentoVO> equipamentos;
    private Collection<MaterialVO> materiais;
    private Collection<AtividadeVO> atividades;
    private Collection<OrigemDestinoVO> origensDestinos;
    private Collection<PessoalVO> pessoas;
    private Collection<UsuarioVO> usuarios;
    private Collection<UsuarioVO> usuariosPessoal;
    private Collection<ComponenteVO> componentes;
    private Collection<ParalisacaoVO> paralisacoes;
    private Collection<ServicoVO> servicos;
    private Collection<PostoVO> postos;
    private Collection<CombustivelLubrificanteVO> combustiveis;
    private Collection<CombustivelPostoVO> combustiveisPostos;
    private Collection<CompartimentoVO> compartimentos;
    private Collection<LubrificacaoEquipamentoVO> lubrificacoesEquipamento;
    private Collection<JustificativaOperadorVO> justificativasOperador;
    private Collection<PreventivaEquipamentoVO> preventivas;

    //novas tabelas servico e mao de obra
    private Collection<HorarioTrabalhoVO> horariosTrabalhos;
    private Collection<PeriodoHorarioTrabalhoVO> periodoHorarioTrabalhos;
    private Collection<EquipeTrabalhoVO> equipesTrabalhos;
    private Collection<AtividadeServicoVO> atividadeServicos;
    private Collection<IntegranteEquipeVO> integrantesEquipe;
    private Collection<PrevisaoServicoVO> previsaoServicos;


    //modulo de manutencao
    private Collection<ManutencaoServicosVO> manutencaoServicos;
    private Collection<EquipamentoCategoriaVO> equipamentoCategorias;
    private Collection<ManutencaoServicoPorCategoriaEquipamentoVO>servicosPorCategoriaEquipamento;


    public Collection<ManutencaoServicosVO> getManutencaoServicos() {
        return manutencaoServicos;
    }

    public void setManutencaoServicos(Collection<ManutencaoServicosVO> manutencaoServicos) {
        this.manutencaoServicos = manutencaoServicos;
    }

    public Collection<EquipamentoCategoriaVO> getEquipamentoCategorias() {
        return equipamentoCategorias;
    }

    public void setEquipamentoCategorias(Collection<EquipamentoCategoriaVO> equipamentoCategorias) {
        this.equipamentoCategorias = equipamentoCategorias;
    }

    public Collection<ManutencaoServicoPorCategoriaEquipamentoVO> getServicosPorCategoriaEquipamento() {
        return servicosPorCategoriaEquipamento;
    }

    public void setServicosPorCategoriaEquipamento(Collection<ManutencaoServicoPorCategoriaEquipamentoVO> servicosPorCategoriaEquipamento) {
        this.servicosPorCategoriaEquipamento = servicosPorCategoriaEquipamento;
    }

    public Collection<ObraVO> getObras() {
        return obras;
    }

    public void setObras(Collection<ObraVO> obras) {
        this.obras = obras;
    }

    public Collection<FrenteObraVO> getFrentesObra() {
        return frentesObra;
    }

    public void setFrentesObra(Collection<FrenteObraVO> frentesObra) {
        this.frentesObra = frentesObra;
    }

    public Collection<EquipamentoVO> getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(Collection<EquipamentoVO> equipamentos) {
        this.equipamentos = equipamentos;
    }

    public Collection<MaterialVO> getMateriais() {
        return materiais;
    }

    public void setMateriais(Collection<MaterialVO> materiais) {
        this.materiais = materiais;
    }

    public Collection<AtividadeVO> getAtividades() {
        return atividades;
    }

    public void setAtividades(Collection<AtividadeVO> atividades) {
        this.atividades = atividades;
    }

    public Collection<OrigemDestinoVO> getOrigensDestinos() {
        return origensDestinos;
    }

    public void setOrigensDestinos(Collection<OrigemDestinoVO> origensDestinos) {
        this.origensDestinos = origensDestinos;
    }

    public Collection<UsuarioVO> getUsuarios() {
        return usuarios;
    }

    public Collection<PessoalVO> getPessoas() {
        return pessoas;
    }

    public void setUsuarios(Collection<UsuarioVO> usuarios) {
        this.usuarios = usuarios;
    }

    public Collection<UsuarioVO> getUsuariosPessoal() {
        return usuariosPessoal;
    }

    public void setUsuariosPessoal(Collection<UsuarioVO> usuariosPessoal) {
        this.usuariosPessoal = usuariosPessoal;
    }

    public Collection<ComponenteVO> getComponentes() {
        return componentes;
    }

    public void setComponentes(Collection<ComponenteVO> componentes) {
        this.componentes = componentes;
    }

    public Collection<ParalisacaoVO> getParalisacoes() {
        return paralisacoes;
    }

    public void setParalisacoes(Collection<ParalisacaoVO> paralisacoes) {
        this.paralisacoes = paralisacoes;
    }

    public Collection<ServicoVO> getServicos() {
        return servicos;
    }

    public void setServicos(Collection<ServicoVO> servicos) {
        this.servicos = servicos;
    }

    public Collection<PostoVO> getPostos() {
        return postos;
    }

    public void setPostos(Collection<PostoVO> postos) {
        this.postos = postos;
    }

    public Collection<CombustivelLubrificanteVO> getCombustiveis() {
        return combustiveis;
    }

    public void setCombustiveis(Collection<CombustivelLubrificanteVO> combustiveis) {
        this.combustiveis = combustiveis;
    }

    public Collection<CombustivelPostoVO> getCombustiveisPostos() {
        return combustiveisPostos;
    }

    public void setCombustiveisPostos(
            Collection<CombustivelPostoVO> combustiveisPostos) {
        this.combustiveisPostos = combustiveisPostos;
    }

    public Collection<CompartimentoVO> getCompartimentos() {
        return compartimentos;
    }

    public void setCompartimentos(Collection<CompartimentoVO> compartimentos) {
        this.compartimentos = compartimentos;
    }

    public Collection<LubrificacaoEquipamentoVO> getLubrificacoesEquipamento() {
        return lubrificacoesEquipamento;
    }

    public void setLubrificacoesEquipamento(
            Collection<LubrificacaoEquipamentoVO> lubrificacoesEquipamento) {
        this.lubrificacoesEquipamento = lubrificacoesEquipamento;
    }

    public Collection<JustificativaOperadorVO> getJustificativasOperador() {
        return justificativasOperador;
    }

    public void setJustificativasOperador(
            Collection<JustificativaOperadorVO> justificativasOperador) {
        this.justificativasOperador = justificativasOperador;
    }

    public Collection<PreventivaEquipamentoVO> getPreventivas() {
        return preventivas;
    }

    public void setPreventivas(Collection<PreventivaEquipamentoVO> preventivas) {
        this.preventivas = preventivas;
    }

    public void setPessoas(Collection<PessoalVO> pessoas) {
        this.pessoas = pessoas;
    }

    public Collection<HorarioTrabalhoVO> getHorariosTrabalhos() {
        return horariosTrabalhos;
    }

    public void setHorariosTrabalhos(Collection<HorarioTrabalhoVO> horariosTrabalhos) {
        this.horariosTrabalhos = horariosTrabalhos;
    }

    public Collection<PeriodoHorarioTrabalhoVO> getPeriodoHorarioTrabalhos() {
        return periodoHorarioTrabalhos;
    }

    public void setPeriodoHorarioTrabalhos(Collection<PeriodoHorarioTrabalhoVO> periodoHorarioTrabalhos) {
        this.periodoHorarioTrabalhos = periodoHorarioTrabalhos;
    }

    public Collection<EquipeTrabalhoVO> getEquipesTrabalhos() {
        return equipesTrabalhos;
    }

    public void setEquipesTrabalhos(Collection<EquipeTrabalhoVO> equipesTrabalhos) {
        this.equipesTrabalhos = equipesTrabalhos;
    }

    public Collection<AtividadeServicoVO> getAtividadeServicos() {
        return atividadeServicos;
    }

    public void setAtividadeServicos(Collection<AtividadeServicoVO> atividadeServicos) {
        this.atividadeServicos = atividadeServicos;
    }

    public Collection<IntegranteEquipeVO> getIntegrantesEquipe() {
        return integrantesEquipe;
    }

    public void setIntegrantesEquipe(Collection<IntegranteEquipeVO> integrantesEquipe) {
        this.integrantesEquipe = integrantesEquipe;
    }

    public Collection<PrevisaoServicoVO> getPrevisaoServicos() {
        return previsaoServicos;
    }

    public void setPrevisaoServicos(Collection<PrevisaoServicoVO> previsaoServicos) {
        this.previsaoServicos = previsaoServicos;
    }


}
