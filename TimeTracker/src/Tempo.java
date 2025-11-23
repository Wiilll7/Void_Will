import java.time.LocalDateTime;

public class Tempo {
	
	LocalDateTime data_inicial;
	LocalDateTime data_final;

	
	public Tempo(LocalDateTime data_inicial, LocalDateTime data_final) {
		setData_inicial(data_inicial);
		setData_final(data_final);
	}
	
	public Tempo() {
		
	}
	
	
	public LocalDateTime getData_inicial() {
		return data_inicial;
	}
	public void setData_inicial(LocalDateTime data_inicial) {
		this.data_inicial = data_inicial;
	}
	public LocalDateTime getData_final() {
		return data_final;
	}
	public void setData_final(LocalDateTime data_final) {
		this.data_final = data_final;
	}

	
	public void terminarTempo() {
		
	}

	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Tempo [data_inicial=");
		builder.append(data_inicial);
		builder.append("]");
		return builder.toString();
	}
	
}
