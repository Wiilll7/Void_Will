package exemplo_mediator;

public class Main {

	public static void main(String[] args) {
		
		ChatMediator chatRoom = new ChatRoom();
		
		User user1 = new User(chatRoom, "Joao Mello");
		User user2 = new User(chatRoom, "Pedro Amaral");
		User user3 = new User(chatRoom, "Bernardo");
		
		chatRoom.addUser(user1);
		chatRoom.addUser(user2);
		chatRoom.addUser(user3);
		
		user1.send("oi");
		
	}

}
