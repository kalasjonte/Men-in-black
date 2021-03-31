/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klasser;

import java.util.ArrayList;
import oru.inf.InfDB;
import oru.inf.InfException;
import java.util.HashMap;
import javax.swing.JOptionPane;

/**
 * @AUTHORS JOSEF JAMAI-HAITOT, JONATHAN GABRIELSSON, VILMA OTTNER
 * @VERSION 2021.01.05
 */
public class Alien {

    private int id;
    private String registreringsdatum;
    private String losenord;
    private String namn;
    private String telefon;

    private int agent;
    private int plats;
    private static InfDB db = null;

    public Alien(Alien alien) {
        this.agent = alien.getAgent();
        this.id = alien.getId();
        this.losenord = alien.getLosenord();
        this.registreringsdatum = alien.getRegistreringsdatum();
        this.plats = alien.getPlats();
        this.telefon = alien.getTelefon();
    }

    public Alien(String id) {
        this.id = Integer.parseInt(id);
    }

    public Alien(String namn, String losenord, String telefon, String registreringsdatum, int id, int agent, int plats) {
        this(namn, losenord, telefon, registreringsdatum, agent, plats);
        this.id = id;
    }

    public Alien(String namn, String losenord, String telefon, String registreringsdatum, int agent, int plats) {

        this.losenord = losenord;
        this.telefon = telefon;
        this.namn = namn;
        this.registreringsdatum = registreringsdatum;
        this.agent = agent;
        this.plats = plats;
    }

    public Alien(String id, String namn, String losenord) {
        this.id = Integer.parseInt(id);
        this.namn = namn;
        this.losenord = losenord;

    }

    public Alien(HashMap<String, String> alien) throws InfException {
        this(
                alien.get("NAMN"),
                alien.get("LOSENORD"),
                alien.get("TELEFON"),
                alien.get("REGISTRERINGSDATUM"),
                Integer.parseInt(alien.get("ALIEN_ID")),
                Integer.parseInt(alien.get("ANSVARIG_AGENT")),
                Integer.parseInt(alien.get("ANSVARIG_AGENT"))
        );
    }

    public Alien(int id) throws InfException {
        this(db.fetchRow("select * from alien where alien_id =" + id));
    }

    public final int getId() {
        return id;
    }

    public final void setId(Integer id) {
        this.id = id;
    }

    public final String getRegistreringsdatum() {
        return registreringsdatum;
    }

    public final void setRegistreringsdatum(String registreringsdatum) {
        this.registreringsdatum = registreringsdatum;
    }

    public final String getLosenord() {
        return losenord;
    }

    public final void setLosenord(String losenord) {
        this.losenord = losenord;
    }

    public final String getNamn() {
        return namn;
    }

    public final void setNamn(String namn) {
        this.namn = namn;
    }

    public final String getTelefon() {
        return telefon;
    }

    public final void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public final int getAgent() {
        return agent;
    }

    public final void setAgent(int agent) {
        this.agent = agent;
    }

    public final int getPlats() {
        return plats;
    }

    public final void setPlats(int plats) {
        this.plats = plats;
    }

    /**
     * Gör en strng som ska användas som query
     * @return string
     */
    private String insertQueryString() {
        return "INSERT INTO ALIEN "
                + "(ALIEN_ID, REGISTRERINGSDATUM, LOSENORD, NAMN, TELEFON, PLATS, ANSVARIG_AGENT)"
                + " VALUES ('" + this.getId()
                + "', '" + this.getRegistreringsdatum()
                + "', '" + this.getLosenord()
                + "', '" + this.getNamn()
                + "', '" + this.getTelefon()
                + "', " + this.getPlats()
                + ", " + this.getAgent() + ")";

    }
    //Inserta med string hämtad från insertQueryString()
    public void insertSql() throws InfException {
        String query = this.insertQueryString();
        getDb().insert(query);
    }

    public void removeSql() throws InfException {
        getDb().delete("delete from ALIEN where alien_id=" + this.getId());
    }

    public void updateNamnSql() throws InfException {
        getDb().update("update ALIEN set namn='" + this.getNamn() + "' where alien_id=" + this.getId());
    }

    public void updatePlatsSql() throws InfException {
        getDb().update("update ALIEN set PLATS= " + this.getPlats() + " where alien_id=" + this.getId());
    }

    public void updateLosenordSql() throws InfException {
        getDb().update("update ALIEN set LOSENORD = '" + this.getLosenord() + "' where alien_id=" + this.getId());
    }

