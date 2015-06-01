Architectural pattern
==

What is a Pattern?
An architectural pattern establishes a relationship between:

-  A context: A recurring, common situation in the world that gives rise to a problem.
-  A problem: The problem, appropriately generalized, that arises in the given context.
-  A solution: A successful architectural resolution to the problem, appropriately abstracted. The solution for a pattern is determined and described by:
	-  A set of element types
	-  A set of interaction mechanisms or connectors
	-  A topological layout of the components
	-  A set of semantic constraints covering topology, element behavior, and interaction mechanisms


1. Layer pattern
--
-  Context: All complex systems experience the need to develop and evolve portions of the system independently. Need a clear and well- documented separation of concerns, so that modules of the system may be independently developed and maintained.
-  Problem: The software needs to be segmented in such a way that the modules can be developed and evolved separately with little interaction among the parts, supporting portability, modifiability, and reuse.
-  Solution: To achieve this separation of concerns, the layered pattern divides the software into units called layers. Each layer is a grouping of modules that offers a cohesive set of services. The usage must be unidirectional. Layers completely partition a set of software, and each partition is exposed through a public interface.


###Layer Pattern Solution
-  Overview: The layered pattern defines layers and a
unidirectional allowed-to-use relation among the layers.
-  Elements: Layer, a kind of module. The description of a layer
should define what modules the layer contains.
-  Relations: Allowed to use. The design should define what the layer usage rules are and any allowable exceptions.
-  Constraints:
	-  Every piece of software is allocated to exactly one layer. 
	-  There are at least two layers.
	-  The allowed-to-use relations should not be circular.
-  Weaknesses:
	-  The addition of layers adds up-front cost and complexity to a
system.
	-  Layers contribute a performance penalty.


2. Broker pattern
--
-  Context: Many systems are constructed from a collection of services distributed across multiple servers. Implementing these systems is complex because you need to worry about how the systems will interoperate—how they will connect to each other and how they will exchange information—as well as the availability of the component services.
-  Problem: How do we structure distributed software so that service users do not need to know the nature and location of service providers, making it easy to dynamically change the bindings between users and providers?
-  Solution: The broker pattern separates users of services (clients) from providers of services (servers) by inserting an intermediary, called a broker. When a client needs a service, it queries a broker via a service interface. The broker then forwards the client’s service request to a server, which processes the request.

###Broker Solution 
-  Overview: The broker pattern defines a runtime component, called a broker, that mediates the communication between a number of clients and servers.
-  Elements:
	-  Client, a requester of services
	-  Server, a provider of services
	-  Broker, an intermediary that locates an appropriate server to fulfill a client’s request, forwards the request to the server, and returns the results to the client
	-  Client-side proxy, an intermediary that manages the actual communication with the broker, including marshaling, sending, and unmarshaling of messages
	-  Server-side proxy, an intermediary that manages the actual communication with the broker, including marshaling, sending, and unmarshaling of messages
-  Relations: The attachment relation associates clients (and, optionally, client-side proxies) and servers (and, optionally, server-side proxies) with brokers.
-  Constraints: The client can only attach to a broker (potentially via a client-side proxy). The server can only attach to a broker (potentially via a server-side proxy).
-  Weaknesses:
	-  Brokers add a layer of indirection, and hence latency, between clients and servers, and that layer may be a communication bottleneck.
	-  The broker can be a single point of failure.
	-  A broker adds up-front complexity.
	-  A broker may be a target for security attacks.
	-  A broker may be difficult to test.

3. Model-View-Controller pattern
--
-  Context: UI is typically frequently modified. Users often wish to look at data from different perspectives. These representations should both reflect the current state of the data.
-  Problem: How can user interface functionality be kept separate from application functionality and yet still be responsive to user input, or to changes in the underlying application’s data? And how can multiple views of the user interface be created, maintained, and coordinated when the underlying application data changes?
-  Solution: The model-view-controller (MVC) pattern separates application functionality into three kinds of components:
	-  A model, which contains the application’s data
	-  A view, which displays some portion of the underlying data and interacts with the user
	-  A controller, which mediates between the model and the view and manages the notifications of state changes

4. Pipe and Filter pattern
--
-  Context: Many systems are required to transform streams of discrete data items, from input to output. Many types of transformations occur repeatedly in practice, and so it is desirable to create these as independent, reusable parts.
-  Problem: Such systems need to be divided into reusable, loosely coupled components with simple, generic interaction mechanisms. In this way they can be flexibly combined with each other. The components, being generic and loosely coupled, are easily reused. The components, being independent, can execute in parallel.
-  Solution: The pattern of interaction in the pipe-and-filter pattern is characterized by successive transformations of streams of data. Data arrives at a filter’s input port(s), is transformed, and then is passed via its output port(s) through a pipe to the next filter. A single filter can consume data from, or produce data to, one or more ports.

