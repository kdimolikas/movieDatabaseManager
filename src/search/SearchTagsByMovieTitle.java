package search;

import java.util.ArrayList;
import java.util.Arrays;

public class SearchTagsByMovieTitle extends SearchEngine {

	private String mTitle = " ";
	
	@Override
	public void setTitle() {
		addTitle("--\t Tags assigned to movie \" "+ mTitle + " \"\t--"); 

	}

	@Override
	public ArrayList<String> getResponse(String key) {
		
	
		
		mTitle = getDataProvider().getMovieTitleWithTitle(key);
		String mId =  getDataProvider().getMovieIdWithTitle(key);
		setTitle();
		super.updateAnswer(new ArrayList<String>(Arrays.asList(super.getTitle())));
		super.updateAnswer(getDataProvider().getDetailedDescription(mId));
		super.updateAnswer(new ArrayList<String>(Arrays.asList("--\t Detailed list of tags assigned to \" "+mTitle+" \" \t--")));
		super.updateAnswer(new ArrayList<String>(Arrays.asList("----------------------------------------------------------------------------------------------------")));
		super.updateAnswer(getDataProvider().getMovieTags(mId));
		super.setItemsNum(getDataProvider().getItemsNum());
		
		return super.getAnswer();
	}

}