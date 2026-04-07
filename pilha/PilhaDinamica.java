package EstruturasLineares.pilha;

import EstruturasLineares.Nodo;

public class PilhaDinamica implements IPilha {

	private Nodo topo;

	public PilhaDinamica() {
		topo = null;
	}

	public void push(int dado) {
		Nodo novoNodo = new Nodo(dado);
		novoNodo.setProx(topo);
		topo = novoNodo;
	}

	public int pop() {
		
		if (vazia()) {
			System.out.println("A pilha esta vazia");
			return -1;
		}
		
		int valorRemovido = topo.getDado();
		topo = topo.getProx();
		
		return valorRemovido;
	}

	public int peek() {
		
		if (vazia()) {
			System.out.println("A pilha esta vazia");
			return -1;
		}
		
		return topo.getDado();
	}

	public boolean vazia() {
		if (topo == null) {
			return true;
		}
		return false;
	}

	public void mostrarLista() {
		if (vazia()) {
			System.out.println("Lista vazia");
			return;
		}
		
		Nodo aux = topo;
		System.out.println("---Topo(Dinamica)---");
		while (aux != null) {
			System.out.println(aux.getDado());
			aux = aux.getProx();
		}
		System.out.println("---Base---");
	}
	
}
