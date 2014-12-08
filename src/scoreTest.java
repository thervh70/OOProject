import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import Database.DBmain;
import Database.Team; 
import Database.XmlParser;

public class scoreTest {

	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
		DBmain d = XmlParser.parseDB();
		
		for(int i = 0; i < 18; i++){
			Team t = d.getTeam(i);
			int att = (int) t.calcAttScore();
			double mapAtt = map(att,50,70,0,100);
			int def = (int) t.calcDefScore();
			double mapDef = map(def,50,70,0,100);
			System.out.println(att + "\t" + mapAtt + "\t" + def + "\t" + mapDef + "\t" + t.getNm());
		}
	}
	
	public static double map(double x, double in_min, double in_max, double out_min, double out_max)
	{
	  return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
	}
}
