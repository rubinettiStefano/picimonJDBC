package controller;

import model.Gagno;
import model.Picimon;

import java.util.ArrayList;

public class EsempioGagni
{
	static ArrayList<Gagno> gagni = new ArrayList<>();

	public static void main(String[] args)
	{
		Gagno g1 = new Gagno();
		g1.name = "Roberto";
		g1.surname = "Rubinetti";
		g1.age = 10;
		g1.credits = 100;

		Picimon p1 = new Picimon();
		p1.name = "Babaciu";
		p1.level = 2;
		p1.type = "bambola";
		p1.attack = 12;
		p1.defense = 15;
		p1.hp = 20;

//		 new Picimon("Babaciu","bambola",2,20,12,15);


		//collego collego il picimon al gagno
		g1.squad.add(p1);
		//collego il gagno al picimon
		p1.owner = g1;

		Picimon p2 = new Picimon();
		p2.name = "SuperTele";
		p2.level = 4;
		p2.type = "palla";
		p2.attack = 8;
		p2.defense = 7;
		p2.hp = 5;

		p2.linkToFather(g1);


		System.out.println("Gagno quale e la media del tuo team? "+g1.averageSquadLevel());
		System.out.println("Gagno quale e il tuo picimon piu forte? "+g1.strongestPicimon());

		System.out.println("Babaciu, qual è il nome del tuo allenatore?"+p1.owner.name);
		System.out.println("Babaciu, qual è la media del tuo team?"+p1.owner.averageSquadLevel());

		Gagno g2 = new Gagno();
		g2.name = "Luigi";
		g2.surname = "Martelli";
		g2.age = 12;
		g2.credits = 150;

		Picimon p3 = new Picimon();
		p3.name = "Fiammetta";
		p3.level = 3;
		p3.type = "drago";
		p3.attack = 18;
		p3.defense = 10;
		p3.hp = 25;


		//this -> oggetto su cui un metodo viene chiamato/invocato/eseguito
		//cio che c'e` SUBITO prima del punto
		g2.squad.add(p3);
		p3.owner = g2;

		Picimon p4 = new Picimon();
		p4.name = "Zampino";
		p4.level = 5;
		p4.type = "gatto";
		p4.attack = 15;
		p4.defense = 12;
		p4.hp = 30;

		g2.squad.add(p4);
		p4.owner = g2;

		gagni.add(g1);
		gagni.add(g2);

		//partendo dalla lista di gagni, come stampo il livello di SuperTele?
		int livelloSupertele = gagni.get(0).squad.get(1).level;

		Gagno gg =gagni.get(1).strongestPicimon().owner.squad.get(1).owner;

		//              =  gagni
		//Metodi comuni ArrayList
		//size()   -> conto quanti elementi ho nella lista
		//get(pos) -> prende un singolo elemento in base al suo indice
		//QUALE è il tipo di ritorno di get chiamato su gagni?
		//GAGNO
		//add(GAGNO) -> aggiunge un elemento alla lista
		//dato che la lista è di Gagno, dovrà prendere come parametro un Gagno
		//QUALE è il tipo di ritorno di add chiamato su gagni?


		//Come facciamo se non sappiamo nulla tranne che esiste un picimon da qualche parte
		//di nome supertele

//		for(int i=0;i<gagni.size();i++)
//		{
//			ArrayList<Picimon> picimonsDelGagno = gagni.get(i).squad;
//
//			for(int j=0;j<picimonsDelGagno.size();j++)
//				if(picimonsDelGagno.get(j).name.equalsIgnoreCase("supertele"))
//					System.out.println("Il livello di supertele è "+picimonsDelGagno.get(j).level);
//		}

	}
}
