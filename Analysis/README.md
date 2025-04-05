Project Description
Computer local area networks sometimes adopt a star topology. Star topology means
each network node (computer, or other computing device) is connected to a central
(server) node (central hub, router or switch) with a point-to-point connection. All nodes
link to each other via the central (server) node. No direct connection exists between the
peripheral (client) nodes. The central node is the server and serves all other nodes (clients).
The peripheral nodes to the central node pass all traffic through the central node.
You decide to model the star topology using a data structure with a central node having
multiple pointers/references on it and space for at least two (2) data values. The
peripheral nodes only have one pointer/reference each and space for at least two (2) data
values.
a. ServerNode Class
i. Write a java class (call it ServerNode) for the server node. [1 mark]
ii. The server node object must broker messages sent by client node objects. [2 marks]
b. ClientNode Class
i. Write a java class (call it ClientNode) for the client node. [1 mark]
ii. Each client node object must have a send( ) and receive( ) method, [2 marks]
iii. Each client node must have an ID/unique name [2 mark]
NOTE:
The send( ) method is naturally a wrapper around a call to an appropriate
method on the server node.
The receive( ) method need do nothing more than print the message and the
name/id of the sender. It need only be sequential.
e.g. client X sends "hello" to client Y who prints the message.
c. Star Class
i.Write a java class (call it Star) for the model of the network.
ii. The class must have the methods insertNode( ) and deleteNodee( ). NOTE:
[2 marks]
 [2 marks]
3
NOTE:
insertNode(zero or more parameters): adds a node to the model,
deleteNode(zero or more parameters): deletes a node from the model.
d. Data compression
[3 marks]
i. If you were to compress the messages sent between clients, which compression
algorithm(s) would be most suited for this purpose? Justify your answer. Compress
and decompress any one of the messages sent between any two nodes.
ii. Compute the worst case time complexity of your solution in d(i).
Notes
Details missing from the assignment directions e.g. return types for the methods and
so on, give you the opportunity to be creative.
None of the above classes is the main class; the main class is separate from
the classes described above.
The examiner may award bonus marks for productive creativity.
The examiner expects copious amounts of comments in your code in order to be
convinced that you understand what you are doing.
SESSION B - 15 MARKS
FT and PT DSA2 students are to form themselves into Groups/Teams of minimum 5 or maximum 6.
Project description
State one algorithm for each and write a menu driven program in java to perform the following
operations on Trees in Data Structures and Algorithms, using the eight (8) elements below:
7 5 9 4 6 8 13 2
===================TREE IMPLEMENTATION MENU===================
1. Binary Search Tree (Inserting Node 3)
2. Binary Search Tree (Postorder transversal)
3. AVL (Insert the nine elements)
4. Red Black Tree (Insert the nine elements and display Postorder transversal)
5. B-trees (Implementation and search for key 8)
6. Exit