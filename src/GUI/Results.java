package GUI;

import GUI.ManagementCenter;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Results {

	public static void start(Stage primaryStage) {
		Pane root = new Pane();
		Button back = new Button("Back to Management Center");
		Text a = new Text("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor");
		Text b = new Text("incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud ");
		Text c = new Text("exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure ");
		Text d = new Text("dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.");
		Text e = new Text("Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt");
		Text f = new Text("mollit anim id est laborum");
		
		VBox table = new VBox(15);
		table.setPrefSize(1370, 660);
		table.setBackground(new Background(new BackgroundFill(Color.CORNSILK, CornerRadii.EMPTY, Insets.EMPTY)));
		table.setLayoutX(200);
		table.setLayoutY(160);
		
		table.getChildren().addAll(new Text("This box will show the results till now"), new Text("With stuff shown in a VBox"), new Text("More stuff"), a, b, c, d, e, f);
		
		back.setFont(new Font("Arial", 25));
		back.setLayoutX(80);
		back.setLayoutY(870);
		
		root.getChildren().addAll(back, table);
		
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
