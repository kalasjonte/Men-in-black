/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klasser;

import oru.inf.InfDB;
import oru.inf.InfException;

public class Utrustning
{

	private int utrustningsID;
	private String benamning;
	private String typ;
	private String egenskap;
	private static InfDB db;

	public static InfDB getConnection()
	{
		return db;
	}

	public static void initConnection(InfDB newDb)
	{
		db = newDb;
	}

	public Utrustning(String benamning) throws InfException
	{

		this.benamning = benamning;
		try{
		this.utrustningsID = Integer.parseInt(db.getAutoIncrement("UTRUSTNING", "UTRUSTNINGS_ID"));
		}
		catch (Exception e) {
		System.out.print("UTRUSTNING AUTO INC");
		}

	}

	public void setUtrustningsID(int newUtrustningsID)
	{
		utrustningsID = newUtrustningsID;
	}

	public void setBenamning(String newBenamning)
	{
		benamning = newBenamning;
	}

	public void setEgenskap(String egenskap)
	{
		this.egenskap = egenskap;
	}

	public void setTyp(String typ)
	{
		this.typ = typ;
	}

	public int getUtrustingsID()
	{
		return utrustningsID;
	}

	public String getBenamning()
	{
		return benamning;
	}

	public void updateBenamningSql() throws InfException
	{
		String id = Integer.toString(utrustningsID);
		db.update("update UTRUSTNING set BENAMNING ='" + benamning + "' where UTRUSTNINGS_ID=" + id);
	}

	public void addUtrustningSql() throws InfException
	{

		String info = "'" + utrustningsID + "','" + benamning + "'";
		db.insert("insert into UTRUSTNING values(  " + info + ")");

	}

	public Boolean removePlatsSql() throws InfException
	{
		String sid = Integer.toString(utrustningsID);
		db.delete("delete from UTRUSTNING where UTRUSTNINGS_ID =" + sid);
		return true;
	}

}
