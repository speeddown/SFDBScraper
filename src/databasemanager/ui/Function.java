package databasemanager.ui;

public abstract class Function implements IExecutable, INameable, IInteractable, IEndable{
	protected String displayName = "Not set";
	protected Application owner = null;
	
	abstract public void Execute();
	abstract public void Interact();
	abstract public void ConfirmFinish(String promptText);
	
	//Function Constructor
	public Function(String displayName, Application Owner) {
		SetDisplayName(displayName);
		SetOwner(Owner);
	}
	
	//Interface Methods::Override in concrete implementations for specific behavior
	
	@Override
	public void End(String decision){
		switch (decision) {
		case "y":
			owner.Run();
			break;
		case "n":
			Execute();
			break;
			
		default:
			break;
		}
	}
	
	@Override
	public void ConfirmFinish(){
		boolean inputOkay = false;
		String input = "y";
		
		while(inputOkay == false){
			System.out.println("Finished with " + displayName + " session?");
			System.out.print("    Choice[y, n]: ");
			
			
			inputOkay = isInputOkay(input);
		}
		
		End(input);
	}
	
	
	//Function Methods
	public boolean isInputOkay(String input){
		if(input.compareTo("y") < 0 && input.compareTo("n") < 0){
			return false;
		}
		else{
			return true;
		}
	}
	
	public String GetDisplayName(){
		return displayName;
	}
	
	public void SetDisplayName(String name){
		displayName = name;
	}

	public void SetOwner(Application app){
		this.owner = app;
	}
}
