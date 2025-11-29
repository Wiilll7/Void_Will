package other;

import bo.TarefaBO;
import dto.Tarefa;
import java.util.List;
import javax.swing.DefaultListModel;

public class TelaAdmin extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaAdmin.class.getName());

    public TelaAdmin() {
        initComponents();
    }

    public void atualizarTitulo(String nomeDaTarefa) {
        String textoFormatado = "<html><div style='text-align: center; width: 115px;'>" 
                                + nomeDaTarefa + "</div></html>";

        titleTitulo.setText(textoFormatado);
    }
    
    private void carregarListas() {
        TarefaBO bo = new TarefaBO();
        List<Tarefa> todasTarefas = bo.readAll();
        DefaultListModel<Tarefa> modeloAFazer = new DefaultListModel<>();
        DefaultListModel<Tarefa> modeloConcluidas = new DefaultListModel<>();

        for (Tarefa t : todasTarefas) {
            if (t.getEstado().getId() == 3) { 
                modeloConcluidas.addElement(t);
            } else {
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
        ladoConcluido = new javax.swing.JScrollPane();
        listaConcluidas = new javax.swing.JList<>();
        centro = new javax.swing.JPanel();
        areaTitulo = new javax.swing.JPanel();
        titleTitulo = new javax.swing.JLabel();
        comentario = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        ladoAFazer = new javax.swing.JScrollPane();
        listaAFazer = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jSplitPane2.setDividerLocation(230);
        jSplitPane2.setMinimumSize(new java.awt.Dimension(191, 40));
        jSplitPane2.setPreferredSize(new java.awt.Dimension(191, 40));

        jSplitPane3.setDividerLocation(420);

        listaConcluidas.setModel(new javax.swing.DefaultListModel<dto.Tarefa>());
        ladoConcluido.setViewportView(listaConcluidas);

        jSplitPane3.setRightComponent(ladoConcluido);

        areaTitulo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        titleTitulo.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        titleTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleTitulo.setText("Titulo\n");

        javax.swing.GroupLayout areaTituloLayout = new javax.swing.GroupLayout(areaTitulo);
        areaTitulo.setLayout(areaTituloLayout);
        areaTituloLayout.setHorizontalGroup(
            areaTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titleTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        areaTituloLayout.setVerticalGroup(
            areaTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titleTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
        );

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        comentario.setViewportView(jTextArea1);

        javax.swing.GroupLayout centroLayout = new javax.swing.GroupLayout(centro);
        centro.setLayout(centroLayout);
        centroLayout.setHorizontalGroup(
            centroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(centroLayout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(centroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(comentario, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                    .addComponent(areaTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(58, Short.MAX_VALUE))
        );
        centroLayout.setVerticalGroup(
            centroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(centroLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(areaTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(comentario, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(303, Short.MAX_VALUE))
        );

        jSplitPane3.setLeftComponent(centro);

        jSplitPane2.setRightComponent(jSplitPane3);

        listaAFazer.setModel(new javax.swing.DefaultListModel<dto.Tarefa>());
        ladoAFazer.setViewportView(listaAFazer);

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
    private javax.swing.JScrollPane comentario;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JSplitPane jSplitPane3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JScrollPane ladoAFazer;
    private javax.swing.JScrollPane ladoConcluido;
    private javax.swing.JList<Tarefa> listaAFazer;
    private javax.swing.JList<Tarefa> listaConcluidas;
    private javax.swing.JLabel titleTitulo;
    // End of variables declaration//GEN-END:variables
}
