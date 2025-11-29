package bo;

import java.time.LocalDateTime;
import java.util.List;

import dao.TempoDAO;
import dto.Tempo;

public class TempoBO {

    public boolean create(Tempo tempo) {
        TempoDAO dao = new TempoDAO();
        return dao.create(tempo);
    }

    public Tempo readById(int id) {
        TempoDAO dao = new TempoDAO();
        return dao.readById(id);
    }

    public List<Tempo> readAll() {
        TempoDAO dao = new TempoDAO();
        return dao.readAll();
    }
    
    public Tempo readLastAdded(int tarefaId) {
    	TempoDAO dao = new TempoDAO();
    	Tempo result = dao.readLastAdded(tarefaId);
    	if (result == null) {
    		Tempo tempo = new Tempo(0,tarefaId,null,null);
    		return tempo;
    	} else {
    		return result;
    	}
    }

    public List<Tempo> readByTarefaId(int tarefaId) {
        TempoDAO dao = new TempoDAO();
        return dao.readByTarefaId(tarefaId);
    }

    public boolean update(Tempo tempo) {
        TempoDAO dao = new TempoDAO();
        return dao.update(tempo);
    }
    
    public Tempo toggleCounting(Tempo tempo) {
    	TempoDAO dao = new TempoDAO();
    	
    	if (tempo.getDataInicial() == null || tempo.getDataFinal() != null) {
    		tempo = new Tempo(tempo.getId(),tempo.getTarefaId(),LocalDateTime.now(),null);
    		if (dao.create(tempo)) {
    			return tempo;
    		} else {
    			return null;
    		}
    	} else {
    		tempo = new Tempo(tempo.getId(),tempo.getTarefaId(),tempo.getDataInicial(),LocalDateTime.now());
    		if (dao.update(tempo)) {
    			return tempo;
    		} else {
    			return null;
    		}
    	}
    }

    public boolean deleteById(int id) {
        TempoDAO dao = new TempoDAO();
        return dao.deleteById(id);
    }
}
