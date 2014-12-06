package GUI;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
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
			
	
	}
			
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
