package MainPackage.AgregacaoComposicao.SistemaAcademico;

import java.util.List;

public class Fase {

	private List<Disciplina> disciplina;

	public Fase(List<Disciplina> disciplina) {
		this.disciplina = disciplina;
	}
	public List<Disciplina> getDisciplina() {
		return disciplina;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Fase [disciplina=");
		builder.append(disciplina);
		builder.append("]");
		return builder.toString();
	}
	
}
