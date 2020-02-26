package applicationManager;


import java.io.IOException;
import java.util.HashMap;

public interface IMainApplication {
	
	public abstract int loadAllData() throws IOException;//Load all data about movies
	public abstract int submitQuery(int queryType, int criterion, String data);//Submit a query about movies
	public abstract HashMap<String, Integer> getFileInfo();
	
	
}