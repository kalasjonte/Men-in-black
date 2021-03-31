/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mib.grupp2;

import static Klasser.Agent.getConnection;
import javax.swing.JOptionPane;
import oru.inf.InfException;
import Klasser.Validering;

/**
 *
 * @author jonat
 */
public class TaBort extends javax.swing.JFrame {

    private int objekt;
    private String tabell;

    /**
     * Skapar och visar f�nstrets komponenter, tar bort och l�gger in val i v�r
     * combobox. G�mmer 1 label och 1 knapp
     */
    public TaBort() {
        initComponents();
        jComboTyp.removeAllItems();
        jComboTyp.addItem("Agent");
        jComboTyp.addItem("Alien");
        jComboTyp.addItem("Utrustning");
        jLblHittad.setVisible(false);
        jBtnRadera.setVisible(false);

    }

    /**
     *
     * @param id p� Agenten
     * @return true/false
     *
     * Ska ta ett id fr�n ageten vi f�rs�ker ta bort och kolla om den �r en chef
     * �r det en chef, ska vi byta ut en mot en annan agent. F�rst kollar den om
     * agenten �r en kontorschef, genom att se om id:et finns i kontorschef
     * tabellen. Finns den inte f�r vi null, och metoden returnerar true. Finns
     * den som chef m�ste anv�ndaren skriva in en ny agent som ska ta �ver
     * kontoret Sen kollar vi om agenten redan �r chef, och att den existrerar.
     * Annars felmedelande Om checkarna �r uppfyllda byter vi ut kontorschefen
     * till en ny agent
     *
     */
    private boolean kontorsChef(int id) {
        boolean kontor = false;
        try {
            String koll = getConnection().fetchSingle("Select AGENT_ID from KONTORSCHEF WHERE AGENT_ID =" + id + ";");

            if (koll != null) {
                String plats = getConnection().fetchSingle("Select KONTORSBETECKNING from KONTORSCHEF WHERE AGENT_ID =" + koll + ";");
                String nyChef = JOptionPane.showInputDialog("Du f�rs�ker ta bort en agent som �r chef �ver ett Kontor, skriv in ett ID p� en ny agent att ta �ver Kontoret.");
                if (Validering.isID(nyChef)) {
                    String koll2 = getConnection().fetchSingle("Select AGENT_ID from KONTORSCHEF WHERE AGENT_ID =" + nyChef + ";");
                    String koll3 = getConnection().fetchSingle("Select AGENT_ID from AGENT WHERE AGENT_ID =" + nyChef + ";");
                    if (koll2 != null) {
                        JOptionPane.showMessageDialog(null, "V�lj en agent som INTE har en chef roll �ver Kontor eller omr�de, och som existerar");
                        return false;
                    } else if (koll3 == null) {
                        JOptionPane.showMessageDialog(null, "Ditt nyinskrivna id tillh�r ingen agent i databasen");
                        return false;
                    } else if (koll2 == null) {
                        getConnection().delete("DELETE FROM KONTORSCHEF WHERE AGENT_ID=" + koll + ";");
                        getConnection().insert("INSERT INTO KONTORSCHEF VALUES(" + nyChef + ",'" + plats + "');");
                        kontor = true;
                        return kontor;
                    }
                }

            } else if (koll == null) {
                return true;
            }
        } catch (InfException e) {
            System.out.print(e);
        }
        return true;
    }

    /**
     *
     *  * @param id p� Agenten
     * @return true/false
     *
     * Ska ta ett id fr�n ageten vi f�rs�ker ta bort och kolla om den �r en chef
     * �r det en chef, ska vi byta ut en mot en annan agent .F�rst kollar den om
     * agenten �r en omr�deschef, genom att se om id:et finns i omr�deschef
     * tabellen. Finns den inte f�r vi null, och metoden returnerar true. Finns
     * den som chef m�ste anv�ndaren skriva in en ny agent som ska ta �ver
     * omr�det. Sen kollar vi om agenten(nya) redan �r chef, och att den existrerar.
     * Annars felmedelande. Om checkarna �r uppfyllda byter vi ut omr�deschefen
     * till en ny agent
     */
    private boolean omradesChef(int id) {
        boolean omrade = false;
        try {
            String koll = getConnection().fetchSingle("Select AGENT_ID from OMRADESCHEF WHERE AGENT_ID =" + id + ";");

            if (koll != null) {
                String plats = getConnection().fetchSingle("Select OMRADE from OMRADESCHEF WHERE AGENT_ID =" + koll + ";");
                String nyChef = JOptionPane.showInputDialog("Du f�rs�ker ta bort en agent som �r chef �ver ett omr�de, skriv in ett ID p� en ny agent att ta �ver omr�de.");
                if (Validering.isID(nyChef)) {
                    String koll2 = getConnection().fetchSingle("Select AGENT_ID from OMRADESCHEF WHERE AGENT_ID =" + nyChef + ";");
                    String koll3 = getConnection().fetchSingle("Select AGENT_ID from AGENT WHERE AGENT_ID =" + nyChef + ";");
                    if (koll2 != null) {
                        JOptionPane.showMessageDialog(null, "V�lj en agent som INTE har en chef roll �ver omr�de eller kontor, och som existerar");
                        return false;
                    } else if (koll3 == null) {
                        JOptionPane.showMessageDialog(null, "Ditt nyinskrivna id tillh�r ingen agent i databasen");
                        return false;
                    } else if (koll2 == null) {
                        getConnection().delete("DELETE FROM OMRADESCHEF WHERE AGENT_ID='" + koll + "';");
                        getConnection().insert("INSERT INTO OMRADESCHEF VALUES(" + nyChef + ",'" + plats + "');");
                        omrade = true;
                        return omrade;
                    }
                }

            } else if (koll == null) {
                return true;
            }
        } catch (InfException e) {
            System.out.print("SQL");
        }
        return true;
    }
    /**
     * 
     * @param id p� agent
     * @return true eller false
     * Vi kollar om agenten vi f�rs�ker ta bort �r ansvarig �ver en alien.
     * �r den det s� m�ste vi l�gga in en ny agent som ska ta �ver det ansvaret
     * Det nya id:et som anv�ndaren skriver in m�ste vara p� en agent som existrerar
     */

