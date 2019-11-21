package dataLoad;

import java.util.ArrayList;
import java.util.TreeMap;

import dataModel.Country;


public class CountryLoader extends AbstractRecordLoader<Country> {
	
	
	@Override
	protected int createObject(ArrayList<String> tokens, TreeMap<String,Country> countryMap) {
		
		String movieId;
		String name;

		movieId = tokens.get(0);
		name = tokens.get(1);
		
		boolean exists = existsInMap(countryMap,name);  //check if arg2 exists in arg1
		
		Country c;
		c=!exists ? new Country(name): countryMap.get(name);
		c.addMovieId(movieId);
		
		countryMap.put(name, c);
		
		return 0;
		
	}
	

}
