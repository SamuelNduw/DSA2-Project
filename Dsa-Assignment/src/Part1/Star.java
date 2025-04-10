package Part1;

// Class representing the Star network topology
public class Star {

    // The central server node (hub) of the star topology
    private ServerNode server = new ServerNode();

    // Inserts a new client node into the network by creating a client
    // and registering it with the central server
    public void insertNode(String id) {
        ClientNode newClient = new ClientNode(id, server); // Create new client with reference to server
        server.addClient(newClient); // Add client to server’s client list
    }

    // Removes an existing client node from the network by its ID
    public void deleteNode(String id) {
        server.removeClient(id); // Remove client from the server’s list
    }

    // Getter method to allow access to the server (needed for testing or further operations)
    public ServerNode getServer() {
        return server;
    }
}
