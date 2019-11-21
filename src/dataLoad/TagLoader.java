package dataLoad;

import java.util.ArrayList;
import java.util.TreeMap;


import dataModel.Tag;
import dataModel.TagsRatings;

public class TagLoader extends AbstractRecordLoader<TagsRatings> {

	@Override
	protected int createObject(ArrayList<String> tokens, TreeMap<String, TagsRatings> tagMap) {
		
		String userId;
		String movieId;
		String tagId;
		String dateDay;
		String dateMonth;
		String dateYear;
		String dateHour;
		String dateMin;
		String dateSec;
		
		

		userId = tokens.get(0);
		movieId = tokens.get(1);
		tagId = tokens.get(2);
		dateDay = tokens.get(3);
		dateMonth = tokens.get(4);
		dateYear = tokens.get(5);
		dateHour = tokens.get(6);
		dateMin = tokens.get(7);
		dateSec = tokens.get(8);
		
		boolean exists = super.existsInMap(tagMap,tagId);  //check if arg2 exists in arg1
		
		TagsRatings t = !exists ? new Tag(tagId,userId) : tagMap.get(tagId);
		
		
		ArrayList<String> date = new ArrayList<String>();
		date.add(dateDay);
		date.add(dateMonth);
		date.add(dateYear);
		date.add(dateHour);
		date.add(dateMin);
		date.add(dateSec);

		t.addMovieId(movieId);
		t.addSubmissionDate(movieId, userId, date);
		t.addUserOfAMovie(movieId, userId);
		
		tagMap.put(tagId, t);
		
		return 0;
	}

}
