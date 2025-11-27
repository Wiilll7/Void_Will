package dto;

public class TipoAtividade {
	// Attributes
	private int id;
	private String nome;
	private String descricao;
	
	// Constructor
	public TipoAtividade(int id,String nome, String descricao) {
		setId(id);
		setNome(nome);
		setDescricao(descricao);
	}
	
	// Getters
	public int getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public String getDescricao() {
		return descricao;
	}
	
	// Setters
	public void setId(int id) {
		this.id = id;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
