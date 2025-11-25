package dto;

import java.time.LocalDateTime;
import java.util.List;

public class Tarefa {
	// Attributes
	List<Tempo> tempo;
	String titulo;
	String descricao;
	String comentario;
	LocalDateTime data_entrega;
	TipoAtividade tipo_atividade;
	Estado estado;
	Dificuldade dificuldade;
	
	
	// Constructor
	public Tarefa(List<Tempo> tempo, String titulo, String descricao, String comentario, LocalDateTime data_entrega, TipoAtividade tipo_atividade, Estado estado, Dificuldade dificuldade) {
		setTempo(tempo);
		setTitulo(titulo);
		setDescricao(descricao);
		setData_entrega(data_entrega);
		setTipo_atividade(tipo_atividade);
		setEstado(estado);
		setDificuldade(dificuldade);
	}

	
	// Getters
	public List<Tempo> getTempo() {
		return tempo;
	}
	public String getTitulo() {
		return titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public String getComentario() {
		return comentario;
	}
	public LocalDateTime getData_entrega() {
		return data_entrega;
	}
	public TipoAtividade getTipo_atividade() {
		return tipo_atividade;
	}
	public Estado getEstado() {
		return estado;
	}
	public Dificuldade getDificuldade() {
		return dificuldade;
	}

	
	// Setters
	public void setTempo(List<Tempo> tempo) {
		this.tempo = tempo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public void setData_entrega(LocalDateTime data_entrega) {
		this.data_entrega = data_entrega;
	}
	public void setTipo_atividade(TipoAtividade tipo_atividade) {
		this.tipo_atividade = tipo_atividade;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public void setDificuldade(Dificuldade dificuldade) {
		this.dificuldade = dificuldade;
	}
}
