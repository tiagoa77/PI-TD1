/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Classes.Cliente;
import Classes.Local;
import Classes.Sistema;
import java.awt.Toolkit;
import javax.swing.JOptionPane;

/**
 *
 * @author Tiago
 */
public class AdicionarCliente extends javax.swing.JDialog {

    private OCP o;

    /**
     * Creates new form AdicionarCliente
     */
    public AdicionarCliente(OCP o) {

        initComponents();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Tiago\\Documents\\NetBeansProjects\\PI-TD1\\logo.png"));
        this.setTitle("OCP Portugal");
        this.o = o;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelNovoCliente = new javax.swing.JLabel();
        jLabelImagem = new javax.swing.JLabel();
        jTextFieldNome = new javax.swing.JTextField();
        jLabelNome = new javax.swing.JLabel();
        jButtonRegistar = new javax.swing.JButton();
        jLabelNomeFarmaceutico = new javax.swing.JLabel();
        jTextFieldNomeFarmaceutico = new javax.swing.JTextField();
        jLabelContacto = new javax.swing.JLabel();
        jTextFieldContacto = new javax.swing.JTextField();
        jLabelNIF = new javax.swing.JLabel();
        jTextFieldNIF = new javax.swing.JTextField();
        jLabeMorada = new javax.swing.JLabel();
        jTextFieldMorada = new javax.swing.JTextField();
        jLabeConcelho = new javax.swing.JLabel();
        jTextFieldConcelho = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        setResizable(false);

        jLabelNovoCliente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelNovoCliente.setText("Novo Cliente");

        jLabelImagem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/logo.png"))); // NOI18N

        jLabelNome.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelNome.setText("Nome:");

        jButtonRegistar.setText("Registar");
        jButtonRegistar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRegistarActionPerformed(evt);
            }
        });

        jLabelNomeFarmaceutico.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelNomeFarmaceutico.setText("Nome Farmacêutico:");

        jLabelContacto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelContacto.setText("Contacto:");

        jLabelNIF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelNIF.setText("NIF:");

        jLabeMorada.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabeMorada.setText("Morada:");

        jLabeConcelho.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabeConcelho.setText("Concelho");

        jTextFieldConcelho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldConcelhoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabelNovoCliente))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabeConcelho)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jTextFieldConcelho, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabelNomeFarmaceutico)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextFieldNomeFarmaceutico, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jButtonRegistar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabelContacto)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jTextFieldContacto, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabelNome, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(32, 32, 32)
                                        .addComponent(jTextFieldNome))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabelNIF)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jTextFieldNIF, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabeMorada)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jTextFieldMorada, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 12, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jLabelNovoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNome, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNomeFarmaceutico, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldNomeFarmaceutico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelContacto, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldContacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNIF, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldNIF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabeMorada, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldMorada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabeConcelho, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldConcelho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jButtonRegistar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonRegistarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRegistarActionPerformed
        String nomeFarmacia = jTextFieldNome.getText();
        String nomeResp = jTextFieldNomeFarmaceutico.getText();
        int contacto = Integer.parseInt(jTextFieldContacto.getText());
        int nif = Integer.parseInt(jTextFieldNIF.getText());
        String morada = jTextFieldMorada.getText();
        String concelho = jTextFieldConcelho.getText();
        int id = 0;
        int id_local = 0;

        Local novoL = new Local(id, morada, concelho);

        Local local = this.o.getSistema().addLocal(novoL.getId_local(), novoL);

        Cliente novoC = new Cliente(id, nomeFarmacia, nomeResp, contacto, nif, 1, local.getId_local());
        if (this.o.getSistema().addCliente(novoC.getId_cliente(), novoC) == 1) {
            JOptionPane.showMessageDialog(null, "Adicionado com Sucesso");
            this.setVisible(false);
        }
        else {
            JOptionPane.showMessageDialog(null, "Erro");
    }
    }//GEN-LAST:event_jButtonRegistarActionPerformed

    private void jTextFieldConcelhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldConcelhoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldConcelhoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonRegistar;
    private javax.swing.JLabel jLabeConcelho;
    private javax.swing.JLabel jLabeMorada;
    private javax.swing.JLabel jLabelContacto;
    private javax.swing.JLabel jLabelImagem;
    private javax.swing.JLabel jLabelNIF;
    private javax.swing.JLabel jLabelNome;
    private javax.swing.JLabel jLabelNomeFarmaceutico;
    private javax.swing.JLabel jLabelNovoCliente;
    private javax.swing.JTextField jTextFieldConcelho;
    private javax.swing.JTextField jTextFieldContacto;
    private javax.swing.JTextField jTextFieldMorada;
    private javax.swing.JTextField jTextFieldNIF;
    private javax.swing.JTextField jTextFieldNome;
    private javax.swing.JTextField jTextFieldNomeFarmaceutico;
    // End of variables declaration//GEN-END:variables
}
