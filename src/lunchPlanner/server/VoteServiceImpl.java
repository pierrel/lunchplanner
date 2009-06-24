package lunchPlanner.server;

import lunchPlanner.client.VoteService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.gwt.user.server.rpc.RPCRequest;
import com.google.gwt.user.server.rpc.*;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;


public class VoteServiceImpl extends RemoteServiceServlet implements VoteService{
	
	public String voteServer(String input){
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		//if (user != null) {
        //    resp.getWriter().println("Hello, " + user.getNickname());
        //} else {
        //    resp.sendRedirect(userService.createLoginURL(req.getRequestURI()));
        //}
		
		//this.getServletName();
		String sourceURL = this.getServletContext().getContextPath();
		
		//RPCRequest x = new RPCRequest(null, null, null);
		//if(user == null)
		return userService.createLoginURL(sourceURL);
		//return sourceURL;
	}
}
