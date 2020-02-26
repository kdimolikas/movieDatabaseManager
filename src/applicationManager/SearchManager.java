package applicationManager;

import java.util.ArrayList;
import java.util.List;

import search.ISearchManager;

import search.SearchEngineFactory;

/**
 * 
 * Responsible for communicating with classes of {@link search} package via {@link search.ISearchManager}.
 * @since 2017-12-15
 * @version 1.0
 *
 */

public class SearchManager {
	
	private ISearchManager answerReceiver;
	private SearchEngineFactory sFactory;
	private List<String> answer;
	private int retrievedItems;
	private long elapsedTime;
	
	
	public SearchManager() {
		
		sFactory = new SearchEngineFactory();
		answer = new ArrayList<String>();
		retrievedItems = 0;
		elapsedTime = (long)0.0;
		
	}
	
	
	public List<String> getAnswer(){return answer;}


	public int searchMovieDesc(String searchKey, int criterion) {
		
		long startTime = System.currentTimeMillis();
		long endTime;
	
		answerReceiver = sFactory.createSearchEngine(criterion);
		
		if (!answerReceiver.equals(null)) {
			
			answer = answerReceiver.searchFor(searchKey);
			endTime = System.currentTimeMillis();
			this.elapsedTime = endTime - startTime;
			this.retrievedItems = answerReceiver.getItemsNum(); 

		}else {
			System.err.println("Search problem.");
			System.exit(0);
		}
		
		
		if (!answer.isEmpty())
			showSearchResults();
		else
			System.out.println("Movie description not found.");
		
		return this.retrievedItems;
		
	}
	
	
	public int searchMoviesByCriteria(String searchKey, int criterion) {
		
		long startTime = System.currentTimeMillis();
		long endTime;
		
		if (criterion == 1)//Search by genre
			answerReceiver = sFactory.createSearchEngine("Genre");
		else if (criterion == 2)//Search by country
			answerReceiver = sFactory.createSearchEngine("Country");
		else if (criterion == 3)//Search by actor
			answerReceiver = sFactory.createSearchEngine("Actor");
		else if (criterion == 4)//Search by director 
			answerReceiver = sFactory.createSearchEngine("Director");
		
		if (!answerReceiver.equals(null)) {
			answer = answerReceiver.searchFor(searchKey);
			endTime = System.currentTimeMillis();
			this.elapsedTime = endTime - startTime;
			this.retrievedItems = answerReceiver.getItemsNum(); 
		}else {
			System.err.println("Search problem.");
			System.exit(0);
		}
		
		if (!answer.isEmpty())
			showSearchResults();
		else
			System.out.println("Movie description not found.");
		
		
		return this.retrievedItems;
		
	}
	
	private void showSearchResults() {


		if (!getAnswer().isEmpty()) {

			System.out.println("--------------------------------------------------------------------------------------");
			System.out.println("Search for: "+answerReceiver.getTitleOfSearch());
			System.out.println("Number of items retrieved: "+ this.retrievedItems);
			System.out.println("Time elapsed (in ms): "+ this.elapsedTime);
			System.out.println("--------------------------------------------------------------------------------------");

		}else
			System.out.println("No results available.");
	}

}