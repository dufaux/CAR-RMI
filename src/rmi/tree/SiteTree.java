package rmi.tree;

import java.rmi.RemoteException;

import rmi.Site;

public interface SiteTree extends Site{
	
	public void setFather(SiteTree site) throws RemoteException;
	
	public void setSons(SiteTree... sons ) throws RemoteException;
}
