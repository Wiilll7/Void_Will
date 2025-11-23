
public class TipoAtividade {

	String nome;
	String descricao;
	
	
	public TipoAtividade(String nome, String descricao) {
		setNome(nome);
		setDescricao(descricao);
	}
	
	public TipoAtividade() {
		
	}
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		if (nome.length() > 4)
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		if (descricao.length() > 5)
		this.descricao = descricao;
	}


	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TipoAtividade [nome=");
		builder.append(nome);
		builder.append(", descricao=");
		builder.append(descricao);
		builder.append("]");
		return builder.toString();
	}
	
}
