package MainPackage.AgregacaoComposicao.SistemaAcademico;

public class Professor {

	private String nome;

	public Professor(String nome) {
		super();
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Professor [nome=");
		builder.append(nome);
		builder.append("]");
		return builder.toString();
	}
	
}
