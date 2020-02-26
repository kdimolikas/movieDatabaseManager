package search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dataLoad.IDataProvider;

public class SearchMoviesByActor extends SearchEngine {

	private String actorName = " ";
	
	public SearchMoviesByActor() {
		//Nothing 2 add here
	}
	
	/**
	 * Used ONLY for testing
	 * @param dProvider
	 */
	public SearchMoviesByActor(IDataProvider dProvider) {
		super(dProvider);
	}
	
	@Override
	public void setTitle() {
		
		addTitle("--\t Short description of movies with  \" "+ actorName + " \"\t-- "); 

	}

	@Override
	public List<String> getResponse(String key) {
		
	
		
		actorName = getDataProvider().getPersonName(key, 1) ;
		setTitle();
		
		
		super.updateAnswer(new ArrayList<String>(Arrays.asList(super.getTitleOfSearch())));
		super.updateAnswer(getDataProvider().getShortDescriptionByActor(key));
		super.setItemsNum(getDataProvider().getItemsNum());
		
		return super.getAnswer();
	}

}