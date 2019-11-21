package search;

import java.util.ArrayList;
import java.util.Arrays;

public class SearchRatingsByMovieId extends SearchEngine {

	private String mTitle = " ";
	
	
	@Override
	public void setTitle() {
		
		
		addTitle("--\t Ratings assigned to movie \" "+ mTitle + " \"\t--"); 

	}

	@Override
	public ArrayList<String> getResponse(String key) {
		
		mTitle = getDataProvider().getMovieTitleWithId(key);
		setTitle();

		super.updateAnswer(new ArrayList<String>(Arrays.asList(super.getTitle())));
		super.updateAnswer(getDataProvider().getDetailedDescription(key));
		super.updateAnswer(new ArrayList<String>(Arrays.asList("--\t Detailed list of ratings assigned to \" "+mTitle+" \" \t--")));
		super.updateAnswer(new ArrayList<String>(Arrays.asList("----------------------------------------------------------------------------------------------------")));
		super.updateAnswer(getDataProvider().getMovieRatings(key));
		super.setItemsNum(getDataProvider().getItemsNum());

		return super.getAnswer();
	}

}