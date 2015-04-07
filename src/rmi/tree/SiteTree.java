package rmi.tree;

import java.rmi.RemoteException;

import rmi.Site;

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
}
