package search;

import java.util.ArrayList;
import java.util.Arrays;

public class SearchMovieDescriptionByTitle extends SearchEngine {

	private String mTitle = " ";
	
	@Override
	public ArrayList<String> getResponse(String key) {
		
	
		mTitle = getDataProvider().getMovieTitleWithTitle(key);
		String mId = getDataProvider().getMovieIdWithTitle(key);
		setTitle();
		
		super.updateAnswer(new ArrayList<String>(Arrays.asList(super.getTitle())));
		super.updateAnswer(getDataProvider().getDetailedDescription(mId));
		super.setItemsNum(getDataProvider().getItemsNum());
		
		return super.getAnswer();
	}

	@Override
	public void setTitle() {
		
		addTitle("--\t Detailed description of \" "+ mTitle + " \"\t--"); 
		
	}

}