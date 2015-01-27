package View;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class StartupMenu {
	
	/**This shows the startupmenu where you can choose between a new game, to load a game or to exit the game.
	 * @author D18.1
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

		VBox vbox = new VBox(5);
		vbox.getChildren().addAll(newGame,loadGame,exit);
		
		Style.setLocation(vbox, 1360, 590);
		
		vbox.setAlignment(Pos.CENTER);
		
		root.getChildren().add(Style.setBackground("/View/Resources/background_splashscreen.png"));

		
		newGame.setOnAction(new EventHandler<ActionEvent>(){
			/**Gives functionality to the "New Game" button to transition to the team picking screen on mouse click.
			 * @author D18.1
			 * 
			 */
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
			/**Gives functionality to the "Load Game" button to transition to the LoadGame screen on mouse click.
			 * @author D18.1
			 * 
			 */
			@Override
			public void handle(ActionEvent e) {
				LoadGame.start(primaryStage);
			}
		});
		
		exit.setOnAction(new EventHandler<ActionEvent>() {
			/**Gives functionality to the "Exit" button to exit the application on mouse click.
			 * @author D18.1
			 * 
			 */
			@Override
			public void handle(ActionEvent e) {

				EventHandler<MouseEvent> yes = new EventHandler<MouseEvent>() {
					
					public void handle(MouseEvent e) {
						root.setDisable(false);
						primaryStage.close();
					}
				};
				
				EventHandler<MouseEvent> no = new EventHandler<MouseEvent>() {
					
					public void handle(MouseEvent e) {
						;
					}
				};
				
				Popup confirm = Warning.makeWarning("Do you really want to end \nthis presentation?", root, yes, no);
				confirm.show(primaryStage);	
			}
		});
		primaryStage.getScene().setOnKeyPressed(null);
		root.getChildren().addAll(vbox);
		primaryStage.getScene().setRoot(root);
		primaryStage.show();

	}
}
