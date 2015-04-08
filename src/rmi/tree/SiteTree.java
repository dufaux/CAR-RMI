package rmi.tree;

import java.rmi.RemoteException;

import rmi.Site;
import rmi.exeption.AlreadyInListException;

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
	 * Sets the sons of this site
	 * @param sons the sons to be added to this site
	 * @throws RemoteException
	 */
	public void setSons(SiteTree... sons ) throws RemoteException;

	/**
	 * Adds a son to this site.
	 * @param siteTree
	 * @throws 
	 */
	public void addSon(SiteTree siteTree) throws RemoteException,AlreadyInListException;
	
	/**
	 * Returns an array containing the list of sons of this site.
	 * @return an array containing the list of sons of this site.The array is empty if it does not have any son
	 */
	public SiteTree[] getSons();
	
	/**
	 * Returns the father of this site.
	 * @return the father of this site. 
	 * 			null if it is the root
	 */
	public Site getFather();

}
