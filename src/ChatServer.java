import java.util.List;
import java.util.Map;


public class ChatServer {
	
	private User serverAdmin;
	private Map<User, Connection> userConnections;
	private List<Message> messageLog;
	
	public void run() {
		while(true) {
			Connection newConnection = checkForConnection();
			if(newConnection != null) {
				User newUser = Message.fromData(newConnection.receiveData()).creator;
				this.userConnections.put(newUser, newConnection);
			}
			
			for(Map.Entry<User, Connection> userConnection : this.userConnections.entrySet()) {
				byte[] userData = userConnection.getValue().receiveData();
				if(userData != null) {
					this.messageLog.add(Message.fromData(userData));
				}
			}
		}
	}
	
	private Connection checkForConnection() {
		return new Connection();
	}
	
	public static class ChatClient {
		private Connection serverConnection;
		private User currentUser;
		private String currentAddressBuffer;
		private String currentTextBuffer;
		private boolean shouldConnect;
		private boolean shouldDisconnect;
		private boolean shouldSendMessage;
		
		public void run() {
			
			while(!this.shouldConnect) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			this.connectToServer(NetworkAddress.parse(this.currentAddressBuffer));
			// ID message
			this.sendMessage(new Message(this.currentUser, null));
			this.shouldConnect = false;
			
			while(!this.shouldDisconnect) {
				if(this.shouldSendMessage) {
					this.sendMessage(new Message(this.currentUser, this.currentTextBuffer));
					this.currentTextBuffer = null;
					this.shouldSendMessage = false;
				}
			}
		}
		
		private void connectToServer(NetworkAddress address) {
			this.serverConnection = address.connect();
		}
		
		private void sendMessage(Message message) {
			this.serverConnection.sendData(message.toData());
		}
		
		private void signOff() {
			this.serverConnection.close();
			this.serverConnection = null;
		}
	}
	
	public static class User {
		private String nickName;
	}
	
	
	public static class Message {
		private User creator;
		private String text;
		
		public Message(User creator, String text) {
			this.creator = creator;
			this.text = text;
		}
		
		public static Message fromData(byte[] data) {
			return new Message(null, null);
		}
		
		public byte[] toData() {
			return new byte[0];
		}
	}
	
	public static class NetworkAddress {
		private int[] ipAddress;
		private int port;
		
		public static NetworkAddress parse(String rawAddress) {
			return new NetworkAddress();
		}
		
		public Connection connect() {
			return new Connection();
		}
	}
	
	public static class Connection {
		public void sendData(byte[] data) {
			
		}
		
		public byte[] receiveData() {
			return new byte[0];
		}
		
		public void close() {
			
		}
	}
}
