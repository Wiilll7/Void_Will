package other;

import javax.swing.JOptionPane;
import dto.Usuario;
import bo.UsuarioBO;
import dto.Tarefa;
import java.util.List;
import javax.swing.JOptionPane;

public class RemoveUserTarefa extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(RemoveUserTarefa.class.getName());
    private Tarefa tarefaSelecionada;
    
    public RemoveUserTarefa(Tarefa tarefaSelecionada) {
        this.tarefaSelecionada = tarefaSelecionada;
        initComponents();
        carregarUsuariosParaRemover();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmbUsuarios = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        cmbUsuarios.setModel(new javax.swing.DefaultComboBoxModel<>());

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Selecione o Usuário");

        jButton1.setText("Remover");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmbUsuarios, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(104, 104, 104)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addGap(24, 24, 24)
                .addComponent(cmbUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(23, 23, 23))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Usuario u = (Usuario) cmbUsuarios.getSelectedItem();
        if (u != null) {
            UsuarioBO uBO = new UsuarioBO();

            if (uBO.removeTarefa(u, tarefaSelecionada)) {
                JOptionPane.showMessageDialog(this,
                    "Usuário removido com sucesso!",
                    "Aviso",
                    JOptionPane.PLAIN_MESSAGE);

            } else {
                JOptionPane.showMessageDialog(this,
                    "Algo de errado aconteceu.",
                    "Aviso",
                    JOptionPane.PLAIN_MESSAGE);
            }
            this.dispose();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void carregarUsuariosParaRemover() {
        bo.UsuarioBO bo = new bo.UsuarioBO();

        java.util.List<dto.Usuario> usuariosNaTarefa = bo.readByTarefaId(tarefaSelecionada.getId());

        cmbUsuarios.removeAllItems();

        cmbUsuarios.setRenderer(new javax.swing.DefaultListCellRenderer() {
            @Override
            public java.awt.Component getListCellRendererComponent(javax.swing.JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof dto.Usuario) {
                    setText(((dto.Usuario) value).getNome());
                }
                return this;
            }
        });

        if (usuariosNaTarefa != null) {
            for (dto.Usuario u : usuariosNaTarefa) {

                if (u.getTipo() == dto.TipoUsuario.ADMIN) {
                    continue;
                }

                cmbUsuarios.addItem(u);
            }
        }
    }
    
    public static void main(String args[]) {
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
        Tarefa t = new Tarefa();
        java.awt.EventQueue.invokeLater(() -> new RemoveUserTarefa(t).setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<Usuario> cmbUsuarios;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
