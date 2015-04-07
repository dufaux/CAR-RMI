package rmi.graph;

import java.rmi.RemoteException;

import rmi.Site;

public interface SiteGraph extends Site{
	/**
	 * Sets the neighbor 
	 * @param site
	 * @throws RemoteException
	 */
	public void setNeighbor(SiteGraph... site) throws RemoteException;
}
