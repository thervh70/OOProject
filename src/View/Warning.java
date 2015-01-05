package View;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Popup;

public abstract class Warning {
	
	public static Popup makeWarning(String t) {
		Popup warning = new Popup();
		warning.centerOnScreen();
		warning.setWidth(200);
		warning.setHeight(500);
		Rectangle rect = new Rectangle(500, 300, Color.WHITESMOKE);
		rect.setLayoutX(-62);
		rect.setLayoutY(110);
		rect.setArcHeight(30);
		rect.setArcWidth(30);
		Text text = new Text(t);
		text.setLayoutY(250);
		Button confirm = new Button("OK");
		confirm.setLayoutX(165);
		confirm.setLayoutY(300);
		
		confirm.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent m){
				warning.hide();
			}
		});
		warning.getContent().addAll(rect, text, confirm);
		return warning;
	}
}
