package ListaContatos;

import java.util.Scanner;

public class ContatoMain {

	public static void startMenu(Scanner input, ListaContatos lista) {
		boolean fechar = false;
		while (!fechar) {
			System.out.println("╔════════════════════════════════════════════════════════╗");
	        System.out.println("║            Gerenciador de Contatos - Menu              ║");
	        System.out.println("╠════════════════════════════════════════════════════════╣");
	        System.out.println("║  1. Ver contatos                                       ║");
	        System.out.println("║  2. Mudar contatos                                     ║");
	        System.out.println("║  3. Adicionar contatos                                 ║");
	        System.out.println("║  4. Remover contatos                                   ║");
	        System.out.println("║  0. Sair do sistema                                    ║");
	        System.out.println("╚════════════════════════════════════════════════════════╝");
	        
	        
	        int resposta = -1;
	    	while (resposta == -1) {
		        try {
		        	resposta = input.nextInt();
		        } catch (Exception e){
		        	System.out.print("O input precisa ser inteiro\n");
		        }
		        input.nextLine();
	    	}
	        
	        switch (resposta) {
	        default:
	        	System.out.print("Input inválido\n\n");
	        	break;
	        case 0:
	        	System.out.print("Fechando programa...\n\n");
	        	fechar = true;
	        	break;
	        case 1:
	        	verContatos(lista);
	        	break;
	        case 2:
	        	alterarContato(input,lista);
	        	break;
	        case 3:
	        	criarContato(input,lista);
	        	break;
	        case 4:
	        	deletarContato(input,lista);
	        	break;
	        }
	        
	        arrumarIds(lista);
		}
	}
	
	public static void arrumarIds(ListaContatos lista) {
		int i = 0;
		for (Contato contato : lista.getListaContatos()) {
			contato.setId(i);
			i++;
		}
	}
	
	public static Contato selecionarContato(Scanner input,ListaContatos lista) {
		boolean fechar = false;
		
		while (!fechar) {
			verContatos(lista);
			System.out.print("Escolha um contato por id, ou digite -2 para cancelar: ");
			
			int id = -1;
	    	while (id == -1) {
		        try {
		        	id = input.nextInt();
		        } catch (Exception e){
		        	System.out.print("O input precisa ser inteiro\n");
		        }
		        input.nextLine();
	    	}
	    	
	    	if (id == -2) {
	    		System.out.print("Fechando menu...\n\n");
	    		return null;
	    	} else {
	    		Contato target = null;
	    		for (Contato contato : lista.getListaContatos()) {
	    			if (id == contato.getId()) {
	    				target = contato;
	    				break;
	    			}
	    		}
	    		if (target != null) {
	    			return target;
	    		} else {
	    			System.out.print("Não foi possível achar o Contato.\n\n");
	    			return null;
	    		}
	    	}
		}
		
		return null;
	}
	
	public static void verContatos(ListaContatos lista) {
		System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║            Lista de Contatos             ║");
        System.out.println("╚══════════════════════════════════════════╝");
        if (lista.getListaContatos().size() > 0) {
			for (Contato cont : lista.getListaContatos()) {
				System.out.println(cont.toString());
			}
        } else {
        	System.out.println("Sem contatos");
        }
	}
	
	public static void alterarContato(Scanner input,ListaContatos lista) {
		boolean fechar = false;
		while (!fechar) {
			Contato contato = null;
			contato = selecionarContato(input,lista);
			if (contato == null) {
				System.out.print("Não foi possível encontrar o contato...\n\n");
				break;
			}
			
			
			
			System.out.println("╔════════════════════════════════════════╗");
	        System.out.println("║            O que alterar?              ║");
	        System.out.println("╠════════════════════════════════════════╣");
	        System.out.println("║  1. Nome                               ║");
	        System.out.println("║  2. Email                              ║");
	        System.out.println("║  3. Telefone                           ║");
	        System.out.println("║  4. Data de Nascimento                 ║");
	        System.out.println("║  0. Cancelar                           ║");
	        System.out.println("╚════════════════════════════════════════╝");
	        
	        int resposta = -1;
	        try {
	        	resposta = input.nextInt();
	        } catch (Exception e){
	        	System.out.print("O input precisa ser inteiro\n");
	        }
	        input.nextLine();
	        
	        String dado;
	        switch (resposta) {
	        default:
	        	System.out.print("Input inválido\n\n");
	        	break;
	        case 0:
	        	System.out.print("Cancelando...\n\n");
	        	fechar = true;
	        	break;
	        case 1:
	        	System.out.print("Informe o nome: ");
	        	dado = input.nextLine();
	        	contato.setNome(dado);
	        	break;
	        case 2:
	        	System.out.print("Informe o email: ");
	        	dado = input.nextLine();
	        	contato.setEmail(dado);
	        	break;
	        case 3:
	        	System.out.print("Informe o telefone: ");
	        	dado = input.nextLine();
	        	contato.setTelefone(dado);
	        	break;
	        case 4:
	        	System.out.print("Informe a data de nascimento: ");
	        	dado = input.nextLine();
	        	contato.setDataNascimento(dado);
	        	break;
	        
	        }
		}
	}
	
	public static void criarContato(Scanner input,ListaContatos lista) {
		boolean fechar = false;
		while (!fechar) {
			System.out.println("╔════════════════════════════════════════════╗");
	        System.out.println("║            Criar novo contato              ║");
	        System.out.println("╚════════════════════════════════════════════╝");
	        
			String dado;
			Contato contato = new Contato();
			
			System.out.print("Informe o nome: ");
	    	dado = input.nextLine();
	    	contato.setNome(dado);
	    
	    	System.out.print("Informe o email: ");
	    	dado = input.nextLine();
	    	contato.setEmail(dado);
	    	
	    	System.out.print("Informe o telefone: ");
	    	dado = input.nextLine();
	    	contato.setTelefone(dado);
	
	    	System.out.print("Informe a data de nascimento: ");
	    	dado = input.nextLine();
	    	contato.setDataNascimento(dado);
	    	
	    	System.out.print("Prévia:\n"+ contato.toString() +"\nQuer adicionar?\n[1] Adicionar\n[2] Fazer de novo\n[0] Cancelar\n");
	    	
	    	String resposta = input.nextLine();
	        
	        switch (resposta) {
	        default:
	        	System.out.print("Input inválido\n\n");
	        	break;
	        case "0":
	        	System.out.print("Cancelando criação...\n\n");
	        	fechar = true;
	        	break;
	        case "1":
	        	verContatos(lista);
	        	lista.addContato(contato);
	        	System.out.print("Contato adicionado.\nFechando menu de criação...\n\n");
	        	fechar = true;
	        	break;
	        case "2":
	        	System.out.print("Retornando...\n\n");
	        	break;
	        }
		}
	}
	
	public static void deletarContato(Scanner input, ListaContatos lista) {
		boolean fechar = false;
		while (!fechar) {
			System.out.println("╔═════════════════════════════════════════╗");
	        System.out.println("║            Deletar contato              ║");
	        System.out.println("╚═════════════════════════════════════════╝");
	        
	        
	        
	        Contato contato = null;
			contato = selecionarContato(input,lista);
			if (contato == null) {
				System.out.print("Não foi possível encontrar o contato...\n\n");
				break;
			}
	        
	        lista.removeContato(contato);
			System.out.print("Contato removido.\nVoltando para menu...\n\n");
		}
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		ListaContatos lista = new ListaContatos();
		
		startMenu(input,lista);
		input.close();
	}

}
