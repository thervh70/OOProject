package View;

import java.awt.Dimension;
import java.awt.Toolkit;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public abstract class Style {
	
	private static double width;
	private static double height;
	
	public static void setButtonStyle(Button b, double size){
		Color color = Color.WHITESMOKE;
		CornerRadii corner = new CornerRadii(10);
		Insets inset = new Insets(10);
		BackgroundFill fill = new BackgroundFill(color, corner, inset);
		Background buttonBack = new Background(fill);
	 
		Font buttonFont = new Font("Agency FB", getNewSize(size));
		
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
		Font textFont = new Font("Agency FB", getNewSize(size));
		Color color = Color.WHITESMOKE;
		t.setFont(textFont);
		t.setFill(color);
	}
	
	public static void setLabelStyle(Label l, int size){
	   	Font textFont = new Font("Agency FB", getNewSize(size));
	   	Color color = Color.WHITESMOKE;
		l.setFont(textFont);
		l.setTextFill(color);
	}
	
	public static void setRectangleStyle(Rectangle r){
		Color color = Color.YELLOW;
		r.setFill(color);
		r.setArcHeight(10);
		r.setArcWidth(10);
		r.setOpacity(0.7);
	}
	
	public static ImageView setBackground(String image){		
		Image background = new Image(image);
		ImageView imgView = new ImageView(background);

		imgView.setFitHeight(height);
		imgView.setFitWidth(width);
		
		return imgView;
	}
	
	public static double getNewSize(double size){
		return (width/1920)*size;
	}
	
	public static void setLocation(Node n, double x, double y){
		double ratioX = x/1920;
		double ratioY = y/1080;
		n.setLayoutX(ratioX*width);
		n.setLayoutY(ratioY*height);
	}

	public static void setScreen() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		width = screenSize.getWidth();
		height = screenSize.getHeight();
	}

	public static double getWidth() {
		return width;
	}
	
	public static double getHeight() {
		return height;
	}
}
