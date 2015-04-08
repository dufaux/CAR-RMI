package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Represents a node of network
 *
 */
public interface Site extends Remote {
	/**
	 * Print the received message
	 * 
	 * @param message
	 */
	public void receiveMessage(Message message) throws RemoteException;

	/**
	 * Sends a message to all the surrounding
	 * @param message
	 * @throws RemoteException
	 */
	public void sendMessage(Message message) throws RemoteException;

	/**
	 * Creates a message
	 * 
	 * @param message
	 * @throws RemoteException
	 */
	public Message createMessage(String message) throws RemoteException;

	/**
	 * Returns the identification of this site
	 * 
	 * @return the identifiant of the site
	 * @throws RemoteException
	 */
	public String getId() throws RemoteException;
	
	/**
	 * Returns the last message that this site has received.
	 * @return the last received message, null if there isn't yet a received message
	 * @throws RemoteException
	 */
	public Message getLastMessage() throws RemoteException;
}
