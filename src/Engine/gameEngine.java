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
	private static int goals = 0;
	
	/**For now, the main contains the excecution of the attack-method
	 * Each team get's the chance to attack --> the attack method is called
	 * A syso is then displayed with the score
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException 
	 */
	
	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
		
		DBmain d = XmlParser.parseDB();
		Team alpha = d.getTeam(13);
		Team beta = d.getTeam(14);
		
		System.out.println(alpha.getNm() + " vs " + beta.getNm());
		System.out.println(Math.round(alpha.calcAttScore()) + "\t" + Math.round(beta.calcAttScore()));
		System.out.println(Math.round(alpha.calcDefScore()) + "\t" + Math.round(beta.calcDefScore()) + "\n");
		
		int psv = 0;
		int cam = 0;
		int gel = 0;
		int A = 0;
		int B = 0;
		
		for(int i = 0; i < 1000; i++){
			double alphaAttMap =  map(alpha.calcAttScore(),50,70,0,100);
			double betaAttMap = map(beta.calcAttScore(),50,70,0,100);
			
			double alphaDefMap = map(alpha.calcDefScore(),50,70,0,100);
			double betaDefMap = map(alpha.calcDefScore(),50,70,0,100);
			
			A = attack(alphaAttMap, betaDefMap);
			targA = attempts;
			B = attack(betaAttMap, alphaDefMap);
			targB = attempts;
		
			if(A>B) psv++;
			else if(B>A) cam++;
			else if(B==A) gel++;
			
			System.out.println(targA + "\t" + A + "-" + B + "\t" + targB);
		}
		
		System.out.println("\n");
		System.out.println("Wins psv: " + psv);
		System.out.println("Wins Cambuur: " + cam + "\n");
		System.out.println("Ties: " + gel + "\n");
		System.out.println("Total Goals: " + goals);
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
	
	public static int attack(double att, double def){
		double a = 0,b = 0;
		int c = 0;
		
		attempts = (int) Math.round((Math.random()*(att/6)+1));
		
		for(int i = 0; i < attempts; i++){
			a = att*(Math.random()-.2); //correction ensures not too much goals are made
			b = def*(Math.random()); //correction ensures not too much ties are made
			
			if(a>b){ 
				c++;
			}		
		}
		goals += c;
		return c;
	}
	
	public static double map(double x, double in_min, double in_max, int out_min, int out_max) {
		return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
	}
}
