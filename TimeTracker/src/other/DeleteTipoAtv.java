package other;

import bo.TarefaBO;
import bo.TipoAtividadeBO;
import dto.Tarefa;
import dto.TipoAtividade;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class DeleteTipoAtv extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(DeleteTipoAtv.class.getName());
    private JFrame JFrame;

    public DeleteTipoAtv() {
        initComponents();
        carregarTiposAtividade();
    }

    private void carregarTiposAtividade() {
        TipoAtividadeBO bo = new TipoAtividadeBO();
        java.util.List<TipoAtividade> lista = bo.readAll();

        cmbTipoAtividade.removeAllItems();

        cmbTipoAtividade.setRenderer(new javax.swing.DefaultListCellRenderer() {
            @Override
            public java.awt.Component getListCellRendererComponent(javax.swing.JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof TipoAtividade) {
                    setText(((TipoAtividade) value).getNome());
                }
                return this;
            }
        });

        for (TipoAtividade t : lista) {
            cmbTipoAtividade.addItem(t);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cmbTipoAtividade = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Selecione o Tipo de Atividade");

        cmbTipoAtividade.addActionListener(this::cmbTipoAtividadeActionPerformed);

        jButton1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jButton1.setText("Deletar");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(cmbTipoAtividade, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(jButton1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbTipoAtividade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cmbTipoAtividadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoAtividadeActionPerformed
        
    }//GEN-LAST:event_cmbTipoAtividadeActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        TipoAtividade tipoParaDeletar = (TipoAtividade) cmbTipoAtividade.getSelectedItem();
    
        if (tipoParaDeletar != null) {

            JFrame = new JFrame("Exit");
            
            if (JOptionPane.showConfirmDialog(JFrame, "Você tem certeza que quer deletar o Tipo de Atividade?\n"
                    + "Todas as Tarefas conectadas a esse Tipo de Atividade serão excluidas.", "Atenção!",
                    JOptionPane.OK_OPTION) == JOptionPane.OK_OPTION) {

                TarefaBO tarefaBO = new TarefaBO();

                List<Tarefa> todasAsTarefas = tarefaBO.readAll();

                for (Tarefa t : todasAsTarefas) {
                    if (t.getTipoAtividade() != null && t.getTipoAtividade().getId() == tipoParaDeletar.getId()) {
                        tarefaBO.delete(t.getId());
                    }
                }

                TipoAtividadeBO tipoBO = new TipoAtividadeBO();

                if (tipoBO.deleteById(tipoParaDeletar.getId())) { 
                    JOptionPane.showMessageDialog(this,
                    "Tipo de Atividade deletada com sucesso!",
                    "Aviso",
                    JOptionPane.PLAIN_MESSAGE);

                    carregarTiposAtividade(); 
                } else {
                    JOptionPane.showMessageDialog(this,
                    "Erro ao deletar Tipo de Atividade.",
                    "Aviso",
                    JOptionPane.PLAIN_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
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
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new DeleteTipoAtv().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<TipoAtividade> cmbTipoAtividade;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
