package View;

import Controller.saveGame;
import Model.Fieldplayer;
import Model.Goalkeeper;
import Model.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.scene.control.TableView;
import View.Warning;
import Model.Team;

public class TeamManagement {

	private static HBox hbox;
	private static Text att,def;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void start(Stage primaryStage) {
		Pane root = new Pane();
		
		Button back = new Button("Back to Management Center");
		Style.setButtonStyle(back, 45);
		Style.setLocation(back, 150, 870);
		
		Button switchPlayer = new Button("<->");
		Style.setButtonStyle(switchPlayer, 45);
		Style.setLocation(switchPlayer, 900, 375);
		
		Button switchKeeper = new Button("<->");
		Style.setButtonStyle(switchKeeper, 45);
		Style.setLocation(switchKeeper, 900, 750);
		
		root.getChildren().add(Style.setBackground("/View/Resources/background_team-management.png"));
		
		Text setup = new Text("Set-up");
		Text bench = new Text("Bench");
		Text setupKeeper = new Text("Keepers");
		Text benchKeeper = new Text("Keepers");
		
		Style.setTextStyle(setup, 45);
		Style.setLocation(setup, 455, 230);
		
		Style.setTextStyle(bench, 45);
		Style.setLocation(bench, 1365, 230);
		
		Style.setTextStyle(setupKeeper, 45);
		Style.setLocation(setupKeeper, 450, 680);
		
		Style.setTextStyle(benchKeeper, 45);
		Style.setLocation(benchKeeper, 1350, 680);
		
		att = new Text();
		def = new Text();
		Style.setTextStyle(att, 60);
		Style.setTextStyle(def, 60);
		
		hbox = new HBox(40);
		hbox.getChildren().addAll(att,def);
		Style.setLocation(hbox, 780, 870);
		
		//Create a table for the setup with fixed columns
		TableView<Fieldplayer> tableSelectionField = new TableView<Fieldplayer>();
		tableSelectionField.setEditable(false);
		tableSelectionField.setPrefSize(Style.getNewSize(700), Style.getNewSize(375));
		Style.setLocation(tableSelectionField, 150, 250);
		
		
		//Separate table for Keepers in Field
		TableView<Goalkeeper> tableSelectionKeeper = new TableView<Goalkeeper>();
		tableSelectionKeeper.setEditable(false);
		tableSelectionKeeper.setPrefSize(Style.getNewSize(700), Style.getNewSize(150));
		Style.setLocation(tableSelectionKeeper, 150, 700);
		
		//Create a table for the bench with fixed columns
		TableView<Fieldplayer> tableTeamField = new TableView<Fieldplayer>();
		tableTeamField.setEditable(false);
		tableTeamField.setPrefSize(Style.getNewSize(700), Style.getNewSize(375));
		Style.setLocation(tableTeamField, 1050, 250);
		
		//Additional table for keepers
		TableView<Goalkeeper> tableTeamKeeper = new TableView<Goalkeeper>();
		tableTeamKeeper.setEditable(false);
		tableTeamKeeper.setPrefSize(Style.getNewSize(700), Style.getNewSize(150));
		Style.setLocation(tableTeamKeeper, 1050, 700);
		
		refreshPlayers(tableSelectionField,tableSelectionKeeper,tableTeamField,tableTeamKeeper);
		
		
		
		switchPlayer.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				movePlayer(tableSelectionField, tableTeamField, primaryStage, root);
				refreshPlayers(tableSelectionField, tableSelectionKeeper, tableTeamField, tableTeamKeeper);
			}
		});
		
		switchKeeper.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				moveKeeper(tableSelectionKeeper, tableTeamKeeper, primaryStage, root);
				refreshPlayers(tableSelectionField, tableSelectionKeeper, tableTeamField, tableTeamKeeper);
			}
		});
		
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
        
        //Columns for Selection Fieldplayers
        TableColumn nameS = new TableColumn("Name");
		nameS.setCellValueFactory(new PropertyValueFactory<Player, String>("name"));
        TableColumn positionS = new TableColumn("Position");
        positionS.setCellValueFactory(new PropertyValueFactory<Player, String>("pos"));
		TableColumn ageS = new TableColumn("Age");
        ageS.setCellValueFactory(new PropertyValueFactory<Player, Integer>("age"));
		TableColumn worthS = new TableColumn("Worth");
        worthS.setCellValueFactory(new PropertyValueFactory<Player, Integer>("pri"));
        TableColumn paceS = new TableColumn("PAC");
        paceS.setCellValueFactory(new PropertyValueFactory<Fieldplayer, Integer>("pac"));
		TableColumn shootingS = new TableColumn("SHO");
        shootingS.setCellValueFactory(new PropertyValueFactory<Fieldplayer, Integer>("sho"));
		TableColumn passingS = new TableColumn("PAS");
        passingS.setCellValueFactory(new PropertyValueFactory<Fieldplayer, Integer>("pas"));
		TableColumn dribblingS = new TableColumn("DRI");
        dribblingS.setCellValueFactory(new PropertyValueFactory<Fieldplayer, Integer>("dri"));
		TableColumn defendingS = new TableColumn("DEF");
        defendingS.setCellValueFactory(new PropertyValueFactory<Fieldplayer, Integer>("def"));
		TableColumn physicalS = new TableColumn("PHY");
        physicalS.setCellValueFactory(new PropertyValueFactory<Fieldplayer, Integer>("phy"));
        
        nameS.setResizable(false);
		nameS.setPrefWidth(Style.getNewSize(197));
		positionS.setResizable(false);
		positionS.setPrefWidth(Style.getNewSize(100));
		ageS.setResizable(false);
		ageS.setPrefWidth(Style.getNewSize(50));
		worthS.setResizable(false);
		worthS.setPrefWidth(Style.getNewSize(100));
		shootingS.setResizable(false);
		shootingS.setPrefWidth(Style.getNewSize(50));
		passingS.setResizable(false);
		passingS.setPrefWidth(Style.getNewSize(50));
		dribblingS.setResizable(false);
		dribblingS.setPrefWidth(Style.getNewSize(50));
		defendingS.setResizable(false);
		defendingS.setPrefWidth(Style.getNewSize(50));
		physicalS.setResizable(false);
		physicalS.setPrefWidth(Style.getNewSize(50));
        
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
        		
        //Columns for Selection Goalkeepers
        TableColumn nameKS = new TableColumn("Name");
		nameKS.setCellValueFactory(new PropertyValueFactory<Player, String>("name"));
        TableColumn positionKS = new TableColumn("Position");
        positionKS.setCellValueFactory(new PropertyValueFactory<Player, String>("pos"));
		TableColumn ageKS = new TableColumn("Age");
        ageKS.setCellValueFactory(new PropertyValueFactory<Player, Integer>("age"));
		TableColumn worthKS = new TableColumn("Worth");
        worthKS.setCellValueFactory(new PropertyValueFactory<Player, Integer>("pri"));
        TableColumn divingKS = new TableColumn("DIV");
        divingKS.setCellValueFactory(new PropertyValueFactory<Goalkeeper, Integer>("div"));
        TableColumn handlingKS = new TableColumn("HAN");
        handlingKS.setCellValueFactory(new PropertyValueFactory<Goalkeeper, Integer>("han"));
        TableColumn kickingKS = new TableColumn("KICK");
        kickingKS.setCellValueFactory(new PropertyValueFactory<Goalkeeper, Integer>("kick"));
        TableColumn reflexKS = new TableColumn("REF");
        reflexKS.setCellValueFactory(new PropertyValueFactory<Goalkeeper, Integer>("ref"));
        TableColumn speedKS = new TableColumn("SPD");
        speedKS.setCellValueFactory(new PropertyValueFactory<Goalkeeper, Integer>("spd"));
        TableColumn posKS = new TableColumn("PING");
        posKS.setCellValueFactory(new PropertyValueFactory<Goalkeeper, Integer>("ping"));
        TableColumn heightKS = new TableColumn("HEI");
        heightKS.setCellValueFactory(new PropertyValueFactory<Goalkeeper, Integer>("hei"));		
        
        nameKS.setResizable(false);
		nameKS.setPrefWidth(Style.getNewSize(202));
		positionKS.setResizable(false);
		positionKS.setPrefWidth(Style.getNewSize(100));
		ageKS.setResizable(false);
		ageKS.setPrefWidth(Style.getNewSize(50));
		worthKS.setResizable(false);
		worthKS.setPrefWidth(Style.getNewSize(100));
		divingKS.setResizable(false);
		divingKS.setPrefWidth(Style.getNewSize(35));
		handlingKS.setResizable(false);
		handlingKS.setPrefWidth(Style.getNewSize(35));
		kickingKS.setResizable(false);
		kickingKS.setPrefWidth(Style.getNewSize(35));
		reflexKS.setResizable(false);
		reflexKS.setPrefWidth(Style.getNewSize(35));
		speedKS.setResizable(false);
		speedKS.setPrefWidth(Style.getNewSize(35));
		posKS.setResizable(false);
		posKS.setPrefWidth(Style.getNewSize(35));
		heightKS.setResizable(false);
		heightKS.setPrefWidth(Style.getNewSize(35));

		//Add columns to table
		tableTeamField.getColumns().addAll(name,position,age,worth,shooting,passing,dribbling,defending,physical);
		tableSelectionField.getColumns().addAll(nameS,positionS,ageS,worthS,shootingS,passingS,dribblingS,defendingS,physicalS);
		tableTeamKeeper.getColumns().addAll(nameK,positionK,ageK,worthK,divingK,handlingK,kickingK,reflexK,speedK,posK,heightK);
		tableSelectionKeeper.getColumns().addAll(nameKS,positionKS,ageKS,worthKS,divingKS,handlingKS,kickingKS,reflexKS,speedKS,posKS,heightKS);	
		
		//Add elements to the canvas
		root.getChildren().addAll(back, tableSelectionField, tableTeamField, tableSelectionKeeper, tableTeamKeeper, setup, bench, setupKeeper,benchKeeper);
		root.getChildren().addAll(switchPlayer,switchKeeper, hbox);
		
		back.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				ManagementCenter.start(primaryStage);
			}
		});
		
		
		
		primaryStage.getScene().setRoot(root);
		primaryStage.show();
	}
	
	public static void movePlayer(TableView tableL, TableView tableR, Stage ps, Pane root){
		Player pL = (Player)tableL.getSelectionModel().getSelectedItem();
		Player pR = (Player)tableR.getSelectionModel().getSelectedItem();
		Team myTeam = saveGame.myteam;
		if(pL instanceof Fieldplayer & pR instanceof Fieldplayer){
			myTeam.fromSelection(pL);
			myTeam.toSelection(pR);
			tableL.getSelectionModel().clearSelection();
			tableR.getSelectionModel().clearSelection();
		}
		else{
			Popup wr = Warning.makeWarning("Nothing selected.", root);
			wr.show(ps);
		}
	}
	
	public static void moveKeeper(TableView tableL, TableView tableR, Stage ps, Pane root){
		Player pL = (Player)tableL.getSelectionModel().getSelectedItem();
		Player pR = (Player)tableR.getSelectionModel().getSelectedItem();
		Team myTeam = saveGame.myteam;
		if(pL instanceof Goalkeeper & pR instanceof Goalkeeper){
			myTeam.fromSelection(pL);
			myTeam.toSelection(pR);
			tableL.getSelectionModel().clearSelection();
			tableR.getSelectionModel().clearSelection();
		}
		else{
			Popup wr = Warning.makeWarning("Nothing selected.", root);
			wr.show(ps);
		}
	}
	
	public static void refreshPlayers(TableView<Fieldplayer> tableSelectionField, TableView<Goalkeeper> tableSelectionKeeper, TableView<Fieldplayer> tableTeamField, TableView<Goalkeeper> tableTeamKeeper){
		ObservableList<Fieldplayer> selectionField = FXCollections.observableArrayList();
		for (int i = 0; i < 11; i++) {
			Player p = saveGame.myteam.getSelectionPlayer(i);
			if(p instanceof Fieldplayer)
				selectionField.add((Fieldplayer) p);
		}
		
		tableSelectionField.setItems(selectionField);
		
		ObservableList<Fieldplayer> teamField = FXCollections.observableArrayList();
		for (int j = 0; j < saveGame.myteam.getSize(); j++) {
			Player p = saveGame.myteam.getPlayer(j);
			if(!saveGame.myteam.getSelection().contains(p) && p instanceof Fieldplayer){
				teamField.add((Fieldplayer) p);
			}
		}
		
		tableTeamField.setItems(teamField);
		
		ObservableList<Goalkeeper> selectionKeeper = FXCollections.observableArrayList();
		Goalkeeper g = saveGame.myteam.getSelectionKeeper();
		selectionKeeper.add(g);
		
		tableSelectionKeeper.setItems(selectionKeeper);
		
		ObservableList<Goalkeeper> teamKeeper = FXCollections.observableArrayList();
		for(int i = 0; i < saveGame.myteam.getSize(); i++){
			Player p = saveGame.myteam.getPlayer(i);
			if(!saveGame.myteam.getSelection().contains(p) && p instanceof Goalkeeper){
				teamKeeper.add((Goalkeeper) p);
			}
		}
		
		tableTeamKeeper.setItems(teamKeeper);
		
		att.setText("Att: " + Math.round(saveGame.myteam.calcAttScore()));
		
		def.setText("Def: " + Math.round(saveGame.myteam.calcDefScore()));
		
	}
}
