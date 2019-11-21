package search;

public class SearchEngineFactory {
	

	
	public SearchEngine createSearchEngine(int criterion) {
		
		
		
		if (criterion == 1)
			return new SearchMovieDescriptionById();
		else if (criterion == 2)
			return new SearchMovieDescriptionByTitle();
		else
			throw new IllegalArgumentException("Invalid argument for createSearchEngine(int).");
		
		
		
	}
	
	
	public SearchEngine createSearchEngine(String criterion) {
		
		
		
		 if (criterion.equalsIgnoreCase("Genre"))
			return new SearchMoviesByGenre();
		 else if (criterion.equalsIgnoreCase("Country"))
				return new SearchMoviesByCountry();
		 else if (criterion.equalsIgnoreCase("Actor"))
				return new SearchMoviesByActor();
		 else if (criterion.equalsIgnoreCase("Director"))
				return new SearchMoviesByDirector();
		 else
				throw new IllegalArgumentException("Invalid argument for createSearchEngine(String).");
		
		
		
	}
	
	
	public SearchEngine createSearchEngineForTagsRatings(int criterion) {
		
		
		if (criterion == 1)
			return new SearchTagsByMovieId();
		else if (criterion == 2 )
			return new SearchTagsByMovieTitle();
		else if (criterion == 3)
			return new SearchRatingsByMovieId();
		else if (criterion == 4)
			return new SearchRatingsByMovieTitle();
		 else
			throw new IllegalArgumentException("Invalid argument for createSearchEngineForTagsRatings(int).");
					
		
	}
	

}