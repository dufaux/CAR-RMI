package main;

import java.io.FileNotFoundException;
import java.net.UnknownHostException;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import rmi.graph.Graph;
import rmi.graph.SiteGraph;
import rmi.graph.SiteGraphImpl;

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
		
		
		ArrayList<Registry> registrys = new ArrayList<Registry>();
		ArrayList<SiteGraph> sites = new ArrayList<SiteGraph>();
		
		Registry reg;
		int nbsite;
		SiteGraph site;
		for(int i = 0;i<args.length;i=i+2){
			reg = LocateRegistry.getRegistry(args[i]);
			nbsite = Integer.parseInt(args[i+1]);
			for(int j = 1;j<=nbsite;j++){
				site = (SiteGraph) reg.lookup("site"+j);
				sites.add(site);
			}
		}
		

		Graph biggraph = new Graph();
		biggraph.setListe(sites);
		biggraph.init("graph.csv");
		biggraph.createFile("graph.txt");
		
		
		
		/*// recupere les registre
		Registry reg = LocateRegistry.getRegistry(args[0]);
		registrys.add(reg);
		reg = LocateRegistry.getRegistry(args[1]);
		registrys.add(reg);
		//reg = LocateRegistry.getRegistry(args[2]);
		//registrys.add(reg);
		
		
		SiteGraph s1 = (SiteGraph) registrys.get(0).lookup("site");
		SiteGraph s2 = (SiteGraph) registrys.get(1).lookup("site");
		SiteTree s3 = (SiteTree) registrys.get(2).lookup("site");
		
		
		
		
		//s1.setSons(s2, s3);
		s1.setNeighbor(s2);
		s2.setNeighbor(s1);
		//s3.setFather(s1);*/
		
	}

}
