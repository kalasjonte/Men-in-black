/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mib.grupp2;

import Klasser.Agent;
import Klasser.Alien;
import Klasser.Kontorschef;
import Klasser.Omradeschef;
import Klasser.Validering;
import Klasser.Faltagent;
import Klasser.Omrade;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import oru.inf.InfDB;
import oru.inf.InfDBHelper;
import oru.inf.InfException;

/**
 *
 * @author vilma
 * @version 2020-01-07
 */
public class AndraAgent extends javax.swing.JFrame
{

	Agent agent;
	Agent loggedInAgent;

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[])
	{
		Agent agent;
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
			java.util.logging.Logger.getLogger(AndraAgent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(AndraAgent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(AndraAgent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(AndraAgent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		/* Create and display the form */
//		Agent agent;
		java.awt.EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{

				InfDB db = null;
				try {
					InfDBHelper.getAdvanceParams();

					db = new InfDB("C:/Users/josef/Documents/NetBeansProjects/MenInBlackPrelim/MIBDB.FDB", InfDBHelper.getAdvanceParams());
				} catch (InfException e) {
					System.out.println("com.frames.AndraAgent.run()");
					System.out.println(e);
				}

				Agent.initConnection(db);
				new AndraAgent().setVisible(true);
			}
		});
	}

	/**
	 * Skapar och inisiterar detta fönster samt komponenter
	 */
	public AndraAgent()
	{
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		initComponents();
		enableEverything(false);

		hideErrors();
	}
	//Gör alla textboxar till tomma

	public void TextBoxClear()
	{
		txtNamn.setText("");
		txtLosenord.setText("");
		txtTelNum.setText("");
		txtOmrade.setText("");
		txtAnstallningsdatum.setText("");
		rBAdmin.setSelected(false);
		rBFaltagent.setSelected(false);
		txtKontor.setText("");
		rBKontorschef.setSelected(false);
		txtAnsvarsOmrode.setText("");
		rBOmradesChef.setSelected(false);
	}
	//Fyller textboxen med information på hämtad agent id

	public void TextBoxFill()
	{

		if (agent != null) {
			txtNamn.setText(agent.getNamn());
			txtLosenord.setText(agent.getLosenord());
			txtTelNum.setText(agent.getTelefon());

			txtOmrade.setText("" + agent.getOmrade());
			txtAnstallningsdatum.setText(agent.getAnstallningsdatum());
			enableEverything(true);
			if (agent.getAdministrator().toUpperCase().equals("J")) {
				rBAdmin.setSelected(true);
			} else {
				rBAdmin.setSelected(false);
			}
			try {
				if (agent.isFaltagent()) {
					rBFaltagent.setSelected(true);
				}
				if (agent.isKontorschef()) {
					Kontorschef kAgent;
					kAgent = new Kontorschef(agent);
					txtKontor.setText(kAgent.getKontorsbeteckning());
					rBKontorschef.setSelected(true);
					txtKontor.setEnabled(true);

				}
				if (agent.isOmradesschef()) {
					Omradeschef oAgent;
					oAgent = new Omradeschef(agent);
					txtAnsvarsOmrode.setText("" + oAgent.getAnsvarsOmrade());
					rBOmradesChef.setSelected(true);
				}
			} catch (InfException e) {
				System.out.print("Caught inTextBoxFill()\t");
				System.out.println(e);

			}
		}
		btnAndra.setEnabled(true);
	}
	//Validerar datainmatning

	private boolean validation()
	{
		hideErrors();
		boolean valid = true;
		if (!Validering.checkName(txtNamn.getText())) {
			valid = false;
			lblErrorNamn.setText("Ogiltigt Namn");
			lblErrorNamn.setVisible(true);
		}
		if (!Validering.checkPassword(txtLosenord.getText())) {
			valid = false;
			lblErrorLosenord.setText("Inte tomt eller > 6 bokstäver");
			lblErrorLosenord.setVisible(true);
		}
		if (!Validering.validDate(txtAnstallningsdatum.getText())) {
			valid = false;
			lblErrorAnstallning.setText("[yyyy-mm-dd]!");
			lblErrorAnstallning.setVisible(true);
		}
		if (!Validering.checkDigit(txtOmrade.getText())) {
			valid = false;
			lblErrorOmrade.setText("Inte Siffra");
			lblErrorOmrade.setVisible(true);
		} else if (!Omrade.idExists(Integer.parseInt(txtOmrade.getText()))) {
			valid = false;
			lblErrorOmrade.setVisible(true);
			lblErrorOmrade.setText("Not found");
		}
		if (!Validering.checkTelefon(txtTelNum.getText())) {
			valid = false;
			lblErrorTelefon.setVisible(true);
		}
		if (rBKontorschef.isSelected()) {
			if (!Validering.checkKontorsbeteckning(txtKontor.getText())) {
				valid = false;
				lblErrorKontor.setVisible(true);
				lblErrorKontor.setText("Felaktig inmatning");
			}
		}
		if (rBOmradesChef.isSelected()) {
			if (!Validering.checkDigit(txtAnsvarsOmrode.getText())) {
				valid = false;
				lblErrorAnsvarsOmrade.setText("Inte Siffra");
				lblErrorAnsvarsOmrade.setVisible(true);
			} else if (!Omrade.idExists(Integer.parseInt(txtAnsvarsOmrode.getText()))) {
				valid = false;
				lblErrorAnsvarsOmrade.setVisible(true);
				lblErrorAnsvarsOmrade.setText("Not found");
			}
		}
		return valid;
	}
	//Gömmer felmedelanden

	private void hideErrors()
	{
		lblErrorAnsvarsOmrade.setVisible(false);
		lblErrorAnstallning.setVisible(false);
		lblErrorKontor.setVisible(false);
		lblErrorLosenord.setVisible(false);
		lblErrorNamn.setVisible(false);
		lblErrorOmrade.setVisible(false);
		lblErrorID.setVisible(false);
		lblErrorAndra.setVisible(false);
		lblErrorTelefon.setVisible(false);
	}
	//Visar allt

	private void enableEverything(boolean enable)
	{
		btnAndra.setEnabled(enable);
		txtAnstallningsdatum.setEnabled(enable);
		txtKontor.setEnabled(enable);
		txtLosenord.setEnabled(enable);
		txtNamn.setEnabled(enable);
		txtOmrade.setEnabled(enable);
		txtTelNum.setEnabled(enable);
		txtAnsvarsOmrode.setEnabled(enable);
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
        lblAndraAgentTitel = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        btnGetAgent = new javax.swing.JButton();
        lblErrorID = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblErrorAndra = new javax.swing.JLabel();
        txtOmrade = new javax.swing.JTextField();
        txtAnsvarsOmrode = new javax.swing.JTextField();
        lblErrorKontor = new javax.swing.JLabel();
        txtNamn = new javax.swing.JTextField();
        lblLosenord = new javax.swing.JLabel();
        lblErrorAnstallning = new javax.swing.JLabel();
        txtLosenord = new javax.swing.JTextField();
        lblAnstDat = new javax.swing.JLabel();
        lblKontorsbeteckning = new javax.swing.JLabel();
        lblErrorAnsvarsOmrade = new javax.swing.JLabel();
        lblTelefon = new javax.swing.JLabel();
        rBKontorschef = new javax.swing.JRadioButton();
        lblErrorOmrade = new javax.swing.JLabel();
        rBOmradesChef = new javax.swing.JRadioButton();
        rBFaltagent = new javax.swing.JRadioButton();
        lblErrorTelefon = new javax.swing.JLabel();
        txtAnstallningsdatum = new javax.swing.JTextField();
        btnAndra = new javax.swing.JButton();
        lblErrorNamn = new javax.swing.JLabel();
        jLblNamn = new javax.swing.JLabel();
        lblAnsvarsOmrade = new javax.swing.JLabel();
        txtKontor = new javax.swing.JTextField();
        lblErrorLosenord = new javax.swing.JLabel();
        txtTelNum = new javax.swing.JTextField();
        lblOmrade = new javax.swing.JLabel();
        rBAdmin = new javax.swing.JRadioButton();
        jBtnExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblAndraAgentTitel.setText("Ändra Agent");

        btnGetAgent.setText("Hämta Agent");
        btnGetAgent.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnGetAgentActionPerformed(evt);
            }
        });

        lblErrorID.setForeground(new java.awt.Color(255, 0, 0));
        lblErrorID.setText("Inte hittad");

        lblErrorAndra.setText("[Success or Failure]");

        lblErrorKontor.setForeground(new java.awt.Color(255, 0, 0));
        lblErrorKontor.setText("Fel inmatad data");

        lblLosenord.setText("Lösenord:");

        lblErrorAnstallning.setForeground(new java.awt.Color(255, 0, 0));
        lblErrorAnstallning.setText("Fel inmatad data");

        lblAnstDat.setText("Anställningsdatum:");

        lblKontorsbeteckning.setText("Kontor:");

        lblErrorAnsvarsOmrade.setForeground(new java.awt.Color(255, 0, 0));
        lblErrorAnsvarsOmrade.setText("Fel inmatad data");

        lblTelefon.setText("Telefonnummer:");

        rBKontorschef.setText("Kontorschef");
        rBKontorschef.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                rBKontorschefActionPerformed(evt);
            }
        });

        lblErrorOmrade.setForeground(new java.awt.Color(255, 0, 0));
        lblErrorOmrade.setText("Fel inmatad data");

        rBOmradesChef.setText("Områdeschef");

        rBFaltagent.setText("Fältagent");
        rBFaltagent.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                rBFaltagentActionPerformed(evt);
            }
        });

        lblErrorTelefon.setForeground(new java.awt.Color(255, 0, 0));
        lblErrorTelefon.setText("Fel inmatad data");

        btnAndra.setText("Ändra");
        btnAndra.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnAndraActionPerformed(evt);
            }
        });

        lblErrorNamn.setForeground(new java.awt.Color(255, 0, 0));
        lblErrorNamn.setText("Fel inmatad data");

        jLblNamn.setText("Namn:");

        lblAnsvarsOmrade.setText("Ansvarsområde:");

        lblErrorLosenord.setForeground(new java.awt.Color(255, 0, 0));
        lblErrorLosenord.setText("Fel inmatad data");

        lblOmrade.setText("Områdesplacering:");

        rBAdmin.setText("Administratör");
        rBAdmin.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                rBAdminActionPerformed(evt);
            }
        });

        jBtnExit.setText("Stäng fönster");
        jBtnExit.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jBtnExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblAnstDat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLblNamn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblLosenord, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblOmrade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblTelefon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblKontorsbeteckning, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblAnsvarsOmrade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jBtnExit))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnAndra, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblErrorAndra))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtAnsvarsOmrode)
                                    .addComponent(txtOmrade)
                                    .addComponent(txtAnstallningsdatum)
                                    .addComponent(txtTelNum)
                                    .addComponent(txtLosenord)
                                    .addComponent(txtNamn)
                                    .addComponent(txtKontor, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblErrorNamn)
                                    .addComponent(lblErrorLosenord)
                                    .addComponent(lblErrorAnstallning)
                                    .addComponent(lblErrorOmrade)
                                    .addComponent(lblErrorTelefon)
                                    .addComponent(lblErrorKontor)
                                    .addComponent(lblErrorAnsvarsOmrade)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rBFaltagent, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rBKontorschef)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rBOmradesChef)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rBAdmin)))
                .addGap(19, 19, 19))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rBFaltagent)
                    .addComponent(rBKontorschef)
                    .addComponent(rBOmradesChef)
                    .addComponent(rBAdmin))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLblNamn)
                    .addComponent(txtNamn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblErrorNamn))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLosenord)
                    .addComponent(txtLosenord, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblErrorLosenord))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAnstDat)
                    .addComponent(txtAnstallningsdatum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblErrorAnstallning))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblErrorOmrade)
                    .addComponent(txtOmrade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblOmrade))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTelefon)
                    .addComponent(txtTelNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblErrorTelefon))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtKontor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblKontorsbeteckning)
                    .addComponent(lblErrorKontor))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAnsvarsOmrode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAnsvarsOmrade)
                    .addComponent(lblErrorAnsvarsOmrade))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAndra)
                    .addComponent(lblErrorAndra)
                    .addComponent(jBtnExit))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(143, 143, 143)
                                .addComponent(lblAndraAgentTitel))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(78, 78, 78)
                                .addComponent(btnGetAgent)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblErrorID)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAndraAgentTitel)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGetAgent)
                    .addComponent(lblErrorID))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rBFaltagentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rBFaltagentActionPerformed
		// TODO add your handling code here:
    }//GEN-LAST:event_rBFaltagentActionPerformed
	//Uppdaterar agent objektet med den nya informationen och uppdaterar databasen
	//Uppdaterar även tabeller som är relaterade
    private void btnAndraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAndraActionPerformed
		if (validation()) {
			agent.setNamn(txtNamn.getText());
			agent.setTelefon(txtTelNum.getText());
			agent.setOmrade(Integer.parseInt(txtOmrade.getText()));
			agent.setLosenord(txtLosenord.getText());
			if (rBAdmin.isSelected()) {
				agent.setAdministrator("J");
			} else {
				agent.setAdministrator("N");
			}
			agent.setAnstallningsdatum(txtAnstallningsdatum.getText());

			try {
				agent.updateAllColumnsSql();
			} catch (InfException ex) {
				lblErrorAndra.setForeground(Color.RED);
				lblErrorAndra.setText("Fail");
				lblErrorAndra.setVisible(true);
				Logger.getLogger(AndraAgent.class.getName()).log(Level.SEVERE, null, ex);
				System.out.println("com.frames.AndraAgent.btnAndraActionPerformed()");
			}
			if (rBFaltagent.isSelected()) {
				if (!agent.isFaltagent()) {

					Faltagent faltagent = new Faltagent(agent);
					try {
						faltagent.insertRoleSql();
					} catch (InfException ex) {
						lblErrorAndra.setForeground(Color.RED);
						lblErrorAndra.setText("Fail");
						lblErrorAndra.setVisible(true);
						Logger.getLogger(AndraAgent.class.getName()).log(Level.SEVERE, null, ex);
						System.out.println("com.frames.AndraAgent.btnAndraActionPerformed(): " + ex);
					}

				}
			} else {
				if (agent.isFaltagent()) {
					Faltagent faltagent = new Faltagent(agent);
					try {
						faltagent.deleteRoleSql();
					} catch (InfException ex) {
						lblErrorAndra.setForeground(Color.RED);
						lblErrorAndra.setText("Fail");
						lblErrorAndra.setVisible(true);
						Logger.getLogger(AndraAgent.class.getName()).log(Level.SEVERE, null, ex);
						System.out.println("com.frames.AndraAgent.btnAndraActionPerformed(): " + ex);
					}
				}
			}
			if (rBKontorschef.isSelected()) {

				if (!agent.isKontorschef()) {
					try {

						Kontorschef koChef = new Kontorschef(agent);
						koChef.setKontorsbeteckning(txtKontor.getText());
						koChef.addKontorschefSql();

					} catch (InfException ex) {
						lblErrorAndra.setForeground(Color.RED);
						lblErrorAndra.setText("Fail");
						lblErrorAndra.setVisible(true);
						Logger.getLogger(AndraAgent.class.getName()).log(Level.SEVERE, null, ex);
					}

				} else {
					Kontorschef koChef;
					try {
						koChef = new Kontorschef(agent);
						koChef.setKontorsbeteckning(txtKontor.getText());
						koChef.updateKontorsAnsvarSQL();
					} catch (InfException ex) {
						lblErrorAndra.setForeground(Color.RED);
						lblErrorAndra.setText("Fail");
						lblErrorAndra.setVisible(true);
						Logger.getLogger(AndraAgent.class.getName()).log(Level.SEVERE, null, ex);
					}
				}

			} else {
				if (agent.isKontorschef()) {
					try {
						Kontorschef kontorschef = new Kontorschef(agent);
						kontorschef.deleteRoleSql();
					} catch (InfException ex) {
						lblErrorAndra.setForeground(Color.RED);
						lblErrorAndra.setText("Fail");
						lblErrorAndra.setVisible(true);
						Logger.getLogger(AndraAgent.class.getName()).log(Level.SEVERE, null, ex);
					}
				}
			}
			if (rBOmradesChef.isSelected()) {

				if (!agent.isOmradesschef()) {
					try {
						Omradeschef koChef = new Omradeschef(agent, Integer.parseInt(txtAnsvarsOmrode.getText()));
						koChef.replaceOmradesChef();
					} catch (InfException ex) {
						lblErrorAndra.setForeground(Color.RED);
						lblErrorAndra.setText("Fail");
						lblErrorAndra.setVisible(true);
						Logger.getLogger(AndraAgent.class.getName()).log(Level.SEVERE, null, ex);
					}
				} else {
					Omradeschef koChef;
					try {
						koChef = new Omradeschef(agent, Integer.parseInt(txtAnsvarsOmrode.getText()));
						int oldOmrade = koChef.getAnsvarOmradeSql();
						int gammalAgent = Integer.parseInt(Agent.getConnection().fetchSingle("Select Agent_ID from omradeschef where OMRADE =" +Integer.parseInt(txtAnsvarsOmrode.getText())));
						Agent.getConnection().delete("DELETE from OMRADESCHEF where AGENT_ID = " + gammalAgent);
						Agent.getConnection().insert("Insert into omradeschef VALUES(" + gammalAgent + ", " + oldOmrade + ")");
						koChef.updateAnsvarsOmradeSQL();
					} catch (InfException ex) {
						lblErrorAndra.setForeground(Color.RED);
						lblErrorAndra.setText("Fail");
						lblErrorAndra.setVisible(true);
						Logger.getLogger(AndraAgent.class.getName()).log(Level.SEVERE, null, ex);
					}
				}

			} else {
				if (agent.isOmradesschef()) {
					Omradeschef koChef;
					try {
						koChef = new Omradeschef(agent, 0);
						koChef.deleteRoleSql();
					} catch (InfException ex) {
						lblErrorAndra.setText("Fail");
						lblErrorAndra.setVisible(true);
						Logger.getLogger(AndraAgent.class.getName()).log(Level.SEVERE, null, ex);
					}
				}
			}
			try {
				agent.updateAllColumnsSql();

				lblErrorAndra.setForeground(Color.GREEN);
				lblErrorAndra.setText("Success");
				lblErrorAndra.setVisible(true);
			} catch (InfException ex) {
				Logger.getLogger(AndraAgent.class.getName()).log(Level.SEVERE, null, ex);
				lblErrorAndra.setForeground(Color.RED);
				lblErrorAndra.setText("Fail");
				lblErrorAndra.setVisible(true);
			}

		} else {
			lblErrorAndra.setForeground(Color.RED);
			lblErrorAndra.setText("Fail");
			lblErrorAndra.setVisible(true);
		}
		// TODO add your handling code here:
    }//GEN-LAST:event_btnAndraActionPerformed
