package search;


import java.util.ArrayList;


import dataLoad.DataProviderFactory;
import dataLoad.IDataProvider;

/**
 * Responsible for retrieving answers to user's questions.
 * @since 2017-12-14
 * @version 1.0
 *
 */

public abstract class SearchEngine implements ISearchManager {

	private DataProviderFactory dProviderFactory;
	private IDataProvider dProvider;
	private String title;
	private ArrayList<String> answer;
	private int retrievedItemsNum;
	
	
	public SearchEngine() {
		
		dProviderFactory = new DataProviderFactory();
		dProvider = dProviderFactory.createDataProvider();
		title = "";
		answer = new ArrayList<String>();
		retrievedItemsNum = 0;
	}
	
	
	public String getTitleOfSearch() {return title;}
	
	public int getItemsNum() {return retrievedItemsNum;}

	public ArrayList<String> getAnswer() {return answer;}
	
	public void updateAnswer(ArrayList<String> l) {
		
		l.forEach(s -> answer.add(s));
		
	}
	
	public void addTitle(String s) {title = s;}
	
	public String getTitle() {return title;}
	
	public IDataProvider getDataProvider() {return dProvider;}
	
	
	public abstract void setTitle();
	
	public void setItemsNum(int value) {retrievedItemsNum = value;}
	
	public abstract ArrayList<String> getResponse(String keyWord);
	

	
	/**
	 * Responsible for finding answers using the given parameter.
	 * @param searchKey a key word given by user.
	 * @return the answer as a {@link java.util.ArrayList}.
	 */
	@Override
	public ArrayList<String> searchFor(String searchKey) {
		
		ArrayList<String> answer = new ArrayList<String>();
		
		answer = getResponse(searchKey);
	
		
		return answer;
	}
	

}