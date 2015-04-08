package main;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.ConnectException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import rmi.SiteAdministration;
import rmi.SiteAdministrationImpl;
import rmi.tree.SiteTree;
import rmi.tree.SiteTreeImpl;

public class MainCreateSiteTree {

	/**
	 * @param args
	 * @throws RemoteException 
	 * @throws AlreadyBoundException 
	 * @throws UnknownHostException 
	 * @throws MalformedURLException 
	 */
	public static void main(String[] args) throws UnknownHostException, RemoteException, AlreadyBoundException, MalformedURLException {
		InetAddress addr;
		addr = InetAddress.getLocalHost();
		String hostname = addr.getHostName();
		    
		System.setProperty("java.rmi.server.hostname",hostname);
		Registry reglocal = LocateRegistry.getRegistry("localhost");
		
		SiteAdministration adminNumber;
		try {
			adminNumber = (SiteAdministration) reglocal.lookup("numberofsites");
		}catch (NotBoundException e) {
			adminNumber = new SiteAdministrationImpl();
			reglocal.bind("numberofsites", adminNumber);
		}
		
		int nombre = adminNumber.getNumberOfSites();
			
			
		SiteTree site = new SiteTreeImpl(hostname+" "+(nombre+1));
		String rmiadd = "site"+(nombre+1);
		
		reglocal.bind(rmiadd, site);
		adminNumber.increment();
		System.out.println("NEW SITE TREE CREATED ON THIS COMPUTER : "+hostname+" \n" +
				"bound on local registry with this adress : "+rmiadd);
	}

}
