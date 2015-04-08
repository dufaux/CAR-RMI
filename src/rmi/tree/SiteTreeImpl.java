package rmi.tree;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import rmi.Message;
import rmi.MessageImpl;
import rmi.Site;

/**
 * Is the implementation of the interface SiteTree
 * @author rakotoarivony
 * @author dufaux
 *
 */
public class SiteTreeImpl extends UnicastRemoteObject implements SiteTree {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6733433430886258854L;
	
	private SiteTree father;
	private List<SiteTree> sons;
	protected String id;
	protected Message lastMessage;
	
	/**
	 * Constructor
	 * @throws RemoteException 
	 */
	public SiteTreeImpl(String id) throws RemoteException {
		super();
		this.id = id;
		this.sons = new LinkedList<SiteTree>();
	}

	@Override
	public void sendMessage(Message message) throws RemoteException {
		Message messageTree = message;

		final Message messageToDiffuse = new MessageImpl(message.getInitiator(), this, message.getContent());

		if (this.father != null && !this.father.equals(messageTree.getSender())) {
			new Runnable() {
				@Override
				public void run() {
					try {
						father.receiveMessage(messageToDiffuse);
					} catch (RemoteException e) {
						System.out.println(e.getMessage());
					}
				}
			}.run();
		}
		for (final SiteTree son : this.sons) {
			if (!son.equals(messageTree.getSender())) {
				new Runnable() {
					@Override
					public void run() {
						try {
							son.receiveMessage(messageToDiffuse);
						} catch (RemoteException e) {
							System.out.println(e.getMessage());
						}
					}
				}.run();
			}
		}

	}

	@Override
	public void receiveMessage(Message message) throws RemoteException {
		this.lastMessage = message;
		String toPrint = "Message initiated by " + message.getInitiator().getId() + " and sent by "+message.getSender()+" : ";
		toPrint += message.getContent();
		System.out.println(toPrint);
		this.sendMessage(message);

	}

	@Override
	public Message createMessage(String content) throws RemoteException {
		Message message = new MessageImpl(this, this, content);
		return message;
	}

	@Override
	public void setFather(SiteTree site) throws RemoteException {
		this.father = site;
	}

	@Override
	public void setSons(SiteTree... sons) throws RemoteException {
		this.sons = Arrays.asList(sons);
	}

	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (!(o instanceof SiteTreeImpl))
			return false;
		SiteTreeImpl tree = (SiteTreeImpl) o;
		return (this.id.equals(tree.id));
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
	public void addSon(SiteTree siteTree) throws RemoteException {
		if(!this.sons.contains(siteTree))
			this.sons.add(siteTree);		
	}
	
	@Override
	public String toString(){
		return this.id;
	}

	@Override
	public SiteTree[] getSons() {
		return this.sons.toArray(new SiteTree[this.sons.size()]);
	}

	@Override
	public Site getFather() {
		return this.father;
	}
}
