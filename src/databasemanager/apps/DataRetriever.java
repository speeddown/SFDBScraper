package databasemanager.apps;

import java.util.HashMap;

import com.sforce.soap.enterprise.QueryResult;
import databasemanager.Main;
import databasemanager.function.FreeQuery;
import databasemanager.function.GuidedQuery;
import databasemanager.types.*;
import databasemanager.ui.*;

public class DataRetriever extends Application {
	public HashMap<String, CommandList> availableCommands = new HashMap<>();
	private QueryAssistance selectedAssistance;
	private QueryResult queryResult = null;
	
	//Constructor Methods
	public DataRetriever(String displayName, Application owner) {
		super();
	}
	
	
	@Override
	public void Run(){
		AddFunctions();
		PrintApplicationOpen();
		InitMenu(this, "Data Retriever");
		
		while(Application.leaveApp != true){
			menu.ShowMenu();
			PromptExit();
		}
		
		Exit();
	}
	
	@Override
	public void Exit() {
		System.out.println("\nReturning to manager menu...");
	}
	

	@Override
	public void PrintApplicationOpen(){
		System.out.println("\n\nData Retriever v 1.0");
	}
	
	@Override
	public void AddFunctions(){
            AddFunction(new Integer(1), new FreeQuery("Query", this));
            AddFunction(new Integer(2), new GuidedQuery("Assisted Query", this));
	}
	
	
	
	//Unique Methods
	public void StoreQueryResults(){
		System.out.print("Enter a tag for the data object: ");
		String tag = Main.GetInput();
		Main.SkipLine();
		System.out.print("Enter a name for the data object: ");
		String name = Main.GetInput();
		Main.SkipLine();
		AddDataObject(queryResult, name, tag);
	}
	
	public void ChooseSystem(){
		System.out.print("\nChoose the assistance type you'd prefer[Guided, Free]: ");
		boolean isGoodChoice = false;
		
		while(isGoodChoice == false){
			String input = Main.GetInput();
			isGoodChoice = ValidateAssistanceChoice(input);
			if(isGoodChoice == false){
				System.out.print("Choose again[Guidance, Free]: ");
			}
		}
	}
	
	private boolean ValidateAssistanceChoice(String input){
		if(input.compareTo("Guided") < 0 && input.compareTo("Free") < 0){
			return false;
		}
		else if(input.toLowerCase().compareTo("guided") == 0){
			selectedAssistance = QueryAssistance.Guided;
			return true;
		}
		else if(input.toLowerCase().compareTo("free") == 0){
			selectedAssistance = QueryAssistance.Free;
			return true;
		}
		else{
			return false;
		}
	}


	
}
