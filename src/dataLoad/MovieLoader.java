package dataLoad;

import java.util.ArrayList;
import java.util.TreeMap;

import dataModel.Movie;

public class MovieLoader extends AbstractRecordLoader <Movie> {

	@Override
	protected int createObject(ArrayList<String> tokens, TreeMap<String,Movie> movieMap) {
		
		String id;
		String title;
		String year;
		String allCRatings;
		String aRatings;
		String numReviews;
		
		
		id = tokens.get(0);
		title = tokens.get(1);
		year = tokens.get(5);
		allCRatings = tokens.get(7);
		aRatings = tokens.get(17);
		numReviews = tokens.get(8);
		
		Movie m = new Movie(id, title, year, allCRatings, aRatings, numReviews);
		m.setPicUrl(tokens.get(2), tokens.get(4), 1);
		m.setPicUrl(tokens.get(6), tokens.get(tokens.size()-1), 0);
		movieMap.put(id, m);
		
		return 0;
	}

}