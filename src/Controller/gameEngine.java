package Controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

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
	
	private Team teamA, teamB;
	private int attemptsA, attemptsB, goalsA, goalsB, attempts, toto;
	private int[] goalminutesA, goalminutesB, attemptminutesA, attemptminutesB;
	
	private static double amount = 1000;
	private static int a = 1;
	private static int b= 2;
	
	/**For now, the main contains the excecution of the attack-method
	 * Each team get's the chance to attack --> the attack method is called
	 * A syso is then displayed with the score
	 * 
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException 
	 */
	
	@SuppressWarnings("resource")
	public static void main(String[] args){
		int choice;
		double totalAttA = 0, totalAttB = 0, totalGoalA = 0, totalGoalB = 0;
		
		Scanner input = new Scanner(System.in);
		System.out.println("1 game (0) or multiple games (1)?");
		
		choice = input.nextInt();
		if(choice == 0){
			DBmain d = XmlParser.parseDB();
			Team alpha = d.getTeam(a);
			Team beta = d.getTeam(b);
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
		
		else if(choice == 1){
			DBmain d = XmlParser.parseDB();
			Team alpha = d.getTeam(a);
			Team beta = d.getTeam(b);
			gameEngine match = new gameEngine();
			
			int winsA = 0, winsB = 0, ties = 0;
			
			for(int i = 0; i < amount; i++){
				match.play(alpha,beta);
				
				if(match.getGoalsA() > match.getGoalsB()){
					winsA++;
				}
				
				else if(match.getGoalsB() > match.getGoalsA()){
					winsB++;
				}
				
				else if(match.getGoalsA() == match.getGoalsB()){
					ties++;
				}
				
				totalGoalA += match.getGoalsA();
				totalGoalB += match.getGoalsB();
				totalAttA += match.getAttemptsA();
				totalAttB += match.getAttemptsB();
			}
			
			System.out.println("Amount of Wins from " + alpha.getNm() + ": " + winsA);
			System.out.println("Amount of Wins from " + beta.getNm() + ": " + winsB);
			System.out.println("Amount of Ties: " + ties);
			
			System.out.println("\nAverage Goals from " + alpha.getNm() + ": " + (double) (totalGoalA/amount));
			System.out.println("Average Goals from " + beta.getNm() + ": " + (double) (totalGoalB/amount));
			
			System.out.println("\nAverage Attempts from " + alpha.getNm() + ": " + (double) (totalAttA/amount));
			System.out.println("Average Attempts from " + beta.getNm() + ": " + (double) (totalAttB/amount));
			
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
		teamA = alpha;
		teamB = beta;
		
		double alphaAttMap =  map(alpha.calcAttScore(),0,40,0,100);
		double betaAttMap = map(beta.calcAttScore(),0,40,0,100);
		
		double alphaDefMap = map(alpha.calcDefScore(),0,40,0,100);
		double betaDefMap = map(alpha.calcDefScore(),0,40,0,100);
		
		final Set<Integer> availableMin = new HashSet<>(); {
		    for (int i = 0; i <= 90; i++) {
		        availableMin.add(i);
		    }
		}
		
		goalsA = attack(alphaAttMap, betaDefMap);
		attemptsA = attempts;
		attemptminutesA = minutes(attemptsA, availableMin);
		goalminutesA = minutes(goalsA, availableMin);
		Arrays.sort(attemptminutesA);
		Arrays.sort(goalminutesA);
		
		goalsB = attack(betaAttMap, alphaDefMap);
		attemptsB = attempts;
		attemptminutesB = minutes(attemptsB, availableMin);		
		goalminutesB = minutes(goalsB, availableMin);
		Arrays.sort(attemptminutesB);
		Arrays.sort(goalminutesB);
		
		if(goalsA > goalsB){toto = 1;}
		else if(goalsB > goalsA){toto = 2;}
		else if(goalsA == goalsB){toto = 0;}
		
		
		//System.out.println(alpha.getNm() + "\t" + alphaAttMap + "\t" + alphaDefMap);
		//System.out.println(beta.getNm() + "\t" + betaAttMap + "\t" + betaDefMap);
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
		return c;
	}
	
	/**Method to determine the minute an action was made
	 * The chosen minute is deleted from 
	 * 
	 * @param c Amount of things
	 * @return array with integers (minutes)
	 */
	
	public int[] minutes(int c, Set<Integer> not){
		
		int[] minutes = new int[c];
		
		while(contains(minutes,0)){
			int i =0;
			pick:
				while(i < c){
					int a = (int) Math.round((Math.random()*88) + 1);
					
					if(not.contains(a)){
						minutes[i] = a;
						not.remove(a);
					}
					
					else if(!not.contains(a)){
						continue pick;
					}	
					
					i++;
				}
		}
		
		return minutes;
	}
	
	public static boolean contains(int[] set, int x){
		for(int i = 0; i < set.length; i++){
			if(set[i] == x){
				return true;
			}
		}
		return false;
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
	
	public Team getTeamA(){ return teamA;}
	public Team getTeamB(){ return teamB;}
	public int getAttemptsA(){ return attemptsA; }
	public int getAttemptsB(){ return attemptsB; }
	public int getGoalsA(){ return goalsA; }
	public int getGoalsB(){ return goalsB; }
	public int[] getGoalminutesA(){ return goalminutesA; }
	public int[] getGoalminutesB(){ return goalminutesB; }
	public int[] getAttemptminutesA(){ return attemptminutesA; }
	public int[] getAttemptminutesB(){ return attemptminutesB; }
	public int getToto() {return toto;}
}
