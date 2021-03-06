/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mib.grupp2;

import Klasser.Agent;
import Klasser.Validering;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import oru.inf.InfException;
import oru.inf.InfDB;

/**
 *
 * @author jonat
 */
public class AndraLosenord extends javax.swing.JFrame {

    /**
     * Skapar ett nytt f?nster och initierar komponenterna
     */
    public AndraLosenord() {
        initComponents();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelInfoText = new javax.swing.JLabel();
        jLabelInfoNuvarandeLosenord = new javax.swing.JLabel();
        jTextInputLosenord = new javax.swing.JTextField();
        jLabelInfoNyttLosenord = new javax.swing.JLabel();
        jTextInputNyttLosen = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jButtonByt = new javax.swing.JButton();
        jBtnExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabelInfoText.setText("H?r kan du ?ndra ditt l?senord");

        jLabelInfoNuvarandeLosenord.setText("Ditt nuvarande l?senord:");

        jLabelInfoNyttLosenord.setText("Ditt nya l?senord:");

        jButtonByt.setText("Byt");
        jButtonByt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBytActionPerformed(evt);
            }
        });

        jBtnExit.setText("St?ng f?nster");
        jBtnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(165, 165, 165)
                .addComponent(jBtnExit))
            .addGroup(layout.createSequentialGroup()
                .addGap(147, 147, 147)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelInfoNyttLosenord, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4)
                                .addComponent(jLabelInfoText))
                            .addGap(129, 129, 129))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jTextInputNyttLosen, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextInputLosenord, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabelInfoNuvarandeLosenord, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonByt, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(41, 41, 41)))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelInfoText)
                .addGap(35, 35, 35)
                .addComponent(jLabelInfoNuvarandeLosenord)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextInputLosenord, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jLabelInfoNyttLosenord)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextInputNyttLosen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonByt))
                .addGap(29, 29, 29)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addComponent(jBtnExit)
                .addGap(33, 33, 33))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
/**
 * 
 * @param evt (Klick p? knapp)
 * Kollar vem som ?r inloggad , aka vem ska byta l?senord. ?r f?ltet agent i huvudf?nster
 * null, inneb?r det att en alien ?r inloggad..
 * S?tter in l?senordet i objektet och sedan uppdaterar databasen
 * St?mmer ej nuvarande l?senord eller valideringen inte ?r true, ge felmedelanden
 */
    private void jButtonBytActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBytActionPerformed
        if (Validering.validPassword(jTextInputLosenord.getText()) && Validering.validPassword(jTextInputNyttLosen.getText())) {
            if (Huvudfonster.agent != null) {
                if (Huvudfonster.agent.getLosenord().equals(jTextInputLosenord.getText())) {
                    String nytt = jTextInputNyttLosen.getText();
                    if (nytt.length() > 6) {
                        JOptionPane.showMessageDialog(null, "L?senordet f?r h?gsst vara 6 tecken");
                        return;
                    } else {
                        Huvudfonster.agent.setLosenord(nytt);
                    }
                    try {
                        Huvudfonster.agent.updateLosenordSql();
                        JOptionPane.showMessageDialog(null, "Ditt nya l?senord ?r registrerat!");
                    } catch (InfException ex) {
                        System.out.println(ex);
                    }
                } else if (!Huvudfonster.agent.getLosenord().equals(jTextInputLosenord.getText())) {
                    JOptionPane.showMessageDialog(null, "Ditt nuvarande l?senord st?mmer inte med det du skrivit in");
                }
            } else if (Huvudfonster.alien.getLosenord().equals(jTextInputLosenord.getText())) {
                String nytt = jTextInputNyttLosen.getText();
                if (nytt.length() > 6) {
                    JOptionPane.showMessageDialog(null, "L?senordet f?r h?gst vara 6 tecken");
                    return;
                } else {
                    Huvudfonster.alien.setLosenord(nytt);
                }
                try {
                    Huvudfonster.alien.updateLosenordSql();
                    JOptionPane.showMessageDialog(null, "Ditt nya l?senord ?r registrerat!");
                } catch (InfException ex) {
                    System.out.println(ex);
                }
            } else if (!Huvudfonster.alien.getLosenord().equals(jTextInputLosenord.getText())) {
                JOptionPane.showMessageDialog(null, "Ditt nuvarande l?senord st?mmer inte med det du skrivit in");
            }
        }

    }//GEN-LAST:event_jButtonBytActionPerformed

    private void jBtnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnExitActionPerformed
        dispose();
    }//GEN-LAST:event_jBtnExitActionPerformed

    /**
     * @param args the command line arguments
     */
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AndraLosenord.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AndraLosenord.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AndraLosenord.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AndraLosenord.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AndraLosenord().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnExit;
    private javax.swing.JButton jButtonByt;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelInfoNuvarandeLosenord;
    private javax.swing.JLabel jLabelInfoNyttLosenord;
    private javax.swing.JLabel jLabelInfoText;
    private javax.swing.JTextField jTextInputLosenord;
    private javax.swing.JTextField jTextInputNyttLosen;
    // End of variables declaration//GEN-END:variables
}
