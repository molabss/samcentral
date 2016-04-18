package br.com.constran.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import br.com.constran.model.reports.ReportVo;
import br.com.constran.util.dao.AbstractDAO;

public class ReportDAO extends AbstractDAO{

	public ReportDAO(Connection conn) {
		super(conn);
	}

	
	public ReportVo getDadosReport(String id) throws Exception {

		ReportVo report = new ReportVo();
		String arquivo = "";
		String tipo = "";
		List<String> list = new ArrayList<String>();

		cStmt = conn.prepareCall("EXEC selecionaReport ?");
		cStmt.setString(1,id);
		rs = cStmt.executeQuery();
		
		while (rs.next()) {
			arquivo = rs.getString(1);
			tipo = rs.getString(2);
			list.add(rs.getString(3));
		}
		
		report.setNomeArquivo(arquivo);
		report.setTipo(tipo);
		report.setParametros(list);

		return report;
	}	
	
}
