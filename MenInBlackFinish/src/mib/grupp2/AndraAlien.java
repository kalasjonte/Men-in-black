/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mib.grupp2;

import Klasser.Agent;
import Klasser.Alien;
import Klasser.Boglodite;
import Klasser.Squid;
import Klasser.Validering;
import Klasser.Worm;
import Klasser.Plats;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;

import oru.inf.InfException;
import oru.inf.InfDBHelper;

/**
 *
 * @author vilmaottner
 */
public class AndraAlien extends javax.swing.JFrame
{

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[])
	{
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
			java.util.logging.Logger.getLogger(AndraAlien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(AndraAlien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(AndraAlien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(AndraAlien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable()
		{
			Alien alien;

			public void run()
			{
				new AndraAlien().setVisible(true);
			}
		});
	}
	Alien alien;

	/**
	 * Skapar ett nytt fönster på ändra agent och initiserar komponenter
         * fyller combobox
	 *
	 * @param alien
	 */
	public AndraAlien()
	{
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		initComponents();
		String Arr[] = {"Boglodit", "Worm", "Squid", "Ingen Ras"};
		this.cBRasAlternativ.setModel(new DefaultComboBoxModel<>(Arr));
		jBtnAndra.setEnabled(false);
		lblAndradNotif.setVisible(false);
		hideErrorLabels();
		System.out.println("mib.grupp2.AndraAlien.<init>()"+ jTxtTelNum.getText());
	}
        
//Sätter alla textboxar till tomt och gömmer felmedelanden
	private void TextBoxClear()
	{
		hideErrorLabels();
		txtAnsvarigAgent.setText("");
		jTxtNamn.setText("");
		jTxtPlats.setText("");
		jTxtTelNum.setText("");
		txtIntegerRaser.setText("");
	}

        //Fyller textboxarna med information vi hämtat med det inskrivna ID:et
        //från användaren
	private void TextBoxFill()
	{
		TextBoxClear();
		System.out.println("mib.grupp2.AndraAlien.TextBoxFill()"+ jTxtTelNum.getText());;
		if (alien != null) {

			txtAnsvarigAgent.setText("" + alien.getAgent());
			jTxtNamn.setText(alien.getNamn());
			jTxtPlats.setText("" + alien.getPlats());
			jTxtTelNum.setText(alien.getTelefon());
			System.out.println("mib.fonster.AndraAlien.TextBoxFill(): "+ alien.getTelefon());
			cBRasAlternativ.setSelectedIndex(alien.whichRace());
			try {
				if (alien.whichRace() == 0) {
					Boglodite boglo = Boglodite.getAlien(alien.getId());
					txtIntegerRaser.setText("" + boglo.getAntalBoogies());
				} else if (alien.whichRace() == 2) {
					Squid squid = Squid.getAlien(alien.getId());
					txtIntegerRaser.setText("" + squid.getAntalArmar());
				}
			} catch (InfException e) {
				System.out.println(e);
			}
			jBtnAndra.setEnabled(true);
			lblAndradNotif.setVisible(false);
		}
	}

        //Tar bort rasen från databasen beroende på vilken ras det var
	private void removeRace()
	{
		try {
			switch (alien.whichRace()) {
				case 0:
					Boglodite boglo;
					boglo = new Boglodite(alien, 0);
					boglo.deleteRaceEntryDB();
					break;
				case 1:
					Worm worm;
					worm = new Worm(alien);
					worm.deleteRaceEntryDB();
					break;
				case 2:
					Squid squid;
					squid = new Squid(alien, 0);
					squid.deleteRaceEntryDB();
					break;
				default:
					break;
			}
		} catch (InfException e) {
			System.out.println(e);
		}
	}

        //Insertar en ny ras i databasen
	private void raceFromComboBox()
	{
		try {
			switch (cBRasAlternativ.getSelectedIndex()) {
				case 0:
					if (Validering.isDigit(txtIntegerRaser.getText())) {
						Boglodite boglo;
						boglo = new Boglodite(alien, Integer.parseInt(txtIntegerRaser.getText()));
						boglo.insertRas();
						break;
					}
				case 1:
					Worm worm;
					worm = new Worm(alien);
					worm.insertRas();
					break;
				case 2:
					if (Validering.isDigit(txtIntegerRaser.getText())) {
						Squid squid;
						squid = new Squid(alien, Integer.parseInt(txtIntegerRaser.getText()));
						squid.insertRas();
						break;
					}
				default:
					break;
			}
		} catch (InfException e) {
			System.out.println(e);
		}
	}

        //Gömmer felmedelanden
	private void hideErrorLabels()
	{
		lblErrorNamn.setVisible(false);
		lblErrorPlats.setVisible(false);
		lblErrorTelefon.setVisible(false);
		lblErrorRas.setVisible(false);
		lblErrorRaceTrait.setVisible(false);
		lblErrorAnsvarigAgent.setVisible(false);

	}
        //Validering på inmatad data
	private boolean validation()
	{
		hideErrorLabels();
		boolean valid = true;
		if (Validering.isID(txtAnsvarigAgent.getText())) {
			if (!Agent.idExists(Integer.parseInt(txtAnsvarigAgent.getText()))) {
				lblErrorAnsvarigAgent.setText("Inte hittad");
				lblErrorAnsvarigAgent.setForeground(Color.red);
				lblErrorAnsvarigAgent.setVisible(true);
				valid = false;
			}

		} else {
			lblErrorAnsvarigAgent.setText("Ogilitigt ID");
			lblErrorAnsvarigAgent.setForeground(Color.red);
			lblErrorAnsvarigAgent.setVisible(true);
			valid = false;
		}
		if (Validering.isPlats(jTxtPlats.getText())) {
			if (!Plats.idExists(Integer.parseInt(jTxtPlats.getText()))) {
				lblErrorPlats.setText("Inte hittad");
				lblErrorPlats.setForeground(Color.red);
				lblErrorPlats.setVisible(true);
				valid = false;
			}
		} else {
			lblErrorPlats.setText("Ogiltigt ID");
			lblErrorPlats.setForeground(Color.red);
			lblErrorPlats.setVisible(true);
			valid = false;
		}
		System.out.println("mib.grupp2.AndraAlien.validation()"+ jTxtTelNum.getText());
		if (!Validering.validName(jTxtNamn.getText())) {
			lblErrorNamn.setText("Ogiltigt namn");
			lblErrorNamn.setForeground(Color.red);
			lblErrorNamn.setVisible(true);
			valid = false;
		}
		if (!Validering.checkTelefon(jTxtTelNum.getText())) {
			lblErrorTelefon.setText("Ogiltigt Telefonnummer");
			lblErrorTelefon.setForeground(Color.red);
			lblErrorTelefon.setVisible(true);
			valid = false;
		}
		if (cBRasAlternativ.getSelectedIndex() != 3) {
			if (!Validering.notNegative(txtIntegerRaser.getText())) {
				lblErrorRaceTrait.setText("Får inte vara negativt");
				lblErrorRaceTrait.setForeground(Color.red);
				lblErrorRaceTrait.setVisible(true);
				valid = false;
			}
		}

		if (cBRasAlternativ.getSelectedIndex() == 3) {
			lblErrorRas.setText("Måste ha ras");
			lblErrorRas.setForeground(Color.red);
			lblErrorRas.setVisible(true);
			valid = false;
		}
		return valid;
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

        jLblAndraAli = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLblAnvAge = new javax.swing.JLabel();
        txtAnsvarigAgent = new javax.swing.JTextField();
        jLblNamn = new javax.swing.JLabel();
        jTxtNamn = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLblRas = new javax.swing.JLabel();
        jLblPlats = new javax.swing.JLabel();
        jTxtPlats = new javax.swing.JTextField();
        jLblTelNum = new javax.swing.JLabel();
        jTxtTelNum = new javax.swing.JTextField();
        jBtnAndra = new javax.swing.JButton();
        cBRasAlternativ = new javax.swing.JComboBox<>();
        txtIntegerRaser = new javax.swing.JTextField();
        rasEgenskap = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        lblAndradNotif = new javax.swing.JLabel();
        lblErrorAnsvarigAgent = new javax.swing.JLabel();
        lblErrorNamn = new javax.swing.JLabel();
        lblErrorRas = new javax.swing.JLabel();
        lblErrorRaceTrait = new javax.swing.JLabel();
        lblErrorPlats = new javax.swing.JLabel();
        lblErrorTelefon = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLblAndraAli.setText("Ändra Alien");

        jLblAnvAge.setText("Ansvarig agent:");

        txtAnsvarigAgent.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                txtAnsvarigAgentActionPerformed(evt);
            }
        });

        jLblNamn.setText("Namn:");

        jTxtNamn.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jTxtNamnActionPerformed(evt);
            }
        });

        jLblRas.setText("Ras:");

        jLblPlats.setText("Plats:");

        jTxtPlats.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jTxtPlatsActionPerformed(evt);
            }
        });

        jLblTelNum.setText("Telefonnummer:");

        jTxtTelNum.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jTxtTelNumActionPerformed(evt);
            }
        });

        jBtnAndra.setText("Ändra");
        jBtnAndra.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jBtnAndraActionPerformed(evt);
            }
        });

        cBRasAlternativ.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cBRasAlternativ.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                cBRasAlternativActionPerformed(evt);
            }
        });

        rasEgenskap.setText("jLabel2");

        jLabel2.setText("Välj Alien ID");

        txtID.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                txtIDActionPerformed(evt);
            }
        });

        jButton1.setText("Hämta");
        jButton1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton1ActionPerformed(evt);
            }
        });

        lblAndradNotif.setForeground(new java.awt.Color(0, 255, 0));
        lblAndradNotif.setText("Ändrad!");

        lblErrorAnsvarigAgent.setText("jLabel3");

        lblErrorNamn.setText("jLabel3");

        lblErrorRas.setText("jLabel3");

        lblErrorRaceTrait.setText("jLabel4");

        lblErrorPlats.setText("jLabel6");

        lblErrorTelefon.setText("jLabel7");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jBtnAndra)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(lblAndradNotif)
                                        .addGap(11, 11, 11)))
                                .addGap(14, 14, 14))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jLblNamn)
                                                        .addGap(64, 64, 64)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jTxtNamn, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(cBRasAlternativ, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                    .addComponent(jLblRas))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(lblErrorNamn)
                                                    .addComponent(lblErrorRas)))
                                            .addComponent(rasEgenskap)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(95, 95, 95)
                                                .addComponent(txtIntegerRaser, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lblErrorRaceTrait)))
                                        .addGap(8, 8, 8)
                                        .addComponent(jLabel5))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLblAnvAge)
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtAnsvarigAgent, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(txtID)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jButton1)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblErrorAnsvarigAgent))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(95, 95, 95)
                                        .addComponent(jTxtPlats, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblErrorPlats))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLblTelNum)
                                            .addComponent(jLblPlats))
                                        .addGap(18, 18, 18)
                                        .addComponent(jTxtTelNum, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblErrorTelefon)))
                                .addContainerGap(165, Short.MAX_VALUE))))))
            .addGroup(layout.createSequentialGroup()
                .addGap(183, 183, 183)
                .addComponent(jLblAndraAli, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLblAndraAli)
                .addGap(33, 33, 33)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLblAnvAge)
                    .addComponent(txtAnsvarigAgent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblErrorAnsvarigAgent))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTxtNamn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLblNamn)
                    .addComponent(lblErrorNamn))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLblRas)
                    .addComponent(cBRasAlternativ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblErrorRas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIntegerRaser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rasEgenskap)
                    .addComponent(lblErrorRaceTrait))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLblPlats)
                    .addComponent(jTxtPlats, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblErrorPlats))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLblTelNum)
                    .addComponent(jTxtTelNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblErrorTelefon))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(lblAndradNotif)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBtnAndra)
                .addGap(8, 8, 8))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtAnsvarigAgentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAnsvarigAgentActionPerformed
		// TODO add your handling code here:
    }//GEN-LAST:event_txtAnsvarigAgentActionPerformed

    private void jTxtNamnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtNamnActionPerformed
		// TODO add your handling code here:
    }//GEN-LAST:event_jTxtNamnActionPerformed

    private void jTxtPlatsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtPlatsActionPerformed
		// TODO add your handling code here:
    }//GEN-LAST:event_jTxtPlatsActionPerformed

    private void jTxtTelNumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtTelNumActionPerformed
		// TODO add your handling code here:
    }//GEN-LAST:event_jTxtTelNumActionPerformed
