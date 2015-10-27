package br.com.calculoareaulceras.model.entity;

public class Medico {
	private int idMedico;
	private String nome;
	private String telefone;
	private String login;
	private String senha;
	private String email;

	public Medico() {
		super();
	}

	public Medico(int idMedico, String nome, String telefone, String login,
			String senha, String email) {
		super();
		this.idMedico = idMedico;
		this.nome = nome;
		this.telefone = telefone;
		this.login = login;
		this.senha = senha;
		this.email = email;
	}

	public int getIdMedico() {
		return idMedico;
	}

	public void setIdMedico(int idMedico) {
		this.idMedico = idMedico;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
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

}
