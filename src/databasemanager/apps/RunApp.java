package databasemanager.apps;

import databasemanager.function.ApplicationLoader;
import databasemanager.ui.Application;
import databasemanager.function.Exit;

public class RunApp extends Application{
	
	@Override
	public void Run() {
		AddFunctions();
		PrintApplicationOpen();
		InitMenu(this, "Main Menu");
		
		
		while(leaveApp != true){
			ShowMenu();
			PromptExit();
		}
		
		Exit();
	}
	
	@Override
	public void Exit(){
		System.out.println("\n\nLogging out of salesforce API and exiting application...");
	}
	
	@Override
	public void AddFunctions(){
		AddFunction(new Integer(1), 	new ApplicationLoader("Database Manager", this, new DatabaseManager()));
		AddFunction(new Integer(2), 	new Exit("Exit", this));
	}
	
	@Override
	public void PrintApplicationOpen(){
		System.out.println("\n\nLogoJET Database Manager v1.0");
	}
}
