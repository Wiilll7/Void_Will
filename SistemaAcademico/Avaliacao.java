package MainPackage.AgregacaoComposicao.SistemaAcademico;

import java.util.List;

public class Avaliacao {

	private String nome;
	private float nota;
	private List<Professor> professores;
	private List<Aluno> alunos;
	
	public Avaliacao(String nome, float nota, List<Professor> professores, List<Aluno> alunos) {
		this.nome = nome;
		this.nota = nota;
		this.professores = professores;
		this.alunos = alunos;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public float getNota() {
		return nota;
	}
	public void setNota(float nota) {
		this.nota = nota;
	}
	public List<Professor> getProfessores() {
		return professores;
	}
	public List<Aluno> getAlunos() {
		return alunos;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Avaliacao [nome=");
		builder.append(nome);
		builder.append(", nota=");
		builder.append(nota);
		builder.append(", professores=");
		builder.append(professores);
		builder.append(", alunos=");
		builder.append(alunos);
		builder.append("]");
		return builder.toString();
	}
	
}
