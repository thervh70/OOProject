package View;

import Controller.saveGame;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Popup;

public class Warning{
	
	/**Makes a standard popup with the given string and disables the current pane while the popup is showing.
	 * @author D18.1
	 * 
	 * @param t - The string to be added to the popup
	 * @param root - The pane to which the popup will be added and the original screen will be disabled temporarily.
	 * @return - The popup
	 */
	public static Popup makeWarning(String t, Pane root) {
		root.setDisable(true);
		Popup warning = new Popup();
		warning.centerOnScreen();
		warning.setWidth(Style.getNewSize(200));
		warning.setHeight(Style.getNewSize(500));
		Rectangle rect = new Rectangle(Style.getNewSize(500), Style.getNewSize(300), Color.WHITESMOKE);
		Style.setLocation(rect, -40, 110);
		rect.setArcHeight(30);
		rect.setArcWidth(30);
		Text text = new Text(t);
		Style.setLocation(text, 0, 210);
		Style.setTextStyle(text, 40);
		text.setFill(Color.BLACK);
		Button confirm = new Button("OK");
		Style.setLocation(confirm, 165, 300);
		Style.setButtonStyle(confirm, 40);
		
		
		confirm.setOnMouseClicked(new EventHandler<MouseEvent>() {
			/**Gives functionality to the confirm button which closes the popup and reactivates the rest of the screen on click.
			 * @author D18.1
			 * 
			 */
			@Override
			public void handle(MouseEvent m){
				warning.hide();
				root.setDisable(false);
			}
		});
		
		warning.getContent().addAll(rect, text, confirm);
		return warning;
	}
	
	/**Makes a popup with a rectangle as background color and the given string as text and disables the current pane while the popup is showing.
	 * @author D18.1
	 * 
	 * @param t - The string to be shown on the popup.
	 * @param root - The pane where the popup should show which will be disabled temporarily.
	 * @param r - The rectangle which will be used for the background of the popup.
	 * @return - The finished popup.
	 */
	public static Popup makeWarning(String t, Pane root, Rectangle r) {
		root.setDisable(true);
		Popup warning = new Popup();
		r.setOpacity(0.5);
		warning.centerOnScreen();
		warning.setWidth(Style.getNewSize(200));
		warning.setHeight(Style.getNewSize(500));
		Rectangle rect = new Rectangle(Style.getNewSize(500), Style.getNewSize(300), Color.WHITESMOKE);
		Style.setLocation(rect, -40, 110);
		rect.setArcHeight(30);
		rect.setArcWidth(30);
		Text text = new Text(t);
		Style.setLocation(text, 0, 250);
		Style.setTextStyle(text, 40);
		text.setFill(Color.BLACK);
		Button confirm = new Button("OK");
		Style.setLocation(confirm, 165, 300);
		Style.setButtonStyle(confirm, 40);
		
		/**Gives functionality to the confirm button which closes the popup and reactivates the rest of the screen on click.
		 * @author D18.1
		 * 
		 */
		confirm.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent m){
				r.setOpacity(1);
				warning.hide();
				root.setDisable(false);
			}
		});
		
		warning.getContent().addAll(rect, text, confirm);
		return warning;
	}
	
	/**Makes a popup with a given text to which the user can answer yes or no.
	 * @author D18.1
	 * 
	 * @param t - The string to be shown on the popup.
	 * @param root - The pane the popup is shown over and which is temporarily disabled.
	 * @param event - 
	 * @param event2
	 * @return - The completed popup.
	 */
	public static Popup makeWarning(String t, Pane root, EventHandler<MouseEvent> event,EventHandler<MouseEvent> event2) {
		root.setDisable(true);
		Popup warning = new Popup();
		warning.centerOnScreen();
		warning.setWidth(Style.getNewSize(200));
		warning.setHeight(Style.getNewSize(500));
		Rectangle rect = new Rectangle(Style.getNewSize(500), Style.getNewSize(300), Color.WHITESMOKE);
		Style.setLocation(rect, -40, 110);
		rect.setArcHeight(30);
		rect.setArcWidth(30);
		Text text = new Text(t);
		Style.setLocation(text, 0, 210);
		Style.setTextStyle(text, 40);
		text.setFill(Color.BLACK);
		
		Button yes = new Button("Yes");
		Style.setLocation(yes, 100, 300);
		Style.setButtonStyle(yes, 40);
		
		Button no = new Button("No");
		Style.setLocation(no, 200, 300);
		Style.setButtonStyle(no, 40);
		
		
		yes.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent y){
				warning.hide();
				event.handle(y);
			}
		});
		
		no.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent n){
				warning.hide();
				event2.handle(n);
				root.setDisable(false);
			}
		});
		
		warning.getContent().addAll(rect, text, yes, no);
		return warning;
	}
	
	/**Makes a popup with a given text and prompts the user for input and shows yes/no buttons.
	 * @author D18.1
	 * 
	 * @param t - The string to be shown on the popup
	 * @param root - The pane the popup is shown over and which is temporarily disabled.
	 * @param event - 
	 * @param event2
	 * @return - The completed popup.
	 */
	public static Popup getInput(String t, Pane root, EventHandler<MouseEvent> event,EventHandler<MouseEvent> event2) {
		root.setDisable(true);
		Popup warning = new Popup();
		warning.centerOnScreen();
		warning.setWidth(Style.getNewSize(200));
		warning.setHeight(Style.getNewSize(500));
		
		Rectangle rect = new Rectangle(Style.getNewSize(500), Style.getNewSize(300), Color.WHITESMOKE);
		Style.setLocation(rect, -40, 110);
		rect.setArcHeight(30);
		rect.setArcWidth(30);
		
		Text text = new Text(t);
		Style.setLocation(text, 130, 180);
		Style.setTextStyle(text, 40);
		text.setFill(Color.BLACK);
		
		Button yes = new Button("Save");
		Style.setLocation(yes, 100, 300);
		Style.setButtonStyle(yes, 40);
		
		Button no = new Button("Back");
		Style.setLocation(no, 200, 300);
		Style.setButtonStyle(no, 40);
				
		TextField input = new TextField();
		input.setMaxWidth(Style.getNewSize(300));
		input.setPromptText("Enter File Name");
		input.setAlignment(Pos.CENTER);
		input.setFont(Style.getFont(45));	
		Style.setLocation(input, 50, 200);
		
		yes.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent y){
				warning.hide();
				saveGame.setFile(input.getText() + ".xml");
				event.handle(y);
			}
		});
		
		no.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent n){
				warning.hide();
				event2.handle(n);
				root.setDisable(false);
			}
		});
		
		warning.getContent().addAll(rect, text, input, yes, no);
		return warning;
	}
	
}
