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
import dto.Estado;
import javax.swing.JOptionPane;

public class TelaFuncionario extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaFuncionario.class.getName());
    private Usuario usuarioLogado;
    private Tarefa tarefaSelecionadaTarefa;

    public TelaFuncionario(Usuario usuario) {
        this.usuarioLogado = usuario;
        initComponents();
        carregarListas();
        mostrarPainelDetalhes(false);
        aplicarEstiloGlobal(this);
        txtDescricao.setLineWrap(true);
        txtDescricao.setWrapStyleWord(true);
        aplicarEstiloGlobal(this);
        this.setSize(900, 700);
    }
    private String formatarData(java.time.LocalDateTime data) {
        if (data == null) {
            return "Sem data";
        }
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return data.format(formatter);
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
    
    private void mostrarPainelDetalhes(boolean mostrar) {
        CardLayout cl = (CardLayout) centro.getLayout();

        if (mostrar) {
            cl.show(centro, "painelDetalhes");
        } else {
            cl.show(centro, "painelAviso");
        }
    }
    public void atualizarTitulo(String nomeDaTarefa) {
        String textoFormatado = "<html><div style='text-align: center; width: 115px;'>" 
                                + nomeDaTarefa + "</div></html>";

        titleTitulo.setText(textoFormatado);
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
    
    private void carregarListas() {
        TarefaBO bo = new TarefaBO();
        List<Tarefa> todasTarefas = bo.readByUsuarioId(usuarioLogado.getId());
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
        
        listaAFazer.setModel(modeloAFazer);
        listaConcluidas.setModel(modeloConcluidas);
        listaSelecionadas.setModel(modeloSelecionada);

        listaAFazer.setCellRenderer(new TarefaRenderer());
        listaConcluidas.setCellRenderer(new TarefaRenderer());
        listaSelecionadas.setCellRenderer(new TarefaRenderer());
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        telaMenu = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        btnTrabalho = new javax.swing.JButton();
        telaTarefas = new javax.swing.JPanel();
        jSplitPane2 = new javax.swing.JSplitPane();
        jSplitPane3 = new javax.swing.JSplitPane();
        centro = new javax.swing.JPanel();
        painelDetalhes = new javax.swing.JPanel();
        areaTitulo = new javax.swing.JPanel();
        titleTitulo = new javax.swing.JLabel();
        panelComentarios = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        listaComentarios = new javax.swing.JList<>();
        painelDescricao = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtDescricao = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        textDetalhes = new javax.swing.JTextArea();
        btnLixeira = new javax.swing.JButton();
        btnSelecionar = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        painelAviso = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        ladoConcluido = new javax.swing.JPanel();
        titleTarefasConcluidas = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaConcluidas = new javax.swing.JList<>();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        ladoAFazer = new javax.swing.JPanel();
        titleTarefasPendentes = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaAFazer = new javax.swing.JList<>();
        telaTrabalho = new javax.swing.JPanel();
        panelTarefasSelecionadas = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        listaSelecionadas = new javax.swing.JList<>();
        btnMenu = new javax.swing.JButton();
        btnTelaTarefas = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        telaAvisoTrabalho = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new java.awt.CardLayout());

        jButton1.setText("Tela Tarefas");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        btnTrabalho.setText("Tela Trabalho");
        btnTrabalho.addActionListener(this::btnTrabalhoActionPerformed);

        javax.swing.GroupLayout telaMenuLayout = new javax.swing.GroupLayout(telaMenu);
        telaMenu.setLayout(telaMenuLayout);
        telaMenuLayout.setHorizontalGroup(
            telaMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(telaMenuLayout.createSequentialGroup()
                .addGap(269, 269, 269)
                .addComponent(jButton1)
                .addGap(90, 90, 90)
                .addComponent(btnTrabalho)
                .addContainerGap(382, Short.MAX_VALUE))
        );
        telaMenuLayout.setVerticalGroup(
            telaMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(telaMenuLayout.createSequentialGroup()
                .addGap(324, 324, 324)
                .addGroup(telaMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(btnTrabalho))
                .addContainerGap(360, Short.MAX_VALUE))
        );

        jPanel1.add(telaMenu, "telaMenu");

        jSplitPane2.setDividerLocation(230);
        jSplitPane2.setMinimumSize(new java.awt.Dimension(191, 40));
        jSplitPane2.setPreferredSize(new java.awt.Dimension(191, 40));

        jSplitPane3.setDividerLocation(410);

        centro.setLayout(new java.awt.CardLayout());

        areaTitulo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        titleTitulo.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        titleTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleTitulo.setText("Titulo\n");

        javax.swing.GroupLayout areaTituloLayout = new javax.swing.GroupLayout(areaTitulo);
        areaTitulo.setLayout(areaTituloLayout);
        areaTituloLayout.setHorizontalGroup(
            areaTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(areaTituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        areaTituloLayout.setVerticalGroup(
            areaTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(areaTituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelComentarios.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(41, 43, 45)), "Comentarios", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        listaComentarios.setModel(new javax.swing.DefaultListModel<dto.Comentario>());
        jScrollPane3.setViewportView(listaComentarios);

        javax.swing.GroupLayout panelComentariosLayout = new javax.swing.GroupLayout(panelComentarios);
        panelComentarios.setLayout(panelComentariosLayout);
        panelComentariosLayout.setHorizontalGroup(
            panelComentariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
        );
        panelComentariosLayout.setVerticalGroup(
            panelComentariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
        );

        painelDescricao.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(41, 43, 45)), "Descrição", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jScrollPane5.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        txtDescricao.setColumns(20);
        txtDescricao.setRows(5);
        txtDescricao.setWrapStyleWord(true);
        jScrollPane5.setViewportView(txtDescricao);

        javax.swing.GroupLayout painelDescricaoLayout = new javax.swing.GroupLayout(painelDescricao);
        painelDescricao.setLayout(painelDescricaoLayout);
        painelDescricaoLayout.setHorizontalGroup(
            painelDescricaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        painelDescricaoLayout.setVerticalGroup(
            painelDescricaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelDescricaoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(41, 43, 45)), "Detalhes", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        textDetalhes.setColumns(20);
        textDetalhes.setRows(5);
        jScrollPane4.setViewportView(textDetalhes);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
        );

        btnLixeira.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btnLixeira.setText("Lixeira");
        btnLixeira.addActionListener(this::btnLixeiraActionPerformed);

        btnSelecionar.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btnSelecionar.setText("Selecionar");
        btnSelecionar.addActionListener(this::btnSelecionarActionPerformed);

        jButton3.setText("+");
        jButton3.addActionListener(this::jButton3ActionPerformed);

        btnRefresh.setText("🗘");
        btnRefresh.addActionListener(this::btnRefreshActionPerformed);

        javax.swing.GroupLayout painelDetalhesLayout = new javax.swing.GroupLayout(painelDetalhes);
        painelDetalhes.setLayout(painelDetalhesLayout);
        painelDetalhesLayout.setHorizontalGroup(
            painelDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelDetalhesLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(painelDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(painelDetalhesLayout.createSequentialGroup()
                        .addComponent(btnLixeira)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSelecionar))
                    .addComponent(painelDescricao, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelComentarios, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(areaTitulo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        painelDetalhesLayout.setVerticalGroup(
            painelDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelDetalhesLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(areaTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(painelDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(painelDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelDetalhesLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelComentarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(painelDetalhesLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRefresh)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSelecionar)
                    .addComponent(btnLixeira))
                .addContainerGap(57, Short.MAX_VALUE))
        );

        centro.add(painelDetalhes, "painelDetalhes");

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setText("Selecione uma Tarefa");

        javax.swing.GroupLayout painelAvisoLayout = new javax.swing.GroupLayout(painelAviso);
        painelAviso.setLayout(painelAvisoLayout);
        painelAvisoLayout.setHorizontalGroup(
            painelAvisoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelAvisoLayout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(jLabel1)
                .addContainerGap(34, Short.MAX_VALUE))
        );
        painelAvisoLayout.setVerticalGroup(
            painelAvisoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelAvisoLayout.createSequentialGroup()
                .addGap(316, 316, 316)
                .addComponent(jLabel1)
                .addContainerGap(356, Short.MAX_VALUE))
        );

        centro.add(painelAviso, "painelAviso");

        jSplitPane3.setLeftComponent(centro);

        titleTarefasConcluidas.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        titleTarefasConcluidas.setText("Tarefas Concluidas");

        listaConcluidas.setModel(new javax.swing.DefaultListModel<dto.Tarefa>());
        listaConcluidas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listaConcluidasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(listaConcluidas);

        jButton2.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jButton2.setText("Menu");
        jButton2.addActionListener(this::jButton2ActionPerformed);

        jButton4.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jButton4.setText("Trabalho");
        jButton4.addActionListener(this::jButton4ActionPerformed);

        javax.swing.GroupLayout ladoConcluidoLayout = new javax.swing.GroupLayout(ladoConcluido);
        ladoConcluido.setLayout(ladoConcluidoLayout);
        ladoConcluidoLayout.setHorizontalGroup(
            ladoConcluidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ladoConcluidoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ladoConcluidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                    .addGroup(ladoConcluidoLayout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4)))
                .addContainerGap())
            .addGroup(ladoConcluidoLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(titleTarefasConcluidas)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ladoConcluidoLayout.setVerticalGroup(
            ladoConcluidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ladoConcluidoLayout.createSequentialGroup()
                .addComponent(titleTarefasConcluidas, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 568, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ladoConcluidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton4))
                .addGap(73, 73, 73))
        );

        jSplitPane3.setRightComponent(ladoConcluido);

        jSplitPane2.setRightComponent(jSplitPane3);

        titleTarefasPendentes.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        titleTarefasPendentes.setText("Tarefas Pendentes");

        listaAFazer.setModel(new javax.swing.DefaultListModel<dto.Tarefa>());
        listaAFazer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listaAFazerMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(listaAFazer);

        javax.swing.GroupLayout ladoAFazerLayout = new javax.swing.GroupLayout(ladoAFazer);
        ladoAFazer.setLayout(ladoAFazerLayout);
        ladoAFazerLayout.setHorizontalGroup(
            ladoAFazerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ladoAFazerLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(titleTarefasPendentes)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(ladoAFazerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        ladoAFazerLayout.setVerticalGroup(
            ladoAFazerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ladoAFazerLayout.createSequentialGroup()
                .addComponent(titleTarefasPendentes, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 619, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );

        jSplitPane2.setLeftComponent(ladoAFazer);

        javax.swing.GroupLayout telaTarefasLayout = new javax.swing.GroupLayout(telaTarefas);
        telaTarefas.setLayout(telaTarefasLayout);
        telaTarefasLayout.setHorizontalGroup(
            telaTarefasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 910, Short.MAX_VALUE)
            .addGroup(telaTarefasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jSplitPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 910, Short.MAX_VALUE))
        );
        telaTarefasLayout.setVerticalGroup(
            telaTarefasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 708, Short.MAX_VALUE)
            .addGroup(telaTarefasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jSplitPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE))
        );

        jPanel1.add(telaTarefas, "telaTarefas");

        panelTarefasSelecionadas.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setText("Tarefas Selecionadas");

        listaSelecionadas.setModel(new javax.swing.DefaultListModel<dto.Tarefa>());
        jScrollPane6.setViewportView(listaSelecionadas);

        btnMenu.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btnMenu.setText("Menu");
        btnMenu.addActionListener(this::btnMenuActionPerformed);

        btnTelaTarefas.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btnTelaTarefas.setText("Tarefas");
        btnTelaTarefas.addActionListener(this::btnTelaTarefasActionPerformed);

        javax.swing.GroupLayout panelTarefasSelecionadasLayout = new javax.swing.GroupLayout(panelTarefasSelecionadas);
        panelTarefasSelecionadas.setLayout(panelTarefasSelecionadasLayout);
        panelTarefasSelecionadasLayout.setHorizontalGroup(
            panelTarefasSelecionadasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTarefasSelecionadasLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelTarefasSelecionadasLayout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(panelTarefasSelecionadasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelTarefasSelecionadasLayout.createSequentialGroup()
                        .addComponent(btnMenu)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnTelaTarefas))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        panelTarefasSelecionadasLayout.setVerticalGroup(
            panelTarefasSelecionadasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTarefasSelecionadasLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 528, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTarefasSelecionadasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMenu)
                    .addComponent(btnTelaTarefas))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setLayout(new java.awt.CardLayout());

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel3.setText("Escolha uma Tarefa");

        javax.swing.GroupLayout telaAvisoTrabalhoLayout = new javax.swing.GroupLayout(telaAvisoTrabalho);
        telaAvisoTrabalho.setLayout(telaAvisoTrabalhoLayout);
        telaAvisoTrabalhoLayout.setHorizontalGroup(
            telaAvisoTrabalhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(telaAvisoTrabalhoLayout.createSequentialGroup()
                .addGap(138, 138, 138)
                .addComponent(jLabel3)
                .addContainerGap(147, Short.MAX_VALUE))
        );
        telaAvisoTrabalhoLayout.setVerticalGroup(
            telaAvisoTrabalhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(telaAvisoTrabalhoLayout.createSequentialGroup()
                .addGap(264, 264, 264)
                .addComponent(jLabel3)
                .addContainerGap(381, Short.MAX_VALUE))
        );

        jPanel3.add(telaAvisoTrabalho, "card2");

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 219, Short.MAX_VALUE)
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 565, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 331, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(66, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel4, "card3");

        javax.swing.GroupLayout telaTrabalhoLayout = new javax.swing.GroupLayout(telaTrabalho);
        telaTrabalho.setLayout(telaTrabalhoLayout);
        telaTrabalhoLayout.setHorizontalGroup(
            telaTrabalhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, telaTrabalhoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelTarefasSelecionadas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        telaTrabalhoLayout.setVerticalGroup(
            telaTrabalhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(telaTrabalhoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(telaTrabalhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
                    .addComponent(panelTarefasSelecionadas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel1.add(telaTrabalho, "telaTrabalho");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void listaAFazerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaAFazerMouseClicked
        Tarefa tarefaSelecionada = listaAFazer.getSelectedValue();
    
        if (tarefaSelecionada != null) {
            tarefaSelecionadaTarefa = tarefaSelecionada;
            mostrarPainelDetalhes(true);
            atualizarTitulo(tarefaSelecionada.getTitulo());
            txtDescricao.setText(tarefaSelecionada.getDescricao());
            carregarComentarios(tarefaSelecionada.getId());  
            String dataBonita = formatarData(tarefaSelecionada.getDataEntrega());
            textDetalhes.setText("Data de Entrega: "+ dataBonita + "\n"
                    + "Estado: " + tarefaSelecionada.getEstado().name() + "\n"
                    + "Dificuldade: " + tarefaSelecionada.getDificuldade().name() + "\n"
                    + "Tipo da Tarefa: " + tarefaSelecionada.getTipoAtividade().getNome());
        }
    }//GEN-LAST:event_listaAFazerMouseClicked

    private void listaConcluidasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaConcluidasMouseClicked
        Tarefa tarefaSelecionada = listaConcluidas.getSelectedValue();
    
        if (tarefaSelecionada != null) {
            tarefaSelecionadaTarefa = tarefaSelecionada;
            mostrarPainelDetalhes(true);
            atualizarTitulo(tarefaSelecionada.getTitulo());
            txtDescricao.setText(tarefaSelecionada.getDescricao());
            carregarComentarios(tarefaSelecionada.getId());
            String dataBonita = formatarData(tarefaSelecionada.getDataEntrega());
            textDetalhes.setText("Data de Entrega: "+ dataBonita + "\n"
                    + "Estado: " + tarefaSelecionada.getEstado().name() + "\n"
                    + "Dificuldade: " + tarefaSelecionada.getDificuldade().name() + "\n"
                    + "Tipo da Tarefa: " + tarefaSelecionada.getTipoAtividade().getNome());
        }
    }//GEN-LAST:event_listaConcluidasMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        CardLayout cl = (CardLayout) jPanel1.getLayout();
        cl.show(jPanel1, "telaTarefas");
        carregarListas();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        CardLayout cl = (CardLayout) jPanel1.getLayout();
        cl.show(jPanel1, "telaMenu");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnLixeiraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLixeiraActionPerformed
        TarefaBO tBO = new TarefaBO();
        
        Tarefa t = new Tarefa(tarefaSelecionadaTarefa.getId(), tarefaSelecionadaTarefa.getTitulo(), tarefaSelecionadaTarefa.getDescricao(),
        tarefaSelecionadaTarefa.getDataEntrega(), Estado.NA_LIXEIRA, tarefaSelecionadaTarefa.getDificuldade(), 
        tarefaSelecionadaTarefa.getTipoAtividade());
        if (tBO.update(t)) {
            carregarListas();
            JOptionPane.showMessageDialog(this, 
                "Tarefa movida para a Lixeira.", 
                "Aviso", 
                JOptionPane.PLAIN_MESSAGE
            );
            CardLayout cl = (CardLayout) centro.getLayout();
            cl.show(centro, "painelAviso");
        }
    }//GEN-LAST:event_btnLixeiraActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        AddComentario addC = new AddComentario(tarefaSelecionadaTarefa.getId());
        addC.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnSelecionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelecionarActionPerformed
        TarefaBO tBO = new TarefaBO();
        
        Tarefa t = new Tarefa(tarefaSelecionadaTarefa.getId(), tarefaSelecionadaTarefa.getTitulo(), tarefaSelecionadaTarefa.getDescricao(),
        tarefaSelecionadaTarefa.getDataEntrega(), Estado.EM_ATIVIDADE, tarefaSelecionadaTarefa.getDificuldade(), 
        tarefaSelecionadaTarefa.getTipoAtividade());
        if (tBO.update(t)) {
            carregarListas();
            JOptionPane.showMessageDialog(this, 
                "Tarefa selecionada.", 
                "Aviso", 
                JOptionPane.PLAIN_MESSAGE
            );
            CardLayout cl = (CardLayout) centro.getLayout();
            cl.show(centro, "painelAviso");
        }
    }//GEN-LAST:event_btnSelecionarActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        carregarComentarios(tarefaSelecionadaTarefa.getId());
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnTrabalhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrabalhoActionPerformed
        CardLayout cl = (CardLayout) jPanel1.getLayout();
        cl.show(jPanel1, "telaTrabalho");
    }//GEN-LAST:event_btnTrabalhoActionPerformed

    private void btnMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuActionPerformed
        CardLayout cl = (CardLayout) jPanel1.getLayout();
        cl.show(jPanel1, "telaMenu");
    }//GEN-LAST:event_btnMenuActionPerformed

    private void btnTelaTarefasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTelaTarefasActionPerformed
        CardLayout cl = (CardLayout) jPanel1.getLayout();
        cl.show(jPanel1, "telaTarefas");
    }//GEN-LAST:event_btnTelaTarefasActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        CardLayout cl = (CardLayout) jPanel1.getLayout();
        cl.show(jPanel1, "telaTrabalho");
    }//GEN-LAST:event_jButton4ActionPerformed

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
    private javax.swing.JPanel areaTitulo;
    private javax.swing.JButton btnLixeira;
    private javax.swing.JButton btnMenu;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSelecionar;
    private javax.swing.JButton btnTelaTarefas;
    private javax.swing.JButton btnTrabalho;
    private javax.swing.JPanel centro;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JSplitPane jSplitPane3;
    private javax.swing.JPanel ladoAFazer;
    private javax.swing.JPanel ladoConcluido;
    private javax.swing.JList<Tarefa> listaAFazer;
    private javax.swing.JList<Comentario> listaComentarios;
    private javax.swing.JList<Tarefa> listaConcluidas;
    private javax.swing.JList<Tarefa> listaSelecionadas;
    private javax.swing.JPanel painelAviso;
    private javax.swing.JPanel painelDescricao;
    private javax.swing.JPanel painelDetalhes;
    private javax.swing.JPanel panelComentarios;
    private javax.swing.JPanel panelTarefasSelecionadas;
    private javax.swing.JPanel telaAvisoTrabalho;
    private javax.swing.JPanel telaMenu;
    private javax.swing.JPanel telaTarefas;
    private javax.swing.JPanel telaTrabalho;
    private javax.swing.JTextArea textDetalhes;
    private javax.swing.JLabel titleTarefasConcluidas;
    private javax.swing.JLabel titleTarefasPendentes;
    private javax.swing.JLabel titleTitulo;
    private javax.swing.JTextArea txtDescricao;
    // End of variables declaration//GEN-END:variables
}
