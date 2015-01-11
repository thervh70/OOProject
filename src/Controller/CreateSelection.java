package Controller;

import java.util.ArrayList;

import Model.*;

public class CreateSelection {
	
	/**Return true if size of team is ok
	 * 
	 * @param t Team to be checked
	 * @return
	 */
	public static boolean checkSize(Team t) {
		for(int type=0;type<3;type++) {
			int aantal = 0;
			int tel = 0;
			String[] types = null;
			
			switch(type) {
			case 0: aantal = 2; types = new String[] {"GK"}; break;
			case 1: aantal = 9; types = new String[] {"RB", "LB", "CB","CDM", "CM"}; break;
			case 2: aantal = 7; types = new String[] {"CAM", "LW", "RW","ST"}; break;
			}
			
			for(int i=0;i<t.getSize();i++) {
				String pos = t.getPlayer(i).getPos();
				if(containsString(types, pos))
					tel++;
			}
			
			if(tel < aantal){
				return false;
			}
		}
		return true;
	}
	
	public static Team create(Team t) {
		
		t.removeSelection();
		for(int type=0;type<3;type++) {
			int aantal = 0;
			String[] types = null;
			
			switch(type) {
			case 0: aantal = 1; types = new String[] {"GK"}; break;
			case 1: aantal = 6; types = new String[] {"RB", "LB", "CB","CDM", "CM"}; break;
			case 2: aantal = 4; types = new String[] {"CAM", "LW", "RW","ST"}; break;
			}
			
			ArrayList<Player> list = new ArrayList<Player>();
			for(int i=0;i<t.getSize();i++) {
				String pos = t.getPlayer(i).getPos();
				if(containsString(types, pos))
					list.add(t.getPlayer(i));
			}
			
			for(int j=0;j<aantal;j++) {
				Player p = getBestPlayer(list);
				t.addPlayer(p);
				t.toSelection(p);
				list.remove(p);
			}
		}
		return t;
	}
	
	private static Player getBestPlayer(ArrayList<Player> list) {
		Player res = list.get(0);
		double score = getPlayerScore(res);
		for(int i=0;i<list.size();i++) {
			if(getPlayerScore(list.get(i)) > score) {
				res = list.get(i);
			}
		}
		return res;
	}
	
	private static double getPlayerScore(Player pl) {
		double score = 0;
		if(pl instanceof Fieldplayer) {
			Fieldplayer p = (Fieldplayer)(pl);
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
		}
		else if(pl instanceof Goalkeeper) {
			Goalkeeper p = (Goalkeeper)(pl);
			int div = p.getDiv();
			int han = p.getHan();
			int kick = p.getKick();
			int ref = p.getRef();
			int spd = p.getSpd();
			int ping = p.getPing();
			int hei = p.getHei();
			score = (div + han + kick + ref + spd + ping + hei)/7;
		}
		return score;
	}
	
	private static boolean containsString(String[] array, String string) {
		for(int i=0;i<array.length;i++) {
			if(array[i].equals(string)) {
				return true;
			}
		}
		return false;
	}

}
