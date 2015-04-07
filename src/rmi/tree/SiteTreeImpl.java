package rmi.tree;

import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import rmi.Message;

public class SiteTreeImpl implements SiteTree {

	private SiteTree father;
	private List<SiteTree> sons;
	private String id;

	/**
	 * Constructor
	 */
	public SiteTreeImpl(String id) {
		super();
		this.id = id;
		this.sons = new LinkedList<SiteTree>();
	}

	@Override
	public void sendMessage(Message message) throws RemoteException {
		MessageTree messageTree = (MessageTree) message;

		final Message messageToDiffuse = new MessageTreeImpl(message.getInitiator(), this, message.getContents());

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
		for (final SiteTree neighbor : this.sons) {
			if (!neighbor.equals(messageTree.getSender())) {
				new Runnable() {
					@Override
					public void run() {
						try {
							neighbor.receiveMessage(messageToDiffuse);
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
		String toPrint = "Message from " + message.getInitiator() + " : ";
		toPrint = message.getContents();
		System.out.println(toPrint);
		this.sendMessage(message);

	}

	@Override
	public Message createMessage(String content) throws RemoteException {
		Message message = new MessageTreeImpl(this, this, content);
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
	public String getId() throws RemoteException {
		return this.id;
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

}
