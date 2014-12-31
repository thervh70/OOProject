package View;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;


import org.xml.sax.SAXException;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


public class SplashScreen extends Application {
	
	public void start(Stage primaryStage) throws SAXException,IOException {
		
		Style.setScreen();
		
		//set up window
		Pane root = new Pane();
		Scene scene = new Scene(root,Style.getWidth(), Style.getHeight());
		
		//create background image
		root.getChildren().add(Style.setBackground("/View/Resources/background_splashscreen.png"));

		Text t = new Text("PRESS ANY KEY TO START");
		Style.setTextStyle(t,70);
		Style.setLocation(t,1220,700);
		
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
