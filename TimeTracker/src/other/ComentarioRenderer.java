package other;

import dto.Comentario;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

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

        int largura = list.getWidth() - 30; 
        if (largura <= 0) largura = 200;

        String textoHtml = "<html><body style='width: " + largura + "px; padding: 5px;'>" 
                           + comentario.getDescricao() 
                           + "</body></html>";
        
        lblDescricao.setText(textoHtml);

        //Uma linha branca, outra cinza claro
        if (index % 2 == 0) {
            setBackground(new Color(245, 245, 245));
        } else {
            setBackground(Color.WHITE);
        }

        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

        return this;
    }
}
