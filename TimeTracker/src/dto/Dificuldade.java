package dto;

public enum Dificuldade {
	FACIL(1),MEDIO(2),DIFICIL(3);
	
	private int id;
	
	private Dificuldade(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public static Dificuldade getFromId(int id) {
		for (Dificuldade d : values()) {
			if (d.getId() == id) {
				return d;
			}
		}
		return null;
	}
	
}
