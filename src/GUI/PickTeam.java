package GUI;

import Database.*;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PickTeam{
	
	public static int choice = 0;
	
	/* The start method shows the starting team picking screen
	 * 
	 * @param primaryStage - The window shown
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	public static void start(Stage primaryStage) throws SAXException, IOException, ParserConfigurationException {
		Pane root = new Pane();
		
		root.getChildren().add(Style.setBackground("/GUI/Resources/background_pick-team.png"));

		int size = 35;
		
		//Declare a bunch of buttons
		Button ADO = new Button("ADO");								Style.setButtonStyle(ADO, size);
		Button Ajax = new Button("Ajax");							Style.setButtonStyle(Ajax, size);
		Button AZ = new Button("AZ");								Style.setButtonStyle(AZ, size);
		Button Excelsior = new Button("Excelsior");					Style.setButtonStyle(Excelsior, size);
		Button Dordrecht = new Button("FC Dordrecht");				Style.setButtonStyle(Dordrecht, size);
		Button Groningen = new Button("FC Groningen");				Style.setButtonStyle(Groningen, size);
		Button Twente = new Button("FC Twente");					Style.setButtonStyle(Twente, size);
		Button Utrecht = new Button("FC Utrecht");					Style.setButtonStyle(Utrecht, size);
		Button Feyenoord = new Button("Feyenoord");					Style.setButtonStyle(Feyenoord, size);
		Button Eagles = new Button("Go Ahead Eagles");				Style.setButtonStyle(Eagles, size);
		Button Heracles = new Button("Heracles Almelo");			Style.setButtonStyle(Heracles, size);
		Button NAC = new Button("NAC Breda");						Style.setButtonStyle(NAC, size);
		Button PEC = new Button("PEC Zwolle");						Style.setButtonStyle(PEC, size);
		Button PSV = new Button("PSV");								Style.setButtonStyle(PSV, size);
		Button Cambuur = new Button("SC Cambuur");					Style.setButtonStyle(Cambuur, size);
		Button Heerenveen = new Button("SC Heerenveen");			Style.setButtonStyle(Heerenveen, size);
		Button Vitesse = new Button("Vitesse");						Style.setButtonStyle(Vitesse, size);
		Button Willem = new Button("Willem II");					Style.setButtonStyle(Willem, size);
		
		Button Continue = new Button("Continue");
		Button Back = new Button("Back");
		
		Style.setButtonStyle(Continue, 45);
		Continue.setLayoutX(1480);
		Continue.setLayoutY(850);
		
		Style.setButtonStyle(Back, 45);
		Back.setLayoutX(150);
		Back.setLayoutY(870);
		
		//Add the team buttons to an VBox
		VBox vbox = new VBox(2);
		vbox.setLayoutX(150);
		vbox.setLayoutY(200);
		
		VBox vbox2 = new VBox(2);
		vbox2.setLayoutX(400);
		vbox2.setLayoutY(200);
		
		vbox.getChildren().addAll(ADO, Ajax, AZ, Excelsior, Dordrecht, Groningen,Twente, Utrecht, Feyenoord); 
		vbox2.getChildren().addAll(Eagles, Heracles, NAC,PEC, PSV, Cambuur, Heerenveen, Vitesse, Willem);
		
		//initialize a VBox to list the players per team, get the database to get the players
		VBox players = new VBox(5);
		players.setLayoutX(1000);
		DBmain teams = XmlParser.parseDB();
				
		//Give the buttons functionality and show the players of each team
		players = showPlayers(teams, root, players, ADO);
		players = showPlayers(teams, root, players, Ajax);
		players = showPlayers(teams, root, players, AZ);
		players = showPlayers(teams, root, players, Excelsior);
		players = showPlayers(teams, root, players, Dordrecht);
		players = showPlayers(teams, root, players, Groningen);
		players = showPlayers(teams, root, players, Twente);
		players = showPlayers(teams, root, players, Utrecht);
		players = showPlayers(teams, root, players, Feyenoord);
		players = showPlayers(teams, root, players, Eagles);
		players = showPlayers(teams, root, players, Heracles);
		players = showPlayers(teams, root, players, NAC);
		players = showPlayers(teams, root, players, PEC);
		players = showPlayers(teams, root, players, PSV);
		players = showPlayers(teams, root, players, Cambuur);
		players = showPlayers(teams, root, players, Heerenveen);
		players = showPlayers(teams, root, players, Vitesse);
		players = showPlayers(teams, root, players, Willem);
		
		//Give click functionality to "Continue", directs to "Management Center"
		Continue.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e){
				ManagementCenter.start(primaryStage);
			}
		});

		//Give click functionality to "Back", directs to "Startup Menu"
		Back.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e){
				StartupMenu.start(primaryStage);
			}
		});
		
		//Add everything to the window and show it
		root.getChildren().addAll(vbox, vbox2, Continue, Back,players);
		primaryStage.getScene().setRoot(root);
		primaryStage.show();
	}
	
	/* showPlayers(DBmain, Pane, VBox, Button) shows the players from the 
	 * team that is selected by taking the players out of the DBmain
	 * and adding them to a VBox which is added to the Pane.
	 * The method also returns the VBox containing all the players,
	 * names and attributes
	 */
	public static VBox showPlayers(DBmain teams, Pane root, VBox players, Button name) {
		name.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				players.getChildren().clear();
				Team team = new Team(null, 0, 0);
				switch(name.getText().toString()){
				case "ADO": team = teams.getTeam(0); choice=0; break;
				case "Ajax": team = teams.getTeam(1); choice=1; break;
				case "AZ": team = teams.getTeam(2); choice=2; break;
				case "Excelsior": team = teams.getTeam(3); choice=3; break;
				case "FC Dordrecht": team = teams.getTeam(4); choice=4; break;
				case "FC Groningen": team = teams.getTeam(5); choice=5; break;
				case "FC Twente": team = teams.getTeam(6); choice=6; break;
				case "FC Utrecht": team = teams.getTeam(7); choice=7; break;
				case "Feyenoord": team = teams.getTeam(8); choice=8; break;
				case "Go Ahead Eagles": team = teams.getTeam(9); choice=9; break;
				case "Heracles Almelo": team = teams.getTeam(10); choice=10; break;
				case "NAC Breda": team = teams.getTeam(11); choice=11; break;
				case "PEC Zwolle": team = teams.getTeam(12); choice=12; break;
				case "PSV": team = teams.getTeam(13); choice=13; break;
				case "SC Cambuur": team = teams.getTeam(14); choice=14; break;
				case "SC Heerenveen": team = teams.getTeam(15); choice=15; break;
				case "Vitesse": team = teams.getTeam(16); choice=16; break;
				case "Willem II": team = teams.getTeam(17); choice=17; break;
				}
				for(int i = 0; i < team.getSize(); i++){
					players.getChildren().add(new Text(team.getPlayer(i).toString()));
				}
			}
		});
		return players;
	}
}
