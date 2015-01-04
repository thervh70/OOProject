package GUI;

import Database.*;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
	
	public static int choice;
	
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
		
		//call the database of teams
		DBmain teams = XmlParser.parseDB();
		
		final ObservableList<Player> data = FXCollections.observableArrayList();
		
		TableView<Player> players = new TableView<Player>();
		players.setEditable(false);
		players.setPrefSize(700, 540);
		players.setLayoutX(1000);
		players.setLayoutY(200);
		players.setEditable(false);
		
		players.setItems(data);
		
		TableColumn name = new TableColumn("Name");
		name.setCellValueFactory(new PropertyValueFactory<Player, String>("name"));
        TableColumn position = new TableColumn("Position");
        position.setCellValueFactory(new PropertyValueFactory<Player, String>("Tpos"));
		TableColumn age = new TableColumn("Age");
        age.setCellValueFactory(new PropertyValueFactory<Player, Integer>("tableAge"));

		TableColumn worth = new TableColumn("Worth");
        worth.setCellValueFactory(new PropertyValueFactory<Player, Integer>("tablePrice"));
        TableColumn pace = new TableColumn("PAC");
        pace.setCellValueFactory(new PropertyValueFactory<Player, Integer>("tablePac"));
		TableColumn shooting = new TableColumn("SHO");
        shooting.setCellValueFactory(new PropertyValueFactory<Player, Integer>("tableSho"));
		TableColumn passing = new TableColumn("PAS");
        passing.setCellValueFactory(new PropertyValueFactory<Player, Integer>("tablePas"));
		TableColumn dribbling = new TableColumn("DRI");
        dribbling.setCellValueFactory(new PropertyValueFactory<Player, Integer>("tableDri"));
		TableColumn defending = new TableColumn("DEF");
        defending.setCellValueFactory(new PropertyValueFactory<Player, Integer>("tableDef"));
		TableColumn physical = new TableColumn("PHY");
        physical.setCellValueFactory(new PropertyValueFactory<Player, Integer>("tablePhy"));
		name.setResizable(false);
		name.setPrefWidth(200);
		position.setResizable(false);
		position.setPrefWidth(100);
		age.setResizable(false);
		age.setPrefWidth(50);
		worth.setResizable(false);
		worth.setPrefWidth(100);
		shooting.setResizable(false);
		shooting.setPrefWidth(50);
		passing.setResizable(false);
		passing.setPrefWidth(50);
		dribbling.setResizable(false);
		dribbling.setPrefWidth(50);
		defending.setResizable(false);
		defending.setPrefWidth(50);
		physical.setResizable(false);
		name.setEditable(false);

		physical.setPrefWidth(50);
		
		//Give the buttons functionality and show the players of each team
				choice = showPlayers(teams, data, ADO);
				choice = showPlayers(teams, data, Ajax);
				choice = showPlayers(teams, data, AZ);
				choice = showPlayers(teams, data, Excelsior);
				choice = showPlayers(teams, data, Dordrecht);
				choice = showPlayers(teams, data, Groningen);
				choice = showPlayers(teams, data, Twente);
				choice = showPlayers(teams, data, Utrecht);
				choice = showPlayers(teams, data, Feyenoord);
				choice = showPlayers(teams, data, Eagles);
				choice = showPlayers(teams, data, Heracles);
				choice = showPlayers(teams, data, NAC);
				choice = showPlayers(teams, data, PEC);
				choice = showPlayers(teams, data, PSV);
				choice = showPlayers(teams, data, Cambuur);
				choice = showPlayers(teams, data, Heerenveen);
				choice = showPlayers(teams, data, Vitesse);
				choice = showPlayers(teams, data, Willem);

		//Add columns to table
		players.getColumns().addAll(name,position,age,worth,shooting,passing,dribbling,defending,physical);
		
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
		root.getChildren().addAll(vbox, vbox2, Continue, Back, players);
		primaryStage.getScene().setRoot(root);
		primaryStage.show();
	}
	
	/* showPlayers(DBmain, Pane, VBox, Button) shows the players from the 
	 * team that is selected by taking the players out of the DBmain
	 * and adding them to a VBox which is added to the Pane.
	 * The method also returns the VBox containing all the players,
	 * names and attributes
	 */
	public static int showPlayers(DBmain teams, ObservableList data, Button name) {
		name.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				data.clear();
				switch(name.getText().toString()){
				case "ADO": choice=0; break;
				case "Ajax": choice=1; break;
				case "AZ": choice=2; break;
				case "Excelsior":  choice=3; break;
				case "FC Dordrecht": choice=4; break;
				case "FC Groningen": choice=5; break;
				case "FC Twente": choice=6; break;
				case "FC Utrecht": choice=7; break;
				case "Feyenoord": choice=8; break;
				case "Go Ahead Eagles": choice=9; break;
				case "Heracles Almelo": choice=10; break;
				case "NAC Breda": choice=11; break;
				case "PEC Zwolle": choice=12; break;
				case "PSV": choice=13; break;
				case "SC Cambuur": choice=14; break;
				case "SC Heerenveen": choice=15; break;
				case "Vitesse": choice=16; break;
				case "Willem II": choice=17; break;
				}
				for (int i = 0; i < teams.getTeam(choice).getSize(); i++){
					data.add(teams.getTeam(choice).getPlayer(i));
				}
			}
		});
		return choice;
	}
}
