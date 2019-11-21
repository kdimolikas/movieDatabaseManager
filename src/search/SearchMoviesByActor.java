package search;

import java.util.ArrayList;
import java.util.Arrays;

public class SearchMoviesByActor extends SearchEngine {

	private String actorName = " ";
	
	@Override
	public void setTitle() {
		
		addTitle("--\t Short description of movies with  \" "+ actorName + " \"\t-- "); 

	}

	@Override
	public ArrayList<String> getResponse(String key) {
		
	
		
		actorName = getDataProvider().getPersonName(key, 1) ;
		setTitle();
		
		
		super.updateAnswer(new ArrayList<String>(Arrays.asList(super.getTitle())));
		super.updateAnswer(getDataProvider().getShortDescriptionByActor(key));
		super.setItemsNum(getDataProvider().getItemsNum());
		
		return super.getAnswer();
	}

}