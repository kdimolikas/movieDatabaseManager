package dataLoad;

import java.util.ArrayList;
import java.util.TreeMap;

import dataModel.Person;

public class PersonLoader extends AbstractRecordLoader<Person> {
	
	@Override
	protected int createObject(ArrayList<String> tokens, TreeMap<String,Person> personMap) {
		
		
		String id;
		String name;
		String movieId;
		
		movieId = tokens.get(0);
		id = tokens.get(1);
		name = tokens.get(2);
		
		
		boolean isKey = existsInMap(personMap,id);  //check if arg2 exists in arg1
		Person p;
		
		p = !isKey ?  new Person(id,name) : personMap.get(id);
		p.addMovieId(movieId);
		
		
		personMap.put(id, p);
		
		return 0;
	}
	
	

}