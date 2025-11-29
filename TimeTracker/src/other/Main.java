package other;

import dto.TipoAtividade;
import java.util.List;
import bo.TipoAtividadeBO;
import dto.Usuario;
import bo.UsuarioBO;
import dto.TipoUsuario;

public class Main {
    
    public static void main(String[] args) {

        TipoUsuario admin = TipoUsuario.ADMIN;
        TipoUsuario funcionario = TipoUsuario.FUNCIONARIO;
        Usuario user = new Usuario(1, "Willian", "1234", admin);
        UsuarioBO userBO = new UsuarioBO();
        userBO.create(user);
        user = new Usuario(7, "Romulo", "4321", funcionario);
        userBO.create(user);

    }
        
}
