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
        
        
        LocalDateTime ldt = LocalDateTime.now();
        Estado pendente = Estado.PENDENTE;
        Dificuldade medio = Dificuldade.FACIL;
        TipoAtividadeBO taBO = new TipoAtividadeBO();
        
        TipoAtividade ta = new TipoAtividade(1, "php", "fazer sites");
        taBO.create(ta);
    	
    }
        
}
