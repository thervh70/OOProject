package Tests;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Test;

import Model.Competition;
import Model.DBmain;
import Model.Match;
import Model.Team;
import Model.XmlParser;

public class CompetitionTest {
	
	String write = "   <Competition>\r\n"
			+ "      <Round>\r\n"
			+ "         <Day>1</Day>\r\n"
			+ "         <Match>\r\n"
			+ "            <Home>ADO Den Haag</Home>\r\n"
			+ "            <Away>Ajax</Away>\r\n"
			+ "            <Homescore>-1</Homescore>\r\n"
			+ "            <Awayscore>-1</Awayscore>\r\n"
			+ "         </Match>\r\n"
			+ "      </Round>\r\n"
			+ "      <Round>\r\n"
			+ "         <Day>2</Day>\r\n"
			+ "      </Round>\r\n"
			+ "      <Round>\r\n"
			+ "         <Day>3</Day>\r\n"
			+ "      </Round>\r\n"
			+ "      <Round>\r\n"
			+ "         <Day>4</Day>\r\n"
			+ "      </Round>\r\n"
			+ "      <Round>\r\n"
			+ "         <Day>5</Day>\r\n"
			+ "      </Round>\r\n"
			+ "      <Round>\r\n"
			+ "         <Day>6</Day>\r\n"
			+ "      </Round>\r\n"
			+ "      <Round>\r\n"
			+ "         <Day>7</Day>\r\n"
			+ "      </Round>\r\n"
			+ "      <Round>\r\n"
			+ "         <Day>8</Day>\r\n"
			+ "      </Round>\r\n"
			+ "      <Round>\r\n"
			+ "         <Day>9</Day>\r\n"
			+ "      </Round>\r\n"
			+ "      <Round>\r\n"
			+ "         <Day>10</Day>\r\n"
			+ "      </Round>\r\n"
			+ "      <Round>\r\n"
			+ "         <Day>11</Day>\r\n"
			+ "      </Round>\r\n"
			+ "      <Round>\r\n"
			+ "         <Day>12</Day>\r\n"
			+ "      </Round>\r\n"
			+ "      <Round>\r\n"
			+ "         <Day>13</Day>\r\n"
			+ "      </Round>\r\n"
			+ "      <Round>\r\n"
			+ "         <Day>14</Day>\r\n"
			+ "      </Round>\r\n"
			+ "      <Round>\r\n"
			+ "         <Day>15</Day>\r\n"
			+ "      </Round>\r\n"
			+ "      <Round>\r\n"
			+ "         <Day>16</Day>\r\n"
			+ "      </Round>\r\n"
			+ "      <Round>\r\n"
			+ "         <Day>17</Day>\r\n"
			+ "      </Round>\r\n"
			+ "      <Round>\r\n"
			+ "         <Day>18</Day>\r\n"
			+ "      </Round>\r\n"
			+ "      <Round>\r\n"
			+ "         <Day>19</Day>\r\n"
			+ "      </Round>\r\n"
			+ "      <Round>\r\n"
			+ "         <Day>20</Day>\r\n"
			+ "      </Round>\r\n"
			+ "      <Round>\r\n"
			+ "         <Day>21</Day>\r\n"
			+ "      </Round>\r\n"
			+ "      <Round>\r\n"
			+ "         <Day>22</Day>\r\n"
			+ "      </Round>\r\n"
			+ "      <Round>\r\n"
			+ "         <Day>23</Day>\r\n"
			+ "      </Round>\r\n"
			+ "      <Round>\r\n"
			+ "         <Day>24</Day>\r\n"
			+ "      </Round>\r\n"
			+ "      <Round>\r\n"
			+ "         <Day>25</Day>\r\n"
			+ "      </Round>\r\n"
			+ "      <Round>\r\n"
			+ "         <Day>26</Day>\r\n"
			+ "      </Round>\r\n"
			+ "      <Round>\r\n"
			+ "         <Day>27</Day>\r\n"
			+ "      </Round>\r\n"
			+ "      <Round>\r\n"
			+ "         <Day>28</Day>\r\n"
			+ "      </Round>\r\n"
			+ "      <Round>\r\n"
			+ "         <Day>29</Day>\r\n"
			+ "      </Round>\r\n"
			+ "      <Round>\r\n"
			+ "         <Day>30</Day>\r\n"
			+ "      </Round>\r\n"
			+ "      <Round>\r\n"
			+ "         <Day>31</Day>\r\n"
			+ "      </Round>\r\n"
			+ "      <Round>\r\n"
			+ "         <Day>32</Day>\r\n"
			+ "      </Round>\r\n"
			+ "      <Round>\r\n"
			+ "         <Day>33</Day>\r\n"
			+ "      </Round>\r\n"
			+ "   </Competition>\r\n";


	@Test
	public void testAdd(){
		DBmain db = XmlParser.parseDB();
		Team team = db.getTeam(0);
		Team team2 = db.getTeam(1);
		Match match = new Match(1,team, team2);
		Competition comp = new Competition();
		Competition comp2 = new Competition();
		Competition comp3 = new Competition();
		comp.add(match);
		comp2.add(match);
		comp2.add(match);
		assertFalse(comp.equals(comp3));
		assertTrue(comp.equals(comp2));
	}
	
	@Test
	public void testRemove(){
		DBmain db = XmlParser.parseDB();
		Team team = db.getTeam(0);
		Team team2 = db.getTeam(1);
		Match match = new Match(1,team, team2);
		Match match2 = new Match(2,team, team2);
		Competition comp = new Competition();
		Competition comp2 = new Competition();
		Competition comp3 = new Competition();
		comp.add(match);
		comp2.add(match);
		comp2.add(match2);
		comp2.remove(match2);
		assertTrue(comp.equals(comp2));
	}
	
	@Test
	public void testEquals(){

		DBmain db = XmlParser.parseDB();
		Team team = db.getTeam(0);
		Team team2 = db.getTeam(1);
		Match match = new Match(1,team, team2);
		Match match2 = new Match(2,team, team2);
		Match match3 = new Match(3,team, team2);
		Competition comp = new Competition();
		comp.add(match);
		comp.add(match2);
		comp.add(match3);
		
		Competition comp2 = new Competition();
		comp2.add(match);
		comp2.add(match2);
		comp2.add(match3);
		
		Competition comp3 = new Competition();
		comp3.add(match);
		comp3.add(match2);
		
		assertTrue(comp.equals(comp2));
		assertFalse(comp.equals(comp3));
		assertFalse(comp.equals(team));
	}
	
	@Test
	public void testToString() {
		DBmain db = XmlParser.parseDB();
		Team team = db.getTeam(0);
		Team team2 = db.getTeam(1);
		Match match = new Match(1,team, team2);
		Competition comp = new Competition();
		comp.add(match);
		comp.add(match);
		comp.add(match);
		String txt = comp.toString();
		assertEquals(txt, "Competition\n===========\nMatch( Day: 1, Home: ADO Den Haag 0, Away: Ajax 0)\n");
		assertNotEquals(txt, "   Competition===========");
	}
	
	@Test
	public void testToWrite(){
		DBmain db = XmlParser.parseDB();
		Team team = db.getTeam(0);
		Team team2 = db.getTeam(1);
		Match match = new Match(1,team, team2, -1, -1);
		Competition comp = new Competition();
		comp.add(match);
		comp.add(match);
		comp.add(match);
		String txt = comp.toWrite();
		assertEquals(txt, write);
		assertNotEquals(txt, "   " + write);
	}
	
	@Test
	public void setMatchesForDayTest() {
		
	}

}
