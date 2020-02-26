/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

import dataLoad.DataProvider;
import dataLoad.ILoaderManager;
import dataModel.Movie;
import dataModel.Person;

/**
 * Testing {@link dataLoad.DataProvider} class.
 * @since 2020-02-21
 * @version 1.0
 */
@RunWith(MockitoJUnitRunner.class)
public class DataProviderTest {

	@InjectMocks
	private DataProvider dataProviderToTest;
	
	@Mock
	private ILoaderManager mockLoader;

	@Mock
	private Movie mockMovie; 
	
	@Mock
	Person mockPerson1;

	@Mock
	Person mockPerson2;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

		when(mockMovie.getMovieTitle()).thenReturn("Toy Story");
		
		when(mockMovie.getMovieId()).thenReturn("1");
		
		when(mockPerson1.getPersonName()).thenReturn("Tom Hanks");
	
		when(mockPerson2.getPersonName()).thenReturn("Tim Allen");
		
		when(mockMovie.getPicUrl()).thenReturn("http://toy_story.png");
		
		when(mockLoader.getMovies()).thenReturn(new TreeMap<String,Movie>(){
			private static final long serialVersionUID = 1L;
			{
				put("1", mockMovie);
			}
		});
		
		when(mockLoader.getActors()).thenReturn(new TreeMap<String, Person>() {
			private static final long serialVersionUID = 1L;
			{
				put("1",mockPerson1);
				put("2",mockPerson2);
			}
		});
		
		dataProviderToTest = DataProvider.getInstance(mockLoader);
	}

	/**
	 * Test method for {@link dataLoad.DataProvider#DataProvider()}.
	 */
	@Test
	public void testDataProvider() {
		assertNotNull("after setUp(), dataProviderToTest must not be null", dataProviderToTest);
	}

	/**
	 * Test method for
	 * {@link dataLoad.DataProvider#getMovieTitleWithId(java.lang.String)}.
	 */
	@Test
	public void testGetMovieTitleWithId() {
		assertEquals(mockMovie.getMovieTitle(),dataProviderToTest.getMovieTitleWithId("1"));
	}

	/**
	 * Test method for
	 * {@link dataLoad.DataProvider#getMovieTitleWithTitle(java.lang.String)}.
	 */
	@Test
	public void testGetMovieTitleWithTitle() {
		assertEquals(mockMovie.getMovieTitle(), dataProviderToTest.getMovieTitleWithTitle("Toy Story"));
	}

	/**
	 * Test method for
	 * {@link dataLoad.DataProvider#getMovieIdWithTitle(java.lang.String)}.
	 */
	@Test
	public void testGetMovieIdWithTitle() {
		assertEquals(mockMovie.getMovieId(), dataProviderToTest.getMovieIdWithTitle("Toy Story"));
	}

	/**
	 * Test method for
	 * {@link dataLoad.DataProvider#getPersonName(java.lang.String, int)}.
	 */
	@Test
	public void testGetPersonName() {
		assertEquals(mockPerson1.getPersonName(),
					dataProviderToTest.getPersonName(mockPerson1.getPersonName(),1));
		assertEquals(mockPerson2.getPersonName(),
					dataProviderToTest.getPersonName(mockPerson2.getPersonName(),1));
	}
	
	/**
	 * Test method for
	 * {@link dataLoad.DataProvider#getMoviePicUrl(String)}
	 */
	@Test
	public void testGetMoviePicUrl() {
		assertNotNull("Movie pic not defined",
				dataProviderToTest.getMoviePicUrl(mockMovie.getMovieTitle()));
	}
}