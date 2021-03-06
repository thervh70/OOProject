package View;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import Controller.saveGame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class LoadGame {
	
	/**Shows the LoadGame screen, where the user can pick a save file to load and continue from.
	 * @author D18.1
	 * 
	 * @param primaryStage - the window of the application
	 */
	public static void start(Stage primaryStage) {
		
		Pane root = new Pane();
		
		root.getChildren().add(Style.setBackground("/View/Resources/background_savegame.png"));

		Button Back = new Button("Back");
		Style.setButtonStyle(Back, 45);
		Style.setLocation(Back, 150, 870);
		
		Rectangle r = new Rectangle();
		Style.setLocation(r,150, 200);
		r.setWidth(Style.getNewSize(700));
		r.setHeight(Style.getNewSize(600));
		r.setArcWidth(20);
		r.setArcHeight(20);
		r.setFill(Color.WHITE);
		
		root.getChildren().addAll(r,Back);
		
		ArrayList<String> files = new ArrayList<String>();
		try {
			Files.walk(Paths.get("src/Controller/Saves/")).forEach(filePath -> {
			    if (Files.isRegularFile(filePath)) {
			    	String file = filePath.toString();
			    	String[] parts = file.split("\\\\");
			    	String end = parts[3];
			    	files.add(end);
			    }
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Button[] buttons = new Button[files.size()];
		Text[] text = new Text[files.size()];
		for (int i = 0; i < files.size(); i++) {
			final String infile = files.get(i);
			buttons[i] = new Button("Load game");
			Style.setLocation(buttons[i], 700, (200 + i*55));
			buttons[i].setOnAction(new EventHandler<ActionEvent>(){
				/**Gives the load buttons functionality to read a file and go to the management center with that file on action.
				 * 
				 */
				public void handle(ActionEvent e){
					try{
						saveGame.loadSave(infile);
						ManagementCenter.start(primaryStage);
					} catch (NullPointerException e1){
						Popup warning = Warning.makeWarning("Invalid File", root, r);
						warning.show(primaryStage);
						e1.printStackTrace();
					}
					
				}

				
			});
			Style.setButtonStyle(buttons[i], 30);
			
			
			text[i] =new Text(files.get(i));
			Font font = Font.loadFont(Style.class.getResource("/View/Resources/AGENCYR.TTF").toExternalForm(),Style.getNewSize(50));
			text[i].setFont(font);
			Style.setLocation(text[i], 170, (250 + i*55));
			root.getChildren().add(buttons[i]);
			root.getChildren().add(text[i]);
		}

		Back.setOnAction(new EventHandler<ActionEvent>() {
			/**Gives functionality to the "Back" button and goes to the Startup Menu on action.
			 * @author D18.1
			 * 
			 */
			@Override
			public void handle(ActionEvent e){
				StartupMenu.start(primaryStage);
			}
		});
		
		primaryStage.getScene().setRoot(root);
		primaryStage.show();
	}
}
