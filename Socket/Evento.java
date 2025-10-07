
import java.time.LocalDateTime;

public class Evento {

	private String descricao;
	private String local;
	private LocalDateTime data;
	private String fuso;
	private LocalDateTime dataCadastro;
	
	public Evento(String input) {
		String[] split = input.split(";");
		setDescricao(split[0]);
		setLocal(split[1]);
		setData(LocalDateTime.parse(split[2]));
		setFuso(split[3]);
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
	public LocalDateTime getData() {
		return data;
	}
	public void setData(LocalDateTime data) {
		this.data = data;
	}
	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public String getFuso() {
		return fuso;
	}
	public void setFuso(String fuso) {
		this.fuso = fuso;
	}

	
	
	public boolean verificaEvento(Evento evento) {
		return evento.getDescricao().equals(getDescricao());
	}

	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Evento:\n[descricao: ");
		builder.append(descricao);
		builder.append("]\n[local: ");
		builder.append(local);
		builder.append("]\n[data: ");
		builder.append(data);
		builder.append("]\n[fuso: ");
		builder.append(fuso);
		builder.append("]\n[dataCadastro: ");
		builder.append(dataCadastro);
		builder.append("]");
		return builder.toString();
	}
}