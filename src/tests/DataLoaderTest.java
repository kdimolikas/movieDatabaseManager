package tests;

import static org.junit.Assert.*;


import org.junit.Before;

import org.junit.Test;

import dataLoad.DataLoader;

/**
 * Testing {@link dataLoad.DataLoader} class and its methods 
 * {@link dataLoad.DataLoader#getInstance()},{@link dataLoad.DataLoader#loadAllData()}
 * and {@link dataLoad.DataLoader#loadTagsRatings(int)}
 * @since 2017-11-21
 * @version 1.0
 */
public class DataLoaderTest {

	private DataLoader loaderToTest;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		loaderToTest = DataLoader.getInstance();
	}

	/**
	 * Test method for {@link dataLoad.DataLoader#DataLoader()}.
	 */
	@Test
	public void testDataLoader() {
		assertNotNull("after setUp() loaderToTest must not be null",loaderToTest);
	}
	
	/**
	 * Test method for {@link dataLoad.DataLoader#getInstance()}.
	 */
	@Test
	public void testGetInstance() {
		assertTrue(DataLoader.getInstance() instanceof DataLoader);
	}
}