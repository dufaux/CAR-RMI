package main;

import java.io.FileNotFoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import rmi.SiteAdministration;
import rmi.tree.SiteTree;
import rmi.tree.Tree;

/**
 * Create a Tree Network from names of computers
 * 
 */
public class MainCreateTreeNetwork {

	/**
	 * @param args
	 * @throws RemoteException 
	 * @throws NotBoundException 
	 * @throws FileNotFoundException 
	 * @throws AlreadyInListException 
	 */
	public static void main(String[] args) throws RemoteException, NotBoundException, FileNotFoundException {
		
		ArrayList<SiteTree> sites = new ArrayList<SiteTree>();
		
		Registry reg;
		SiteAdministration nbsite;
		SiteTree site;
		for(int i = 0;i<args.length;i++){
			reg = LocateRegistry.getRegistry(args[i]);
			nbsite = (SiteAdministration) reg.lookup("numberofsites");
			for(int j = 1;j<=nbsite.getNumberOfSites();j++){
				site = (SiteTree) reg.lookup("site"+j);
				sites.add(site);
			}
		}
		
		Tree greentree = new Tree();
		greentree.setListe(sites);
		greentree.init("tree.csv");
		greentree.createFile("tree.txt");
	}

}
