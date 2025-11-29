package other;

import dto.Tarefa;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;


public class TarefaRenderer extends JPanel implements ListCellRenderer<Tarefa> {

    private JLabel lblTitulo = new JLabel();
    private JLabel lblData = new JLabel();
    private JLabel lblStatus = new JLabel();

    public TarefaRenderer() {
        setLayout(new GridLayout(3, 1));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Margem interna
        setOpaque(true);

        add(lblTitulo);
        add(lblData);
        add(lblStatus);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Tarefa> list, Tarefa tarefa, int index, boolean isSelected, boolean cellHasFocus) {
        
        lblTitulo.setText("Checking: " + tarefa.getTitulo());
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(java.awt.Font.BOLD, 14f));
        
        lblData.setText("Data: " + tarefa.getDataEntrega().toString());
        
        lblStatus.setText("Status: " + tarefa.getEstado().name());

        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(Color.WHITE);
            setForeground(list.getForeground());
        }
        
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

        return this;
    }
}