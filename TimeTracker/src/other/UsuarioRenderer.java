package other;

import dto.Usuario;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

public class UsuarioRenderer extends JPanel implements ListCellRenderer<Usuario> {

    private JLabel lblNome = new JLabel();
    private JLabel lblTipo = new JLabel();
    private JLabel lblId = new JLabel();

    public UsuarioRenderer() {
        setLayout(new GridLayout(3, 1));
        setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        setOpaque(true);

        add(lblNome);
        add(lblId);
        add(lblTipo);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Usuario> list, Usuario usuario, int index, boolean isSelected, boolean cellHasFocus) {
        
        lblNome.setText("Nome do usuário: " + usuario.getNome());
        lblNome.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblId.setText("ID do usuário: " + Integer.toString(usuario.getId()));
        
        if (usuario.getTipo() != null) {
            lblTipo.setText("Tipo do usuário: " + usuario.getTipo().toString());
        } else {
            lblTipo.setText("Tipo desconhecido");
        }

        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            if (index % 2 == 0) {
                setBackground(new Color(245, 245, 245));
            } else {
                setBackground(Color.WHITE);
            }
            setForeground(list.getForeground());
        }
        
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

        return this;
    }
}
