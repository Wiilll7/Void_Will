package other;

import dto.Usuario;
import bo.UsuarioBO;
import dto.TipoUsuario;

public class AddUsuarioPopup extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AddUsuarioPopup.class.getName());

    public AddUsuarioPopup() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNomeUsuario = new javax.swing.JTextField();
        pswSenha = new javax.swing.JPasswordField();
        chbExibeSenha = new javax.swing.JCheckBox();
        pswConfirmaSenha = new javax.swing.JPasswordField();
        chbExibeConfirmarSenha = new javax.swing.JCheckBox();
        btnConfirmarAdd = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        cmbTipoFuncionario = new javax.swing.JComboBox<>();
        titleRetorno = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Adicionar Novo Usuário");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setText("Nome de Usuário:");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setText("Senha:");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setText("Confirmar Senha:");

        txtNomeUsuario.addActionListener(this::txtNomeUsuarioActionPerformed);

        chbExibeSenha.setText("Exibir Senha");
        chbExibeSenha.addActionListener(this::chbExibeSenhaActionPerformed);

        chbExibeConfirmarSenha.setText("Exibir Senha");
        chbExibeConfirmarSenha.addActionListener(this::chbExibeConfirmarSenhaActionPerformed);

        btnConfirmarAdd.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnConfirmarAdd.setText("Confirmar");
        btnConfirmarAdd.addActionListener(this::btnConfirmarAddActionPerformed);

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setText("Tipo de Usuário:");

        cmbTipoFuncionario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Funcionario", "Admin" }));

        titleRetorno.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(chbExibeConfirmarSenha)
                            .addComponent(txtNomeUsuario)
                            .addComponent(pswSenha)
                            .addComponent(chbExibeSenha)
                            .addComponent(pswConfirmaSenha)
                            .addComponent(cmbTipoFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(btnConfirmarAdd)))
                .addContainerGap(27, Short.MAX_VALUE))
            .addComponent(titleRetorno, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNomeUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(pswSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chbExibeSenha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pswConfirmaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chbExibeConfirmarSenha)
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cmbTipoFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnConfirmarAdd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(titleRetorno, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(9, Short.MAX_VALUE))
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtNomeUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeUsuarioActionPerformed

    private void chbExibeSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbExibeSenhaActionPerformed
        if (chbExibeSenha.isSelected()) {
            pswSenha.setEchoChar((char)0);
        } else {
            pswSenha.setEchoChar('*');
        }
    }//GEN-LAST:event_chbExibeSenhaActionPerformed

    private void chbExibeConfirmarSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbExibeConfirmarSenhaActionPerformed
        if (chbExibeConfirmarSenha.isSelected()) {
            pswConfirmaSenha.setEchoChar((char)0);
        } else {
            pswConfirmaSenha.setEchoChar('*');
        }
    }//GEN-LAST:event_chbExibeConfirmarSenhaActionPerformed
    
    private void btnConfirmarAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarAddActionPerformed
        String user = txtNomeUsuario.getText();
        char[] arraySenha = pswSenha.getPassword();
        String senha = new String(arraySenha);
        char[] arrayConfirmaSenha = pswConfirmaSenha.getPassword();
        String confirmaSenha = new String(arrayConfirmaSenha);

        if (senha.equals(confirmaSenha)) {
            
            if (user == null || user.trim().isEmpty()) {
                titleRetorno.setForeground(new java.awt.Color(200, 0, 0));
                titleRetorno.setText("Nome de usuário inválido.");
                return;
            }

            if (cmbTipoFuncionario.getSelectedIndex() == 0) {
                TipoUsuario tu = TipoUsuario.FUNCIONARIO;
                Usuario u = new Usuario(9, user, senha, tu);
                UsuarioBO uBO = new UsuarioBO();

                if (uBO.create(u)) {
                    txtNomeUsuario.setText(null);
                    pswSenha.setText(null);
                    pswConfirmaSenha.setText(null);
                    titleRetorno.setForeground(java.awt.Color.decode("#28a745"));
                    titleRetorno.setText("Usuário criado com sucesso!");
                } else {
                    titleRetorno.setForeground(new java.awt.Color(200, 0, 0));
                    titleRetorno.setText("Já existe outro usuário com esse login.");
                }

            } else if (cmbTipoFuncionario.getSelectedIndex() == 1) {
                TipoUsuario tu = TipoUsuario.ADMIN;
                Usuario u = new Usuario(9, user, senha, tu);
                UsuarioBO uBO = new UsuarioBO();

                if (uBO.create(u)) {
                    txtNomeUsuario.setText(null);
                    pswSenha.setText(null);
                    pswConfirmaSenha.setText(null);
                    titleRetorno.setForeground(java.awt.Color.decode("#28a745"));
                    titleRetorno.setText("Usuário criado com sucesso!");
                } else {
                    titleRetorno.setForeground(new java.awt.Color(200, 0, 0));
                    titleRetorno.setText("Já existe outro usuário com esse login.");
                }
            } else {
                titleRetorno.setForeground(new java.awt.Color(200, 0, 0));
                titleRetorno.setText("Algo de errado aconteu.");
            }

        } else {
            titleRetorno.setForeground(new java.awt.Color(200, 0, 0));
            titleRetorno.setText("As senhas não coincidem.");
        }

    }//GEN-LAST:event_btnConfirmarAddActionPerformed

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

        java.awt.EventQueue.invokeLater(() -> new AddUsuarioPopup().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConfirmarAdd;
    private javax.swing.JCheckBox chbExibeConfirmarSenha;
    private javax.swing.JCheckBox chbExibeSenha;
    private javax.swing.JComboBox<String> cmbTipoFuncionario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField pswConfirmaSenha;
    private javax.swing.JPasswordField pswSenha;
    private javax.swing.JLabel titleRetorno;
    private javax.swing.JTextField txtNomeUsuario;
    // End of variables declaration//GEN-END:variables
}
