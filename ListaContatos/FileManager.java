package ListaContatos;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class FileManager {
	
	
	public static boolean createCsv(ListaContatos contatos) {
		
		try {
			
			String conteudo = "id,nome,telefone";
			
			for (int i = 0; i < contatos.getListaContatos().size(); i++) {
				conteudo += "\n"+contatos.getListaContatos().get(i).getId()+","+contatos.getListaContatos().get(i).getNome()+","+contatos.getListaContatos().get(i).getTelefone();
			}
			
			Path endereco = Paths.get("ContatosCSV.csv");	
			
			Files.write(endereco, conteudo.getBytes());
			
			return true;
		} catch (Exception e) {
			System.out.println("Erro na criacao do csv");
			return false;
		}
		
	}
	public static List<Contato> readCsv() {
		List<Contato> contatos = new ArrayList<Contato>();
		
		try {
			
			List<String> linhas = Files.readAllLines(Paths.get("ContatosCSV.csv"));
	
			for (int i = 1; i < linhas.size(); i++) {
				Contato c = new Contato();
				
			    String[] colunas = linhas.get(i).split(",");
			    
			    c.setId(Integer.parseInt(colunas[0]));
			    c.setNome(colunas[1]);
			    c.setTelefone(colunas[2]);
			    
			    contatos.add(c);
			}
		} catch (Exception e) {
			System.out.println("Erro na leitura de arquivos CSV");
		}
		
		return contatos;
	}
	
	public static boolean createJson(ListaContatos contatos) {
		
		try {
			
	        Gson gson = new GsonBuilder().setPrettyPrinting().create();
	        String json = gson.toJson(contatos.getListaContatos());
	
	        Files.write(Paths.get("ContatosJSON.json"), json.getBytes());
	        
	        return true;
		} catch (Exception e) {
			System.out.println("Erro na criacao JSON");
			return false;
		}
	}
	public static void readJson() {
		
		try {
			
			
			
		} catch (Exception e) {
			System.out.println("Erro na leitura JSON");
		}
		
	}
	
}
