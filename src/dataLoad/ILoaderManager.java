package dataLoad;


import java.io.IOException;
import java.util.HashMap;
import java.util.TreeMap;

import dataModel.Country;
import dataModel.Genre;
import dataModel.Location;
import dataModel.Movie;
import dataModel.Person;
import dataModel.TagsRatings;

/**
 * Used by {@link applicationManager.LoaderManager} for loading functions,
 * as well as by {@link dataLoad.DataProvider} to provide collections of the created objects.
 * @since 2017-12-15
 * @version 1.0
 */
public interface ILoaderManager {
	
	int loadTagsRatings(int option);
	TreeMap<String, Movie> getMovies();
	TreeMap<String, Genre> getGenres();
	TreeMap<String, Person> getActors();
	TreeMap<String, Person> getDirectors();
	TreeMap<String, Country> getCountries();
	TreeMap<String, TagsRatings> getTags();
	TreeMap<String, TagsRatings> getRatings();
	TreeMap<String, Location> getLocations();
	HashMap<String,Integer> getFileInfo();
	int loadAllData() throws IOException;
	int loadMovies() throws IOException;
	int loadActors() throws IOException;
	int loadDirectors() throws IOException;
	int loadCountries() throws IOException;
	int loadGenres() throws IOException;
	
}