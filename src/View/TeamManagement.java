package View;

import Controller.saveGame;
import Model.Fieldplayer;
import Model.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TeamManagement {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void start(Stage primaryStage) {
		final ObservableList<Player> data = FXCollections.observableArrayList();
		for (int i = 0; i < saveGame.myteam.getSize(); i++) {
			data.add(saveGame.myteam.getPlayer(i));
		}
		final ObservableList<Player> data2 = FXCollections.observableArrayList(new Fieldplayer("Bankspeler", "ViezeVuile", "bank", 80, 80, 1, 1, 1, 1, 1, 1));
		Pane root = new Pane();
		
		Button back = new Button("Back to Management Center");
		Style.setButtonStyle(back, 45);
		Style.setLocation(back, 150, 870);
		
		Button switchLeftPlayer = new Button("-->");
		Style.setButtonStyle(switchLeftPlayer, 45);
		Style.setLocation(switchLeftPlayer, 900, 375);
		
		Button switchRightPlayer = new Button("<--");
		Style.setButtonStyle(switchRightPlayer, 45);
		Style.setLocation(switchRightPlayer, 900, 450);
		
		Button switchLeftKeeper = new Button("-->");
		Style.setButtonStyle(switchLeftKeeper, 45);
		Style.setLocation(switchLeftKeeper, 900, 700);
		
		Button switchRightKeeper = new Button("<--");
		Style.setButtonStyle(switchRightKeeper, 45);
		Style.setLocation(switchRightKeeper, 900, 775);
		
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
		
		
		
		//Create a table for the setup with fixed columns
		TableView<Player> tableSetupField = new TableView<Player>();
		tableSetupField.setEditable(false);
		tableSetupField.setPrefSize(Style.getNewSize(700), Style.getNewSize(375));
		Style.setLocation(tableSetupField, 150, 250);
		
		tableSetupField.setItems(data);
		
		//Separate table for Keepers in Field
		TableView<Player> tableSetupKeeper = new TableView<Player>();
		tableSetupKeeper.setEditable(false);
		tableSetupKeeper.setPrefSize(Style.getNewSize(700), Style.getNewSize(150));
		Style.setLocation(tableSetupKeeper, 150, 700);
		
		//Create a table for the bench with fixed columns
		TableView<Player> tableBench = new TableView<Player>();
		tableBench.setEditable(false);
		tableBench.setPrefSize(Style.getNewSize(700), Style.getNewSize(375));
		Style.setLocation(tableBench, 1050, 250);
		tableBench.setEditable(false);
		
		tableBench.setItems(data2);

		//Additional table for keepers
		TableView<Player> tableBenchKeeper = new TableView<Player>();
		tableBenchKeeper.setEditable(false);
		tableBenchKeeper.setPrefSize(Style.getNewSize(700), Style.getNewSize(150));
		Style.setLocation(tableBenchKeeper, 1050, 700);
		
		//Creat columns for both tables
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
		name.setPrefWidth(Style.getNewSize(200));
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

		name.setEditable(false);

		physical.setPrefWidth(Style.getNewSize(50));

		//Add columns to table
		tableSetupField.getColumns().addAll(name,position,age,worth,shooting,passing,dribbling,defending,physical);
		tableBench.getColumns().addAll(name,position,age,worth,shooting,passing,dribbling,defending,physical);
		
	
		
		//Add elements to the canvas
		root.getChildren().addAll(back, tableSetupField, tableBench, setup, bench, tableSetupKeeper,tableBenchKeeper, setupKeeper,benchKeeper);
		root.getChildren().addAll(switchLeftPlayer,switchRightPlayer,switchLeftKeeper,switchRightKeeper);
		
		back.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				ManagementCenter.start(primaryStage);
			}
		});
		
		primaryStage.getScene().setRoot(root);
		primaryStage.show();
	}
}
