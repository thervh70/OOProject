package Database;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

public class TestTeam {
	private ArrayList<TestPlayer> t;
	private String nm;
	int bdgt;
	
	public TestTeam(String name, int budget) {
		t = new ArrayList<TestPlayer>();
		nm = name;
		bdgt = budget;
	}
	
	public void addTestPlayer(TestPlayer p) {
		if(!t.contains(p)) {
			t.add(p);
		}
	}
	
	public String toString() {
		String res = "Team: " + nm + "(" + t.size() + ")\n  ";
		for (int i = 0; i < t.size(); i++) {
			res += t.get(i) + "\n  ";
		}
		res.substring(0,res.length() - 3);
		res += "\n";
		return res;
	}
	
	public String toWrite() {
		String res = "   <TEAM>\r\n";
		res += "      <TEAMNAME>" + nm + "</TEAMNAME>\r\n";
		res += "      <BUDGET>" + bdgt + "</BUDGET>\r\n";
		for(int i=0;i<t.size();i++) {
			TestPlayer p = this.getT().get(i);
			res += p.toWrite();
		}
		res += "   </TEAM>\r\n";
		return res;
	}
	
	public static void write(String infile, Team p) {
		Writer writer = null;
		try {
		    writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(infile), "utf-8"));
		    String player = p.toWrite();
		    writer.write(player);
		}
		 catch (IOException ex) {
			  // report
			} finally {
			   try {writer.close();} catch (Exception ex) {}
			}
	}
	
	
	public String getNm() {
		return this.nm;
	}

	public ArrayList<TestPlayer> getT() {
		return this.t;
	}
	
	public int getBdgt() {
		return this.bdgt;
	}
	
	public int getSize() {
		return t.size();
	}
	
	public TestPlayer getPlayer(int i) {
		return t.get(i);
	}
}
