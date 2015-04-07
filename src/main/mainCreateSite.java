package main;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import rmi.tree.SiteTree;
import rmi.tree.SiteTreeImpl;

public class mainCreateSite {

	/**
	 * @param args
	 * @throws RemoteException 
	 * @throws AlreadyBoundException 
	 */
	public static void main(String[] args) throws RemoteException, AlreadyBoundException {
		
		//System.out.println(args[0]);
		System.setProperty("java.rmi.server.hostname",args[0]);
		
		SiteTree site = new SiteTreeImpl(args[0]);
		
		Registry reglocal = LocateRegistry.getRegistry("localhost");
		reglocal.bind("site", site);
		System.out.println("site("+args[0]+") est bind sur le registry a l'adresse ''site''");
	}

}
