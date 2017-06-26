package databasemanager.ui;

public interface IEndable {
	void End(String decision);
	void ConfirmFinish();
	void ConfirmFinish(String promptText);
}
