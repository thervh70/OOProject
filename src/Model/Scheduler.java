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

public class Scheduler {

	public static void main(String[] args){
		List<Team> TeamOrder = new ArrayList<Team>();
		int number;
		DBmain d = XmlParser.parseDB();
		Competition comp = new Competition();
		for (int i = 0; i < 17; i++) {
			number = (int) Math.round((Math.random()* 18- 0.5));
			while(TeamOrder.contains(d.getTeam(number))){
				number = (int) Math.round((Math.random()* 18- 0.5));
			}
			TeamOrder.add(d.getTeam(number));
		}
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			Document document = builder.parse("src/Model/Resources/Schedule.xml");
			NodeList division = document.getDocumentElement().getChildNodes();
			for(int i=1;i<division.getLength();i+=2) {
		    	Node team = division.item(i);
		    	NodeList matchattrs = team.getChildNodes();
		    	Match match = new Match(Integer.parseInt(matchattrs.item(1).getTextContent()),Integer.parseInt(matchattrs.item(3).getTextContent()),Integer.parseInt(matchattrs.item(5).getTextContent()));
		    	comp.add(match);
		    	
		    }
		} 
		catch (ParserConfigurationException | IOException | SAXException e) {
			e.printStackTrace();
		} 
		  
		for (int i = 0; i < comp.getSize(); i++) {
			for (int j = 0; j < 17; j++) {
				Match match =comp.get(i);
				if(match.getHomeIndex() == j){
					match.setTeamHome(TeamOrder.get(j));
				}
				if(match.getAwayIndex() == j){
					match.setTeamAway(TeamOrder.get(j));
				}
				System.out.println(i);			}
		}
		System.out.println(comp.toString());
		
	}
}
