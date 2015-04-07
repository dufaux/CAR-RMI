package main;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import rmi.Site;
import rmi.tree.SiteTreeImpl;

public class mainCreateSite {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//System.out.println(args[0]);
		System.setProperty("java.rmi.server.hostname",args[0]);
		
		SiteTree site = new SiteTreeImpl(args[0]);
		
		Registry reglocal = LocateRegistry.getRegistry("localhost");
		reglocal.bind("site", site);
		System.out.println("site("+args[0]+") est bind sur le registry a l'adresse ''site''");
	}

}
