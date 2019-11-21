package mainApplication;


import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import applicationManager.IMainApplication;

import applicationManager.MainManagerFactory;


/**
 * 
 * @since 2017-11-05
 * @version 1.0
 *
 */

public class MainApplication {
	
	private Scanner reader;
	
	@SuppressWarnings("resource")
	public MainApplication() {
		
		reader = new Scanner(System.in).useDelimiter(System.lineSeparator()); 
		
	}
	
	
	private void closeScanner() {this.reader.close();}
	
	
	//Main menu
	public int printMenu() {
		
		int answer = 0;
		
		while (answer > 4 || answer<=0) {
			
			
			System.out.println("Choose(1-4)\n 1. Load all data\n 2. Load tags/ratings\n 3. Submit a query\n "
					+ "4. Exit\n");
			
			answer = reader.nextInt();
			
			if (answer > 4 || answer <= 0)
				System.out.println("Invalid input. Try again...");
		}
		
		return answer;
		
	}
	
	
	//Menu for loading tags - ratings
	public int printTagRatingMenu() {
		
		int answer = 0;
		
		while (answer > 4 || answer<=0) {
			
			System.out.println("Choose(1-4)\n 1. Load only tags\n 2. Load only ratings\n 3. Load tags and ratings\n "
					+ "4. Exit\n");
			
			answer = reader.nextInt();
			
			if (answer > 4 || answer <= 0)
				System.out.println("Invalid input. Try again...");
		}
		
		return answer;
		
	}
	
	
	
	//Search menu
	public int printSearchMenu(){
		
		int answer = 0;
		
		while (answer > 4 || answer <=0) {
			
			System.out.println("Choose(1-4)\n 1. Search movie description\n 2. Search movies by criteria\n "
					+ "3. Search tags/ratings\n 4. Exit\n");

			answer = reader.nextInt();

			if (answer > 4 || answer <= 0)
				System.out.println("Invalid input. Try again...");
			
		}
		
		return answer;
	}
	
	
	
	
	public int printSearchSubMenu(int type){
		
		int answer = 0;
		
		
			
			if (type == 1) {
				
				while (answer > 3 || answer <=0) {
					
					System.out.println("Choose(1-3)\n 1. Search by movie id\n 2. Search by movie title \n 3. Exit\n");
					answer = reader.nextInt();
					if (answer == 3)
						return -1;
					if (answer > 3 || answer <= 0)
						System.out.println("Invalid input. Try again...");
					
				}
				
				return answer;
			
			}else if (type == 2) {
			
				while (answer > 5 || answer<=0) {
					
					System.out.println("Choose(1-5)\n Search movies by:\n 1. Genre\n 2. Country\n 3. Actor\n 4. Director\n 5. Exit\n");
					answer = reader.nextInt();
					if (answer == 5)
						return -1;
					if (answer > 5 || answer<=0)
						System.out.println("Invalid input. Try again...");
					
				}
				return answer;
			
			}else if (type == 3) {
				
				while (answer > 5 || answer<=0) {
					
					System.out.println("Choose(1-4)\n 1. Search tags by movie id \n 2. Search tags by movie title\n"
							+ " 3. Search ratings by movie id\n 4. Search ratings by movie title\n 5. Exit");
					answer = reader.nextInt();
					if (answer == 5)
						return -1;
					if (answer > 5 || answer<=0)
						System.out.println("Invalid input. Try again...");
					
				}
				return answer;
			
			}else
				throw new IllegalArgumentException();
		
		
	}
	
	
	//File creation menu
	public int printFileCreationMenu() {
		
		
		int answer = 0;
		
		while (answer > 5 || answer <=0) {
			
			System.out.println("Choose(1-4) a file type to save search results:\n 1. Txt file\n 2. Html file\n "
					+ "3. Markdown file\n 4. Pdf file\n 5. Exit\n");

			answer = reader.nextInt();

			if (answer > 5 || answer <= 0)
				System.out.println("Invalid input. Try again...");
			
		}
		
		return answer;
		
		
	}
	
	
	//File conversion menu
	public char printFileConvertionMenu() {
		
		
		char answer = 'a';
		
		while (answer != 'y' && answer != 'n') {
			
			System.out.println("Choose(y/n)\n Convert txt file to pdf?\n ");

			answer = reader.next().charAt(0);

			if (answer != 'y' && answer != 'n')
				System.out.println("Invalid input. Try again...");
			
		}
		
		return answer;
		
		
	}
	
	
	
