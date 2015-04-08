package main;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import rmi.tree.SiteTree;
import rmi.tree.SiteTreeImpl;

public class MainCreateSiteTree {

	/**
	 * @param args
	 * @throws RemoteException 
	 * @throws AlreadyBoundException 
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args){

		InetAddress addr;
	    try {
			addr = InetAddress.getLocalHost();
			String hostname = addr.getHostName();
		    
			System.setProperty("java.rmi.server.hostname",hostname);
			LocateRegistry.createRegistry(1099);
			
			SiteTree site = new SiteTreeImpl(hostname);
			String rmiadd = "site";
			
			Registry reglocal = LocateRegistry.getRegistry("localhost");
			reglocal.bind(rmiadd, site);
			System.out.println("NEW SITE CREATED ON THIS COMPUTER : "+hostname+" \n" +
					"bound on local registry with this adress : "+rmiadd);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}

}
