package GUI;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class StartupMenu {

	public static void start(Stage primaryStage) {
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
		
		loadGame.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				ManagementCenter.start(primaryStage);
			}
		});
		
		exit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				primaryStage.close();
			}
		});
		
		Scene scene = new Scene(startupMenu, 1770, 980);
		startupMenu.getChildren().addAll(newGame, loadGame, exit);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void back(Stage primaryStage){
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
		
		loadGame.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				ManagementCenter.start(primaryStage);
			}
		});
		
		exit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				primaryStage.close();
			}
		});
		
		Scene scene = new Scene(startupMenu, 1770, 980);
		startupMenu.getChildren().addAll(newGame, loadGame, exit);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
