package main;

import java.io.FileNotFoundException;
import java.net.UnknownHostException;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import rmi.SiteAdministration;
import rmi.graph.Graph;
import rmi.graph.SiteGraph;

/**
 * Create a graph network from names of computers
 */
public class MainCreateGraphNetwork {

	/**
	 * @param args
	 * @throws NotBoundException
	 * @throws RemoteException 
	 * @throws AccessException 
	 * @throws UnknownHostException 
	 * @throws FileNotFoundException 
	 */
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
				biggraph.add(site);
			}
		}
		


		biggraph.init("graph.csv");
		biggraph.createFile("graph.txt");
		
	}

}
