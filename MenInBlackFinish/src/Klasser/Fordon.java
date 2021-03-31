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
public class Fordon {

    private String fordonsId;
    private String fordonsbeskrivning;
    private String registreringsdatum;
    private int arsmodell;
    private InfDB db;

    public Fordon(String registreringsdatum, String fordonsbeskrivning, int arsmodell) {

        this.registreringsdatum = registreringsdatum;
        this.fordonsbeskrivning = fordonsbeskrivning;
        this.arsmodell = arsmodell;

        try {
            db = new InfDB("C:\\db\\MIBDB.fdb");
            this.fordonsId = db.getAutoIncrement("FORDON", "FORDONS_ID");
        } catch (InfException undantag) {
            System.out.print("fel");
        }
    }

    public String getFordonsId() {
        return fordonsId;
    }

    public String getFordonsbeskrivning() {
        return fordonsbeskrivning;
    }

    public void setFordonsbeskrivning(String fordonsbeskrivning) {
        this.fordonsbeskrivning = fordonsbeskrivning;
    }

    public String getRegistreringsdatum() {
        return registreringsdatum;
    }

    public void setRegistreringsdatum(String registreringsdatum) {
        this.registreringsdatum = registreringsdatum;
    }

    public Integer getArsmodell() {
        return arsmodell;
    }

    public void setArsmodell(Integer arsmodell) {
        this.arsmodell = arsmodell;
    }

    public void updateFordonsBeskrivningSql() throws InfException {
        db.update("update FORDON set FORDONSBESKRIVNING ='" + fordonsbeskrivning + "' where FORDONS_ID=" + fordonsId);
    }

    public void updateRegistreringsdatumSql() throws InfException {
        db.update("update FORDON set REGISTRERINGSDATUM ='" + registreringsdatum + "' where FORDONS_ID=" + fordonsId);
    }

    public void updateArsmodellql() throws InfException {
        db.update("update FORDON set ARSMODELL ='" + arsmodell + "' where FORDONS_ID=" + fordonsId);
    }

    public void updateEveryColumPlatsFordon() throws InfException {
        updateFordonsBeskrivningSql();
        updateRegistreringsdatumSql();
        updateArsmodellql();

    }

    public void addFordonSql() throws InfException {
        String info = "'" + fordonsId + "','" + fordonsbeskrivning + "','" + registreringsdatum + "','" + arsmodell + "'";
        db.insert("insert into FORODN values( " + info + ")");
    }

    public void removeFordonSql() throws InfException {

        db.delete("delete from FORDON where FORDONS_ID =" + fordonsId);

    }

}
