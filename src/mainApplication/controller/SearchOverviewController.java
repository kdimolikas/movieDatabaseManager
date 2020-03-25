package mainApplication.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

import javafx.fxml.FXML;

import javafx.scene.canvas.Canvas;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.event.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import mainApplication.Gui;

import search.SearchEngine;
import search.SearchEngineFactory;
import shapes.AbstractShape;
import shapes.ShapeFactory;
import shapes.ShapeRenderer;
import shapes.Shapes;
import shapes.Sizes;


/**
 * Controls {@link SearchOverview.fxml}.
 * @author KD
 * @since 2019-11-19 | revised on 2020-02-25
 * @version 1.0.0
 */
public class SearchOverviewController {

	private static final String NO_IMAGE = "file:resources/images/noimage.jpg";
	@SuppressWarnings("unused")
	private Gui mainApp;

	private SearchEngine sEngine;
	private SearchEngineFactory sFactory;
	private int criterion=1;
	
	private ObservableList<String> items = FXCollections.observableArrayList(); 
	
	//Star shape for visualizing audience ratings
	private ShapeFactory shapeFactory;
	private ShapeRenderer renderer;
		
	@FXML
	private TextField searchKey;

	@FXML
	private Label searchTitle;
	
	@FXML
	private ImageView moviePic = new ImageView();
	
	@FXML
	private HBox filters;
	
	@FXML
	private ComboBox<String> actors;
	
	private ObservableList<String> initialActorList;

	private ObservableList<String> filteredActorList;
	
	@FXML
	private ComboBox<String> directors;
	
	private ObservableList<String> initialDirectorList;

	private ObservableList<String> filteredDirectorList;
	
	@FXML
	private ComboBox<String> genres;
	
	@FXML
	private ComboBox<String> countries;
	
	@FXML
	private HBox radioBox;
	
	@FXML
	private RadioButton idRadio;

	@FXML
	private RadioButton nameRadio;
	
	@FXML
	private RadioButton actorRadio;
	
	@FXML
	private RadioButton directorRadio;
	
	@FXML
	private RadioButton countryRadio;
	
	@FXML
	private RadioButton genreRadio;
	
	@FXML
	private final ToggleGroup radioGroup = new ToggleGroup();
	
	@FXML
	private ListView<String> answerView  = new ListView<String>();
	
	private List<String> answer;
	
	@FXML
	private HBox ratingsBox;
	
