package Part1;

import java.util.HashMap;
import java.util.Map;

// Class representing the central server node in a star topology network
public class ServerNode {

    // Map to store connected clients using their unique ID as the key
    private Map<String, ClientNode> clients = new HashMap<>();

    // Adds a new client to the network (i.e., connects the client to the server)
    public void addClient(ClientNode client) {
        clients.put(client.getId(), client); // Store client using its ID
    }

    // Removes a client from the network based on its ID
    public void removeClient(String clientId) {
        clients.remove(clientId); // Remove client from the map
    }

    // Routes (brokers) a message from one client to another
    // Ensures the receiver exists before delivering the message
    public void brokerMessage(String senderId, String receiverId, String message) {
        if (clients.containsKey(receiverId)) {
            clients.get(receiverId).receive(message, senderId); // Deliver the message
        } else {
            System.out.println("Client not found: " + receiverId); // Receiver doesn't exist
        }
    }

    // Getter method to retrieve a client by its ID
    public ClientNode getClient(String id) {
        return clients.get(id); // Return client object from the map
    }
}
