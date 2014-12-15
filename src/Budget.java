/**Beginning of our budget class 
 * @author Bram
 */

import Database.*;
public class Budget {
	DBmain test = XmlParser.parseDB();

	public static void main(String[] args) {
		Player p = new Fieldplayer("Frits","Fritsman","RB",19,100000,100,100,100,100,100,100);
	}
	
	public static void bieden(Player p){
		if(!(budg_vir<p.getPri())){
			budg_vir-=p.getPri();
			if (p.getPri()<budg_rel){
				if(Math.random()>0.6){
					budg_rel-=p.getPri();
					System.out.println(budg_rel);
				}
				else{
					budg_vir+=p.getPri();
				}
			}
			else{
				budg_vir+=p.getPri();
			}
		}
	}
}