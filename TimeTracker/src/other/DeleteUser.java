package other;

import bo.UsuarioBO;
import dto.Usuario;
import javax.swing.JOptionPane;

public class DeleteUser extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(DeleteUser.class.getName());
    private Usuario usuarioLogado;
    
    public DeleteUser(Usuario usuarioLogado) {
        initComponents();
        this.usuarioLogado = usuarioLogado;
        carregarUsuariosParaExclusao();
    }

    private void carregarUsuariosParaExclusao() {
        UsuarioBO bo = new UsuarioBO();
        java.util.List<Usuario> todosUsuarios = bo.readAll();

        cmbUsuarios.removeAllItems(); 

        cmbUsuarios.setRenderer(new javax.swing.DefaultListCellRenderer() {
            @Override
            public java.awt.Component getListCellRendererComponent(javax.swing.JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if (value instanceof Usuario) {
                    Usuario u = (Usuario) value;
                    setText(u.getNome() + " (" + u.getTipo() + ")");
                }
                return this;
            }
        });

        for (Usuario u : todosUsuarios) {

            if (u.getId() == usuarioLogado.getId()) {
                continue;
            }

            cmbUsuarios.addItem(u);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cmbUsuarios = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Selecione o Usuario");

        cmbUsuarios.setModel(new javax.swing.DefaultComboBoxModel<>(new dto.Usuario[] {}));

        jButton1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jButton1.setText("Excluir");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmbUsuarios, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addGap(28, 28, 28)
                .addComponent(cmbUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dto.Usuario selecionado = (dto.Usuario) cmbUsuarios.getSelectedItem();
    
        if (selecionado != null) {

            int resposta = javax.swing.JOptionPane.showConfirmDialog(this, 
                    "Tem certeza que deseja excluir o usuário " + selecionado.getNome() + "?", 
                    "Confirmar Exclusão", 
                    javax.swing.JOptionPane.YES_NO_OPTION);

            if (resposta == javax.swing.JOptionPane.YES_OPTION) {

                bo.UsuarioBO bo = new bo.UsuarioBO();
                boolean sucesso = bo.delete(selecionado.getId());

                if (sucesso) {
                    JOptionPane.showMessageDialog(this, 
                    "Usuário deletado com Sucesso!", 
                    "Aviso", 
                    JOptionPane.PLAIN_MESSAGE);

                    carregarUsuariosParaExclusao(); 

                } else {
                    JOptionPane.showMessageDialog(this, 
                    "Erro ao excluir.", 
                    "Aviso", 
                    JOptionPane.PLAIN_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
    private javax.swing.JComboBox<Usuario> cmbUsuarios;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
