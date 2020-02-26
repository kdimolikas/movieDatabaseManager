package tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Before;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.when;

import dataLoad.IDataProvider;
import search.SearchMovieDescriptionById;
import search.SearchMoviesByActor;
import search.SearchMoviesByCountry;
import search.SearchMoviesByGenre;

/**
 * Testing {@link search.SearchEngine} class and its methods.
 * @since 2020-02-21
 * @version 1.0
 */
@RunWith(MockitoJUnitRunner.class)
public class SearchEngineTest {

	private static final String MOVIE_TITLE = "Toy Story"; 
	private static final String ACTOR_NAME = "Tom Hanks"; 
	private static final String GENRE_NAME = "Comedy"; 
	private static final String COUNTRY_NAME = "GREECE"; 
	private static final String MOVIE_SEARCH_TITLE =
							"--\t Detailed description of \" "+ MOVIE_TITLE + " \"\t--"; 
	private static final String ACTOR_SEARCH_TITLE =
			"--\t Short description of movies with  \" "+ ACTOR_NAME + " \"\t-- "; 
	private static final String GENRE_SEARCH_TITLE =
			"--\t Short description of movies of genre \" "+ GENRE_NAME + " \"\t--"; 
	private static final String COUNTRY_SEARCH_TITLE =
			"--\t Short description of movies originating from \" "+ COUNTRY_NAME + " \"\t--";
	
	@InjectMocks
	private SearchMovieDescriptionById searchById;
	@InjectMocks
	private SearchMoviesByGenre searchByGenre;
	@InjectMocks
	private SearchMoviesByCountry searchByCountry;
	@InjectMocks
	private SearchMoviesByActor searchByActor;

	@Mock
	private static IDataProvider mockDataProvider;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	
		when(mockDataProvider.getMovieTitleWithId("1")).thenReturn(MOVIE_TITLE);
		when(mockDataProvider.getItemsNum()).thenReturn(1);
		when(mockDataProvider.getDetailedDescription("1")).thenReturn(
								new ArrayList<String>(Arrays.asList(MOVIE_TITLE,
																  	"1"
												 				 	)
													)
		);
		
		when(mockDataProvider.getShortDescriptionByGenre(GENRE_NAME)).thenReturn(
				new ArrayList<String>(Arrays.asList(MOVIE_TITLE))
		);
		
		when(mockDataProvider.getShortDescriptionByCountry(COUNTRY_NAME)).thenReturn(
				new ArrayList<String>(Arrays.asList(MOVIE_TITLE))
		);
		
		when(mockDataProvider.getPersonName(ACTOR_NAME, 1)).thenReturn(
				ACTOR_NAME);
		when(mockDataProvider.getShortDescriptionByActor(ACTOR_NAME)).thenReturn(
				new ArrayList<String>(Arrays.asList(MOVIE_TITLE))
		);
		
		searchById = new SearchMovieDescriptionById(mockDataProvider);
		searchByGenre = new SearchMoviesByGenre(mockDataProvider);
		searchByCountry = new SearchMoviesByCountry(mockDataProvider);
		searchByActor = new SearchMoviesByActor(mockDataProvider);
	}
	
	@Test
	public void testSearchDescriptionByIdNotNull() {
		assertNotNull("after setUp() searchDescriptionById must not be null", searchById);
	}
	
	@Test
	public void testSearchByGenreNotNull() {		
		assertNotNull("after setUp() searchByGenre must not be null", searchByGenre);
	}

	@Test
	public void testSearchByCountryNotNull() {
		assertNotNull("after setUp() searchByCountry must not be null", searchByCountry);
	}
	
	@Test
	public void testSearchByActorNotNull() {	
		assertNotNull("after setUp() searchByActor must not be null", searchByActor);
	}
		
	/**
	 * Test method for {@link search.SearchEngine#searchFor(java.lang.String)}.
	 * @throws IOException
	 */
	@Test
	public void testSearchForMovieDescriptionById() throws IOException {
		List<String> answer = Collections.singletonList(null);
		answer = searchById.searchFor("1");
		assertEquals(MOVIE_SEARCH_TITLE, answer.get(0));	
		assertEquals(MOVIE_TITLE, answer.get(1));		
	}
	
	/**
	 * Test method for {@link search.SearchEngine#searchFor(java.lang.String)}.
	 * @throws IOException
	 */
	@Test
	public void testSearchByGenre() throws IOException {
		List<String> answer = Collections.singletonList(null);
		answer = searchByGenre.searchFor(GENRE_NAME);
		assertEquals(GENRE_SEARCH_TITLE, answer.get(0));
		assertEquals(MOVIE_TITLE, answer.get(1));
	}
	
	/**
	 * Test method for {@link search.SearchEngine#searchFor(java.lang.String)}.
	 * @throws IOException
	 */
	@Test
	public void testSearchByCountry() throws IOException {
		List<String> answer = Collections.singletonList(null);
		answer = searchByCountry.searchFor(COUNTRY_NAME);
		assertEquals(COUNTRY_SEARCH_TITLE, answer.get(0));
	}
	
	/**
	 * Test method for {@link search.SearchEngine#searchFor(java.lang.String)}.
	 * @throws IOException
	 */
	@Test
	public void testSearchByActor() throws IOException {
		List<String> answer = Collections.singletonList(null);
		answer = searchByActor.searchFor(ACTOR_NAME);
		assertEquals(ACTOR_SEARCH_TITLE, answer.get(0));
		assertEquals(mockDataProvider.getShortDescriptionByActor(ACTOR_NAME).get(0), answer.get(1));
	}
}