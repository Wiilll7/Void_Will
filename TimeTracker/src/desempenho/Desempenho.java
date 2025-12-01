package desempenho;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bo.TarefaBO;
import bo.TempoBO;
import bo.TipoAtividadeBO;
import dto.Tarefa;
import dto.Tempo;
import dto.Usuario;

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
    public static DefaultCategoryDataset getTempoTotalPorDia(int usuarioId) {

        TempoBO tempoBO = new TempoBO();
        List<Tempo> tempos = tempoBO.readByUsuarioId(usuarioId);
        List<String> datas = new ArrayList<String>();
        List<Double> tempo_gasto = new ArrayList<Double>();

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

                double time_count = Duration.between(t.getDataInicial(), t.getDataFinal()).toSeconds();

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
	
	// Tempo total / TipoAtividade
    public static DefaultCategoryDataset getTempoTotalPorTipoAtividade(int usuarioId) {
        TempoBO tempoBO = new TempoBO();
        TarefaBO taBO = new TarefaBO();
        List<Tempo> tempos = tempoBO.readByUsuarioId(usuarioId);
        List<String> tipo_atvs = new ArrayList<String>();
        List<Double> tempo_gasto = new ArrayList<Double>();

        if (tempos != null) {
            for (Tempo t : tempos) {
                int exists = -1;
                String tipo_atv_tempo = taBO.readById(t.getTarefaId()).getTipoAtividade().getNome();

                for (int i = 0; i < tipo_atvs.size(); i++) {
                    String tipo_atv = tipo_atvs.get(i);

                    if (tipo_atv.equals(tipo_atv_tempo)) {
                        exists = i;
                        break;
                    }
                }

                double time_count = Duration.between(t.getDataInicial(), t.getDataFinal()).toSeconds();

                if (exists != -1) {
                        tempo_gasto.set(exists, tempo_gasto.get(exists) + time_count);
                } else {
                        tipo_atvs.add(tipo_atv_tempo);
                        tempo_gasto.add(time_count);
                }
            }

            return arraysToDataset(tempo_gasto,tipo_atvs);

        } else {
            return null;
        }
    }
	
    // DificuldadeMedia / Dia
    public static DefaultCategoryDataset getDificuldadeMediaPorDia(int usuarioId) {

        TarefaBO tarefaBO = new TarefaBO();
        TempoBO tempoBO = new TempoBO();
        List<Tarefa> tarefas = tarefaBO.readByUsuarioId(usuarioId);
        List<String> datas = new ArrayList<String>();
        List<Double> dificuldade_media = new ArrayList<Double>();
        List<Integer> tarefa_count = new ArrayList<Integer>();

        if (tarefas != null) {
            for (Tarefa t : tarefas) {
                int exists = -1;
                List<Tempo> lista_tarefa_tempo = tempoBO.readByTarefaId(t.getId());
                if (lista_tarefa_tempo != null && lista_tarefa_tempo.size() > 0) {
                    Tempo tarefa_tempo = lista_tarefa_tempo.get(0);
                    String data_tarefa = dateToString(tarefa_tempo.getDataInicial());

                    for (int i = 0; i < datas.size(); i++) {
                        String data = datas.get(i);

                        if (data.equals(data_tarefa)) {
                                exists = i;
                                break;
                        }
                    }

                    double dificuldade_parcial = t.getDificuldade().getId();

                    if (exists != -1) {
    	            	dificuldade_media.set(exists, dificuldade_media.get(exists) + dificuldade_parcial);
    	            	tarefa_count.set(exists, tarefa_count.get(exists) + 1);
    	            } else {
    	            	datas.add(data_tarefa);
    	                dificuldade_media.add(dificuldade_parcial);
    	                tarefa_count.add(1);
    	            }
                }
            }
            
            for (int i = 0; i < dificuldade_media.size(); i++) {
            	dificuldade_media.set(i, dificuldade_media.get(i) / tarefa_count.get(i));
            }

            return arraysToDataset(dificuldade_media,datas);

        } else {
                return null;
        }
    }
    
    // DificuldadeMedia / TipoAtividade
    public static DefaultCategoryDataset getDificuldadeMediaPorTipoAtividade(int usuarioId) {

        TarefaBO tarefaBO = new TarefaBO();
        List<Tarefa> tarefas = tarefaBO.readByUsuarioId(usuarioId);
        List<String> tipo_atvs = new ArrayList<String>();
        List<Double> dificuldade_media = new ArrayList<Double>();
        List<Integer> tarefa_count = new ArrayList<Integer>();

        if (tarefas != null) {
            for (Tarefa t : tarefas) {
            	int exists = -1;
                String tipo_atv_tarefa = t.getTipoAtividade().getNome();

                for (int i = 0; i < tipo_atvs.size(); i++) {
                    String tipo_atv = tipo_atvs.get(i);

                    if (tipo_atv.equals(tipo_atv_tarefa)) {
                        exists = i;
                        break;
                    }
                }
            	
                
	            double dificuldade_parcial = t.getDificuldade().getId();
	
	            if (exists != -1) {
	            	dificuldade_media.set(exists, dificuldade_media.get(exists) + dificuldade_parcial);
	            	tarefa_count.set(exists, tarefa_count.get(exists) + 1);
	            } else {
	                tipo_atvs.add(tipo_atv_tarefa);
	                dificuldade_media.add(dificuldade_parcial);
	                tarefa_count.add(1);
	            }
            }
            
            for (int i = 0; i < dificuldade_media.size(); i++) {
            	dificuldade_media.set(i, dificuldade_media.get(i) / tarefa_count.get(i));
            }

            return arraysToDataset(dificuldade_media,tipo_atvs);

        } else {
            return null;
        }
    }
    
        
    public static DefaultCategoryDataset arraysToDataset(List<Double> values,List<String> categories) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        for (int i = 0; i < categories.size(); i++) {
            dataset.addValue(values.get(i), "", categories.get(i));
        }

        return dataset;
    }
}
