package bo;

import java.util.List;

import dao.UsuarioDAO;
import dto.Tarefa;
import dto.Usuario;

public class UsuarioBO {
    public boolean create(Usuario usuario) {
        UsuarioDAO dao = new UsuarioDAO();
        
        List<Usuario> lista = readAll();
        boolean allow = true;
        for (Usuario u : lista) {
        	if (u.getNome().equals(usuario.getNome())) {
        		allow = false;
        		break;
        	}
        }
        if (allow) {
        	return dao.create(usuario);
        } else {
        	return false;
        }
    }
	
    public boolean addTarefa(Usuario usuario,Tarefa tarefa) {
		UsuarioDAO dao = new UsuarioDAO();
		return dao.addTarefa(usuario, tarefa);
	}

    public Usuario readBySenha(String nome, String senha) {
        UsuarioDAO dao = new UsuarioDAO();
        return dao.readBySenha(nome,senha);
    }
	
    public Usuario readById(int id) {
        UsuarioDAO dao = new UsuarioDAO();
        return dao.readById(id);
    }
    
    public List<Usuario> readByTarefaId(int tarefa_id) {
    	UsuarioDAO dao = new UsuarioDAO();
        return dao.readByTarefaId(tarefa_id);
    }

    public List<Usuario> readAll() {
        UsuarioDAO dao = new UsuarioDAO();
        return dao.readAll();
    }

    public boolean update(Usuario usuario) {
        UsuarioDAO dao = new UsuarioDAO();
        
        
        List<Usuario> lista = readAll();
        boolean allow = true;
        for (Usuario u : lista) {
        	if (u.getNome().equals(usuario.getNome()) && u.getId() != usuario.getId()) {
        		allow = false;
        		break;
        	}
        }
        if (allow) {
        	return dao.update(usuario);
        } else {
        	return false;
        }
    }

    public boolean delete(int id) {
        UsuarioDAO dao = new UsuarioDAO();
        return dao.delete(id);
    }
    
    public boolean removeTarefa(Usuario usuario,Tarefa tarefa) {
		UsuarioDAO dao = new UsuarioDAO();
		return dao.removeTarefa(usuario, tarefa);
	}
}
