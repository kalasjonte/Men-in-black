/*
 * En klass som ska simulera en instans av en agent i MIB. Hanterar data som
hör till agenter. 
 */
package Klasser;

;
import oru.inf.InfDB;
import oru.inf.InfException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @AUTHORS JOSEF JAMAI-HAITOT, JONATHAN GABRIELSSON, VILMA OTTNER
 * @VERSION 2020.01.07
 */


public class Agent
{

	private static InfDB db;
	private int agentId;
	private String namn;
	private String telefon;
	private String anstallningsdatum;
	private String administrator;
	private String losenord;
	private int omradesPlacering;

	public Agent(Agent agent)
	{
		this(agent.getNamn(), agent.getLosenord(), agent.getTelefon(), agent.getAnstallningsdatum(), agent.getId(), agent.getOmrade(), agent.getAdministrator());
	}

	public Agent(String namn, String anstallningsdatum, String administrator, String losenord)
	{
		this.administrator = "" + administrator.toUpperCase().charAt(0);
		this.anstallningsdatum = anstallningsdatum;
		this.namn = namn;
		this.losenord = losenord;
	}

	public Agent(String namn, String losen, String telefon, String date, int id, int omrade, String admin)
	{

		this.namn = namn;
		this.losenord = losen;
		this.telefon = telefon;
		this.anstallningsdatum = date;
		this.agentId = id;
		this.omradesPlacering = omrade;
		this.administrator = admin;
	}

	public Agent(int id, String namn, String losenord)
	{
		this.agentId = id;
		this.namn = namn;
		this.losenord = losenord;

	}
        
        public Agent(String id)
        {
            this.agentId = Integer.parseInt(id);
        }

	public Agent(int id) throws InfException
	{
		this(db.fetchRow("select * from AGENT where AGENT_ID =" + id));
	}

	private Agent(HashMap<String, String> agentMap)
	{
		this.administrator = "" + agentMap.get("ADMINISTRATOR").toUpperCase().charAt(0);
		this.agentId = Integer.parseInt(agentMap.get("AGENT_ID"));
		this.anstallningsdatum = agentMap.get("ANSTALLNINGSDATUM");
		this.omradesPlacering = Integer.parseInt(agentMap.get("OMRADE"));
		this.losenord = agentMap.get("LOSENORD");
		this.namn = agentMap.get("NAMN");
		this.telefon = agentMap.get("TELEFON");
	}

	public Agent(int agentId, String namn, String anstallningsdatum, String administrator, String losenord)
	{
		this(namn, anstallningsdatum, administrator, losenord);
		this.agentId = agentId;
	}

	public Agent(String id, String namn, String losenord)
	{
		this.agentId = Integer.parseInt(id);
		this.namn = namn;
		this.losenord = losenord;

	}

	public final int getId()
	{
		return agentId;
	}

	public final void setId(int agentId)
	{
		this.agentId = agentId;
	}

	public final String getNamn()
	{
		return namn;
	}

	public final void setNamn(String namn)
	{
		this.namn = namn;
	}

	public final String getTelefon()
	{

		return telefon;
	}

	public final void setTelefon(String telefon)
	{
		this.telefon = telefon;

	}

	public final String getAnstallningsdatum()
	{
		return anstallningsdatum;
	}

	public final void setAnstallningsdatum(String anstallningsdatum)
	{
		this.anstallningsdatum = anstallningsdatum;
	}

	public final String getAdministrator()
	{
		return this.administrator;
	}

	public final void setAdministrator(String administratorStatus)
	{
		this.administrator = "" + administratorStatus.toUpperCase().charAt(0);

	}

	public final String getLosenord()
	{
		return losenord;
	}

	public final void setLosenord(String losenord)
	{
		this.losenord = losenord;
	}

	public final int getOmrade()
	{
		return omradesPlacering;
	}

	public final void setOmrade(int omrade)
	{
		this.omradesPlacering = omrade;
	}
        /**
         * Lägger in värden i databasen
         * @throws InfException 
         */
	public void insertDb() throws InfException
	{
		String info;
		info = ""
				+ this.getId() + ",'"
				+ this.getNamn() + "','"
				+ this.getTelefon() + "','"
				+ this.getAnstallningsdatum() + "','"
				+ this.getAdministrator() + "','"
				+ this.getLosenord() + "','"
				+ this.getOmrade();
		db.insert("insert into agent values( " + info + ")");
	}

	public void deleteSql() throws InfException
	{
		db.delete("delete from agent where Agent_Id =" + this.getId());
	}

	public void updateNamnSql() throws InfException
	{
		db.update("update agent set NAMN ='" + this.getNamn() + "' where AGENT_ID=" + this.getId());
	}

	public void updateAnstallningsdatumSql() throws InfException
	{
		db.update("update agent set ANSTALLNINGSDATUM ='" + this.getAnstallningsdatum() + "' where AGENT_ID=" + this.getId());
	}

