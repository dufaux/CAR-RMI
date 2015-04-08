Johan Dufaux
Allan Rakotoarivony

# Introduction

These programs work as RMI network. 
Each computer on the network need to launch his own rmiregistry.
Our program can have multiple site on multiple computer. After creating each site (as tree site or graph site), an other jar connect site together and they can send and receive message from their neighbors.

### launch the program
For each computer:

in bin directory:
```
SH?
rmiregistry
```
in main directory:
```

```



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
	System.out.println(e.getMessage());
}
```



# Code Samples


```
```

```
```

```
```

```
```

```
```



