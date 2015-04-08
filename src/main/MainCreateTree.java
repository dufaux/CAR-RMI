package main;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import rmi.tree.SiteTree;

public class MainCreateTree {

	/**
	 * @param args
	 * @throws RemoteException 
	 * @throws NotBoundException 
	 */
	public static void main(String[] args) throws RemoteException, NotBoundException {
		
		ArrayList<Registry> registrys = new ArrayList<Registry>();
		
		
		// recupere les registre
		Registry reg = LocateRegistry.getRegistry(args[0]);
		registrys.add(reg);
		reg = LocateRegistry.getRegistry(args[1]);
		registrys.add(reg);
		reg = LocateRegistry.getRegistry(args[2]);
		registrys.add(reg);
		
		
		SiteTree s1 = (SiteTree) registrys.get(0).lookup("site");
		SiteTree s2 = (SiteTree) registrys.get(1).lookup("site");
		SiteTree s3 = (SiteTree) registrys.get(2).lookup("site");
		
		
		s1.setSons(s2, s3);
		s2.setFather(s1);
		s3.setFather(s1);
	}

}
