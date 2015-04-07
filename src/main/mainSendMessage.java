package main;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import rmi.Site;
import rmi.tree.MessageTree;
import rmi.tree.MessageTreeImpl;

public class mainSendMessage {

	/**
	 * @param args
	 * @throws RemoteException 
	 * @throws NotBoundException 
	 */
	public static void main(String[] args) throws RemoteException, NotBoundException {
		
		Registry reg = LocateRegistry.getRegistry(args[0]);
		Site site = (Site) reg.lookup("site");
		MessageTree m = new MessageTreeImpl(site, site, "Coucou c'est "+args[0]);
		site.sendMessage(m);

	}

}
