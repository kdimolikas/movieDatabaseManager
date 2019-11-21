
package tests;

import static org.junit.Assert.*;


import org.junit.Before;

import org.junit.Test;

import search.SearchEngine;
import search.SearchEngineFactory;
import search.SearchMovieDescriptionById;
import search.SearchMovieDescriptionByTitle;
import search.SearchMoviesByActor;
import search.SearchMoviesByCountry;
import search.SearchMoviesByDirector;
import search.SearchMoviesByGenre;
import search.SearchRatingsByMovieId;
import search.SearchRatingsByMovieTitle;
import search.SearchTagsByMovieId;
import search.SearchTagsByMovieTitle;

/**
 * Testing {@link search.SearchEngineFactory} class.
 * 
 * @since 2017-11-20
 * @version 1.0
 *
 */
public class SearchEngineFactoryTest {

	private SearchEngineFactory factoryToTest;


	@Before
	public void setUp() throws Exception {
		
		factoryToTest = new SearchEngineFactory();
		
	}


	@Test
	public void testSearchEngineFactoryNotNull() {
		
		assertNotNull("After setUp(), the searchEngineFactory must not be null.",factoryToTest);
		
		
	}
	
	

	/**
	 * Test method for {@link search.SearchEngineFactory#createSearchEngine(int)}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCreateSearchEngineInt() {
		
		SearchEngine searchById = factoryToTest.createSearchEngine(1);
		assertTrue(searchById instanceof SearchMovieDescriptionById);
		
		SearchEngine searchByTitle = factoryToTest.createSearchEngine(2);
		assertTrue(searchByTitle instanceof SearchMovieDescriptionByTitle);
		
		assertNull(factoryToTest.createSearchEngine(3));
		
	}
	
	

	
	/**
	 * Test method for {@link search.SearchEngineFactory#createSearchEngine(java.lang.String)}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCreateSearchEngineString() {
	
		SearchEngine searchByGenre = factoryToTest.createSearchEngine("Genre");
		assertTrue(searchByGenre instanceof SearchMoviesByGenre);
		
		SearchEngine searchByCountry = factoryToTest.createSearchEngine("Country");
		assertTrue(searchByCountry instanceof SearchMoviesByCountry);
		
		SearchEngine searchByActor = factoryToTest.createSearchEngine("Actor");
		assertTrue(searchByActor instanceof SearchMoviesByActor);
		
		SearchEngine searchByDirector = factoryToTest.createSearchEngine("Director");
		assertTrue(searchByDirector instanceof SearchMoviesByDirector);
		
		assertNull(factoryToTest.createSearchEngine("wrong argument"));
		
	}

	/**
	 * Test method for {@link search.SearchEngineFactory#createSearchEngineForTagsRatings(int)}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCreateSearchEngineForTagsRatings() {
		
		SearchEngine searchTagsRatings = factoryToTest.createSearchEngineForTagsRatings(1);
		assertTrue(searchTagsRatings instanceof SearchTagsByMovieId);
		
		searchTagsRatings = factoryToTest.createSearchEngineForTagsRatings(2);
		assertTrue(searchTagsRatings instanceof SearchTagsByMovieTitle);
		
		searchTagsRatings = factoryToTest.createSearchEngineForTagsRatings(3);
		assertTrue(searchTagsRatings instanceof SearchRatingsByMovieId);
		
		searchTagsRatings = factoryToTest.createSearchEngineForTagsRatings(4);
		assertTrue(searchTagsRatings instanceof SearchRatingsByMovieTitle);
		
		assertNull(factoryToTest.createSearchEngineForTagsRatings(5));
		
	}

}
