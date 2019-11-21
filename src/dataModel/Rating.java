package dataModel;

import java.util.ArrayList;

public class Rating extends TagsRatings {
	
	private String rating;
	
	public Rating() {super("");}
	
	public Rating(String ratingValue,String uId) {
		
		super(uId);
		rating = ratingValue;
		
	 
		
	}
	
	


	@Override
	public ArrayList<String> getShortDescription(String movieId) {
		
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
			
			desc.add("Rating value > "+ rating);
		}
			
		
		return desc;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setValue(String v) {
		rating = v;
		
	}

}
