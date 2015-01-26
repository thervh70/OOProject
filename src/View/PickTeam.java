package View;

import Controller.saveGame;
import Model.DBmain;
import Model.Fieldplayer;
import Model.Goalkeeper;
import Model.Player;
import Model.Team;
import Model.XmlParser;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class PickTeam{
	
	private static int choice = -1;
	private static TableView<Fieldplayer> tableTeamField;
	private static TableView<Goalkeeper> tableTeamKeeper;
	
	/**This shows the picking screen where you can pick your initial team.
	 * @author D18.1
	 * 
	 * @param primaryStage - The window shown 
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void start(Stage primaryStage) throws SAXException, IOException, ParserConfigurationException {
		Pane root = new Pane();
		
		choice = -1;
		
		//Create a table for the bench with fixed columns
		tableTeamField = new TableView<Fieldplayer>();
		tableTeamField.setEditable(false);
		tableTeamField.setPrefSize(Style.getNewSize(700), Style.getNewSize(375));
		Style.setLocation(tableTeamField, 1050, 250);

		//Additional table for keepers
		tableTeamKeeper = new TableView<Goalkeeper>();
		tableTeamKeeper.setEditable(false);
		tableTeamKeeper.setPrefSize(Style.getNewSize(700), Style.getNewSize(150));
		Style.setLocation(tableTeamKeeper, 1050, 700);
		
		//Columns for Team Fieldplayers
		TableColumn name = new TableColumn("Name");
		name.setCellValueFactory(new PropertyValueFactory<Player, String>("name"));
        TableColumn position = new TableColumn("Position");
        position.setCellValueFactory(new PropertyValueFactory<Player, String>("pos"));
		TableColumn age = new TableColumn("Age");
        age.setCellValueFactory(new PropertyValueFactory<Player, Integer>("age"));
		TableColumn worth = new TableColumn("Worth");
        worth.setCellValueFactory(new PropertyValueFactory<Player, Integer>("pri"));
        TableColumn pace = new TableColumn("PAC");
        pace.setCellValueFactory(new PropertyValueFactory<Fieldplayer, Integer>("pac"));
		TableColumn shooting = new TableColumn("SHO");
		shooting.setCellValueFactory(new PropertyValueFactory<Fieldplayer, Integer>("sho"));
		TableColumn passing = new TableColumn("PAS");
		passing.setCellValueFactory(new PropertyValueFactory<Fieldplayer, Integer>("pas"));
		TableColumn dribbling = new TableColumn("DRI");
		dribbling.setCellValueFactory(new PropertyValueFactory<Fieldplayer, Integer>("dri"));
		TableColumn defending = new TableColumn("DEF");
		defending.setCellValueFactory(new PropertyValueFactory<Fieldplayer, Integer>("def"));
		TableColumn physical = new TableColumn("PHY");
        physical.setCellValueFactory(new PropertyValueFactory<Fieldplayer, Integer>("phy"));
        
        name.setResizable(false);
		name.setPrefWidth(Style.getNewSize(197));
		position.setResizable(false);
		position.setPrefWidth(Style.getNewSize(100));
		age.setResizable(false);
		age.setPrefWidth(Style.getNewSize(50));
		worth.setResizable(false);
		worth.setPrefWidth(Style.getNewSize(100));
		shooting.setResizable(false);
		shooting.setPrefWidth(Style.getNewSize(50));
		passing.setResizable(false);
		passing.setPrefWidth(Style.getNewSize(50));
		dribbling.setResizable(false);
		dribbling.setPrefWidth(Style.getNewSize(50));
		defending.setResizable(false);
		defending.setPrefWidth(Style.getNewSize(50));
		physical.setResizable(false);
		physical.setPrefWidth(Style.getNewSize(50));
		
		//Columns for Team Goalkeepers
        TableColumn nameK = new TableColumn("Name");
		nameK.setCellValueFactory(new PropertyValueFactory<Player, String>("name"));
        TableColumn positionK = new TableColumn("Position");
        positionK.setCellValueFactory(new PropertyValueFactory<Player, String>("pos"));
		TableColumn ageK = new TableColumn("Age");
        ageK.setCellValueFactory(new PropertyValueFactory<Player, Integer>("age"));
		TableColumn worthK = new TableColumn("Worth");
        worthK.setCellValueFactory(new PropertyValueFactory<Player, Integer>("pri"));
        TableColumn divingK = new TableColumn("DIV");
        divingK.setCellValueFactory(new PropertyValueFactory<Goalkeeper, Integer>("div"));
        TableColumn handlingK = new TableColumn("HAN");
        handlingK.setCellValueFactory(new PropertyValueFactory<Goalkeeper, Integer>("han"));
        TableColumn kickingK = new TableColumn("KICK");
        kickingK.setCellValueFactory(new PropertyValueFactory<Goalkeeper, Integer>("kick"));
        TableColumn reflexK = new TableColumn("REF");
        reflexK.setCellValueFactory(new PropertyValueFactory<Goalkeeper, Integer>("ref"));
        TableColumn speedK = new TableColumn("SPD");
        speedK.setCellValueFactory(new PropertyValueFactory<Goalkeeper, Integer>("spd"));
        TableColumn posK = new TableColumn("PING");
        posK.setCellValueFactory(new PropertyValueFactory<Goalkeeper, Integer>("ping"));
        TableColumn heightK = new TableColumn("HEI");
        heightK.setCellValueFactory(new PropertyValueFactory<Goalkeeper, Integer>("hei"));
        
        nameK.setResizable(false);
		nameK.setPrefWidth(Style.getNewSize(202));
		positionK.setResizable(false);
		positionK.setPrefWidth(Style.getNewSize(100));
		ageK.setResizable(false);
		ageK.setPrefWidth(Style.getNewSize(50));
		worthK.setResizable(false);
		worthK.setPrefWidth(Style.getNewSize(100));
		divingK.setResizable(false);
		divingK.setPrefWidth(Style.getNewSize(35));
		handlingK.setResizable(false);
		handlingK.setPrefWidth(Style.getNewSize(35));
		kickingK.setResizable(false);
		kickingK.setPrefWidth(Style.getNewSize(35));
		reflexK.setResizable(false);
		reflexK.setPrefWidth(Style.getNewSize(35));
		speedK.setResizable(false);
		speedK.setPrefWidth(Style.getNewSize(35));
		posK.setResizable(false);
		posK.setPrefWidth(Style.getNewSize(35));
		heightK.setResizable(false);
		heightK.setPrefWidth(Style.getNewSize(35));
		
		tableTeamField.getColumns().addAll(name,position,age,worth,shooting,passing,dribbling,defending,physical);
		tableTeamKeeper.getColumns().addAll(nameK,positionK,ageK,worthK,divingK,handlingK,kickingK,reflexK,speedK,posK,heightK);
		
		root.getChildren().add(Style.setBackground("/View/Resources/background_pick-team.png"));
		

		Text Fieldplayers = new Text("Fieldplayers");
		Style.setTextStyle(Fieldplayers, 45);
		Style.setLocation(Fieldplayers, 1320, 230);
		
		Text Keepers = new Text("Keepers");
		Style.setTextStyle(Keepers, 45);
		Style.setLocation(Keepers, 1350, 680);

		double size = 45;
		
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
		Style.setLocation(Continue, 1480, 870);
		
		Style.setButtonStyle(Back, 45);
		Style.setLocation(Back, 150, 870);
		
		//Add the team buttons to an VBox
		VBox vbox = new VBox(-13);
		Style.setLocation(vbox, 150, 200);
		
		VBox vbox2 = new VBox(-13);
		Style.setLocation(vbox2, 400, 200);
		
		vbox.getChildren().addAll(ADO, Ajax, AZ, Excelsior, Dordrecht, Groningen,Twente, Utrecht, Feyenoord); 
		vbox2.getChildren().addAll(Eagles, Heracles, NAC,PEC, PSV, Cambuur, Heerenveen, Vitesse, Willem);
		
		//initialize a VBox to list the players per team, get the database to get the players
		VBox players = new VBox(5);
		players.setLayoutX(Style.getNewSize(1000));
		DBmain teams = XmlParser.parseDB();
				
		//Give the buttons functionality and show the players of each team
		showPlayers(teams, root, ADO);
		showPlayers(teams, root, Ajax);
		showPlayers(teams, root, AZ);
		showPlayers(teams, root, Excelsior);
		showPlayers(teams, root, Dordrecht);
		showPlayers(teams, root, Groningen);
		showPlayers(teams, root, Twente);
		showPlayers(teams, root, Utrecht);
		showPlayers(teams, root, Feyenoord);
		showPlayers(teams, root, Eagles);
		showPlayers(teams, root, Heracles);
		showPlayers(teams, root, NAC);
		showPlayers(teams, root, PEC);
		showPlayers(teams, root, PSV);
		showPlayers(teams, root, Cambuur);
		showPlayers(teams, root, Heerenveen);
		showPlayers(teams, root, Vitesse);
		showPlayers(teams, root, Willem);
		
		//Give click functionality to "Continue", directs to "Management Center"
		Continue.setOnAction(new EventHandler<ActionEvent>() {
			/**Gives functionality to the "Continue" button to throw a warning if the user has not yet selected a team.
			 * If the user has selected a team, the button will transition to the Management Center screen on mouse click.
			 * @author D18.1
			 * 
			 */
			@Override
			public void handle(ActionEvent e){
				if(choice == -1){
					Popup warning = Warning.makeWarning("No team selected", root);
					warning.show(primaryStage);
				}
				else{
					saveGame.newSave(teams.getTeam(choice));
					ManagementCenter.start(primaryStage);
				}
			}
		});

		//Give click functionality to "Back", directs to "Startup Menu"
		Back.setOnAction(new EventHandler<ActionEvent>() {
			/**Gives functionality to the "Back" button to set the choice parameter back to deselect selected teams 
			 * and transition back to the startup menu on mouse click.
			 * @author D18.1
			 * 
			 */
			@Override
			public void handle(ActionEvent e){
				choice = -1;
				StartupMenu.start(primaryStage);
			}
		});
		
		//Add everything to the window and show it
		root.getChildren().addAll(vbox, vbox2, Continue, Back,players,tableTeamField,tableTeamKeeper,Keepers,Fieldplayers);
		primaryStage.getScene().setRoot(root);
		primaryStage.show();
	}
	
	/**Gives functionality to the team buttons, so the user can choose his/her team
	 * Also shows the players of the team in a table
	 * @author D18.1
	 * 
	 */
	public static void showPlayers(DBmain teams, Pane root, Button name) {
		name.setOnAction(new EventHandler<ActionEvent>() {
			
			@SuppressWarnings("unused")
			@Override
			public void handle(ActionEvent e) {
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
				
				Team t = teams.getTeam(choice);
				
				ObservableList<Fieldplayer> teamField = FXCollections.observableArrayList();
				for (int j = 0; j < t.getSize(); j++) {
					Player p = t.getPlayer(j);
					if(p instanceof Fieldplayer){
						teamField.add((Fieldplayer) p);
					}
				}
				
				tableTeamField.setItems(teamField);
				
				ObservableList<Goalkeeper> teamKeeper = FXCollections.observableArrayList();
				for(int i = 0; i < t.getSize(); i++){
					Player p = t.getPlayer(i);
					if(p instanceof Goalkeeper){
						teamKeeper.add((Goalkeeper) p);
					}
				}
				
				tableTeamKeeper.setItems(teamKeeper);
			}
		});
	}
}
