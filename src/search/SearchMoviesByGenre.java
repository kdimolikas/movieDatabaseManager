package search;

import java.util.ArrayList;
import java.util.Arrays;

public class SearchMoviesByGenre extends SearchEngine {

	private String genreName = " ";
	
	
	@Override
	public ArrayList<String> getResponse(String key) {
		
		
		genreName = key;
		setTitle();
		
		super.updateAnswer(new ArrayList<String>(Arrays.asList(super.getTitle())));
		super.updateAnswer(getDataProvider().getShortDescriptionByGenre(key));
		super.setItemsNum(getDataProvider().getItemsNum());
		
		return super.getAnswer();
	}

	@Override
	public void setTitle() {
		
		addTitle("--\t Short description of movies of genre \" "+ genreName + " \"\t--"); 
		
	}
	

}