/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klasser;

import mib.grupp2.Huvudfonster;
import oru.inf.InfDB;
import oru.inf.InfException;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Josef
 */
public class Main
{

	private static InfDB db;

	public InfDB getDb()
	{
		return db;
	}

	public static void main(String args[])
	{

		Path absolut = Paths.get("MIBDB.FDB");
		String connectionString;
		connectionString = absolut.toAbsolutePath().normalize().toString();
		try {
			//db = new InfDB("C:\\db\\MIBDB.fdb");
			db = new InfDB(connectionString);
		} catch (InfException e) {

			System.out.println("Klasser.Main.main()");
			System.out.println(e);

			System.out.println("C:\\db\\MIBDB.fdb");
		}

		Alien.initConnection(db);
		Agent.initConnection(db);
		Omrade.initConnection(db);
		Plats.initConnection(db);
		Utrustning.initConnection(db);

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
			java.util.logging.Logger.getLogger(Huvudfonster.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Huvudfonster.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Huvudfonster.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Huvudfonster.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>
		//</editor-fold>
		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				new Huvudfonster().setVisible(true);
			}
		});
	}

}
