package lunchPlanner.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("lunch")
public interface LunchService extends RemoteService {
	/**
	 * Method used to echo a list of contacts. This method serves no real
	 * purpose other than to demonstrate how to annotate a service method.
	 * 
	 * @param listOfRequests
	 *            contact list to echo
	 * @return echoed contact list
	 * 
	 * @gwt.typeArgs listOfRequests <lunchPlanner.client.LunchRequest>
	 * @gwt.typeArgs <lunchPlanner.client.LunchRequest>
	 */
	public ArrayList ProcessRequest(ArrayList listOfRequests);

	/**
	 * Method used to echo a list of contacts. This method serves no real
	 * purpose other than to demonstrate how to annotate a service method.
	 * 
	 * @param listOfRequests
	 *            contact list to echo
	 * @return echoed contact list
	 * 
	 * @gwt.typeArgs listOfRequests <lunchPlanner.client.LunchRequest>
	 * @gwt.typeArgs <lunchPlanner.client.LunchRequest>
	 */
	public ArrayList VoteRequest(ArrayList listOfRequests);

	/**
	 * Method used to echo a list of contacts. This method serves no real
	 * purpose other than to demonstrate how to annotate a service method.
	 * 
	 * @param listOfRequests
	 *            contact list to echo
	 * @return echoed contact list
	 * 
	 * @gwt.typeArgs <lunchPlanner.client.LunchRequest>
	 */
	public ArrayList getRestaurants();

	
	//public ArrayList setRestaurant(ArrayList listOfRequests);
}
