package dataLoad;

import java.util.ArrayList;
import java.util.TreeMap;

import dataModel.Rating;

import dataModel.TagsRatings;

public class RatingLoader extends AbstractRecordLoader<TagsRatings> {
	
	
	@Override
	protected int createObject(ArrayList<String> tokens, TreeMap<String, TagsRatings> ratingMap) {
		
		String userId;
		String movieId;
		String rating;
		String dateDay;
		String dateMonth;
		String dateYear;
		String dateHour;
		String dateMin;
		String dateSec;
		
		userId = tokens.get(0);
		movieId = tokens.get(1);
		rating = tokens.get(2);
		dateDay = tokens.get(3);
		dateMonth = tokens.get(4);
		dateYear = tokens.get(5);
		dateHour = tokens.get(6);
		dateMin = tokens.get(7);
		dateSec = tokens.get(8);
		
		
		boolean exists = super.existsInMap(ratingMap,rating);  //check if arg2 exists in arg1
		
		TagsRatings r = !exists ? new Rating(rating,userId) : ratingMap.get(rating);
		
		
		ArrayList<String> date = new ArrayList<String>();
		date.add(dateDay);
		date.add(dateMonth);
		date.add(dateYear);
		date.add(dateHour);
		date.add(dateMin);
		date.add(dateSec);
		
	
		r.addMovieId(movieId);
		r.addUserOfAMovie(movieId, userId);
		r.addSubmissionDate(movieId, userId, date);
		ratingMap.put(rating, r);
		
		return 0;
	}

}
