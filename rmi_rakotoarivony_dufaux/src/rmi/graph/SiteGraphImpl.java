package rmi.graph;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import rmi.Message;
import rmi.MessageImpl;

/**
 * Is the implementation of the interface SiteGraph
 * @author rakotoarivony
 * @author dufaux
 *
 */
public class SiteGraphImpl extends UnicastRemoteObject implements SiteGraph{

	private List<SiteGraph> neighbors;
	private static final long serialVersionUID = 7532958343594021652L;
	private List<Message> history;
	protected String id;
	protected Message lastMessage;
	
	/**
	 * constructor of SiteGraphImpl
	 * @param id : the id of the site
	 * @throws RemoteException
	 */
	public SiteGraphImpl(String id) throws RemoteException {
		super();
		this.id = id;
		this.neighbors = new LinkedList<SiteGraph>();
		this.history = new LinkedList<Message>();
	}

	@Override
	public void sendMessage(final Message message) throws RemoteException {
		for (final SiteGraph neighbor : this.neighbors) {
			new Runnable() {
				@Override
				public void run() {
					try {
						neighbor.receiveMessage(message);
					} catch (RemoteException e) {
						throw new RuntimeException(e);
					}
				}
			}.run();
		}
	}

	@Override
	public void setNeighbor(SiteGraph... sites) throws RemoteException {
		this.neighbors = new LinkedList<SiteGraph>(Arrays.asList(sites));
	}

	@Override
	public void receiveMessage(Message message) throws RemoteException {		
		if(!this.messageAlreadyReceived(message) && !this.equals(message.getInitiator()) ){
			this.lastMessage = message;
			this.addMessageInHistory(message);
			String toPrint = message.getInitiator().getId() +" : ";
			toPrint += message.getContent();
			System.out.println(toPrint);
			this.sendMessage(message);
		}
	}

	@Override
	public Message createMessage(String content) throws RemoteException {
		Message message = new MessageImpl(this,this, content,System.currentTimeMillis());
		this.addMessageInHistory(message);
		return message;
	}


	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (!(o instanceof SiteGraphImpl))
			return false;
		SiteGraphImpl siteg = (SiteGraphImpl) o;
		return (this.id.equals(siteg.id));
	}
	
	@Override
	public String getId() throws RemoteException {
		return this.id;
	}

	@Override
	public Message getLastMessage() throws RemoteException {
		return this.lastMessage;
	}
	
	@Override
	public String toString(){
		return this.id;
	}

	@Override
	public SiteGraph[] getNeighbor() throws RemoteException {
		return this.neighbors.toArray(new SiteGraph[this.neighbors.size()]);
	}

	@Override
	public void addNeighbor(SiteGraph site) throws RemoteException {
		this.neighbors.add(site);
	}
	
	synchronized private boolean messageAlreadyReceived(Message message){
		return this.history.contains(message);
	}
	
	synchronized private void addMessageInHistory(Message message){
		this.history.add(message);
	}
}
