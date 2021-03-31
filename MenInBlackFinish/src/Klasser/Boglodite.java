/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klasser;

import oru.inf.InfException;

/**
 *
 * @author Josef
 */
public class Boglodite extends Alien
{

	private int antalBoogies;

	public Boglodite(String namn, String losenord, String telefon, String registreringsdatum, int id, int agent, int plats)
	{
		super(namn, losenord, telefon, registreringsdatum, id, agent, plats);
	}

	public Boglodite(Alien alien, int antalBoogies)
	{
		super(alien);
		this.antalBoogies = antalBoogies;
	}

	/**
	 * @return the antalBoogies
	 */
	public int getAntalBoogies()
	{
		return antalBoogies;
	}

	/**
	 * @param antalBoogies the antalBoogies to set
	 */
	public void setAntalBoogies(int antalBoogies)
	{
		this.antalBoogies = antalBoogies;
	}

	public void insertDB() throws InfException
	{
		super.insertSql();
		insertRas();
	}
        //Lägger in data i BOGLODITE tabellen
	public void insertRas() throws InfException
	{
		Alien.getDb().insert("insert into BOGLODITE values (" + getId() + "," + getAntalBoogies() + ")");
	}
        //Updaterat tabellen
	public void updateDB() throws InfException
	{
		super.updateAllSql();
		Alien.getDb().update("update BOGLODITE set ANTAL_BOOGIES = " + getAntalBoogies() + " where alien_id=" + getId());
	}

	public void deleteDB() throws InfException
	{
		deleteRaceEntryDB();
		super.removeSql();
	}

	public void deleteRaceEntryDB() throws InfException
	{
		Alien.getDb().delete("delete from BOGLODITE where ALIEN_ID = " + getId());
	}
        //Hämtar alien på ID
	public static Boglodite getAlien(int id) throws InfException
	{
		Alien alien = Alien.getAlien(id);
		int boogies = Integer.parseInt(getDb().fetchColumn("Select ANTAL_BOOGIES FROM BOGLODITE where ALIEN_ID = " + alien.getId()).get(0));
		return new Boglodite(alien, boogies);
	}
}
