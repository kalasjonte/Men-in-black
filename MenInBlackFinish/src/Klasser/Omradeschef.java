/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klasser;

import java.util.logging.Level;
import java.util.logging.Logger;
import oru.inf.InfException;

public final class Omradeschef extends Agent
{

	private int AnsvarsOmrade;

	public Omradeschef(Agent agent) throws InfException
	{
		super(agent);
		this.AnsvarsOmrade = getAnsvarOmradeSql();
	}

	public Omradeschef(Agent agent, int ansvarsOmrade) throws InfException
	{
		super(agent);
		this.AnsvarsOmrade = ansvarsOmrade;
	}

	public void setAnsvarsOmrade(int omrade)
	{
		this.AnsvarsOmrade = omrade;
	}

	public int getAnsvarsOmrade()
	{
		return AnsvarsOmrade;
	}

	public int getAnsvarOmradeSql() throws InfException
	{

		int ansvarsOmrade;
		ansvarsOmrade = Integer.parseInt(getConnection().fetchSingle("Select omrade from omradeschef where AGENT_ID =" + getId()));
		return ansvarsOmrade;

	}

	public void updateAnsvarsOmradeSQL() throws InfException
	{
		deleteRoleSql();
		replaceOmradesChef();
	}

	public void insert() throws InfException
	{
		super.insertDb();
		addOmradesChefSql();
	}

	public void addOmradesChefSql() throws InfException
	{
		getConnection().insert("insert into OMRADESCHEF values( " + getId() + ",'" + getAnsvarsOmrade() + "')");
	}

	public void removeOmradesChef() throws InfException
	{
		deleteRoleSql();
		super.deleteSql();

	}

	public final void deleteRoleSql() throws InfException
	{
		getConnection().delete("delete from OMRADESCHEF where AGENT_ID=" + getId());
	}

	public void updateAllColumns() throws InfException
	{
		super.updateAllColumnsSql();
		updateAnsvarsOmradeSQL();
	}

	public static void switchOmrade(Agent agent, Agent agent2)
	{

	}

	

	public void replaceOmradesChef()
	{
		try {
			getConnection().delete("DELETE FROM OMRADESCHEF WHERE OMRADE =" + this.getAnsvarsOmrade());
		} catch (InfException e) {

		}
		try {
			addOmradesChefSql();
		} catch (InfException ex) {
			Logger.getLogger(Omradeschef.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

}
