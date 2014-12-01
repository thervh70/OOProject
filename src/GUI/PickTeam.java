package GUI;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class PickTeam extends Application {

	@Override
	public void start(Stage primaryStage) throws SAXException, IOException, ParserConfigurationException {
		Pane root = new Pane();
		Button ADO = new Button("ADO");
		Button Ajax = new Button("Ajax");
		Button AZ = new Button("AZ");
		Button Excelsior = new Button("Excelsior");
		Button Dordrecht = new Button("FC Dordrecht");
		Button Groningen = new Button("FC Groningen");
		Button Twente = new Button("FC Twente");
		Button Utrecht = new Button("FC Utrecht");
		Button Feyenoord = new Button("Feyenoord");
		Button Eagles = new Button("Go Ahead Eagles");
		Button Heracles = new Button("Heracles Almelo");
		Button NAC = new Button("NAC Breda");
		Button PEC = new Button("PEC Zwolle");
		Button PSV = new Button("PSV");
		Button Cambuur = new Button("SC Cambuur");
		Button Heerenveen = new Button("SC Heerenveen");
		Button Vitesse = new Button("Vitesse");
		Button Willem = new Button("Willem II");
		
		
		Scene scene = new Scene(root, 1770, 980);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
