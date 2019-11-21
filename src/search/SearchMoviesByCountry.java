package search;

import java.util.ArrayList;
import java.util.Arrays;

public class SearchMoviesByCountry extends SearchEngine {

	private String countryName = " ";
	
	
	@Override
	public void setTitle() {
		
		addTitle("--\t Short description of movies originating from \" "+ countryName + " \"\t--"); 

	}

	@Override
	public ArrayList<String> getResponse(String key) {
		
		//ArrayList<String> answer = new ArrayList<String>();
		
		countryName = key.toUpperCase();
		setTitle();
		
//		answer = getDataProvider().getShortDescriptionByCountry(key);
		super.updateAnswer(new ArrayList<String>(Arrays.asList(super.getTitle())));
		super.updateAnswer(getDataProvider().getShortDescriptionByCountry(key));
		super.setItemsNum(getDataProvider().getItemsNum());
		
		return super.getAnswer();
		
		
	}

}