package search;

import java.util.ArrayList;
import java.util.Arrays;

public class SearchTagsByMovieId extends SearchEngine {

	
	private String mTitle = " ";
	
	@Override
	public void setTitle() {
		
		addTitle("--\t Tags assigned to movie \" "+ mTitle + " \"\t--"); 

	}

	@Override
	public ArrayList<String> getResponse(String key) {
	
		
		
		mTitle = getDataProvider().getMovieTitleWithId(key);
		setTitle();

		super.updateAnswer(new ArrayList<String>(Arrays.asList(super.getTitle())));
		super.updateAnswer(getDataProvider().getDetailedDescription(key));
		super.updateAnswer(new ArrayList<String>(Arrays.asList("--\t Detailed list of tags assigned to \" "+mTitle+" \" \t--")));
		super.updateAnswer(new ArrayList<String>(Arrays.asList("----------------------------------------------------------------------------------------------------")));
		super.updateAnswer(getDataProvider().getMovieTags(key));
		super.setItemsNum(getDataProvider().getItemsNum());

		return super.getAnswer();
	}

}