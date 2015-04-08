package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Is the implementation of SiteAdministration
 * contain a number which can be increment or decrement and provide the number
 * of site on the same computer (rmiregistry)
 */
public class SiteAdministrationImpl extends UnicastRemoteObject implements SiteAdministration{
	

	private static final long serialVersionUID = -4229306066238959896L;
	
	int number;
	
	/**
	 * Constructor of SiteAdministrationImpl
	 * initialize the number at 0
	 * @throws RemoteException
	 */
	public SiteAdministrationImpl() throws RemoteException{
		number = 0;
	}
	
	@Override
	public int getNumberOfSites()  throws RemoteException{
		return this.number;
	}
	
	@Override
	public void increment()  throws RemoteException{
		this.number++;
	}
	
	@Override
	public void decrement()  throws RemoteException{
		this.number--;
	}
}
