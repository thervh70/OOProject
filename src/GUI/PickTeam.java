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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PickTeam{
	
	/* The start method shows the starting team picking screen
	 * 
	 * @param primaryStage - The window shown
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	public static void start(Stage primaryStage) throws SAXException, IOException, ParserConfigurationException {
		Pane root = new Pane();
		
		//Declare a bunch of buttons
		Button ADO = new Button("ADO");
		Button Ajax = new Button("Ajax");
		Button AZ = new Button("AZ");
		Button Excelsior = new Button("Excelsior");
		Button Dordrecht = new Button("FC Dordrecht");
		Button Groningen = new Button("FC Groningen");
		Button Twente = new Button("FC Twente");
		Button Utrecht = new Button("FC Utrecht");
		Button Feyenoord = new Button("Feyenoord");
		Button Eagles = new Button("Go Ahead Eagles");
		Button Heracles = new Button("Heracles Almelo");
		Button NAC = new Button("NAC Breda");
		Button PEC = new Button("PEC Zwolle");
		Button PSV = new Button("PSV");
		Button Cambuur = new Button("SC Cambuur");
		Button Heerenveen = new Button("SC Heerenveen");
		Button Vitesse = new Button("Vitesse");
		Button Willem = new Button("Willem II");
		
		Button Continue = new Button("Continue");
		Button Back = new Button("Back");
		
		Line line = new Line();
		
		line.setEndX(1770);
		line.setLayoutY(820);
		
		Continue.setFont(new Font("Arial", 35));
		Continue.setLayoutX(1480);
		Continue.setLayoutY(850);
		
		Back.setFont(new Font("Arial", 35));
		Back.setLayoutX(50);
		Back.setLayoutY(850);
		
		//Add the team buttons to an HBox, so they are set on 1 line
		HBox hbox = new HBox(5);
		HBox hbox2 = new HBox(5);
		HBox hbox3 = new HBox(5);
		hbox2.setLayoutY(100);
		hbox3.setLayoutY(200);
		hbox.setStyle("-fx-padding: 10;");
		hbox2.setStyle("-fx-padding: 10;");
		hbox3.setStyle("-fx-padding: 10;");
		hbox.getChildren().addAll(ADO, Ajax, AZ, Excelsior, Dordrecht, Groningen); 
		hbox2.getChildren().addAll(Twente, Utrecht, Feyenoord, Eagles, Heracles, NAC);
		hbox3.getChildren().addAll(PEC, PSV, Cambuur, Heerenveen, Vitesse, Willem);
		
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
		root.getChildren().addAll(hbox, hbox2, hbox3, Continue, Back, line);
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
				case "ADO": team = teams.getTeam(0); break;
				case "Ajax": team = teams.getTeam(1); break;
				case "AZ": team = teams.getTeam(2); break;
				case "Excelsior": team = teams.getTeam(3); break;
				case "FC Dordrecht": team = teams.getTeam(4); break;
				case "FC Groningen": team = teams.getTeam(5); break;
				case "FC Twente": team = teams.getTeam(6); break;
				case "FC Utrecht": team = teams.getTeam(7); break;
				case "Feyenoord": team = teams.getTeam(8); break;
				case "Go Ahead Eagles": team = teams.getTeam(9); break;
				case "Heracles Almelo": team = teams.getTeam(10); break;
				case "NAC Breda": team = teams.getTeam(11); break;
				case "PEC Zwolle": team = teams.getTeam(12); break;
				case "PSV": team = teams.getTeam(13); break;
				case "SC Cambuur": team = teams.getTeam(14); break;
				case "SC Heerenveen": team = teams.getTeam(15); break;
				case "Vitesse": team = teams.getTeam(16); break;
				case "Willem II": team = teams.getTeam(17); break;
				}
				for(int i = 0; i < team.getSize(); i++){
					players.getChildren().add(new Text(team.getPlayer(i).toString()));
				}
				root.getChildren().add(players);
			}
		});
		return players;
	}
}
