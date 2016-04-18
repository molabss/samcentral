package br.com.constran.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.constran.model.carga.Fornecedor;
import br.com.constran.util.dao.AbstractDAO;

public class FornecedorDAO extends AbstractDAO {

	public FornecedorDAO(Connection conn) {
		super(conn);
	}
	
	public void cadastraFornecedor(Fornecedor fornecedor,String cargaParaObra) throws SQLException {
		
		//fornecedor.setDataCadastro((new SimpleDateFormat("yyyy-MM-dd HH:mm:sss").format(new Date())).toString());
		
		
		/*(
			  @razao 				varchar(128)
			, @fantasia 			char(32)
			, @endereco 			varchar(128)
			, @cnpj 				char(18)
			, @inscEstadual 		char(18)
			, @contatos 			varchar(64)
			, @indicacao 			varchar(64)
			, @responsavelLegal 	varchar(40)
			, @responsavelTecnico 	varchar(40)
			, @cargaParaObra 		char(6)
		)
		*/
		
		//StringBuilder insert = new StringBuilder();
		//insert.append("INSERT INTO FORNECEDORES (RAZAO,FANTASIA,ENDERECO,CNPJ,INSCESTADUAL, ");
		//insert.append("CONTATOS,INDICACAO,DATACADASTRO,RESPONSAVELLEGAL,RESPONSAVELTECNICO,cargaParaObra) ");
		//insert.append("VALUES (?,?,?,?,?,?,?,?,?,?,?)");
		
		
		StringBuilder exec = new StringBuilder(Procedures.PROC_INSERT_FORNECEDORES).append("?,?,?,?,?,?,?,?,?,?");
		
	    cStmt = conn.prepareCall(exec.toString());
	    cStmt.setString(1,fornecedor.getRazao());
	    cStmt.setString(2,fornecedor.getFantasia());
	    cStmt.setString(3,fornecedor.getEndereco());
	    cStmt.setString(4,fornecedor.getCnpj());
	    cStmt.setString(5,fornecedor.getInscEstadual());
	    cStmt.setString(6,fornecedor.getContatos());
	    cStmt.setString(7,fornecedor.getIndicacao());
	    cStmt.setString(8,fornecedor.getResponsavelLegal());
	    cStmt.setString(9,fornecedor.getResponsavelTecnico());
	    cStmt.setString(10,cargaParaObra);
		   
		cStmt.execute();	
		
		/*
		prepStmt = conn.prepareStatement(exec.toString());
		prepStmt.setString(1, fornecedor.getRazao());
		prepStmt.setString(2, fornecedor.getFantasia());
		prepStmt.setString(3, fornecedor.getEndereco());
		prepStmt.setString(4, fornecedor.getCnpj());
		prepStmt.setString(5, fornecedor.getInscEstadual());
		prepStmt.setString(6, fornecedor.getContatos());
		prepStmt.setString(7, fornecedor.getIndicacao());
		//prepStmt.setString(8,fornecedor.getDataCadastro());
		prepStmt.setString(8,fornecedor.getResponsavelLegal());
		prepStmt.setString(9, fornecedor.getResponsavelTecnico());
		prepStmt.setString(10, cargaParaObra);
		*/
		
		//prepStmt.executeUpdate();
		liberarRecursosBD();
	}
	
	
	public List<Fornecedor> listar(String cargaParaObra) throws SQLException{
		List<Fornecedor> lista = new ArrayList<Fornecedor>();
		Fornecedor f;
		
		StringBuilder select = new StringBuilder();
		select.append("SELECT IDFORNECEDOR, RAZAO, FANTASIA, ENDERECO, CNPJ, INSCESTADUAL, ");
		select.append("CONTATOS, INDICACAO, DATACADASTRO, RESPONSAVELLEGAL, RESPONSAVELTECNICO, CODIGODEQ ");
		select.append("FROM FORNECEDORES WHERE CARGAPARAOBRA = ?");
		
		prepStmt = conn.prepareStatement(select.toString());
		prepStmt.setString(1, cargaParaObra);
		
		rs = prepStmt.executeQuery();
		while(rs.next()){
			f = new Fornecedor();
			f.setId(rs.getInt("IDFORNECEDOR"));
			f.setRazao(rs.getString("RAZAO"));
			f.setFantasia(rs.getString("FANTASIA"));
			f.setEndereco(rs.getString("ENDERECO"));
			f.setCnpj(rs.getString("CNPJ"));
			f.setInscEstadual(rs.getString("INSCESTADUAL"));
			f.setContatos(rs.getString("CONTATOS"));
			f.setIndicacao(rs.getString("INDICACAO"));
			f.setDataCadastro(rs.getString("DATACADASTRO"));
			f.setResponsavelLegal(rs.getString("RESPONSAVELLEGAL"));
			f.setResponsavelTecnico(rs.getString("RESPONSAVELTECNICO"));
			f.setCodigoDeq(rs.getString("CODIGODEQ"));
			lista.add(f);
		}
		
		liberarRecursosBD();
		return lista;
	}
	
	
	   public void removeFornecedor(String ccObra,String id, String codigoDeq) throws SQLException{
		   
		   StringBuilder deleteCheck = new StringBuilder ();
		   deleteCheck.append("select * from equipamentos where proprietario in ");
		   deleteCheck.append("(select idFornecedor from fornecedores where idFornecedor = ?) and cargaParaObra = ?");
		   prepStmt = conn.prepareStatement(deleteCheck.toString()); 
		   prepStmt.setString(1, id);
		   prepStmt.setString(2, ccObra);
		   if(prepStmt.executeQuery().next()){
			   throw new SQLException("Este Fornecedor não pode ser removido.\n Existem equipamentos vinculados a ele.");
		   }
		   
		   
		   deleteCheck.delete(0, deleteCheck.length());
		   deleteCheck = new StringBuilder ();
		   deleteCheck.append("select * from contratos where fornecedor in ");
		   deleteCheck.append("(select idFornecedor from fornecedores where idFornecedor = ?) and ccObra = ?");
		   prepStmt = conn.prepareStatement(deleteCheck.toString()); 
		   prepStmt.setString(1, id);
		   prepStmt.setString(2, ccObra);		   
		   if(prepStmt.executeQuery().next()){
			   throw new SQLException("Este Fornecedor não pode ser removido.\n\n Existem Contratos vinculados a ele.");
		   }
		   
		   StringBuilder exec = new StringBuilder();
		   
		   //DELETAR USANDO PROCEDURE (BASE DE PRODUCAO) PARA REMOVER TAMBÉM DA BASE DO SAT DEQ VIA LINKED SERVER (VER PROCEDURE)
		   if(codigoDeq != null){
			   //exec.append(Procedures.PROC_DELETE_FORNECEDORES).append("?,?");
			   
			   cStmt = conn.prepareCall(Procedures.PROC_DELETE_FORNECEDORES+" ?,?");
			   cStmt.setString(1, id);
			   cStmt.setString(2, codigoDeq);
			   cStmt.execute();				   
		   }
		   else{
			   
			 //REMOVENDO DA BASE DE TESTE
			   exec.append("delete from fornecedores where idFornecedor = ?");
			   prepStmt = conn.prepareStatement(exec.toString());
			   prepStmt.setString(1, id);
			   prepStmt.executeUpdate();
		   }
		   //prepStmt = conn.prepareStatement(exec.toString());
		   //prepStmt.setString(1, id);
		   //if(codigoDeq != null) prepStmt.setString(2, codigoDeq);
		   //prepStmt.executeQuery();
		   //liberarRecursosBD();
		   
		   liberarRecursosBD();
	   }
}
