/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Classes.Sistema;
import Classes.Login;
import javax.swing.JOptionPane;

/**
 *
 * @author Tiago
 */
public class AdicionarAdmin extends javax.swing.JDialog {

    Sistema sistema;

    /**
     * Creates new form AdicionarAdmin
     */
    public AdicionarAdmin(Sistema s) {
        initComponents();
        this.sistema = s;

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelAdicionarUtilizador = new javax.swing.JPanel();
        jLabelImagem = new javax.swing.JLabel();
        jLabelUser = new javax.swing.JLabel();
        jTextFieldUser = new javax.swing.JTextField();
        jLabelUserPass = new javax.swing.JLabel();
        jPasswordFieldPass = new javax.swing.JPasswordField();
        jButtonRegistar = new javax.swing.JButton();
        jLabelNovoUtilizador = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabelImagem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/logo.png"))); // NOI18N

        jLabelUser.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelUser.setText("Nome");

        jLabelUserPass.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelUserPass.setText("Password");

        jPasswordFieldPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordFieldPassActionPerformed(evt);
            }
        });

        jButtonRegistar.setText("Registar");
        jButtonRegistar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRegistarActionPerformed(evt);
            }
        });

        jLabelNovoUtilizador.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelNovoUtilizador.setText("Novo Administrador");

        javax.swing.GroupLayout jPanelAdicionarUtilizadorLayout = new javax.swing.GroupLayout(jPanelAdicionarUtilizador);
        jPanelAdicionarUtilizador.setLayout(jPanelAdicionarUtilizadorLayout);
        jPanelAdicionarUtilizadorLayout.setHorizontalGroup(
            jPanelAdicionarUtilizadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelAdicionarUtilizadorLayout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jLabelNovoUtilizador)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanelAdicionarUtilizadorLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanelAdicionarUtilizadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonRegistar, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelAdicionarUtilizadorLayout.createSequentialGroup()
                        .addGroup(jPanelAdicionarUtilizadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelUserPass, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelUser, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelAdicionarUtilizadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldUser)
                            .addComponent(jPasswordFieldPass, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelAdicionarUtilizadorLayout.setVerticalGroup(
            jPanelAdicionarUtilizadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAdicionarUtilizadorLayout.createSequentialGroup()
                .addGroup(jPanelAdicionarUtilizadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelAdicionarUtilizadorLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabelImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelAdicionarUtilizadorLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelNovoUtilizador, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanelAdicionarUtilizadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelUser, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanelAdicionarUtilizadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPasswordFieldPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelUserPass, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButtonRegistar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelAdicionarUtilizador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelAdicionarUtilizador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPasswordFieldPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordFieldPassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordFieldPassActionPerformed

    private void jButtonRegistarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRegistarActionPerformed
        // TODO add your handling code here:
        String login = jTextFieldUser.getText();
        String pass = jPasswordFieldPass.getText();
        Login novo = new Login(login, pass);
        int i = 0;
        this.sistema.getLogins().put(i, novo);

        JOptionPane.showMessageDialog(null, "Registado com sucesso");
        new Inicio().setVisible(true);
        this.setVisible(false);

    }//GEN-LAST:event_jButtonRegistarActionPerformed

/**
 * @param args the command line arguments
 */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonRegistar;
    private javax.swing.JLabel jLabelImagem;
    private javax.swing.JLabel jLabelNovoUtilizador;
    private javax.swing.JLabel jLabelUser;
    private javax.swing.JLabel jLabelUserPass;
    private javax.swing.JPanel jPanelAdicionarUtilizador;
    private javax.swing.JPasswordField jPasswordFieldPass;
    private javax.swing.JTextField jTextFieldUser;
    // End of variables declaration//GEN-END:variables
}