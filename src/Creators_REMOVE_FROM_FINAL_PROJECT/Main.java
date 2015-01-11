package Creators_REMOVE_FROM_FINAL_PROJECT;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import Model.Goalkeeper;


public class Main {

	public static void main(String[] args) throws FileNotFoundException{
		Scanner sc = new Scanner(System.in);
		System.out.print("Wat is de teamnaam? ");
		String teamnaam = sc.nextLine();
		ArrayList<Goalkeeper> k = new ArrayList<Goalkeeper>();
		String km = "1";
		do {
			System.out.print("Wat is de voornaam? ");
			String vn = sc.next();
			System.out.print("Wat is de achternaam? ");
			String an = sc.next();
			System.out.print("Wat is de DIV? ");
			int div = Integer.parseInt(sc.next());
			System.out.print("Wat is de HAN? ");
			int han = Integer.parseInt(sc.next());
			System.out.print("Wat is de KIC? ");
			int kic = Integer.parseInt(sc.next());
			System.out.print("Wat is de REF? ");
			int ref = Integer.parseInt(sc.next());
			System.out.print("Wat is de SPD? ");
			int spe = Integer.parseInt(sc.next());
			System.out.print("Wat is de POS? ");
			int pos = Integer.parseInt(sc.next());
			System.out.print("Wat is de HEI? ");
			int hei = Integer.parseInt(sc.next());
			System.out.print("Wat is de Age? ");
			int age = Integer.parseInt(sc.next());
			Goalkeeper keeper = new Goalkeeper(vn,an,"GK",age,0,true,0,0,div,han,kic,ref,spe,pos,hei);
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
