package dto;

import java.time.LocalDateTime;

public class Tempo {
	// Attributes
	private int id;
	private int tarefa_id;
	private LocalDateTime data_inicial;
	private LocalDateTime data_final;

	// Constructor
	public Tempo(int id,int tarefa_id,LocalDateTime data_inicial, LocalDateTime data_final) {
		setId(id);
		setTarefaId(tarefa_id);
		setDataInicial(data_inicial);
		setDataFinal(data_final);
	}
	
	// Getters
	public int getId() {
		return id;
	}
	public int getTarefaId() {
		return tarefa_id;
	}
	public LocalDateTime getDataInicial() {
		return data_inicial;
	}
	public LocalDateTime getDataFinal() {
		return data_final;
	}
	
	// Setters
	public void setId(int id) {
		this.id = id;
	}
	public void setTarefaId(int tarefa_id) {
		this.tarefa_id = tarefa_id;
	}
	public void setDataInicial(LocalDateTime data_inicial) {
		this.data_inicial = data_inicial;
	}
	public void setDataFinal(LocalDateTime data_final) {
		this.data_final = data_final;
	}
}
