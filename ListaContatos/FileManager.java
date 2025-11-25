package ListaContatos;


import java.nio.file.*;
import java.util.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.yaml.snakeyaml.*;
import org.yaml.snakeyaml.constructor.Constructor;
import java.io.*;

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
	public static List<Contato> readJson() {
		List<Contato> contatos = new ArrayList<Contato>();
		
		try {
			
			 String json = Files.readString(Paths.get("ContatosJSON.json"));
		     Gson gson = new Gson();
		     contatos = gson.fromJson(json, new TypeToken<List<Contato>>(){}.getType());

			
		} catch (Exception e) {
			System.out.println("Erro na leitura JSON");
		}
		
		return contatos;
	}
	
	public static boolean createYaml(ListaContatos contatos) {
		
		try {
			
	        DumperOptions options = new DumperOptions();
	        options.setIndent(2);
	        options.setPrettyFlow(true);
	        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
	        Yaml yaml = new Yaml(options);

	        FileWriter writer = new FileWriter("ContatosYaml.yaml");
	        yaml.dump(contatos, writer);
			
	        return true;
		} catch (Exception e) {
			System.out.println("Erro na criacao do Yaml");
			return false;
		}
		
	}
	
	public static List<Contato> readYaml() {

	    try {
	        String yamlContent = Files.readString(Paths.get("ContatosYaml.yaml"));
	        
	        LoaderOptions options = new LoaderOptions();
	        options.setTagInspector(tag -> true);
	        Constructor constructor = new Constructor(ListaContatos.class, options);
	        Yaml yaml = new Yaml(constructor);

	        ListaContatos obj = yaml.load(yamlContent);

	        return obj.getListaContatos();

	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Erro ao ler YAML");
	        return new ArrayList<>();
	    }
	}


	
}
