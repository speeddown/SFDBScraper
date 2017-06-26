package databasemanager.types;

import databasemanager.Main;
import java.util.ArrayList;

import databasemanager.ui.IPrintable;

public class CommandList implements IPrintable{
	ArrayList<String> commands = new ArrayList<>();
	String enterPrompt = "Enter ";
	String type = "undefined";
	
	public CommandList(){ }
	
	public CommandList(String[] commands, String type){
		for(String command : commands){
			this.commands.add(command);
		}
		this.type = type;
	}
	
	public void SetEnterPrompt(String prompt){
		enterPrompt = prompt;
	}
	
	public String EnterPrompt(){
		boolean finished = false;
		String text = "";
		
		while(!finished){
			System.out.print("\n" + enterPrompt + " " +  type + ": ");
			String input = Main.GetInput();
			if(commands.contains(input)){
				text = input;
				finished = true;
			}
			else if(input.compareTo("help") == 0){
				Print();
			}
		}
		
		return text;
	}
	
	public void AddCommand(String command){
		commands.add(command);
	}
	
	public void Append(ArrayList<String> commandList){
		commands.addAll(commandList);
	}
	
	@Override
	public void Print() {
		int columnCount = 0;
		int columnLimit = 3;
		
		for(int i = 0; i < commands.size(); i++){
			if(columnCount == columnLimit){
				Main.SkipLine();
			}
			
			System.out.printf("%1$-10s", commands.get(i));
			columnCount++;
		}
	}
}
