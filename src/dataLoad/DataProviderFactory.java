package dataLoad;

public class DataProviderFactory {
	
	
	public IDataProvider createDataProvider(){
		
		return new DataProvider();
		
		
	}

}