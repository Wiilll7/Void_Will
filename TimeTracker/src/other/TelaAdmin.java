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
    
    public TelaAdmin(Usuario usuario) {
        usuarioLogado = usuario;
        initComponents();
        carregarListas();
        aplicarEstiloGlobal(this);
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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
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
                .addContainerGap(115, Short.MAX_VALUE))
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

        javax.swing.GroupLayout telaDetalhesLayout = new javax.swing.GroupLayout(telaDetalhes);
        telaDetalhes.setLayout(telaDetalhesLayout);
        telaDetalhesLayout.setHorizontalGroup(
            telaDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, telaDetalhesLayout.createSequentialGroup()
                .addContainerGap(536, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addContainerGap())
        );
        telaDetalhesLayout.setVerticalGroup(
            telaDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(telaDetalhesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton3)
                .addContainerGap(668, Short.MAX_VALUE))
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
            
            mostrarTelaDetalhes(true);
            
        }
    }//GEN-LAST:event_listaSelecionadaMouseClicked

    private void listaPendentesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaPendentesMouseClicked
        Tarefa t = listaPendentes.getSelectedValue();
        
        if (t != null) {
            
            mostrarTelaDetalhes(true);
            
        }
    }//GEN-LAST:event_listaPendentesMouseClicked

    private void listaConcluidaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaConcluidaMouseClicked
        Tarefa t = listaConcluida.getSelectedValue();
        
        if (t != null) {
            
            mostrarTelaDetalhes(true);
            
        }
    }//GEN-LAST:event_listaConcluidaMouseClicked

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
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JList<Tarefa> listaConcluida;
    private javax.swing.JList<Tarefa> listaPendentes;
    private javax.swing.JList<Tarefa> listaSelecionada;
    private javax.swing.JPanel panelListas;
    private javax.swing.JPanel panelTarefa;
    private javax.swing.JPanel telaAviso;
    private javax.swing.JPanel telaDetalhes;
    private javax.swing.JPanel telaMenu;
    // End of variables declaration//GEN-END:variables
}
