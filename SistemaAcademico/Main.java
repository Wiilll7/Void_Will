package MainPackage.AgregacaoComposicao.SistemaAcademico;

import java.util.List;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		
		Aluno aluno = new Aluno("Joao");
		List<Aluno> alunos = new ArrayList<>();
		alunos.add(aluno);
		
		Professor professor = new Professor("Curvello");
		List<Professor> professores = new ArrayList<>();
		professores.add(professor);
		
		Avaliacao avaliacao = new Avaliacao("p1", 10, professores, alunos);
		List<Avaliacao> avaliacoes = new ArrayList<>();
		avaliacoes.add(avaliacao);
		
		Frequencia frequencia = new Frequencia(true, professores, alunos);
		List<Frequencia> frequencias = new ArrayList<>();
		frequencias.add(frequencia);
		
		Disciplina disciplina = new Disciplina(frequencias, avaliacoes, professores, alunos);
		List<Disciplina> disciplinas = new ArrayList<>();
		disciplinas.add(disciplina);
		
		Fase fase = new Fase(disciplinas);
		List<Fase> fases = new ArrayList<>();
		fases.add(fase);
		
		Curso curso = new Curso("BCC", fases);
		
		Situacao situacao = new Situacao(alunos, disciplinas);
		
		System.out.println(curso);
		System.out.println(situacao);
		
	}

}
