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
public class Squid extends Alien {

    private int antalArmar;

    public Squid(String namn, String losenord, String telefon, String registreringsdatum, int id, int agent, int plats) {
        super(namn, losenord, telefon, registreringsdatum, id, agent, plats);
    }

    public Squid(Alien alien, int armar) {
        super(alien);
        this.antalArmar = armar;
    }

    /**
     * @return the antalArmar
     */
    public int getAntalArmar() {
        return antalArmar;
    }

    /**
     * @param antalArmar the antalArmar to set
     */
    public void setAntalArmar(int antalArmar) {
        this.antalArmar = antalArmar;
    }

    public void insertDB() throws InfException {
        super.insertSql();

        insertRas();
    }

    public void insertRas() throws InfException {
        Alien.getDb().insert("insert into SQUID values (" + getId() + "," + getAntalArmar() + ")");
    }

    public void updateDB() throws InfException {
        super.updateAllSql();
        Alien.getDb().update("update BOGLODITE set ANTAL_BOOGIES = " + getAntalArmar() + " where alien_id=" + getId());
    }

    public void deleteDB() throws InfException {
        deleteRaceEntryDB();
        super.removeSql();
    }

    public void deleteRaceEntryDB() throws InfException {
        Alien.getDb().delete("delete from SQUID where ALIEN_ID = " + getId());
    }

    public static Squid getAlien(int id) throws InfException {
        Alien alien = Alien.getAlien(id);
        int armar = Integer.parseInt(getDb().fetchColumn("Select ANTAL_ARMAR FROM SQUID where ALIEN_ID = " + alien.getId()).get(0));
        return new Squid(alien, armar);

    }
}
