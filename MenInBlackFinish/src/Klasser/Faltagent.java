/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klasser;

import oru.inf.InfException;

public final class Faltagent extends Agent {

    public Faltagent(Agent agent) {
        super(agent);

    }

    public void insertSql() throws InfException {
        super.insertDb();
        insertRoleSql();
    }
    
    public void insertRoleSql() throws InfException {
        getConnection().insert("insert into FALTAGENT values( " + getId() + ")");
    }

    @Override
    public final void deleteSql() throws InfException {
        deleteRoleSql();
        super.deleteSql();
    }

    public final void deleteRoleSql() throws InfException {
        getConnection().delete("delete from FALTAGENT where AGENT_ID=" + getId());
    }

}
