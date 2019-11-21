package dataLoad;

import java.util.ArrayList;
import java.util.TreeMap;

import dataModel.Country;
import dataModel.Genre;
import dataModel.Movie;
import dataModel.Person;

import dataModel.TagsRatings;

/**
 * Providing answers to questions submitted by the user. 
 * @since 2017-11-21
 * @version 1.0
 *
 */

public class DataProvider implements IDataProvider {

	DataLoaderFactory dLoaderFactory;
	ILoaderManager dLoader;
	private TreeMap<String, Movie> movies = new TreeMap<String, Movie>();
	private TreeMap<String, Person> actors = new TreeMap<String, Person>();
	private TreeMap<String, Person> directors = new TreeMap<String, Person>();
	private TreeMap<String, Genre> genres = new TreeMap<String, Genre>();
	private TreeMap<String, Country> countries = new TreeMap<String, Country>();
	private int itemsNum;
	
	
	public DataProvider() {
		
		dLoaderFactory = new DataLoaderFactory();
		dLoader = dLoaderFactory.createDataLoader();
			
		movies = dLoader.getMovies();
		actors = dLoader.getActors();
		directors = dLoader.getDirectors();
		genres = dLoader.getGenres();
		countries = dLoader.getCountries();
		itemsNum = 0;
		
	}
	
	@Override
	public int getItemsNum() {return itemsNum;}
	
	
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
		
		Movie m = new Movie();
		try {
			m =  getMovieWithId(id);
			return m.getMovieTitle();
		}catch (NullPointerException e) {
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
		}else
			return new ArrayList<String>();
		
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
	

	
	public Movie getMovieWithId(String id){return movies.get(id);}
	
	
	
	public Movie getMovieWithTitle(String aTitle) {
		
		Movie m =null;
		m = movies.values().stream()
				 .filter(w -> w.getMovieTitle().equalsIgnoreCase(aTitle))
				 .findAny()
				 .orElse(null);
		
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

}