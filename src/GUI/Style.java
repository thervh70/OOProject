package GUI;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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
	}
	
	public static void setTextStyle(Text t, int size){
		Font textFont = new Font("Agency FB", size);
		Color color = Color.WHITESMOKE;
		t.setFont(textFont);
		t.setFill(color);
	}
}
