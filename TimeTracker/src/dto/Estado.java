package dto;

public enum Estado {
	NA_LIXEIRA(1),PENDENTE(2),EM_ATIVIDADE(3),CONCLUIDA(4);
	
	private int id;
	private Estado(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	
	public static Estado getFromId(int id) {
		for (Estado e : values()) {
			if (e.getId() == id) {
				return e;
			}
		}
		return null;
	}
}