//Hämtar ID för agenten
    private void btnGetAgentActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnGetAgentActionPerformed
    {//GEN-HEADEREND:event_btnGetAgentActionPerformed
		TextBoxClear();
		if (Validering.isID(txtID.getText())) {
			String idInput = txtID.getText();
			if (Validering.isDigit(idInput)) {
				int id;
				id = Integer.parseInt(idInput);
				if (Agent.idExists(id)) {
					try {
						agent = Agent.getSql(id);
					} catch (InfException e) {
						btnAndra.setEnabled(false);
						System.out.println(e);
					}
					TextBoxFill();
				} else {
					btnAndra.setEnabled(false);
				}
			} else {
				txtNamn.setText("ID:" + idInput + " Not Valid");
				btnAndra.setEnabled(false);
			}
		}

		// TODO add your handling code here:
    }//GEN-LAST:event_btnGetAgentActionPerformed

    private void rBAdminActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_rBAdminActionPerformed
    {//GEN-HEADEREND:event_rBAdminActionPerformed
		// TODO add your handling code here:
    }//GEN-LAST:event_rBAdminActionPerformed

    private void rBKontorschefActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_rBKontorschefActionPerformed
    {//GEN-HEADEREND:event_rBKontorschefActionPerformed
		// TODO add your handling code here:
    }//GEN-LAST:event_rBKontorschefActionPerformed

    private void jBtnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnExitActionPerformed
		dispose();
    }//GEN-LAST:event_jBtnExitActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAndra;
    private javax.swing.JButton btnGetAgent;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jBtnExit;
    private javax.swing.JLabel jLblNamn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblAndraAgentTitel;
    private javax.swing.JLabel lblAnstDat;
    private javax.swing.JLabel lblAnsvarsOmrade;
    private javax.swing.JLabel lblErrorAndra;
    private javax.swing.JLabel lblErrorAnstallning;
    private javax.swing.JLabel lblErrorAnsvarsOmrade;
    private javax.swing.JLabel lblErrorID;
    private javax.swing.JLabel lblErrorKontor;
    private javax.swing.JLabel lblErrorLosenord;
    private javax.swing.JLabel lblErrorNamn;
    private javax.swing.JLabel lblErrorOmrade;
    private javax.swing.JLabel lblErrorTelefon;
    private javax.swing.JLabel lblKontorsbeteckning;
    private javax.swing.JLabel lblLosenord;
    private javax.swing.JLabel lblOmrade;
    private javax.swing.JLabel lblTelefon;
    private javax.swing.JRadioButton rBAdmin;
    private javax.swing.JRadioButton rBFaltagent;
    private javax.swing.JRadioButton rBKontorschef;
    private javax.swing.JRadioButton rBOmradesChef;
    private javax.swing.JTextField txtAnstallningsdatum;
    private javax.swing.JTextField txtAnsvarsOmrode;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtKontor;
    private javax.swing.JTextField txtLosenord;
    private javax.swing.JTextField txtNamn;
    private javax.swing.JTextField txtOmrade;
    private javax.swing.JTextField txtTelNum;
    // End of variables declaration//GEN-END:variables
}
