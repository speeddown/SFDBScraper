package databasemanager.types;

public class DataObject {
	private String tag = "Undefined";
	private String Name = "None";
	private Object data = null;
	
	public DataObject(String name, String tag, Object data){
		this.Name = name;
		this.tag = tag;
		this.data = data;
	}
}
