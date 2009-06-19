package lunchPlanner.server;

import lunchPlanner.client.VoteService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;


public class VoteServiceImpl extends RemoteServiceServlet implements
VoteService{
	public String voteServer(String input){
		return "Bryan is cool";
	}
}
