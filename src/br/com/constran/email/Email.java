package br.com.constran.email;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;




import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import br.com.constran.system.AppDirectory;

public class Email {
	
	private String hostName;
	private String destinatario; 
	private String assunto;
	private String corpoMensagem;
	private String smtpPort;
	private String from;
	private String fromName;
	private HtmlEmail htmlEmail;
	

	
	public Email(String destinatario, String mensagem) {
		
		
		
		this.destinatario = destinatario;
		this.corpoMensagem = mensagem;
		
		setEmailAtribbs();

		try {
			
			htmlEmail = new HtmlEmail();
			htmlEmail.setDebug(false);
			htmlEmail.setHostName(hostName);
			htmlEmail.addTo(destinatario);
			htmlEmail.setFrom(from,fromName);
			htmlEmail.setSmtpPort(Integer.valueOf(smtpPort));
			htmlEmail.setSubject(assunto);
			
			File img = new File(AppDirectory.DIR_IMG_ASSINATURA);
			
			StringBuffer msg = new StringBuffer();
			msg.append("<img src=cid:").append(htmlEmail.embed(img)).append(">");
		
			corpoMensagem = corpoMensagem.replace("#ASSINATURA#", msg.toString());
			
		
			htmlEmail.setMsg(corpoMensagem);
			htmlEmail.setCharset("UTF-8");
			
			
			
	
			
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}
	
	private void setEmailAtribbs(){
    	
		try {
			
	    	InputStream input = Email.class.getClassLoader().getResourceAsStream("email.properties");
			
			/*
	    	if(input==null){
	            System.out.println("Sorry, unable to find ");
	            return;
			}
			*/			
			
			Properties prop = new Properties();
			prop.load(input);
			
			hostName = prop.getProperty("hostname");
			assunto = prop.getProperty("subject");
			smtpPort = prop.getProperty("smtpport");
			from = prop.getProperty("emailfrom");
			fromName = prop.getProperty("fromName");
			
		} catch (IOException e) {
			e.printStackTrace();
		}    	
	}
	
	public void enviar() throws EmailException{

		htmlEmail.send();

	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public HtmlEmail getHtmlEmail() {
		return htmlEmail;
	}

	public void setHtmlEmail(HtmlEmail htmlEmail) {
		this.htmlEmail = htmlEmail;
	}
	

}
