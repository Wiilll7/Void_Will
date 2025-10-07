package Socket;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class Evento {

	private String descricao;
	private String local;
	private ZonedDateTime data;
	private LocalDateTime dataCadastro;
	
	public Evento(String input) {
		String[] split = input.split(";");
		setDescricao(split[0]);
		setLocal(split[1]);
		setData(ZonedDateTime.parse(split[2]));
		setDataCadastro(LocalDateTime.now());
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public ZonedDateTime getData() {
		return data;
	}
	public void setData(ZonedDateTime data) {
		this.data = data;
	}
	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public boolean verificaEvento(Evento evento) {
		return evento.getDescricao().equals(getDescricao());
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Evento [descricao=");
		builder.append(descricao);
		builder.append(", local=");
		builder.append(local);
		builder.append(", data=");
		builder.append(data);
		builder.append(", dataCadastro=");
		builder.append(dataCadastro);
		builder.append("]");
		return builder.toString();
	}
	
}