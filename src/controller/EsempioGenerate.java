package controller;

import helpers.DbHelper;
import model.Picimon;

public class EsempioGenerate
{

	static DbHelper dbHelper = new DbHelper("picimon_silicon");
	public static void main(String[] args)
	{
		Picimon p = new Picimon("Cerea","saluto",10,40,2,100);

		System.out.println(p.generateInsertQuery());
	}
}
