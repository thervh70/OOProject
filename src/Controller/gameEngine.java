package Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import Model.Player;
import Model.Team;

/**The root of our game.
 * This class calculates the score for a match along with the stats of a match.
 * @author D18.1
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
	
	/**
	 * This constructor creates a new instance of the GameEngine.
	 * That way we are able to get the results from a seperate GameEngine
	 */
	
	public gameEngine(){
		
	}
	
	/**This method plays a match.
	 * All stats are stored within values of this class
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
		
		makeToto();
		
		saveGame.refreshTeam(alpha, teamA);
		saveGame.refreshTeam(beta, teamB);
	}
	
	/**This method generates a toto based on the results from a match
	 * 
	 * @return int toto: A won - 1, b won - 2, tie - 0
	 */
	
	@SuppressWarnings("null")
	public int makeToto() {
		if(goalsA > goalsB){toto = 1; return toto;}
		else if(goalsB > goalsA){toto = 2; return toto;}
		else if(goalsA == goalsB){toto = 0; return toto;}
		return (Integer) null;		
	}

	/**An attack needs 2 values: 1 attacking (Team A) and 1 defending (Team B)
	 * Int d is calculated first. This value represents the amount of goal attempts.
	 * The for loop is initiated, with the amount of attempts as max.
	 * An attacking and a defending score for 1 attack is calculated.
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
	
	/**A static method to check whether a set contains x
	 *  
	 * @param set an integer array
	 * @param x the number looked for
	 * @return a boolean
	 */
	public static boolean contains(int[] set, int x){
		for(int i = 0; i < set.length; i++){
			if(set[i] == x){
				return true;
			}
		}
		return false;
	}

	/**@return Get Team A */
	public Team getTeamA() {return teamA;}
	
	/**@return Get Team B */
	public Team getTeamB() {return teamB;}

	/**@return The total amount of attempts made by team A*/
	public int getAttemptsA() {return attemptsA;}

	/**@return The total amount of attempts made by team B*/
	public int getAttemptsB() {return attemptsB;}

	/**@return The total amount of goals made by team A*/
	public int getGoalsA() {return goalsA;}

	/**@return The total amount of goals made by team B*/
	public int getGoalsB() {return goalsB;}

	/**@return The total amount of yellow cards of team A*/
	public int getYellowcardsA() {return yellowcardsA;}

	/**@return The total amount of yellow cards of team B*/
	public int getYellowcardsB() {return yellowcardsB;}

	/**@return The total amount of red cards of team A*/
	public int getRedcardsA() {return redcardsA;}

	/**@return The total amount of red cards of team B*/
	public int getRedcardsB() {return redcardsB;}

	/**@return The total amount of injuries of team A*/
	public int getInjuriesA() {return injuriesA;}
	
	/**@return The total amount of injuries of team B*/
	public int getInjuriesB() {return injuriesB;}

	/**@return The total amount of attempts*/
	public int getAttempts() {return attempts;}
	
	/**@return The toto of a match*/
	public int getToto() {return toto;}

	/**@return A array of integers with the minutes Team A scored a goal*/
	public int[] getGoalminutesA() {return goalminutesA;}

	/**@return A array of integers with the minutes Team B scored a goal*/
	public int[] getGoalminutesB() {return goalminutesB;}

	/**@return A array of integers with the minutes an attempt was made by Team A*/
	public int[] getAttemptminutesA() {return attemptminutesA;}

	/**@return A array of integers with the minutes an attempt was made by Team B*/
	public int[] getAttemptminutesB() {return attemptminutesB;}

	/**@return A array of integers with the minutes players from team A get a yellow card */
	public int[] getYellowcardminutesA() {return yellowcardminutesA;}

	/**@return A array of integers with the minutes players from team B get a yellow card */
	public int[] getYellowcardminutesB() {return yellowcardminutesB;}

	/**@return A array of integers with the minutes players from team A get a red card */
	public int[] getRedcardminutesA() {return redcardminutesA;}

	/**@return A array of integers with the minutes players from team B get a red card */
	public int[] getRedcardminutesB() {return redcardminutesB;}

	/**@return A array of integers with the minutes players from team A get injured */
	public int[] getInjuryminutesA() {return injuryminutesA;}

	/**@return A array of integers with the minutes players from team B get injured */
	public int[] getInjuryminutesB() {return injuryminutesB;}

	/**@return An ArrayList of players from Team A with a Yellow Card */
	public static ArrayList<Player> getYellowPlayerA() {return yellowPlayerA;}

	/**@return An ArrayList of players from Team B with a Yellow Card */
	public static ArrayList<Player> getYellowPlayerB() {return yellowPlayerB;}

	/**@return An ArrayList of players from Team A with a Red Card */
	public static ArrayList<Player> getRedPlayerA() {return redPlayerA;}
	
	/**@return An ArrayList of players from Team B with a Red Card */
	public static ArrayList<Player> getRedPlayerB() {return redPlayerB;}
	
	/**@return An ArrayList of injured players from Team A */
	public static ArrayList<Player> getInjuredPlayerA() {return injuredPlayerA;}
	
	/**@return An ArrayList of injured players from Team B */
	public static ArrayList<Player> getInjuredPlayerB() {return injuredPlayerB;}	
}
