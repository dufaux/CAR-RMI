package main;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import rmi.Message;
import rmi.Site;

public class MainSendMessage {

	/**
	 * @param args
	 * @throws RemoteException 
	 * @throws NotBoundException 
	 */
	public static void main(String[] args) throws RemoteException, NotBoundException {
		
		Registry reg = LocateRegistry.getRegistry(args[0]);
		Site site = (Site) reg.lookup("site");
		Message m = site.createMessage("Coucou c'est "+args[0]);
		site.sendMessage(m);

	}

}
