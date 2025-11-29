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
        */
        
        LocalDateTime ldt = LocalDateTime.now();
        Estado pendente = Estado.PENDENTE;
        Dificuldade medio = Dificuldade.MEDIO;
        
        TipoAtividade interfaceJava = new TipoAtividade(1, "InterfaceJava", "Voce deve realizar uma interface em java");
        TipoAtividadeBO taBO = new TipoAtividadeBO();
        taBO.create(interfaceJava);
        
        Tarefa t = new Tarefa(1, "Tarefa de Teste", "Esta tarefa esta sendo usada para testes apenas", ldt, pendente, medio, interfaceJava);
        TarefaBO tarefaBO = new TarefaBO();
        tarefaBO.create(t);

    }
        
}
