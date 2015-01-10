package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import Model.DBmain;
import Model.Player;
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
	private int attemptsA, attemptsB, goalsA, goalsB, yellowcardsA, yellowcardsB,redcardsA, redcardsB, injuriesA, injuriesB, attempts, toto;
	private int[] goalminutesA, goalminutesB, attemptminutesA, attemptminutesB, yellowcardminutesA, redcardminutesA, yellowcardminutesB, redcardminutesB, injuryminutesA, injuryminutesB;
	
	private static ArrayList<Player> yellowPlayerA = new ArrayList<Player>();
	private static ArrayList<Player> yellowPlayerB = new ArrayList<Player>();
	private static ArrayList<Player> redPlayerA = new ArrayList<Player>();
	private static ArrayList<Player> redPlayerB = new ArrayList<Player>();
	
	private static double amount = 5000;
	private static int a = 7;
	private static int b= 5;
	
	/**For now, the main contains the excecution of the attack-method
	 * Each team get's the chance to attack --> the attack method is called
	 * A syso is then displayed with the score
	 */
	
	@SuppressWarnings("resource")
	public static void main(String[] args){
		int choice;
		double totalAttA = 0, totalAttB = 0, totalGoalA = 0, totalGoalB = 0, totalYelA = 0, totalYelB = 0, totalRedA = 0, totalRedB = 0;
		
		Scanner input = new Scanner(System.in);
		System.out.println("1 game (0) or multiple games (1)? Or (2) show scores?");
		
		DBmain db = XmlParser.parseDB();
		saveGame.setDB(db);
		
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
			
			System.out.println("\n\nCards " + alpha.getNm());
			
			System.out.print("Yellow: ");
			for(Player p : yellowPlayerA){
				System.out.print(p.getName() + " ");;
			}
			System.out.print("\nRed: ");
			for(Player p : redPlayerA){
				System.out.print(p.getName() + " ");;
			}
			
			System.out.println("\n\nCards " + beta.getNm());
			
			System.out.print("Yellow: ");
			for(Player p : yellowPlayerB){
				System.out.print(p.getName() + " ");;
			}
			System.out.print("\nRed: ");
			for(Player p : redPlayerB){
				System.out.print(p.getName() + " ");;
			}
		}
		
		else if(choice == 1){
			DBmain d = saveGame.getDB();
			Team alpha = d.getTeam(a);
			Team beta = d.getTeam(b);
			gameEngine match = new gameEngine();
			saveGame.setDay(1);
			
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
				totalYelA += match.getYellowcardsA();
				totalYelB += match.getYellowcardsB();
				totalRedA += match.getRedcardsA();
				totalRedB += match.getRedcardsB();
			
				d.clearAllCards();
				saveGame.nextDay();
			}
			
			System.out.println("Amount of Wins from " + alpha.getNm() + ": " + winsA);
			System.out.println("Amount of Wins from " + beta.getNm() + ": " + winsB);
			System.out.println("Amount of Ties: " + ties);
			
			System.out.println("\nAverage Goals from " + alpha.getNm() + ": " + (double) (totalGoalA/amount));
			System.out.println("Average Goals from " + beta.getNm() + ": " + (double) (totalGoalB/amount));
			
			System.out.println("\nAverage Attempts from " + alpha.getNm() + ": " + (double) (totalAttA/amount));
			System.out.println("Average Attempts from " + beta.getNm() + ": " + (double) (totalAttB/amount));
			
			System.out.println("\nAverage Yellow from " + alpha.getNm() + ": " + (double) (totalYelA/amount));
			System.out.println("Average Yellow from " + beta.getNm() + ": " + (double) (totalYelB/amount));
			
			System.out.println("\nAverage Red from " + alpha.getNm() + ": " + (double) (totalRedA/amount));
			System.out.println("Average Red from " + beta.getNm() + ": " + (double) (totalRedB/amount));
			
		}
		
		else if(choice == 2){
			DBmain d = XmlParser.parseDB();
			for(int i = 0; i < 18; i++){
				Team t = d.getTeam(i);
				System.out.println(t.calcAttScore() + "\t" + t.calcDefScore() + "\t" + t.getNm());
			}
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
		yellowcardsA = 0;
		yellowcardsB = 0;
		redcardsA = 0;
		redcardsB = 0;
		
		double alphaAtt =  alpha.calcAttScore();
		double betaAtt = beta.calcAttScore();
		
		double alphaDef = alpha.calcDefScore();
		double betaDef = beta.calcDefScore();
		
		final Set<Integer> availableMin = new HashSet<>(); {
		    for (int i = 0; i <= 90; i++) {
		        availableMin.add(i);
		    }
		}		
		
		for(int i = 0; i < 11; i++){
			Player p = alpha.getSelectionPlayer(i);
			int card = p.card();
			if(card == 1){
				yellowcardsA++;
				yellowPlayerA.add(p);
			}
			else if(card == 2){
				redcardsA++;
				redPlayerA.add(p);
			}
		}
		
		goalsA = attack(alphaAtt, betaDef);
		attemptsA = attempts;
		
		yellowcardminutesA = minutes(yellowcardsA, availableMin);
		redcardminutesA = minutes(redcardsA, availableMin);
		attemptminutesA = minutes(attemptsA, availableMin);
		goalminutesA = minutes(goalsA, availableMin);
		Arrays.sort(yellowcardminutesA);
		Arrays.sort(redcardminutesA);
		Arrays.sort(attemptminutesA);
		Arrays.sort(goalminutesA);
		
		for(int i = 0; i < 11; i++){
			Player p = beta.getSelectionPlayer(i);
			int card = p.card();
			if(card == 1){
				yellowcardsB++;
				yellowPlayerB.add(p);
			}
			else if(card == 2){
				redcardsB++;
				redPlayerB.add(p);
			}
		}
		
		goalsB = attack(betaAtt, alphaDef);
		attemptsB = attempts;
		
		yellowcardminutesB = minutes(yellowcardsB, availableMin);
		redcardminutesB = minutes(redcardsB, availableMin);
		attemptminutesB = minutes(attemptsB, availableMin);		
		goalminutesB = minutes(goalsB, availableMin);
		Arrays.sort(yellowcardminutesB);
		Arrays.sort(redcardminutesB);
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
		
		attempts = (int) Math.round((Math.random()*(att/30)+1));
		
		for(int i = 0; i < attempts; i++){
			a = att*(Math.random()+.2);
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
	public int getYellowcardsA() {return yellowcardsA;}
	public int getYellowcardsB() {return yellowcardsB;}
	public int getRedcardsA() {	return redcardsA;}
	public int getRedcardsB() {	return redcardsB;}
	public int[] getYellowcardminutesA() {return yellowcardminutesA;}
	public int[] getRedcardminutesA() {return redcardminutesA;}
	public int[] getYellowcardminutesB() {return yellowcardminutesB;}
	public int[] getRedcardminutesB() {return redcardminutesB;}
	public ArrayList<Player> getYellowPlayerA() {return yellowPlayerA;}
	public ArrayList<Player> getYellowPlayerB() {return yellowPlayerB;}
	public ArrayList<Player> getRedPlayerA() {return redPlayerA;}
	public ArrayList<Player> getRedPlayerB() {return redPlayerB;}
}