	@FXML
	private Canvas audienceRatingsCanvas;
	
	
	@FXML
	private void initialize() {
		sFactory = new SearchEngineFactory();
		sEngine = sFactory.createSearchEngine(1);
		answer = new ArrayList<String>();
		
		radioBox.setVisible(true);
		moviePic.setImage(new Image("file:resources/images/noimage.jpg"));
		this.answerView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		
		this.shapeFactory = new ShapeFactory();
		renderer = new ShapeRenderer(500, 50, 200, 0);
		renderer.setScale(Sizes.SMALL);
		
		idRadio.setToggleGroup(radioGroup);
		nameRadio.setToggleGroup(radioGroup);
		actorRadio.setToggleGroup(radioGroup);
		directorRadio.setToggleGroup(radioGroup);
		countryRadio.setToggleGroup(radioGroup);
		genreRadio.setToggleGroup(radioGroup);
		
		getActors();
		initialActorList = FXCollections.observableArrayList(actors.getItems());
		
		getDirectors();
		initialDirectorList = FXCollections.observableArrayList(directors.getItems());
		
		getGenres();
		
		getCountries();
		
		actors.getEditor().textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				Predicate<String> startsWith = i -> i.toLowerCase().startsWith(newValue.toLowerCase());	
				Platform.runLater(() -> {
					filteredActorList = initialActorList.filtered(startsWith);
					actors.getItems().setAll(filteredActorList);
					actors.show();
					});			
			}
			
		});
		
		directors.getEditor().textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				Predicate<String> startsWith = i -> i.toLowerCase().startsWith(observable.getValue().toLowerCase());	
				Platform.runLater(() -> {
					filteredDirectorList = initialDirectorList.filtered(startsWith);
					directors.getItems().setAll(filteredDirectorList);
					directors.show();
					});			
			}
			
		});
		
		actors.valueProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (newValue!=null && !newValue.equalsIgnoreCase(actors.getPromptText())) {
					submitQuery(newValue);
				}
			}
			
		});
		
		directors.valueProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (newValue!=null && !newValue.equalsIgnoreCase(directors.getPromptText())) {
					submitQuery(newValue);
				}
			}
			
		});
		
		countries.valueProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (newValue!=null) {	
					submitQuery(newValue);
				}
			}
			
		});
		
		genres.valueProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (newValue!=null) {	
					submitQuery(newValue);
				}
			}
			
		});
		
		
		radioGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
				if (((RadioButton)radioGroup.getSelectedToggle()).getId().equals("idRadio")) {
					clearFilters();
					disableFilters();
					criterion = 1;
					sEngine = sFactory.createSearchEngine(criterion);
					searchKey.setDisable(false);
				}
				else if (((RadioButton)radioGroup.getSelectedToggle()).getId().equals("nameRadio")) {
					clearFilters();
					disableFilters();
					criterion = 2;
					sEngine = sFactory.createSearchEngine(criterion);
					searchKey.setDisable(false);
				}
				else if (((RadioButton)radioGroup.getSelectedToggle()).getId().equals("actorRadio")) {
					clearFilters();
					disableFilters();
					actors.setDisable(false);
					sEngine = sFactory.createSearchEngine("Actor");
				}
				else if (((RadioButton)radioGroup.getSelectedToggle()).getId().equals("directorRadio")) {
					clearFilters();
					disableFilters();
					directors.setDisable(false);
					sEngine = sFactory.createSearchEngine("Director");
				}
				else if (((RadioButton)radioGroup.getSelectedToggle()).getId().equals("countryRadio")) {
					clearFilters();
					disableFilters();
					countries.setDisable(false);
					sEngine = sFactory.createSearchEngine("Country");
				}
				else if (((RadioButton)radioGroup.getSelectedToggle()).getId().equals("genreRadio")) {
					clearFilters();
					disableFilters();
					genres.setDisable(false);
					sEngine = sFactory.createSearchEngine("Genre");
				}
			}
			
		});
		
		//track the image's error property.        
		moviePic.getImage().errorProperty().addListener(new ChangeListener<Boolean>() {
			@Override 
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean imageError) {
				if (imageError) {
					moviePic.setImage(new Image(NO_IMAGE));
				}
			}
		});

		answerView.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				String selectedItem = answerView.getSelectionModel().getSelectedItem();
				if (selectedItem.startsWith("Title:") && selectedItem!=null){
					String movieTitle = selectedItem.split(":")[1];
					String picUrl = sEngine.getMoviePicUrl(movieTitle);
					if (!picUrl.isEmpty()) {
						updateImage(sEngine.getMoviePicUrl(movieTitle));
					}
				}	
			}
		});
	}
	
	private void disableFilters() {
		actors.setDisable(true);
		directors.setDisable(true);
		countries.setDisable(true);
		genres.setDisable(true);
		searchKey.setDisable(true);
	}
	
	private void clearFilters() {
		actors.getSelectionModel().clearSelection();
		actors.setValue(actors.getPromptText());
		directors.getSelectionModel().clearSelection();
		directors.setValue(directors.getPromptText());
		countries.getSelectionModel().clearSelection();
		genres.getSelectionModel().clearSelection();
		searchKey.clear();
	}
	
	private void getActors() {
		Set<String> actorsSet = sEngine.getActors();
		actors.getItems().addAll(actorsSet);
	}
	
	private void getDirectors() {
		Set<String> directorsSet = sEngine.getDirectors();
		directors.getItems().addAll(directorsSet);
	}
	
	private void getGenres() {
		Set<String> genresSet = sEngine.getGenres();
		genres.getItems().addAll(genresSet);
	}	
	
	private void getCountries() {
		Set<String> cSet = sEngine.getCountries();
		countries.getItems().addAll(cSet);
	}
	
	/**
	 * Draw rating stars for the given movie.
	 * @param numOfStars - the number of rating stars
	 */
	private void drawRatingStars(int numOfStars) {
	
		this.audienceRatingsCanvas.getGraphicsContext2D().clearRect(0, 0,
																	this.audienceRatingsCanvas.getWidth(),
																	this.audienceRatingsCanvas.getHeight());
		AbstractShape[] stars = new AbstractShape[5];
		
		for(int i = 0; i < 5; i++) {
			AbstractShape star = shapeFactory.createShape(Shapes.STAR);

			if (i < numOfStars) {
				star.setShapeColors(Color.BLACK, Color.GOLD);
			}else {
				star.setShapeColors(Color.BLACK, Color.LIGHTGREY);
			}
			stars[i] = star;
		}
		
		renderer.renderShapes(stars);
		this.audienceRatingsCanvas = renderer.getCanvas();
		ratingsBox.getChildren().clear();
		ratingsBox.getChildren().add(audienceRatingsCanvas);
	}

	public void customizeListCells() {
		
		this.answerView.setCellFactory(lc-> new ListCell<String>() {

			@Override
			protected void updateItem(final String item, final boolean empty) {

				super.updateItem(item, empty);
				if (!empty ) {

					if (item.startsWith("Title:")) {

						setTextFill(javafx.scene.paint.Paint.valueOf(Color.DARKGREEN.toString()));
						setStyle("-fx-font-weight:bold");

					}
					else if (item.startsWith("Cast")) {
						Text txt = new Text();
						txt.wrappingWidthProperty().bind(answerView.widthProperty().subtract(10));
						txt.setText(item);
						setPrefWidth(0);
						setGraphic(txt);
					}
					else {

						setTextFill(javafx.scene.paint.Paint.valueOf(Color.BLACK.toString()));
						setStyle("-fx-font-weight:normal"); 

					}

					setText(item);
				}
			};
		});
	}

	public int submitQuery(String data) {
		
		String searchTitle;
		String picUrl="";
		Pattern picPattern = Pattern.compile("(jpg|png)$");
		Matcher m;
		
		if (items.size() > 0)
			clear();
				
		answer = sEngine.searchFor(data);
		
		if (!answer.isEmpty()) {	
			searchTitle = sEngine.getTitleOfSearch();
			if (answer.size()>1) {
				m = picPattern.matcher(answer.get(1));
				if (m.find()) {
					picUrl = answer.get(1);
					answer.remove(0);
				}else {
					moviePic.setImage(new Image(NO_IMAGE));
				}
			}
			answer.remove(0);
			items.setAll(answer);
			
			this.searchTitle.textProperty().setValue(searchTitle);			
			this.answerView.setItems(items);	
			this.customizeListCells();		
			this.answerView.refresh();
						
			updateImage(picUrl);
			
			//search for movie(either by title or id)
			if (((RadioButton)radioGroup.getSelectedToggle()).getId().equals("idRadio") || 
					((RadioButton)radioGroup.getSelectedToggle()).getId().equals("nameRadio")) {
				this.ratingsBox.setVisible(true);
				double ratings = Double.parseDouble(answer.get(6).split(":")[1]);
				this.drawRatingStars((int)Math.round(ratings));
			}else {
				this.ratingsBox.setVisible(false);
			}
				
		}
		return 0;
	}
	
	private void updateImage(String picUrl) {
		
		Pattern picPattern = Pattern.compile("(jpg|png)$");
		Matcher m;
		Image tempImg = null;
		
		m = picPattern.matcher(picUrl);
		
		if (m.find()) {
			
			try {
				tempImg = getMovieImage(picUrl);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
	
			if (tempImg.isError()) {
				System.err.println("An error occured while loading image: "+tempImg.getException().getMessage());
				moviePic.setImage(new Image(NO_IMAGE));
			}else
				moviePic.setImage(tempImg);
		}
			
		moviePic.setSmooth(true);
		moviePic.setPreserveRatio(true);						
	}
	
	
	/**
	 * Attempts to download the image of the given url
	 * @param picUrl image url
	 * @return an instance of {@link javafx.scene.image.Image} class 
	 * @throws MalformedURLException
	 */
	private Image getMovieImage(String picUrl) throws MalformedURLException {
		
		URL url;
		String newUrl =  convertUrl(picUrl);
		url = new URL(newUrl);
		
		Task<Image> imageLoadTask = createLoadImageTaskWorker(url);
		
		imageLoadTask.setOnSucceeded(t -> {
			try {
				moviePic.setImage(imageLoadTask.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		});
		
		/*Start task*/
    	try {
			new Thread(imageLoadTask).start();
		}catch(IllegalThreadStateException ex) {
			ex.printStackTrace();
		}		
				
		return moviePic.getImage();
	}
	
	/**
	 * Creates a task for loading images.
	 * @param url image url
	 * @return an instance of {@link javafx.concurrent.Task} class 
	 */
	private Task<Image> createLoadImageTaskWorker(URL url) {
		
		Task<Image> task = new Task<Image>() {

			HttpURLConnection conn = null;
			InputStream stream ;
			Image tempImg = new Image(NO_IMAGE);
			
			@Override
			protected Image call() throws Exception {
				try {
					conn = (HttpURLConnection) url.openConnection();
					conn.setRequestMethod("GET");
					conn.connect();
					stream = conn.getInputStream();
					tempImg = new Image(stream);
	
				}catch (IOException e) {
					System.err.println("Attempt to connect to url failed. "+"Cause: "+e.getMessage());
				}finally {
					if (!conn.equals(null))
						conn.disconnect();
				}
				return tempImg;
			}
			
			@Override
			public void cancelled() {
				super.cancelled();
				this.updateMessage("Cancelled to load image.");
			}

			@Override
			public void failed() {
				super.failed();
				this.updateMessage("Failed to load image.");
			}
			
		};
		
		return task;
	}
	
	/**
	 * Converting an http url to https
	 * @param url the initial url
	 * @return modified url
	 */
	private String convertUrl(String url) {	
		String newUrl = "";
		if (url.startsWith("http:"))
			newUrl = "https://".concat(url.substring(7));
		else
			newUrl = url;
		
		return newUrl;	
	}
	
	@FXML
	public void handleSearchBtnClick() {
		if (!this.searchKey.toString().equals(null))
			this.submitQuery(this.searchKey.textProperty().getValue());
	}
	
	private void clear() {	
		this.answerView.getItems().clear();
		this.answer.clear();
		this.items.clear();
		this.searchTitle.setText("");
	}

	public void setMainApp(Gui mainApp) {		
		this.mainApp = mainApp;
	}
}