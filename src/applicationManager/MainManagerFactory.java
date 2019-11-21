package applicationManager;

public class MainManagerFactory {
	
	public IMainApplication createMainManager() {
		
		return new MainManager();
	}

}