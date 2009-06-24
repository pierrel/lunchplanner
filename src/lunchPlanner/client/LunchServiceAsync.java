package lunchPlanner.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LunchServiceAsync {

	void ProcessRequest(ArrayList listOfRequests, AsyncCallback async);
	  
	  void VoteRequest(ArrayList listOfRequests, AsyncCallback async);
	  
	  void getRestaurants(AsyncCallback async);
	  
	  //void setRestaurants(ArrayList listOfRequests, AsyncCallback async);
}
