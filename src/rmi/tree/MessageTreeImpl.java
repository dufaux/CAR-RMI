package rmi.tree;

import rmi.Site;

/**
 * Represent a message with an iniator, a sender and a content.
 */
public class MessageTreeImpl implements MessageTree{

	/**
	 * 
	 */
	private static final long serialVersionUID = -108044260821572554L;
	private Site initiator;
	private Site sender;
	private String content;
	
	/**
	 * Create a message
	 * @param initiator : site which initiate the message.
	 * @param sender : site which send the message to another site.
	 * @param content : the content of the message
	 */
	public MessageTreeImpl(Site initiator, Site sender, String content){
		this.initiator = initiator;
		this.sender = sender;
		this.content = content;
	}
	
	/**
	 * return the content of the message.
	 */
	public String getContents() {
		return this.content;
	}

	/**
	 * return the initiator of the message.
	 */
	public Site getInitiator() {
		return this.initiator;
	}

	/**
	 * return the sender of the message.
	 */
	public Site getSender() {
		return this.sender;
	}

	/**
	 * change the sender of the message.
	 */
	public void setSender(Site newsender) {
		this.sender = newsender;
	}
	

}
