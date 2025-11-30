package other;

import bo.TarefaBO;
import dto.Tarefa;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MiniCalendarioPanel extends JPanel {

    public MiniCalendarioPanel() {
        setLayout(new GridLayout(1, 4, 10, 0)); 
        setOpaque(false);

        carregarDias();
    }

    private void carregarDias() {
        TarefaBO bo = new TarefaBO();
        List<Tarefa> todasTarefas = bo.readAll(); 

        if (todasTarefas == null) {
            todasTarefas = new ArrayList<Tarefa>();
        }
        
        LocalDate dataBase = LocalDate.now().minusDays(1);

        for (int i = 0; i < 4; i++) {
            LocalDate dataAtual = dataBase.plusDays(i);

            List<Tarefa> tarefasDoDia = new ArrayList<>();
            for (Tarefa t : todasTarefas) {
                if (t.getDataEntrega() != null && 
                    t.getDataEntrega().toLocalDate().isEqual(dataAtual)) {
                    tarefasDoDia.add(t);
                }
            }

            JPanel containerDia = new JPanel(new BorderLayout());
            containerDia.setOpaque(false);

            String nomeDia = dataAtual.getDayOfWeek()
                    .getDisplayName(TextStyle.SHORT, new Locale("pt", "BR"));
            nomeDia = nomeDia.substring(0, 1).toUpperCase() + nomeDia.substring(1);

            JLabel lblSemana = new JLabel(nomeDia, SwingConstants.CENTER);
            lblSemana.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 16));
            lblSemana.setForeground(Color.BLACK);
            
            DiaPanel boxTarefas = new DiaPanel(dataAtual.getDayOfMonth(), tarefasDoDia);
            
            containerDia.add(lblSemana, BorderLayout.NORTH);
            containerDia.add(boxTarefas, BorderLayout.CENTER);

            add(containerDia);
        }
    }
}