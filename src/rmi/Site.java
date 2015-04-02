package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Site extends Remote{
	
	public void setVoisin(Site site) throws RemoteException;
	
	public void sendMessage(String string) throws RemoteException;
	
	public void sendMessage(Message message) throws RemoteException;

}
