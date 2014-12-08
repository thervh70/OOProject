/**Beginning of our budget class 
 * @author Bram
 */

import Database.*;
public class Budget {
	private static int budg_rel;
	private static int budg_vir;

	public static void main(String[] args) {
		Player p = new Fieldplayer("Frits","Fritsman","RB",19,100000,100,100,100,100,100,100);
		budg_rel=100000000;
		budg_vir=100000000;
		for(int i=0;i<100;i++){
			System.out.println(bieden(p));
			/**System.out.println(budg_rel);
			System.out.println(budg_vir);**/
		}
	}
	
	public static boolean bieden(Player p){
		if(budg_vir<p.getPri()){
			return false;
		}
		else{
			budg_vir-=p.getPri();
			if (p.getPri()<budg_rel){
				if(Math.random()>0.6){
					budg_rel-=p.getPri();
					System.out.println(budg_rel);
					return true;
				}
				else{
					budg_vir+=p.getPri();
					return false;
				}
			}
			else{
				budg_vir+=p.getPri();
				return false;
			}
		}
	}
}
