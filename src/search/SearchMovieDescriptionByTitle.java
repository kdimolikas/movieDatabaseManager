package search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchMovieDescriptionByTitle extends SearchEngine {

	private String mTitle = " ";
	
	@Override
	public List<String> getResponse(String key) {
		
	
		mTitle = getDataProvider().getMovieTitleWithTitle(key);
		String mId = getDataProvider().getMovieIdWithTitle(key);
		setTitle();
		
		super.updateAnswer(new ArrayList<String>(Arrays.asList(super.getTitleOfSearch())));
		super.updateAnswer(getDataProvider().getDetailedDescription(mId));
		super.setItemsNum(getDataProvider().getItemsNum());
		
		return super.getAnswer();
	}

	@Override
	public void setTitle() {
		
		addTitle("--\t Detailed description of \" "+ mTitle + " \"\t--"); 
		
	}
}