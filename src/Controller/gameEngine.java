package Controller;

import java.io.IOException;
import java.util.Arrays;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import Model.DBmain;
import Model.Team;
import Model.XmlParser;

/**The root of our game.
 * This class calculates the score for a match along with the stats of a match.
 *  
 * @author Robin
 *
 */

public class gameEngine {
	
	private int attemptsA, attemptsB, goalsA, goalsB, attempts, goals;
	private int[] goalminutesA, goalminutesB, attemptminutesA, attemptminutesB;
	
	/**For now, the main contains the excecution of the attack-method
	 * Each team get's the chance to attack --> the attack method is called
	 * A syso is then displayed with the score
	 * 
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException 
	 */
	
	public static void main(String[] args){
		DBmain d = XmlParser.parseDB();
		Team alpha = d.getTeam(13);
		Team beta = d.getTeam(14);
		gameEngine match = new gameEngine();
		
		match.play(alpha,beta);
		System.out.println(alpha.getNm() + " " + match.getGoalsA() + " - " + match.getGoalsB() + " " + beta.getNm());
		System.out.println("\n\nGoals " + alpha.getNm() + ": " + match.getGoalsA());
		
		for(int h = 0; h<match.getGoalsA(); h++){
			System.out.print(match.getGoalminutesA()[h] + "   ");
		}
		
		System.out.println("\n\nGoals " + beta.getNm() + ": " + match.getGoalsB());
		
		for(int i = 0; i<match.getGoalsB(); i++){
			System.out.print(match.getGoalminutesB()[i] + "   ");
		}
		
		System.out.println("\n\nAttempts " + alpha.getNm() + ": " + match.getAttemptsA());
		
		for(int j = 0; j<match.getAttemptsA(); j++){
			System.out.print(match.getAttemptminutesA()[j] + "  ");
		}
		
		System.out.println("\n\nAttempts " + beta.getNm() + ": " + match.getAttemptsB());
		
		for(int k = 0; k<match.getAttemptsB(); k++){
			System.out.print(match.getAttemptminutesB()[k] + "  ");
		}
	}
	
	public gameEngine(){
		
	}
	
	/**This method plays a match.
	 * All stats are stored into static values within this class
	 * 
	 * @param alpha Team A
	 * @param beta Team B
	 */
	
	public void play(Team alpha, Team beta){
		double alphaAttMap =  map(alpha.calcAttScore(),0,40,0,100);
		double betaAttMap = map(beta.calcAttScore(),0,40,0,100);
		
		double alphaDefMap = map(alpha.calcDefScore(),0,40,0,100);
		double betaDefMap = map(alpha.calcDefScore(),0,40,0,100);
		
		goalsA = attack(alphaAttMap, betaDefMap);
		attemptsA = attempts;
		attemptminutesA = minutes(attemptsA);
		goalminutesA = minutes(goals);
		Arrays.sort(attemptminutesA);
		Arrays.sort(goalminutesA);
		
		goalsB = attack(betaAttMap, alphaDefMap);
		attemptsB = attempts;
		attemptminutesB = minutes(attemptsB);		
		goalminutesB = minutes(goals);
		Arrays.sort(attemptminutesB);
		Arrays.sort(goalminutesB);
	}
	
	/**An attack needs 2 values: 1 attacking (Team A) and 1 defending (Team B)
	 * Int d is calculated first. This value represents the amount of goal attempts.
	 * The for loop is initiated, with the amount of attempts as max.
	 * An attacking and a defending score is calculated.
	 * This happens randomly, with the teamscore as a basis.
	 * If the attacking team is lucky, they score the highest.
	 * 
	 * Within the attacking mechanism is a -0.2, this is a numerical correction.
	 * That way the program is able to reproduce reliable scores.
	 * 
	 * @param att Attacking score of team A
	 * @param def Defending score of team B
	 * @return The amount of goals scored by attacking team
	 */
	
	public int attack(double att, double def){
		double a = 0,b = 0;
		int c = 0;
		
		attempts = (int) Math.round((Math.random()*(att/6)+1));
		
		for(int i = 0; i < attempts; i++){
			a = att*(Math.random()-.2); //correction ensures not too much goals are made
			b = def*(Math.random());
			
			if(a>b){ 
				c++;
			}		
		}
		goals += c;
		return c;
	}
	
	/**Method to determine the minute an action was made
	 * 
	 * @param c Amount of things
	 * @return array with integers (minutes)
	 */
	
	public int[] minutes(int c){
		int[] minutes = new int[c];
		
		for(int i = 0; i < c; i++){
			minutes[i]=(int)Math.round(Math.random()*90);
		}
		
		return minutes;
	}
	
	/**This method enlarges the difference between 2 teams.
	 * That way we are able to make realistic scores.
	 * 
	 * @param x The number to be mapped
	 * @param in_min input minimum
	 * @param in_max input maximum
	 * @param out_min output maximum
	 * @param out_max output maximum
	 * @return
	 */
	
	private static double map(double x, double in_min, double in_max, int out_min, int out_max) {
		return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
	}
	
	
	public int getAttemptsA(){ return attemptsA; }
	public int getAttemptsB(){ return attemptsB; }
	public int getGoalsA(){ return goalsA; }
	public int getGoalsB(){ return goalsB; }
	public int[] getGoalminutesA(){ return goalminutesA; }
	public int[] getGoalminutesB(){ return goalminutesB; }
	public int[] getAttemptminutesA(){ return attemptminutesA; }
	public int[] getAttemptminutesB(){ return attemptminutesB; }
}
