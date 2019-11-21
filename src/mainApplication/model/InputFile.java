package mainApplication.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class InputFile {
	
	private StringProperty fileName;
	private StringProperty recordNum;
	
	
	public String getFileName() {
		


		
		return fileName.get();
		
	
	
	}
	
	
	public void setFileName(String value) {
	
		Pattern p = Pattern.compile("\\.dat");
		Matcher m = p.matcher(value);
		
		String token1;
		
		if (m.find()) {
			token1 = value.split("\\.")[0];
			String token2 = token1.contains("_") ? token1.split("\\_")[1] : token1 ;
			String token3 = token2.contains("/") ? token2.split("/")[1] : token2;
			fileNameProperty().set(token3.substring(0,1).toUpperCase()+token3.substring(1));
		}else
			fileNameProperty().set(value);
	
	
	}
	
	public StringProperty fileNameProperty() {
		

	
		
		if (fileName == null)
			fileName = new SimpleStringProperty(this,"Undefined");

		return  fileName;
		
	}
	
	
	
	public String getRecordNum() {return recordNum.get();}
	
	public void setRecordNum(String value) {recordNumProperty().set(value);}
	
	public StringProperty recordNumProperty() {
		
		if (recordNum == null)
			recordNum = new SimpleStringProperty(this,"Undefined");
		return  recordNum;
		
	}
	

}