    public void updateAgentSql() throws InfException {
        getDb().update("update ALIEN set ANSVARIG_AGENT= " + this.getAgent() + " where alien_id=" + this.getId());
    }

    private void updateTelefon() throws InfException {
        getDb().update("update ALIEN set telefon= " + this.telefon + " where alien_id=" + this.getId());
    }

    public void updateAllSql() throws InfException {
        this.updateNamnSql();
        this.updatePlatsSql();
        this.updateLosenordSql();
        this.updateAgentSql();
        updateTelefon();
    }

    public void updateOrInsertSql() throws InfException {
        boolean alienExists = !getDb().fetchColumn("select namn from alien where ALIEN_ID = " + this.getId()).isEmpty();
        if (alienExists) {
            this.updateAllSql();
        } else {
            this.insertSql();
        }
    }

    public void printAlien() {
        System.out.println(getId());
        System.out.println(getNamn());
        System.out.println(getTelefon());
    }

    public static void initConnection(InfDB newDB) {
        db = newDB;
    }

    public static boolean checkConnection() {
        return getDb() != null;
    }
    /**
     * Kollar så namn och lösenord stämmer vid inlogg
     * @param namn
     * @param pwd
     * @return 
     */
    public static boolean loginConfirmation(String namn, String pwd) {
        try {
            HashMap<String, String> alien = getDb().fetchRow("Select * from alien where NAMN = " + namn + " AND LOSENORD = " + pwd);
            if (alien == null) {
                return false;
            }
        } catch (InfException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    public static boolean idExists(int id) {
        boolean idExists = false;
        try {
            idExists = getDb().fetchColumn("select ALIEN_ID from Alien;").contains("" + id);
        } catch (InfException e) {
            System.out.println(e);
        }
        return idExists;
    }

    public static Alien getAlien(int id) throws InfException {

        return new Alien(getDb().fetchRow("Select * from alien where ALIEN_ID = " + id));

        //throw new NullPointerException("ID: " +  id  + " Not Found: Alien getAlien(int)");
    }

    public static Alien getAlien(String namn, String pwd) throws InfException {

        return new Alien(getDb().fetchRow("Select * from alien where NAMN = " + namn + " AND LOSENORD = " + pwd));

    }
    /**
     * Skriver ut all information om Alien
     * @return ArrayList av alien
     * @throws InfException 
     */
    public static ArrayList<Alien> getSql() throws InfException {
        ArrayList<HashMap<String, String>> alienHashMapList = getDb().fetchRows("select * from alien");
        ArrayList<Alien> alienList = new ArrayList<>();
        for (HashMap<String, String> alien : alienHashMapList) {
            alienList.add(new Alien(alien));

        }
        return alienList;
    }

    public static ArrayList<Alien> getSql(String namn) throws InfException {
        ArrayList<HashMap<String, String>> alienHashMapList = getDb().fetchRows("select * from alien where UPPER(NAMN) = '" + namn.toUpperCase() + "'");
        ArrayList<Alien> alienList = new ArrayList<>();
        for (HashMap<String, String> alien : alienHashMapList) {
            alienList.add(new Alien(alien));

        }
        return alienList;
    }

    public static int uniqueID() throws InfException {
        String nextId = getDb().getAutoIncrement("ALIEN", "ALIEN_ID");
        return Integer.parseInt(nextId);
    }

    /**
     * @return the db
     */
    public static InfDB getDb() {
        return db;
    }
    /**
     * Kollar igenom databasen för att hitta id kopplat till rasen
     * @return 
     */
    public int whichRace() {
        int race;
        boolean Boglo;
        boolean Worm;
        boolean Squid;

        try {
            Boglo = getDb().fetchSingle("Select ALIEN_ID from BOGLODITE where ALIEN_ID = " + getId() + ";") != null;
        } catch (InfException e) {
            System.out.println(e);
            Boglo = false;
        }
        try {
            Worm = getDb().fetchSingle("Select ALIEN_ID from WORM where ALIEN_ID = " + getId()) != null;
        } catch (InfException e) {
            System.out.println(e);
            Worm = false;
        }
        try {
            Squid = getDb().fetchSingle("Select ALIEN_ID from SQUID where ALIEN_ID = " + getId()) != null;
        } catch (InfException e) {
            System.out.println(e);
            Squid = false;
        }

        if (Boglo) {
            race = 0;
        } else if (Worm) {
            race = 1;
        } else if (Squid) {
            race = 2;
        } else {
            race = 3;
        }

        return race;

    }
}
