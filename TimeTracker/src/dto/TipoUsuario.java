package dto;

public enum TipoUsuario {
	ADMIN(1),FUNCIONARIO(2);
	
	private int id;
	private TipoUsuario(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	
	public static TipoUsuario getFromId(int id) {
		for (TipoUsuario tu : values()) {
			if (tu.getId() == id) {
				return tu;
			}
		}
		return null;
	}
}
