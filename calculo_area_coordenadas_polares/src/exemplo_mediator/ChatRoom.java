package exemplo_mediator;

import java.util.ArrayList;
import java.util.List;

public class ChatRoom implements ChatMediator {

	private List<User> users;
	
	public ChatRoom () {
		this.users = new ArrayList<>();
	}
	
	public void addUser(User user) {
		this.users.add(user) ;
	}
	
	public void sendMessage(String msg, User originator) {
		for ( User u : users ) {
			if ( u != originator) {
				u.receive(msg);
			}
		}
	}

}
