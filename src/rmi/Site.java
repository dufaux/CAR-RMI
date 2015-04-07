package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Site extends Remote {
	/**
	 * Print the received message and send it to its surrounding.
	 * @param message
	 */
	public void receiveMessage(Message message) throws RemoteException;
	
	/**
	 * 
	 * @param message
	 */
	public void sendMessage(Message message) throws RemoteException;

	/**
	 * Creates a message
	 * @param message
	 */
	public Message createMessage(String message) throws RemoteException;
}
