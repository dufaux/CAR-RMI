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
		
		// recupere les registre
		/*Registry reg = LocateRegistry.getRegistry(args[0]);
		registrys.add(reg);
		reg = LocateRegistry.getRegistry(args[1]);
		registrys.add(reg);
		reg = LocateRegistry.getRegistry(args[2]);
		registrys.add(reg);
		
		SiteTree s1 = (SiteTree) registrys.get(0).lookup("site");
		SiteTree s2 = (SiteTree) registrys.get(1).lookup("site");
		SiteTree s3 = (SiteTree) registrys.get(2).lookup("site");*/
		
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
		/*
		s1.setSons(s2, s3);
		s2.setFather(s1);
		s3.setFather(s1);*/
	}

}
