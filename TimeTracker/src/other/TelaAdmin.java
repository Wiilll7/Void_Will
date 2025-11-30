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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class TelaAdmin extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaAdmin.class.getName());
    private Usuario usuarioLogado;
    private Tarefa tarefaSelecionadaTarefa;
    
    public TelaAdmin(Usuario usuario) {
        usuarioLogado = usuario;
        initComponents();
        carregarListas();
        aplicarEstiloGlobal(this);
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
        telaMenu = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
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
        panelTarefa = new javax.swing.JPanel();
        telaAviso = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        telaDetalhes = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
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

        jButton1.setText("Tarefas");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        javax.swing.GroupLayout telaMenuLayout = new javax.swing.GroupLayout(telaMenu);
        telaMenu.setLayout(telaMenuLayout);
        telaMenuLayout.setHorizontalGroup(
            telaMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(telaMenuLayout.createSequentialGroup()
                .addGap(386, 386, 386)
                .addComponent(jButton1)
                .addContainerGap(460, Short.MAX_VALUE))
        );
        telaMenuLayout.setVerticalGroup(
            telaMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(telaMenuLayout.createSequentialGroup()
                .addGap(206, 206, 206)
                .addComponent(jButton1)
                .addContainerGap(470, Short.MAX_VALUE))
        );

        jPanel1.add(telaMenu, "telaMenu");

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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
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
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Tarefas Concluidas");

        javax.swing.GroupLayout panelListasLayout = new javax.swing.GroupLayout(panelListas);
        panelListas.setLayout(panelListasLayout);
        panelListasLayout.setHorizontalGroup(
            panelListasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelListasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelListasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelListasLayout.setVerticalGroup(
            panelListasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelListasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
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

        jButton2.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jButton2.setText("Menu");
        jButton2.addActionListener(this::jButton2ActionPerformed);

        javax.swing.GroupLayout telaAvisoLayout = new javax.swing.GroupLayout(telaAviso);
        telaAviso.setLayout(telaAvisoLayout);
        telaAvisoLayout.setHorizontalGroup(
            telaAvisoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(telaAvisoLayout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addComponent(jLabel4)
                .addContainerGap(188, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, telaAvisoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addContainerGap())
        );
        telaAvisoLayout.setVerticalGroup(
            telaAvisoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(telaAvisoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2)
                .addGap(256, 256, 256)
                .addComponent(jLabel4)
                .addContainerGap(365, Short.MAX_VALUE))
        );

        panelTarefa.add(telaAviso, "telaAviso");

        jButton3.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jButton3.setText("Menu");
        jButton3.addActionListener(this::jButton3ActionPerformed);

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

        jButton6.setText("jButton6");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton5)
                        .addGap(18, 18, 18)
                        .addComponent(jButton6)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                        .addComponent(btnRefresh)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5)
                    .addComponent(jButton6))
                .addGap(36, 36, 36))
        );

        javax.swing.GroupLayout telaDetalhesLayout = new javax.swing.GroupLayout(telaDetalhes);
        telaDetalhes.setLayout(telaDetalhesLayout);
        telaDetalhesLayout.setHorizontalGroup(
            telaDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(telaDetalhesLayout.createSequentialGroup()
                .addGroup(telaDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(telaDetalhesLayout.createSequentialGroup()
                        .addContainerGap(609, Short.MAX_VALUE)
                        .addComponent(jButton3))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        telaDetalhesLayout.setVerticalGroup(
            telaDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(telaDetalhesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        CardLayout cl = (CardLayout) jPanel1.getLayout();
        cl.show(jPanel1, "telaTarefas");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        CardLayout cl = (CardLayout) jPanel1.getLayout();
        cl.show(jPanel1, "telaMenu");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        CardLayout cl = (CardLayout) jPanel1.getLayout();
        cl.show(jPanel1, "telaMenu");
    }//GEN-LAST:event_jButton3ActionPerformed

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
            
        }
    }//GEN-LAST:event_listaConcluidaMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        AddComentario addC = new AddComentario(tarefaSelecionadaTarefa.getId());
        addC.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        carregarComentarios(tarefaSelecionadaTarefa.getId());
    }//GEN-LAST:event_btnRefreshActionPerformed

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
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
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
    private javax.swing.JPanel telaDetalhes;
    private javax.swing.JPanel telaMenu;
    private javax.swing.JTextArea textDescricao;
    private javax.swing.JTextArea textDetalhes;
    private javax.swing.JLabel titleTitulo;
    // End of variables declaration//GEN-END:variables
}
