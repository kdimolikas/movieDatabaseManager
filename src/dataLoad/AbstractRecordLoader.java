package dataLoad;


import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
 * Used by {@link dataLoad.DataLoader} for loading data from input files and creating objects
 * representing this data.
 * @since 2017-11-20
 * @version 1.0
 *
 * @param <E> a class of {@link dataModel}.
 */

public abstract class AbstractRecordLoader <E> {
	
	private int recordsNum;
	
	
	public int getNumOfRecords() {return recordsNum;}
	
	protected abstract int createObject(ArrayList<String> tokens, TreeMap<String,E> objCollection);
	
	
	
	@SuppressWarnings("unchecked")
	public int load(String fileName, String delimeter, boolean hasHeaderLine, int numFields, TreeMap<String,E> objCollection, boolean isObject) throws IOException {
		
		Parser parser = null;
		
		if (numFields<1) {
			
			System.out.println("The number of fields must be greater than 1!");
			System.exit(0);
			
		}
		
		if (!fileName.equals(null))
			parser = new Parser(fileName);
		
		else {
			
			System.err.println("The file name can't be null");
			System.exit(0);
			
		}
		
		ArrayList<String> records = new ArrayList<String>();
		
		records = parser.loadRecords();
		
		
		if (hasHeaderLine) {
			
			records.remove(0);
		}
		
		this.recordsNum = records.size();
		
		try {
			
			for (String line:records) {

				ArrayList<String> tokens = new ArrayList<String>();
				tokens = getTokensList(line, delimeter);


				//true if objects will be created
				if (isObject) {

					int objCodeError;

					if (tokens.size() == numFields) {

						objCodeError = createObject(tokens,objCollection);


						if (objCodeError!=0) {

							System.out.println("Error at creating objects.");
							System.exit(0);

						}

					}else
						continue;


				//add data from input file to Map argument
				}else {

					objCollection.put(tokens.get(0), (E)tokens);
				}

			}
			
			if (objCollection.size() > 0) {
				if (isObject) {
					System.out.println(objCollection.size()+" objects have been created.");
					System.out.println("-----------------------------------------------------------------------------------------------------------------");
				}
				return objCollection.size();
			}
			
		}catch (NullPointerException e) {
			
			System.err.println(e.getMessage());;
			return -1;

		}
		
	
		return 0;
		
	}
	
	
	
	private ArrayList<String> getTokensList(String input, String delim) {
		
		StringTokenizer tokenizer = new StringTokenizer(input,delim);
		ArrayList<String> list = new ArrayList<String>();
	
		while (tokenizer.hasMoreTokens()) {
			
			String s = tokenizer.nextToken();
			list.add(s.trim());
			
		}

		return list;
	}
	
	
	/**
	 * 
	 * @param tMap a {@link java.util.TreeMap} holding objects of {@link dataModel} classes.
	 * @param key a string to search for.
	 * @return true if the given key exists in TreeMap. 
	 */
	protected boolean existsInMap(TreeMap<String,?> tMap,String key){
		
			
			if ((tMap.containsKey(key))) {
				
				return true;
				
			}
			
		
		return false;
		
	}

}