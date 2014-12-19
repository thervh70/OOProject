import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import Model.DBmain;
import Model.Team;
import Model.XmlParser;

public class scoreTest {

	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
		DBmain d = XmlParser.parseDB();
		
		System.out.println("Att\tDef\tName");
		for(int i = 0; i < 18; i++){
			Team t = d.getTeam(i);
			int att = (int) t.calcAttScore();
			int def = (int) t.calcDefScore();
			System.out.println(att + "\t" + def + "\t" + t.getNm());
		}
		
		System.out.println("\n\nType\n0\t1\t2\t3\t4\t5\tName");
		for(int i = 0; i < 18; i++){
			Team t = d.getTeam(i);
			System.out.println(t.countSelection(0) + "\t" + t.countSelection(1) + "\t" + t.countSelection(2) + "\t" + t.countSelection(3) + "\t" + t.countSelection(4) + "\t" + t.countSelection(5) + "\t" + t.getNm());
		}
	}
	
	public static double map(double x, double in_min, double in_max, double out_min, double out_max)
	{
	  return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
	}
}
