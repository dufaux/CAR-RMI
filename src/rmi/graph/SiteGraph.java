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
	
	public SiteGraph[] getNeighbor() throws RemoteException;
}
