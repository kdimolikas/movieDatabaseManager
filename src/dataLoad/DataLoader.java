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


/**
 * Responsible for loading data from input files.
 * @since 2017-12-14|revised 2020-02-18
 * @version 1.0
 */
public class DataLoader implements ILoaderManager {

	private static final String MOVIES_FILE = "input/movies.dat";
	private static final String ACTORS_FILE = "input/movie_actors.dat";
	private static final String DIRECTORS_FILE = "input/movie_directors.dat";
	private static final String COUNTRIES_FILE = "input/movie_countries.dat";
	private static final String GENRES_FILE = "input/movie_genres.dat";
	@SuppressWarnings("unused")
	private static final String LOCATIONS_FILE = "input/movie_locations.dat";
	
	private static DataLoader instance = null;
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
	
	protected DataLoader() {	
		loaderFactory = new LoaderFactory();		
		movies = new TreeMap<String, Movie>();
		actors = new TreeMap<String, Person>();
		directors = new TreeMap<String, Person>();
		genres = new TreeMap<String, Genre>();
		countries = new TreeMap<String, Country>();
		locations = new TreeMap<String, Location>();
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

	public TreeMap<String, Location> getLocations(){return locations;}

	public HashMap<String, Integer> getFileInfo(){return this.fileInfo;}

	
	
	/**
	 * Interconnects actors to movies.
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
	 * Load movies
	 */
	@SuppressWarnings("unchecked")
	@Override
	public int loadMovies() throws IOException {
		int moviesNum = 0;
		loader = loaderFactory.createLoader("Movie");
		moviesNum = loader.load(MOVIES_FILE, "\t", true, 21, movies,true);
		fileInfo.put(MOVIES_FILE,movies.size());
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
		actorsNum = loader.load(ACTORS_FILE, "\t", true, 4, actors,true);
		fileInfo.put(ACTORS_FILE,actors.size());
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
		directorsNum = loader.load(DIRECTORS_FILE, "\t", true, 3, directors,true);
		fileInfo.put(DIRECTORS_FILE,directors.size());
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
		countriesNum = loader.load(COUNTRIES_FILE, "\t", true, 2, countries,true);
		fileInfo.put(COUNTRIES_FILE,countries.size());
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
		genresNum = loader.load(GENRES_FILE, "\t", true, 2, genres, true);
		fileInfo.put(GENRES_FILE,genres.size());
		assignGenres();
		return genresNum;
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