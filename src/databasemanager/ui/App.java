package databasemanager.ui;

import java.util.HashMap;
import java.util.Map;
import databasemanager.types.DataObject;

public abstract class App {
	protected String displayName;
	protected HashMap<Integer, Function> functions = new HashMap<>();
	protected Map<String, DataObject> dataObjects = new HashMap<>();
	protected Menu menu = null;
	protected String header = null;
	
	abstract public void Init();
	abstract public void Run();
	abstract public void Wrap();
	abstract public void Exit();
	
	
	//Private classes used behind the scenes by application to handle the UI and utility issues
	private void PrintHeader(String title, String version){
		if(header != null){
			System.out.printf("%20s ##############################", " ");
			System.out.printf("%20s %10s %10s", "#", title, "#");
			System.out.printf("%20s %10s %10s", "#", version, "#");
			System.out.printf("%20s");
		}
	}
	
	private void PrintMenu(){
		
	}
	
	private void AddFunctions(){
		
	}
	
	private void InitMenu(){
		
	}
	
	private void Prompt(String detail){
		
	}
}
