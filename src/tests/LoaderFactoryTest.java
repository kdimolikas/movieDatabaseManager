
package tests;

import static org.junit.Assert.*;

import org.junit.Before;

import org.junit.Test;

import dataLoad.AbstractRecordLoader;
import dataLoad.CountryLoader;
import dataLoad.GenreLoader;
import dataLoad.LoaderFactory;
import dataLoad.LocationLoader;
import dataLoad.MovieLoader;
import dataLoad.PersonLoader;
import dataLoad.RatingLoader;
import dataLoad.TagLoader;

/**
 * 
 * Testing the constructor of {@link dataLoad.LoaderFactory} class 
 * and its {@link dataLoad.LoaderFactory#createLoader(java.lang.String)} method.
 * 
 * @version 1.0
 * @since 2017-11-20
 *
 */
public class LoaderFactoryTest {


	private LoaderFactory factoryToTest;
	
		 
	
	@Before
	public void setUp() throws Exception {
		
		factoryToTest = new LoaderFactory();
		
	}
	
	

	@Test
	public void testLoaderFactoryNotNull() {
		assertNotNull("After setUp(), the loaderFactory must not be null.",factoryToTest);
	}
	
	
	
	/**
	 * Test method for {@link dataLoad.LoaderFactory#createLoader(java.lang.String)}.
	 * Tests how createLoader(String) behaves for given valid and invalid arguments. 
	 */
	@SuppressWarnings("rawtypes")
	@Test(expected = IllegalArgumentException.class)
	public void testCreateLoader() {
		
		AbstractRecordLoader loader = factoryToTest.createLoader("Movie");
		assertTrue(loader instanceof MovieLoader );
		
		loader = factoryToTest.createLoader("Person");
		assertTrue(loader instanceof PersonLoader);
		
		
		loader = factoryToTest.createLoader("Genre");
		assertTrue(loader instanceof GenreLoader);
		
		loader = factoryToTest.createLoader("Country");
		assertTrue(loader instanceof CountryLoader);
		
		loader = factoryToTest.createLoader("Location");
		assertTrue(loader instanceof LocationLoader);
		
		loader = factoryToTest.createLoader("Tag");
		assertTrue(loader instanceof TagLoader);
		
		loader = factoryToTest.createLoader("Rating");
		assertTrue(loader instanceof RatingLoader);
		
		loader = factoryToTest.createLoader("wrong argument");
		assertEquals(loader,null);
				
	}

}
