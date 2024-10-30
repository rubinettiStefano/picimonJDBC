package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Picimon
{
	public int id,id_gagno;
	public String name,type;
	public int level,hp,attack,defense;

	public Gagno owner;

	public Picimon(){}

	//Costruttore che prende in ingresso una riga del db (result set)
	//e la usa per creare un oggetto Picimon, estraendone i campi uno ad uno
	//e assegnandoli alle proprieta'
	public Picimon(ResultSet dbRow) throws SQLException
	{
		id 		= dbRow.getInt("id");
		name 	= dbRow.getString("name");
		type 	= dbRow.getString("type");
		hp 		= dbRow.getInt("hp");
		level 	= dbRow.getInt("level");
		attack 	= dbRow.getInt("attack");
		defense = dbRow.getInt("defense");
		id_gagno = dbRow.getInt("id_gagno");
	}

	public Picimon(int id, String name, String type, int level, int hp, int attack, int defense)
	{
		this.id = id;
		this.name = name;
		this.type = type;
		this.level = level;
		this.hp = hp;
		this.attack = attack;
		this.defense = defense;
	}

	public Picimon(String name, String type, int level, int hp, int attack, int defense)
	{
		this.name = name;
		this.type = type;
		this.level = level;
		this.hp = hp;
		this.attack = attack;
		this.defense = defense;
	}


	public void linkToFather(Gagno father)
	{
		owner = father;//tu sei mio padre
		father.squad.add(this);//io sono tuo figlio
		id_gagno = father.id;
	}

	public String toString()
	{
		return
				"id:" + id + "\n" +
						"name:" + name + "\n" +
						"type:" + type + "\n" +
						"level:" + level + "\n" +
						"hp:" + hp + "\n" +
						"attack:" + attack + "\n" +
						"defense:" + defense + "\n" +
						"";
	}

	//un oggetto dovra' essere in grado di GENERARE le proprie query
	//di inserimento,modifica e cancellazione

	public String generateInsertQuery()
	{
		StringBuilder builder = new StringBuilder();
		//append significa "mettere in coda"
		builder.append("INSERT INTO picimon(name,type,level,hp,attack,defense)VALUES(");
		builder.append("'"+name+"',");
		builder.append("'"+type+"',");
		builder.append(level+",");
		builder.append(hp+",");
		builder.append(attack+",");
		builder.append(defense);
		builder.append(");");

		return builder.toString();
	}


	public String generateUpdateQuery()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE picimon SET ");
		builder.append("name='"+name+"',");
		builder.append("type='"+type+"',");
		builder.append("level="+level+",");
		builder.append("hp="+hp+",");
		builder.append("attack="+attack+",");
		builder.append("defense="+defense);
		builder.append(" WHERE id="+id);
		builder.append(" ;");

		return builder.toString();
	}

	public String generateDeleteQuery()
	{
		return "DELETE FROM picimon WHERE id="+id+";";
	}


	public int totalStats()
	{
		return hp+defense+attack;
	}
}
