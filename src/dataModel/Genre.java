package dataModel;

import java.util.ArrayList;

public class Genre{
	
	
	private String name;
	private ArrayList<String> movieIds;
	private ArrayList<Movie> movies;
	
	public Genre() {}
	
	public Genre(String aName) {
		
		name = aName;
		movieIds = new ArrayList<String>() ;
		movies = new ArrayList<Movie>();
		
	}
	
	
	public Genre(String aName, String aMovieId) {
		
		name = aName;
		movieIds = new ArrayList<String>() ;
		movies = new ArrayList<Movie>();
		
	}
	
	
	public String getGenreName() {return name;}

	public ArrayList<String> getMovieIds() {return movieIds;}
	
	public void addMovie(Movie m) {movies.add(m);}

	public void addMovieId(String id) {movieIds.add(id);}
	
	public ArrayList<Movie> getMovies(){return movies;}

	//public void setMovies(ArrayList<Movie> list){movies = list;}
	
//	public ArrayList<String> getShortDescription(){
//		
//		ArrayList<String> desc = new ArrayList<String>();
//		
//		for (Movie m:movies)
//			desc.add("Title: "+m.getMovieTitle());
//
//		
//		return desc;
//	}

}
