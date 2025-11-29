package bo;

import java.util.List;

import dao.TarefaDAO;
import dto.Tarefa;

public class TarefaBO {

    public boolean create(Tarefa tarefa) {
        TarefaDAO dao = new TarefaDAO();
        
        
        return dao.create(tarefa);
    }

    public Tarefa readById(int id) {
        TarefaDAO dao = new TarefaDAO();
        return dao.readById(id);
    }

    public List<Tarefa> readAll() {
        TarefaDAO dao = new TarefaDAO();
        return dao.readAll();
    }

    public boolean update(Tarefa tarefa) {
        TarefaDAO dao = new TarefaDAO();
        return dao.update(tarefa);
    }

    public boolean delete(int id) {
        TarefaDAO dao = new TarefaDAO();
        return dao.delete(id);
    }
}