5. Client-Server pattern
--
-  Context: There are shared resources and services that large numbers of distributed clients wish to access, and for which we wish to control access or quality of service.
-  Problem: By managing a set of shared resources and services, we can promote modifiability and reuse, by factoring out common services and having to modify these in a single location, or a small number of locations. We want to improve scalability and availability by centralizing the control of these resources and services, while distributing the resources themselves across multiple physical servers.
-  Solution: Clients interact by requesting services of servers, which provide a set of services. Some components may act as both clients and servers. There may be one central server or multiple distributed ones.

6. Peer-to-Peer pattern
--
-  Context: Distributed computational entities—each of which is considered equally important in terms of initiating an interaction and each of which provides its own resources—need to cooperate and collaborate to provide a service to a distributed community of users.
-  Problem: How can a set of “equal” distributed computational entities be connected to each other via a common protocol so
that they can organize and share their services with high availability and scalability?
-  Solution: In the peer-to-peer (P2P) pattern, components directly interact as peers. All peers are “equal” and no peer or group of peers can be critical for the health of the system. Peer- to-peer communication is typically a request/reply interaction without the asymmetry found in the client-server pattern.

6. Service Oriented Architecture pattern
--
-  Context: A number of services are offered (and described) by service providers and consumed by service consumers. Service consumers need to be able to understand and use these services without any detailed knowledge of their implementation.
-  Problem: How can we support interoperability of distributed components running on different platforms and written in different implementation languages, provided by different organizations, and distributed across the Internet?
-  Solution: The service-oriented architecture (SOA) pattern describes a collection of distributed components that provide and/or consume services.

7. Publish-Subscribe pattern
--
-  Context: There are a number of independent producers and consumers of data that must interact. The precise number and nature of the data producers and consumers are not predetermined or fixed, nor is the data that they share.
-  Problem: How can we create integration mechanisms that support the ability to transmit messages among the producers and consumers so they are unaware of each other’s identity, or potentially even their existence?
-  Solution: In the publish-subscribe pattern, components interact via announced messages, or events. Components may subscribe to a set of events. Publisher components place events on the bus by announcing them; the connector then delivers those events to the subscriber components that have registered an interest in those events.

8. Shared-Data pattern
--
-  Context: Various computational components need to share and manipulate large amounts of data. This data does not belong solely to any one of those components.
-  Problem: How can systems store and manipulate persistent data that is accessed by multiple independent components?
-  Solution: In the shared-data pattern, interaction is dominated by the exchange of persistent data between multiple data accessors and at least one shared-data store. Exchange may be initiated by the accessors or the data store. The connector type is data reading and writing.

9. Map-Reduce pattern
--
-  Context: Businesses have a pressing need to quickly analyze enormous volumes of data they generate or access, at petabyte scale.
-  Problem: For many applications with ultra-large data sets, sorting the data and then analyzing the grouped data is sufficient. The problem the map- reduce pattern solves is to efficiently perform a distributed and parallel sort of a large data set and provide a simple means for the programmer to specify the analysis to be done.
-  Solution: The map-reduce pattern requires three parts:
-  A specialized infrastructure takes care of allocating software to the hardware nodes in a massively parallel computing environment and handles sorting the data as needed.
-  A programmer specified component called the map which filters the data to retrieve those items to be combined.
-  A programmer specified component called reduce which combines the results of the map

10. Multi-Tier pattern
--
-  Context:Inadistributeddeployment,thereisoftena need to distribute a system’s infrastructure into distinct subsets.
-  Problem:Howcanwesplitthesystemintoanumber of computationally independent execution structures —groups of software and hardware—connected by some communications media?
-  Solution:Theexecutionstructuresofmanysystems are organized as a set of logical groupings of components. Each grouping is termed a tier.

###Multi-Tier Solution
-  Overview: The execution structures of many systems are organized as a set of logical groupings of components. Each grouping is termed a tier.
-  Elements:
	-  Tier, which is a logical grouping of software components.
-  Relations:
	-  Is part of, to group components into tiers.
	-  Communicates with, to show how tiers and the components they contain interact with each other.
	-  Allocated to, in the case that tiers map to computing platforms.
-  Constraints: A software component belongs to exactly one tier.
-  Weaknesses: Substantial up-front cost and complexity.

Relationship between Tactics and Patterns
--
-  Patterns are built fromt actics; if a pattern is a molecule, a tactic is an atom.
-  MVC, for example utilizes the tactics: –  Increase semantic coherence
	-  Encapsulation
	-  Use an intermediary
	-  Use run time binding


-  Patterns solve a specific problem but are neutral or have weaknesses with respect to other qualities.
-  Considerthebrokerpattern
	-  May have performance bottlenecks –  May have a single point of failure
-  Usingtacticssuchas
	-  Increase resources will help performance
	-  Maintain multiple copies will help availability


Reference
--
**Source:** (TDT4240: Software Architecture -- Lecture Slides by Professor Alf Inge Wang)
