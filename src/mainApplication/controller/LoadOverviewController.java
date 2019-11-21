package mainApplication.controller;

import java.util.Map;


import dataLoad.DataLoaderFactory;
import dataLoad.ILoaderManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import mainApplication.Gui;
import mainApplication.model.InputFile;

/**
 * Controls {@link LoadOverview.fxml}.
 * @author KD
 * @since 2019-11-21
 * @version 1.0.1
 */
public class LoadOverviewController {
	

	private Gui mainApp;
	
	private	ProgressBar bar;
	
	private Label status;
	 
	private ObservableList<InputFile> contentList = FXCollections.observableArrayList();

	private ILoaderManager loader;
	
	private DataLoaderFactory lFactory;

	@FXML
	private TextArea intro;
	
	@FXML
	private TableView<InputFile> contentTable = new TableView<InputFile>();
	
	private TableColumn<InputFile, String> fileColumn = new TableColumn<InputFile, String>("Content");
		
	private TableColumn<InputFile, Integer> recordColumn = new TableColumn<InputFile, Integer>("Records");

	 
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */ 
	@SuppressWarnings("unchecked")
	@FXML
    private void initialize() {
    	
    	lFactory = new DataLoaderFactory();
    	
    	loader = lFactory.createDataLoader();
    	
    	intro.setId("fancyText");

    	bar = new ProgressBar();
    	status = new Label();
    	
    	intro.setEditable(false);
    	contentTable.getColumns().addAll(fileColumn,recordColumn);
    	fileColumn.setCellValueFactory(new PropertyValueFactory<>("fileName"));
    	recordColumn.setCellValueFactory(new PropertyValueFactory<>("recordNum"));
    	
    	
    	@SuppressWarnings({ "rawtypes", "unused" })
		Callback<TableColumn<InputFile,String>, TableCell> tableCellFactory =
                   new Callback<TableColumn<InputFile,String>, TableCell>() {
               
    		   @Override
               public TableCell<InputFile,String> call(TableColumn<InputFile,String> p) {
                  
    			   TableCell<InputFile,String> cell = new TableCell<InputFile,String>();
                   
    			   cell.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                	   
                	   public void handle(MouseEvent e) {
                		   
                		   cell.setTextFill(Paint.valueOf(Color.ALICEBLUE.toString())); 
                	   }        	   
                	   
                   });
                   
                   return cell;
               }
           };                  
    }
    
    
	public void loadAllData() {
    	
		int maxTasksNum = 5;
		
		Stage stage = new Stage(StageStyle.UTILITY);
		StackPane root = new StackPane();
		root.setPadding(new Insets(10));
		StackPane.setAlignment(bar, Pos.TOP_CENTER);
		root.getChildren().addAll(bar,status);
		Scene scene = new Scene(root, 200, 80, Color.LIGHTGRAY);
		stage.setScene(scene);
		stage.resizableProperty().set(false);
		stage.setTitle("Loading data...");
		stage.setAlwaysOnTop(true);
		bar.setProgress(0.0);
		stage.show();
		
		
		Task<Integer> loadTask = this.createLoadWorker(maxTasksNum);

		
		loadTask.setOnSucceeded(e -> {
			for (Map.Entry<String,Integer> f:loader.getFileInfo().entrySet()) {
				String key = f.getKey();
				Integer value = f.getValue();
				InputFile in = new InputFile();
				in.setFileName(key);
				in.setRecordNum(Integer.toString(value));
				contentList.add(in);
				stage.hide();
			}

  		  contentTable.setItems(contentList);

  		  contentTable.refresh();   
		});

		/*Bind UI items with task properties*/
		bar.progressProperty().unbind();
		bar.progressProperty().bind(loadTask.progressProperty());
	
		status.textProperty().unbind();
		status.textProperty().bind(loadTask.messageProperty());
		
		intro.textProperty().unbind();
		intro.textProperty().bind(loadTask.messageProperty());
		
		/*Start task*/
    	try {
			new Thread(loadTask).start();
		}catch(IllegalThreadStateException ex) {
			ex.printStackTrace();
		}

    }
    
	public Task<Integer> createLoadWorker(long max) {
		
		Task<Integer> task = new Task<Integer>() {

			@Override
			protected Integer call() throws Exception {
				
				int items = 0;
				
				this.updateMessage("Loading movies...");
				items += loader.loadMovies();
				this.updateProgress(1, max);

				this.updateMessage("Loading actors...");
				items += loader.loadActors();
				this.updateProgress(2, max);

				this.updateMessage("Loading directors...");
				items += loader.loadDirectors();
				this.updateProgress(3, max);

				this.updateMessage("Loading countries...");
				items += loader.loadCountries();
				this.updateProgress(4, max);

				this.updateMessage("Loading genres...");
				items += loader.loadGenres();
				this.updateProgress(5, max);
					
				return items;
			}
			
			@Override
			public void succeeded() {
				super.succeeded();
				this.updateProgress(5, max);
				this.updateMessage("Done:All data loaded");
			}
			
			@Override
			public void cancelled() {
				this.updateMessage("Cancelled:");
			}
			
			@Override
			public void failed() {
				this.updateMessage("Failed:");
			}		
		};
		
		return task;
	}
	
	@FXML
	public void handleHomeBtn() {
		
		mainApp.initRootLayout();
		
	}
	
    public void setMainApp(Gui mainApp) {
	     
    	this.mainApp = mainApp;
    	
    	contentTable.setEditable(false);
    }
}