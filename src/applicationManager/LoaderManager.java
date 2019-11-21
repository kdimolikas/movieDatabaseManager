package applicationManager;


import java.io.IOException;
import java.util.HashMap;

import dataLoad.DataLoaderFactory;
import dataLoad.ILoaderManager;


public class LoaderManager {

	private ILoaderManager loader;
	private DataLoaderFactory dLoaderFactory;

	
	public LoaderManager() {
		

		dLoaderFactory = new DataLoaderFactory();
		loader = dLoaderFactory.createDataLoader();
	
	}
	
	
	public int loadAllData() throws IOException {
		
		int temp = -1;
		
		temp = loader.loadAllData();
		
	
		return temp;
	}
	
	
	public int loadTagRatingsData(int option) {
		
		int temp = -1;
		
		temp = loader.loadTagsRatings(option);
		
		
		return temp;
	}
	
	
	public HashMap<String,Integer> getFileInfo(){
		
		return loader.getFileInfo();
		
	}
		
}