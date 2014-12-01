package GUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class StartupMenu extends Application {

	@Override
	public void start(Stage primaryStage) {
		Pane startupMenu = new Pane();
		Button newGame = new Button("NEW GAME");
		Button loadGame = new Button("LOAD GAME");
		Button exit = new Button("EXIT");
		newGame.setFont(new Font("Arial", 35));
		loadGame.setFont(new Font("Arial", 35));
		exit.setFont(new Font("Arial", 35));
		newGame.setLayoutX(1400);
		newGame.setLayoutY(600);
		loadGame.setLayoutX(1400);
		loadGame.setLayoutY(700);
		exit.setLayoutX(1400);
		exit.setLayoutY(800);
		
		Scene scene = new Scene(startupMenu, 1770, 980);
		startupMenu.getChildren().addAll(newGame, loadGame, exit);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
