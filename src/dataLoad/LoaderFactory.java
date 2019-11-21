package dataLoad;

/**
 * Creates loaders for the data of input files.
 * @since 2017-11-20
 * 
 *
 */

public class LoaderFactory {
	
	/**
	 * 
	 * @param loaderType defines the type of loader to create. 
	 * @return an instance of one subclass of {@link dataLoad.AbstractRecordLoader} class.
	 */
	@SuppressWarnings("rawtypes")
	public AbstractRecordLoader createLoader(String loaderType){
		
			
			if (loaderType.equals("Movie"))
				return new MovieLoader();
			else if(loaderType.equals("Person"))
				return new PersonLoader();
			else if(loaderType.equals("Genre"))
				return new GenreLoader();
			else if(loaderType.equals("Country"))
				return new CountryLoader();
			else if(loaderType.equals("Location"))
				return new LocationLoader();
			else if(loaderType.equals("Tag"))
				return new TagLoader();
			else if(loaderType.equals("Rating"))
				return new RatingLoader();
			else {
				throw new IllegalArgumentException("\""+loaderType+"\":"+"Invalid argument to create Loader.");
			}

		
	}

}