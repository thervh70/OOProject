package GUI;

import Database.Fieldplayer;
import Database.Player;
import Database.*;
import Game.saveGame;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TeamManagement {

	public static void start(Stage primaryStage) {
		final ObservableList<Player> data = FXCollections.observableArrayList();
		for (int i = 0; i < saveGame.myteam.getSize(); i++) {
			data.add(saveGame.myteam.getPlayer(i));
		}
		Pane root = new Pane();
		
		Button back = new Button("Back to Management Center");
		Style.setButtonStyle(back, 45);
		back.setLayoutX(150);
		back.setLayoutY(870);
		
		Image background = new Image("/GUI/Resources/background_team-management.png");
		ImageView imgView = new ImageView(background);
		root.getChildren().add(imgView);
		
		Text setup = new Text("Set-up");
		Text bench = new Text("Bench");
		
		Style.setTextStyle(setup, 45);
		setup.setLayoutX(475);
		setup.setLayoutY(230);
		
		Style.setTextStyle(bench, 45);
		bench.setLayoutX(1325);
		bench.setLayoutY(230);
		
		//Create a table for the setup with fixed columns
		TableView<Player> tableSetup = new TableView<Player>();
		tableSetup.setEditable(false);
		tableSetup.setPrefSize(700, 540);
		tableSetup.setLayoutX(175);
		tableSetup.setLayoutY(250);
		tableSetup.setEditable(false);
		
		tableSetup.setItems(data);

		
		//Create a table for the bench with fixed columns
		TableView<String> tableBench = new TableView();
		tableBench.setEditable(false);
		tableBench.setPrefSize(700, 540);
		tableBench.setLayoutX(1025);
		tableBench.setLayoutY(250);
		tableBench.setEditable(false);
		
		//Creat columns for both tables
		TableColumn name = new TableColumn("Name");
		name.setCellValueFactory(new PropertyValueFactory<Player, String>("name"));
        TableColumn position = new TableColumn("Position");
        position.setCellValueFactory(new PropertyValueFactory<Player, String>("Tpos"));
        tableSetup.getColumns().addAll(name, position);
        /*
		TableColumn age = new TableColumn("Age");
        age.setCellFactory(new PropertyValueFactory<Player, Integer>("age"));
		TableColumn worth = new TableColumn("Worth");
        worth.setCellFactory(new PropertyValueFactory<Player, Integer>("pri"));
		TableColumn pace = new TableColumn("PAC");
        pace.setCellFactory(new PropertyValueFactory<Player, Integer>("pac"));
		TableColumn shooting = new TableColumn("SHO");
        shooting.setCellFactory(new PropertyValueFactory<Player, Integer>("sho"));
		TableColumn passing = new TableColumn("PAS");
        passing.setCellFactory(new PropertyValueFactory<Player, Integer>("pas"));
		TableColumn dribbling = new TableColumn("DRI");
        dribbling.setCellFactory(new PropertyValueFactory<Player, Integer>("dri"));
		TableColumn defending = new TableColumn("DEF");
        defending.setCellFactory(new PropertyValueFactory<Player, Integer>("def"));
		TableColumn physical = new TableColumn("PHY");
        physical.setCellFactory(new PropertyValueFactory<Player, Integer>("phy"));
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

		//Add columns to table
		tableSetup.getColumns().addAll(name,position,age,worth,shooting,passing,dribbling,defending,physical);
		tableBench.getColumns().addAll(name,position,age,worth,shooting,passing,dribbling,defending,physical);*/
		
	
		
		//Add elements to the canvas
		root.getChildren().addAll(back, tableSetup, tableBench, setup, bench);
		
		back.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				ManagementCenter.start(primaryStage);
			}
		});
		
		primaryStage.getScene().setRoot(root);
		primaryStage.show();
		
		System.out.println(saveGame.getMyTeam().getPlayer(1).getFnm());
	}
}
