package View;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Popup;

public abstract class Warning {
	
	public static Popup makeWarning(String t, Pane root) {
		root.setDisable(true);
		Popup warning = new Popup();
		warning.centerOnScreen();
		warning.setWidth(Style.getNewSize(200));
		warning.setHeight(Style.getNewSize(500));
		Rectangle rect = new Rectangle(Style.getNewSize(500), Style.getNewSize(300), Color.WHITESMOKE);
		Style.setLocation(rect, -62, 110);
		rect.setArcHeight(30);
		rect.setArcWidth(30);
		Text text = new Text(t);
		Style.setLocation(text, 0, 250);
		Style.setTextStyle(text, 40);
		text.setFill(Color.BLACK);
		Button confirm = new Button("OK");
		Style.setLocation(confirm, 165, 300);
		Style.setButtonStyle(confirm, 40);
		
		
		confirm.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent m){
				warning.hide();
				root.setDisable(false);
			}
		});
		
		warning.getContent().addAll(rect, text, confirm);
		return warning;
	}
}
