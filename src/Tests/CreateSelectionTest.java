package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Controller.CreateSelection;
import Controller.saveGame;
import Model.DBmain;
import Model.Fieldplayer;
import Model.Goalkeeper;
import Model.Team;
import Model.XmlParser;

public class CreateSelectionTest {

	
	Fieldplayer p1 = new Fieldplayer("Frits", "Fritsmans", "RB", 21, 182556, true, 0, 0, 44, 56, 81, 39, 72, 58);
	Fieldplayer p2 = new Fieldplayer("Guus", "Meeuwis", "LW", 35, 19546, true, 0, 0, 33, 86, 16, 46, 71, 22);
	Goalkeeper p3 = new Goalkeeper("Frits", "Fritsmans", "GK", 21, 182556, true, 0, 0, 44, 56, 81, 39, 72, 55, 58);
	Goalkeeper p4 = new Goalkeeper("Guus", "Meeuwis", "GK", 35, 19546, true, 0, 0, 33, 86, 16, 46, 71, 46, 22);
	Fieldplayer p5 = new Fieldplayer("Frits", "Fritsmans", "LB", 21, 182556, true, 0, 0, 44, 56, 81, 39, 72, 58);
	Fieldplayer p6 = new Fieldplayer("Edward", "Stutjes", "ST", 29, 169745, true, 0, 0, 66, 52, 39, 48, 56, 87);
	Fieldplayer p7 = new Fieldplayer("Gijsje", "Truusje", "CDM", 25, 154679, true, 0, 0, 65, 49, 87, 55, 69, 47);
	Fieldplayer p8 = new Fieldplayer("Gijsje", "Truusje", "CB", 25, 154679, true, 1, 0, 65, 49, 87, 55, 69, 47);
	Fieldplayer p9 = new Fieldplayer("Gijsje", "Truusje", "CM", 25, 154679, false, 2, 0, 65, 49, 87, 55, 69, 47);
	Fieldplayer p10 = new Fieldplayer("Gijsje", "Truusje", "CAM", 25, 154679, false, 2, 2, 65, 49, 87, 55, 69, 47);
	Fieldplayer p11 = new Fieldplayer("Gijsje", "Truusje", "RW", 25, 154670, true, 0, 0, 65, 49, 87, 55, 69, 47);
	Fieldplayer p12 = new Fieldplayer("Frits", "Fritsmans", "RB", 21, 182550, true, 0, 0, 44, 56, 81, 39, 72, 58);
	Fieldplayer p13 = new Fieldplayer("Frits", "Fritsmans", "RB", 21, 18256, true, 0, 0, 44, 56, 81, 39, 72, 58);
	Fieldplayer p14 = new Fieldplayer("Frits", "Fritsmans", "RB", 21, 182506, true, 0, 0, 44, 56, 81, 39, 72, 58);
	Fieldplayer p15 = new Fieldplayer("Frits", "Fritsmans", "RB", 21, 18250, true, 0, 0, 44, 56, 81, 39, 72, 58);
	Fieldplayer p16 = new Fieldplayer("Gijsje", "Truusje", "RW", 25, 15467, true, 0, 0, 65, 49, 87, 55, 69, 47);
	Fieldplayer p17 = new Fieldplayer("Gijsje", "Truusje", "RW", 25, 1546, true, 0, 0, 65, 49, 87, 55, 69, 47);
	Fieldplayer p18 = new Fieldplayer("Gijsje", "Truusje", "RW", 25, 154, true, 0, 0, 65, 49, 87, 55, 69, 47);

	Team t1 = new Team("Ajax", 100000, 150000);
	
	@Test
	public void checkSizeTest() {
		t1.addPlayer(p1);
		t1.addPlayer(p2);
		t1.addPlayer(p3);
		t1.addPlayer(p4);
		t1.addPlayer(p5);
		t1.addPlayer(p6);
		t1.addPlayer(p7);
		t1.addPlayer(p8);
		t1.addPlayer(p9);
		t1.addPlayer(p10);
		t1.addPlayer(p11);
		t1.addPlayer(p12);
		t1.addPlayer(p13);
		t1.addPlayer(p14);
		t1.addPlayer(p15);
		t1.addPlayer(p16);
		t1.addPlayer(p17);
		assertFalse(CreateSelection.checkSize(t1));
		t1.addPlayer(p18);
		assertTrue(CreateSelection.checkSize(t1));
	}
	
	@Test
	public void createTest(){
		t1.addPlayer(p1);
		t1.addPlayer(p2);
		t1.addPlayer(p3);
		t1.addPlayer(p4);
		t1.addPlayer(p5);
		t1.addPlayer(p6);
		t1.addPlayer(p7);
		t1.addPlayer(p8);
		t1.addPlayer(p9);
		t1.addPlayer(p10);
		t1.addPlayer(p11);
		t1.addPlayer(p12);
		t1.addPlayer(p13);
		t1.addPlayer(p14);
		t1.addPlayer(p15);
		t1.addPlayer(p16);
		t1.addPlayer(p17);
		t1.addPlayer(p18);
		saveGame.newSave(t1);
		Team t2 = CreateSelection.create(t1);
		assertTrue(t2.getSelection().contains(p3));
		assertFalse(t2.getSelection().contains(p4));
	}

}
