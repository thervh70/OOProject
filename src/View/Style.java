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
	
	/**Give an existing button the standard layout for buttons used in Frits.
	 * @author D18.1
	 * 
	 * @param b - The button getting styled
	 * @param size - 
	 */
	public static void setButtonStyle(Button b, double size){
		Color color = Color.WHITESMOKE;
		CornerRadii corner = new CornerRadii(10);
		Insets inset = new Insets(10);
		BackgroundFill fill = new BackgroundFill(color, corner, inset);
		Background buttonBack = new Background(fill);
	 
		Font buttonFont = Font.loadFont(Style.class.getResource("/View/Resources/AGENCYR.TTF").toExternalForm(), getNewSize(size));
		
		b.setBackground(buttonBack);
		b.setFont(buttonFont);
		
		Color drag_color = Color.LIGHTGREY;
		BackgroundFill drag_fill = new BackgroundFill(drag_color, corner, inset);
		Background drag_buttonBack = new Background(drag_fill);
		
		b.setOnMouseEntered(new EventHandler<MouseEvent>() {
			/**The button color will darken when the mouse enters the button.
			 * @author D18.1
			 * 
			 */
			@Override
			public void handle(MouseEvent m) {
				b.setBackground(drag_buttonBack);
			}
			
		});
		
		b.setOnMouseExited(new EventHandler<MouseEvent>() {
			/**The button color will brighten when the mouse exits the button.
			 * @author D18.1
			 * 
			 */
			@Override
			public void handle(MouseEvent m) {
				b.setBackground(buttonBack);
			}
			
		});		
	}
	
	/** Apply a certain style to a text
	 * @author D18.1
	 * @param t		The Text object to which the style will be applied
	 * @param size	The font-size of this Text object
	 */
	public static void setTextStyle(Text t, int size){
		Font textFont = Font.loadFont(Style.class.getResource("/View/Resources/AGENCYR.TTF").toExternalForm(), getNewSize(size));
		Color color = Color.WHITESMOKE;
		t.setFont(textFont);
		t.setFill(color);
	}
	
	/** Apply a certain style to a label
	 * @author D18.1
	 * @param l		Label to which the style will be applied
	 * @param size	The font-size of this label
	 */
	public static void setLabelStyle(Label l, int size){
	   	Font textFont = Font.loadFont(Style.class.getResource("/View/Resources/AGENCYR.TTF").toExternalForm(), getNewSize(size));
	   	Color color = Color.WHITESMOKE;
		l.setFont(textFont);
		l.setTextFill(color);
	}
	
	/**Apply a certain style to a Rectangle object
	 * @author D18.1
	 * @param r	Rectangle to which the style will be applied
	 */
	public static void setRectangleStyle(Rectangle r){
		Color color = Color.YELLOW;
		r.setFill(color);
		r.setArcHeight(10);
		r.setArcWidth(10);
		r.setOpacity(0.7);
	}
	
	/**	Set the background for a certain screen
	 * @author D18.1
	 * @param image	Name of the background image that needs to be loaded for this particular screen
	 * @return ImageView object that sets the background specific for this screen
	 */
	public static ImageView setBackground(String image){		
		Image background = new Image(image);
		ImageView imgView = new ImageView(background);

		imgView.setFitHeight(height);
		imgView.setFitWidth(width);
		
		return imgView;
	}
	
	/** Return a font object with a specified size
	 * @author D18.1
	 * @param size	The size the Font needs to have
	 * @return		The Font requested
	 */
	public static Font getFont(int size){
		return Font.loadFont(Style.class.getResource("/View/Resources/AGENCYR.TTF").toExternalForm(), size);
	}
	
	/** Adjusts a requested size to the resolution of the screen 
	 * @author D18.1
	 * 
	 * @param size	Value which is correct for a screen with a width of 1920px, but needs to be adjusted
	 * @return		The value adjusted to the different screen resolution
	 */
	public static double getNewSize(double size){
		return (width/1920)*size;
	}
	
	/** Adjusts the location of object on the screen the screen resolution
	 * @author	D18.1
	 * @param n	Visible object that needs to be readjusted
	 * @param x	x coordinate of this object 
	 * @param y	y coordinate
	 */
	public static void setLocation(Node n, double x, double y){
		double ratioX = x/1920;
		double ratioY = y/1080;
		n.setLayoutX(ratioX*width);
		n.setLayoutY(ratioY*height);
	}

	/**
	 * 	Adjusts the screen size to different screen resolutions
	 * @author D18.1
	 */
	public static void setScreen() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		width = screenSize.getWidth();
		height = screenSize.getHeight();
	}
	
	/** Return the width of the screen
	 * @author D18.1
	 * @return The width of the screen
	 */
	public static double getWidth() {
		return width;
	}
	
	/**	Return the height of the screen
	 * @author D18.1
	 * @return The height of the screen
	 */
	public static double getHeight() {
		return height;
	}
}
