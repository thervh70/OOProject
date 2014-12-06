package GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TransferMarket {

	public static void start(Stage primaryStage) {
		Pane root = new Pane();
		Button back = new Button("Back to Management Center");
		
		back.setFont(new Font("Arial", 25));
		back.setLayoutX(80);
		back.setLayoutY(870);
		
		root.getChildren().add(back);
		
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
