
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainController extends ObserverClass{
	
	Stage homePage;
	
	@FXML 
	private Button CarIns;
	@FXML 
	private Button ApartIns;
	@FXML 
	private Button LifeIns;
	@FXML 
	private Button HealthIns;
	@FXML 
	private Button ViewAll;
	
	
	public void start(Stage primaryStage) throws Exception {	
		Parent root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Insurance Home Page ");
		primaryStage.setScene(scene);
		primaryStage.show();	
	}
	
	public void startCarIns() throws Exception {
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		CarInsController lc = new CarInsController();
		lc.start(primaryStage);
		homePage = (Stage)CarIns.getScene().getWindow();
		homePage.close();
		
	}
	public void startApartIns() throws Exception{
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		ApartmentInsController lc = new ApartmentInsController();
		lc.start(primaryStage);
		homePage = (Stage)CarIns.getScene().getWindow();
		homePage.close();
		
	}
	public void startHealthIns() throws Exception {
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		HealthInsController lc = new HealthInsController();
		lc.start(primaryStage);
		homePage = (Stage)CarIns.getScene().getWindow();
		homePage.close();
		
	}
	public void startLifeIns() throws Exception {
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		LifeInsController lc = new LifeInsController();
		lc.start(primaryStage);
		homePage = (Stage)CarIns.getScene().getWindow();
		homePage.close();
		
	}
	public void ViewAllIns() throws Exception {
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		AllInsurancesController lc = new AllInsurancesController();
		lc.start(primaryStage);
		homePage = (Stage)CarIns.getScene().getWindow();
		homePage.close();
		
	}

	@Override
	public void update() throws IOException {
		
	}
   
	
	
	
	

}
