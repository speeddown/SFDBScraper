package databasemanager.ui;

import databasemanager.Main;

public class Menu implements IMenu{
	String title = "Menu Title(Not set)";
	Application app = null;
	
	static Integer userChoice = 0;
	
	public Menu(Application app, String title){
		this.app = app;
		this.title = title;
	}

	//Shows the menu
	@Override
	public void ShowMenu() {
		ShowTitle();
		GetChoice();
	}

	@Override
	public void GetChoice() {
		Main.SkipLine();
		
		boolean inputOkay = false;
		
		while(inputOkay != true){
			
			ShowFunctions();
			ShowSelectPrompt();
			
			String input = GetInput();
			
			try {
				ConvertInput(input);
			} catch (Exception e) {
				ShowFormatWarning();
				continue;
			}
			inputOkay = CheckChoiceBound();
		}
		app.functions.get(userChoice).Execute();
	}
	
	@Override
	public String GetInput(){
		return Main.GetInput();
	}
	
	@Override
	public void ConvertInput(String input) throws Exception{
		userChoice = Integer.valueOf(input);
	}
	
	@Override
	public boolean CheckChoiceBound(){
		if(userChoice.intValue() > 0 && userChoice.intValue() <= app.functions.values().size()){
			return true;
		} else {
			ShowOutOfBoundsWarning();
			return false;
		}
	}
	
	@Override
	public void ShowFunctions(){
		int count = 1;
		
		for(Function function : app.functions.values()){
			System.out.println("  " + count + " - " + function.GetDisplayName());
			count++;
		}
	}
	
	@Override
	public void ShowOutOfBoundsWarning(){
		System.out.println("\n" + IMenu.OutOfBoundsMessage + "\n");
	}
	
	@Override
	public void ShowFormatWarning(){
		System.out.println(IMenu.SelectionFormatMessage);
	}
	
	@Override
	public void ShowSelectPrompt(){
		System.out.print("\n" + IMenu.SelectPrompt + ": ");
	}
	
	@Override
	public void ShowTitle(){
		System.out.println("\n\n\n" + title);
	}
}
