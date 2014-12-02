package Engine;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import Database.DBmain;
import Database.Team; 
import Database.XmlParser;

/**The root of our game.
 * This class calculates the score for a match.
 *  
 * @author Robin
 *
 */

public class gameEngine {
	
	private static int targA = 0;
	private static int targB = 0;
	
	private static int attempts = 0;
	
	/**For now, the main contains the excecution of the attack-method
	 * Each team get's the chance to attack --> the attack method is called
	 * A syso is then displayed with the score
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException 
	 */
	
	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
		
		DBmain d = XmlParser.parseDB();
		Team alpha = d.getT(13);
		Team beta = d.getT(14);
		
		System.out.println(alpha.getNm() + " vs " + beta.getNm());
		System.out.println(alpha.calcAttScore() + "\t" + beta.calcAttScore());
		System.out.println(alpha.calcDefScore() + "\t" + beta.calcDefScore() + "\n");
		
		int psv = 0;
		int cam = 0;
		int gel = 0;
		
		for(int i = 0; i < 1000; i++){
			int A = attack(alpha.calcAttScore(), beta.calcDefScore());
			targA = attempts;
		//	System.out.println("Attempts PSV: " + targA);
			int B = attack(beta.calcAttScore(), alpha.calcDefScore());
			targB = attempts;
		//	System.out.println("Attempts Cambuur: " + targB);
			
			if(A>B) psv++;
			else if(B>A) cam++;
			else if(B==A) gel++;
		}
		
		System.out.println(alpha.getNm()+ ": " + psv);
		System.out.println(beta.getNm()+ ": " + cam);
		System.out.println("Gelijk: " + gel);
		
		/*
		if(A>B){
			System.out.print(alpha.getNm() + " won: ");
		}
		else if(B>A){
			System.out.print(beta.getNm() + " won: ");
		}
		else{
			System.out.print("It's a tie: ");
		}
		
		System.out.println(A + "-" + B);
		
		System.out.println("Attempts A: " + targA);
		System.out.println("Attempts B: " + targB);
		*/
	}
	
	/**An attack needs 2 values: 1 attacking (Team A) and 1 defending (Team B)
	 * Int d is calculated first. This value represents the amount of goal attempts.
	 * The for loop is initiated, with the amount of attempts as max.
	 * An attacking and a defending score is calculated.
	 * This happens randomly, with the teamscore as a basis.
	 * If the attacking team is lucky, they score the highest.
	 * 
	 * Within the attacking mechanism is a -0.3, this is a numerical correction.
	 * That way the program is able to reproduce reliable scores.
	 * 
	 * @param att Attacking score of team A
	 * @param def Defending score of team B
	 * @return The amount of goals scored by attacking team
	 */
	
	public static int attack(int att, int def){
		double a = 0,b = 0;
		int c = 0;
		
		attempts = (int) Math.round((Math.random()*(att/5))+1);
		
		for(int i = 0; i < attempts; i++){
			a = att*(Math.random()-0.3);
			b = def*(Math.random());
			
			if(a>b){ 
				c++;
			}		
		}
		
		return c;
	}
}
