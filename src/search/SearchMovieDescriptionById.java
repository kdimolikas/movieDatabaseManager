package search;


import java.util.ArrayList;
import java.util.Arrays;

public class SearchMovieDescriptionById extends SearchEngine {

	
	private String mTitle = " ";
	
	@Override
	public ArrayList<String> getResponse(String key) {
		
		
		mTitle = getDataProvider().getMovieTitleWithId(key);
		setTitle();
		

		super.updateAnswer(new ArrayList<String>(Arrays.asList(super.getTitle())));
		super.updateAnswer(getDataProvider().getDetailedDescription(key));
		super.setItemsNum(getDataProvider().getItemsNum());
		
		return super.getAnswer();
		
	}

	@Override
	public void setTitle() {

		addTitle("--\t Detailed description of \" "+ mTitle + " \"\t--"); 
		
	}
	
}