package rmi.graph;

import java.rmi.RemoteException;

import rmi.Site;

/**
 * Represents a site in a graph network
 * @author rakotoarivony
 * @author dufaux
 */
public interface SiteGraph extends Site{
	/**
	 * Sets the neighbors of this site
	 * @param sites the sites to add as neighbor
	 * @throws RemoteException
	 */
	public void setNeighbor(SiteGraph... sites) throws RemoteException;
	

	/**
	 * Adds a neighbor to a site
	 * @param site the neighbor
	 * @throws RemoteException
	 */
	public void addNeighbor(SiteGraph site) throws RemoteException;
	
	/**
	 * Return an array of neighbors of the site
	 * @return the array of neighbors
	 * @throws RemoteException
	 */
	public SiteGraph[] getNeighbor() throws RemoteException;
}
