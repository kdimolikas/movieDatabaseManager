/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import dataLoad.DataLoader;
import dataLoad.DataProvider;
import dataLoad.ILoaderManager;
import dataModel.Movie;

/**
 * @since 2017-11-21
 * @version 1.0
 *
 */
public class DataProviderTest {

	
	private DataProvider dataProviderToTest;
	private static ILoaderManager loaderManager;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		loaderManager = DataLoader.getInstance();
		loaderManager.loadAllData();
		loaderManager.loadTagsRatings(1);
		
		
	}



	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		dataProviderToTest = new DataProvider();
	}



	/**
	 * Test method for {@link dataLoad.DataProvider#DataProvider()}.
	 */
	@Test
	public void testDataProvider() {
		assertNotNull("after setUp(), dataProviderToTest must not be null",dataProviderToTest);
	}

	
	
	/**
	 * Test method for {@link dataLoad.DataProvider#getDetailedDescription(java.lang.String)}.
	 */
	@Test
	public void testGetDetailedDescription() {
		
		Movie m = new Movie();
		m = loaderManager.getMovies().get("1");
		ArrayList<String> answer = m.getDetailedDescription();
		
		assertArrayEquals(answer.toArray(), dataProviderToTest.getDetailedDescription("1").toArray());
		
		
	}

	/**
	 * Test method for {@link dataLoad.DataProvider#getMovieTitleWithId(java.lang.String)}.
	 */
	@Test
	public void testGetMovieTitleWithId() {
		
		Movie m = new Movie();
		m = loaderManager.getMovies().get("1");
		String answer = m.getMovieTitle();
		
		assertEquals(answer, dataProviderToTest.getMovieTitleWithId("1"));
	}

	/**
	 * Test method for {@link dataLoad.DataProvider#getMovieTitleWithTitle(java.lang.String)}.
	 */
	@Test
	public void testGetMovieTitleWithTitle() {
		Movie m = new Movie();
		m = loaderManager.getMovies().get("1");
		String answer = m.getMovieTitle();
		
		assertEquals(answer, dataProviderToTest.getMovieTitleWithTitle("Toy Story"));
	}

	/**
	 * Test method for {@link dataLoad.DataProvider#getMovieIdWithTitle(java.lang.String)}.
	 */
	@Test
	public void testGetMovieIdWithTitle() {
		Movie m = new Movie();
		m = loaderManager.getMovies().get("1");
		String answer = m.getMovieId();
		
		assertEquals(answer, dataProviderToTest.getMovieIdWithTitle("Toy Story"));
	}

	/**
	 * Test method for {@link dataLoad.DataProvider#getPersonName(java.lang.String, int)}.
	 */
	@Ignore
	public void testGetPersonNameById() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link dataLoad.DataProvider#getShortDescriptionByGenre(java.lang.String)}.
	 */
	@Ignore
	public void testGetShortDescriptionByGenre() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link dataLoad.DataProvider#getShortDescriptionByCountry(java.lang.String)}.
	 */
	@Ignore
	public void testGetShortDescriptionByCountry() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link dataLoad.DataProvider#getShortDescriptionByDirector(java.lang.String)}.
	 */
	@Ignore
	public void testGetShortDescriptionByDirector() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link dataLoad.DataProvider#getShortDescriptionByActor(java.lang.String)}.
	 */
	@Ignore
	public void testGetShortDescriptionByActor() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link dataLoad.DataProvider#getMovieTags(java.lang.String)}.
	 */
	@Ignore
	public void testGetMovieTags() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link dataLoad.DataProvider#getMoviePic(java.lang.String, int)}.
	 */
	@Ignore
	public void testGetMoviePic() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link dataLoad.DataProvider#getMovieWithId(java.lang.String)}.
	 */
	@Ignore
	public void testGetMovieWithId() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link dataLoad.DataProvider#getMovieWithTitle(java.lang.String)}.
	 */
	@Ignore
	public void testGetMovieWithTitle() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link dataLoad.DataProvider#getShortDescription(java.util.ArrayList)}.
	 */
	@Ignore
	public void testGetShortDescription() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link dataLoad.DataProvider#getTagDescription(dataModel.Movie)}.
	 */
	@Ignore
	public void testGetTagDescription() {
		fail("Not yet implemented");
	}



}
