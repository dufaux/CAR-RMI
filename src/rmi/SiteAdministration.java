package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SiteAdministration extends Remote{

	public int getNumberOfSites()  throws RemoteException;
	
	public void increment()  throws RemoteException;
	
	public void decrement()  throws RemoteException;
}
