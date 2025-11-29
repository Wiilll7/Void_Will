package other;

import bo.TarefaBO;
import dto.Tarefa;
import java.util.List;
import javax.swing.DefaultListModel;
import bo.ComentarioBO;
import dto.Comentario;

public class TelaAdmin extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaAdmin.class.getName());

    public TelaAdmin() {
        initComponents();
        carregarListas();
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
        List<Tarefa> todasTarefas = bo.readAll();
        DefaultListModel<Tarefa> modeloAFazer = new DefaultListModel<>();
        DefaultListModel<Tarefa> modeloConcluidas = new DefaultListModel<>();

        for (Tarefa t : todasTarefas) {
            if (t.getEstado().getId() == 4) { 
                modeloConcluidas.addElement(t);
            } else if (t.getEstado().getId() == 2) {
                modeloAFazer.addElement(t);
            }
        }
        
        listaAFazer.setModel(modeloAFazer);
        listaConcluidas.setModel(modeloConcluidas);

        listaAFazer.setCellRenderer(new TarefaRenderer());
        listaConcluidas.setCellRenderer(new TarefaRenderer());
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane2 = new javax.swing.JSplitPane();
        jSplitPane3 = new javax.swing.JSplitPane();
        centro = new javax.swing.JPanel();
        areaTitulo = new javax.swing.JPanel();
        titleTitulo = new javax.swing.JLabel();
        panelComentarios = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        listaComentarios = new javax.swing.JList<>();
        ladoConcluido = new javax.swing.JPanel();
        titleTarefasConcluidas = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaConcluidas = new javax.swing.JList<>();
        ladoAFazer = new javax.swing.JPanel();
        titleTarefasPendentes = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaAFazer = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jSplitPane2.setDividerLocation(230);
        jSplitPane2.setMinimumSize(new java.awt.Dimension(191, 40));
        jSplitPane2.setPreferredSize(new java.awt.Dimension(191, 40));

        jSplitPane3.setDividerLocation(420);

        areaTitulo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        titleTitulo.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        titleTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleTitulo.setText("Titulo\n");

        javax.swing.GroupLayout areaTituloLayout = new javax.swing.GroupLayout(areaTitulo);
        areaTitulo.setLayout(areaTituloLayout);
        areaTituloLayout.setHorizontalGroup(
            areaTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titleTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
        areaTituloLayout.setVerticalGroup(
            areaTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titleTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
        );

        panelComentarios.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(41, 43, 45)), "Comentarios", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        listaComentarios.setModel(new javax.swing.DefaultListModel<dto.Comentario>());
        jScrollPane3.setViewportView(listaComentarios);

        javax.swing.GroupLayout panelComentariosLayout = new javax.swing.GroupLayout(panelComentarios);
        panelComentarios.setLayout(panelComentariosLayout);
        panelComentariosLayout.setHorizontalGroup(
            panelComentariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
        );
        panelComentariosLayout.setVerticalGroup(
            panelComentariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelComentariosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout centroLayout = new javax.swing.GroupLayout(centro);
        centro.setLayout(centroLayout);
        centroLayout.setHorizontalGroup(
            centroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(centroLayout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(centroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(areaTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelComentarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(58, Short.MAX_VALUE))
        );
        centroLayout.setVerticalGroup(
            centroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(centroLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(areaTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(panelComentarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(183, Short.MAX_VALUE))
        );

        jSplitPane3.setLeftComponent(centro);

        titleTarefasConcluidas.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        titleTarefasConcluidas.setText("Tarefas Concluidas");

        listaConcluidas.setModel(new javax.swing.DefaultListModel<dto.Tarefa>());
        jScrollPane2.setViewportView(listaConcluidas);

        javax.swing.GroupLayout ladoConcluidoLayout = new javax.swing.GroupLayout(ladoConcluido);
        ladoConcluido.setLayout(ladoConcluidoLayout);
        ladoConcluidoLayout.setHorizontalGroup(
            ladoConcluidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ladoConcluidoLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(titleTarefasConcluidas)
                .addContainerGap(32, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ladoConcluidoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        ladoConcluidoLayout.setVerticalGroup(
            ladoConcluidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ladoConcluidoLayout.createSequentialGroup()
                .addGap(0, 8, Short.MAX_VALUE)
                .addComponent(titleTarefasConcluidas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 658, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addGap(0, 37, Short.MAX_VALUE))
            .addGroup(ladoAFazerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        ladoAFazerLayout.setVerticalGroup(
            ladoAFazerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ladoAFazerLayout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addComponent(titleTarefasPendentes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 658, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jSplitPane2.setLeftComponent(ladoAFazer);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void listaAFazerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaAFazerMouseClicked
        Tarefa tarefaSelecionada = listaAFazer.getSelectedValue();
    
        if (tarefaSelecionada != null) {
            atualizarTitulo(tarefaSelecionada.getTitulo());
            //txtDescricao.setText(tarefaSelecionada.getDescricao());
            carregarComentarios(tarefaSelecionada.getId());  
        }
    }//GEN-LAST:event_listaAFazerMouseClicked

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
        java.awt.EventQueue.invokeLater(() -> new TelaAdmin().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel areaTitulo;
    private javax.swing.JPanel centro;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JSplitPane jSplitPane3;
    private javax.swing.JPanel ladoAFazer;
    private javax.swing.JPanel ladoConcluido;
    private javax.swing.JList<Tarefa> listaAFazer;
    private javax.swing.JList<Comentario> listaComentarios;
    private javax.swing.JList<Tarefa> listaConcluidas;
    private javax.swing.JPanel panelComentarios;
    private javax.swing.JLabel titleTarefasConcluidas;
    private javax.swing.JLabel titleTarefasPendentes;
    private javax.swing.JLabel titleTitulo;
    // End of variables declaration//GEN-END:variables
}
