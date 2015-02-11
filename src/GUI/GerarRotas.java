/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Classes.RotasEscolhidas;
import ClassesDAO.MyParams;
import ilog.concert.IloException;
import ilog.concert.IloIntMap;
import ilog.concert.IloIntSet;
import ilog.cplex.IloCplex;
import ilog.opl.IloOplDataSource;
import ilog.opl.IloOplErrorHandler;
import ilog.opl.IloOplException;
import ilog.opl.IloOplFactory;
import ilog.opl.IloOplModel;
import ilog.opl.IloOplModelDefinition;
import ilog.opl.IloOplModelSource;
import ilog.opl.IloOplSettings;
import java.awt.Toolkit;
import java.util.HashMap;

/**
 *
 * @author Tiago
 */
public class GerarRotas extends javax.swing.JDialog {

    private OCP ocp;

    /**
     * Creates new form AdicionarCliente
     */
    public GerarRotas(OCP o) {

        initComponents();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Tiago\\Documents\\NetBeansProjects\\PI-TD1\\logo.png"));
        this.setTitle("OCP Portugal");
        this.ocp = o;

        int status = 127;
        try {
            IloOplFactory.setDebugMode(false);
            IloOplFactory oplF = new IloOplFactory();
            IloOplErrorHandler errHandler = oplF.createOplErrorHandler(System.out);
            IloOplModelSource modelSource = oplF.createOplModelSource("C:\\PI_TD.mod");
            IloOplSettings settings = oplF.createOplSettings(errHandler);
            IloOplModelDefinition def = oplF.createOplModelDefinition(modelSource, settings);
            IloCplex cplex = oplF.createCplex();
            IloOplModel opl = oplF.createOplModel(def, cplex);

            IloOplDataSource dataSource = new MyParams(oplF, ocp);
            opl.addDataSource(dataSource);
            opl.generate();
            if (cplex.solve()) {
                opl.printSolution(System.out);
                opl.postProcess();
            } else {
                System.out.println("NOOOOOOOOOOOOO SOLUTIONNNNNNNNNNNNNNNNN!");
                status = 2;
            }

            HashMap<Integer, RotasEscolhidas> hm = new HashMap<>();
            RotasEscolhidas r = null;
            IloIntMap rs = opl.getElement("y").asIntMap();
            IloIntSet i = opl.getElement("R").asIntSet();

            for (java.util.Iterator it1 = i.iterator(); it1.hasNext();) {
                Integer sub1 = (Integer) it1.next();
                r = new RotasEscolhidas(sub1, rs.get(sub1));
                hm.put(sub1, r);
            }

            for (Integer esc : hm.keySet()) {
                if (hm.get(esc).getEscolhida() == 1) {
                    r = new RotasEscolhidas(esc, esc);
                    this.ocp.getSistema().addRotasEscolhidas(esc, r);
                }
            }

            StringBuilder sb = new StringBuilder();
            sb.append("Rotas Escolhidas: \n");
            for (Integer esc : hm.keySet()) {
                if (hm.get(esc).getEscolhida() == 1) {
                    sb.append("Rota: ").append(esc).append("\n");
                }
            }

            this.jTextOutpud.setText(sb.toString());
            //System.gc();
            oplF.end();

        } catch (IloOplException ex) {
            System.err.println("### OPL exception: " + ex.getMessage());
            ex.printStackTrace();
            status = 2;
        } catch (IloException ex) {
            System.err.println("### CONCERT exception: " + ex.getMessage());
            ex.printStackTrace();
            status = 3;
        } catch (Exception ex) {
            System.err.println("### UNEXPECTED UNKNOWN ERROR ...");
            ex.printStackTrace();
            status = 4;
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelNovoCliente = new javax.swing.JLabel();
        jLabelImagem = new javax.swing.JLabel();
        jButtonSair = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextOutpud = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        setResizable(false);

        jLabelNovoCliente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelNovoCliente.setText("Geração de Rotas");

        jLabelImagem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/logo.png"))); // NOI18N

        jButtonSair.setText("Sair");
        jButtonSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSairActionPerformed(evt);
            }
        });

        jTextOutpud.setColumns(20);
        jTextOutpud.setRows(5);
        jScrollPane1.setViewportView(jTextOutpud);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabelNovoCliente)
                        .addGap(0, 424, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabelImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1))))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(191, 191, 191)
                .addComponent(jButtonSair, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jLabelNovoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonSair, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSairActionPerformed
        this.setVisible(false);

    }//GEN-LAST:event_jButtonSairActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSair;
    private javax.swing.JLabel jLabelImagem;
    private javax.swing.JLabel jLabelNovoCliente;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextOutpud;
    // End of variables declaration//GEN-END:variables
}
