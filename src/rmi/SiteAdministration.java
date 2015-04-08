package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Represent a objet on each computer to manage the number of site by computer.
 */
public interface SiteAdministration extends Remote{

	/**
	 * return the number of site
	 * @return : the number of site on this computer
	 * @throws RemoteException
	 */
	public int getNumberOfSites()  throws RemoteException;
	
	/**
	 * increment the number of site
	 * @throws RemoteException
	 */
	public void increment()  throws RemoteException;
	
	/**
	 * decrement the number of site
	 * @throws RemoteException
	 */
	public void decrement()  throws RemoteException;
}
