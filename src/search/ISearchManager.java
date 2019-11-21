package search;

import java.util.ArrayList;

/**
 * Used by {@link applicationManager.SearchManager} for searching functions.
 * @since 2017-12-15
 * @version 1.0
 *
 */

public interface ISearchManager {
	
	public abstract ArrayList<String> searchFor(String searchKey);
	public abstract String getTitleOfSearch();
	public abstract int getItemsNum();

}