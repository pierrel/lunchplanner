package lunchPlanner.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("vote")
public interface VoteService extends RemoteService{
	String voteServer(String name);
}
