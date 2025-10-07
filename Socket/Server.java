package Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.ArrayList;

public class Server {
	
	public static void main(String[] args) throws IOException {
		
		ServerSocket servidor = null;
		Socket conexao = null;
		BufferedReader entrada = null;
		List<Evento> eventos = new ArrayList<>();
		
		try {
			
			servidor = new ServerSocket(1306);
			conexao = servidor.accept();
			entrada = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
			PrintStream saida = new PrintStream(conexao.getOutputStream());
	
			do {
				
				String texto = entrada.readLine();
				
				if (texto.equals("data")) {
					
					texto = entrada.readLine();
					String resposta = "";
					for (int i = 0; i < eventos.size(); i++) {
						if (texto.equals(eventos.get(i).getFuso())) {
							resposta += eventos.get(i).toString() + ";";
						}
					}
					
					saida.println(resposta);
					
				} else {
					
					Evento evento = new Evento(texto);
					
					boolean v = true;
					
					for (int i = 0; i < eventos.size(); i++) {
						
						if (eventos.get(i).verificaEvento(evento)) {
							v = false;	
							break;
						}
						
					}
					
					if (v) {	
						eventos.add(evento);
					}
					
					for (int i = 0; i < eventos.size(); i++) {
						
						System.out.println(eventos.get(i).toString());
						
					}
				}
			
			} while(true);

		} catch(IOException e) {
			
			System.out.println("Algo errado aconteceu");
			
		} finally {
			
			conexao.close();
			servidor.close();
			
		}
	}
}
