package search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dataLoad.IDataProvider;

public class SearchMoviesByCountry extends SearchEngine {

	private String countryName = " ";

	public SearchMoviesByCountry() {
		//Nothing 2 add here
	}
	
	/**
	 * Used ONLY for testing
	 * @param dProvider
	 */
	public SearchMoviesByCountry(IDataProvider dProvider) {
		super(dProvider);
	}
	
	@Override
	public void setTitle() {
		
		addTitle("--\t Short description of movies originating from \" "+ countryName + " \"\t--"); 

	}

	@Override
	public List<String> getResponse(String key) {

		countryName = key.toUpperCase();
		setTitle();

		super.updateAnswer(new ArrayList<String>(Arrays.asList(super.getTitleOfSearch())));
		super.updateAnswer(getDataProvider().getShortDescriptionByCountry(key));
		super.setItemsNum(getDataProvider().getItemsNum());
		
		return super.getAnswer();
	}
}