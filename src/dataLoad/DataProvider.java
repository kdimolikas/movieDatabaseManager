package dataLoad;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;
import java.util.function.Predicate;

import dataModel.Country;
import dataModel.Genre;
import dataModel.Movie;
import dataModel.Person;

import dataModel.TagsRatings;

/**
 * Providing answers to questions submitted by the user. 
 * @since 2017-11-21 | revised on 2020-02-25
 * @version 1.0
 */
public class DataProvider implements IDataProvider {

	private static DataProvider instance = null;
	
	private DataLoaderFactory dLoaderFactory;
	private ILoaderManager dLoader;
	private TreeMap<String, Movie> movies = new TreeMap<String, Movie>();
	private TreeMap<String, Person> actors = new TreeMap<String, Person>();
	private TreeMap<String, Person> directors = new TreeMap<String, Person>();
	private TreeMap<String, Genre> genres = new TreeMap<String, Genre>();
	private TreeMap<String, Country> countries = new TreeMap<String, Country>();
	private int itemsNum;
	
	protected DataProvider() {	
		dLoaderFactory = new DataLoaderFactory();
		dLoader = dLoaderFactory.createDataLoader();			
		movies = dLoader.getMovies();
		actors = dLoader.getActors();
		directors = dLoader.getDirectors();
		genres = dLoader.getGenres();
		countries = dLoader.getCountries();
		itemsNum = 0;
	}
	
	/**
	 * Used ONLY for testing
	 * @param lManager
	 */
	protected DataProvider(ILoaderManager lManager) {		
		dLoader = lManager;			
		movies = dLoader.getMovies();
		actors = dLoader.getActors();
		directors = dLoader.getDirectors();
		genres = dLoader.getGenres();
		countries = dLoader.getCountries();
		itemsNum = 0;
	}
	
	public static DataProvider getInstance() {
		if (instance == null) {
			instance = new DataProvider();
		}
		return instance;
	}
	
	public static DataProvider getInstance(ILoaderManager lManager) {
		if (instance == null) {
			instance = new DataProvider(lManager);
		}
		return instance;
	}
	
	@Override
	public int getItemsNum() {
		return itemsNum;
	}
	
	@Override
	public ArrayList<String> getDetailedDescription(String key) {
		
		ArrayList<String> list = new  ArrayList<String>();
		Movie m = new Movie();
		
		m = getMovieWithId(key);
		
		if (m!=null) {
			this.itemsNum = 1;
			list = m.getDetailedDescription();
		}else
			this.itemsNum = 0;
	
		return list;
	}

	@Override
	public String getMovieTitleWithId(String id) {
		
		@SuppressWarnings("unused")
		Movie m = new Movie();
		try {
			m =  getMovieWithId(id);
			return movies.get(id).getMovieTitle();
		}catch (NullPointerException e) {
			e.printStackTrace();
			return "No movie with the given id.";
		}	
	}

	@Override
	public String getMovieTitleWithTitle(String title) {
		
		Movie m = new Movie();
		try {
			m =  getMovieWithTitle(title);
			return m.getMovieTitle();
		}catch (NullPointerException e) {
			return "No movie with the given title.";
		}
	}
	
	@Override
	public String getMovieIdWithTitle(String title) {
		
		Movie m = new Movie();
		try {
			m =  getMovieWithTitle(title);
			return m.getMovieId();
		}catch(NullPointerException e) {
			return "Undefined";
		}
	}

	@Override
	public String getPersonName(String aName,int personRole) {
		
		Person p = new Person();
		
		try {
			
		//actor
		if (personRole == 1)
			
			p = actors.values().stream()
							   .filter(w -> w.getPersonName().equalsIgnoreCase(aName))
							   .findAny()
							   .orElse(null);
		
		//director
		else if (personRole == 2)
			
			p = directors.values().stream()
			   .filter(w -> w.getPersonName().equalsIgnoreCase(aName))
			   .findAny()
			   .orElse(null);
		
		return p.getPersonName();
		
		}catch (NullPointerException e) {
			return "No person with the given name.";
		}
	}
	
	
	@Override
	public ArrayList<String> getShortDescriptionByGenre(String genreName) {
		
		ArrayList<Movie> movies = new ArrayList<Movie>();
		
		Genre g = genres.values().stream()
								.filter(w -> w.getGenreName().equalsIgnoreCase(genreName))
								.findAny()
								.orElse(null);
		if (g!=null) {
			movies = g.getMovies();
			return getShortDescription(movies);
		}else
			return new ArrayList<String>();
	}
	
	@Override
	public ArrayList<String> getShortDescriptionByCountry(String countryName) {
		
		ArrayList<Movie> movies = new ArrayList<Movie>();
		
		Country c = countries.values().stream()
								.filter(w -> w.getCountryName().equalsIgnoreCase(countryName))
								.findAny()
								.orElse(null);
		if (c!=null) {
			movies = c.getMovies();
			return getShortDescription(movies);
		}else
			return new ArrayList<String>();
		
	}
	
