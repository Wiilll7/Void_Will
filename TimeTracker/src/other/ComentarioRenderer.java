package other;

import dto.Comentario;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.text.View;

public class ComentarioRenderer extends JPanel implements ListCellRenderer<Comentario> {

    private JLabel lblDescricao = new JLabel();

    public ComentarioRenderer() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setOpaque(true);
        
        lblDescricao.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        add(lblDescricao, BorderLayout.CENTER);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Comentario> list, Comentario comentario, int index, boolean isSelected, boolean cellHasFocus) {
        
        int larguraList = list.getWidth();
        if (larguraList <= 0) larguraList = 250;
        
        int larguraDisponivel = larguraList - 70;

        String textoHtml = "<html><body style='width: " + larguraDisponivel + "px; font-family: Segoe UI; font-size: 10px;'>" 
                           + comentario.getDescricao() 
                           + "</body></html>";
        
        lblDescricao.setText(textoHtml);

        View view = (View) lblDescricao.getClientProperty(javax.swing.plaf.basic.BasicHTML.propertyKey);
        
        if (view != null) {
            view.setSize(larguraDisponivel, 0);
            float h = view.getPreferredSpan(View.Y_AXIS);
            
            this.setPreferredSize(new Dimension(larguraList, (int) h + 15));
        } else {
            this.setPreferredSize(new Dimension(larguraList, 60));
        }

        // Cores
        if (index % 2 == 0) {
            setBackground(new Color(245, 245, 245));
        } else {
            setBackground(Color.WHITE);
        }
        
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

        return this;
    }
}