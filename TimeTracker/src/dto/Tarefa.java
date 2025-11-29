package dto;

import java.time.LocalDateTime;

public class Tarefa {
	// Attributes
	private int id;
	private String titulo;
	private String descricao;
	private LocalDateTime data_entrega;
	private Estado estado;
	private Dificuldade dificuldade;
	private TipoAtividade tipo_atividade;
	

	// Constructor
	public Tarefa(int id, String titulo, String descricao, LocalDateTime data_entrega, Estado estado, Dificuldade dificuldade, TipoAtividade tipo_atividade) {
		setId(id);
		setTitulo(titulo);
		setDescricao(descricao);
		setDataEntrega(data_entrega);
		setEstado(estado);
		setDificuldade(dificuldade);
		setTipoAtividade(tipo_atividade);
	}
        public Tarefa() {
            
	}

	
	// Getters
	public int getId() {
		return id;
	}
	public String getTitulo() {
		return titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public LocalDateTime getDataEntrega() {
		return data_entrega;
	}
	public Estado getEstado() {
		return estado;
	}
	public Dificuldade getDificuldade() {
		return dificuldade;
	}
	public TipoAtividade getTipoAtividade() {
		return tipo_atividade;
	}
	
	
	// Setters
	public void setId(int id) {
		this.id = id;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public void setDataEntrega(LocalDateTime data_entrega) {
		this.data_entrega = data_entrega;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public void setDificuldade(Dificuldade dificuldade) {
		this.dificuldade = dificuldade;
	}
	public void setTipoAtividade(TipoAtividade tipo_atividade) {
		this.tipo_atividade = tipo_atividade;
	}
}
