package dataModel;

import java.util.ArrayList;
import java.util.HashMap;


public class Person{

	private String personId;
	private String personName;
	private ArrayList<Movie> movies;
	private ArrayList<String> movieIds;
	private HashMap<String,String> actorRankings;

	public Person() {}
	
	public Person(String anId, String aName) {

		personId = anId;
		personName = aName;
		movies = new ArrayList<Movie>();
		movieIds = new ArrayList<String>();
		actorRankings = new HashMap<String,String>();
	}

	public void addMovie(Movie m) {movies.add(m);}

	public void addMovieId(String id) {movieIds.add(id);}

	public String getPersonId() {return personId;}
	
	public String getPersonName() {
		
		if (personName == null) {
			return "Undefined";
		}
		return personName;
	}	
	
	public ArrayList<Movie> getMovies() {return movies;}

	public ArrayList<String> getMovieIds() {return movieIds;}
	
	public void addRanking(String movieId,String rank) {actorRankings.put(movieId, rank);}

	public String getRankingForMovie(String movieId) {return actorRankings.get(movieId);}
	
	public ArrayList<String> getShortDescription() {
		
		ArrayList<String> desc = new ArrayList<String>();
		desc.add("Name: "+personName);
		
		for (Movie m:movies) {
			desc.add("Title: "+ m.getMovieTitle() + "( Ranking: "+ getRankingForMovie(m.getMovieId()) +" )");
			
		}
		return desc;	
	}
}