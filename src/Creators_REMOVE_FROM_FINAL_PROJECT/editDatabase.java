package Creators_REMOVE_FROM_FINAL_PROJECT;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import Model.Goalkeeper;
import Model.Team;
import Model.XmlParser;

public class editDatabase {
	
	public static Team parseKeeper() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		System.out.println("Teamnaam:");
		String filename = "Willem II";
		Team t = new Team(filename, 100000, 100000);
		try {
			builder = factory.newDocumentBuilder(); 
			Document document = builder.parse("src/Creators_REMOVE_FROM_FINAL_PROJECT/keepers/" + filename + ".xml");   
			NodeList keepers = document.getDocumentElement().getChildNodes();
			for(int i=3;i<keepers.getLength();i+=2) {
				Node keeper = keepers.item(i);
		    	NodeList keeperattr = keeper.getChildNodes();
		    	Goalkeeper g = XmlParser.parseKeeper(keeperattr);
		    	t.addPlayer(g);
			}
		}
		catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		String write = "<KEEPERS>\r\n";
		for(int i=0;i<t.getSize();i++) {
			if(t.getPlayer(i) instanceof Goalkeeper) {
				Goalkeeper keeper = (Goalkeeper)(t.getPlayer(i));
				write += keeper.toWrite();
			}
		}
		write += "</KEEPERS>\r\n";
		System.out.println(write);
		try {
			PrintWriter writer = new PrintWriter("src/Creators_REMOVE_FROM_FINAL_PROJECT/keepers/" + filename + "Goed.xml");
			writer.println(write);
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return t;
	}
	
	public static Team createSelection(Team t) {
		t.toSelection(t.getPlayer(0));
		for(int i=3;i<13;i++) {
			t.toSelection(t.getPlayer(i));
		}
		return t;
	}

}