	public String getInputData(int operationType) {
		
		
		String answer = new String();
		if (operationType == 3)
			System.out.println("Insert search data:\n");
		else if (operationType == 4)
			System.out.println("Insert file name:\n");
		else if (operationType == 5)
			System.out.println("Insert the name of the txt file:\n");
		else if (operationType == 6)
			System.out.println("Choose a name for the pdf file:\n");
		
		answer = reader.next();
	
		return answer;
		
	}
	
	

	public static void main(String[] args) throws IOException {
		
		final MainApplication app = new MainApplication();
		final MainManagerFactory managerFactory = new MainManagerFactory();
		boolean moviesLoaded = false;
		@SuppressWarnings("unused")
		boolean tagsLoaded = false;
		boolean ratingsLoaded = false;
		long startTime = System.currentTimeMillis();
		long endTime;
		IMainApplication manager = managerFactory.createMainManager();
		
		
		
		while (true) {
			
			int operation = app.printMenu();
			
			//Load all data
			if (operation == 1) {
				
				int temp = manager.loadAllData();
				if (temp > 0)
					moviesLoaded = true;
				
			
			}else if (operation != 1 && operation != 4) {
				
				if (moviesLoaded) {
					
					//Load tags-ratings
					if (operation == 2) {
						
						int option = app.printTagRatingMenu();
						while (option < 4) {
							int temp = manager.loadTagRatingsData(option);
							
							
							if (temp > 0) {
								
								if (option == 1)
									tagsLoaded = true;
								else if (option == 2)
									ratingsLoaded = true;
								else if (option == 3) {
									tagsLoaded = true;
									ratingsLoaded = true;
								}
									
							}
							
							option = app.printTagRatingMenu();
						}
						
					}
					
					//Search options
					else if (operation == 3) {
						
						int qOperation = app.printSearchMenu();
						
						while(qOperation < 4) {
						
						int criterionOption = app.printSearchSubMenu(qOperation);
						
						if (criterionOption != -1) {
							
							boolean abortSearch = false;
							
							//Search for tags\ratings
							if (qOperation == 3) {
								
								if (criterionOption == 1 || criterionOption == 2) {
									if (!tagsLoaded) {
										
										System.err.println("Tags were not loaded.\n");
										abortSearch =  true;	
									}
								}else if (criterionOption == 3 || criterionOption == 4) {
									if (!ratingsLoaded) {
										
										System.err.println("Ratings were not loaded.\n");
										abortSearch =  true;
									}
									
									
								}
								
							}
							
							if (!abortSearch) {
							
								String inputData = app.getInputData(operation);
								int searchStatus = manager.submitQuery(qOperation,criterionOption,inputData);

								
								if (searchStatus>0) {
								
									//File creation/conversion options
									int fOperation = app.printFileCreationMenu();

									while(fOperation < 5) {

										String inData = app.getInputData(4);
										while (inData.isEmpty()) {
											
											System.out.print("Invalid input!\nChoose a name for the file.\n");
											inData = app.getInputData(4);
											
										}
										//int temp = manager.createFile(fOperation,inData);
										char ans = 'a';
										//if (fOperation == 1 && temp == 0)
											//ans = app.printFileConvertionMenu();

										if (ans == 'y') {

											String txtName = app.getInputData(5);
											String pdfName = app.getInputData(6);


											if (pdfName.isEmpty())
												pdfName = txtName;

//											try {
//												//manager.convertFile(txtName, pdfName);
//											} catch (IOException e) {
//
//												e.printStackTrace();
//											}

										}

										fOperation = app.printFileCreationMenu();

									}
								}else if (searchStatus == 0)
									System.out.println("No results found. Try again...");
							
							}
						}
						
						qOperation = app.printSearchMenu();
						
						}
			
				
						
					}
					
				}else {
					
					System.err.println("Error!No movies loaded. Choose option 1.\n");
					
					continue;
				}
			}
				
			else
				break;
			
		}
		
		endTime = System.currentTimeMillis();
		long elapsedTime = endTime - startTime;
		long sec = TimeUnit.MILLISECONDS.toSeconds(elapsedTime);
		if (elapsedTime > 60000) {
			
			long min = TimeUnit.MILLISECONDS.toMinutes(elapsedTime);
			sec = sec - TimeUnit.MINUTES.toSeconds(min);
			System.out.println("Connected to app for: "+min+"min "+sec+"s");
			
		}else
			System.out.println("Connected to app for: "+sec+"s");
		
		app.closeScanner();

	}

}