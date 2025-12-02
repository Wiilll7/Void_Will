package other;

import bo.TarefaBO;
import dto.Tarefa;
import dto.TipoUsuario;
import dto.Usuario;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class CalendarioPanel extends JPanel {

    private JPanel gridDias;
    private JLabel lblMesAno;
    private YearMonth mesAtual;
    Usuario usuarioLogado;

    public CalendarioPanel(Usuario usuarioLogado) {
        setLayout(new BorderLayout());
        mesAtual = YearMonth.now();
        this.usuarioLogado = usuarioLogado;

        criarCabecalho();
        criarGrid();
        atualizarCalendario();
    }

    private void criarCabecalho() {
        JPanel topo = new JPanel(new BorderLayout());
        
        JButton btnAnt = new JButton("<");
        JButton btnProx = new JButton(">");
        lblMesAno = new JLabel("", SwingConstants.CENTER);
        lblMesAno.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18));

        btnAnt.addActionListener(e -> {
            mesAtual = mesAtual.minusMonths(1);
            atualizarCalendario();
        });

        btnProx.addActionListener(e -> {
            mesAtual = mesAtual.plusMonths(1);
            atualizarCalendario();
        });

        topo.add(btnAnt, BorderLayout.WEST);
        topo.add(lblMesAno, BorderLayout.CENTER);
        topo.add(btnProx, BorderLayout.EAST);

        JPanel semanaPanel = new JPanel(new GridLayout(1, 7));
        String[] diasSemana = {"Dom", "Seg", "Ter", "Qua", "Qui", "Sex", "Sáb"};
        for (String dia : diasSemana) {
            JLabel lbl = new JLabel(dia, SwingConstants.CENTER);
            semanaPanel.add(lbl);
        }
        
        JPanel norte = new JPanel(new BorderLayout());
        norte.add(topo, BorderLayout.NORTH);
        norte.add(semanaPanel, BorderLayout.CENTER);
        
        add(norte, BorderLayout.NORTH);
    }

    private void criarGrid() {
        gridDias = new JPanel(new GridLayout(0, 7)); 
        gridDias.setBackground(Color.WHITE);
        add(gridDias, BorderLayout.CENTER);
    }

    private void atualizarCalendario() {
        gridDias.removeAll();
        
        lblMesAno.setText(mesAtual.getMonth().toString() + " / " + mesAtual.getYear());

        TarefaBO bo = new TarefaBO();
        List<Tarefa> todasTarefas = new ArrayList<Tarefa>();
        
        if (usuarioLogado.getTipo() == TipoUsuario.FUNCIONARIO) {
            todasTarefas = bo.readByUsuarioId(usuarioLogado.getId()); 
        } else {
            todasTarefas = bo.readAll();
        }
        
        if (todasTarefas == null) {
            todasTarefas = new ArrayList<Tarefa>();
        }

        LocalDate primeiroDiaDoMes = mesAtual.atDay(1);
        int diasNoMes = mesAtual.lengthOfMonth();
        
        int diaSemanaInicio = primeiroDiaDoMes.getDayOfWeek().getValue(); 
        if (diaSemanaInicio == 7) diaSemanaInicio = 0;

        for (int i = 0; i < diaSemanaInicio; i++) {
            gridDias.add(new JPanel());
        }

        for (int dia = 1; dia <= diasNoMes; dia++) {
            LocalDate dataAtual = mesAtual.atDay(dia);
            
            List<Tarefa> tarefasDesseDia = new ArrayList<>();
            for (Tarefa t : todasTarefas) {
                if (t.getDataEntrega() != null && 
                    t.getDataEntrega().toLocalDate().isEqual(dataAtual)) {
                    tarefasDesseDia.add(t);
                }
            }

            gridDias.add(new DiaPanel(dia, tarefasDesseDia));
        }

        gridDias.revalidate();
        gridDias.repaint();
    }
}
