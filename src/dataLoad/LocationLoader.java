package dataLoad;

import java.util.ArrayList;
import java.util.TreeMap;


import dataModel.Location;

public class LocationLoader extends AbstractRecordLoader<Location> {

	@Override
	protected int createObject(ArrayList<String> tokens, TreeMap<String, Location> locationMap) {
		
		String movieId;
		String countryName;
		String stateName = "";
		String cityName = "";
		String streetName = "";

		movieId = tokens.get(0);
		countryName = tokens.get(1);
		
		
		if (tokens.size() > 2) 
			stateName = tokens.get(2);
		
		if (tokens.size() > 3) 
			cityName = tokens.get(3);
		
		if (tokens.size() > 4)
			streetName = tokens.get(4);
		
		
		boolean exists = existsInMap(locationMap,countryName+"_"+cityName+"_"+streetName);  //check if arg2 exists in arg1
		Location l;
		l = !exists ? new Location(countryName,stateName,cityName,streetName): locationMap.get(countryName+"_"+cityName+"_"+streetName);
		l.addMovieId(movieId);
		
		locationMap.put(countryName+"_"+cityName+"_"+streetName, l);
		
		return 0;
	}
	
	

}
