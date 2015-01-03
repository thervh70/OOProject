package View;

import javafx.animation.RotateTransition;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


public class LoadingScreen {


	public static void start(Stage primaryStage) {
		Pane root = new Pane();

		Color color = Color.BLACK;
		CornerRadii corner = new CornerRadii(0);
		Insets inset = new Insets(0);
		BackgroundFill fill = new BackgroundFill(color, corner, inset);
		Background black = new Background(fill);
		
		root.setBackground(black);
		
		Image logo = new Image("/View/Resources/Frits_logo.png");
		ImageView logo_img = new ImageView(logo);
		Style.setLocation(logo_img, 100, 800);
		logo_img.setFitHeight(Style.getNewSize(200));
		logo_img.setFitWidth(Style.getNewSize(200));
		
		Text text = new Text("LOADING...");
		Style.setTextStyle(text, 38);
		Style.setLocation(text, 150, 750);
		
		RotateTransition frits = new RotateTransition(Duration.millis(1700), logo_img);
	    frits.setByAngle(360);
	    frits.setCycleCount(Integer.MAX_VALUE);
	    frits.setAutoReverse(false);
		frits.play();
		
		root.getChildren().addAll(logo_img,text);
		
		primaryStage.getScene().setRoot(root);
		primaryStage.show();
		
        root.setOnMouseMoved(new EventHandler<MouseEvent>()
                {
                    @Override
                    public void handle(MouseEvent t) {
						ManagementCenter.start(primaryStage);
					}
                });
		
		root.setOnMouseClicked(new EventHandler<MouseEvent>()
				{
					@Override
					public void handle(MouseEvent t2) {
						ManagementCenter.start(primaryStage);
					}
 				});
		};
}