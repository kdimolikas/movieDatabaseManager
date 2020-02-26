package dataModel;

import java.io.IOException;
import java.net.HttpURLConnection;

import java.net.URL;
import java.util.ArrayList;

import java.util.HashMap;


/**
 * Models a Movie
 * @since 2020-02
 * @version 1.0
 * @author KD
 */
public class Movie {
	
	private static final String NO_IMAGE = "file:resources/images/noimage.jpg";
	
	private String id;
	private String title;
	private String year;
	private String allCriticsRating;
	private String allCriticsNumReviews;
	private String audienceRating;
	private ArrayList<Person> actors;
	private ArrayList<Genre> genres;
	private ArrayList<TagsRatings> tags;
	private ArrayList<TagsRatings> ratings;
	private ArrayList<Location> locations;
	private Person director;
	private HashMap<String,String> imdbUrlPic;
	private HashMap<String,String> rtUrlPic;
	private Country country;

	public Movie() {}
	
	public Movie(String anId, String aTitle, String aYear, String allCRatings, String aRatings, String numReviews) {
		
		id = anId;
		title = aTitle;
		year = aYear;
		allCriticsRating = allCRatings; 
		audienceRating = aRatings;
		allCriticsNumReviews = numReviews;
		actors = new ArrayList<Person>();
		director = new Person();
		genres = new ArrayList<Genre>();
		imdbUrlPic = new HashMap<String,String>();
		rtUrlPic = new HashMap<String,String>();
		country = new Country();
		locations = new ArrayList<Location>();
		tags = new ArrayList<TagsRatings>();
		ratings = new ArrayList<TagsRatings>();
		
	}
	
	public void addActor(Person p) {actors.add(p);}

	public void addGenre(Genre g) {genres.add(g);}

	public void addLocation(Location l) {locations.add(l);}
	
	public void addTag(Tag t) {tags.add(t);}
	
	public void setDirector(Person d) {director = d;}
	
	public void setCountry(Country c) {country = c;}
	
	public void setGenres(ArrayList<Genre> aList){ genres = aList;}
	
	public void setPicUrl(String sourceId, String aUrl, int isImdb) {
		if (isImdb == 1) {
				imdbUrlPic.put(sourceId,aUrl);
		}else if (isImdb == 0) {
				rtUrlPic.put(sourceId,aUrl);				
		}else
			System.out.println("Invalid value for source argument.");
	}

	public String getPicUrl(){
		String pic = NO_IMAGE;
		pic = imdbUrlPic.values().stream().findFirst().get();
		if (!exists(pic)) {
			pic = rtUrlPic.values().stream().findFirst().get();
			if (!exists(pic))
				pic = NO_IMAGE;
		}
		return pic; 	
	}

	/**
	 * Checks if the given url exists
	 * @param a string corresponding to a url
	 * @return true if the given url exists
	 * @throws IOException
	 */
	private boolean exists(String aUrl){
		
		int code = 404;
		
		try {
		URL u = new URL(aUrl);
		HttpURLConnection huc =  ( HttpURLConnection ) u.openConnection (); 
		huc.setRequestMethod("GET");
	
		huc.connect();
		
		code = huc.getResponseCode();
		huc.disconnect();
		
		}catch( IOException e) {
			
			e.printStackTrace();
			
		}
		
		if (code != 404)
			return true;
		
		return false;
	}
	

	public ArrayList<Person> getMovieActors(){return actors;}

	
	public String getMovieTitle() {
		if (title == null) {
			title = "No available title.";
		}
		return title;
	}


	public String getMovieId() {return id;}
	
	public ArrayList<Genre> getGenres(){return genres;}

	public ArrayList<TagsRatings> getTags(){return tags;}

	public ArrayList<TagsRatings> getRatings(){return ratings;}

	public String getCriticsRatings() {return allCriticsRating;}
	
	public ArrayList<String> getDetailedDescription() {
		
		ArrayList<String> desc = new ArrayList<String>();
		ArrayList<String> castList = new ArrayList<String>();
		ArrayList<String> gList = new ArrayList<String>();
		
		actors.forEach(a -> castList.add(a.getPersonName()));
		genres.forEach(g -> gList.add(g.getGenreName()));
		String s = null;
		String c = null;
		c = String.join(",", castList);
		s = String.join(",",gList);
		
		desc.add(this.getPicUrl());
		desc.add("Title: "+this.getMovieTitle());
		desc.add("Year: "+year);
		desc.add("Director: "+director.getPersonName());
		desc.add("Cast:\t"+c);
		desc.add("Country: "+country.getCountryName());
		desc.add("Critics Ratings: "+allCriticsRating);
		desc.add("Audience Ratings: "+audienceRating);
		desc.add("Number of reviews: "+allCriticsNumReviews);
		desc.add( "Genre:\t"+s);
		//desc.add("----------------------------------------------------------------------");
		
		return desc;
	}
	
	public ArrayList<String> getShortDescription() {
		
		ArrayList<String> desc = new ArrayList<String>();

		desc.add("Title: "+this.getMovieTitle());
		desc.add("Year: "+year);
		desc.add("Director: "+director.getPersonName());
		//desc.add("---------------------------------------------------------------------");
	
		return desc;
	}
}