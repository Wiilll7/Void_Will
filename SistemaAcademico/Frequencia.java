package MainPackage.AgregacaoComposicao.SistemaAcademico;

import java.util.List;

public class Frequencia {

	private boolean presente;
	private List<Professor> professores;
	private List<Aluno> alunos;
	
	public Frequencia(boolean presente, List<Professor> professores, List<Aluno> alunos) {
		this.presente = presente;
		this.professores = professores;
		this.alunos = alunos;
	}
	
	public boolean isPresente() {
		return presente;
	}
	public void setPresente(boolean presente) {
		this.presente = presente;
	}
	public List<Professor> getProfessores() {
		return professores;
	}
	public List<Aluno> getAlunos() {
		return alunos;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Frequencia [presente=");
		builder.append(presente);
		builder.append(", professores=");
		builder.append(professores);
		builder.append(", alunos=");
		builder.append(alunos);
		builder.append("]");
		return builder.toString();
	}
	
}
