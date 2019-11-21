package tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;



import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;


import dataLoad.DataLoader;
import dataLoad.DataProvider;
import dataLoad.IDataProvider;
import dataLoad.ILoaderManager;

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
 * 
 * Testing {@link search.SearchEngine} class and its methods.
 * 
 * @since 2017-11-20
 * @version 1.0
 *
 */

public class SearchEngineTest {

	private SearchMovieDescriptionById searchDescriptionById;
	private SearchMovieDescriptionByTitle searchDescriptionByTitle;
	private SearchMoviesByGenre searchByGenre;
	private SearchMoviesByCountry searchByCountry;
	private SearchMoviesByActor searchByActor;
	private SearchMoviesByDirector searchByDirector;
	private SearchTagsByMovieId searchTagsById;
	private SearchTagsByMovieTitle searchTagsByTitle;
	private SearchRatingsByMovieId searchRatingsById;
	private SearchRatingsByMovieTitle searchRatingsByTitle;

	private static ILoaderManager loaderManager;


	
	

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	
		searchDescriptionById = new SearchMovieDescriptionById();
		searchDescriptionByTitle = new SearchMovieDescriptionByTitle();
		searchByGenre = new SearchMoviesByGenre();
		searchByCountry = new SearchMoviesByCountry();
		searchByActor = new SearchMoviesByActor();
		searchByDirector = new SearchMoviesByDirector();
		searchTagsById = new SearchTagsByMovieId();
		searchTagsByTitle = new SearchTagsByMovieTitle();
		searchRatingsById = new SearchRatingsByMovieId();
		searchRatingsByTitle = new SearchRatingsByMovieTitle();
	
		
		
	}
	
	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		
		loaderManager = DataLoader.getInstance();
		loaderManager.loadAllData();
		loaderManager.loadTagsRatings(1);
		
	}

	
	@Test
	public void testSearchDescriptionByIdNotNull() {
		
		assertNotNull("after setUp() searchDescriptionById must not be null", searchDescriptionById);
		
	}

	
	@Test
	public void testSearchDescriptionByTitleNotNull() {
		
		assertNotNull("after setUp() searchDescriptionByTitle must not be null", searchDescriptionByTitle);
		
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
	
	
	@Test
	public void testSearchByDirectorNotNull() {
		
		assertNotNull("after setUp() searchByDirector must not be null", searchByDirector);
		
	}
	
	
	@Test
	public void testSearchTagsByIdNotNull() {
		
		assertNotNull("after setUp() searchTagsById must not be null", searchTagsById);
		
	}
	
	
	@Test
	public void testSearchTagsByTitleNotNull() {
		
		assertNotNull("after setUp() searchTagsByTitle must not be null", searchTagsByTitle);
		
	}

	
	@Test
	public void testSearchRatingsByIdNotNull() {
		
		assertNotNull("after setUp() searchRatingsById must not be null", searchRatingsById);
		
	}
	
	
	@Test
	public void testSearchRatingsByTitleNotNull() {
		
		assertNotNull("after setUp() searchRatingsByTitle must not be null", searchRatingsByTitle);
		
	}
	

	/**
	 * Test method for {@link search.SearchEngine#searchFor(java.lang.String)}.
	 * @throws IOException
	 */

	@Ignore
	public void testSearchForMovieDescriptionById() throws IOException {
		
		IDataProvider dataProvider = new DataProvider();
		
		
		ArrayList<String> answer = new ArrayList<String>();
	
		answer = dataProvider.getDetailedDescription("1");
		

		
		assertArrayEquals(answer.toArray(), searchDescriptionById.searchFor("1").toArray());
		
	}
	
	
	/**
	 * Test method for {@link search.SearchEngine#searchFor(java.lang.String)}.
	 * @throws IOException
	 */

	@Ignore
	public void testSearchForMovieDescriptionByTitle() throws IOException {
		
		
		IDataProvider dataProvider = new DataProvider();
		
		
		ArrayList<String> answer = new ArrayList<String>();
	
		answer = dataProvider.getDetailedDescription("1");
		
		
		assertArrayEquals(answer.toArray(), searchDescriptionByTitle.searchFor("Toy Story").toArray());
		
	}
	
	
	/**
	 * Test method for {@link search.SearchEngine#searchFor(java.lang.String)}.
	 * @throws IOException
	 */
	
	@Ignore
	public void testSearchByGenre() throws IOException {
		

		
		IDataProvider dataProvider = new DataProvider();


		ArrayList<String> answer = new ArrayList<String>();
		answer = dataProvider.getShortDescriptionByGenre("IMAX");
		
		assertArrayEquals(answer.toArray(), searchByGenre.searchFor("IMAX").toArray());
		
	}
	
	
	
	/**
	 * Test method for {@link search.SearchEngine#searchFor(java.lang.String)}.
	 * @throws IOException
	 */
	
	@Ignore
	public void testSearchByCountry() throws IOException {
		

		
		IDataProvider dataProvider = new DataProvider();


		ArrayList<String> answer = new ArrayList<String>();
		answer = dataProvider.getShortDescriptionByCountry("Greece");
		
		assertArrayEquals(answer.toArray(), searchByCountry.searchFor("Greece").toArray());
		
	}
	
	
	
	/**
	 * Test method for {@link search.SearchEngine#searchFor(java.lang.String)}.
	 * @throws IOException
	 */
	
	@Test
	public void testSearchByActor() throws IOException {
		

		IDataProvider dataProvider = new DataProvider();

		ArrayList<String> answer = new ArrayList<String>();
		answer.add("--\t Short description of movies with  \" "+ "Miranda Otto" + " \"\t-- ");
		dataProvider.getShortDescriptionByActor("miranda otto").forEach(s -> answer.add(s));
		
		
		assertArrayEquals(answer.toArray(), searchByActor.searchFor("miranda otto").toArray());
		
	}
	
	
	
	/**
	 * Test method for {@link search.SearchEngine#searchFor(java.lang.String)}.
	 * @throws IOException
	 */
	
	@Test
	public void testSearchByDirector() throws IOException {
		

		
		IDataProvider dataProvider = new DataProvider();


		ArrayList<String> answer = new ArrayList<String>();
		answer.add("--\t Short description of movies directed by \" "+ "Woody Allen" + " \"\t--");
		dataProvider.getShortDescriptionByDirector("woody allen").forEach(s -> answer.add(s));
		
		assertArrayEquals(answer.toArray(), searchByDirector.searchFor("woody allen").toArray());
		
	}
	
	
	/**
	 * Test method for {@link search.SearchEngine#searchFor(java.lang.String)}.
	 * @throws IOException
	 */
	
	@Ignore
	public void testSearchTagsById() throws IOException {
		

		
		IDataProvider dataProvider = new DataProvider();


		ArrayList<String> answer = new ArrayList<String>();
//		answer.add(dataProvider.getMoviePic("1",1));
		//answer.add(dataProvider.getMoviePic("1"));
		answer.add("Tags assigned to movie \" "+ dataProvider.getMovieTitleWithId("1") + " \"");
		for (String s: dataProvider.getDetailedDescription("1"))
			answer.add(s);
		answer.add("--\tList of tags assigned to \" "+dataProvider.getMovieTitleWithId("1")+" \" \t--");
		answer.add("----------------------------------------------------------------------------------------------------");
		for (String s:dataProvider.getMovieTags("1"))
			answer.add(s);
		
		assertArrayEquals(answer.toArray(), searchTagsById.searchFor("1").toArray());
		
	}
	
	
	/**
	 * Test method for {@link search.SearchEngine#searchFor(java.lang.String)}.
	 * @throws IOException
	 */
	
	@Ignore
	public void testSearchTagsByTitle() throws IOException {
		

		
		IDataProvider dataProvider = new DataProvider();


		ArrayList<String> answer = new ArrayList<String>();
		
		answer.add("Tags assigned to movie \" "+ dataProvider.getMovieTitleWithId("1") + " \"");
		for (String s: dataProvider.getDetailedDescription("1"))
			answer.add(s);
		answer.add("--\tList of tags assigned to \" "+dataProvider.getMovieTitleWithId("1")+" \" \t--");
		answer.add("----------------------------------------------------------------------------------------------------");
		for (String s:dataProvider.getMovieTags("1"))
			answer.add(s);
		
		assertArrayEquals(answer.toArray(), searchTagsByTitle.searchFor("Toy Story").toArray());
		
	}
	
	
	/**
	 * Test method for {@link search.SearchEngine#searchFor(java.lang.String)}.
	 * @throws IOException
	 */
	
	@Ignore
	public void testSearchRatingsById() throws IOException {
		

		
		IDataProvider dataProvider = new DataProvider();


		ArrayList<String> answer = new ArrayList<String>();
		
		answer.add("Ratings assigned to movie \" "+ dataProvider.getMovieTitleWithId("1") + " \"");
		for (String s: dataProvider.getDetailedDescription("1"))
			answer.add(s);
		answer.add("--\tList of ratings assigned to \" "+dataProvider.getMovieTitleWithId("1")+" \" \t--");
		answer.add("----------------------------------------------------------------------------------------------------");
		for (String s:dataProvider.getMovieRatings("1"))
			answer.add(s);
		
		assertArrayEquals(answer.toArray(), searchRatingsById.searchFor("1").toArray());
		
	}
	
	
	
	/**
	 * Test method for {@link search.SearchEngine#searchFor(java.lang.String)}.
	 * @throws IOException
	 */
	
	@Ignore
	public void testSearchRatingsByTitle() throws IOException {
		

		
		IDataProvider dataProvider = new DataProvider();


		ArrayList<String> answer = new ArrayList<String>();
		
		answer.add("Ratings assigned to movie \" "+ dataProvider.getMovieTitleWithId("1") + " \"");
		for (String s: dataProvider.getDetailedDescription("1"))
			answer.add(s);
		answer.add("--\tList of ratings assigned to \" "+dataProvider.getMovieTitleWithId("1")+" \" \t--");
		answer.add("----------------------------------------------------------------------------------------------------");
		for (String s:dataProvider.getMovieRatings("1"))
			answer.add(s);
		
		assertArrayEquals(answer.toArray(), searchRatingsByTitle.searchFor(" Toy Story").toArray());
		
	}

}
