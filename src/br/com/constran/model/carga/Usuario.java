package br.com.constran.model.carga;

public class Usuario {
	
	private Integer idUsuario;
	private String nome;
	private String login;
	private String senha;
	private String email;
	private String grupo;
	
	private String ccObra;
	private String cargaFinalizada;
	
	
	public Usuario (){
		
	}
	
	
	
	
	public Usuario(Integer idUsuario, String nome, String login, String senha,
			String grupo, String ccObra, String cargaFinalizada) {
		super();
		this.idUsuario = idUsuario;
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.grupo = grupo;
		this.ccObra = ccObra;
		this.cargaFinalizada = cargaFinalizada;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}
	
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getGrupo() {
		return grupo;
	}
	
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	public String getCcObra() {
		return ccObra;
	}
	public void setCcObra(String ccObra) {
		this.ccObra = ccObra;
	}

	public String getCargaFinalizada() {
		return cargaFinalizada;
	}

	public void setCargaFinalizada(String cargaFinalizada) {
		this.cargaFinalizada = cargaFinalizada;
	}
	
	
	
}
