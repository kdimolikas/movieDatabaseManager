package dataLoad;

import java.util.ArrayList;
import java.util.List;

/**
 * Used by {@link search.SearchEngine} to provide answers to users' questions.
 * @since 2017-12-15|revised 2020-02-18
 * @version 1.0
 */
public interface IDataProvider {
	public abstract ArrayList<String> getDetailedDescription(String key);
	public abstract String getMovieTitleWithId(String id);
	public abstract String getMovieTitleWithTitle(String title);
	public abstract String getPersonName(String anId,int personRole);
	public abstract ArrayList<String> getShortDescriptionByGenre(String genreName);
	public abstract ArrayList<String> getShortDescriptionByCountry(String countryName);
	public abstract ArrayList<String> getShortDescriptionByActor(String actorId);
	public abstract ArrayList<String> getShortDescriptionByDirector(String directorId);
	public abstract ArrayList<String> getMovieTags(String movieId);
	public abstract ArrayList<String> getMovieRatings(String movieId);
	public abstract String getMovieIdWithTitle(String title);
	public abstract int getItemsNum();
	public abstract String getMoviePicUrl(String movieTitle);
	public abstract List<String> getGenres();
	public abstract Iterable<String> getCountries();
	public abstract List<String> getActors();
	public abstract Iterable<String> getDirectors();
}