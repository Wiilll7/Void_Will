package exemplo_mediator;

public interface ChatMediator {
	void sendMessage(String msg, User user);
	void addUser(User user);
}
