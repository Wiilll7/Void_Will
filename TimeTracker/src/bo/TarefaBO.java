package bo;

import java.util.List;

import dao.TarefaDAO;
import dao.UsuarioDAO;
import dto.Tarefa;
import dto.Usuario;

public class TarefaBO {

    public boolean create(Tarefa tarefa) {
        TarefaDAO dao = new TarefaDAO();
        
        List<Tarefa> lista = readAll();
        boolean allow = true;
        for (Tarefa t : lista) {
        	if (t.getTitulo().equals(tarefa.getTitulo())) {
        		allow = false;
        		break;
        	}
        }
        if (allow) {
        	return dao.create(tarefa);
        } else {
        	return false;
        }
    }

    public Tarefa readById(int id) {
        TarefaDAO dao = new TarefaDAO();
        return dao.readById(id);
    }
    
    public List<Tarefa> readByUsuarioId(int usuario_id) {
        TarefaDAO dao = new TarefaDAO();
        return dao.readByUsuarioId(usuario_id);
    }

    public List<Tarefa> readAll() {
        TarefaDAO dao = new TarefaDAO();
        return dao.readAll();
    }

    public boolean update(Tarefa tarefa) {
        TarefaDAO dao = new TarefaDAO();
        
        List<Tarefa> lista = readAll();
        boolean allow = true;
        for (Tarefa t : lista) {
        	if (t.getTitulo().equals(tarefa.getTitulo()) && t.getId() != tarefa.getId()) {
        		allow = false;
        		break;
        	}
        }
        if (allow) {
        	return dao.update(tarefa);
        } else {   
            return false;
        }
    }

    public boolean delete(int id) {
        TarefaDAO dao = new TarefaDAO();
        return dao.delete(id);
    }
}
