package GUI;

import java.awt.Dimension;
import java.awt.Toolkit;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public abstract class Style {
	
	public static void setButtonStyle(Button b, int size){
		Color color = Color.WHITESMOKE;
		CornerRadii corner = new CornerRadii(10);
		Insets inset = new Insets(10);
		BackgroundFill fill = new BackgroundFill(color, corner, inset);
		Background buttonBack = new Background(fill);
		
		Font buttonFont = new Font("Agency FB", size);
		
		b.setBackground(buttonBack);
		b.setFont(buttonFont);
		
		Color drag_color = Color.LIGHTGREY;
		BackgroundFill drag_fill = new BackgroundFill(drag_color, corner, inset);
		Background drag_buttonBack = new Background(drag_fill);
		
		b.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent m) {
				b.setBackground(drag_buttonBack);
			}
			
		});
		
		b.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent m) {
				b.setBackground(buttonBack);
			}
			
		});
		
	}
	
	public static void setTextStyle(Text t, int size){
		Font textFont = new Font("Agency FB", size);
		Color color = Color.WHITESMOKE;
		t.setFont(textFont);
		t.setFill(color);
	}
	
	public static ImageView setBackground(String image){		
		Image background = new Image(image);
		ImageView imgView = new ImageView(background);

		imgView.setFitHeight(SplashScreen.height);
		imgView.setFitWidth(SplashScreen.width);
		
		return imgView;
	}
}
