import java.util.List;

public class Usuario {

	String nome;
	String senha;
	TipoUsuario tipo;
	List<Tarefa> tarefas_habilitadas;
	
	
	public Usuario(String nome, String senha, TipoUsuario tipo, List<Tarefa> tarefas_habilitadas) {
		setNome(nome);
		setSenha(senha);
		setTipo(tipo);
		setTarefas_habilitadas(tarefas_habilitadas);
	}
	
	public Usuario(String nome, String senha, TipoUsuario tipo) {
		setNome(nome);
		setSenha(senha);
		setTipo(tipo);
		setTarefas_habilitadas(tarefas_habilitadas);
	}
	
	public Usuario() {
		
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public TipoUsuario getTipo() {
		return tipo;
	}
	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}
	public List<Tarefa> getTarefas_habilitadas() {
		return tarefas_habilitadas;
	}
	public void setTarefas_habilitadas(List<Tarefa> tarefas_habilitadas) {
		this.tarefas_habilitadas = tarefas_habilitadas;
	}


	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Usuario [nome=");
		builder.append(nome);
		builder.append(", senha=");
		builder.append(senha);
		builder.append(", tipo=");
		builder.append(tipo);
		builder.append(", tarefas_habilitadas=");
		builder.append(tarefas_habilitadas);
		builder.append("]");
		return builder.toString();
	}
	
}
