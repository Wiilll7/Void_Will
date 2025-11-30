package other;

import dto.Tarefa;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class DiaPanel extends JPanel {

    public DiaPanel(int dia, List<Tarefa> tarefasDoDia) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(100, 100));

        JLabel lblDia = new JLabel(String.valueOf(dia) + " ");
        lblDia.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblDia.setHorizontalAlignment(JLabel.RIGHT);
        add(lblDia, BorderLayout.NORTH);

        JPanel panelTarefas = new JPanel();
        panelTarefas.setLayout(new BoxLayout(panelTarefas, BoxLayout.Y_AXIS));
        panelTarefas.setBackground(Color.WHITE);

        for (Tarefa t : tarefasDoDia) {
            JLabel lblTarefa = new JLabel("• " + t.getTitulo());
            lblTarefa.setFont(new Font("Segoe UI", Font.PLAIN, 10));
            
            if (t.getEstado().getId() == 4) {
                lblTarefa.setForeground(new Color(0, 140, 0));
            } else if (t.getEstado().getId() == 3) {
                lblTarefa.setForeground(Color.BLUE);
            } else {
                lblTarefa.setForeground(Color.BLACK);
            }
            
            panelTarefas.add(lblTarefa);
        }

        JScrollPane scroll = new JScrollPane(panelTarefas);
        scroll.setBorder(null);
        add(scroll, BorderLayout.CENTER);
    }
}
