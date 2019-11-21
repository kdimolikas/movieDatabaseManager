package search;

import java.util.ArrayList;
import java.util.Arrays;

public class SearchMoviesByDirector extends SearchEngine {

	private String directorName = " ";
	
	
	@Override
	public void setTitle() {
		
		addTitle("--\t Short description of movies directed by \" "+ directorName + " \"\t--"); 

	}

	@Override
	public ArrayList<String> getResponse(String key) {
	
		//ArrayList<String> answer = new ArrayList<String>();
		
		directorName = getDataProvider().getPersonName(key, 2) ;
		setTitle();
		
		super.updateAnswer(new ArrayList<String>(Arrays.asList(super.getTitle())));
		super.updateAnswer(getDataProvider().getShortDescriptionByDirector(key));
		super.setItemsNum(getDataProvider().getItemsNum());
		
		return super.getAnswer();
	}

}