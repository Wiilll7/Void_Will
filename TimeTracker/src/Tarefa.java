import java.time.LocalDateTime;

public class Tarefa {

	Tempo tempo;
	String titulo;
	String descricao;
	LocalDateTime data_entrega;
	String tipo_atividade;
	String estado;
	String dificuldade;
	
	public Tarefa(Tempo tempo, String titulo, String descricao, LocalDateTime data_entrega, String tipo_atividade, String estado, String dificuldade) {
		setTempo(tempo);
		setTitulo(titulo);
		setDescricao(descricao);
		setData_entrega(data_entrega);
		setTipo_atividade(tipo_atividade);
		setEstado(estado);
		setDificuldade(dificuldade);
	}
	
	public Tarefa(Tempo tempo, String titulo, LocalDateTime data_entrega, String tipo_atividade, String dificuldade) {
		setTempo(tempo);
		setTitulo(titulo);
		setData_entrega(data_entrega);
		setTipo_atividade(tipo_atividade);
		setDificuldade(dificuldade);
	}
	
	public Tarefa() {
		
	}
	
	public Tempo getTempo() {
		return tempo;
	}
	public void setTempo(Tempo tempo) {
		this.tempo = tempo;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		if (titulo.length() > 3)
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		if (descricao.length() > 5)
		this.descricao = descricao;
	}
	public LocalDateTime getData_entrega() {
		return data_entrega;
	}
	public void setData_entrega(LocalDateTime data_entrega) {
		this.data_entrega = data_entrega;
	}
	public String getTipo_atividade() {
		return tipo_atividade;
	}
	public void setTipo_atividade(String tipo_atividade) {
		if (tipo_atividade.length() > 3)
		this.tipo_atividade = tipo_atividade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		if (estado.length() > 4)
		this.estado = estado;
	}
	public String getDificuldade() {
		return dificuldade;
	}
	public void setDificuldade(String dificuldade) {
		if (dificuldade.length() > 4)
		this.dificuldade = dificuldade;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Tarefa [tempo=");
		builder.append(tempo);
		builder.append(", titulo=");
		builder.append(titulo);
		builder.append(", descricao=");
		builder.append(descricao);
		builder.append(", data_entrega=");
		builder.append(data_entrega);
		builder.append(", tipo_atividade=");
		builder.append(tipo_atividade);
		builder.append(", estado=");
		builder.append(estado);
		builder.append(", dificuldade=");
		builder.append(dificuldade);
		builder.append("]");
		return builder.toString();
	}
	
}
