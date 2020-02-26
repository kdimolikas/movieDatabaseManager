package search;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import dataLoad.DataProviderFactory;
import dataLoad.IDataProvider;

/**
 * Responsible for retrieving answers to user's questions.
 * @author KD
 * @since 2017-12-14 | revised on 2020-02-25
 * @version 1.0
 */
public abstract class SearchEngine implements ISearchManager {

	private DataProviderFactory dProviderFactory;
	private IDataProvider dProvider;
	private String title;
	private List<String> answer;
	private int retrievedItemsNum;
	
	public SearchEngine() {
		dProviderFactory = new DataProviderFactory();
		dProvider = dProviderFactory.createDataProvider();
		title = "";
		answer = new ArrayList<String>();
		retrievedItemsNum = 0;
	}
	
	/**
	 * Used ONLY for testing
	 * @param dataProvider
	 */
	public SearchEngine(IDataProvider dataProvider) {
		this.dProvider = dataProvider;
		title = "";
		answer = new ArrayList<String>();
		retrievedItemsNum = 0;
	}
		
	public int getItemsNum() {return retrievedItemsNum;}

	public List<String> getAnswer() {return answer;}
	
	public void updateAnswer(ArrayList<String> l) {
		l.forEach(s -> answer.add(s));
	}
	
	public void addTitle(String s) {title = s;}
	
	public String getTitleOfSearch() {return title;}
	
	public IDataProvider getDataProvider() {return dProvider;}
	
	protected abstract void setTitle();
	
	public void setItemsNum(int value) {retrievedItemsNum = value;}
	
	protected abstract List<String> getResponse(String keyWord);
	
	/**
	 * Responsible for finding answers using the given parameter.
	 * @param searchKey a key word given by user.
	 * @return the answer as a {@link java.util.ArrayList}.
	 */
	@Override
	public List<String> searchFor(String searchKey) {	
		answer = getResponse(searchKey);
		return answer;
	}
	
	public String getMoviePicUrl(String movieTitle) {
		return dProvider.getMoviePicUrl(movieTitle);
	}
	
	public Set<String> getGenres(){
		Set<String> genres = new TreeSet<>();
		dProvider.getGenres().forEach(s->genres.add(s));
		return genres;
	}
	
	public Set<String> getCountries(){
		Set<String> countries = new TreeSet<>();
		dProvider.getCountries().forEach(s->countries.add(s));
		return countries;
	}

	public Set<String> getActors() {
		Set<String> actors = new TreeSet<>();
		dProvider.getActors().forEach(a->actors.add(a));;
		return actors;
	}
	
	public Set<String> getDirectors() {
		Set<String> directors = new TreeSet<>();
		dProvider.getDirectors().forEach(d->directors.add(d));;
		return directors;
	}
}