package GUI;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import Game.saveGame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoadGame {
	
	public static void start(Stage primaryStage) {
		
		Pane root = new Pane();
		
		root.getChildren().add(Style.setBackground("/GUI/Resources/background_savegame.png"));

		Rectangle r = new Rectangle();
		r.setX(150);
		r.setY(150);
		r.setWidth(700);
		r.setHeight(800);
		r.setArcWidth(20);
		r.setArcHeight(20);
		r.setFill(Color.WHITE);
		root.getChildren().add(r);
		
		Text t = new Text("Choose your game");
		t.setLayoutX(100);
		t.setLayoutY(100);
		Style.setTextStyle(t, 70);
		root.getChildren().add(t);
		
		ArrayList<String> files = new ArrayList<String>();
		try {
			Files.walk(Paths.get("src/saves")).forEach(filePath -> {
			    if (Files.isRegularFile(filePath)) {
			    	String file = filePath.toString();
			    	String[] parts = file.split("\\\\");
			    	String end = parts[2];
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
			buttons[i].setLayoutX(700);
			buttons[i].setLayoutY(150 + i*55);
			buttons[i].setOnAction(new EventHandler<ActionEvent>(){
				public void handle(ActionEvent e){
					saveGame.read(infile);
					LoadingScreen.start(primaryStage);
				}

				
			});
			Style.setButtonStyle(buttons[i], 30);
			
			
			text[i] =new Text(files.get(i));
			Font textFont = new Font("Agency FB", 50);
			text[i].setFont(textFont);
			text[i].setLayoutX(170);
			text[i].setLayoutY(200 + i*55);
			root.getChildren().add(buttons[i]);
			root.getChildren().add(text[i]);
		}
		
		
		
		primaryStage.getScene().setRoot(root);
		primaryStage.show();
	}
}
