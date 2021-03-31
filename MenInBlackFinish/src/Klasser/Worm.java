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
public class Worm extends Alien {

    public Worm(Alien alien) {
        super(alien);
    }

    public void insertDB() throws InfException {
        super.insertSql();

        insertRas();
    }

    public void insertRas() throws InfException {
        getDb().insert("Insert into WORM values (" + getId() + ")");
    }

    public void updateDB() throws InfException {
        super.updateAllSql();
    }

    public void deleteDB() throws InfException {
        super.removeSql();
    }

    public void deleteRaceEntryDB() throws InfException {
        Alien.getDb().delete("delete from WORM where ALIEN_ID = " + getId());
    }

    public static Worm getAlien(int id) throws InfException {
        Alien alien = Alien.getAlien(id);
        return new Worm(alien);
    }
}
