package GUI;

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
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;

public class SplashScreen extends Application {


	public void start(Stage primaryStage) throws SAXException,IOException {
			/* Pane root = new Pane();
			Pane root2 = new Pane();
			Scene scene = new Scene(root,400,400);

			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			Button t= new Button("Hello FX!");
			Button t2 = new Button("Andere button");
			t.getStyleClass().add("custom-button");
			t2.getStyleClass().add("custom-button");
			t.setOnAction(new EventHandler<ActionEvent>(){
				@Override public void handle(ActionEvent e) {
					scene = new Scene(root2,400,400);
					
					scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					primaryStage.setScene(scene);
					primaryStage.show();
			    }
				
			});
			//root.setCenter(t);
			//t.setLayoutX(50);
			//t.setLayoutY(50);
			
			//Image image = new Image("/application/plaatje.jpg");
	        //ImageView imgView = new ImageView(image);
	        //root.getChildren().add(imgView);

			
		    root.getChildren().add(t);
		    root2.getChildren().add(t2);
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setResizable(false);
			//scene.getStylesheets().add("/application/FirstCSS.css");
			
			
			root.getStyleClass().add("background");
		} catch(Exception e) {
			e.printStackTrace();
		} */
		
		
		StackPane root = new StackPane();
		Scene scene = new Scene(root,1770, 980);

		scene.getStylesheets().add(getClass().getResource("/GUI/Resources/splash_screen.css").toExternalForm());
		
		Image background = new Image("/GUI/Resources/background_splashscreen.png");
		ImageView imgView = new ImageView(background);
        root.getChildren().add(imgView);
        //Text t = new Text(10, 50, "PRESS ANY KEY TO START");
        //t.getStyleClass().add("custom-text");
        //t.setLayoutX(1000);
        //t.setLayoutY(700);
		Button button = new Button("PRESS ANY KEY TO START");
		button.setLayoutY(600);
		button.setAlignment(Pos.BOTTOM_CENTER);
		button.setOnAction(new EventHandler<ActionEvent>(){
			
		@Override
		public void handle(ActionEvent e){
			try {
				PickTeam.start(primaryStage);
			} catch (SAXException | IOException
							| ParserConfigurationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			
		});
		
		//root.getChildren().add(t);
		root.getChildren().add(button);
		FadeTransition ft = new FadeTransition(Duration.millis(3000), button);
	     ft.setFromValue(1.0);
	     ft.setToValue(0);
	     ft.setCycleCount(4);
	     ft.setAutoReverse(true);
	 
	     ft.play();
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setResizable(false);
			

	          
	
	}
			
	
	
	public static void main(String[] args) {
		launch(args);
		
	}
	

	
}