	@Override
	public ArrayList<String> getShortDescriptionByDirector(String directorName) {
		
		ArrayList<Movie> movies = new ArrayList<Movie>();
				
		Person p = directors.values().stream()
				.filter(w -> w.getPersonName().equalsIgnoreCase(directorName))
				.findAny()
				.orElse(null);
		
		if (p != null) {
			movies = p.getMovies();
			return getShortDescription(movies);
		}else
			return new ArrayList<String>();
	}
	
	@Override
	public ArrayList<String> getShortDescriptionByActor(String actorName) {
		
		ArrayList<Movie> movies = new ArrayList<Movie>();
		
		Person p = actors.values().stream()
								.filter(w -> w.getPersonName().equalsIgnoreCase(actorName))
								.findAny()
								.orElse(null);
		
		if (p != null) {
			movies = p.getMovies();
			return getShortDescription(movies);
		}else {
			return new ArrayList<String>();
		}
	}
	
	@Override
	public ArrayList<String> getMovieTags(String key){
		
		ArrayList<String> list = new  ArrayList<String>();
		Movie m = new Movie();
		
		m = getMovieWithId(key);
		
		if (m!=null)
			list = getTagDescription(m);
		
		return list;	
	}
		
	private Movie getMovieWithId(String id){
		return movies.get(id);
	}
		
	private Movie getMovieWithTitle(String aTitle) {
		
		Movie m = null;
		Predicate<Movie> pred = i -> i.getMovieTitle().equalsIgnoreCase(aTitle.trim());
		
		try {
			m = movies.values().stream()
				 .filter(pred)
				 .findAny()
				 .orElse(m);
		}catch(NullPointerException ex) {
			System.err.println("Movie with the given title not found");
			ex.printStackTrace();
		}
		return m;	
	}
	
	public ArrayList<String> getShortDescription(ArrayList<Movie> mList){
		
		ArrayList<String> list = new ArrayList<String>();
		
		for (Movie m:mList) 			
			m.getShortDescription().forEach(s -> list.add(s));
	
		list.add("-------------------------------------------");
		list.add("Number of movies retrieved: "+mList.size());
		list.add("-------------------------------------------");
		this.itemsNum = mList.size();
		return list;
		
	}

	public ArrayList<String> getTagDescription(Movie m){
		
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<TagsRatings> tags = new ArrayList<TagsRatings>();
		
		tags = m.getTags();
		int count = 0;
		for (TagsRatings t:tags) {	
			count += t.getNumOfUsers(m.getMovieId());
			t.getShortDescription(m.getMovieId()).forEach(s -> list.add(s));
			list.add("-------------------------------------------");
		}
		
		this.itemsNum = count;
		
		list.add("-------------------------------------------");
		list.add("Total number of tags retrieved: "+count);
		list.add("-------------------------------------------");
		
		list.add("-------------------------------------------");
		list.add("Number of unique tags retrieved: "+tags.size());
		list.add("-------------------------------------------");
		
		return list;		
	}

	@Override
	public ArrayList<String> getMovieRatings(String key) {
		
		ArrayList<String> list = new  ArrayList<String>();
		Movie m = new Movie();
		
		m = getMovieWithId(key);
		if (m!=null)
			list = getRatingDescription(m);
		
		return list;
	}
	
	public ArrayList<String> getRatingDescription(Movie m){
		
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<TagsRatings> ratings = new ArrayList<TagsRatings>();
		
		ratings = m.getRatings();
		int count = 0;
		for (TagsRatings r:ratings) {	
			count += r.getNumOfUsers(m.getMovieId());
			r.getShortDescription(m.getMovieId()).forEach(s -> list.add(s));
			list.add("-------------------------------------------");
		}
		
		this.itemsNum = count;
		
		list.add("-------------------------------------------");
		list.add("Total number of ratings retrieved: "+count);
		list.add("-------------------------------------------");
		
		list.add("-------------------------------------------");
		list.add("Number of unique ratings retrieved: "+ratings.size());
		list.add("-------------------------------------------");
		
		return list;	
	}

	@Override
	public String getMoviePicUrl(String movieTitle) {
		
		String picUrl = null;
		
		try {
			picUrl = this.getMovieWithTitle(movieTitle).getPicUrl();
		}
		catch(NullPointerException ex) {
			ex.printStackTrace();
		}
		
		return picUrl;
	}
	
	@Override
	public List<String> getGenres(){
		List<String> grs = new ArrayList<String>();
		this.genres.values().forEach(g->
									grs.add(g.getGenreName())
									);
		Collections.sort(grs);
		return grs;
	}
	
	@Override
	public List<String> getCountries(){
		List<String> crs = new ArrayList<String>();
		this.countries.values().forEach(c ->
									crs.add(c.getCountryName())
									);
		Collections.sort(crs);
		return crs;
	}

	@Override
	public List<String> getActors() {
		List<String> ars = new ArrayList<String>();
		this.actors.values().forEach(a -> {
									if (a.getPersonName().length() > 0) {
										ars.add(a.getPersonName());
									}
									});
		return ars;
	}
	
	@Override
	public List<String> getDirectors() {
		List<String> drs = new ArrayList<String>();
		this.directors.values().forEach(d -> {
									if (d.getPersonName().length() > 0) {
										drs.add(d.getPersonName());
									}
									});
		return drs;
	}
}