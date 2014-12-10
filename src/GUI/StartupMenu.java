package GUI;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StartupMenu {
	
	/*The method start shows the startup menu for the new 
	 * up and coming game Frits!
	 * 
	 * @param primaryStage - The window
	 */
	public static void start(Stage primaryStage) {
		Pane root = new Pane();
		Button newGame = new Button("NEW GAME");
		Button loadGame = new Button("LOAD GAME");
		Button exit = new Button("EXIT");
		
		Style.setButtonStyle(newGame, 55);
		Style.setButtonStyle(loadGame, 55);
		Style.setButtonStyle(exit, 55);

		/*
		newGame.setLayoutX(1400);
		newGame.setLayoutY(600);
		loadGame.setLayoutX(1400);
		loadGame.setLayoutY(700);
		exit.setLayoutX(1400);
		exit.setLayoutY(800);
		*/
		
		VBox vbox = new VBox(5);
		vbox.getChildren().addAll(newGame,loadGame,exit);
		vbox.setLayoutX(1360);
		vbox.setLayoutY(590);
		vbox.setAlignment(Pos.CENTER);
		
		Image background = new Image("/GUI/Resources/background_splashscreen.png");
		ImageView imgView = new ImageView(background);
		root.getChildren().add(imgView);
		
		//Give click functionality to "New Game", directs to Team Picking 
		newGame.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e){
				try {
					PickTeam.start(primaryStage);
				} catch (SAXException | IOException
						| ParserConfigurationException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		//Give click functionality to "Load Game", directs to "Management Center"
		loadGame.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				ManagementCenter.start(primaryStage);
			}
		});
		
		//Give click functionality to "Exit", closes the window
		exit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				primaryStage.close();
			}
		});
		
		root.getChildren().addAll(vbox);
		primaryStage.getScene().setRoot(root);
		primaryStage.show();

	}
}
