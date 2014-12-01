package GUI;
	
import javafx.application.Application;
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
		try {
			Pane root = new Pane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			Button t= new Button("Hello FX!");
			t.getStyleClass().add("custom-button");
			//root.setCenter(t);
			//t.setLayoutX(50);
			//t.setLayoutY(50);
			
			//Image image = new Image("/application/plaatje.jpg");
	        //ImageView imgView = new ImageView(image);
	        //root.getChildren().add(imgView);

			
		    root.getChildren().add(t);
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setResizable(false);
			//scene.getStylesheets().add("/application/FirstCSS.css");
			
			
			root.getStyleClass().add("background");
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
