package lunchPlanner.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface VoteServiceAsync {
	void voteServer(String input, AsyncCallback<String> callback);
}
