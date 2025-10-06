package MainPackage.AgregacaoComposicao.SistemaAcademico;

import java.util.List;

public class Curso {

	private String nome;
	private List<Fase> fases;
	
	public Curso(String nome, List<Fase> fases) {
		this.nome = nome;
		this.fases = fases;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<Fase> getFases() {
		return fases;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Curso [nome=");
		builder.append(nome);
		builder.append(", fases=");
		builder.append(fases);
		builder.append("]");
		return builder.toString();
	}
	
}
