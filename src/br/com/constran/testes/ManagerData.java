package br.com.constran.testes;

import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;


@WebServlet("/ManagerData")
public class ManagerData extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       

    public ManagerData() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	
    protected boolean processRequestPost(HttpServletRequest request, HttpServletResponse response) throws Exception {

        boolean statusOK = true;
        
        //try {
        
            BufferedReader reader = request.getReader();
            
            Gson gson = new Gson();
            
            //String line = "";
            
            //while(reader.readLine() != null){
                //System.out.print(reader.readLine());
            //}
            
            StringBuilder builder = new StringBuilder();
            String aux = "";

            while ((aux = reader.readLine()) != null) {
                builder.append(aux);
            }

            String text = builder.toString();
            System.out.println(text);


            //reader = request.getReader();
            //ExportMobileDate exmd = gson.fromJson(reader, ExportMobileDate.class);

            //if (exmd != null) {

                //String directoryServer = (System.getProperty("os.name").contains("Windows")) ? "C:/" : "/opt/tomcat7/webapps/Files/";
                //directoryServer = directoryServer.concat(exmd.getCcObra()).concat("/web/").concat(Util.getFoldersByDate(exmd.getDate()));

                //File f = new File(directoryServer);
                //if (!f.exists() && !f.isDirectory()) {
                    //statusOK = f.mkdir();
                //}

                //String nameFile = Util.getFileExportFormated(exmd.getCcObra(), exmd.getDispositivo(), exmd.getDate());
                //statusOK = FileUtil.save(exmd, directoryServer, nameFile);
            //}
        //} catch (Exception e) {

            //statusOK = false;
            //e.printStackTrace();
            //return statusOK;
        //}
            
        return statusOK;
    }	
	

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOError, IOException {

        try {
            boolean statusOK = processRequestPost(request, response);
            if (statusOK) {
                response.setContentType("text/plain;charset=utf-8");
                response.setCharacterEncoding("UTF-8");
                response.setStatus(response.SC_OK);
                return;
            } else {
                response.setContentType("text/plain;charset=utf-8");
                response.setCharacterEncoding("UTF-8");
                response.setStatus(response.SC_INTERNAL_SERVER_ERROR, "Erro ao gerar arquivo");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
