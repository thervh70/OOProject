/**The root of our game.
 * This class calculates the score for a match.
 *  
 * @author Robin
 *
 */

public class gameEngine {

	//Pace, Shoot, Pass, Dribble, Defend, Physical, Condition
	//TODO: Calculate attack scores based on team scores
	
	/*
	private double pacAveA,shoAveA,pasAveA,driAveA,defAveA,phyAveA,conAveA;  //Team average of individual scores, Team A
	private double pacAveB,shoAveB,pasAveB,driAveB,defAveB,phyAveB,conAveB;  //Team average of individual scores, Team B
	private int dfCntA,mfCntA,stCntA; //Count the number of defenders, midfielders, strikers, Team A
	private int dfCntB,mfCntB,stCntB; //Count the number of defenders, midfielders, strikers, Team A
	*/
	//Here are the testing values, change these to change the strength of the teams
	private static int teamAatt = 50;
	private static int teamAdef = 50;
	
	private static int teamBatt = 70;
	private static int teamBdef = 70;
	
	/*
	private static int targA = 0;
	private static int targB = 0;
	*/
	
	/**For now, the main contains the excecution of the attack-method
	 * Each team get's the chance to attack --> the attack method is called
	 * A syso is then displayed with the score
	 */
	
	public static void main(String[] args) {
	
		int A = attack(teamAatt, teamBdef);
		int B = attack(teamBatt, teamAdef);
		
		if(A>B){
			System.out.print("Team A won: ");;
		}
		else if(B>A){
			System.out.print("Team B won: ");
		}
		else{
			System.out.print("It's a tie: ");
		}
		
		System.out.println(A + "-" + B);
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
	 * To add extra randomness an extra function was made, this was unneccesary.
	 * 
	 * @param att Attacking score of team A
	 * @param def Defending score of team B
	 * @return The amount of goals scored by attacking team
	 */
	
	public static int attack(int att, int def){
		double a = 0,b = 0;
		int c = 0;
		
		//TODO: Link int d to target attempts
		int d = (int) Math.round((Math.random()*10)+1);
		
		for(int i = 0; i < d; i++){
			a = att*(Math.random()-0.3);
			b = def*(Math.random());
			
			if(a>b){ 
				c++;
			}		
		}
		
		//Extra randomness --> 
		//c = (int) (c*(Math.random()+0.5));
		
		return c;
	}
}
