package lunchPlanner.client;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;

public class VoteWidget extends Composite{
	private final VoteServiceAsync voteService = GWT
	.create(VoteService.class);

	ArrayList<String> restaurants;
	ArrayList<Restaurant> restaurants2;
	VerticalPanel vPanelRadio;
	VerticalPanel vPanelVoteCount;
	VerticalPanel widgetVPanel;
	Button voteButton;
	ArrayList<RadioButton> radioButtons;
	ArrayList<HTML> restaurantVotesHTML;
	TextBox RestaurantNameEntry;
	Button submitRestaurant;
	FlexTable flexTable;
	public VoteWidget(){
		flexTable = new FlexTable();
		widgetVPanel = new VerticalPanel();
		
		FlexCellFormatter cellFormatter = flexTable.getFlexCellFormatter();
		flexTable.addStyleName("cw-FlexTable");
		//flexTable.setWidth("32em");
		flexTable.setCellSpacing(5);
		flexTable.setCellPadding(3);
		cellFormatter.setHorizontalAlignment(0, 1,
				HasHorizontalAlignment.ALIGN_LEFT);
		cellFormatter.setColSpan(0, 0, 2);
		
		
		HorizontalPanel hPanelRestaurants = new HorizontalPanel();
		vPanelRadio = new VerticalPanel();
		vPanelVoteCount = new VerticalPanel();
		voteButton = new Button("Vote!");
		RestaurantNameEntry = new TextBox();
		submitRestaurant = new Button("Submit Restaurant");
		widgetVPanel.add(flexTable);
		widgetVPanel.add(voteButton);
		widgetVPanel.add(new HTML("Enter a Restaurant Name:"));
		widgetVPanel.add(RestaurantNameEntry);
		widgetVPanel.add(submitRestaurant);
		restaurants = new ArrayList();
		radioButtons = new ArrayList();	
		getRestaurants();
		
		//now we attach listeners to the vote button
		createVoteButtonListener();
		
		initWidget(widgetVPanel);
	}
	
	public String getSelectedRadioButton(){
		for(RadioButton each : radioButtons){
			if(each.isChecked())
				return each.getHTML();
		}
		return null;
	}
	
	
	public ArrayList<String> getRestaurants(){
		ArrayList<LunchRequest> reply;
		LunchServiceAsync lunchService = GWT.create(LunchService.class);
		lunchService.getRestaurants(new AsyncCallback<ArrayList>() {
							public void onFailure(Throwable caught) {
							}
							public void onSuccess(ArrayList result) {
								restaurants = new ArrayList<String>();
								restaurants2 = new ArrayList<Restaurant>();
								for(LunchRequest each : (ArrayList<LunchRequest>)result){
									String name = each.getValue("restaurantName");
									restaurants.add(name);
									int vote = Integer.parseInt(each.getValue("voteCount"));
									restaurants2.add(new Restaurant(name, vote));
								}
								updateVoteOptions(restaurants2);
							}
		});
		return null;
	}
	
	
	public void updateVoteOptions(ArrayList<Restaurant> newRestaurants){
		vPanelRadio.clear();
		vPanelVoteCount.clear();
		restaurants2 = (ArrayList<Restaurant>)newRestaurants.clone();
		radioButtons = new ArrayList();
		restaurantVotesHTML = new ArrayList();
		int count = 0;
		for(Restaurant each : newRestaurants){
			count++;
			RadioButton radioButton = new RadioButton("restaurants", each.getName());
			radioButton.ensureDebugId("restaurants-" + each.getName());
			radioButtons.add(radioButton);
			HTML voteCount = new HTML(String.valueOf(each.getVoteCount()));
			restaurantVotesHTML.add(voteCount);
			//vPanelRadio.add(radioButton);
			//vPanelVoteCount.add(voteCount);
			
			
			flexTable.setWidget(count, 0, radioButton);
			flexTable.setWidget(count, 1, voteCount);
		}
	}
	
	public void createVoteButtonListener(){
		class VoteButtonHandler implements ClickHandler{
			
			@Override
			public void onClick(ClickEvent event) {
				String name = getSelectedRadioButton();
				if(name != null){
					LunchRequest lunchRequest = new LunchRequest();
					lunchRequest.setValue("voteTarget", name);
					ArrayList<LunchRequest> theRequest = new ArrayList<LunchRequest>();
					theRequest.add(lunchRequest);
					LunchServiceAsync lunchService = GWT.create(LunchService.class);
					lunchService.VoteRequest(theRequest, new AsyncCallback<ArrayList>() {
							public void onFailure(Throwable caught) {
							}
							public void onSuccess(ArrayList result) {
								if(result != null){
									LunchRequest reply = (LunchRequest)result.get(0);
									String isUserValid = reply.getValue("isUserValid");
									String notValid = "notValid";
									if(notValid.equals(isUserValid)){
										Window.Location.assign(reply.getValue("userURL"));
										return;
									}
									
									String restaurantName = reply.getValue("voteTarget");
									int voteCount = Integer.parseInt(reply.getValue("voteCount"));
									for(Restaurant each : restaurants2){
										if(each.getName().equals(restaurantName))
											each.setVoteCount(voteCount);
									}
								}
								updateVoteOptions(restaurants2);
							}
							
					});
				}
			}
			
			
			
		}
		
		VoteButtonHandler theVoteButtonHandler = new VoteButtonHandler();
		voteButton.addClickHandler(theVoteButtonHandler);
	}
}
