package controller;

import helpers.DbHelper;
import model.Picimon;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class PicimonManagement
{
	static DbHelper dbh 				= new DbHelper("picimon_silicon");
	static Scanner sc 					= new Scanner(System.in);
	static ArrayList<Picimon> picimons;

	public static void main(String[] args)throws SQLException
	{
		refreshListFromDb();
		String cmd="";//comando dato da utente
		boolean goOn = false;//condizione di ripetizione del ciclo

		do
		{
			System.out.println("Inserisci comando");
			cmd = sc.nextLine().toLowerCase();

			//C.R.U.D.
			//Create -> Insert
			//Read   -> Select
			//Update
			//Delete

			switch (cmd)
			{
				case "create":
					create();
					break;
				case "read":
					read();
					break;
				case "update":
					update();
					break;
				case "delete":
					delete();
					break;
//				case "readbytype":
//					readbytype();//chiede a utente un tipo, stampate solo picimon di quel tipo (lavoro solo sull' arraylist)
//					break;
//				case "levelup":
//					levelup();//chiedete a utente un id di un picimon, aumentate il suo livello di 1, tutte le sue statistiche del 5% (per aumento fate stat * 105 /100)
//					break;
//				case "deletebyname":
//					deletebyname();//chiedete ad utente un nome, cancellate il picimon con quel nome (mi piacerebbe che chiamaste il metodo generateDeleteQuery, non fate scrivere voi la query
//					break;
				case "quit":
					System.out.println("Arrivederci");
					break;
				default:
					System.out.println("Comando non valido");
			}

			goOn = !cmd.equals("quit");
		}while (goOn);
		sc.close();

	}

	//chiedere utente dati, inserire picimon nel db
	public static void create() throws SQLException
	{
		Picimon p = new Picimon();
		System.out.println("Inserisci nome");
		p.name = sc.nextLine();
		System.out.println("Inserisci tipo");
		p.type = sc.nextLine();
		System.out.println("Inserisci livello");
		p.level = Integer.parseInt(sc.nextLine());
		System.out.println("Inserisci hp");
		p.hp = Integer.parseInt(sc.nextLine());
		System.out.println("Inserisci attack");
		p.attack = Integer.parseInt(sc.nextLine());
		System.out.println("Inserisci defense");
		p.defense = Integer.parseInt(sc.nextLine());

		String insertQuery = p.generateInsertQuery();
		dbh.executeDML(insertQuery);
		refreshListFromDb();
		System.out.println("Picimon Inserito");
	}


	public static void read()
	{
		for(int i=0;i<picimons.size();i++)
		{
			System.out.println(picimons.get(i));
		}
	}

	public static void update() throws SQLException
	{
		System.out.println("HEI, dammi id del picimon da cancellare");
		int id = Integer.parseInt(sc.nextLine());

		Picimon toUpdate = extractOne(id);

		if(toUpdate==null)
		{
			System.out.println("Picimon non trovato");
			return;//un return; nei metodi void chiude il metodo
		}

		String resp;

		System.out.println("Questo e` il nome: "+toUpdate.name+", digita quello nuovo o lascia vuoto per non cambiarlo");
		resp = sc.nextLine();
		if(!resp.equals(""))
			toUpdate.name = resp;

		System.out.println("Questo e` il tipo: "+toUpdate.type+", digita quello nuovo o lascia vuoto per non cambiarlo");
		resp = sc.nextLine();
		if(!resp.equals(""))
			toUpdate.type = resp;

		System.out.println("Questo e` il livello: "+toUpdate.level+", digita quello nuovo o lascia vuoto per non cambiarlo");
		resp = sc.nextLine();
		if(!resp.equals(""))
			toUpdate.level = Integer.parseInt(resp);

		System.out.println("Questo sono gli hp: "+toUpdate.hp+", digita quello nuovo o lascia vuoto per non cambiarlo");
		resp = sc.nextLine();
		if(!resp.equals(""))
			toUpdate.hp = Integer.parseInt(resp);

		System.out.println("Questo e` l' attacco: "+toUpdate.attack+", digita quello nuovo o lascia vuoto per non cambiarlo");
		resp = sc.nextLine();
		if(!resp.equals(""))
			toUpdate.attack = Integer.parseInt(resp);

		System.out.println("Questo e` la difesa: "+toUpdate.defense+", digita quello nuovo o lascia vuoto per non cambiarlo");
		resp = sc.nextLine();
		if(!resp.equals(""))
			toUpdate.defense = Integer.parseInt(resp);

		System.out.println("Ecco la versione finale modificata? sicuro sicuro di voler applicare i cambiamenti(Y per modificare)");
		System.out.println(toUpdate);

		if(sc.nextLine().equalsIgnoreCase("Y"))
		{
			dbh.executeDML(toUpdate.generateUpdateQuery());
			refreshListFromDb();
			System.out.println("Modifica avvenuta con successo");
		}
		else
			System.out.println("Modifica annullata");
	}

	public static void delete() throws SQLException
	{
		System.out.println("HEI, dammi id del picimon da cancellare");
		int id = Integer.parseInt(sc.nextLine());

		Picimon toDelete = extractOne(id);

		if(toDelete==null)
		{
			System.out.println("Picimon non trovato");
			return;//un return; nei metodi void chiude il metodo
		}

		System.out.println("Vuoi cancellare questo? sicuro sicuro(Y per cancellare)");
		System.out.println(toDelete);
		if(sc.nextLine().equalsIgnoreCase("Y"))
		{
			dbh.executeDML(toDelete.generateDeleteQuery());
			refreshListFromDb();
			System.out.println("Cancellazione avvenuta con successo");
		}
		else
			System.out.println("Cancellazione annullata");
	}

	public static Picimon extractOne(int id)
	{
		Picimon res =null;
		for(int i=0;i<picimons.size();i++)
			if(picimons.get(i).id == id)
				res = picimons.get(i);

		return res;
	}


	//Andra' a leggere tutte le righe della tabella picimon, convertendole, le inserira nella lista
	//verra chiamato piu' volte
	public static void refreshListFromDb() throws SQLException
	{
		picimons = new ArrayList<>();

		ResultSet rs = dbh.executeSelect("SELECT * FROM picimon");

		while(rs.next())
			picimons.add(new Picimon(rs));
	}

}
