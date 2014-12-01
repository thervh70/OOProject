/**Beginning of our budget class 
 * @author Bram
 */
import Database.Player;
import java.util.*;
public class Budget {
	private static int budg_rel;
	private static int budg_vir;

	public static void main(String[] args) {
		Player p = new Player("Frits","Fritsman","MV",19,100000,100,100,100,100,100,100);
		Scanner sc = new Scanner(System.in);
		System.out.print("Wat is uw budget? ");
		budg_rel=budg_vir=sc.nextInt();
		sc.close();
		System.out.println(bieden(p));
		System.out.println(budg_rel);
		System.out.println(budg_vir);
	}
	
	public static boolean bieden(Player p){
		budg_vir-=p.getPri();
		if (p.getPri()<budg_rel){
			budg_rel-=p.getPri();
			return true;
		}
		else{
			budg_vir+=p.getPri();
			return false;
		}
	}
}
