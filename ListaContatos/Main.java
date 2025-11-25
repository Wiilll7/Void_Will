package ListaContatos;

import java.util.Scanner;


public class Main {

	public static void main(String[] args) {

		ListaContatos contatos = new ListaContatos();
		Scanner in = new Scanner(System.in);
		Contato c = new Contato(1, "Willian", "8297342389");
		contatos.addContato(c);
		c = new Contato(2, "Romulo", "128368716");
		contatos.addContato(c);
		
		System.out.println("1. criar csv, 2. ler csv");
		int x = in.nextInt();
		
		switch(x) {
		case 1:
			if (FileManager.createCsv(contatos)) {
				System.out.println("Arquivo criado");
			}
			break;
		case 2:
			contatos.setListaContatos(FileManager.readCsv());
			
			System.out.println(contatos.getListaContatos());
			break;
		case 3:
			if (FileManager.createJson(contatos)) {
				System.out.println("Arquivo criado");
			}
			break;
		case 4:
			contatos.setListaContatos(FileManager.readJson());	
			
			System.out.println(contatos.getListaContatos());
			break;
		case 5:
			if (FileManager.createYaml(contatos)) {
				System.out.println("Arquivo criado");
			}
			break;
		case 6:
			contatos.setListaContatos(FileManager.readYaml());	
			
			System.out.println(contatos.getListaContatos());
			break;
		}
		
		
		in.close();
	}

}
