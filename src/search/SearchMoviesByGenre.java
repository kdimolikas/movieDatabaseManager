package search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dataLoad.IDataProvider;

public class SearchMoviesByGenre extends SearchEngine {

	private String genreName = " ";
	
	public SearchMoviesByGenre() {
		//Nothing 2 add here
		super();
	}
	
	/**
	 * Used ONLY for testing
	 * @param dProvider
	 */
	public SearchMoviesByGenre(IDataProvider dProvider) {
		super(dProvider);
	}
	
	@Override
	public List<String> getResponse(String key) {
				
		genreName = key;
		setTitle();
		
		super.updateAnswer(new ArrayList<String>(Arrays.asList(super.getTitleOfSearch())));
		super.updateAnswer(getDataProvider().getShortDescriptionByGenre(key));
		super.setItemsNum(getDataProvider().getItemsNum());
		
		return super.getAnswer();
	}

	@Override
	public void setTitle() {
		
		addTitle("--\t Short description of movies of genre \" "+ genreName + " \"\t--"); 
		
	}
	

}