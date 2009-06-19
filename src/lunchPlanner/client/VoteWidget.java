package lunchPlanner.client;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.VerticalPanel;

public class VoteWidget extends Composite{
	private final VoteServiceAsync voteService = GWT
	.create(VoteService.class);

	ArrayList<String> restaurants;
	VerticalPanel vPanel;
	public VoteWidget(){
		vPanel = new VerticalPanel();
		vPanel.add(new HTML("Restaurants"));
		restaurants = new ArrayList();
		restaurants.add("Ming");
		restaurants.add("Ming2");
		for(String each : restaurants){
			RadioButton radioButton = new RadioButton("restaurants", each);
			radioButton.ensureDebugId("restaurants-" + each);
			vPanel.add(radioButton);
		}
		initWidget(vPanel);
	}
	
	public void updateVoteOptions(ArrayList<String> newRestaurants){
		vPanel.clear();
		vPanel.add(new HTML("Restaurants"));
		restaurants = (ArrayList<String>)newRestaurants.clone();
		for(String each : newRestaurants){
			RadioButton radioButton = new RadioButton("restaurants", each);
			radioButton.ensureDebugId("restaurants-" + each);
			vPanel.add(radioButton);
		}
	}
}
