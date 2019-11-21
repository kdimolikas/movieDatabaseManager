package dataLoad;

import java.util.ArrayList;
import java.util.TreeMap;

import dataModel.Genre;

public class GenreLoader extends AbstractRecordLoader<Genre> {
	
	
	@Override
	protected int createObject(ArrayList<String> tokens, TreeMap<String,Genre> genreMap) {
		
		
		String gName;
		String movieId;
		
		
		movieId = tokens.get(0);
		gName = tokens.get(1);

		boolean exists = existsInMap(genreMap,gName);  //check if arg2 exists in arg1
		Genre g;
		g = !exists ? new Genre(gName,movieId) : genreMap.get(gName);
		g.addMovieId(movieId);
		

		genreMap.put(gName,g);
		
		return 0;
		
	}
	
	

}