package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import rmi.Message;
import rmi.SiteAdministration;
import rmi.SiteAdministrationImpl;
import rmi.tree.SiteTree;
import rmi.tree.SiteTreeImpl;

/**
 * Create a Tree site on the current computer
 *
 */
public class MainCreateTreeSite {

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
			
			
		final SiteTree site = new SiteTreeImpl(hostname+"_"+(nombre+1));
		String rmiadd = "site"+(nombre+1);
		
		reglocal.bind(rmiadd, site);
		adminNumber.increment();
		System.out.println("NEW SITE TREE CREATED ON THIS COMPUTER : "+hostname+" \n" +
				"bound on local registry with this adress : "+rmiadd);
		
		
		new Runnable() {
			@Override
			public void run() {
				 try {
					 InputStreamReader r=new InputStreamReader(System.in);  
					 BufferedReader br=new BufferedReader(r);  
					  
					 String message="";  
					  
					 System.out.println("ok");
					  while(! message.equals("stop")){  
					   System.out.println("Enter message: ");  
					   message=br.readLine();
					   Message m = site.createMessage(message);
					   site.sendMessage(m);
					   System.out.println("You sent : "+message+" to your neighbors");  
					  }  
					  
					 br.close();  
					 r.close(); 
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
			}
		}.run();
	}

}
