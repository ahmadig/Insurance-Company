import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class AllInsurancesController extends ObserverClass{
	
	private ObservableList<Person> data;
	@FXML
	private Button btnBack;
	@FXML
	private TableView<Person> tblOrders;
	@FXML
	private TableColumn<Person, String> name;
	@FXML
	private TableColumn<Person, String> date;
	@FXML
	private TableColumn<Person, String> family;
	@FXML
	private TableColumn<Person, String> remarks;
	@FXML
	private Text info;

	@FXML
	public void initialize() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		SaveToDB db = SaveToDB.getinstance();
		data = FXCollections.observableArrayList();
		ResultSet set = db.showAll();
	    while(set.next()) {
	    	data.add(new Person(set.getString(1),set.getString(2),set.getString(3),set.getString(4)));
	    }
	    setCellTable();
	    tblOrders.setItems(data);
	    ArrayList<String> arr = readJson();
		info.setText(arr.get(0)+arr.get(1)+arr.get(2));
	}
	public void start(Stage primaryStage) throws Exception {	
		Parent root = FXMLLoader.load(getClass().getResource("info.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Insurance view ");
		primaryStage.setScene(scene);
		primaryStage.show();	
	}
	public void backButton() throws Exception {
		Stage stage = (Stage)btnBack.getScene().getWindow();
		stage.close();
		Stage st = new Stage();
		MainController mt = new MainController();
		mt.start(st);
	}
	public void setCellTable() {
		name.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
		family.setCellValueFactory(new PropertyValueFactory<Person, String>("family"));
		date.setCellValueFactory(new PropertyValueFactory<Person, String>("date"));
		remarks.setCellValueFactory(new PropertyValueFactory<Person, String>("note"));
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
		log.registerNewAction("Checked all insurances");
		
	}

}
