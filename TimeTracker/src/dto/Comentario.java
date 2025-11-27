package dto;

public class Comentario {
	// Attributes
	public int id;
	public int tarefa_id;
	public String descricao;
	
	// Constructor
	public Comentario(int id,int tarefa_id,String descricao) {
		setId(id);
		setTarefaId(tarefa_id);
		setDescricao(descricao);
	}
	
	
	// Getters
	public int getId() {
		return id;
	}
	public int getTarefaId() {
		return tarefa_id;
	}
	public String getDescricao() {
		return descricao;
	}
	
	// Setters
	public void setId(int id) {
		this.id = id;
	}
	public void setTarefaId(int tarefa_id) {
		this.tarefa_id = tarefa_id;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
