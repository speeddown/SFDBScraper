package databasemanager.function;

import java.util.ArrayList;

import com.sforce.soap.enterprise.QueryResult;
import com.sforce.soap.enterprise.sobject.Account;
import com.sforce.soap.enterprise.sobject.SObject;
import com.sforce.ws.ConnectionException;
import databasemanager.Main;

import databasemanager.ui.Application;
import databasemanager.ui.Function;

public class FreeQuery extends Function {
	QueryResult queryResult = null;
	String queryString = null;

	public FreeQuery(String displayName, Application Owner) {
		super(displayName, Owner);
	}

	@Override
	public void Execute() {
		Interact();
	}

	@Override
	public void Interact() {
		QueryPrompt();
	}

	@Override
	public void ConfirmFinish(String promptText) {
		
	}
	
	//Unique Function Methods
	
	private void SaveData(){
		System.out.print("Enter name for data: ");
		String name = Main.GetInput();
		System.out.print("Enter name for tag: ");
		String tag = Main.GetInput();
		
		owner.AddDataObject(queryResult, tag, name);
	}
	
	private void QueryPrompt(){
		System.out.printf("\n\n%30s %10s %5s", "!", "Caution", "!");
		System.out.println("\nYou have chosen to enter you query unguided, if the query fails you will be brought back to the "
						   + "Database Manager main menu.");
		System.out.print("\nEnter query string: ");
		queryString = Main.GetInput();
		
		PerformQuery(queryString);
	}
	
	private void PerformQuery(String queryText){
		try {
			queryResult = Main.connection.query(queryText);
			PromptPrint();
		} catch (ConnectionException e) {
			System.out.println("\nQuery failed due to improperly formated string.");
		}
	}
	
	private void PromptPrint(){
		if(queryResult != null){
			System.out.printf("\n %30s Query Results", " ");
			System.out.println("\nFound " + queryResult.getSize() + " results matching query");
			
			System.out.print("Would you like to print the results?[y, n]: ");
			String view = Main.GetInput();
			boolean decided = false;
			
			while(decided == false){
				if(view.compareTo("y") == 0){
					decided = true;
					try{
						PrintResult();
					} catch (ConnectionException ce){
						System.out.println("\nConnection error.");
					}
				}
				else if (view.compareTo("n") == 0){
					decided = true;
				}
				else{
					System.out.println("\nInvalid input...");
					System.out.print("\n\nWould you like to print the result[y, n]: ");
					view = Main.GetInput();
				}
			}
			
			PromptSave();
		}
	}
	
	private void PrintResult() throws ConnectionException{
		boolean finished = false;
		
		while(!finished){
			ArrayList<Object> newList = new ArrayList<>();
			SObject[] records = queryResult.getRecords();
			for(int i = 0; i < records.length; i++){
				Account account = (Account) records[i];
				String name = account.getName();
				
				if(name != null){
					Main.SkipLine();
					System.out.printf("%10s -- %10s", i, name);
				}
			}
			
			if(queryResult.isDone()){
				finished = true;
			} else {
				queryResult = Main.connection.queryMore(queryResult.getQueryLocator());
			}
		}
	}
	
	public void PromptSave(){
		String input = "";
		boolean isSerious = false;
		System.out.print("\nData must be saved as a data object before it can be used in manipulations."
					   + "\n	Would you like to save your query results?[y, n]: ");
		input = Main.GetInput();
		
		while(!isSerious){
			if(input.compareTo("y") == 0){
				Main.SkipLine();
				System.out.print("Enter a tag for the data object: ");
				String tag = Main.GetInput();
				System.out.print("Enter a name for the data object: ");
				String name = Main.GetInput();
				owner.AddDataObject(queryResult, tag, name);
				
				isSerious = true;
			} 
			else if (input.compareTo("n") == 0){
				String confirm = "";
				System.out.print("Dispose of data and return to Query menu?[y, n]");
				confirm = Main.GetInput();
				if(confirm.compareTo("y") == 0){
					System.out.println("\nData cleared. Returning to Query menu.");
					isSerious = true;
				} else {
					isSerious = false;
				}
			}
			else{
				System.out.println("Invalid input...\n");
			}
		}
	}

}
