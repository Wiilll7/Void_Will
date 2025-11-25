package dto;

import java.time.LocalDateTime;

public class Tempo {
	// Attributes
	LocalDateTime data_inicial;
	LocalDateTime data_final;

	// Constructor
	public Tempo(LocalDateTime data_inicial, LocalDateTime data_final) {
		setData_inicial(data_inicial);
		setData_final(data_final);
	}
	
	// Getters
	public LocalDateTime getData_inicial() {
		return data_inicial;
	}
	public LocalDateTime getData_final() {
		return data_final;
	}
	
	// Setters
	public void setData_inicial(LocalDateTime data_inicial) {
		this.data_inicial = data_inicial;
	}
	public void setData_final(LocalDateTime data_final) {
		this.data_final = data_final;
	}
}
