package databasemanager.function;

import databasemanager.ui.Application;
import databasemanager.ui.Function;

public class ApplicationLoader extends Function{
	protected Application linkedApp = null;

	
	public ApplicationLoader(String displayName, Application owner, Application linkedApp){
		super(displayName, owner);
		SetLinkedApp(linkedApp);
	}
	
	@Override
	public void Execute(){
		Interact();
	}
	
	@Override
	public void Interact(){
		linkedApp.Run();
	}

	@Override
	public void ConfirmFinish(String promptText) {
		
	}
	
	
	//Unique Methods
	public void SetLinkedApp(Application app){
		this.linkedApp = app;
	}
}
