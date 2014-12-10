package GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TransferMarket {

	public static void start(Stage primaryStage) {
		Pane root = new Pane();
		
		Image background = new Image("/GUI/Resources/background_transfer-market.png");
		ImageView imgView = new ImageView(background);
		root.getChildren().add(imgView);
		
		Button back = new Button("Back to Management Center");
		Text forSale = new Text("For Sale");
		Text myProducts = new Text("My Products :)");
		
		Style.setButtonStyle(back, 45);
		back.setLayoutX(150);
		back.setLayoutY(870);
		
		Style.setTextStyle(forSale, 45);
		forSale.setLayoutX(475);
		forSale.setLayoutY(230);

		Style.setTextStyle(myProducts, 45);
		myProducts.setLayoutX(1275);
		myProducts.setLayoutY(230);

		
		TableView<String> tableForSale = new TableView();
		tableForSale.setEditable(false);
		tableForSale.setPrefSize(800, 540);
		tableForSale.setLayoutX(125);
		tableForSale.setLayoutY(250);
		tableForSale.setEditable(false);
		
		TableView<String> tableMyPlayers = new TableView();
		tableMyPlayers.setPrefSize(800, 540);
		tableMyPlayers.setLayoutX(990);
		tableMyPlayers.setLayoutY(250);
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
		tableMyPlayers.getColumns().addAll(name,position,age,worth,shooting,passing,dribbling,defending,physical, team);

		
		root.getChildren().addAll(back, tableForSale, forSale, tableMyPlayers, myProducts);
		
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
