package other;

import dto.Usuario;
import bo.UsuarioBO;
import dto.TipoUsuario;
import java.util.List;

public class EditUsuarioPopup extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(EditUsuarioPopup.class.getName());

    public EditUsuarioPopup() {
        initComponents();
        carregarUsuariosNoCombo();
    }

    private void carregarUsuariosNoCombo() {
        UsuarioBO bo = new UsuarioBO();
        List<Usuario> listaUsuarios = bo.readAll(); 

        cmbUsuarios.removeAllItems();

        cmbUsuarios.setRenderer(new javax.swing.DefaultListCellRenderer() {
            @Override
            public java.awt.Component getListCellRendererComponent(javax.swing.JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if (value instanceof dto.Usuario) {
                    dto.Usuario u = (dto.Usuario) value;
                    setText(u.getNome());
                }
                return this;
            }
        });

        for (Usuario u : listaUsuarios) {
            cmbUsuarios.addItem(u);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        cmbUsuarios = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        chbExibeConfirmarSenha1 = new javax.swing.JCheckBox();
        btnConfirmarEdit = new javax.swing.JButton();
        titleRetorno1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        labelNome2 = new javax.swing.JLabel();
        cmbTipoFuncionario1 = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtNomeUsuario1 = new javax.swing.JTextField();
        pswSenha1 = new javax.swing.JPasswordField();
        chbExibeSenha1 = new javax.swing.JCheckBox();
        pswConfirmaSenha1 = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        cmbUsuarios.setModel(new javax.swing.DefaultComboBoxModel<>(new dto.Usuario[] {}));
        cmbUsuarios.addActionListener(this::cmbUsuariosActionPerformed);

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setText("Escolha o Usuário:");

        chbExibeConfirmarSenha1.setText("Exibir Senha");
        chbExibeConfirmarSenha1.addActionListener(this::chbExibeConfirmarSenha1ActionPerformed);

        btnConfirmarEdit.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnConfirmarEdit.setText("Confirmar");
        btnConfirmarEdit.addActionListener(this::btnConfirmarEditActionPerformed);

        titleRetorno1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel8.setText("Tipo de Usuário:");

        labelNome2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        labelNome2.setText("Nome:");

        cmbTipoFuncionario1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Funcionario", "Admin" }));

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel10.setText("Senha:");

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel11.setText("Confirmar Senha:");

        txtNomeUsuario1.addActionListener(this::txtNomeUsuario1ActionPerformed);

        chbExibeSenha1.setText("Exibir Senha");
        chbExibeSenha1.addActionListener(this::chbExibeSenha1ActionPerformed);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel6)
                        .addGap(6, 6, 6)
                        .addComponent(cmbUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(147, 147, 147)
                        .addComponent(chbExibeConfirmarSenha1))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel8)
                        .addGap(6, 6, 6)
                        .addComponent(cmbTipoFuncionario1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(titleRetorno1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(6, 6, 6)
                                .addComponent(pswSenha1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(6, 6, 6)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(chbExibeSenha1)
                                    .addComponent(pswConfirmaSenha1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(labelNome2)
                                .addGap(6, 6, 6)
                                .addComponent(txtNomeUsuario1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(btnConfirmarEdit)))
                .addGap(6, 6, 6))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel6))
                    .addComponent(cmbUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(labelNome2))
                    .addComponent(txtNomeUsuario1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel10))
                    .addComponent(pswSenha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chbExibeSenha1)
                .addGap(3, 3, 3)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel11))
                    .addComponent(pswConfirmaSenha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addComponent(chbExibeConfirmarSenha1)
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel8))
                    .addComponent(cmbTipoFuncionario1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addComponent(btnConfirmarEdit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(titleRetorno1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel2.setText("Editar Usuário Existente");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cmbUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbUsuariosActionPerformed
        Usuario u = (Usuario) cmbUsuarios.getSelectedItem();

        if (u != null) {
            txtNomeUsuario1.setText(u.getNome());
            pswSenha1.setText(u.getSenha());
            pswConfirmaSenha1.setText(u.getSenha());

            switch (u.getTipo()) {
                case ADMIN:
                cmbTipoFuncionario1.setSelectedItem("Admin");
                break;
                case FUNCIONARIO:
                cmbTipoFuncionario1.setSelectedItem("Funcionario");
                break;
            }

        }
    }//GEN-LAST:event_cmbUsuariosActionPerformed

    private void chbExibeConfirmarSenha1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbExibeConfirmarSenha1ActionPerformed
        if (chbExibeConfirmarSenha1.isSelected()) {
            pswConfirmaSenha1.setEchoChar((char)0);
        } else {
            pswConfirmaSenha1.setEchoChar('*');
        }
    }//GEN-LAST:event_chbExibeConfirmarSenha1ActionPerformed

    private void btnConfirmarEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarEditActionPerformed
        Usuario u = (Usuario) cmbUsuarios.getSelectedItem();
        UsuarioBO uBO = new UsuarioBO();
        String user = txtNomeUsuario1.getText();
        char[] arraySenha = pswSenha1.getPassword();
        String senha = new String(arraySenha);
        char[] arrayConfirmaSenha = pswConfirmaSenha1.getPassword();
        String confirmaSenha = new String(arrayConfirmaSenha);
        
        if (u != null) {
            
            if (user == null || user.trim().isEmpty()) {
                titleRetorno1.setForeground(new java.awt.Color(200, 0, 0));
                titleRetorno1.setText("Nome de usuário inválido.");
                return;
            }
            
            if (senha.equals(confirmaSenha)) {

                switch (cmbTipoFuncionario1.getSelectedIndex()) {
                    case 0:
                    TipoUsuario tu = TipoUsuario.FUNCIONARIO;
                    Usuario usuario = new Usuario(u.getId(), user, senha, tu);

                    if (uBO.update(usuario)) {
                        txtNomeUsuario1.setText(null);
                        pswSenha1.setText(null);
                        pswConfirmaSenha1.setText(null);
                        titleRetorno1.setForeground(java.awt.Color.decode("#28a745"));
                        titleRetorno1.setText("Usuário editado com sucesso!");
                    } else {
                        titleRetorno1.setForeground(new java.awt.Color(200, 0, 0));
                        titleRetorno1.setText("Já existe outro usuário com esse login.");
                    }
                    break;
                    case 1:
                    TipoUsuario tu1 = TipoUsuario.ADMIN;
                    Usuario usuario1 = new Usuario(u.getId(), user, senha, tu1);

                    if (uBO.update(usuario1)) {
                        txtNomeUsuario1.setText(null);
                        pswSenha1.setText(null);
                        pswConfirmaSenha1.setText(null);
                        titleRetorno1.setForeground(java.awt.Color.decode("#28a745"));
                        titleRetorno1.setText("Usuário editado com sucesso!");
                    } else {
                        titleRetorno1.setForeground(new java.awt.Color(200, 0, 0));
                        titleRetorno1.setText("Já existe outro usuário com esse login.");
                    }
                    break;
                    default:
                    titleRetorno1.setForeground(new java.awt.Color(200, 0, 0));
                    titleRetorno1.setText("Algo de errado aconteu.");
                    break;
                }
            } else {
                titleRetorno1.setForeground(new java.awt.Color(200, 0, 0));
                titleRetorno1.setText("As senhas não coincidem.");
            }
        }

    }//GEN-LAST:event_btnConfirmarEditActionPerformed

    private void txtNomeUsuario1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeUsuario1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeUsuario1ActionPerformed

    private void chbExibeSenha1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbExibeSenha1ActionPerformed
        if (chbExibeSenha1.isSelected()) {
            pswSenha1.setEchoChar((char)0);
        } else {
            pswSenha1.setEchoChar('*');
        }
    }//GEN-LAST:event_chbExibeSenha1ActionPerformed

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

        java.awt.EventQueue.invokeLater(() -> new EditUsuarioPopup().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConfirmarEdit;
    private javax.swing.JCheckBox chbExibeConfirmarSenha1;
    private javax.swing.JCheckBox chbExibeSenha1;
    private javax.swing.JComboBox<String> cmbTipoFuncionario1;
    private javax.swing.JComboBox<Usuario> cmbUsuarios;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel labelNome2;
    private javax.swing.JPasswordField pswConfirmaSenha1;
    private javax.swing.JPasswordField pswSenha1;
    private javax.swing.JLabel titleRetorno1;
    private javax.swing.JTextField txtNomeUsuario1;
    // End of variables declaration//GEN-END:variables
}
