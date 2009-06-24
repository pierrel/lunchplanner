package lunchPlanner.client;

import java.util.HashMap;

public class Restaurant {
	String name;
	int voteCount; 
	HashMap<String, String> attributes;
	
	public Restaurant(){
		name = "";
		voteCount = 0;
		attributes = new HashMap();
	}

	public Restaurant(String name){
		this.name = name;
		voteCount = 0;
		attributes = new HashMap();
	}
	
	
	public Restaurant(String name, int vote) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.voteCount = vote;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}



}
