package databasemanager.function;

import java.util.HashMap;
import databasemanager.types.CommandList;
import databasemanager.types.QueryAssistance;
import databasemanager.ui.Application;
import databasemanager.ui.Function;

public class GuidedQuery extends Function {
	private HashMap<String, CommandList> availableCommands = new HashMap<>();
	
	private String open 		= "open";
	private String data 		= "data";
	private String from 		= "from";
	private String object 		= "object";
	private String where 		= "where";
	private String leftCond 	= "left conditional";
	private String cond 		= "Conditional";
	private String rightCond 	= "right conditional";
	
	private String queryString = "";

	private QueryAssistance selectedAssistance;

	public GuidedQuery(String displayName, Application Owner) {
		super(displayName, Owner);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void Execute() {
		String[] commands = new String[]{"SELECT", "FROM", "WHERE"};
		CommandList openCList = new CommandList(commands, open);
		availableCommands.put(open, openCList);
		
		commands = new String[]{"id", "firstName", "lastName", "phone", "email"};
		CommandList dataCList = new CommandList(commands, data);
		availableCommands.put(data, dataCList);
		
		commands = new String[]{"FROM"};
		CommandList fromCList = new CommandList(commands, from);
		availableCommands.put(from, fromCList);
		
		commands = new String[]{"Account", "Contact", "Asset", "Case"};
		CommandList objectCList = new CommandList(commands, object);
		availableCommands.put(object, objectCList);
		
		commands = new String[]{"WHERE"};
		CommandList whereCList = new CommandList(commands, where);
		availableCommands.put(where, whereCList);
		
		commands = new String[]{"** The left conditional can be anything you wish to compare to another conditional **", "", "", 
								"** It is optional **", "", "",
								"** Leave empty if you do not plan on entering the other parts of the conditional statement **"};
		
		CommandList leftCondCList = new CommandList(commands, leftCond);
		availableCommands.put(leftCond, leftCondCList);
		
		commands = new String[]{"=", "!=", ">", "<", "<=", ">=", "!"};
		CommandList condCList = new CommandList(commands, cond);
		availableCommands.put(cond, condCList);
		
		commands = new String[]{"** The right conditional can be anything you wish to compare to another conditional **", "", "", 
								"** It is optional **", "", "",
								"** If you haven't entered the left conditional and comparator, leave empty **"};
		CommandList rightCondList = new CommandList(commands, rightCond);
		availableCommands.put(rightCond, rightCondList);
		
		Interact();
	}

	@Override
	public void Interact() {
		
		if(selectedAssistance == QueryAssistance.Guided){
			QueryWithGuidance();
		}
		else{
			QueryWithGuidance();
		}
	}

	@Override
	public void ConfirmFinish(String promptText) {
		// TODO Auto-generated method stub
		
	}
	
	private void QueryWithGuidance(){
		CommandGuidance(open);
		CommandGuidance(data);
		CommandGuidance(from);
		CommandGuidance(object);
		CommandGuidance(where);
		CommandGuidance(leftCond);
		CommandGuidance(cond);
		CommandGuidance(rightCond);
	}
	
	private void CommandGuidance(String type){
		switch (type) {
		case "open":
			open = availableCommands.get(type).EnterPrompt();
			AssembleQuery(open);
			break;
		
		case "data":
			data = availableCommands.get(type).EnterPrompt();
			AssembleQuery(data);
			break;
		
		case "from":
			from = availableCommands.get(type).EnterPrompt();
			break;
			
		case "object":
			object = availableCommands.get(type).EnterPrompt();
			break;
			
		case "where":
			where = availableCommands.get(type).EnterPrompt();
			AssembleQuery(", ");
			AssembleQuery(where);
			break;
		
		case "leftCond":
			leftCond = availableCommands.get(type).EnterPrompt();
			if(leftCond.compareTo("empty") != 0){
				AssembleQuery(leftCond);
			}
			break;
			
		case "cond":
			cond = availableCommands.get(cond).EnterPrompt();
			if(cond.compareTo("empty") != 0){
				AssembleQuery(cond);
			}
			break;
			
		case "rightCond":
			if(rightCond.compareTo("empty") != 0){
				AssembleQuery(rightCond);
			}
			break;

		default:
			break;
		}
	}

	private void AssembleQuery(String addition){
		queryString += " ";
		queryString += addition;
	}
}
