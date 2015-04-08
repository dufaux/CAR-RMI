package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import rmi.Message;
import rmi.SiteAdministration;
import rmi.SiteAdministrationImpl;
import rmi.graph.SiteGraph;
import rmi.graph.SiteGraphImpl;

/**
 * Create a Graph site on the current computer
 * 
 */
public class MainCreateGraphSite {

	/**
	 * @param args
	 * @throws RemoteException
	 * @throws AlreadyBoundException
	 * @throws UnknownHostException
	 */
	public static void main(String[] args) throws RemoteException,
			AlreadyBoundException, UnknownHostException {

		InetAddress addr;
		addr = InetAddress.getLocalHost();
		String hostname = addr.getHostName();

		System.setProperty("java.rmi.server.hostname", hostname);
		Registry reglocal = LocateRegistry.getRegistry("localhost");

		SiteAdministration adminNumber;
		try {
			adminNumber = (SiteAdministration) reglocal.lookup("numberofsites");
		} catch (NotBoundException e) {
			adminNumber = new SiteAdministrationImpl();
			reglocal.bind("numberofsites", adminNumber);
		}

		int nombre = adminNumber.getNumberOfSites();

		final SiteGraph site = new SiteGraphImpl(hostname + "_" + (nombre + 1));
		String rmiadd = "site" + (nombre + 1);

		reglocal.bind(rmiadd, site);
		adminNumber.increment();
		System.out.println("NEW SITE GRAPH CREATED ON THIS COMPUTER : "
				+ hostname + " \n"
				+ "bound on local registry with this adress : " + rmiadd);

		new Runnable() {
			@Override
			public void run() {
				try {
					InputStreamReader r = new InputStreamReader(System.in);
					BufferedReader br = new BufferedReader(r);

					String message = "";

					System.out.println("ok");
					while (!message.equals("stop")) {
						System.out.println("Enter message: ");
						message = br.readLine();
						Message m = site.createMessage(message);
						site.sendMessage(m);
						System.out.println("You sent : " + message
								+ " to your neighbors");
					}

					br.close();
					r.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}.run();

	}
}