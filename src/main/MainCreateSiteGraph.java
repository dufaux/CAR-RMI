package main;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import rmi.graph.SiteGraph;
import rmi.graph.SiteGraphImpl;

public class MainCreateSiteGraph {

	/**
	 * @param args
	 * @throws RemoteException 
	 * @throws AlreadyBoundException 
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws RemoteException, AlreadyBoundException, UnknownHostException {
		
		InetAddress addr;
	    addr = InetAddress.getLocalHost();
	    String hostname = addr.getHostName();
	    
		System.setProperty("java.rmi.server.hostname",hostname);
		LocateRegistry.createRegistry(1099);
		
		
		SiteGraph site = new SiteGraphImpl(hostname);
		String rmiadd = "site";
		
		Registry reglocal = LocateRegistry.getRegistry("localhost");
		reglocal.bind(rmiadd, site);
		System.out.println("NEW SITE CREATED ON THIS COMPUTER : "+hostname+" \n" +
				"bound on local registry with this adress : "+rmiadd);
	}


}
