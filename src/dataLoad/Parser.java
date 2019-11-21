package dataLoad;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Parser {
	
	
	private ArrayList<String> contents;
	private String filePath;
	
	public Parser(String fPath) {
		
	
		contents = new ArrayList<String>();
		filePath = fPath;

			
	}
	

	
	public void addString(String s){
		
		contents.add(s);
				
	}
	
	
	public ArrayList<String> getContents(){	return contents;}
	
	
	
	public ArrayList<String> loadRecords() throws IOException  {
		
		
		FileInputStream fin = null;
		
		try {
			 fin = new FileInputStream(new File(filePath));						
		
			 Scanner scan = new Scanner(fin,"UTF-8");

			 while (scan.hasNextLine())
				 this.addString(scan.nextLine());

			 scan.close();
			 
		}catch (FileNotFoundException e) {
			
			
			System.err.println(e.getMessage());
				
		
		}finally {
			
			if (fin != null) {
				
				System.out.println("Closing input stream...");
				fin.close();
				
			}else {
				
				System.err.println("Input stream not open.");
				throw new FileNotFoundException();
				
			}
			
		}
		
		showDetails();
		return contents;
	}
	
	
	private void showDetails() {
		
		if (!contents.isEmpty()) {
		
			System.out.println(String.valueOf(getNumOfRecordsLoaded())+" records have been processed from "+filePath+".");
			
			
		}else
			
			System.out.println("The input file is empty!");
		
	}
	
	private int getNumOfRecordsLoaded() {return contents.size();}
	


}
