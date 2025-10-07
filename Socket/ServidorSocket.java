package Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.ArrayList;
import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class ServidorSocket {
	
	public static void main(String[] args) throws IOException {
		
		ServerSocket servidor = null;
		Socket conexao = null;
		BufferedReader entrada = null;
		List<Evento> eventos = new ArrayList<>();
		DateTimeFormatter zoneFormated = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss Z");
		
		try {
			
			servidor = new ServerSocket(1306);
			conexao = servidor.accept();
			entrada = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
			PrintStream saida = new PrintStream(conexao.getOutputStream());
	
			do {
				
				String texto = entrada.readLine();
				Evento evento = new Evento(texto);
				
				if (texto.equals("data")) {
					
					texto = entrada.readLine();
					ZonedDateTime fuso = ZonedDateTime.parse(texto);
					String pog = fuso.format(zoneFormated);
					for (int i = 0; i < eventos.size(); i++) {
						
						if (eventos.get(i).getData().getZone().getId().equals(pog)) {
							saida.println(eventos.get(i).toString());
						}
						
					}
					
				} else {
				
					boolean v = true;
					
					for (int i = 0; i < eventos.size(); i++) {
						
						if (eventos.get(i).verificaEvento(evento)) {
							v = false;	
							break;
						}
						
					}
					
					if (v) {	
						eventos.add(evento);
						saida.println(evento);
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