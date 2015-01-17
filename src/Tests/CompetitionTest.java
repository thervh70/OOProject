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

	@Test
	public void testEquals(){

		DBmain db = XmlParser.parseDB();
		Team team = db.getTeam(0);
		Team team2 = db.getTeam(1);
		Match match = new Match(1,team, team2);
		Competition comp = new Competition();
		comp.add(match);
		comp.add(match);
		comp.add(match);
		
		Competition comp2 = new Competition();
		comp2.add(match);
		comp2.add(match);
		comp2.add(match);
		
		Competition comp3 = new Competition();
		comp3.add(match);
		comp3.add(match);
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
		Scanner sc = new Scanner(comp.toString());
		sc.useDelimiter("\n");
		String txt = "";
		while(sc.hasNext()){
			txt +=sc.next();
		}
		assertTrue(txt.equals("Competition===========Match( Day: 1, Home: ADO Den Haag, Away: Ajax)Match( Day: 1, Home: ADO Den Haag, Away: Ajax)Match( Day: 1, Home: ADO Den Haag, Away: Ajax)"));
		assertFalse(txt.equals("Competition===========Match( Match( Day: 1, Home: ADO Den Haag, Away: Ajax)Match( Day: 1, Home: ADO Den Haag, Away: Ajax)"));
	}
	
	@Test
	public void testToWrite(){
		DBmain db = XmlParser.parseDB();
		Team team = db.getTeam(0);
		Team team2 = db.getTeam(1);
		Match match = new Match(1,team, team2);
		Competition comp = new Competition();
		comp.add(match);
		comp.add(match);
		comp.add(match);
		Scanner sc = new Scanner(comp.toWrite());
		sc.useDelimiter("\r\n");
		String txt = "";
		while(sc.hasNext()){
			txt += sc.next();
		}
		assertTrue(txt.equals("<Competition>   <Round>      <Day>1</Day>      <Match>         <Home>ADO Den Haag</Home>         <Away>Ajax</Away>         <Homescore>-1</Homescore>         <Awayscore>-1</Awayscore>      </Match>      <Match>         <Home>ADO Den Haag</Home>         <Away>Ajax</Away>         <Homescore>-1</Homescore>         <Awayscore>-1</Awayscore>      </Match>      <Match>         <Home>ADO Den Haag</Home>         <Away>Ajax</Away>         <Homescore>-1</Homescore>         <Awayscore>-1</Awayscore>      </Match>   </Round>   <Round>      <Day>2</Day>   </Round>   <Round>      <Day>3</Day>   </Round>   <Round>      <Day>4</Day>   </Round>   <Round>      <Day>5</Day>   </Round>   <Round>      <Day>6</Day>   </Round>   <Round>      <Day>7</Day>   </Round>   <Round>      <Day>8</Day>   </Round>   <Round>      <Day>9</Day>   </Round>   <Round>      <Day>10</Day>   </Round>   <Round>      <Day>11</Day>   </Round>   <Round>      <Day>12</Day>   </Round>   <Round>      <Day>13</Day>   </Round>   <Round>      <Day>14</Day>   </Round>   <Round>      <Day>15</Day>   </Round>   <Round>      <Day>16</Day>   </Round>   <Round>      <Day>17</Day>   </Round>   <Round>      <Day>18</Day>   </Round>   <Round>      <Day>19</Day>   </Round>   <Round>      <Day>20</Day>   </Round>   <Round>      <Day>21</Day>   </Round>   <Round>      <Day>22</Day>   </Round>   <Round>      <Day>23</Day>   </Round>   <Round>      <Day>24</Day>   </Round>   <Round>      <Day>25</Day>   </Round>   <Round>      <Day>26</Day>   </Round>   <Round>      <Day>27</Day>   </Round>   <Round>      <Day>28</Day>   </Round>   <Round>      <Day>29</Day>   </Round>   <Round>      <Day>30</Day>   </Round>   <Round>      <Day>31</Day>   </Round>   <Round>      <Day>32</Day>   </Round>   <Round>      <Day>33</Day>   </Round>   <Round>      <Day>34</Day>   </Round>      <Match>         <Home>ADO Den Haag</Home>         <Away>Ajax</Away>         <Homescore>-1</Homescore>         <Awayscore>-1</Awayscore>      </Match>      <Match>         <Home>ADO Den Haag</Home>         <Away>Ajax</Away>         <Homescore>-1</Homescore>         <Awayscore>-1</Awayscore>      </Match>      <Match>         <Home>ADO Den Haag</Home>         <Away>Ajax</Away>         <Homescore>-1</Homescore>         <Awayscore>-1</Awayscore>      </Match></Competition>"));
		assertFalse(txt.equals("<Competition>   <Round>      <Day>1</Day>      <Match>         <Home>ADO Den Haag</Home>         <Away>Ajax</Away>         <Homescore>-1</Homescore>         <Awayscore>-1</Awayscore>      </Match>      <Match>         <Home>ADO Den Haag</Home>         <Away>Ajax</Away>         <Homescore>-1</Homescore>         <Awayscore>-1</Awayscore>      </Match>      <Match>         <Home>ADO Den Haag</Home>         <Away>Ajax</Away>         <Homescore>-1</Homescore>         <Awayscore>-1</Awayscore>      </Match>   </Round>   <Round>      <Day>2</Day>   </Round>   <Round>      <Day>3</Day>   </Round>   <Round>      <Day>4</Day>   </Round>   <Round>      <Day>5</Day>   </Round>   <Round>      <Day>6</Day>   </Round>   <Round>      <Day>7</Day>   </Round>   <Round>      <Day>8</Day>   </Round>   <Round>      <Day>9</Day>   </Round>   <Round>      <Day>10</Day>   </Round>   <Round>      <Day>11</Day>   </Round>   <Round>      <Day>12</Day>   </Round>   <Round>      <Day>13</Day>   </Round>   <Round>      <Day>14</Day>   </Round>   <Round>      <Day>15</Day>   </Round>   <Round>      <Day>16</Day>   </Round>   <Round>      <Day>17</Day>   </Round>   <Round>      <Day>18</Day>   </Round>   <Round>      <Day>19</Day>   </Round>   <Round>      <Day>20</Day>   </Round>   <Round>      <Day>21</Day>   </Round>   <Round>      <Day>22</Day>   </Round>   <Round>      <Day>23</Day>   </Round>   <Round>      <Day>24</Day>   </Round>   <Round>      <Day>25</Day>   </Round>   <Round>      <Day>26</Day>   </Round>   <Round>      <Day>27</Day>   </Round>   <Round>      <Day>28</Day>   </Round>   <Round>      <Day>29</Day>   </Round>   <Round>      <Day>30</Day>   </Round>   <Round>      <Day>31</Day>   </Round>   <Round>      <Day>32</Day>   </Round>   <Round>      <Day>33</Day>   </Round>   <Round>      <Day>34</Day>   </Round>      <Match>         <Home>ADO Den Haag</Home>         <Away>Ajax</Away>         <Homescore>-1</Homescore>         <Awayscore>-1</Awayscore>      </Match>      </Competition>"));
	}

}
