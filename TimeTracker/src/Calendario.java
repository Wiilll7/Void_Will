import java.util.Arrays;

public class Calendario {

	ListaTarefa[] dias;

	public Calendario(ListaTarefa[] dias, int mes) {
		setDias(dias, mes);
	}
	public Calendario() {
		
	}
	
	public ListaTarefa[] getDias() {
		return dias;
	}
	public void setDias(ListaTarefa[] dias, int mes) {
		this.dias[mes] = dias[mes];
	}
	
	public void colatarTarefas(int mes) {
		// conctar bd
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Calendario [dias=");
		builder.append(Arrays.toString(dias));
		builder.append("]");
		return builder.toString();
	}
	
}
