import java.time.LocalDateTime;

public class Tempo {
	
	LocalDateTime data_inicial;

	public Tempo(LocalDateTime data_inicial) {
		setData_inicial(data_inicial);
	}
	
	public LocalDateTime getData_inicial() {
		return data_inicial;
	}
	public void setData_inicial(LocalDateTime data_inicial) {
		this.data_inicial = data_inicial;
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
