package GUI;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


public class SplashScreen extends Application {
	
	protected static double width, height;
	
	public void start(Stage primaryStage) throws SAXException,IOException {
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		width = screenSize.getWidth();
		height = screenSize.getHeight();
		
		//set up window
		Pane root = new Pane();
		Scene scene = new Scene(root,width, height);
		
		//create background image
		root.getChildren().add(Style.setBackground("/GUI/Resources/background_splashscreen.png"));

/*
		//import css file
		scene.getStylesheets().add(getClass().getResource("/GUI/Resources/splash_screen.css").toExternalForm());
		
		//add css
        t.getStyleClass().add("custom-text");
*/
		
		Text t = new Text("PRESS ANY KEY TO START");
		Style.setTextStyle(t,70);
		t.setLayoutX(1220);
		t.setLayoutY(700);
		
        //When the mouse button is pressed, go to the next StartupMenu screen
        root.setOnMouseClicked(new EventHandler<MouseEvent>() {
             @Override
             public void handle(MouseEvent t) {
                StartupMenu.start(primaryStage);
             }
        });
        
        //Same for when a key is pressed
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
            	StartupMenu.start(primaryStage);
            }
        });
        
        //add text to the canvas and give it a fade in/ fade out effect
		root.getChildren().add(t);
		FadeTransition ft = new FadeTransition(Duration.millis(1000), t);
	     ft.setFromValue(1.0);
	     ft.setToValue(0);
	     ft.setCycleCount(1000);
	     ft.setAutoReverse(true);
	     ft.play();
	     
	     	//Make this scene the primary stage

	     	primaryStage.setFullScreenExitHint("");
			primaryStage.setScene(scene);
			primaryStage.setFullScreen(true);
			primaryStage.show();
			primaryStage.setResizable(false);
			primaryStage.setFullScreen(true);
	}
			
	
	
	/**
	 * Start the program by loading the first screen, the splash screen
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
}
