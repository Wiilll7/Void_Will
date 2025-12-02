package bo;

import java.time.LocalDateTime;
import java.util.List;
import java.time.Duration;

import dao.TempoDAO;
import dto.Tempo;

public class TempoBO {

    public boolean create(Tempo tempo) {
        TempoDAO dao = new TempoDAO();
        
		if (tempo.getDataFinal().isAfter(tempo.getDataInicial())) {
			return dao.create(tempo);
		} else {
			LocalDateTime tempo_temp = tempo.getDataInicial();
			tempo.setDataInicial(tempo.getDataFinal());
			tempo.setDataFinal(tempo_temp);
			return dao.create(tempo);
		}
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

    public List<Tempo> readByUsuarioId(int usuarioId) {
    	TempoDAO dao = new TempoDAO();
        return dao.readByUsuarioId(usuarioId);
    }
    
    public List<Tempo> readByTarefaId(int tarefaId) {
        TempoDAO dao = new TempoDAO();
        return dao.readByTarefaId(tarefaId);
    }
    
    public int[] readTotalTimeInTarefa(int tarefaId) {
        
        Duration total_time = Duration.ZERO;
        int[] time_separated = {0,0,0};
        List<Tempo> lista = readByTarefaId(tarefaId);
        
        if (lista != null) {
        	for (Tempo t : lista) {
        		Duration time;
        		if (t.getDataFinal() == null) {
        			time = Duration.between(t.getDataInicial(), LocalDateTime.now());
        		} else {
        			time = Duration.between(t.getDataInicial(), t.getDataFinal());
        		}
            	total_time = total_time.plus(time);
            }
        	
        	time_separated[0] = (int) total_time.toHours();
        	time_separated[1] = total_time.toMinutesPart();
        	time_separated[2] = total_time.toSecondsPart();
        	
        	return time_separated;
        } else {
        	return time_separated;
        }
    }

    public boolean update(Tempo tempo) {
        TempoDAO dao = new TempoDAO();
        
        if (tempo.getDataFinal().isAfter(tempo.getDataInicial())) {
        	return dao.update(tempo);
		} else {
			LocalDateTime tempo_temp = tempo.getDataInicial();
			tempo.setDataInicial(tempo.getDataFinal());
			tempo.setDataFinal(tempo_temp);
			return dao.update(tempo);
		}
    }
    
    public Tempo toggleCounting(Tempo tempo) {
    	TempoDAO dao = new TempoDAO();
    	
    	if (tempo.getDataInicial() != null && tempo.getDataFinal() == null) {
    		tempo = new Tempo(tempo.getId(),tempo.getTarefaId(),tempo.getDataInicial(),LocalDateTime.now());
    		
    		if (dao.update(tempo)) {
    			return tempo;
    		} else {
    			return null;
    		}
    	} else {
    		tempo = new Tempo(tempo.getId(),tempo.getTarefaId(),LocalDateTime.now(),null);
    		if (dao.create(tempo)) {
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
