
package tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;


import dataLoad.CountryLoader;
import dataLoad.GenreLoader;
import dataLoad.LocationLoader;
import dataLoad.MovieLoader;
import dataLoad.PersonLoader;
import dataLoad.RatingLoader;
import dataLoad.TagLoader;
import dataModel.Country;
import dataModel.Genre;
import dataModel.Location;
import dataModel.Movie;
import dataModel.Person;
import dataModel.TagsRatings;

/**
 * Testing {@link dataLoad.AbstractRecordLoader} and its {@link dataLoad.AbstractRecordLoader#load(String, String, boolean, int, TreeMap, boolean)} method.
 * 
 * @version 1.0
 * @since 2017-11-19
 * 
 */
public class AbstractRecordLoaderTest {

	
	private MovieLoader movieLoader;
	private PersonLoader personLoader;
	private CountryLoader countryLoader;
	private GenreLoader genreLoader;
	private LocationLoader locationLoader;
	private TagLoader tagLoader;
	private RatingLoader ratingLoader;
	


	@Before
	public void setUp() throws Exception {
		
		movieLoader = new MovieLoader();
		personLoader = new PersonLoader();
		countryLoader = new CountryLoader();
		genreLoader = new GenreLoader();
		locationLoader = new LocationLoader();
		tagLoader = new TagLoader();
		ratingLoader = new RatingLoader();
		
	}

	

	
	@Test
	public void testMovieLoaderNotNull() {
		assertNotNull("After the setup, movieLoader is not null", movieLoader);
	}
	
	
	@Test
	public void testPersonLoaderNotNull() {
		assertNotNull("After the setup, personLoader is not null", personLoader);
	}
	
	@Test
	public void testCountryLoaderNotNull() {
		assertNotNull("After the setup, countryLoader is not null", countryLoader);
	}
	
	
	@Test
	public void testGenreLoaderNotNull() {
		assertNotNull("After the setup, genreLoader is not null", genreLoader);
	}
	
	
	@Test
	public void testLocationLoaderNotNull() {
		assertNotNull("After the setup, locationLoader is not null", locationLoader);
	}
	
	
	@Ignore
	public void testTagLoaderNotNull() {
		assertNotNull("After the setup, tagLoader is not null", tagLoader);
	}
	
	
	@Ignore
	public void testRatingLoaderNotNull() {
		assertNotNull("After the setup, ratingLoader is not null", ratingLoader);
	}

	
	
	@Test
	public void testMovieLoaderRecords() throws IOException {
		
		String movieFile = "input/movies.dat";
		
		TreeMap<String, Movie> movies = new TreeMap<String, Movie>();
		
		assertNotEquals(0,movieLoader.load(movieFile, "\t", true, 21, movies, true));
		assertEquals(9730,movies.size());
		assertEquals(9730,movieLoader.load(movieFile, "\t", true, 21, movies, true));
		
		
		
	}
	
	
	@Test
	public void testActorsLoaderRecords() throws IOException {
		
		String actorFile = "input/movie_actors.dat";
		
		TreeMap<String, Person> actors = new TreeMap<String, Person>();
		
		assertNotEquals(0,personLoader.load(actorFile, "\t", true, 4, actors, true));
		assertEquals(95320,actors.size());
		assertEquals(95320,personLoader.load(actorFile, "\t", true, 4, actors, true));
		
			
	}
	
	
	@Test
	public void testDirectorsLoaderRecords() throws IOException {
		
		String directorFile = "input/movie_directors.dat";
		
		TreeMap<String, Person> directors = new TreeMap<String, Person>();
		
		assertNotEquals(0,personLoader.load(directorFile, "\t", true, 3, directors, true));
		assertEquals(4060,directors.size());
		assertEquals(4060,personLoader.load(directorFile, "\t", true, 3, directors, true));
		
		
	}
	
	
	@Test
	public void testCountryLoaderRecords() throws IOException {
		
		String countryFile = "input/movie_countries.dat";
		
		TreeMap<String, Country> countries = new TreeMap<String, Country>();
		
		assertNotEquals(0,countryLoader.load(countryFile, "\t", true,2, countries, true));
		
		assertEquals(71,countries.size());
		assertEquals(71,countryLoader.load(countryFile, "\t", true, 2, countries, true));
		
		
	}
	
	
	@Test
	public void testGenreLoaderRecords() throws IOException {
		
		String genreFile = "input/movie_genres.dat";
		
		TreeMap<String, Genre> genres = new TreeMap<String, Genre>();
		
		assertNotEquals(0,genreLoader.load(genreFile, "\t", true,2, genres, true));
		
		assertEquals(20,genres.size());
		assertEquals(20,genreLoader.load(genreFile, "\t", true, 2, genres, true));
		
		
	}
	
	
	@Test
	public void testLocationLoaderRecords() throws IOException {
		
		String locationFile = "input/movie_locations.dat";
		
		TreeMap<String, Location> locations = new TreeMap<String, Location>();
		
		assertNotEquals(0,locationLoader.load(locationFile, "\t", true,5, locations, true));
		
		assertEquals(10146,locations.size());
		assertEquals(10146,locationLoader.load(locationFile, "\t", true,5, locations, true));
		
		
	}
	
	
	@Test
	public void testTagLoaderRecords() throws IOException {
		
		String tagFile = "input/user_taggedmovies.dat";
		
		TreeMap<String, TagsRatings> tags = new TreeMap<String, TagsRatings>();
		
		assertNotEquals(0,tagLoader.load(tagFile, "\t", true,9, tags, true));
		
		assertEquals(9079,tags.size());
		assertEquals(9079,tagLoader.load(tagFile, "\t", true,9, tags, true));
		
		
	}
	
	
	@Test
	public void testRatingLoaderRecords() throws IOException {
		
		String ratingFile = "input/user_ratedmovies.dat";
		
		TreeMap<String, TagsRatings> ratings = new TreeMap<String, TagsRatings>();
		
		assertEquals(10,ratingLoader.load(ratingFile, "\t", true,9, ratings, true));
		assertEquals(10,ratings.size());
		
	}
	

}
