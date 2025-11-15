import java.util.List;

public class ListaTarefa {

	List<Tarefa> tarefas;

	public ListaTarefa(List<Tarefa> tarefas) {
		setTarefas(tarefas);
	}
	public ListaTarefa() {
		
	}
	
	public List<Tarefa> getTarefas() {
		return tarefas;
	}
	public void setTarefas(List<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}
	
	public void adicionarTarefa(Tarefa tarefa) {
		tarefas.add(tarefa);
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ListaTarefa [tarefas=");
		builder.append(tarefas);
		builder.append("]");
		return builder.toString();
	}
	
}
