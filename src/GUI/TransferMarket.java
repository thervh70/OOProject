package GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TransferMarket {

	public static void start(Stage primaryStage) {
		Pane root = new Pane();
		Button back = new Button("Back to Management Center");
		Text forSale = new Text("For Sale");
		Text myProducts = new Text("My Products :)");
		Font font = new Font("Arial", 35);
		Line line = new Line();
		
		line.setEndX(1770);
		line.setLayoutY(820);
		
		forSale.setFont(font);
		forSale.setLayoutX(105);
		forSale.setLayoutY(90);
		myProducts.setFont(font);
		myProducts.setLayoutX(1005);
		myProducts.setLayoutY(90);
		
		back.setFont(new Font("Arial", 25));
		back.setLayoutX(80);
		back.setLayoutY(870);
		
		TableView<String> tableForSale = new TableView();
		tableForSale.setEditable(false);
		tableForSale.setPrefSize(801, 540);
		tableForSale.setLayoutX(100);
		tableForSale.setLayoutY(100);
		tableForSale.setEditable(false);
		
		TableView<String> tableMyPlayers = new TableView();
		tableMyPlayers.setPrefSize(720, 540);
		tableMyPlayers.setLayoutX(1000);
		tableMyPlayers.setLayoutY(100);
		tableMyPlayers.setEditable(false);
		
		TableColumn name = new TableColumn("Name");
        TableColumn position = new TableColumn("Position");
		TableColumn age = new TableColumn("Age");
		TableColumn worth = new TableColumn("Worth");
		TableColumn shooting = new TableColumn("Shooting");
		TableColumn passing = new TableColumn("Passing");
		TableColumn dribbling = new TableColumn("Dribbling");
		TableColumn defending = new TableColumn("Defending");
		TableColumn physical = new TableColumn("Physical");
		TableColumn price = new TableColumn("Price");
		TableColumn team = new TableColumn("Team");
		name.setResizable(false);
		position.setResizable(false);
		age.setResizable(false);
		worth.setResizable(false);
		shooting.setResizable(false);
		passing.setResizable(false);
		dribbling.setResizable(false);
		defending.setResizable(false);
		physical.setResizable(false);
		price.setResizable(false);
		
		tableForSale.getColumns().addAll(name,position,age,worth,shooting,passing,dribbling,defending,physical, team);
		tableMyPlayers.getColumns().addAll(name,position,age,worth,shooting,passing,dribbling,defending,physical);

		
		root.getChildren().addAll(back, tableForSale, forSale, tableMyPlayers, myProducts, line);
		
		back.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				ManagementCenter.start(primaryStage);
			}
		});
		
		Scene scene = new Scene(root, 1770, 980);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
