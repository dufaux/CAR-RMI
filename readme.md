

#TP CAR RMI 

Johan Dufaux
Allan Rakotoarivony

9 April 2015

# Introduction

These programs work as RMI network. 
Each computer on the network need to launch his own rmiregistry.
Our program can have multiple site on multiple computer. After creating each site (as tree site or graph site), an other jar connect site together and they can send and receive message from their neighbors.

### launch the program
You have to create bin directory from src. 

Example for graph. Replace "graph" by "tree" if you want a tree.  
For each computer:  
  
in bin directory:
```
stubgenerator.sh
rmiregistry
```
in main directory:
```
java -jar createGraphSite.jar // as much as we want
```


in only one computer, after each node have been created:
```
java -jar createGraphNetwork.jar nameofmachine1 nameofmachine2...
```
the graph.txt file is automatically created to represent the network. you can create a graph image with:
```
dot -Tpng graph.txt -o network.png
```


to send message:
in one computer:
```
java -jar sendMessageFrom <pc_name> <machine_id>
```
or write on the terminal of site.

#### Informations
graph.csv and tree.csv are the models used to create networks. (graph.png and tree.png are the representation of .csv files).

# Design

## Packages
| Name | Description |
|-----|-------------|
|main|Contains the executables of the application|
|rmi|Contains principal interfaces of the project|
|rmi.graph|Contains interfaces and implementations of site in a graph network|
|rmi.tree|Contains interfaces and implementations of site in a tree network|


## Exceptions

Creating of new SiteAdministrator if there is no SiteAdministrator
```
try {
	adminNumber = (SiteAdministration) reglocal.lookup("numberofsites");
}catch (NotBoundException e) {
	adminNumber = new SiteAdministrationImpl();
	reglocal.bind("numberofsites", adminNumber);
}
```


```
try {
	neighbor.receiveMessage(message);
} catch (RemoteException e) {
	throw new RuntimeException(e);
}
```



# Code Samples


To create a site, the program tries to get the address of the computer on the network and its local registry. With the help of the SiteAdministration, it gets it id on the localhost. Then a site is registerd on the registry with id : machineName_localId
```
public static void main(String[] args) throws RemoteException, AlreadyBoundException, UnknownHostException {
		
		InetAddress addr;
		addr = InetAddress.getLocalHost();
		String hostname = addr.getHostName();
	    
		System.setProperty("java.rmi.server.hostname",hostname);
		Registry reglocal = LocateRegistry.getRegistry("localhost");
		
		SiteAdministration adminNumber;
		try {
			adminNumber = (SiteAdministration) reglocal.lookup("numberofsites");
		}catch (NotBoundException e) {
			adminNumber = new SiteAdministrationImpl();
			reglocal.bind("numberofsites", adminNumber);
		}
		
		int nombre = adminNumber.getNumberOfSites();
			
			
		final SiteGraph site = new SiteGraphImpl(hostname+"_"+(nombre+1));
		String rmiadd = "site"+(nombre+1);
		
		reglocal.bind(rmiadd, site);
		adminNumber.increment();
		System.out.println("NEW SITE GRAPH CREATED ON THIS COMPUTER : "+hostname+" \n" +
				"bound on local registry with this adress : "+rmiadd);
		
		[...]
	}
}
```

After being registered, the site launch a new thread to read inputs from the users and send it to its neighbors.
```
new Runnable() {
	@Override
	public void run() {
		 try {
			 InputStreamReader r=new InputStreamReader(System.in);  
			 BufferedReader br=new BufferedReader(r);  
			  
			 String message="";  
			  
			 System.out.println("ok");
			  while(! message.equals("stop")){  
			   System.out.println("Enter message: ");  
			   message=br.readLine();
			   Message m = site.createMessage(message);
			   site.sendMessage(m);
			   System.out.println("You sent : "+message+" to your neighbors");  
			  }  
			  
			 br.close();  
			 r.close(); 
		} catch (IOException e) {
			throw new RuntimeException(e);
		}  
	}
}.run();
```

To create the network configuration, this method take as parameters names of machines that have sites and collect those site. It adds those site in a graph and initializes  a graph which structure is taken from an external file.


```
public static void main(String[] args) throws AccessException, RemoteException, NotBoundException, UnknownHostException, FileNotFoundException {	
	Registry reg;
	SiteAdministration nbsite;
	SiteGraph site;

	Graph biggraph = new Graph();
	
	for(int i = 0;i<args.length;i++){
		reg = LocateRegistry.getRegistry(args[i]);
		nbsite = (SiteAdministration) reg.lookup("numberofsites");
		for(int j = 1;j<=nbsite.getNumberOfSites();j++){
			site = (SiteGraph) reg.lookup("site"+j);
			//sites.add(site);
			biggraph.add(site);
		}
	}

	biggraph.init("graph.csv");
	biggraph.createFile("graph.txt");
}

```



With this function, a site sends a message concurrently to all its neighbors.
```
@Override
public void sendMessage(final Message message) throws RemoteException {
	for (final SiteGraph neighbor : this.neighbors) {
		new Runnable() {
			@Override
			public void run() {
				try {
					neighbor.receiveMessage(message);
				} catch (RemoteException e) {
					throw new RuntimeException(e);
				}
			}
		}.run();
	}
}
```


When a site get a message that is sent by another site, if it has not already received it, it print it and re-send it to its neighbor
```
@Override
public void receiveMessage(Message message) throws RemoteException {		
	if(!this.history.contains(message) && !this.equals(message.getInitiator()) ){
		this.lastMessage = message;
		this.history.add(message);
		String toPrint = "Message initiated by " + message.getInitiator().getId() + " and sent by "+message.getSender().getId()+" : ";
		toPrint += message.getContent();
		System.out.println(toPrint);
		this.sendMessage(message);
	}
}

```


#Remarques personelles apres evaluation
Pour les tests, faire des tests du registry (en live), vrai test de RMI  
Runnable != Thread  
Throw nos propres exceptions ou des exceptions qui existent mais pas juste RuntimeException  
Bien nommer (init => createFromSpec)  
Si on override equals => override hashcode  
Expliquer en un paragraphe le design  
Pas de méthodes de plus de 10 lignes  



