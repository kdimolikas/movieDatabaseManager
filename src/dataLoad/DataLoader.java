package dataLoad;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import dataModel.Country;
import dataModel.Genre;
import dataModel.Location;
import dataModel.Movie;
import dataModel.Person;
import dataModel.Rating;
import dataModel.Tag;
import dataModel.TagsRatings;


/**
 * Responsible for loading data from input files.
 * @since 2017-12-14
 * @version 1.0
 *
 */

public class DataLoader implements ILoaderManager {

	
	private static DataLoader instance = null;
	private String movFile;
	private String actorFile;
	private String directorFile;
	private String countryFile;
	private String genreFile;
	@SuppressWarnings("unused")
	private String locationFile;
	private String tagFile;
	private String tagValuesFile;
	private String ratingFile;
	private final HashMap<String,Integer> fileInfo;
	
	private LoaderFactory loaderFactory;

	@SuppressWarnings("rawtypes")
	private AbstractRecordLoader loader;
	
	private TreeMap<String, Movie> movies;
	private TreeMap<String, Person> actors;
	private TreeMap<String, Person> directors;
	private TreeMap<String, Genre> genres;
	private TreeMap<String, Country> countries;
	private TreeMap<String, Location> locations;
	private TreeMap<String, TagsRatings> tags;
	private TreeMap<String, ArrayList<String>> tagValues;
	private TreeMap<String, TagsRatings> ratings;
	
	protected DataLoader() {
		
		loaderFactory = new LoaderFactory();
		
		movFile = "input/movies.dat";
		
		actorFile = "input/movie_actors.dat";

		directorFile = "input/movie_directors.dat";

		countryFile = "input/movie_countries.dat";

		genreFile = "input/movie_genres.dat";

		locationFile = "input/movie_locations.dat";

		tagFile = "input/user_taggedmovies.dat";

		tagValuesFile = "input/tags.dat";
		
		ratingFile = "input/user_ratedmovies.dat";
		
		movies = new TreeMap<String, Movie>();
		actors = new TreeMap<String, Person>();
		directors = new TreeMap<String, Person>();
		genres = new TreeMap<String, Genre>();
		countries = new TreeMap<String, Country>();
		locations = new TreeMap<String, Location>();
		tags = new TreeMap<String, TagsRatings>();
		tagValues = new TreeMap<String, ArrayList<String>>();
		ratings = new TreeMap<String, TagsRatings>();
		
		fileInfo = new HashMap<String,Integer>();
		
	}
	
	
	public static DataLoader getInstance() {
		
		if (instance == null)
			instance = new DataLoader();
		
		return instance;
		
	}
	
	public TreeMap<String, Movie> getMovies(){return movies;}
	
	public TreeMap<String, Person> getActors(){return actors;}

	public TreeMap<String, Person> getDirectors(){return directors;}

	public TreeMap<String, Genre> getGenres(){return genres;}

	public TreeMap<String, Country> getCountries(){return countries;}

	public TreeMap<String, TagsRatings> getTags(){return tags;}

	public TreeMap<String, TagsRatings> getRatings(){return ratings;}

	public TreeMap<String, Location> getLocations(){return locations;}

	public HashMap<String, Integer> getFileInfo(){return this.fileInfo;}

	
	
	/**
	 * 
	 * Interconnects actors to movies.
	 * 
	 */
	
	private void assignMovies2Actors() {
		
		for (Person p:actors.values()) {

			ArrayList<String> movieIds = new ArrayList<String>();
			movieIds = p.getMovieIds();

			for (Movie m:movies.values()) {

				String movieId = m.getMovieId();

				if (movieIds.contains(movieId)) {

					m.addActor(p);
					p.addMovie(m);

				}

			}			
		}
	}
	
	
	/**
	 * Interconnects movies to directors.
	 */
	private void assignDirectors() {
				
		for (Movie m:movies.values()) {

			String movieId = m.getMovieId();

			for (Person p:directors.values()) {

				ArrayList<String> movieIds = new ArrayList<String>();
				movieIds = p.getMovieIds();

				if (movieIds.contains(movieId)) {

					m.setDirector(p);
					p.addMovie(m);

				}
			}		
		}		
	}
		
	
	/**
	 * Assign to each movie a country.
	 */
	private void assignCountries() {
			
		for (Movie m:movies.values()) {
					
			String movieId = m.getMovieId();

			for (Country c:countries.values()) {

				ArrayList<String> movieIds = new ArrayList<String>();
				movieIds = c.getMovieIds();

				if (movieIds.contains(movieId)) {

					m.setCountry(c);
					c.addMovie(m);

				}

			}
					
		}
		
		
	}
	
	
	/**
	 * Assign to each movie locations.
	 */
	@SuppressWarnings("unused")
	private void assignLocations() {
			
		for (Movie m:movies.values()) {
					
			String movieId = m.getMovieId();
						
			for (Map.Entry<String,Location> l:locations.entrySet()) {

				Location lc = new Location();
				lc = l.getValue();

				ArrayList<String> movieIds = new ArrayList<String>();
				movieIds = lc.getMovieIds();

				if (movieIds.contains(movieId)) {

					m.addLocation(lc);

				}

			}	
		}	
	}
	
	
	/**
	 * Assign genres to each movie
	 */
	private void assignGenres(){
				
		for (Movie m:movies.values()) {
		
			String movieId = m.getMovieId();

			for (Genre g:genres.values()) {
				

				ArrayList<String> movieIds = new ArrayList<String>();
				movieIds = g.getMovieIds();
		
				if (movieIds.contains(movieId)) {

					m.addGenre(g);
					g.addMovie(m);

				}
		
			}
	
		}
	}
	
	
	/**
	 * Assign tags to each movie
	 */
	private void assignTags(){
		
		
		for (Movie m:movies.values()) {
		
			String movieId = m.getMovieId();

			for (TagsRatings t:tags.values()) {

				ArrayList<String> movieIds = new ArrayList<String>();
				movieIds = t.getMovieIds();
		
				if (movieIds.contains(movieId)) {

					m.addTag((Tag) t);

				}
		
			}
	
		}
	}
	
	
	/**
	 * Assign values to tags.
	 */
	private void assignValues2Tags() {
		
		for (Map.Entry<String,ArrayList<String>> t:tagValues.entrySet()) {
		
			String v = t.getValue().get(1);
			
			String k = t.getKey();

			TagsRatings tg = new Tag(); 
			
			try {

				tg = tags.values().stream()
						.filter(w -> w.getId().equalsIgnoreCase(k))
						.findAny()
						.orElse(null);

				tg.setValue(v);
				
			}catch (NullPointerException e) {
				
				continue;
			}


		}
			
		
	}
	
	
	/**
	 * Assign ratings to each movie
	 */
	private void assignRatings(){
				
		for (Movie m:movies.values()) {
		
			String movieId = m.getMovieId();

			for (TagsRatings r:ratings.values()) {
				
				ArrayList<String> movieIds = new ArrayList<String>();
				movieIds = r.getMovieIds();
		
				if (movieIds.contains(movieId)) {

					m.addRating((Rating) r);

				}
		
			}
	
		}
	}
	
	
	