/**
 * 
 * @param evt (klick)
 * Ändrar alien objektet till den nya uppdaterade datan och för in det i databasen
 */
    private void jBtnAndraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnAndraActionPerformed
		if (validation()) {
			removeRace();
			raceFromComboBox();
			alien.setAgent(Integer.parseInt(txtAnsvarigAgent.getText()));
			alien.setNamn(jTxtNamn.getText());
			alien.setPlats(Integer.parseInt(jTxtPlats.getText()));
			System.out.println("mib.grupp2.AndraAlien.jBtnAndraActionPerformed()"+ jTxtTelNum.getText());
			alien.setTelefon(jTxtTelNum.getText());
			System.out.println("mib.grupp2.AndraAlien.jBtnAndraActionPerformed():" + jTxtTelNum.getText());
			try {
				alien.updateAllSql();
				lblAndradNotif.setVisible(true);
			} catch (InfException e) {
				System.out.println(e);
			}
		}


    }//GEN-LAST:event_jBtnAndraActionPerformed

    //Kollar vilken ras som är vald i comboboxen
    private void cBRasAlternativActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_cBRasAlternativActionPerformed
    {//GEN-HEADEREND:event_cBRasAlternativActionPerformed
		int NuvarandeRas = cBRasAlternativ.getSelectedIndex();
		if (NuvarandeRas == 0) {
			rasEgenskap.setText("Antal boogies:");
			txtIntegerRaser.setEnabled(true);
		} else if (NuvarandeRas == 1) {
			rasEgenskap.setText("");
			txtIntegerRaser.setEnabled(false);
		} else if (NuvarandeRas == 2) {
			rasEgenskap.setText("Antal armar:");
			txtIntegerRaser.setEnabled(true);
		} else if (NuvarandeRas == 3) {
			txtIntegerRaser.setEnabled(false);
			rasEgenskap.setText("");
		}
    }//GEN-LAST:event_cBRasAlternativActionPerformed

    private void txtIDActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_txtIDActionPerformed
    {//GEN-HEADEREND:event_txtIDActionPerformed
		// TODO add your handling code here:
    }//GEN-LAST:event_txtIDActionPerformed

    //Hämtar en alien beroende på ID, fyller textfälten
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton1ActionPerformed
    {//GEN-HEADEREND:event_jButton1ActionPerformed

		TextBoxClear();
		if (Validering.isID(txtID.getText())) {
			int id = Integer.parseInt(txtID.getText());
			if (Alien.idExists(id)) {
				try {
					alien = Alien.getAlien(id);
				} catch (Exception e) {
					System.out.println(e);
				}
				TextBoxFill();
			} else {
				txtAnsvarigAgent.setText("ID: " + id + " Not found");
				jBtnAndra.setEnabled(false);
			}

		} else {
			txtAnsvarigAgent.setText("Invalid ID input");
			jBtnAndra.setEnabled(false);
		}
		// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cBRasAlternativ;
    private javax.swing.JButton jBtnAndra;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLblAndraAli;
    private javax.swing.JLabel jLblAnvAge;
    private javax.swing.JLabel jLblNamn;
    private javax.swing.JLabel jLblPlats;
    private javax.swing.JLabel jLblRas;
    private javax.swing.JLabel jLblTelNum;
    private javax.swing.JTextField jTxtNamn;
    private javax.swing.JTextField jTxtPlats;
    private javax.swing.JTextField jTxtTelNum;
    private javax.swing.JLabel lblAndradNotif;
    private javax.swing.JLabel lblErrorAnsvarigAgent;
    private javax.swing.JLabel lblErrorNamn;
    private javax.swing.JLabel lblErrorPlats;
    private javax.swing.JLabel lblErrorRaceTrait;
    private javax.swing.JLabel lblErrorRas;
    private javax.swing.JLabel lblErrorTelefon;
    private javax.swing.JLabel rasEgenskap;
    private javax.swing.JTextField txtAnsvarigAgent;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtIntegerRaser;
    // End of variables declaration//GEN-END:variables
}
