package other;

import bo.TarefaBO;
import dto.Tarefa;
import java.util.List;
import javax.swing.DefaultListModel;
import bo.ComentarioBO;
import dto.Comentario;
import java.awt.CardLayout;
import java.time.format.DateTimeFormatter;
import dto.Usuario;
import bo.UsuarioBO;
import desempenho.Desempenho;
import dto.Estado;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class TelaAdmin extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaAdmin.class.getName());
    private Usuario usuarioLogado;
    private Tarefa tarefaSelecionadaTarefa;
    private JFrame JFrame;
    
    public TelaAdmin(Usuario usuario) {
        usuarioLogado = usuario;
        initComponents();
        carregarListas();
        aplicarEstiloGlobal(this);
        carregarUsuariosParaAnalise();
        
        telaCalendario.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentShown(java.awt.event.ComponentEvent e) {

                areaCalendario.removeAll();
                areaCalendario.setLayout(new java.awt.BorderLayout());

                CalendarioPanel novoCalendario = new CalendarioPanel(usuarioLogado);

                areaCalendario.add(novoCalendario, java.awt.BorderLayout.CENTER);

                areaCalendario.revalidate();
                areaCalendario.repaint();
            }
        });
    }

    private String formatarData(java.time.LocalDateTime data) {
        if (data == null) {
            return "Sem data";
        }
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return data.format(formatter);
    }
    
    private void carregarListas() {
        TarefaBO bo = new TarefaBO();
        List<Tarefa> todasTarefas = bo.readAll();
        DefaultListModel<Tarefa> modeloAFazer = new DefaultListModel<>();
        DefaultListModel<Tarefa> modeloConcluidas = new DefaultListModel<>();
        DefaultListModel<Tarefa> modeloSelecionada = new DefaultListModel<>();
        DefaultListModel<Tarefa> modeloLixeira = new DefaultListModel<>();

        if (todasTarefas != null) {
            for (Tarefa t : todasTarefas) {
                switch (t.getEstado().getId()) {
                    case 1:
                        modeloLixeira.addElement(t);
                        break;
                    case 2:
                        modeloAFazer.addElement(t);
                        break;
                    case 3:
                        modeloSelecionada.addElement(t);
                        break;
                    case 4: 
                        modeloConcluidas.addElement(t);
                        break;
                }
            }
        }
        
        listaSelecionada.setModel(modeloSelecionada);
        listaPendentes.setModel(modeloAFazer);
        listaConcluida.setModel(modeloConcluidas);

        listaSelecionada.setCellRenderer(new TarefaRenderer());
        listaPendentes.setCellRenderer(new TarefaRenderer());
        listaConcluida.setCellRenderer(new TarefaRenderer());
    }
    
    private void carregarUsuariosParaAnalise() {
        bo.UsuarioBO bo = new bo.UsuarioBO();
        java.util.List<dto.Usuario> todos = bo.readAll();

        jComboBox1.removeAllItems();

        jComboBox1.addItem(null); 

        jComboBox1.setRenderer(new javax.swing.DefaultListCellRenderer() {
            @Override
            public java.awt.Component getListCellRendererComponent(javax.swing.JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if (value == null) {
                    setText("- Selecione um Funcionário -");
                } else if (value instanceof dto.Usuario) {
                    setText(((dto.Usuario) value).getNome());
                }
                return this;
            }
        });

        for (dto.Usuario u : todos) {
            if (u.getTipo() != dto.TipoUsuario.ADMIN) {
                jComboBox1.addItem(u);
            }
        }
    }
    
    private void carregarComentarios(int idTarefa) {
        ComentarioBO bo = new ComentarioBO();
        List<Comentario> lista = bo.readByTarefaId(idTarefa);

        DefaultListModel<Comentario> modelo = new DefaultListModel<>();

        for (Comentario c : lista) {
            modelo.addElement(c);
        }

        listaComentarios.setModel(modelo);
        listaComentarios.setCellRenderer(new ComentarioRenderer());
    }
    
    private void aplicarEstiloGlobal(java.awt.Container container) {
        for (java.awt.Component c : container.getComponents()) {

            if (c instanceof javax.swing.JScrollPane) {
                javax.swing.JScrollPane scroll = (javax.swing.JScrollPane) c;
                scroll.getVerticalScrollBar().setUI(new other.ModernScrollBarUI());
                scroll.getHorizontalScrollBar().setUI(new other.ModernScrollBarUI());
            }

            else if (c instanceof java.awt.Container) {
                aplicarEstiloGlobal((java.awt.Container) c);
            }
        }
    }
    
    private void mostrarTelaDetalhes(boolean b) {
        CardLayout cl = (CardLayout) panelTarefa.getLayout();
        
        if (b) {
            cl.show(panelTarefa, "telaDetalhes");
        } else {
            cl.show(panelTarefa, "telaAviso");
        }
        
    }
    
    public JPanel criarGraficoBarras(DefaultCategoryDataset dataset, String titulo, String baixo, String lado) {
    
        JFreeChart grafico = ChartFactory.createBarChart(
                titulo,  
                baixo,            
                lado,    
                dataset,                    
                PlotOrientation.VERTICAL,   
                true,                       
                true,                      
                false                       
        );

        org.jfree.chart.plot.CategoryPlot plot = grafico.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setRangeGridlinePaint(Color.GRAY);

        ChartPanel painelGrafico = new ChartPanel(grafico);

        painelGrafico.setPreferredSize(new java.awt.Dimension(400, 300));

        return painelGrafico;
    }
    
    private void carregarUsuariosDaTarefa(dto.Tarefa tarefa) {
       
        javax.swing.DefaultListModel<dto.Usuario> modelo = new javax.swing.DefaultListModel<>();
        listaUsuariosTarefa.setModel(modelo); 

        bo.UsuarioBO usuarioBO = new bo.UsuarioBO();
        java.util.List<dto.Usuario> usuariosEncontrados = usuarioBO.readByTarefaId(tarefa.getId());

        if (usuariosEncontrados != null) {
            for (dto.Usuario u : usuariosEncontrados) {
                modelo.addElement(u);
            }
        }

        listaUsuariosTarefa.setModel(modelo);
        listaUsuariosTarefa.setCellRenderer(new UsuarioRenderer());
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        TelaTarefas = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        panelListas = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaSelecionada = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaPendentes = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        listaConcluida = new javax.swing.JList<>();
        jLabel3 = new javax.swing.JLabel();
        btnRefresh4 = new javax.swing.JButton();
        panelTarefa = new javax.swing.JPanel();
        telaAviso = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jButton15 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        telaDetalhes = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        titleTitulo = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        textDescricao = new javax.swing.JTextArea();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        listaComentarios = new javax.swing.JList<>();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        textDetalhes = new javax.swing.JTextArea();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        listaUsuariosTarefa = new javax.swing.JList<>();
        jButton4 = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        btnRemoveTarefa = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        btnRefresh1 = new javax.swing.JButton();
        btnRefresh2 = new javax.swing.JButton();
        btnRefresh3 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        telaCalendario = new javax.swing.JPanel();
        areaCalendario = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        telaGrafico = new javax.swing.JPanel();
        jButton18 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel12 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        telaDesempenho = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        telaSelect = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        telaGraficoDificuldadeTipoAtv = new javax.swing.JPanel();
        telaGraficoDificuldadeDia = new javax.swing.JPanel();
        telaGraficoTempoTipoAtv = new javax.swing.JPanel();
        telaGraficoTempoDia = new javax.swing.JPanel();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new java.awt.CardLayout());

        jSplitPane1.setDividerLocation(290);

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        listaSelecionada.setModel(new javax.swing.DefaultListModel<dto.Tarefa>());
        listaSelecionada.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listaSelecionadaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(listaSelecionada);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Tarefas em Andamento");

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        listaPendentes.setModel(new javax.swing.DefaultListModel<dto.Tarefa>());
        listaPendentes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listaPendentesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(listaPendentes);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Tarefas Pendentes");

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        listaConcluida.setModel(new javax.swing.DefaultListModel<dto.Tarefa>());
        listaConcluida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listaConcluidaMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(listaConcluida);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Tarefas Concluidas");

        btnRefresh4.setText("🗘");
        btnRefresh4.addActionListener(this::btnRefresh4ActionPerformed);

        javax.swing.GroupLayout panelListasLayout = new javax.swing.GroupLayout(panelListas);
        panelListas.setLayout(panelListasLayout);
        panelListasLayout.setHorizontalGroup(
            panelListasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelListasLayout.createSequentialGroup()
                .addGroup(panelListasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelListasLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelListasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(panelListasLayout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRefresh4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(6, 6, 6)))
                .addContainerGap())
        );
        panelListasLayout.setVerticalGroup(
            panelListasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelListasLayout.createSequentialGroup()
                .addGroup(panelListasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(btnRefresh4))
                .addGap(3, 3, 3)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jSplitPane1.setLeftComponent(panelListas);

        panelTarefa.setLayout(new java.awt.CardLayout());

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel4.setText("Selecione uma Tarefa");

        jButton15.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jButton15.setText("📊");
        jButton15.addActionListener(this::jButton15ActionPerformed);

        jButton9.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jButton9.setText("Calendario");
        jButton9.addActionListener(this::jButton9ActionPerformed);

        jButton10.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jButton10.setText("Trocar Usuario");
        jButton10.addActionListener(this::jButton10ActionPerformed);

        javax.swing.GroupLayout telaAvisoLayout = new javax.swing.GroupLayout(telaAviso);
        telaAviso.setLayout(telaAvisoLayout);
        telaAvisoLayout.setHorizontalGroup(
            telaAvisoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(telaAvisoLayout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addComponent(jLabel4)
                .addContainerGap(150, Short.MAX_VALUE))
            .addGroup(telaAvisoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton10)
                .addGap(18, 18, 18))
        );
        telaAvisoLayout.setVerticalGroup(
            telaAvisoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(telaAvisoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(telaAvisoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton15)
                    .addComponent(jButton9)
                    .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(256, 256, 256)
                .addComponent(jLabel4)
                .addContainerGap(365, Short.MAX_VALUE))
        );

        panelTarefa.add(telaAviso, "telaAviso");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        titleTitulo.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        titleTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleTitulo.setText("Titulo");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titleTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(41, 43, 45)), "Descrição", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        textDescricao.setEditable(false);
        textDescricao.setColumns(20);
        textDescricao.setLineWrap(true);
        textDescricao.setRows(5);
        textDescricao.setWrapStyleWord(true);
        jScrollPane4.setViewportView(textDescricao);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addComponent(jScrollPane4)
                .addContainerGap())
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(41, 43, 45)), "Comentarios", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jScrollPane5.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        listaComentarios.setModel(new javax.swing.DefaultListModel<dto.Comentario>());
        jScrollPane5.setViewportView(listaComentarios);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(41, 43, 45)), "Detalhes", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        textDetalhes.setEditable(false);
        textDetalhes.setColumns(20);
        textDetalhes.setLineWrap(true);
        textDetalhes.setRows(5);
        textDetalhes.setWrapStyleWord(true);
        jScrollPane6.setViewportView(textDetalhes);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addComponent(jScrollPane6)
                .addContainerGap())
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(41, 43, 45)), "Usuarios com a Tarefa", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jScrollPane7.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        listaUsuariosTarefa.setModel(new javax.swing.DefaultListModel<dto.Usuario>());
        jScrollPane7.setViewportView(listaUsuariosTarefa);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButton4.setText("+");
        jButton4.addActionListener(this::jButton4ActionPerformed);

        btnRefresh.setText("🗘");
        btnRefresh.addActionListener(this::btnRefreshActionPerformed);

        jButton5.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jButton5.setText("Excluir");
        jButton5.addActionListener(this::jButton5ActionPerformed);

        jButton6.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jButton6.setText("Editar");
        jButton6.addActionListener(this::jButton6ActionPerformed);

        btnRemoveTarefa.setText("❌");
        btnRemoveTarefa.addActionListener(this::btnRemoveTarefaActionPerformed);

        jButton8.setText("+");
        jButton8.addActionListener(this::jButton8ActionPerformed);

        btnRefresh1.setText("-");
        btnRefresh1.addActionListener(this::btnRefresh1ActionPerformed);

        btnRefresh2.setText("🗘");
        btnRefresh2.addActionListener(this::btnRefresh2ActionPerformed);

        btnRefresh3.setText("-");
        btnRefresh3.addActionListener(this::btnRefresh3ActionPerformed);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRemoveTarefa))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jButton5)
                                        .addGap(110, 110, 110)
                                        .addComponent(jButton6))
                                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnRefresh1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnRefresh3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(btnRefresh2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(0, 80, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRemoveTarefa))
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRefresh3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRefresh)
                        .addGap(121, 121, 121)
                        .addComponent(jButton8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRefresh1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRefresh2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton6))
                .addGap(36, 36, 36))
        );

        jButton3.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jButton3.setText("Editar Usuario");

        jButton14.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jButton14.setText("📊");
        jButton14.addActionListener(this::jButton14ActionPerformed);

        jButton1.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jButton1.setText("Trocar Usuario");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jButton2.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jButton2.setText("Calendario");
        jButton2.addActionListener(this::jButton2ActionPerformed);

        javax.swing.GroupLayout telaDetalhesLayout = new javax.swing.GroupLayout(telaDetalhes);
        telaDetalhes.setLayout(telaDetalhesLayout);
        telaDetalhesLayout.setHorizontalGroup(
            telaDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(telaDetalhesLayout.createSequentialGroup()
                .addGroup(telaDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, telaDetalhesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addGap(12, 12, 12)))
                .addContainerGap())
            .addGroup(telaDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(telaDetalhesLayout.createSequentialGroup()
                    .addGap(243, 243, 243)
                    .addComponent(jButton3)
                    .addContainerGap(279, Short.MAX_VALUE)))
        );
        telaDetalhesLayout.setVerticalGroup(
            telaDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(telaDetalhesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(telaDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton14)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
            .addGroup(telaDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(telaDetalhesLayout.createSequentialGroup()
                    .addGap(338, 338, 338)
                    .addComponent(jButton3)
                    .addContainerGap(336, Short.MAX_VALUE)))
        );

        panelTarefa.add(telaDetalhes, "telaDetalhes");

        jSplitPane1.setRightComponent(panelTarefa);

        javax.swing.GroupLayout TelaTarefasLayout = new javax.swing.GroupLayout(TelaTarefas);
        TelaTarefas.setLayout(TelaTarefasLayout);
        TelaTarefasLayout.setHorizontalGroup(
            TelaTarefasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        TelaTarefasLayout.setVerticalGroup(
            TelaTarefasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1)
        );

        jPanel1.add(TelaTarefas, "telaTarefas");

        areaCalendario.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        areaCalendario.setLayout(new java.awt.BorderLayout());

        jButton7.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jButton7.setText("Menu");
        jButton7.addActionListener(this::jButton7ActionPerformed);

        javax.swing.GroupLayout telaCalendarioLayout = new javax.swing.GroupLayout(telaCalendario);
        telaCalendario.setLayout(telaCalendarioLayout);
        telaCalendarioLayout.setHorizontalGroup(
            telaCalendarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(telaCalendarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(telaCalendarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(areaCalendario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, telaCalendarioLayout.createSequentialGroup()
                        .addGap(0, 874, Short.MAX_VALUE)
                        .addComponent(jButton7)))
                .addContainerGap())
        );
        telaCalendarioLayout.setVerticalGroup(
            telaCalendarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, telaCalendarioLayout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addComponent(jButton7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(areaCalendario, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );

        jPanel1.add(telaCalendario, "telaCalendario");

        jButton18.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jButton18.setText("Menu");
        jButton18.addActionListener(this::jButton18ActionPerformed);

        jComboBox1.addActionListener(this::jComboBox1ActionPerformed);

        jPanel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel12.setLayout(new java.awt.CardLayout());

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel5.setText("Selecione um Usuario");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(276, 276, 276)
                .addComponent(jLabel5)
                .addContainerGap(280, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(249, 249, 249)
                .addComponent(jLabel5)
                .addContainerGap(324, Short.MAX_VALUE))
        );

        jPanel12.add(jPanel14, "cardVazio");

        telaDesempenho.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel13.setLayout(new java.awt.CardLayout());

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel8.setText("Escolha um tipo de Gráfico para Exibir");

        javax.swing.GroupLayout telaSelectLayout = new javax.swing.GroupLayout(telaSelect);
        telaSelect.setLayout(telaSelectLayout);
        telaSelectLayout.setHorizontalGroup(
            telaSelectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(telaSelectLayout.createSequentialGroup()
                .addGap(211, 211, 211)
                .addComponent(jLabel8)
                .addContainerGap(213, Short.MAX_VALUE))
        );
        telaSelectLayout.setVerticalGroup(
            telaSelectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(telaSelectLayout.createSequentialGroup()
                .addGap(273, 273, 273)
                .addComponent(jLabel8)
                .addContainerGap(218, Short.MAX_VALUE))
        );

        jPanel13.add(telaSelect, "card6");

        telaGraficoDificuldadeTipoAtv.setLayout(new java.awt.BorderLayout());
        jPanel13.add(telaGraficoDificuldadeTipoAtv, "telaGraficoDificuldadeTipoAtv");

        telaGraficoDificuldadeDia.setLayout(new java.awt.BorderLayout());
        jPanel13.add(telaGraficoDificuldadeDia, "telaGraficoDificuldadeDia");

        telaGraficoTempoTipoAtv.setLayout(new java.awt.BorderLayout());
        jPanel13.add(telaGraficoTempoTipoAtv, "telaGraficoTempoTipoAtv");

        telaGraficoTempoDia.setLayout(new java.awt.BorderLayout());
        jPanel13.add(telaGraficoTempoDia, "telaGraficoTempoDia");

        jButton12.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        jButton12.setText("Tempo total por Dia");
        jButton12.addActionListener(this::jButton12ActionPerformed);

        jButton13.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        jButton13.setText("Tempo total por Tipo de Atividade");
        jButton13.addActionListener(this::jButton13ActionPerformed);

        jButton16.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        jButton16.setText("Dificuldade média por Dia");
        jButton16.addActionListener(this::jButton16ActionPerformed);

        jButton17.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        jButton17.setText("Dificuldade média por Tipo de Atividade");
        jButton17.addActionListener(this::jButton17ActionPerformed);

        javax.swing.GroupLayout telaDesempenhoLayout = new javax.swing.GroupLayout(telaDesempenho);
        telaDesempenho.setLayout(telaDesempenhoLayout);
        telaDesempenhoLayout.setHorizontalGroup(
            telaDesempenhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(telaDesempenhoLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(telaDesempenhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(telaDesempenhoLayout.createSequentialGroup()
                        .addComponent(jButton12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 872, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        telaDesempenhoLayout.setVerticalGroup(
            telaDesempenhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(telaDesempenhoLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(telaDesempenhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton12)
                    .addComponent(jButton13)
                    .addComponent(jButton16)
                    .addComponent(jButton17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel12.add(telaDesempenho, "cardDesempenho");

        javax.swing.GroupLayout telaGraficoLayout = new javax.swing.GroupLayout(telaGrafico);
        telaGrafico.setLayout(telaGraficoLayout);
        telaGraficoLayout.setHorizontalGroup(
            telaGraficoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(telaGraficoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(telaGraficoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(telaGraficoLayout.createSequentialGroup()
                        .addComponent(jButton18)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        telaGraficoLayout.setVerticalGroup(
            telaGraficoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, telaGraficoLayout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(telaGraficoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton18)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.add(telaGrafico, "telaGrafico");

        jMenu1.setText("Tarefas");

        jMenuItem1.setText("Criar Tarefa");
        jMenuItem1.addActionListener(this::jMenuItem1ActionPerformed);
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Criar Tipo Atividade");
        jMenuItem2.addActionListener(this::jMenuItem2ActionPerformed);
        jMenu1.add(jMenuItem2);

        jMenuItem6.setText("Editar Tipo Atividade");
        jMenuItem6.addActionListener(this::jMenuItem6ActionPerformed);
        jMenu1.add(jMenuItem6);

        jMenuItem7.setText("Deletar Tipo Atividade");
        jMenuItem7.addActionListener(this::jMenuItem7ActionPerformed);
        jMenu1.add(jMenuItem7);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Usuários");

        jMenuItem3.setText("Criar Usuário");
        jMenuItem3.addActionListener(this::jMenuItem3ActionPerformed);
        jMenu2.add(jMenuItem3);

        jMenuItem4.setText("Editar Usuário");
        jMenuItem4.addActionListener(this::jMenuItem4ActionPerformed);
        jMenu2.add(jMenuItem4);

        jMenuItem5.setText("Deletar Usuário");
        jMenuItem5.addActionListener(this::jMenuItem5ActionPerformed);
        jMenu2.add(jMenuItem5);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 686, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void atualizarGraficosDesempenho(int idUsuarioAlvo) {
        
        telaGraficoTempoDia.removeAll();
        telaGraficoDificuldadeDia.removeAll();
        telaGraficoDificuldadeTipoAtv.removeAll();
        telaGraficoTempoTipoAtv.removeAll();
        
        JPanel graficoTempoDiaMenu = criarGraficoBarras(Desempenho.getTempoTotalPorDia(idUsuarioAlvo), "Tempo por dia", "dia", "tempo(h)");
        JPanel graficoTempoDia = criarGraficoBarras(Desempenho.getTempoTotalPorDia(idUsuarioAlvo), "Tempo por dia", "dia", "tempo(h)");
        JPanel graficoDificuldadeDia = criarGraficoBarras(Desempenho.getDificuldadeMediaPorDia(idUsuarioAlvo), "Dificuldade por dia", "dia", "Dificuldade");
        JPanel graficoDificuldadeTipo = criarGraficoBarras(Desempenho.getDificuldadeMediaPorTipoAtividade(idUsuarioAlvo), "Dificuldade por tipo", "tipo", "dificuldade");
        JPanel graficoTempoAtividade = criarGraficoBarras(Desempenho.getTempoTotalPorTipoAtividade(idUsuarioAlvo), "Tempo por TipoAtividade", "tipoAtividade", "Tempo");
        
        telaGraficoTempoDia.add(graficoTempoDia, BorderLayout.CENTER);
        telaGraficoTempoDia.validate();
        telaGraficoTempoDia.repaint();
        telaGraficoDificuldadeDia.add(graficoDificuldadeDia, BorderLayout.CENTER);
        telaGraficoDificuldadeDia.validate();
        telaGraficoDificuldadeDia.repaint();
        telaGraficoDificuldadeTipoAtv.add(graficoDificuldadeTipo, BorderLayout.CENTER);
        telaGraficoDificuldadeTipoAtv.validate();
        telaGraficoDificuldadeTipoAtv.repaint();
        telaGraficoTempoTipoAtv.add(graficoTempoAtividade, BorderLayout.CENTER);
        telaGraficoTempoTipoAtv.validate();
        telaGraficoTempoTipoAtv.repaint();
    }
    
    private void listaSelecionadaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaSelecionadaMouseClicked
        Tarefa t = listaSelecionada.getSelectedValue();
        
        if (t != null) {
            
            tarefaSelecionadaTarefa = t;
            mostrarTelaDetalhes(true);
            titleTitulo.setText(t.getTitulo());
            textDescricao.setText(t.getDescricao());
            carregarUsuariosDaTarefa(t);
            carregarComentarios(t.getId());
            String dataBonita = formatarData(t.getDataEntrega());
            textDetalhes.setText("Data de Entrega: "+ dataBonita + "\n"
                    + "Estado: " + t.getEstado().name() + "\n"
                    + "Dificuldade: " + t.getDificuldade().name() + "\n"
                    + "Tipo da Tarefa: " + t.getTipoAtividade().getNome());
            carregarListas();
            
        }
    }//GEN-LAST:event_listaSelecionadaMouseClicked

    private void listaPendentesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaPendentesMouseClicked
        Tarefa t = listaPendentes.getSelectedValue();
        
        if (t != null) {
            
            tarefaSelecionadaTarefa = t;
            mostrarTelaDetalhes(true);
            titleTitulo.setText(t.getTitulo());
            textDescricao.setText(t.getDescricao());
            carregarUsuariosDaTarefa(t);
            carregarComentarios(t.getId());
            String dataBonita = formatarData(t.getDataEntrega());
            textDetalhes.setText("Data de Entrega: "+ dataBonita + "\n"
                    + "Estado: " + t.getEstado().name() + "\n"
                    + "Dificuldade: " + t.getDificuldade().name() + "\n"
                    + "Tipo da Tarefa: " + t.getTipoAtividade().getNome());
            carregarListas();
            
        }
    }//GEN-LAST:event_listaPendentesMouseClicked

    private void listaConcluidaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaConcluidaMouseClicked
        Tarefa t = listaConcluida.getSelectedValue();
        
        if (t != null) {
            
            tarefaSelecionadaTarefa = t;
            mostrarTelaDetalhes(true);
            titleTitulo.setText(t.getTitulo());
            textDescricao.setText(t.getDescricao());
            carregarUsuariosDaTarefa(t);
            carregarComentarios(t.getId());
            String dataBonita = formatarData(t.getDataEntrega());
            textDetalhes.setText("Data de Entrega: "+ dataBonita + "\n"
                    + "Estado: " + t.getEstado().name() + "\n"
                    + "Dificuldade: " + t.getDificuldade().name() + "\n"
                    + "Tipo da Tarefa: " + t.getTipoAtividade().getNome());
            carregarListas();
            
        }
    }//GEN-LAST:event_listaConcluidaMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        AddComentario addC = new AddComentario(tarefaSelecionadaTarefa.getId());
        addC.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        carregarComentarios(tarefaSelecionadaTarefa.getId());
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        EditarTarefa et = new EditarTarefa(tarefaSelecionadaTarefa);
        et.setVisible(true);
        carregarListas();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void btnRemoveTarefaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveTarefaActionPerformed
        CardLayout cl = (CardLayout) panelTarefa.getLayout();
        cl.show(panelTarefa, "telaAviso");
    }//GEN-LAST:event_btnRemoveTarefaActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        AddUserTarefa aut = new AddUserTarefa(tarefaSelecionadaTarefa);
        aut.setVisible(true);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void btnRefresh1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefresh1ActionPerformed
        RemoveUserTarefa rut = new RemoveUserTarefa(tarefaSelecionadaTarefa);
        rut.setVisible(true);
    }//GEN-LAST:event_btnRefresh1ActionPerformed

    private void btnRefresh2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefresh2ActionPerformed
        carregarUsuariosDaTarefa(tarefaSelecionadaTarefa);
        carregarListas();
    }//GEN-LAST:event_btnRefresh2ActionPerformed

    private void btnRefresh3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefresh3ActionPerformed
        Comentario c = listaComentarios.getSelectedValue();
        if (c != null) {
            ComentarioBO cBO = new ComentarioBO();
            
            if (cBO.deleteById(c.getId())) {
                JOptionPane.showMessageDialog(this, 
                        "Comentario deletado com sucesso.", 
                        "Aviso", 
                        JOptionPane.PLAIN_MESSAGE
                    );
            }
        }
    }//GEN-LAST:event_btnRefresh3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        JFrame = new JFrame("Exit");
            
        if (JOptionPane.showConfirmDialog(JFrame, "Você tem certeza que quer deletar a tarefa?\nVocê não poderá recupera-la novamente.", "Atenção!",
                JOptionPane.OK_OPTION) == JOptionPane.OK_OPTION) {

            TarefaBO tBO = new TarefaBO();

            if (tBO.delete(tarefaSelecionadaTarefa.getId())) {
                carregarListas();
                JOptionPane.showMessageDialog(this, 
                    "Tarefa deletada com sucesso.", 
                    "Aviso", 
                    JOptionPane.PLAIN_MESSAGE
                );
                mostrarTelaDetalhes(false);
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Algo de errado aconteceu.", 
                    "Aviso", 
                    JOptionPane.PLAIN_MESSAGE
                );
            }
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        CardLayout cl = (CardLayout) jPanel1.getLayout();
        cl.show(jPanel1, "telaGrafico");
        carregarUsuariosParaAnalise();
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        CardLayout cl = (CardLayout) jPanel1.getLayout();
        cl.show(jPanel1, "telaGrafico");
        carregarUsuariosParaAnalise();
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        CriarTarefa ct = new CriarTarefa();
        ct.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        CriarTipoAtividade cta = new CriarTipoAtividade();
        cta.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        AddUsuarioPopup aup = new AddUsuarioPopup();
        aup.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        EditUsuarioPopup eup = new EditUsuarioPopup();
        eup.setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        DeleteUser du = new DeleteUser(usuarioLogado);
        du.setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        EditarTipoAtv eta = new EditarTipoAtv();
        eta.setVisible(true);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        DeleteTipoAtv dta = new DeleteTipoAtv();
        dta.setVisible(true);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        CardLayout cl = (CardLayout) jPanel1.getLayout();
        cl.show(jPanel1, "telaCalendario");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        CardLayout cl = (CardLayout) jPanel1.getLayout();
        cl.show(jPanel1, "telaTarefas");
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        CardLayout cl = (CardLayout) jPanel1.getLayout();
        cl.show(jPanel1, "telaCalendario");
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        TelaLogin tl = new TelaLogin();
        tl.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        TelaLogin tl = new TelaLogin();
        tl.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnRefresh4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefresh4ActionPerformed
        carregarListas();
    }//GEN-LAST:event_btnRefresh4ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        CardLayout cl = (CardLayout) jPanel13.getLayout();
        cl.show(jPanel13, "telaGraficoTempoDia");
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        CardLayout cl = (CardLayout) jPanel13.getLayout();
        cl.show(jPanel13, "telaGraficoTempoTipoAtv");
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        CardLayout cl = (CardLayout) jPanel13.getLayout();
        cl.show(jPanel13, "telaGraficoDificuldadeDia");
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        CardLayout cl = (CardLayout) jPanel13.getLayout();
        cl.show(jPanel13, "telaGraficoDificuldadeTipoAtv");
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        CardLayout cl = (CardLayout) jPanel1.getLayout();
        cl.show(jPanel1, "telaTarefas");
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed

        dto.Usuario selecionado = (dto.Usuario) jComboBox1.getSelectedItem();
    
        java.awt.CardLayout cl = (java.awt.CardLayout) jPanel12.getLayout();

        if (selecionado == null) {
            cl.show(jPanel12, "cardVazio");

        } else {
            atualizarGraficosDesempenho(selecionado.getId());

            cl.show(jPanel12, "cardDesempenho");
        }

    }//GEN-LAST:event_jComboBox1ActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Usuario usuarioTeste = new Usuario();
                usuarioTeste.setId(1);
                usuarioTeste.setNome("Admin de Teste");

                new TelaFuncionario(usuarioTeste).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel TelaTarefas;
    private javax.swing.JPanel areaCalendario;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnRefresh1;
    private javax.swing.JButton btnRefresh2;
    private javax.swing.JButton btnRefresh3;
    private javax.swing.JButton btnRefresh4;
    private javax.swing.JButton btnRemoveTarefa;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<Usuario> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JList<Comentario> listaComentarios;
    private javax.swing.JList<Tarefa> listaConcluida;
    private javax.swing.JList<Tarefa> listaPendentes;
    private javax.swing.JList<Tarefa> listaSelecionada;
    private javax.swing.JList<Usuario> listaUsuariosTarefa;
    private javax.swing.JPanel panelListas;
    private javax.swing.JPanel panelTarefa;
    private javax.swing.JPanel telaAviso;
    private javax.swing.JPanel telaCalendario;
    private javax.swing.JPanel telaDesempenho;
    private javax.swing.JPanel telaDetalhes;
    private javax.swing.JPanel telaGrafico;
    private javax.swing.JPanel telaGraficoDificuldadeDia;
    private javax.swing.JPanel telaGraficoDificuldadeTipoAtv;
    private javax.swing.JPanel telaGraficoTempoDia;
    private javax.swing.JPanel telaGraficoTempoTipoAtv;
    private javax.swing.JPanel telaSelect;
    private javax.swing.JTextArea textDescricao;
    private javax.swing.JTextArea textDetalhes;
    private javax.swing.JLabel titleTitulo;
    // End of variables declaration//GEN-END:variables
}
