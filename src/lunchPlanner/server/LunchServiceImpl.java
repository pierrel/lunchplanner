package lunchPlanner.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import lunchPlanner.client.LunchService;
import lunchPlanner.client.LunchRequest;


import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;



public class LunchServiceImpl extends RemoteServiceServlet implements LunchService{

	//ArrayList<Integer> voteList = new ArrayList<Integer>();
	//ArrayList<Restaurant> restaurantList = new ArrayList<Restaurant>();
	HashMap<String, Restaurant> restaurantList;
	HashMap<String, String> userVotedList;
	@Override
	public ArrayList ProcessRequest(ArrayList listOfRequests) {
		String requestType = ((LunchRequest)listOfRequests.get(0)).getValue("requestType");
		return null;
	}
	
	@Override
	public ArrayList VoteRequest(ArrayList listOfRequests){
		//if we are in here then we want to vote
		//HashMap<String, Restaurant> restaurantList = (HashMap<String, Restaurant>) DataStoreUtility.getCached("restaurantCounts");
		
		//we're checking if user is valid
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		LunchRequest lunchRequest = new LunchRequest();
		if(user == null){
			lunchRequest.setValue("isUserValid", "notValid");
			String sourceURL = this.getServletContext().getContextPath();
			lunchRequest.setValue("userURL", userService.createLoginURL(sourceURL));
			ArrayList<LunchRequest> theRequest = new ArrayList<LunchRequest>();
			theRequest.add(lunchRequest);
			return theRequest;	
		}
			
		
		
		if(restaurantList == null)
			restaurantList = new HashMap<String, Restaurant>();
		
		if(userVotedList == null)
			userVotedList = new HashMap<String, String>();
		
		String voteChoice = ((LunchRequest)listOfRequests.get(0)).getValue("voteTarget");
		Restaurant votedRestaurant = restaurantList.get(voteChoice);
		
		//checking if we have already voted
		if(userVotedList.get(user.getEmail()) != null){
			lunchRequest.setValue("voteCount", String.valueOf(votedRestaurant.getVoteCount()));
			lunchRequest.setValue("voteTarget", voteChoice);
			ArrayList<LunchRequest> theRequest = new ArrayList<LunchRequest>();
			theRequest.add(lunchRequest);
			return theRequest;	
		}
		
		//sending an email
		sendEmail(user, voteChoice);
		
		//marking that you have voted
		userVotedList.put(user.getEmail(), "voted");
		
		if(votedRestaurant != null){
			votedRestaurant.setVoteCount(votedRestaurant.getVoteCount() + 1);
			restaurantList.put(voteChoice, votedRestaurant);
			
			//putting into data cache
			//DataStoreUtility.putCached("restaurantCounts", restaurantList);
			
			lunchRequest.setValue("voteCount", String.valueOf(votedRestaurant.getVoteCount()));
			lunchRequest.setValue("voteTarget", voteChoice);
			ArrayList<LunchRequest> theRequest = new ArrayList<LunchRequest>();
			theRequest.add(lunchRequest);
			return theRequest;			
		}
		/*boolean contains = restaurantList.contains(voteChoice);
		if(contains){
			int location = restaurantList.indexOf(voteChoice);
			voteList.set(location, new Integer(voteList.get(location).intValue() + 1));
			LunchRequest lunchRequest = new LunchRequest();
			lunchRequest.setValue("voteCount", String.valueOf(voteList.get(location).intValue() + 1));
			lunchRequest.setValue("voteTarget", voteChoice);
			ArrayList<LunchRequest> theRequest = new ArrayList<LunchRequest>();
			theRequest.add(lunchRequest);
			return theRequest;
		}*/
		
		return null;
	}
	
	@Override
	public ArrayList getRestaurants(){
		//restaurantList = new ArrayList<String>();
		//restaurantList.add("Shiro's");
		//restaurantList.add("Umi");
		//voteList.add(new Integer(0));
		//voteList.add(new Integer(0));
		//restaurantList = new ArrayList<Restaurant>();
		//restaurantList.add(new Restaurant("Shiro's"));
		//restaurantList.add(new Restaurant("Umi"));
		
		//HashMap<String, Restaurant> restaurantList = (HashMap<String, Restaurant>) DataStoreUtility.getCached("restaurantCounts");
		if(restaurantList == null){
			restaurantList = new HashMap<String, Restaurant>();
			restaurantList.put("Specialty's", new Restaurant("Specialty's"));
			restaurantList.put("Bento Box", new Restaurant("Bento Box"));
			restaurantList.put("Jade Garden", new Restaurant("Jade Garden"));
			restaurantList.put("TechTalk/Fishbowl", new Restaurant("TechTalk/Fishbowl"));
			restaurantList.put("Pho", new Restaurant("Pho"));		
			//putting into data cache
			//DataStoreUtility.putCached("restaurantCounts", restaurantList);
		}
		
		
		ArrayList<LunchRequest> theRequest = new ArrayList<LunchRequest>();
		for(String eachRest : restaurantList.keySet()){
			LunchRequest request = new LunchRequest();
			request.setValue("restaurantName", restaurantList.get(eachRest).getName());
			request.setValue("voteCount", String.valueOf(restaurantList.get(eachRest).getVoteCount()));
			theRequest.add(request);
		}
		return theRequest;
	}
	
	
	public void sendEmail(User user, String restaurantName){
		Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        String msgBody = "You have voted for " + restaurantName + "\nThank you for your cooperation." +
        	"\n-The Ming Lunch Team";
        try {
        	Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(user.getEmail()));
            msg.addRecipient(Message.RecipientType.TO,
                             new InternetAddress(user.getEmail(), user.getNickname()));
            msg.setSubject("MingLunch: You have voted for your lunch choice");
            msg.setText(msgBody);
            Transport.send(msg);
        }catch(Exception e){
        	System.out.println(e.getMessage());
        }

	}
	
	
	/*@Override
	public ArrayList setRestaurant(ArrayList listOfRequests){
		ArrayList<LunchRequest> listOfRequests2 = listOfRequests; 
		for(LunchRequest eachReq : listOfRequests2){
			String name = eachReq.getValue("restarantName");
			boolean contains = restaurantList.contains(name);
			if(!contains){
				restaurantList.add(name);
				voteList.add(new Integer(0));
			}
		}
		return null;
	}*/
	
	
	

}
