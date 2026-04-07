package EstruturasLineares.pilha;

public class PilhaEstatica implements IPilha {

	
	private int[] dados;
	private int topo;
	private int capacidade;
	
	
	public PilhaEstatica(int capacidade) {
		this.dados = new int[capacidade];
		this.topo = -1; // -1 indica vazia
		this.capacidade = capacidade;
	}

	
	public void push(int dado) {
		if (cheia()) {
			System.out.println("Lista cheia");
			return;
		}
		
		topo++;
		dados[topo] = dado;
	}

	public int pop() {
		if (vazia()) {
			System.out.println("Lista vazia");
			return -1;
		}
		
		int valorRemovido = dados[topo];
		topo--;
		return valorRemovido;
	}

	public int peek() {
		return dados[topo];
	}

	public boolean vazia() {
		if (topo == -1) {
			return true;
		}
		return false;
	}

	public boolean cheia() {
		return topo == capacidade -1;
	}
	
	public void mostrarLista() {
		if (vazia()) {
			System.out.println("A pilha esta vazia");
		}
		
		System.out.println("---Topo(Estatica)---");
		
		for (int i = topo; i >= 0; i--) {
			System.out.println(dados[i]);
		}
		
		System.out.println("---Base---");
		
	}
	
	public void inverterTexto(String str) {
		
		for (int i = 0; i < str.length(); i++) {
			topo++;
			dados[topo] = str.charAt(i);
		}
		
		for (int i = topo; i >= 0; i--) {
			System.out.print((char) dados[i]);
		}
		
	}
}
