package main;

import java.io.FileNotFoundException;
import java.net.UnknownHostException;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import rmi.SiteAdministration;
import rmi.graph.Graph;
import rmi.graph.SiteGraph;
import rmi.tree.SiteTree;

public class MainCreateGraph {

	/**
	 * @param args
	 * @throws NotBoundException
	 * @throws RemoteException 
	 * @throws AccessException 
	 * @throws UnknownHostException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws AccessException, RemoteException, NotBoundException, UnknownHostException, FileNotFoundException {
		
		
		ArrayList<SiteGraph> sites = new ArrayList<SiteGraph>();
		
		Registry reg;
		SiteAdministration nbsite;
		SiteGraph site;
		/*for(int i = 0;i<args.length;i=i+2){
			reg = LocateRegistry.getRegistry(args[i]);
			nbsite = Integer.parseInt(args[i+1]);
			for(int j = 1;j<=nbsite;j++){
				site = (SiteGraph) reg.lookup("site"+j);
				sites.add(site);
			}
		}*/
		
		for(int i = 0;i<args.length;i++){
			reg = LocateRegistry.getRegistry(args[i]);
			nbsite = (SiteAdministration) reg.lookup("numberofsites");
			for(int j = 1;j<=nbsite.getNumberOfSites();j++){
				site = (SiteGraph) reg.lookup("site"+j);
				sites.add(site);
			}
		}
		

		Graph biggraph = new Graph();
		biggraph.setListe(sites);
		biggraph.init("graph.csv");
		biggraph.createFile("graph.txt");
		
	}

}
