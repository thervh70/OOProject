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
	private static ArrayList<Player> injuredPlayerA = new ArrayList<Player>();
	private static ArrayList<Player> injuredPlayerB = new ArrayList<Player>();
	
	public gameEngine(){
		
	}
	
	/**This method plays a match.
	 * All stats are stored into static values within this class
	 * 
	 * @param alpha Original Team A
	 * @param beta Original Team B
	 */
	
	public void play(Team alpha, Team beta){
		teamA = alpha;
		teamB = beta;
		yellowcardsA = 0;
		yellowcardsB = 0;
		redcardsA = 0;
		redcardsB = 0;
		injuriesA = 0;
		injuriesB = 0;
		
		yellowPlayerA.clear();
		yellowPlayerB.clear();
		redPlayerA.clear();
		redPlayerB.clear();
		injuredPlayerA.clear();
		injuredPlayerB.clear();
		
		double aAtt =  teamA.calcAttScore();
		double bAtt = teamB.calcAttScore();
		
		double aDef = teamA.calcDefScore();
		double bDef = teamB.calcDefScore();
		
		final Set<Integer> availableMin = new HashSet<>(); {
		    for (int i = 0; i <= 90; i++) {
		        availableMin.add(i);
		    }
		}		
		
		for(int i = 0; i < 11; i++){
			Player p = teamA.getSelectionPlayer(i);
			int card = p.card();
			if(card == 1){
				yellowcardsA++;
				yellowPlayerA.add(p);
			}
			else if(card == 2){
				redcardsA++;
				redPlayerA.add(p);
			}
			else if(card == 0){
				int injury = p.injury();
				if(injury != 0){
					injuriesA++;
					injuredPlayerA.add(p);
				}
			}
		}		
		
		gameEngine A = new gameEngine();
		goalsA = A.attack(aAtt, bDef);
		attemptsA = A.attempts;
		
		yellowcardminutesA = minutes(yellowcardsA, availableMin);
		redcardminutesA = minutes(redcardsA, availableMin);
		attemptminutesA = minutes(attemptsA, availableMin);
		goalminutesA = minutes(goalsA, availableMin);
		injuryminutesA = minutes(injuriesA, availableMin);
		Arrays.sort(yellowcardminutesA);
		Arrays.sort(redcardminutesA);
		Arrays.sort(attemptminutesA);
		Arrays.sort(goalminutesA);
		Arrays.sort(injuryminutesA);
		
		for(int i = 0; i < 11; i++){
			Player p = teamB.getSelectionPlayer(i);
			int card = p.card();
			if(card == 1){
				yellowcardsB++;
				yellowPlayerB.add(p);
			}
			else if(card == 2){
				redcardsB++;
				redPlayerB.add(p);
			}
			else if(card == 0){
				int injury = p.injury();
				if(injury != 0){
					injuriesB++;
					injuredPlayerB.add(p);
				}
			}
		}
		
		gameEngine B = new gameEngine();
		goalsB = B.attack(bAtt, aDef);
		attemptsB = B.attempts;
		
		yellowcardminutesB = minutes(yellowcardsB, availableMin);
		redcardminutesB = minutes(redcardsB, availableMin);
		attemptminutesB = minutes(attemptsB, availableMin);		
		goalminutesB = minutes(goalsB, availableMin);
		injuryminutesB = minutes(injuriesB, availableMin);
		Arrays.sort(yellowcardminutesB);
		Arrays.sort(redcardminutesB);
		Arrays.sort(attemptminutesB);
		Arrays.sort(goalminutesB);
		Arrays.sort(injuryminutesB);
		
		if(goalsA > goalsB){toto = 1;}
		else if(goalsB > goalsA){toto = 2;}
		else if(goalsA == goalsB){toto = 0;}
		
		saveGame.refreshTeam(alpha, teamA);
		saveGame.refreshTeam(beta, teamB);
	}
	
	/**An attack needs 2 values: 1 attacking (Team A) and 1 defending (Team B)
	 * Int d is calculated first. This value represents the amount of goal attempts.
	 * The for loop is initiated, with the amount of attempts as max.
	 * An attacking and a defending score is calculated.
	 * This happens randomly, with the teamscore as a basis.
	 * If the attacking team is lucky, they score the highest.
	 * 
	 * Within the attacking mechanism is a +0.2, this is a numerical correction.
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

	public Team getTeamA() {
		return teamA;
	}

	public void setTeamA(Team teamA) {
		this.teamA = teamA;
	}

	public Team getTeamB() {
		return teamB;
	}

	public void setTeamB(Team teamB) {
		this.teamB = teamB;
	}

	public int getAttemptsA() {
		return attemptsA;
	}

	public void setAttemptsA(int attemptsA) {
		this.attemptsA = attemptsA;
	}

	public int getAttemptsB() {
		return attemptsB;
	}

	public void setAttemptsB(int attemptsB) {
		this.attemptsB = attemptsB;
	}

	public int getGoalsA() {
		return goalsA;
	}

	public void setGoalsA(int goalsA) {
		this.goalsA = goalsA;
	}

	public int getGoalsB() {
		return goalsB;
	}

	public void setGoalsB(int goalsB) {
		this.goalsB = goalsB;
	}

	public int getYellowcardsA() {
		return yellowcardsA;
	}

	public void setYellowcardsA(int yellowcardsA) {
		this.yellowcardsA = yellowcardsA;
	}

	public int getYellowcardsB() {
		return yellowcardsB;
	}

	public void setYellowcardsB(int yellowcardsB) {
		this.yellowcardsB = yellowcardsB;
	}

	public int getRedcardsA() {
		return redcardsA;
	}

	public void setRedcardsA(int redcardsA) {
		this.redcardsA = redcardsA;
	}

	public int getRedcardsB() {
		return redcardsB;
	}

	public void setRedcardsB(int redcardsB) {
		this.redcardsB = redcardsB;
	}

	public int getInjuriesA() {
		return injuriesA;
	}

	public void setInjuriesA(int injuriesA) {
		this.injuriesA = injuriesA;
	}

	public int getInjuriesB() {
		return injuriesB;
	}

	public void setInjuriesB(int injuriesB) {
		this.injuriesB = injuriesB;
	}

	public int getAttempts() {
		return attempts;
	}

	public void setAttempts(int attempts) {
		this.attempts = attempts;
	}

	public int getToto() {
		return toto;
	}

	public void setToto(int toto) {
		this.toto = toto;
	}

	public int[] getGoalminutesA() {
		return goalminutesA;
	}

	public void setGoalminutesA(int[] goalminutesA) {
		this.goalminutesA = goalminutesA;
	}

	public int[] getGoalminutesB() {
		return goalminutesB;
	}

	public void setGoalminutesB(int[] goalminutesB) {
		this.goalminutesB = goalminutesB;
	}

	public int[] getAttemptminutesA() {
		return attemptminutesA;
	}

	public void setAttemptminutesA(int[] attemptminutesA) {
		this.attemptminutesA = attemptminutesA;
	}

	public int[] getAttemptminutesB() {
		return attemptminutesB;
	}

	public void setAttemptminutesB(int[] attemptminutesB) {
		this.attemptminutesB = attemptminutesB;
	}

	public int[] getYellowcardminutesA() {
		return yellowcardminutesA;
	}

	public void setYellowcardminutesA(int[] yellowcardminutesA) {
		this.yellowcardminutesA = yellowcardminutesA;
	}

	public int[] getRedcardminutesA() {
		return redcardminutesA;
	}

	public void setRedcardminutesA(int[] redcardminutesA) {
		this.redcardminutesA = redcardminutesA;
	}

	public int[] getYellowcardminutesB() {
		return yellowcardminutesB;
	}

	public void setYellowcardminutesB(int[] yellowcardminutesB) {
		this.yellowcardminutesB = yellowcardminutesB;
	}

	public int[] getRedcardminutesB() {
		return redcardminutesB;
	}

	public void setRedcardminutesB(int[] redcardminutesB) {
		this.redcardminutesB = redcardminutesB;
	}

	public int[] getInjuryminutesA() {
		return injuryminutesA;
	}

	public void setInjuryminutesA(int[] injuryminutesA) {
		this.injuryminutesA = injuryminutesA;
	}

	public int[] getInjuryminutesB() {
		return injuryminutesB;
	}

	public void setInjuryminutesB(int[] injuryminutesB) {
		this.injuryminutesB = injuryminutesB;
	}

	public static ArrayList<Player> getYellowPlayerA() {
		return yellowPlayerA;
	}

	public static void setYellowPlayerA(ArrayList<Player> yellowPlayerA) {
		gameEngine.yellowPlayerA = yellowPlayerA;
	}

	public static ArrayList<Player> getYellowPlayerB() {
		return yellowPlayerB;
	}

	public static void setYellowPlayerB(ArrayList<Player> yellowPlayerB) {
		gameEngine.yellowPlayerB = yellowPlayerB;
	}

	public static ArrayList<Player> getRedPlayerA() {
		return redPlayerA;
	}

	public static void setRedPlayerA(ArrayList<Player> redPlayerA) {
		gameEngine.redPlayerA = redPlayerA;
	}

	public static ArrayList<Player> getRedPlayerB() {
		return redPlayerB;
	}

	public static void setRedPlayerB(ArrayList<Player> redPlayerB) {
		gameEngine.redPlayerB = redPlayerB;
	}

	public static ArrayList<Player> getInjuredPlayerA() {
		return injuredPlayerA;
	}

	public static void setInjuredPlayerA(ArrayList<Player> injuredPlayerA) {
		gameEngine.injuredPlayerA = injuredPlayerA;
	}

	public static ArrayList<Player> getInjuredPlayerB() {
		return injuredPlayerB;
	}

	public static void setInjuredPlayerB(ArrayList<Player> injuredPlayerB) {
		gameEngine.injuredPlayerB = injuredPlayerB;
	}

	

}
