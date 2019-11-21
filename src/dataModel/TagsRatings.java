package dataModel;


import java.util.ArrayList;

import java.util.HashMap;



public abstract class TagsRatings {
	
	
	private ArrayList<String> movieIds;
	@SuppressWarnings("unused")
	private String userId;

	private HashMap<String,ArrayList<String>> userIdsOfMovie;

	private HashMap<String,String> datesOfSubmission;
	
	
	public TagsRatings(String uId) {
		
		
		userId = uId;

		movieIds = new ArrayList<String>();
		userIdsOfMovie = new HashMap<String,ArrayList<String>>();
	

		datesOfSubmission = new HashMap<String,String>();
		
	}
	
	public abstract ArrayList<String> getShortDescription(String movieId);

	public abstract String getId();

	public abstract void setValue(String v);

	public void addMovieId(String anId) {movieIds.add(anId);}
	
	
	public void addUserOfAMovie(String movieId, String userId) {
		
		ArrayList<String> temp = new ArrayList<String>();
		
		if (!userIdsOfMovie.containsKey(movieId)) {
			temp.add(userId);
			userIdsOfMovie.put(movieId, temp);
			
			
		}else
		
			userIdsOfMovie.get(movieId).add(userId);
		
	}
	
	
	public void addSubmissionDate(String movieId, String userId, ArrayList<String> date) {
	
		
		String d = null;

		String key = null; 

		key = movieId+"_"+userId;
		d = formDate(date);
			
		datesOfSubmission.put(key, d);	
		
	}
	
	
	
	public ArrayList<String> getUsersOfAMovie(String movieId){
		
		return userIdsOfMovie.get(movieId);
		
	}
	
	public int getNumOfUsers(String movieId) {return getUsersOfAMovie(movieId).size();}

	public String getDateOfSubmission(String movieId,String userId){


		String key = null;
		key = movieId+"_"+userId;

		try {

			return datesOfSubmission.get(key);

		}catch(Exception e) {

			System.err.println(e.getMessage());

		}
		return "Undefined";

	}
	
	
	
	public ArrayList<String> getMovieIds(){return movieIds;}
	
	
	
	
	public String formDate(ArrayList<String> aDate) {
		
		ArrayList<String> d = new ArrayList<String>(aDate);
	
		String temp;
		
		try {
			temp = "on "+ d.get(0) + "/" + d.get(1) + "/" + d.get(2) + " at " + d.get(3) + ":" + d.get(4) + ":" + d.get(5);
			return temp;
		}catch (NullPointerException e) {
			
			e.printStackTrace();
		}
		
		
		return "Undefined" ;
		
		
	}

}