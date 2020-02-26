package applicationManager;


import java.io.IOException;
import java.util.HashMap;


/**
 * 
 * Responsible for distributing the main functions of application
 * to {@link applicationManager.LoaderManager}, {@link applicationManager.SearchManager}
 * and {@link applicationManager.FileManager}.
 * @since 2017-12-15
 * @version 1.0
 *
 */
public class MainManager implements IMainApplication {
	
	
	private LoaderManager lManager; 
	private SearchManager sManager; 
	
	
	
	
	public MainManager() {
		
		lManager = new LoaderManager();
		sManager = new SearchManager();
	
		
	}
	

	

	@Override
	public int loadAllData() throws IOException {
		
		int temp = -1;
		long startTime = System.currentTimeMillis();
		long endTime;
		long elapsedTime;

		temp = lManager.loadAllData();
		endTime = System.currentTimeMillis();
		elapsedTime = (endTime - startTime)/1000;
		
		if (temp>0) {
			
			System.out.println("---------------------------------------------------------------");
			System.out.println("Number of objects created: "+ temp);
			System.out.println("Time elapsed (in s): "+ elapsedTime);
			System.out.println("---------------------------------------------------------------");
		}
		
		return temp;
	}
	

	/**
	 * Responsible for calling the proper method of {@link applicationManager.SearchManager} class.
	 * @param qType type of question (1: movie description, 2:searching by criteria, 3: searching tags/ratings).
	 * @param cType defines the criterion of the search (e.g. 1 searching description by movie id, 2 searching by movie title).
	 * @param data a key word to search for.
	 * @return the number of retrieved info.
	 */
	@Override
	public int submitQuery(int qType, int cType,  String data ) {
		
		int searchStatus;
		
		if (qType == 1)


			searchStatus = sManager.searchMovieDesc(data, cType);

		else if (qType == 2) {

			searchStatus = sManager.searchMoviesByCriteria(data, cType);


		}
		else {

			throw new IllegalArgumentException("Invalid argument in submitQuery(int,int,String) method.");
			
		}
		
		return searchStatus;
			
	}

	@Override
	public HashMap<String,Integer> getFileInfo(){
		
		return lManager.getFileInfo();
		
	}
	
}