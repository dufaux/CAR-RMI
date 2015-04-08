package rmi.tree;

import rmi.Site;

/**
 * Is the implementation of the interface MessageTree.
 * @author rakotoarivony
 *
 */
public class MessageTreeImpl implements MessageTree{

	/**
	 * 
	 */
	private static final long serialVersionUID = -108044260821572554L;
	private Site initiator;
	/**
	 * Is the site from which the message have been received
	 */
	private Site sender;
	private String content;
	
	/**
	 * Create a message
	 * @param initiator : site which initiates the message.
	 * @param sender : site from which this message is sent
	 * @param content : content of the message
	 */
	public MessageTreeImpl(Site initiator, Site sender, String content){
		this.initiator = initiator;
		this.sender = sender;
		this.content = content;
	}
	
	/**
	 * Returns the content of the message.
	 */
	public String getContent() {
		return this.content;
	}

	/**
	 * Returns the initiator of the message.
	 */
	public Site getInitiator() {
		return this.initiator;
	}

	/**
	 * Returns the sender of the message.
	 */
	public Site getSender() {
		return this.sender;
	}

	/**
	 * Changes the sender of the message.
	 */
	public void setSender(Site newSender) {
		this.sender = newSender;
	}
	

}
