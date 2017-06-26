package databasemanager.ui;

public interface IMenu {
	String title = "";
	String SelectionFormatMessage = "";
	String OutOfBoundsMessage = "Not a menu option, try again.";
	String SelectPrompt = "Choose Option[1 - n]";
	
	
	void ShowMenu();
	void GetChoice();

	void ShowSelectPrompt();
	void ShowFunctions();
	void ShowTitle();
	void ShowOutOfBoundsWarning();
	void ShowFormatWarning();
	
	String GetInput();
	void ConvertInput(String input) throws Exception;
	boolean CheckChoiceBound();
}
