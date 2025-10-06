package MainPackage.AgregacaoComposicao.SistemaAcademico;

public class Aluno{

	private String nome;
	
	public Aluno(String nome) {
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
		builder.append("Aluno [nomeAluno=");
		builder.append(nome);
		builder.append("]");
		return builder.toString();
	}
	
}
