package controller;

import helpers.DbHelper;
import model.Picimon;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Manipolazionemon
{

	static DbHelper dbHelper = new DbHelper("picimon_silicon");

	public static void main(String[] args) throws SQLException
	{
		ResultSet rs = dbHelper.executeSelect("SELECT * FROM picimon");
		ArrayList<Picimon> picimons = new ArrayList<>();
		while(rs.next())
		{
			picimons.add(new Picimon(rs));
		}


		Picimon cicles = picimons.get(1);
		cicles.defense = 200;

		System.out.println("ora eseguo la query:"+cicles.generateUpdateQuery());
		dbHelper.executeDML(cicles.generateUpdateQuery());

		System.out.println("\n\nFACCIO CAMBIAMENTO\n\n");

		for(int i=0;i<picimons.size();i++)
			System.out.println(picimons.get(i));
	}
}
