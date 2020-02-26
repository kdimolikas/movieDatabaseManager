package search;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dataLoad.IDataProvider;

public class SearchMovieDescriptionById extends SearchEngine {

	
	private String mTitle = " ";
	
	public SearchMovieDescriptionById() {
		//Nothing 2 add
		super();
	}
	
	/**
	 * Used ONLY for testing
	 * @param dProvider
	 */
	public SearchMovieDescriptionById(IDataProvider dProvider) {
		super(dProvider);
	}
	
	@Override
	public List<String> getResponse(String key) {
		
		
		mTitle = getDataProvider().getMovieTitleWithId(key);
		setTitle();
		

		super.updateAnswer(new ArrayList<String>(Arrays.asList(super.getTitleOfSearch())));
		super.updateAnswer(getDataProvider().getDetailedDescription(key));
		super.setItemsNum(getDataProvider().getItemsNum());
		
		return super.getAnswer();
		
	}

	@Override
	public void setTitle() {

		addTitle("--\t Detailed description of \" "+ mTitle + " \"\t--"); 
		
	}
	
}