	public void updateAdministratorSql() throws InfException
	{
		db.update("update agent set ADMINISTRATOR ='" + this.getAdministrator() + "' where AGENT_ID=" + this.getId());
	}

	public void updateLosenordSql() throws InfException
	{
		db.update("UPDATE Agent SET LOSENORD = '" + this.losenord + "' where AGENT_ID = " + this.agentId + ";");
	}

	public void updateOmradeSql() throws InfException
	{
		db.update("update agent set OMRADE ='" + this.getOmrade() + "' where AGENT_ID=" + this.getId());
	}

	public void updateTelefonTbl() throws InfException
	{
		db.update("update agent set telefon ='" + getTelefon() + "' where AGENT_ID=" + getId());
	}

	public void updateAllColumnsSql() throws InfException
	{
		updateNamnSql();
		updateAnstallningsdatumSql();
		updateAdministratorSql();
		updateLosenordSql();
		updateOmradeSql();
		updateTelefonTbl();

	}

	public void updateOrInsertSql() throws InfException
	{
		boolean agentExists = !db.fetchColumn("select AGENT_ID from AGENT where AGENT_ID = " + this.getId()).isEmpty();
		if (agentExists) {
			this.updateAllColumnsSql();
		} else {
			this.insertDb();
		}

	}
        
        /**
         * Kollar hur många id som finns i kontorchef
         * @return int hur många som är kontorschef
         */
	public boolean isKontorschef()
	{
		int idCount = 0;
		try {
			idCount = Integer.parseInt(getConnection().fetchSingle("Select COUNT(AGENT_ID) from KONTORSCHEF where agent_id = " + getId()));
		} catch (InfException e) {
			System.out.print("Caught in isKontorschef()\t");
			System.out.println(e);
			idCount = 0;
		}

		return idCount >= 1;

	}
        
        /**
         * Kollar hur många id som finns i områdeschef
         * @return int hur många som är områdeschef
         */
	public boolean isOmradesschef()
	{
		int idCount = 0;
		try {
			idCount = Integer.parseInt(getConnection().fetchSingle("Select COUNT(AGENT_ID) from omradeschef where agent_id = " + getId()));
		} catch (InfException e) {
			System.out.print("Caught in isOmradesschef()\t");
			System.out.println(e);
			idCount = 0;
		}

		return idCount >= 1;
	}

	public boolean isFaltagent()
	{
		int idCount = 0;
		try {
			idCount = Integer.parseInt(getConnection().fetchSingle("Select COUNT(AGENT_ID) from faltagent where agent_id = " + getId()));
		} catch (InfException e) {
			System.out.print("Caught in isFaltagent()\t");
			System.out.println(e);
			idCount = 0;
		}

		return idCount >= 1;
	}

	public static void initConnection(InfDB newDB)
	{
		db = newDB;
	}

	public static InfDB getConnection()
	{
		return db;
	}

	public static boolean checkConnection()
	{
		return getConnection() != null;
	}
        /**
         * Hämtar hela listan på agenter
         * @return Arraylist av agenter
         * @throws InfException 
         */
	public static ArrayList<Agent> getSql() throws InfException
	{
		ArrayList<HashMap<String, String>> agenter = db.fetchRows("Select * from Agent");
		ArrayList<Agent> agentLista = new ArrayList<>();
		agenter.forEach(agent -> {
			agentLista.add(new Agent(agent));
		});
		return agentLista;
	}

	public static Agent getSql(int id) throws InfException
	{
		HashMap<String, String> agent = db.fetchRow("Select * from agent where AGENT_ID=" + id);
		return new Agent(agent);
	}

	public static ArrayList<Agent> getSql(String namn) throws InfException
	{
		ArrayList<HashMap<String, String>> agenter = db.fetchRows("Select * from agent where namn =" + namn);
		ArrayList<Agent> agentLista = new ArrayList<>();
		agenter.forEach(agent -> {
			agentLista.add(new Agent(agent));
		});
		return agentLista;
	}

	public static int uniqueID() throws InfException
	{
		String nextId = getConnection().getAutoIncrement("AGENT", "AGENT_ID");
		return Integer.parseInt(nextId);
	}

	public static boolean idExists(int id)
	{
		boolean idExists = false;
		try {
			idExists = getConnection().fetchColumn("select Agent_id from Agent;").contains("" + id);
		} catch (InfException e) {

			System.out.print("Caught in idExists()\t");
			System.out.println(e);
		}
		return idExists;
	}
        /**
         * Kollar så att namn och lösenord stämmer vid inlogg
         * @param namn
         * @param pwd
         * @return 
         */
	public static boolean loginConfirmation(String namn, String pwd)
	{
		try {
			HashMap<String, String> agent = getConnection().fetchRow("Select * from agent where NAMN = " + namn + " AND LOSENORD = " + pwd);
			if (agent == null) {
				return false;
			}
		} catch (InfException e) {
			System.out.print("Caught in loginConfirmation()\t");
			System.out.println(e);
			return false;
		}
		return true;
	}

}
