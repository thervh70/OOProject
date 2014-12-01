package Database;

public class GetPlayerPrice {

	public static DBmain priceInXML(DBmain db) {
		DBmain res = new DBmain();
		for(int i=0;i<db.getSize();i++) {
			Team t = db.getT(i);
			Team resT = new Team(t.getNm(), t.getBdgt_vir(), t.getBdgt_rel());
			for(int j=0;j<t.getSize();j++) {
				Player p = t.getPlayer(j);
				Player resP = definePlayerPrice(p);
				resT.addPlayer(resP);
			}
			res.addTeam(resT);
			System.out.println(res);
		}
		return res;
	}
	
	public static Player definePlayerPrice(Player p) {
		int pri = 75000;
		double score = 0;
		String pos = p.getPos();
		int pac = p.getPac();
		int sho = p.getSho();
		int pas = p.getPas();
		int dri = p.getDri();
		int def = p.getDef();
		int phy = p.getPhy();
		if(pos.equals("RB") || pos.equals("CB") || pos.equals("LB")) {
			score = (pac*10 + sho*10 + pas*15 + dri*10 + def*40 + phy*15)/100;
		}
		else if(pos.equals("CDM") || pos.equals("CM")) {
			score = (pac*15 + sho*10 + pas*20 + dri*10 + def*25 + phy*20)/100;
		}
		else if(pos.equals("CAM") || pos.equals("LW") || pos.equals("RW")) {
			score = (pac*15 + sho*15 + pas*15 + dri*15 + def*15 + phy*25)/100;
		}
		else if(pos.equals("ST")) {
			score = (pac*10 + sho*25 + pas*15 + dri*15 + def*10 + phy*25)/100;
		}
		float percentage = (float) (score*0.5/100 + Math.random()*5/10);
		if(percentage > 1) System.out.println("percentage te groot!");
		pri += Math.round(percentage*125000);
		p.setPri(pri);
		return p;
	}
}