	/**
	 * Load movies
	 */
	@SuppressWarnings("unchecked")
	@Override
	public int loadMovies() throws IOException {
		
		int moviesNum = 0;
		loader = loaderFactory.createLoader("Movie");
		moviesNum = loader.load(movFile, "\t", true, 21, movies,true);
		fileInfo.put(movFile,movies.size());
		return moviesNum;
	}
	
	/**
	 * Load actors
	 */
	@SuppressWarnings("unchecked")
	@Override
	public int loadActors() throws IOException {
		
		int actorsNum = 0;
		loader = loaderFactory.createLoader("Person");
		actorsNum = loader.load(actorFile, "\t", true, 4, actors,true);
		fileInfo.put(actorFile,actors.size());
		assignMovies2Actors();
		return actorsNum;
	}
	
	
	/**
	 * Load directors
	 */
	@SuppressWarnings("unchecked")
	@Override
	public int loadDirectors() throws IOException {
		
		int directorsNum = 0;
		loader = loaderFactory.createLoader("Person");
		directorsNum = loader.load(directorFile, "\t", true, 3, directors,true);
		fileInfo.put(directorFile,directors.size());
		assignDirectors();
		return directorsNum;
	}
	
	
	/**
	 * Load countries
	 */
	@SuppressWarnings("unchecked")
	@Override
	public int loadCountries() throws IOException {
		
		int countriesNum = 0;
		loader = loaderFactory.createLoader("Country");
		countriesNum = loader.load(countryFile, "\t", true, 2, countries,true);
		fileInfo.put(countryFile,countries.size());
		assignCountries();
		return countriesNum;
	}
	
	
	/**
	 * Load genres
	 */
	@SuppressWarnings("unchecked")
	@Override
	public int loadGenres() throws IOException {
		
		int genresNum = 0;
		loader =  loaderFactory.createLoader("Genre");
		genresNum = loader.load(genreFile, "\t", true, 2, genres, true);
		fileInfo.put(genreFile,genres.size());
		assignGenres();
		return genresNum;
	}
	
	/**
	 * Loading tags/ratings.
	 * @param option 1:only tags, 2:only ratings, 3:tags and ratings
	 * @return total number of objects created.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public int loadTagsRatings(int option) {
		
		int totalNumOfObjs = 0;
		
		try {
			
			//load only tags
			if (option == 1) {
				loader = loaderFactory.createLoader("Tag");
				totalNumOfObjs += loader.load(tagFile, "\t", true, 9, tags,true);
				fileInfo.put(tagFile,loader.getNumOfRecords());
				loader.load(tagValuesFile, "\t", true, 2, tagValues,false);



				assignValues2Tags();
				assignTags();
				
			}
			
			//load only ratings
			else if (option == 2) {
				
				loader = loaderFactory.createLoader("Rating");
				totalNumOfObjs += loader.load(ratingFile, "\t", true, 9, ratings,true);
				fileInfo.put(ratingFile,loader.getNumOfRecords());
				assignRatings();
				
			}
			
			//load tags and ratings
			else if (option == 3) {
				
				loader = loaderFactory.createLoader("Tag");
				totalNumOfObjs += loader.load(tagFile, "\t", true, 9, tags,true);
				fileInfo.put(tagFile,loader.getNumOfRecords());
				loader.load(tagValuesFile, "\t", true, 9, tagValues,false);
				assignValues2Tags();
				assignTags();
				
				loader = loaderFactory.createLoader("Rating");
				totalNumOfObjs += loader.load(ratingFile, "\t", true, 9, ratings,true);
				fileInfo.put(ratingFile,loader.getNumOfRecords());
				
				assignValues2Tags();
				assignTags();
				assignRatings();
				
				
			}
			
		}catch(IOException | IllegalArgumentException e) {
			
			System.err.println("Fail to load tags/ratings from input files.");
			System.err.println(e.getMessage());
			System.exit(0);
			
		}
		
		return totalNumOfObjs;
		
	}


	@Override
	public int loadAllData() throws IOException {
		this.loadMovies();
		this.loadActors();
		this.loadDirectors();
		this.loadCountries();
		this.loadGenres();
		return movies.size() + actors.size() + directors.size() + countries.size() + genres.size();
	}

}