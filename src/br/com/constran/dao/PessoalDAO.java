package br.com.constran.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.constran.model.carga.Funcao;
import br.com.constran.model.carga.Pessoal;
import br.com.constran.util.dao.AbstractDAO;

public class PessoalDAO extends AbstractDAO {

	public PessoalDAO(Connection conn) {
		super(conn);
	}
	
	
	public void cadastrarPessoa(Pessoal pessoa,String cargaParaObra) throws SQLException{
		
		StringBuilder insert = new StringBuilder();
		insert.append("INSERT INTO PESSOAL (ORIGEM,NOME,MATRICULA,SENHA, DATANASCIMENTO,SEXO, ");
		insert.append("RG,CPF,QRCODE,cargaParaObra) ");
		insert.append("VALUES (?,?,?,?,?,?,?,?,?,?)");
		
		prepStmt = conn.prepareStatement(insert.toString());
		
		prepStmt.setString(1,pessoa.getOrigem());
		prepStmt.setString(2,pessoa.getNome());
		prepStmt.setString(3,pessoa.getMatricula());
		prepStmt.setString(4,pessoa.getSenha());
		prepStmt.setString(5,pessoa.getDataNascimento());
		//prepStmt.setString(5,null);
		prepStmt.setString(6,pessoa.getSexo());
		prepStmt.setString(7, pessoa.getRg());
		prepStmt.setString(8, pessoa.getCpf());
		prepStmt.setString(9,pessoa.getQrCode());
		prepStmt.setString(10,cargaParaObra);

		prepStmt.executeUpdate();
		
		liberarRecursosBD();		
	}
	
	
	public List<Funcao> listarFuncoes() throws SQLException{
		
		List<Funcao> lista = new ArrayList<Funcao>();
		
		rs = conn.prepareStatement("select idFuncao, descricaoFuncao from funcoes order by descricaoFuncao").executeQuery();
		while(rs.next()){
			lista.add(new Funcao(rs.getInt("idFuncao"), rs.getString("descricaoFuncao")));
		}
		
		liberarRecursosBD();
		return lista;
	}
	
	
	public List<Pessoal> listarPessoasCadastradas(String cargaParaObra) throws SQLException{
		
		List<Pessoal> lista = new ArrayList<Pessoal>();
	
		prepStmt = conn.prepareStatement("select idPessoal, origem,rg, cpf,nome, senha, matricula, sexo,Convert(varchar(10),CONVERT(datetime,dataNascimento,106),103) as dataNascimento, qrCode from pessoal where cargaParaObra = ? order by nome");
	
		prepStmt.setString(1, cargaParaObra);
		
		rs = prepStmt.executeQuery();
		
		while(rs.next()){
			
			lista.add(new Pessoal(
					 rs.getInt("idPessoal")
					,rs.getString("origem") 
					,rs.getString("nome")
					,rs.getString("senha")
					,rs.getString("matricula")
					,rs.getString("dataNascimento")
					,rs.getString("sexo")
					,rs.getString("rg")
					,rs.getString("cpf")
					,rs.getString("qrCode")
					));
		}
		
		liberarRecursosBD();
		return lista;
	}	
	
	
   /*public void removeFornecedor(String ccObra,String id) throws SQLException{
	   
	   StringBuilder deleteCheck = new StringBuilder ();
	   deleteCheck.append("select * from equipamentos where proprietario in ");
	   deleteCheck.append("(select idFornecedor from fornecedores where idFornecedor = ?) and cargaParaObra = ?");
	   
	   prepStmt = conn.prepareStatement(deleteCheck.toString()); 
	   prepStmt.setString(1, id);
	   prepStmt.setString(2, ccObra);

	   if(prepStmt.executeQuery().next()){
		   throw new SQLException("Este Fornecedor não pode ser removido.\n Existem equipamentos vinculados a ele.");
	   }
	   
	   prepStmt = conn.prepareStatement("delete from fornecedores where idFornecedor = ?");
	   prepStmt.setString(1, id);
	   prepStmt.executeUpdate();
	   liberarRecursosBD();
	}*/	
	
	   public void removePessoa(String ccObra, String id) throws SQLException{
		   
		   StringBuilder deleteCheck = new StringBuilder ();
		   deleteCheck.append("select * from pessoasAlocadasContratos where pessoa in ");
		   deleteCheck.append("(select idPessoal from pessoal where idPessoal = ?) and ccObra = ?");
		   
		   prepStmt = conn.prepareStatement(deleteCheck.toString()); 
		   prepStmt.setString(1, id);
		   prepStmt.setString(2, ccObra);

		   if(prepStmt.executeQuery().next()){
			   liberarRecursosBD();
			   throw new SQLException("Esta Pessoa não pode ser removido.\n Ela está vinculada a um contrato.");
		   }		   
		   
		   prepStmt = conn.prepareStatement("delete from pessoal where idPessoal = ?");
		   prepStmt.setString(1, id);
		   prepStmt.executeUpdate();
		   liberarRecursosBD();
	   }
}
