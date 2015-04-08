package rmi.graph;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import rmi.Message;
import rmi.tree.MessageTreeImpl;
import rmi.tree.SiteTree;

public class SiteGraphImpl extends UnicastRemoteObject implements SiteGraph{

	private String id;
	private List<SiteGraph> neighbors;
	private static final long serialVersionUID = 7532958343594021652L;
	private List<MessageGraph> history;
	
	/**
	 * constructor of SiteGraphImpl
	 * @param id : the id of the site
	 * @throws RemoteException
	 */
	public SiteGraphImpl(String id) throws RemoteException {
		super();
		this.id = id;
		this.neighbors = new LinkedList<SiteGraph>();
		this.history = new LinkedList<MessageGraph>();
	}


	public void sendMessage(final Message message) throws RemoteException {
		for (final SiteGraph neighbor : this.neighbors) {
			new Runnable() {
				@Override
				public void run() {
					try {
						neighbor.receiveMessage(message);
					} catch (RemoteException e) {
						System.out.println(e.getMessage());
					}
				}
			}.run();
		}
	}

	@Override
	public void setNeighbor(SiteGraph... sites) throws RemoteException {
		this.neighbors = Arrays.asList(sites);
	}

	@Override
	public void receiveMessage(Message message) throws RemoteException {
		if(this.history.contains(message) || this.equals(message.getInitiator()) ){
			//whattodo?
		}
		else{
			this.history.add((MessageGraph) message);
			String toPrint = "Message from " + message.getInitiator() + " : ";
			toPrint = message.getContents();
			System.out.println(toPrint);
			this.sendMessage(message);
		}
	}

	@Override
	public Message createMessage(String content) throws RemoteException {
		Message message = new MessageGraphImpl(this, content);
		return message;
	}


	@Override
	public String getId() throws RemoteException {
		return this.id;
	}

	
}
