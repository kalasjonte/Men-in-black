/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klasser;

import oru.inf.InfDB;
import oru.inf.InfException;

/**
 *
 * @author jonat
 */
public class Omrade
{

	private int omradesID;
	private String benamning;

	public Omrade(int omradesID, String benamning)
	{
		this.omradesID = omradesID;
		this.benamning = benamning;
	}
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
			idExists = getConnection().fetchColumn("select OMRADES_ID from OMRADE;").contains("" + id);
		} catch (InfException e) {

			System.out.print("Klasser.Omrade.idExists(): ");
			System.out.println(e);
		}
		return idExists;
	}

	public void setOmradesID(int newOmradesID)
	{
		omradesID = newOmradesID;
	}

	public void setBenamning(String newBenamning)
	{
		benamning = newBenamning;
	}

	public int getOmradesID()
	{
		return omradesID;
	}

	public String getbenamning()
	{
		return benamning;
	}

	public boolean addSQL() throws InfException
	{
		String id = Integer.toString(omradesID);
		String info = "'" + id + "','" + benamning + "'";
		db.insert("insert into OMRADE values( " + info + ")");
		return true;
	}

	public void updateOmradesIDSQL() throws InfException
	{
		String id = Integer.toString(omradesID);
		db.update("update OMRADE set OMRADES_ID ='" + id + "'where OMRADES_ID=" + id);
	}

	public void updateBenamnningSQL() throws InfException
	{
		String id = Integer.toString(omradesID);
		db.update("update OMRADE set BENAMNING='" + benamning + "'where OMRADES_ID=" + id);
	}

	public boolean removeSQL() throws InfException
	{
		String id = Integer.toString(omradesID);
		db.delete("delete from OMRADE where OMRADES_ID =" + id);
		return true;
	}

	public void updateColumn() throws InfException
	{
		updateOmradesIDSQL();
		updateBenamnningSQL();
	}

}
