package View;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import Model.DBmain;
import Model.Player;
import Model.Team;
import Model.XmlParser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TransferMarket {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void start(Stage primaryStage) throws SAXException, IOException, ParserConfigurationException {
		Pane root = new Pane();
		
		root.getChildren().add(Style.setBackground("/View/Resources/background_transfer-market.png"));
		
		Button back = new Button("Back to Management Center");
		Text forSale = new Text("For Sale");
		Text myProducts = new Text("My Products :)");
		
		Style.setButtonStyle(back, 45);
		Style.setLocation(back, 150, 870);
		
		Style.setTextStyle(forSale, 45);
		Style.setLocation(forSale, 475, 230);

		Style.setTextStyle(myProducts, 45);
		Style.setLocation(myProducts, 1275, 230);

		
		TableView<Player> tableForSale = new TableView();
		tableForSale.setEditable(false);
		tableForSale.setPrefSize(Style.getNewSize(800), Style.getNewSize(540));
		Style.setLocation(tableForSale, 125, 250);
		tableForSale.setEditable(false);
		
		TableView<Player> tableMyPlayers = new TableView();
		tableMyPlayers.setPrefSize(Style.getNewSize(800), Style.getNewSize(540));
		Style.setLocation(tableMyPlayers, 990, 250);
		tableMyPlayers.setEditable(false);
		
		TableColumn name = new TableColumn("Name");
        TableColumn position = new TableColumn("Position");
		TableColumn age = new TableColumn("Age");
		TableColumn worth = new TableColumn("Worth");
		TableColumn shooting = new TableColumn("Shooting");
		TableColumn passing = new TableColumn("Passing");
		TableColumn dribbling = new TableColumn("Dribbling");
		TableColumn defending = new TableColumn("Defending");
		TableColumn physical = new TableColumn("Physical");
		TableColumn price = new TableColumn("Price");
		TableColumn team = new TableColumn("Team");
		name.setResizable(false);
		position.setResizable(false);
		age.setResizable(false);
		worth.setResizable(false);
		shooting.setResizable(false);
		passing.setResizable(false);
		dribbling.setResizable(false);
		defending.setResizable(false);
		physical.setResizable(false);
		price.setResizable(false);
		
		tableForSale.getColumns().addAll(name,position,age,worth,shooting,passing,dribbling,defending,physical, team);
		tableMyPlayers.getColumns().addAll(name,position,age,worth,shooting,passing,dribbling,defending,physical, team);

		DBmain d = XmlParser.parseDB();
		Team t = d.getTeam(PickTeam.choice);
		
		ObservableList<Player> user = FXCollections.observableArrayList(t.getPlayer(0));
		
		for(int i = 0; i < t.getSize(); i++){
			Player p = t.getPlayer(i);
			name.getCellData(p.getFnm());
			age.getCellData(p.getAge());
		}
		
		tableMyPlayers.setItems((ObservableList<Player>) user);		
		
		root.getChildren().addAll(back, tableForSale, forSale, tableMyPlayers, myProducts);
		
		back.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				ManagementCenter.start(primaryStage);
			}
		});
		
		primaryStage.getScene().setRoot(root);
		primaryStage.show();
	}

}
