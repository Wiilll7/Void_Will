package other;

import dto.TipoAtividade;
import java.util.List;
import bo.TipoAtividadeBO;
import dto.Usuario;
import bo.UsuarioBO;
import dto.TipoUsuario;
import bo.TarefaBO;
import dto.Tarefa;
import java.time.LocalDateTime;
import dto.Estado;
import dto.Dificuldade;
import dto.TipoAtividade;
import bo.TipoAtividadeBO;
import dto.Comentario;
import bo.ComentarioBO;

public class Main {
    
    public static void main(String[] args) {
        
        /*
        TipoUsuario admin = TipoUsuario.ADMIN;
        TipoUsuario funcionario = TipoUsuario.FUNCIONARIO;
        Usuario user = new Usuario(1, "Willian", "1234", admin);
        UsuarioBO userBO = new UsuarioBO();
        userBO.create(user);
        user = new Usuario(7, "Romulo", "4321", funcionario);
        userBO.create(user);
        
        LocalDateTime ldt = LocalDateTime.now();
        Estado pendente = Estado.PENDENTE;
        Dificuldade medio = Dificuldade.FACIL;
        
        TipoAtividadeBO taBO = new TipoAtividadeBO();
        
        
        Tarefa t = new Tarefa(1, "Tarefa de Teste", "Esta tarefa esta sendo usada para testes apenas, porem preciso enrolar para dar mais linhas e testar se esta funcionando a descricao das tarefas, se funcionar me supreenderei pois é dificil fazer algo que nao da erro aparentemente, ou eu que nao sei so.", ldt, pendente, medio, taBO.readById(4));
        TarefaBO tarefaBO = new TarefaBO();
        tarefaBO.create(t);
        
        
        //Comentario c = new Comentario(1, 1, "Lucas encontrou uma pequena caixa brilhante caída no chão da praça. Ao abri-la, uma luz suave revelou um mapa misterioso apontando para um bosque próximo. Curioso, ele seguiu o caminho indicado. Lá, descobriu uma pedra antiga que pulsava como um coração. Quando a tocou, sentiu coragem e paz, percebendo que alguns tesouros existem apenas para transformar quem os encontra.");
        //ComentarioBO cBO = new ComentarioBO();
        //cBO.create(c);
        */
    	
    	UsuarioBO ubo = new UsuarioBO();
        TarefaBO tbo = new TarefaBO();
        Usuario usuario = ubo.readById(1);
        Tarefa tarefa = tbo.readById(28);
        
        List<Usuario> lista = ubo.readByTarefaId(28);
        
        for (Usuario taf : lista) {
        	System.out.print("\n"+ taf.getNome() + "\n" + taf.getSenha() + "\n");
        }
    }
        
}
