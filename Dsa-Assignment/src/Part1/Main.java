package Part1;

public class Main {
    public static void main(String[] args) {
        // Create the star topology network model.
        // The network contains a central server node that connects to multiple client nodes.
        Star network = new Star();

        // Insert client nodes into the network.
        // Each call creates a new ClientNode and registers it with the central ServerNode.
        network.insertNode("ClientA");
        network.insertNode("ClientB");
        network.insertNode("ClientC");

        // Retrieve the server instance from the network to access its client map.
        // This allows us to get individual client references for testing.
        ServerNode server = network.getServer(); // Uses the getter method in the Star class.

        // Retrieve client nodes by their unique IDs using a getter in the ServerNode.
        ClientNode clientA = server.getClient("ClientA");
        ClientNode clientB = server.getClient("ClientB");
        ClientNode clientC = server.getClient("ClientC");

        // Simulate normal message sending between clients.
        // The messages are routed via the ServerNode, which acts as a broker.
        System.out.println("\n--- Message Simulation ---");
        clientA.send("Hello from A to B", "ClientB");
        clientB.send("Hi A, this is B", "ClientA");
        clientC.send("Hey B, it's C!", "ClientB");

        // Demonstrate data compression and decompression of a message.
        System.out.println("\n--- Data Compression Demonstration ---");
        // Define the original message to be compressed.
        String originalMessage = "Hello from A to B";
        System.out.println("Original Message: " + originalMessage);

        // Compress the original message using our CompressionUtil class.
        // The message is compressed and then encoded in Base64 for easy display.
        String compressedMessage = CompressionUtil.compress(originalMessage);
        System.out.println("Compressed Message (Base64): " + compressedMessage);

        // Decompress the compressed message back to its original form.
        String decompressedMessage = CompressionUtil.decompress(compressedMessage);
        System.out.println("Decompressed Message: " + decompressedMessage);

        // Demonstrate deletion of a node from the network.
        // Remove "ClientB" from the network.
        System.out.println("\n--- Deleting ClientB ---");
        network.deleteNode("ClientB");

        // Test sending a message to the deleted node.
        // Since "ClientB" has been removed, the server should indicate that the client is not found.
        System.out.println("\n--- Sending message to deleted ClientB ---");
        clientA.send("Are you still there?", "ClientB");
    }
}
