package mainApplication;

import java.io.IOException;

import com.sun.javafx.application.LauncherImpl;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;

import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import javafx.stage.Stage;
import main.SimplePreloader;
import mainApplication.controller.GuiController;
import mainApplication.controller.LoadOverviewController;
import mainApplication.controller.SearchOverviewController;


/**
 * The main UI of the app.
 * @author KD
 * @since 2018
 * @version 1.0.0
 */
public class Gui extends Application {
	
	 private Stage primaryStage;
	 private BorderPane rootLayout;
	 @SuppressWarnings("unused")
	 private SearchOverviewController searchController;
	 private LoadOverviewController loadController;
	 private GuiController guiController;
	
	@Override
	public void start(Stage primaryStage) {
		
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Movie Archives");
		this.primaryStage.getIcons().add(new Image("file:resources/images/movie_archive_main.png"));
	
		initRootLayout();
	}
	
	/**
	 * Initializes the root layout.
	 */
	public void initRootLayout() {

		try {		
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Gui.class.getResource("controller/RootLayout.fxml"));
				
			rootLayout = (BorderPane) loader.load();
			guiController = loader.getController();
			this.showRootLayout();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
 
	public void showRootLayout() {
		
		// Show the scene containing the root layout.
		Scene scene = new Scene(rootLayout);
		
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
		
		guiController.setMainApp(this);		
	}

	public void showLoadOverview() {
		
		this.loadController = createLoadController();
	}
	
	public LoadOverviewController createLoadController() {
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Gui.class.getResource("controller/LoadOverview.fxml"));
		
		try {
			
			AnchorPane loadOverview = loader.load();
			rootLayout.setCenter(loadOverview);
			LoadOverviewController ldController = loader.getController();
			ldController.setMainApp(this);
			return ldController;
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
 

	public void loadAllData(){
		
		loadController.loadAllData();	
	}
	
	public SearchOverviewController createSearchController() {
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Gui.class.getResource("controller/SearchOverview.fxml"));
		
		try {
			
			AnchorPane searchOverview = loader.load();
			rootLayout.setCenter(searchOverview);
			SearchOverviewController controller = loader.getController();
			controller.setMainApp(this);
			return controller;
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}	
		return null;
	}
	
	public void showSearchOverview() {
		this.searchController = createSearchController();	
	}
	
	/**
	 * Returns the main stage.
	 * @return the main stage
	 */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
	
	public static void main(String[] args) throws InterruptedException {
		LauncherImpl.launchApplication(Gui.class, SimplePreloader.class, args);	
	}
}