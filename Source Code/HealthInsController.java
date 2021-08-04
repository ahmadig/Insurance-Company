import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HealthInsController extends ObserverClass {
	
	
	
	@FXML
	private TextField familyName;
	@FXML
	private TextField firstName;
	@FXML
	private TextField date;
	@FXML
	private TextField remarks;
	@FXML
	private Button btnSave;
	@FXML
	private Button btnBack;
	@FXML
	private Text info;
	private String users;

	@FXML
	public void initialize() {
		ArrayList<String> arr = readJson();
		info.setText(arr.get(0)+arr.get(1)+arr.get(2));
	}
	public void start(Stage primaryStage) throws Exception {	
		Parent root = FXMLLoader.load(getClass().getResource("RegisterHealthIns.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Health Insurance Page");
		primaryStage.setScene(scene);
		primaryStage.show();	
	}
	public void saveNewOrder() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, IOException {
		SaveToDB db = SaveToDB.getinstance();
	       ArrayList<String> user = new ArrayList<>();
	       users=firstName.getText();
	       user.add(users);
	       user.add(familyName.getText());
	       user.add(date.getText());
	       user.add(remarks.getText());
	       user.add("Health");
		   db.saveUserToDB(user);
		   update();
	}
	public void backButton() throws Exception {
		Stage stage = (Stage) btnBack.getScene().getWindow();
		stage.close();
		Stage st = new Stage();
		MainController mt = new MainController();
		mt.start(st);
	}
	public ArrayList<String> readJson() {
		JSONParser parser = new JSONParser();
		ArrayList<String> arr = new ArrayList<>();
		try {
			Object obj = parser.parse(new FileReader("input.json"));
			JSONObject jsonObj = (JSONObject) obj;
			JSONArray infoarray = (JSONArray)jsonObj.get("pageinfo");
			arr.add("\t\t"+infoarray.get(0).toString()+"\n");
			arr.add("\t"+infoarray.get(1).toString()+"\n");
			arr.add(infoarray.get(2).toString());
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return arr;
	}
	@Override
	public void update() throws IOException {
		log.registerNewAction(users+"bought health insurance");
		
	}

}
