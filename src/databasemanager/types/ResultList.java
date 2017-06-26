package databasemanager.types;

import java.util.ArrayList;

public class ResultList {
	ArrayList<String> results = new ArrayList<String>();
	
	public ResultList(ArrayList<String> query){
		for(String data : query){
			results.add(data);
		}
	}
	
	private void Print(){
		int index = 0;
		for(String result : results){
			System.out.printf("%10s %s", " ", result);
		}
	};
}
