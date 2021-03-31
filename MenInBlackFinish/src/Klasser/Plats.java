/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klasser;

import static Klasser.Omrade.getConnection;
import oru.inf.InfDB;
import oru.inf.InfException;

/**
 *
 * @author jonat
 */
public class Plats {

    private int platsID;
    private Omrade omrade;
    private String benamning;
   private static InfDB db;

	public static InfDB getConnection()
	{
		return db;
	}

	public static void initConnection(InfDB newDb)
	{
		db = newDb;
	}

	public static boolean idExists(int id)
	{
		boolean idExists = false;
		try {
			idExists = getConnection().fetchColumn("select Plats_ID from Plats;").contains("" + id);
		} catch (InfException e) {

			System.out.print("Klasser.Plats.idExists(): ");
			System.out.println(e);
		}
		return idExists;
	}

    public Plats(int platsID, Omrade omrade, String benamning) {

        this.omrade = omrade;
        this.benamning = benamning;
    }

    public void setPlatsID(int newPlatsID) {
        platsID = newPlatsID;
    }

    public void setFinnsi(Omrade omrade) {
        this.omrade = omrade;
    }

    public void setbenamning(String newbenamning) {
        benamning = newbenamning;
    }

    public int getPlatsID() {
        return platsID;
    }

    public Omrade getFinnsI() {
        return omrade;
    }

    public String getbenamning() {
        return benamning;

    }

    private void updatePlatsIDSql() throws InfException {
        String id = Integer.toString(platsID);
        db.update("update PLATS set PLATS_ID ='" + id + "'where PLATS_ID=" + id);

    }

    public void updateOmradeSql() throws InfException {
        String id = Integer.toString(platsID);
        db.update("update PLATS set FINNS_I ='" + omrade.getOmradesID() + "' where PLATS_ID=" + id);
    }

    public void updateBenamningSql() throws InfException {
        String id = Integer.toString(platsID);
        db.update("update PLATS set BENAMNING ='" + benamning + "' where PLATS_ID=" + id);
    }

    public Boolean addPlatsSql() throws InfException {
        String id = Integer.toString(platsID);
        String info = "'" + id + "','" + omrade.getOmradesID() + "','" + benamning + "'";
        db.insert("insert into PLATS values( " + info + ")");
        return true;
    }

    public Boolean removePlatsSql() throws InfException {
        String sid = Integer.toString(platsID);
        db.delete("delete from PLATS where PLATS_ID =" + sid);
        return true;
    }

    public void updateEveryColumPlatsSql() throws InfException {
        updatePlatsIDSql();
        updateBenamningSql();
        updateOmradeSql();

    }
	

}
