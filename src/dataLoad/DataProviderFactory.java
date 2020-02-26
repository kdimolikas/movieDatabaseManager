package dataLoad;

public class DataProviderFactory {
	public IDataProvider createDataProvider(){
		return DataProvider.getInstance();
	}
}