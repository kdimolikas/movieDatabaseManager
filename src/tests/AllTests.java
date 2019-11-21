package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
	
/**
 * Running all tests.
 * @author KD
 * @since 2018-10-13
 * @version 1.0
 *
 */
@RunWith(Suite.class)
@SuiteClasses({AbstractRecordLoaderTest.class, DataLoaderTest.class, DataProviderTest.class,
	LoaderFactoryTest.class,ParserTest.class,SearchEngineFactoryTest.class,SearchEngineTest.class})
public class AllTests {

		//nothing to add here

}

