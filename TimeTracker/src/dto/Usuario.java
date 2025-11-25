package dto;

import java.util.List;

public class Usuario {
	// Attributes
	String nome;
	String senha;
	TipoUsuario tipo;
	List<Tarefa> tarefas_habilitadas;
	
	
	// Constructor
	public Usuario(String nome, String senha, TipoUsuario tipo, List<Tarefa> tarefas_habilitadas) {
		setNome(nome);
		setSenha(senha);
		setTipo(tipo);
		setTarefas_habilitadas(tarefas_habilitadas);
	}


	// Getters
	public String getNome() {
		return nome;
	}
	public String getSenha() {
		return senha;
	}
	public TipoUsuario getTipo() {
		return tipo;
	}
	public List<Tarefa> getTarefas_habilitadas() {
		return tarefas_habilitadas;
	}


	// Setters
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}
	public void setTarefas_habilitadas(List<Tarefa> tarefas_habilitadas) {
		this.tarefas_habilitadas = tarefas_habilitadas;
	}
}
