package desempenho;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bo.TarefaBO;
import bo.TempoBO;
import dto.Tempo;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class Desempenho {
	
    // String -- Date conversion
    static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static String dateToString(LocalDateTime date) {
    	if (date != null) {
    		return date.format(formatter);
    	} else {
    		return null;
    	}
    }
    public static LocalDateTime stringToDate(String date) {
    	if (date != null) {
	    	System.out.println(date);
	    	String[] separate_ms = date.split("\\.");
	    	return LocalDateTime.parse(separate_ms[0],formatter);
    	} else {
    		return null;
    	}
    }
	
    // Tempo total / Dia
    public static CategoryDataset getTempoTotalPorDia() {

        TempoBO tempoBO = new TempoBO();
        List<Tempo> tempos = tempoBO.readAll();
        List<String> datas = new ArrayList<String>();
        List<Integer> tempo_gasto = new ArrayList<Integer>();

        if (tempos != null) {
            for (Tempo t : tempos) {
                int exists = -1;
                String data_tempo = dateToString(t.getDataInicial());

                for (int i = 0; i < datas.size(); i++) {
                    String data = datas.get(i);

                    if (data.equals(data_tempo)) {
                            exists = i;
                            break;
                    }
                }

                int time_count = (int) Duration.between(t.getDataInicial(), t.getDataFinal()).toSeconds();

                if (exists != -1) {
                        tempo_gasto.set(exists, tempo_gasto.get(exists) + time_count);
                } else {
                        datas.add(data_tempo);
                        tempo_gasto.add(time_count);
                }
            }

            return arraysToDataset(tempo_gasto,datas);

        } else {
                return null;
        }
    }
	/*
	// Tempo total / TipoAtividade
	public static CategoryDataset getTempoTotalPorDia() {
		
		TempoBO tempoBO = new TempoBO();
		List<Tempo> tempos = tempoBO.readAll();
		List<String> tiposAtividade = new ArrayList<String>();
		List<Integer> tempo_gasto = new ArrayList<Integer>();
		
		TarefaBO tarefaBO = new TarefaBO();
		if (tempos != null) {
			for (Tempo t : tempos) {
				int exists = -1;
				String tipo = (tarefaBO.readById(t.getTarefaId()).getTipoAtividade());
				
				for (int i = 0; i < datas.size(); i++) {
					String data = datas.get(i);
					
					if (data.equals(data_tempo)) {
						exists = i;
						break;
					}
				}
				
				int time_count = (int) Duration.between(t.getDataInicial(), t.getDataFinal()).toSeconds();
				
				if (exists != -1) {
					tempo_gasto.set(1, tempo_gasto.get(exists) + time_count);
				} else {
					datas.add(data_tempo);
					tempo_gasto.add(time_count);
				}
			}
			
			return arraysToDataset(tempo_gasto,datas);
			
		} else {
			return null;
		}
	}
	*/
        
    public static CategoryDataset arraysToDataset(List<Integer> values,List<String> categories) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        for (int i = 0; i < categories.size(); i++) {
            dataset.addValue(values.get(i), "", categories.get(i));
        }

        return dataset;
    }
}
