package lunchPlanner.client;

import java.util.HashMap;

import com.google.gwt.user.client.rpc.IsSerializable;

public class LunchRequest implements IsSerializable{
	
	/*
	 * HashMap that will always contain strings for both keys and values
	 * @gwt.typeArgs <java.lang.String, java.lang.String>
	 */
	private HashMap lunchRequest;
	
	public LunchRequest(){
		lunchRequest = new HashMap();
	}
	
	public String getValue(String key){
		return (String)lunchRequest.get(key);
	}
	
	public void setValue(String key, String value){
		lunchRequest.put(key, value);
	}
}
