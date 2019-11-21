package dataModel;

import java.util.ArrayList;

public class Country {
	
	private String countryName;
	private ArrayList<Movie> movies;
	private ArrayList<String> movieIds;
	
	public Country() {}
	
	public Country(String aName) {
		
		countryName = aName;
		movies = new ArrayList<Movie>();
		movieIds = new ArrayList<String>();
		
	}
	

	public String getCountryName() {
		
	
	try {
		return countryName;
	}catch(NullPointerException e) {
		System.err.println(e.getMessage());
		
	}
		
		return countryName;
		
	}
	
	public void addMovie(Movie m) {movies.add(m);}
	
	public void addMovieId(String anId) {movieIds.add(anId);}
	
	public ArrayList<Movie> getMovies(){return movies;}
	
	public ArrayList<String> getMovieIds(){return movieIds;}
	

}