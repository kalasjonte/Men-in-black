/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mib.grupp2;

import Klasser.Alien;
import Klasser.Validering;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import oru.inf.InfDB;
import oru.inf.InfException;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author jonat
 */
public class HanteraAliens extends javax.swing.JFrame {

    private ArrayList<HashMap<String, String>> temp;

    public HanteraAliens() {
        initComponents();
        fyll();

        temp = new ArrayList();

        buttonGroup1.add(jRadioPlats);
        buttonGroup1.add(jRadioRas);
        buttonGroup1.add(jRadAID);

    }
    //Fyller listan med alla aliens i dtaabasen
    private void fyll() {
        try {
            this.temp = Alien.getDb().fetchRows("Select * from ALIEN");
            System.out.println(this.temp);
            if (temp != null) {
                DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
                dtm.setRowCount(0);
                int listlagnd = temp.size();
                dtm.setRowCount(listlagnd);

                for (int i = 0; i < temp.size(); i++) {

//                    int currentPlats = Integer.parseInt(Alien.getDb().fetchSingle("Select PLATS from ALIEN"));
//                    int plats = Integer.parseInt(temp.get(i).get("PLATS"));
                    System.out.println(temp.size());
                    String id = temp.get(i).get("ALIEN_ID");
                    String namn = temp.get(i).get("NAMN");
                    String plats = temp.get(i).get("PLATS");
                    String telefon = temp.get(i).get("TELEFON");
                    String ansAge = temp.get(i).get("ANSVARIG_AGENT");
                    String regDat = temp.get(i).get("REGISTRERINGSDATUM");
                    String ras = idRas(Integer.parseInt(id));
                    String egenskap = idEge(Integer.parseInt(id));

                    jTable1.getModel().setValueAt(id, i, 0);
                    jTable1.getModel().setValueAt(namn, i, 1);
                    jTable1.getModel().setValueAt(plats, i, 2);
                    jTable1.getModel().setValueAt(telefon, i, 3);
                    jTable1.getModel().setValueAt(ansAge, i, 4);
                    jTable1.getModel().setValueAt(regDat, i, 5);
                    jTable1.getModel().setValueAt(ras, i, 6);
                    jTable1.getModel().setValueAt(egenskap, i, 7);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jRadioRas = new javax.swing.JRadioButton();
        jRadioPlats = new javax.swing.JRadioButton();
        jTxtStaDat = new javax.swing.JTextField();
        jLblStaDat = new javax.swing.JLabel();
        jLblSluDat = new javax.swing.JLabel();
        jTxtSluDat = new javax.swing.JTextField();
        jLblRegDat = new javax.swing.JLabel();
        jBnSok = new javax.swing.JButton();
        jRadAID = new javax.swing.JRadioButton();
        jbtSokReg = new javax.swing.JButton();
        jBtnExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("S�kord:");

        jTextField1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jTextField1ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {
                {null, null, null, null, null, null, null, null}
            },
            new String []
            {
                "AlienID", "Namn", "Plats", "Tel.Num", "Ans.Age", "Reg.Datum", "Ras", "Egenskap"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel2.setText("S�k p� Alien");

        jRadioRas.setText("Rasnamn:");
        jRadioRas.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jRadioRasActionPerformed(evt);
            }
        });

        jRadioPlats.setText("Platsnamn:");

        jTxtStaDat.setText("T.ex. 2021-01-07");
        jTxtStaDat.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jTxtStaDatActionPerformed(evt);
            }
        });

        jLblStaDat.setText("Startdatum:");

        jLblSluDat.setText("Slutdatum:");

        jTxtSluDat.setText("T.ex. 2021-01-08");

        jLblRegDat.setText("Registreringsdatum:");

        jBnSok.setText("S�k");
        jBnSok.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jBnSokActionPerformed(evt);
            }
        });

        jRadAID.setText("AlienID");
        jRadAID.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jRadAIDActionPerformed(evt);
            }
        });

        jbtSokReg.setText("S�k");
        jbtSokReg.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jbtSokRegActionPerformed(evt);
            }
        });

        jBtnExit.setText("St�ng f�nster");
        jBtnExit.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jBtnExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLblRegDat)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jRadioPlats)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLblStaDat)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(85, 85, 85)
                                                .addComponent(jTxtStaDat, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLblSluDat)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTxtSluDat, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jbtSokReg, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 188, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE)
                        .addGap(122, 122, 122))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jRadAID)
                                .addGap(20, 20, 20)
                                .addComponent(jRadioRas, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(70, 70, 70)
                                .addComponent(jBnSok, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBtnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBnSok))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioRas)
                    .addComponent(jRadioPlats)
                    .addComponent(jRadAID))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLblRegDat)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLblStaDat)
                    .addComponent(jTxtStaDat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLblSluDat)
                    .addComponent(jTxtSluDat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtSokReg))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBtnExit))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioRasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioRasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioRasActionPerformed


    private void jBnSokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBnSokActionPerformed
        try {
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);

            if (jRadAID.isSelected() && Validering.isID(jTextField1.getText())) {
                int id = Integer.parseInt(jTextField1.getText());
                HashMap<String, String> aID = Alien.getDb().fetchRow("select * from ALIEN where ALIEN_ID=" + id);
                String namn = aID.get("NAMN");
                String plats = aID.get("PLATS");
                String telefon = aID.get("TELEFON");
                String ansAge = aID.get("ANSVARIG_AGENT");
                String regDat = aID.get("REGISTRERINGSDATUM");
                String ras = idRas();
                String antal = idEge();

                int listlagnd = aID.size();
                dtm.setRowCount(listlagnd);

                int j = 0;
                jTable1.getModel().setValueAt(id, j, 0);
                jTable1.getModel().setValueAt(namn, j, 1);
                jTable1.getModel().setValueAt(plats, j, 2);
                jTable1.getModel().setValueAt(telefon, j, 3);
                jTable1.getModel().setValueAt(ansAge, j, 4);
                jTable1.getModel().setValueAt(regDat, j, 5);
                jTable1.getModel().setValueAt(ras, j, 6);
                jTable1.getModel().setValueAt(antal, j, 7);

            }

            if (jRadioPlats.isSelected() && Validering.validName(jTextField1.getText())) {
                String platstxt = jTextField1.getText();
                this.temp = Alien.getDb().fetchRows("Select * from ALIEN where PLATS IN (Select PLATS_ID from PLATS where BENAMNING = '" + platstxt + "');");
                System.out.println(this.temp);
                if (temp != null) {

                    int listlagnd = temp.size();
                    dtm.setRowCount(listlagnd);

                    for (int i = 0; i < temp.size(); i++) {

//                    int currentPlats = Integer.parseInt(Alien.getDb().fetchSingle("Select PLATS from ALIEN"));
//                    int plats = Integer.parseInt(temp.get(i).get("PLATS"));
                        System.out.println(temp.size());
                        String id = temp.get(i).get("ALIEN_ID");
                        String namn = temp.get(i).get("NAMN");
                        String plats = temp.get(i).get("PLATS");
                        String telefon = temp.get(i).get("TELEFON");
                        String ansAge = temp.get(i).get("ANSVARIG_AGENT");
                        String regDat = temp.get(i).get("REGISTRERINGSDATUM");
                        String ras = idRas(Integer.parseInt(id));
                        String egenskap = idEge(Integer.parseInt(id));

                        jTable1.getModel().setValueAt(id, i, 0);
                        jTable1.getModel().setValueAt(namn, i, 1);
                        jTable1.getModel().setValueAt(plats, i, 2);
                        jTable1.getModel().setValueAt(telefon, i, 3);
                        jTable1.getModel().setValueAt(ansAge, i, 4);
                        jTable1.getModel().setValueAt(regDat, i, 5);
                        jTable1.getModel().setValueAt(ras, i, 6);
                        jTable1.getModel().setValueAt(egenskap, i, 7);
                    }
                } else if (temp == null) {
                    JOptionPane.showMessageDialog(null, "Fel p� s�kningen, kunde ej hitta angiven plats, eller s� finns det inga aliens d�r");
                }
            }

            if (jRadioRas.isSelected() && Validering.validRas(jTextField1.getText())) {
                String platstxt = jTextField1.getText();
                this.temp = Alien.getDb().fetchRows("Select * from ALIEN where ALIEN_ID IN (Select ALIEN_ID from " + platstxt + ");");
                System.out.println(this.temp);
                if (temp != null) {

                    int listlagnd = temp.size();
                    dtm.setRowCount(listlagnd);

                    for (int i = 0; i < temp.size(); i++) {

                        System.out.println(temp.size());
                        String id = temp.get(i).get("ALIEN_ID");
                        String namn = temp.get(i).get("NAMN");
                        String plats = temp.get(i).get("PLATS");
                        String telefon = temp.get(i).get("TELEFON");
                        String ansAge = temp.get(i).get("ANSVARIG_AGENT");
                        String regDat = temp.get(i).get("REGISTRERINGSDATUM");
                        String ras = idRas(Integer.parseInt(id));
                        String egenskap = idEge(Integer.parseInt(id));

                        jTable1.getModel().setValueAt(id, i, 0);
                        jTable1.getModel().setValueAt(namn, i, 1);
                        jTable1.getModel().setValueAt(plats, i, 2);
                        jTable1.getModel().setValueAt(telefon, i, 3);
                        jTable1.getModel().setValueAt(ansAge, i, 4);
                        jTable1.getModel().setValueAt(regDat, i, 5);
                        jTable1.getModel().setValueAt(ras, i, 6);
                        jTable1.getModel().setValueAt(egenskap, i, 7);
                    }
                } else if (temp == null) {
                    JOptionPane.showMessageDialog(null, "Fel p� s�kningen, kunde ej hitta angiven plats, eller s� finns det inga aliens d�r");
                }
            }

        } catch (InfException undantag) {
            System.out.print(undantag);
        }


    }//GEN-LAST:event_jBnSokActionPerformed

