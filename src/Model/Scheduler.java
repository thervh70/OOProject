package Model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import Controller.saveGame;

public class Scheduler {

	
	
	/**
	 * Generate a randomly generated competition schedule
	 * @return 
	 */
	public static Competition generate(){
		List<Team> TeamOrder = new ArrayList<Team>();
		int number;
		//To be fixed!!
		DBmain d = XmlParser.parseDB();
		//!!!!
		
		Competition comp = new Competition();
		
		//add all teams in a random order to an ordered list
		for (int i = 0; i < 18; i++) {
			number = (int) Math.round((Math.random()* 18- 0.5));
			while(TeamOrder.contains(d.getTeam(number))){
				number = (int) Math.round((Math.random()* 18- 0.5));
			}
			TeamOrder.add(d.getTeam(number));
			//System.out.println(TeamOrder.size());
		}
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		
		//read out the template schedule and assign the randomly ordered teams to a certain index
		try {
			builder = factory.newDocumentBuilder();
			Document document = builder.parse("src/Model/Resources/Schedule.xml");
			NodeList division = document.getDocumentElement().getChildNodes();
			for(int i=1;i<division.getLength();i+=2) {
		    	Node team = division.item(i);
		    	NodeList matchattrs = team.getChildNodes();
		    	int day = Integer.parseInt(matchattrs.item(1).getTextContent());
		    	int homeIndex = Integer.parseInt(matchattrs.item(3).getTextContent());
		    	int awayIndex = Integer.parseInt(matchattrs.item(5).getTextContent());
		    	//Match match = new Match(Integer.parseInt(matchattrs.item(1).getTextContent()),Integer.parseInt(matchattrs.item(3).getTextContent()),Integer.parseInt(matchattrs.item(5).getTextContent()));
		    	Match match = new Match(day,TeamOrder.get(homeIndex),TeamOrder.get(awayIndex));
		    	//System.out.println(day);
		    	//System.out.println(homeIndex);
		    	//System.out.println(awayIndex);
		    	comp.add(match);
		    	
		    }
		} 
		catch (ParserConfigurationException | IOException | SAXException e) {
			e.printStackTrace();
		} 
		return comp;
		
	}
}
