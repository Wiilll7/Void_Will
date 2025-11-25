package dto;

public class TipoAtividade {
	// Attributes
	String nome;
	String descricao;
	
	// Constructor
	public TipoAtividade(String nome, String descricao) {
		setNome(nome);
		setDescricao(descricao);
	}
	
	// Getters
	public String getNome() {
		return nome;
	}
	public String getDescricao() {
		return descricao;
	}
	
	// Setters
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
