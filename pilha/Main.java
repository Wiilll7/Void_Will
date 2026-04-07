package EstruturasLineares.pilha;

public class Main {

	public static void main(String[] args) {
		/*
		PilhaDinamica p = new PilhaDinamica();
		
		p.push(1);
		p.push(2);
		p.push(3);
		p.push(4);
		p.push(5);
		p.push(6);
		p.push(7);

		p.mostrarLista();
		
		System.out.println("");
		
		PilhaEstatica pe = new PilhaEstatica(5);
		
		pe.push(1);
		pe.push(2);
		pe.push(3);
		pe.push(4);
		pe.push(5);
		
		pe.mostrarLista();
		
		pe.push(6);
		
		System.out.println("-----------------");
		System.out.println("-----------------");
		System.out.println("-----------------");
		
		IPilha p1 = new PilhaDinamica();
		executarTestes(p1);
		
		IPilha p2 = new PilhaEstatica(5);
		executarTestes(p2); */
		String str = "relog o cara mais gay do mundo";
		PilhaEstatica p = new PilhaEstatica(str.length());
		
		p.inverterTexto(str);
		
	}
	
	public static void executarTestes(IPilha p) {
		p.push(1);
		p.push(2);
		p.push(3);
		p.push(4);
		p.push(5);
		
		p.mostrarLista();
		
		System.out.println("Valor do Topo: "+p.peek());
		System.out.println("Desempilhado: "+p.pop());
		
		p.mostrarLista();
	}

}
