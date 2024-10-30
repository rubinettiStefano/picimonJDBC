package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Gagno
{
	public int id,credits,age;
	public String name,surname;

	public ArrayList<Picimon> squad = new ArrayList<>();

	public Gagno(){}

	public Gagno(ResultSet dbRow) throws SQLException
	{
		id 			= dbRow.getInt("id");
		name 		= dbRow.getString("name");
		surname 	= dbRow.getString("surname");
		credits 	= dbRow.getInt("credits");
		age 		= dbRow.getInt("age");
	}

	public int averageSquadLevel()
	{
		int sum = 0;
		for(int i = 0; i < squad.size(); i++)
			sum+= squad.get(i).level;

		return sum/squad.size();
	}

	public Picimon	strongestPicimon()
	{
		Picimon localMax = squad.get(0);

		for(int i = 1; i < squad.size(); i++)
			if(squad.get(i).totalStats() >localMax.totalStats())
				localMax = squad.get(i);

		return localMax;
	}

	public int creditsToGiveWhenLosing()
	{
		//       200       10               100
		//       2000/100 -> 20
		return credits*averageSquadLevel()/100;
		// 10                 /100 * 200
		//10/100 -> 0 *200 -> 0
		//averageSquadLevel()/100 * credits
	}
 }
