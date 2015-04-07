package rmi.tree;

import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import rmi.Message;
import rmi.Site;

public class SiteTreeImpl implements SiteTree{
	
	private SiteTree father;
	private List<SiteTree> sons;
	private String id;
	
	
	/**
	 *  Constructor
	 */
	public SiteTreeImpl(String id){
		super();
		this.id = id;
		this.sons = new LinkedList<SiteTree>();
	}

	@Override
	public void sendMessage(Message message) throws RemoteException {
		final MessageTree messageTree = (MessageTree) message;
		String toPrint = "Message from " + messageTree.getInitiator()+" : ";
		toPrint = messageTree.getContents();
		System.out.println(toPrint);
		//envoyer à tout ses fils et son père sauf le sender du message en thread pour la concurrence
		if(this.father != null && !this.father.equals(messageTree.getSender())){
			new Runnable() {					
				@Override
				public void run() {
					try {
						father.sendMessage(messageTree);
					} catch (RemoteException e) {
						System.out.println(e.getMessage());
					}
				}
			}.run();
		}
		for(final SiteTree neighbor : this.sons){
			if(!neighbor.equals(messageTree.getSender())){
				new Runnable() {					
					@Override
					public void run() {
						try {
							neighbor.sendMessage(messageTree);
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
		String toPrint = "Message from " + message.getInitiator()+" : ";
		toPrint = message.getContents();
		System.out.println(toPrint);
		this.sendMessage(message);
		
	}

	@Override
	public Message createMessage(String message) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setFather(SiteTree site) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSons(SiteTree... sons) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getId() throws RemoteException {
		return this.id;
	}
	
	@Override
	public boolean equals(Object o){
		if(o==null) return false;
		if(!(o instanceof SiteTreeImpl)) return false;
		SiteTreeImpl tree = (SiteTreeImpl) o;
		return(this.id.equals(tree.id));
	}

	
}