    private boolean ansvarig(int id) {

        boolean alien = false;
        try {
            String koll = getConnection().fetchSingle("Select ANSVARIG_AGENT from ALIEN WHERE ANSVARIG_AGENT =" + id + ";");

            if (koll != null) {
                String plats = getConnection().fetchSingle("Select ALIEN_ID from ALIEN WHERE ANSVARIG_AGENT =" + koll + ";");
                String nyChef = JOptionPane.showInputDialog("Du f�rs�ker ta bort en agent som �r ansvarig �ver en alien, skriv in ett ID p� en ny agent att ta �ver uppgiten.");
                if (Validering.isID(nyChef)) {
                    int nyAnsvar = Integer.parseInt(nyChef);
                    String koll3 = getConnection().fetchSingle("Select AGENT_ID from AGENT WHERE AGENT_ID =" + nyChef + ";");

                    if (koll3 == null) {
                        JOptionPane.showMessageDialog(null, "Ditt nyinskrivna id tillh�r ingen agent i databasen");
                        return false;
                    } else if (koll3 != null) {
                        getConnection().update("UPDATE ALIEN SET ANSVARIG_AGENT=" + nyAnsvar + "WHERE ANSVARIG_AGENT = " + objekt + ";");
                        alien = true;
                        return alien;
                    }
                }

            } else if (koll == null) {
                return true;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
//                catch (InfException e) {
//			System.out.print("SQL");
//		}
        return true;
    }
/**
 * Kollar vad vi f�tt in f�r information fr�n v�r combobox
 * Switch sats som ska kolla vilken typ vi ska ta bort, Alien,Agent,Utrustning
 * Beroende p� vad som �r valt sparar vi en str�ng f�r tabellen i v�rt f�lt tabell
 * och g�r stringen som anv�ndaren skrivit f�r ID till en int och sparar i v�rt f�lt
 */
    private void kontroll() {
        if (Validering.isID(jTxtID.getText())) {
            String item = jComboTyp.getSelectedItem().toString();
            String hittad = "";
            try {
                switch (item) {
                    case "Alien":
                        hittad = getConnection().fetchSingle("Select NAMN from ALIEN where ALIEN_ID =" + Integer.parseInt(jTxtID.getText()) + ";");
                        if (hittad != null) {
                            jLblHittad.setText(hittad);
                            jLblHittad.setVisible(true);
                            jBtnRadera.setVisible(true);
                            objekt = Integer.parseInt(jTxtID.getText());
                            tabell = "ALIEN";
                            break;
                        } else {
                            JOptionPane.showMessageDialog(null, "Kan ej hitta angivet ID i databasen");
                            break;
                        }

                    case "Agent":
                        hittad = getConnection().fetchSingle("Select NAMN from AGENT where AGENT_ID =" + Integer.parseInt(jTxtID.getText()) + ";");
                        if (hittad != null) {
                            jLblHittad.setText(hittad);
                            jLblHittad.setVisible(true);
                            jBtnRadera.setVisible(true);
                            objekt = Integer.parseInt(jTxtID.getText());
                            tabell = "AGENT";
                            break;
                        } else {
                            JOptionPane.showMessageDialog(null, "Kan ej hitta angivet ID i databasen");
                            break;
                        }
                    case "Utrustning":
                        hittad = getConnection().fetchSingle("Select BENAMNING from UTRUSTNING where UTRUSTNINGS_ID =" + Integer.parseInt(jTxtID.getText()) + ";");
                        if (hittad != null) {
                            jLblHittad.setText(hittad);
                            jLblHittad.setVisible(true);
                            jBtnRadera.setVisible(true);
                            objekt = Integer.parseInt(jTxtID.getText());
                            tabell = "UTRUSTNING";
                            break;
                        } else {
                            JOptionPane.showMessageDialog(null, "Kan ej hitta angivet ID i databasen");
                            break;
                        }

                    default:
                }
            } catch (InfException e) {
                JOptionPane.showMessageDialog(null, "Kan ej hitta angivet ID i databasen");
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Fyll i ID f�ltet med ENDAST siffror");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboTyp = new javax.swing.JComboBox<>();
        jLblID = new javax.swing.JLabel();
        jTxtID = new javax.swing.JTextField();
        jBtnRadera = new javax.swing.JButton();
        jLblHittad = new javax.swing.JLabel();
        jButtonNext = new javax.swing.JButton();
        jLblInfo = new javax.swing.JLabel();
        jBtnExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jComboTyp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboTyp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboTypActionPerformed(evt);
            }
        });

        jLblID.setText("ID:");

        jBtnRadera.setText("Radera");
        jBtnRadera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnRaderaActionPerformed(evt);
            }
        });

        jLblHittad.setText("jLabel1");

        jButtonNext.setText("Vidare");
        jButtonNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNextActionPerformed(evt);
            }
        });

        jLblInfo.setText("Varje g�ng du uppdaterar ID tryck p� vidare f�r att uppdatera till r�tt objekt");

        jBtnExit.setText("St�ng f�nster");
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jComboTyp, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(jLblID, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTxtID, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(124, 124, 124)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonNext, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBtnRadera, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jLblInfo))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jLblHittad, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(197, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jBtnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(103, 103, 103))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboTyp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLblID)
                    .addComponent(jTxtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addComponent(jLblInfo)
                .addGap(19, 19, 19)
                .addComponent(jLblHittad)
                .addGap(18, 18, 18)
                .addComponent(jButtonNext)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(jBtnRadera)
                .addGap(3, 3, 3)
                .addComponent(jBtnExit))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboTypActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboTypActionPerformed

    }//GEN-LAST:event_jComboTypActionPerformed

    //K�r kontroll metoden
    private void jButtonNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNextActionPerformed
        kontroll();


    }//GEN-LAST:event_jButtonNextActionPerformed
