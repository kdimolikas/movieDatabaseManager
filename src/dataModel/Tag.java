package dataModel;

import java.util.ArrayList;


public class Tag extends TagsRatings {
	
	private String id;
	private String value;

	public Tag() {super("");}
	
	public Tag(String anId, String uId) {
		
		super(uId);
		id = anId;
		value = "";
		
	}
	
	public void setValue(String v) {value =v;}
	
	public String getId() {return id;} 

	public String getTagValue() {return value;} 
	
	
	@Override
	public ArrayList<String> getShortDescription(String movieId){
		
		
		ArrayList<String> desc = new ArrayList<String>();
		
		ArrayList<String> users = new ArrayList<String>();
		users = super.getUsersOfAMovie(movieId);
		
		for (String u:users) {
			
			desc.add("User > "+u);
			try {
				
				
				desc.add("Date > "+ super.getDateOfSubmission(movieId, u));
				
			}catch(Exception e) {
				e.printStackTrace();
				
			
			}
			
			desc.add("Tag value > "+ value);
		}
			
		
		return desc;
		
		
	}



}
