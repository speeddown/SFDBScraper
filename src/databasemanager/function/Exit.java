package databasemanager.function;
import databasemanager.ui.Application;
import databasemanager.ui.Function;

public class Exit extends Function {
	
	public Exit(String displayName, Application owner){
		super(displayName, owner);
		
	}

	@Override
	public void Interact() {
		owner.Exit();
	}

	@Override
	public void ConfirmFinish(String promptText) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Execute() {
		Interact();
	}
}
