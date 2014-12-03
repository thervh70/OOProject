package Creators_REMOVE_FROM_FINAL_PROJECT;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) throws FileNotFoundException{
		Scanner sc = new Scanner(System.in);
		System.out.print("Wat is de teamnaam? ");
		String teamnaam = sc.nextLine();
		ArrayList<Keeper> k = new ArrayList<Keeper>();
		String km = "1";
		do {
			System.out.print("Wat is de voornaam? ");
			String vn = sc.next();
			System.out.print("Wat is de achternaam? ");
			String an = sc.next();
			System.out.print("Wat is de DIV? ");
			String div = sc.next();
			System.out.print("Wat is de HAN? ");
			String han = sc.next();
			System.out.print("Wat is de KIC? ");
			String kic = sc.next();
			System.out.print("Wat is de REF? ");
			String ref = sc.next();
			System.out.print("Wat is de SPE? ");
			String spe = sc.next();
			System.out.print("Wat is de POS? ");
			String pos = sc.next();
			System.out.print("Wat is de HEI? ");
			String hei = sc.next();
			System.out.print("Wat is de Age? ");
			String age = sc.next();
			Keeper keeper = new Keeper(vn,an,div,han,kic,ref,spe,pos,hei,age);
			k.add(keeper);
			System.out.print("Zijn er nog meer keepers in dit team (1 voor ja, 0 voor nee) ");
			km = sc.next();
		} while(!(km.equals("0")));
		sc.close();
		PrintWriter printer = new PrintWriter("src/Creators_REMOVE_FROM_FINAL_PROJECT/keepers/"+teamnaam+".xml");
		printer.print("   <KEEPERS_FOR_TEAM>\r\n");
		printer.print("      <TEAMNAME>"+teamnaam+"</TEAMNAME>\r\n");
		for(int i=0;i<k.size();i++){
			printer.print(k.get(i).toWrite());
		}
		printer.print("   </KEEPERS_FOR_TEAM>");
		printer.close();
		System.out.println("Programma is afgesloten.");
	}
}
