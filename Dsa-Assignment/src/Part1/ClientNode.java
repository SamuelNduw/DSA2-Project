package Part1;

// Class representing a client node in a star topology network
public class ClientNode {
    // Unique identifier for the client node
    private String id;

    // Reference to the central server node that handles message routing
    private ServerNode server;

    // Constructor to initialize the client with a unique ID and its server connection
    public ClientNode(String id, ServerNode server) {
        this.id = id;
        this.server = server;
    }

    // Getter method to retrieve the client ID
    public String getId() {
        return id;
    }

    // Method for sending a message to another client via the server
    // This method delegates the actual delivery to the server's brokerMessage method
    public void send(String message, String receiverId) {
        server.brokerMessage(this.id, receiverId, message);
    }

    // Method for receiving a message from another client
    // Simply prints out the sender ID and the message content
    public void receive(String message, String senderId) {
        System.out.println("Message from " + senderId + ": " + message);
    }
}
