package ListaContatos;

public class Contato {

	private int id;
	private String nome;
	private String email;
	private String telefone;
	private String dataNascimento;
	
	
	public Contato(int id, String nome, String email, String telefone, String dataNascimento) {
		setId(id);
		setNome(nome);
		setEmail(email);
		setTelefone(telefone);
		setDataNascimento(dataNascimento);
	}
	public Contato(int id, String nome, String telefone) {
		setId(id);
		setNome(nome);
		setTelefone(telefone);
	}
	public Contato() {

	}	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}


	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Contatos [id=");
		builder.append(id);
		builder.append(", nome=");
		builder.append(nome);
		builder.append(", email=");
		builder.append(email);
		builder.append(", telefone=");
		builder.append(telefone);
		builder.append(", dataNascimento=");
		builder.append(dataNascimento);
		builder.append("]");
		return builder.toString();
	}
	
	
}
