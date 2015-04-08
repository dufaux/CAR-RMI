package rmi.tree;

import rmi.Message;
import rmi.Site;

/**
 * Represents a message in a tree network. 
 * In addition to the initiator and the content, a message in a tree network contains the sender of the message. 
 * When a site receive a message, the sender is the other site from which it received that message.
 * The sender can be get with the method getSender
 * @author rakotoarivony
 * @author dufaux
 */
public interface MessageTree extends Message{
	/**
	 * Returns the site which sent this message
	 * @return
	 */
	public Site getSender();
	
	
	/**
	 * Set the sender of the message
	 * @return
	 */
	public void setSender(Site newsender);
	
}
