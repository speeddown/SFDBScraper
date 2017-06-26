package databasemanager.ui;

import databasemanager.Main;
import java.util.HashMap;
import databasemanager.types.DataObject;

public abstract class Application implements IExitable, IRunnable{
	protected HashMap<Integer, Function> functions = new HashMap<>();
	protected Menu menu = null;
	protected HashMap<String, DataObject> DataObjects = new HashMap<>();
	protected static boolean leaveApp = false;
	protected static Application returnApp = null;
	
	public abstract void Run();
	public abstract void AddFunctions();
	public abstract void PrintApplicationOpen();
	public abstract void Exit();
	
	public void InitMenu(Application app, String menuTitle){
		menu = new Menu(app, menuTitle);
	}
	
	public void ShowMenu(){
		menu.ShowMenu();
	}
	
	public void AddFunction(Integer index, Function function){
		functions.put(index, function);
	}
	
	@Override
	public Application GetReturnApp(){
		return returnApp;
	}
	
	@Override
	public void SetReturnApp(Application app){
		returnApp = app;
	}
	
	public void PromptExit(){
		System.out.print("Do you really want to exit application?[y, n]: " );
		String answer = Main.GetInput();
		boolean goodInput = false;
		
		while(!goodInput){
			if(answer.compareTo("y") == 0){
				goodInput = true;
				leaveApp = true;
				break;
			}
			else if(answer.compareTo("n") == 0){
				goodInput = true;
				break;
			}
			else{
				System.out.print("Invalid input, try again[y, n]: ");
				answer = Main.GetInput();
			}
		}
		
	}
	
	public void AddDataObject(Object data, String tag, String name){
		if(DataObjects.values().contains(data) == true){
			System.out.println("\nThis object is already stored");
			return;
		}
		DataObject newObject = new DataObject(name, tag, data);
		DataObjects.put(tag, newObject);
	}
	
	public DataObject GetDataObject(String tag){
		return DataObjects.get(tag);
	}
}
