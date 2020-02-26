
package tests;

import static org.junit.Assert.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import dataLoad.Parser;

/**
 * Testing {@link dataLoad.Parser} class used to load records from input files.
 * @since 2017-11-19
 * @version 1.0
 */
public class ParserTest {

	private Parser parserToTest;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		parserToTest = new Parser("input/movies.dat");
	}

	/**
	 *Test that the parser is not null after setUp()
	 */
	@Test
	public void testParserNotNull() {
		assertNotNull("After setup, the parser must not be null",parserToTest);
	}
	
	/**
	 * @throws IOException 
	 * Test method for {@link dataLoad.Parser#loadRecords()}.
	 * Tests parser's behavior when the file name is valid.
	 */
	@Test
	public void testLoadRecords() throws IOException {
		parserToTest = new Parser("input/movies.dat");
		assertNotNull("test if loadRecords() works OK",parserToTest.loadRecords());
	}
	
	/**
	 * @throws FileNotFoundException 
	 * Test method for {@link dataLoad.Parser#loadRecords()}.
	 * Tests parser's behavior when the given file name does not exist.
	 */
	@Test(expected = FileNotFoundException.class)
	public void testLoadRecordsForWrongFileName() throws IOException {
		//wrong file path
		Parser failedParser = new Parser("input/movie.dat");
		failedParser.loadRecords();
	}
}
