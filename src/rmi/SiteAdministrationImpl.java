package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SiteAdministrationImpl extends UnicastRemoteObject implements SiteAdministration{
	
	int number;
	
	public SiteAdministrationImpl() throws RemoteException{
		number = 0;
	}
	
	public int getNumberOfSites()  throws RemoteException{
		return this.number;
	}
	
	
	public void increment()  throws RemoteException{
		this.number++;
	}
	
	public void decrement()  throws RemoteException{
		this.number--;
	}
}
