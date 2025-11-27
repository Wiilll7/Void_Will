package bo;

import java.util.List;

import dao.ComentarioDAO;
import dto.Comentario;

public class ComentarioBO {

    public boolean create(Comentario comentario) {
        ComentarioDAO dao = new ComentarioDAO();
        return dao.create(comentario);
    }

    public Comentario readById(int id) {
        ComentarioDAO dao = new ComentarioDAO();
        return dao.readById(id);
    }

    public List<Comentario> readAll() {
        ComentarioDAO dao = new ComentarioDAO();
        return dao.readAll();
    }

    public List<Comentario> readByTarefaId(int tarefaId) {
        ComentarioDAO dao = new ComentarioDAO();
        return dao.readByTarefaId(tarefaId);
    }

    public boolean update(Comentario comentario) {
        ComentarioDAO dao = new ComentarioDAO();
        return dao.update(comentario);
    }

    public boolean deleteById(int id) {
        ComentarioDAO dao = new ComentarioDAO();
        return dao.deleteById(id);
    }
}

