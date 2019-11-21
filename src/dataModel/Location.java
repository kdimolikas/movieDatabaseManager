package dataModel;

import java.util.ArrayList;

public class Location {
	
	@SuppressWarnings("unused")
	private String countryName;
	@SuppressWarnings("unused")
	private String stateName;
	@SuppressWarnings("unused")
	private String cityName;
	@SuppressWarnings("unused")
	private String streetName;

	private ArrayList<String> movieIds;

	
	public Location() {}
	
	public Location(String loc1,String loc2,String loc3,String loc4) {
		
		countryName = loc1;
		stateName = loc2;
		cityName = loc3;
		streetName = loc4;

		movieIds = new ArrayList<String>();
		
	}
	

	
	public void addMovieId(String anId) {movieIds.add(anId);}
	

	
	public ArrayList<String> getMovieIds(){return movieIds;}
	
}