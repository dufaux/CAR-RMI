package rmi.graph;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import rmi.Message;

public class SiteGraphImpl extends UnicastRemoteObject implements SiteGraph{


	protected SiteGraphImpl() throws RemoteException {
		super();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 7532958343594021652L;

	@Override
	public void sendMessage(Message message) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setNeighbor(SiteGraph... site) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void receiveMessage(Message message) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Message createMessage(String message) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getId() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	
}