/**
 * 
 * @param evt , klicken p� knappen radera
 * Kollar vilken str�ng som �r sparad i tabell f�ltet och tar bort fr�n respektive tabell i databasen
 */
    private void jBtnRaderaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnRaderaActionPerformed
        try {
            
            if (tabell.equals("ALIEN")) {
                getConnection().delete("DELETE FROM ALIEN WHERE ALIEN_ID =" + objekt + ";");
                getConnection().delete("DELETE FROM BOGLODITE WHERE ALIEN_ID =" + objekt + ";");
                getConnection().delete("DELETE FROM SQUID WHERE ALIEN_ID =" + objekt + ";");
                getConnection().delete("DELETE FROM WORM WHERE ALIEN_ID =" + objekt + ";");
                JOptionPane.showMessageDialog(null, "Alienen �r borttagen");
                dispose();
            } else if (tabell.equals("AGENT")) {
                boolean test = omradesChef(objekt);
                boolean test2 = kontorsChef(objekt);
                boolean test3 = ansvarig(objekt);
                if (test && test2 && test3) {

                    getConnection().delete("DELETE FROM INNEHAR_FORDON WHERE AGENT_ID = " + objekt + ";");
                    getConnection().delete("DELETE FROM INNEHAR_UTRUSTNING WHERE AGENT_ID = " + objekt + ";");
                    getConnection().delete("DELETE FROM FALTAGENT WHERE AGENT_ID = " + objekt + ";");
                    getConnection().delete("DELETE FROM AGENT WHERE AGENT_ID = " + objekt + ";");

                    JOptionPane.showMessageDialog(null, "Agenten �r borttagen");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Fortfarande invalid id, anv�nd s�k funktionen");
                }

            } else if (tabell.equals("UTRUSTNING")) {
                getConnection().delete("DELETE FROM INNEHAR_UTRUSTNING WHERE UTRUSTNINGS_ID =" + objekt + ";");
                getConnection().delete("DELETE FROM VAPEN WHERE UTRUSTNINGS_ID =" + objekt + ";");
                getConnection().delete("DELETE FROM TEKNIK WHERE UTRUSTNINGS_ID =" + objekt + ";");
                getConnection().delete("DELETE FROM KOMMUNIKATION WHERE UTRUSTNINGS_ID =" + objekt + ";");
                getConnection().delete("DELETE FROM UTRUSTNING WHERE UTRUSTNINGS_ID =" + objekt + ";");
                JOptionPane.showMessageDialog(null, "Utrustningen �r borttagen");
                dispose();
            }
        } catch (InfException e) {
            System.out.println(e);
        }

    }//GEN-LAST:event_jBtnRaderaActionPerformed
//St�nger f�nstret
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
            java.util.logging.Logger.getLogger(TaBort.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TaBort.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TaBort.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TaBort.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TaBort().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnExit;
    private javax.swing.JButton jBtnRadera;
    private javax.swing.JButton jButtonNext;
    private javax.swing.JComboBox<String> jComboTyp;
    private javax.swing.JLabel jLblHittad;
    private javax.swing.JLabel jLblID;
    private javax.swing.JLabel jLblInfo;
    private javax.swing.JTextField jTxtID;
    // End of variables declaration//GEN-END:variables
}
