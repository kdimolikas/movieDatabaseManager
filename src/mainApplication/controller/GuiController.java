package mainApplication.controller;

import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import mainApplication.Gui;

/**
 * Controls {@link mainApplication.Gui} class.
 * @author KD
 * @since 2018
 * @version 1.0.0
 */
public class GuiController {
	
	
	private static final String INTRO =  "Welcome to Movie Archives...\n" 
	 		+ "Here you can find info about a wide range of movies.\n"
	 		+ "You can customize your search using various criteria including movie title, actor/director name, genre, etc.\n";
	
	private Gui mainApp;
	 
	 
	@FXML
	private MenuBar menu;
	
	@FXML
	private MenuItem loadMenuItem;
	
	@FXML
	private ProgressBar psb;

	@FXML
	private Label statusLabel;

	@FXML
	private HBox hb;

	@FXML
	private TextArea desc;
	
	@FXML
	private ImageView imgView;
	 	 
	 @FXML
	 private void initialize() {
		 		 
		 Image img = new Image("file:resources/images/movie_archive_main.png");
		 imgView.setImage(img);
		 
		 desc.setText(INTRO);
		 desc.setWrapText(true);
		 desc.editableProperty().set(false);
	 }
	 
	 @FXML
	 public void handleHomeMenuItem() {
		 mainApp.initRootLayout();
	 }

	 @FXML
	 public void handleCloseApp() {

		 ButtonType yesBtn = new ButtonType("Yes",ButtonData.OK_DONE);
		 ButtonType noBtn = new ButtonType("No",ButtonData.NO);
	 
		 Dialog<ButtonType> dialog = new Dialog<>();
		 dialog.initOwner(mainApp.getPrimaryStage());
		 dialog.getDialogPane().getButtonTypes().addAll(yesBtn,noBtn);
		 dialog.setTitle("Movie Manager");
		 dialog.setHeaderText("Are you sure you want to exit?");
		 //Set the icon
		 ImageView img = new ImageView("file:resources/images/exit.png");
		 img.setFitWidth(50);
		 img.setFitHeight(50);
		 dialog.setGraphic(img);
		 desc.setText("Time to go?");
		 Optional<ButtonType> result = dialog.showAndWait();
		 if (result.get().getButtonData() == ButtonData.OK_DONE) 		
			 mainApp.getPrimaryStage().close();
		 else
			 desc.setText(INTRO);	 
	 }
 
	 @FXML
	 public void handleShowLoader() {
		 loadMenuItem.setDisable(false);
		 mainApp.showLoadOverview();
	 }
	 
	 @FXML
	 public void handleLoadAllData() throws Exception {
		 mainApp.loadAllData();
	 }

	 @FXML
	 public void handleSubmitQuery() {
		mainApp.showSearchOverview();		 	 
	 }
	 
	 public void setMainApp(Gui mainApp) {
		 this.mainApp = mainApp;
	 }
}