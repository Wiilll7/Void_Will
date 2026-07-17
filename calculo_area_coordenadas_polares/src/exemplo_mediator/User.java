package exemplo_mediator;

public class User {

	private ChatMediator mediator;
	private String name;
	
	
	public User(ChatMediator mediator, String name) {
		this.mediator = mediator;
		this.name = name;
	}
	
	
	public void send(String msg) {
		System.out.println(this.name + " sends: " + msg);
		mediator.sendMessage(msg, this);
	}
	
	public void receive(String msg) {
		System.out.println(this.name + " received: " + msg);
	}
	
}
