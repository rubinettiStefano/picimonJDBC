package controller;

import helpers.DbHelper;
import model.Gagno;
import model.Picimon;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LetturaGagniEPicimon
{
	static DbHelper dbh = new DbHelper("picimon_silicon");
	static ArrayList<Gagno> gagni = new ArrayList<>();
	static ArrayList<Picimon> picimonsLiberi = new ArrayList<>();

	public static void main(String[] args) throws SQLException
	{
		init();
 	}

	public static void init()throws SQLException
	{
		ArrayList<Picimon> tuttiPicimons = new ArrayList<>();

		//creo la query per leggere tutti i gagni da db
		String querySelectGagni = "SELECT * FROM gagno";

		ResultSet tabellaGagno = dbh.executeSelect(querySelectGagni);

		while(tabellaGagno.next())
		{
			gagni.add(new Gagno(tabellaGagno));
		}

		String querySelectPicimon = "SELECT * FROM picimon";

		ResultSet tabellaPicimon = dbh.executeSelect(querySelectPicimon);

		while(tabellaPicimon.next())
		{
			tuttiPicimons.add(new Picimon(tabellaPicimon));
		}

		for (int i=0; i<gagni.size(); i++)
		{
			Gagno g = gagni.get(i);
			for(int j=0; j<tuttiPicimons.size(); j++)
			{
				Picimon p = tuttiPicimons.get(j);
				//F.K. = P.K.   chiave esterna figlio = chiave primaria (id) padre
				if(p.id_gagno==g.id)
					p.linkToFather(g);
			}
		}

		for(int i=0; i<tuttiPicimons.size(); i++)
		{
			Picimon p = tuttiPicimons.get(i);
			if(p.id_gagno==0)
				picimonsLiberi.add(p);
		}
	}

















}
