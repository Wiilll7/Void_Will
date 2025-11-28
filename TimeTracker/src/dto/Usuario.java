package dto;


public class Usuario {
	// Attributes
	private int id;
	private String nome;
	private String senha;
	private TipoUsuario tipo;
	
	
	// Constructor
	public Usuario(int id,String nome, String senha, TipoUsuario tipo) {
		setId(id);
		setNome(nome);
		setSenha(senha);
		setTipo(tipo);
	}


	// Getters
	public int getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public String getSenha() {
		return senha;
	}
	public TipoUsuario getTipo() {
		return tipo;
	}


	// Setters
	public void setId(int id) {
		this.id = id;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}
}
