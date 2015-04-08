package rmi.tree;

import java.rmi.RemoteException;

import rmi.Site;

/**
 * Represents a site in a tree network
 * @author rakotoarivony
 * @author dufaux
 */
public interface SiteTree extends Site{
	
	/**
	 * Set the site which is the father of this site
	 * @param site
	 * @throws RemoteException
	 */
	public void setFather(SiteTree site) throws RemoteException;
	
	/**
	 * 
	 * @param sons
	 * @throws RemoteException
	 */
	public void setSons(SiteTree... sons ) throws RemoteException;

	/**
	 * Adds a son to this site
	 * @param siteTree
	 */
	public void addSon(SiteTree siteTree) throws RemoteException;

}
