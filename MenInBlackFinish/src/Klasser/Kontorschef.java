/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klasser;

import oru.inf.InfException;
import oru.inf.InfDB;

/**
 *
 * @author jonat
 */
public final class Kontorschef extends Agent {

    private String kontorsbeteckning;

    public Kontorschef(Agent agent) throws InfException {
        super(agent);
        this.kontorsbeteckning = getKontorsbeteckningSql();
    }

    public Kontorschef(Agent agent, String kontor) throws InfException {
        super(agent);
        this.kontorsbeteckning = kontor;
    }

    public String getKontorsbeteckning() {
        return kontorsbeteckning;
    }

    public void setKontorsbeteckning(String kBeteckning) {
        this.kontorsbeteckning = kBeteckning;
    }

    public String getKontorsbeteckningSql() throws InfException {

        String kontor = getConnection().fetchSingle("Select kontorsbeteckning from kontorschef where AGENT_ID =" + getId());
        return kontor;

    }

    public void updateKontorsAnsvarSQL() throws InfException {
        getConnection().update("update kontorschef set kontorsbeteckning ='" + getKontorsbeteckning() + "' where AGENT_ID=" + getId());
    }

    public void insertSql() throws InfException {
        super.insertDb();
        addKontorschefSql();

    }

    public void addKontorschefSql() throws InfException {
        getConnection().insert("insert into kontorschef values( " + getId() + ",'" + getKontorsbeteckning() + "')");
    }

    public final void deleteRoleSql() throws InfException {
        getConnection().delete("delete from kontorschef where AGENT_ID=" + getId());
    }

    public void removeKontorschef() throws InfException {
        deleteRoleSql();
        super.deleteSql();

    }

    public void updateAllColumns() throws InfException {
        super.updateAllColumnsSql();
        updateKontorsAnsvarSQL();
    }

}
