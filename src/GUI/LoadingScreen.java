package GUI;

import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;


public class LoadingScreen {


	private static boolean done = false;

	public static void start(Stage primaryStage) {
		Pane root = new Pane();

		Color color = Color.BLACK;
		CornerRadii corner = new CornerRadii(0);
		Insets inset = new Insets(0);
		BackgroundFill fill = new BackgroundFill(color, corner, inset);
		Background black = new Background(fill);
		
		root.setBackground(black);
		
		Image logo = new Image("/GUI/Resources/Frits_logo.png");
		ImageView logo_img = new ImageView(logo);
		logo_img.setLayoutX(100);
		logo_img.setLayoutY(800);
		logo_img.setFitHeight(200);
		logo_img.setFitWidth(200);
		
		RotateTransition frits = new RotateTransition(Duration.millis(2500), logo_img);
	    frits.setByAngle(360);
	    frits.setCycleCount(Integer.MAX_VALUE);
	    frits.setAutoReverse(false);
		frits.play();
		root.getChildren().add(logo_img);
		
		primaryStage.getScene().setRoot(root);
		primaryStage.show();
		
        root.setOnMouseClicked(new EventHandler<MouseEvent>()
                {
                    @Override
                    public void handle(MouseEvent t) {
						StartupMenu.start(primaryStage);
					}
                });
		};
}
