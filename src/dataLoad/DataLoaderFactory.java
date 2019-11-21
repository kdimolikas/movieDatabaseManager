package dataLoad;

public class DataLoaderFactory {
	
	public ILoaderManager createDataLoader() {
		
		return DataLoader.getInstance();
		
	}

}
