package MainPackage.AgregacaoComposicao.SistemaAcademico;

import java.util.List;

public class Disciplina {

	private List<Frequencia> frequencias;
	private List<Avaliacao> avaliacoes;
	private List<Professor> professores;
	private List<Aluno> alunos;
	
	public Disciplina(List<Frequencia> frequencias, List<Avaliacao> avaliacoes, List<Professor> professores, List<Aluno> alunos) {
		this.frequencias = frequencias;
		this.avaliacoes = avaliacoes;
		this.professores = professores;
		this.alunos = alunos;
	}
	
	public List<Frequencia> getFrequencias() {
		return frequencias;
	}
	public List<Avaliacao> getAvaliacoes() {
		return avaliacoes;
	}
	public List<Professor> getProfessores() {
		return professores;
	}
	public List<Aluno> getAlunos() {
		return alunos;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Disciplina [frequencias=");
		builder.append(frequencias);
		builder.append(", avaliacoes=");
		builder.append(avaliacoes);
		builder.append(", professores=");
		builder.append(professores);
		builder.append(", alunos=");
		builder.append(alunos);
		builder.append("]");
		return builder.toString();
	}
	
}
