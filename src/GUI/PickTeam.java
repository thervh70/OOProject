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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PickTeam{

	public static void start(Stage primaryStage) throws SAXException, IOException, ParserConfigurationException {
		Pane root = new Pane();
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
		
		Continue.setFont(new Font("Arial", 35));
		Continue.setLayoutX(1480);
		Continue.setLayoutY(850);
		
		Back.setFont(new Font("Arial", 35));
		Back.setLayoutX(50);
		Back.setLayoutY(850);
		
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
		
		VBox players = new VBox(5);
		players.setLayoutX(1000);
		DBmain teams = XmlParser.parseDB();
		
		root.getChildren().addAll(hbox, hbox2, hbox3, Continue, Back);
		
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
		
		Continue.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e){
				ManagementCenter.start(primaryStage);
			}
		});
		
		Back.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e){
				StartupMenu.back(primaryStage);
			}
		});
		
		Scene scene = new Scene(root, 1770, 980);
		primaryStage.setScene(scene);
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
				Team team = teams.getT(0);
				for(int i = 0; i < team.getSize(); i++){
					players.getChildren().add(new Text(team.getPlayer(i).toString()));
				}
				root.getChildren().add(players);
			}
		});
		return players;
	}
}