//    public String vilkenRas(int id)
//    {
//        HashMap<String,string> temp;
//        if(teSelect * from ALIEN where PLATS IN (Select PLATS_ID from PLATS where BENAMNING = '")
//    }
    public String idEge() {

        String boogies = null;
        String armar = null;
        String ret = null;
        int id = Integer.parseInt(jTextField1.getText());

        try {
            boogies = Alien.getDb().fetchSingle("Select ANTAL_BOOGIES from BOGLODITE WHERE ALIEN_ID=" + id + ";");
            if (boogies != null) {
                ret = "Boogies: " + boogies;
                return ret;
            }
        } catch (InfException undantag) {
            System.out.print(undantag);
        }

        try {
            armar = Alien.getDb().fetchSingle("Select ANTAL_ARMAR from SQUID WHERE ALIEN_ID=" + id + ";");
            if (armar != null) {
                ret = "Armar: " + armar;
                return ret;
            }
        } catch (InfException undantag) {
            System.out.print(undantag);
        }

        return null;
    }

    public String idEge(int id) {

        String boogies = null;
        String armar = null;
        String ret = null;

        try {
            boogies = Alien.getDb().fetchSingle("Select ANTAL_BOOGIES from BOGLODITE WHERE ALIEN_ID=" + id + ";");
            if (boogies != null) {
                ret = "Boogies: " + boogies;
                return ret;
            }
        } catch (InfException undantag) {
            System.out.print(undantag);
        }

        try {
            armar = Alien.getDb().fetchSingle("Select ANTAL_ARMAR from SQUID WHERE ALIEN_ID=" + id + ";");
            if (armar != null) {
                ret = "Armar: " + armar;
                return ret;
            }
        } catch (InfException undantag) {
            System.out.print(undantag);
        }

        return null;
    }

    public String idRas() {

        String boogies = null;
        String armar = null;
        String ret = null;
        boolean check = false;
        int id = Integer.parseInt(jTextField1.getText());

        try {
            boogies = Alien.getDb().fetchSingle("Select ANTAL_BOOGIES from BOGLODITE WHERE ALIEN_ID=" + id + ";");
            if (boogies != null) {
                return "Boglodite";
            }
        } catch (InfException e) {
            System.out.println("hej");
        }

        try {
            armar = Alien.getDb().fetchSingle("Select ANTAL_ARMAR from SQUID WHERE ALIEN_ID=" + id + ";");
            if (armar != null) {
                return "Squid";
            }
        } catch (InfException e) {
            System.out.println("hej");
        }

        return "Worm";
    }

    public String idRas(int id) {

        String boogies = null;
        String armar = null;
        String ret = null;
        boolean check = false;

        try {
            boogies = Alien.getDb().fetchSingle("Select ANTAL_BOOGIES from BOGLODITE WHERE ALIEN_ID=" + id + ";");
            if (boogies != null) {
                return "Boglodite";
            }
        } catch (InfException e) {
            System.out.println("hej");
        }

        try {
            armar = Alien.getDb().fetchSingle("Select ANTAL_ARMAR from SQUID WHERE ALIEN_ID=" + id + ";");
            if (armar != null) {
                return "Squid";
            }
        } catch (InfException e) {
            System.out.println("hej");
        }

        return "Worm";
    }


    private void jRadAIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadAIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadAIDActionPerformed

    private void jbtSokRegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtSokRegActionPerformed
        try {
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);

            String start = jTxtStaDat.getText();
            String slut = jTxtSluDat.getText();

            temp = Alien.getDb().fetchRows("Select * from ALIEN where REGISTRERINGSDATUM between '" + start + "' and '" + slut + "';");

            System.out.println(temp);

            int listlagnd = temp.size();
            dtm.setRowCount(listlagnd);

            for (int i = 0; i < temp.size(); i++) {
                System.out.println(i);
                String id = temp.get(i).get("ALIEN_ID");
                String namn = temp.get(i).get("NAMN");
                String plats = temp.get(i).get("PLATS");
                String telefon = temp.get(i).get("TELEFON");
                String ansAge = temp.get(i).get("ANSVARIG_AGENT");
                String regDat = temp.get(i).get("REGISTRERINGSDATUM");
                String ras = idRas(Integer.parseInt(id));
                String egenskap = idEge(Integer.parseInt(id));

                jTable1.getModel().setValueAt(id, i, 0);
                jTable1.getModel().setValueAt(namn, i, 1);
                jTable1.getModel().setValueAt(plats, i, 2);
                jTable1.getModel().setValueAt(telefon, i, 3);
                jTable1.getModel().setValueAt(ansAge, i, 4);
                jTable1.getModel().setValueAt(regDat, i, 5);
                jTable1.getModel().setValueAt(ras, i, 6);
                jTable1.getModel().setValueAt(egenskap, i, 7);

            }
        } catch (InfException undantag) {
            System.out.print(undantag);
        }

    }//GEN-LAST:event_jbtSokRegActionPerformed

    private void jTxtStaDatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtStaDatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtStaDatActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        try {
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);

            if (jRadAID.isSelected()) {
                int id = Integer.parseInt(jTextField1.getText());
                HashMap<String, String> aID = Alien.getDb().fetchRow("select * from ALIEN where ALIEN_ID=" + id);
                String namn = aID.get("NAMN");
                String plats = aID.get("PLATS");
                String telefon = aID.get("TELEFON");
                String ansAge = aID.get("ANSVARIG_AGENT");
                String regDat = aID.get("REGISTRERINGSDATUM");
                String ras = idRas();
                String antal = idEge();

                int listlagnd = aID.size();
                dtm.setRowCount(listlagnd);

                int j = 0;
                jTable1.getModel().setValueAt(id, j, 0);
                jTable1.getModel().setValueAt(namn, j, 1);
                jTable1.getModel().setValueAt(plats, j, 2);
                jTable1.getModel().setValueAt(telefon, j, 3);
                jTable1.getModel().setValueAt(ansAge, j, 4);
                jTable1.getModel().setValueAt(regDat, j, 5);
                jTable1.getModel().setValueAt(ras, j, 6);
                jTable1.getModel().setValueAt(antal, j, 7);

            }

            if (jRadioPlats.isSelected()) {
                String platstxt = jTextField1.getText();
                this.temp = Alien.getDb().fetchRows("Select * from ALIEN where PLATS IN (Select PLATS_ID from PLATS where BENAMNING = '" + platstxt + "');");
                System.out.println(this.temp);
                if (temp != null) {

                    int listlagnd = temp.size();
                    dtm.setRowCount(listlagnd);

                    for (int i = 0; i < temp.size(); i++) {

//                    int currentPlats = Integer.parseInt(Alien.getDb().fetchSingle("Select PLATS from ALIEN"));
//                    int plats = Integer.parseInt(temp.get(i).get("PLATS"));
                        System.out.println(temp.size());
                        String id = temp.get(i).get("ALIEN_ID");
                        String namn = temp.get(i).get("NAMN");
                        String plats = temp.get(i).get("PLATS");
                        String telefon = temp.get(i).get("TELEFON");
                        String ansAge = temp.get(i).get("ANSVARIG_AGENT");
                        String regDat = temp.get(i).get("REGISTRERINGSDATUM");
                        String ras = idRas(Integer.parseInt(id));
                        String egenskap = idEge(Integer.parseInt(id));

                        jTable1.getModel().setValueAt(id, i, 0);
                        jTable1.getModel().setValueAt(namn, i, 1);
                        jTable1.getModel().setValueAt(plats, i, 2);
                        jTable1.getModel().setValueAt(telefon, i, 3);
                        jTable1.getModel().setValueAt(ansAge, i, 4);
                        jTable1.getModel().setValueAt(regDat, i, 5);
                        jTable1.getModel().setValueAt(ras, i, 6);
                        jTable1.getModel().setValueAt(egenskap, i, 7);
                    }
                } else if (temp == null) {
                    JOptionPane.showMessageDialog(null, "Fel p� s�kningen, kunde ej hitta angiven plats, eller s� finns det inga aliens d�r");
                }
            }

            if (jRadioRas.isSelected()) {
                String platstxt = jTextField1.getText();
                this.temp = Alien.getDb().fetchRows("Select * from ALIEN where ALIEN_ID IN (Select ALIEN_ID from " + platstxt + ");");
                System.out.println(this.temp);
                if (temp != null) {

                    int listlagnd = temp.size();
                    dtm.setRowCount(listlagnd);

                    for (int i = 0; i < temp.size(); i++) {

                        System.out.println(temp.size());
                        String id = temp.get(i).get("ALIEN_ID");
                        String namn = temp.get(i).get("NAMN");
                        String plats = temp.get(i).get("PLATS");
                        String telefon = temp.get(i).get("TELEFON");
                        String ansAge = temp.get(i).get("ANSVARIG_AGENT");
                        String regDat = temp.get(i).get("REGISTRERINGSDATUM");
                        String ras = idRas(Integer.parseInt(id));
                        String egenskap = idEge(Integer.parseInt(id));

                        jTable1.getModel().setValueAt(id, i, 0);
                        jTable1.getModel().setValueAt(namn, i, 1);
                        jTable1.getModel().setValueAt(plats, i, 2);
                        jTable1.getModel().setValueAt(telefon, i, 3);
                        jTable1.getModel().setValueAt(ansAge, i, 4);
                        jTable1.getModel().setValueAt(regDat, i, 5);
                        jTable1.getModel().setValueAt(ras, i, 6);
                        jTable1.getModel().setValueAt(egenskap, i, 7);
                    }
                } else if (temp == null) {
                    JOptionPane.showMessageDialog(null, "Fel p� s�kningen, kunde ej hitta angiven plats, eller s� finns det inga aliens d�r");
                }
            }

        } catch (InfException undantag) {
            System.out.print(undantag);
        }
    }//GEN-LAST:event_jTextField1ActionPerformed

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
            java.util.logging.Logger.getLogger(HanteraAliens.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HanteraAliens.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HanteraAliens.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HanteraAliens.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HanteraAliens().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jBnSok;
    private javax.swing.JButton jBtnExit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLblRegDat;
    private javax.swing.JLabel jLblSluDat;
    private javax.swing.JLabel jLblStaDat;
    private javax.swing.JRadioButton jRadAID;
    private javax.swing.JRadioButton jRadioPlats;
    private javax.swing.JRadioButton jRadioRas;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTxtSluDat;
    private javax.swing.JTextField jTxtStaDat;
    private javax.swing.JButton jbtSokReg;
    // End of variables declaration//GEN-END:variables
}