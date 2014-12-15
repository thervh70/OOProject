/**Beginning of our budget class 
 * @author Bram
 */

import Database.*;
public class Budget {
	
	public static void main(String[] args) {
		DBmain test = XmlParser.parseDB();
		Team frits = test.getTeam(1);
		Team frits2 = test.getTeam(2);
		Player testP = frits.getPlayer(0);
		System.out.println(testP.getPri());
		System.out.println("frits : " +frits.containsPlayer(testP));
		System.out.println("        "+frits.getBdgt_vir()+" "+frits.getBdgt_rel());
		System.out.println("frits2: " +frits2.containsPlayer(testP));
		System.out.println("        "+frits2.getBdgt_vir()+" "+frits2.getBdgt_rel());
		bieden(testP,frits2,frits);
		System.out.println("frits : " +frits.containsPlayer(testP));
		System.out.println("        "+frits.getBdgt_vir()+" "+frits.getBdgt_rel());
		System.out.println("frits2: " +frits2.containsPlayer(testP));
		System.out.println("        "+frits2.getBdgt_vir()+" "+frits2.getBdgt_rel());
	}
	
	public static void bieden(Player p, Team t1, Team t2){
		if(!(t1.getBdgt_vir()<p.getPri())){
			t1.subtractBudget_vir(p.getPri());
			if (p.getPri()<t1.getBdgt_rel()){
				if(Math.random()>0.6){
					t1.subtractBudget_rel(p.getPri());
					t1.addPlayer(p);
					t2.removePlayer(p);
					t2.addBudget_rel(p.getPri());
					t2.addBudget_vir(p.getPri());
				}
				else{
					t1.addBudget_vir(p.getPri());
				}
			}
			else{
				t1.addBudget_vir(p.getPri());
			}
		}
	}
}