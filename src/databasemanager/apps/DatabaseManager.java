package databasemanager.apps;

import databasemanager.function.ApplicationLoader;
import databasemanager.function.Exit;
import databasemanager.ui.Application;

public class DatabaseManager extends Application{
	
	@Override
	public void Run(){
		AddFunctions();
		PrintApplicationOpen();
		InitMenu(this, "Database Manager");
		
		while(!leaveApp){
			menu.ShowMenu();
			PromptExit();
		}
	}

	@Override
	public void AddFunctions(){
		AddFunction(new Integer(1), 	new ApplicationLoader("Query", 	this, 	new DataRetriever("Data Retriever", this)));
		AddFunction(new Integer(2), 	new Exit("Exit", this));
	}
	
	@Override
	public void Exit(){
		System.out.println("\nReturning to main menu\n");
	}
	
	@Override
	public void PrintApplicationOpen(){
		System.out.println("\n\nDatabase Manager v 1.0");
	}
}
