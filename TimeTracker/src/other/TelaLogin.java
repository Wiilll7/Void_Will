package other;

import dto.Usuario;
import bo.UsuarioBO;
import dto.TipoUsuario;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class TelaLogin extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaLogin.class.getName());

    public TelaLogin() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titleLogin = new javax.swing.JLabel();
        titleUsuario = new javax.swing.JLabel();
        titleSenha = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        btnEntrar = new javax.swing.JButton();
        chbMostrarSenha = new javax.swing.JCheckBox();
        pswSenha = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        titleLogin.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        titleLogin.setText("LOGIN");

        titleUsuario.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        titleUsuario.setText("Usuário:");

        titleSenha.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        titleSenha.setText("Senha:");

        txtUsuario.addActionListener(this::txtUsuarioActionPerformed);

        btnEntrar.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btnEntrar.setText("Entrar");
        btnEntrar.addActionListener(this::btnEntrarActionPerformed);

        chbMostrarSenha.setText("Mostrar senha");
        chbMostrarSenha.addActionListener(this::chbMostrarSenhaActionPerformed);

        pswSenha.addActionListener(this::pswSenhaActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(titleLogin)
                .addGap(112, 112, 112))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addComponent(btnEntrar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(75, 75, 75)
                                .addComponent(chbMostrarSenha))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(titleUsuario)
                                    .addComponent(titleSenha))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtUsuario)
                                    .addComponent(pswSenha, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE))))))
                .addContainerGap(61, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(titleLogin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(titleUsuario)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(titleSenha)
                    .addComponent(pswSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chbMostrarSenha)
                .addGap(26, 26, 26)
                .addComponent(btnEntrar)
                .addGap(47, 47, 47))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioActionPerformed

    private void btnEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntrarActionPerformed
        String usuario = txtUsuario.getText();
        char[] senhaArray = pswSenha.getPassword();
        String senha = new String(senhaArray);
        
        UsuarioBO userBO = new UsuarioBO();
        Usuario us = userBO.readBySenha(usuario, senha);
        if (us != null) {
            if (us.getTipo() == TipoUsuario.ADMIN) {
                TelaAdmin ta = new TelaAdmin();
                ta.setVisible(true);
                this.dispose();
            } else if (us.getTipo() == TipoUsuario.FUNCIONARIO) {
                TelaFuncionario tf = new TelaFuncionario();
                tf.setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, 
                "Tipo do usuário indefinido.", 
                "Aviso", 
                JOptionPane.PLAIN_MESSAGE
            );
            }
            
        } else {
            JOptionPane.showMessageDialog(this, 
                "Usuário e/ou senha incorretos.", 
                "Aviso", 
                JOptionPane.PLAIN_MESSAGE
            );
        }
    }//GEN-LAST:event_btnEntrarActionPerformed

    private void chbMostrarSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbMostrarSenhaActionPerformed
        if (chbMostrarSenha.isSelected()) {
            pswSenha.setEchoChar((char)0);
        } else {
            pswSenha.setEchoChar('*');
        }
    }//GEN-LAST:event_chbMostrarSenhaActionPerformed

    private void pswSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pswSenhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pswSenhaActionPerformed

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

        java.awt.EventQueue.invokeLater(() -> new TelaLogin().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEntrar;
    private javax.swing.JCheckBox chbMostrarSenha;
    private javax.swing.JPasswordField pswSenha;
    private javax.swing.JLabel titleLogin;
    private javax.swing.JLabel titleSenha;
    private javax.swing.JLabel titleUsuario;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
