package rmi;

import java.io.Serializable;
/**
 * Represents the message that is exchanged between sites.
 * A message contains a content and have an initiator which can be respectively be get by the methods getContent and getInitiator
 * In addition to the initiator and the content, a message in a tree network contains the sender of the message. 
 * When a site receive a message, the sender is the other site from which it received that message.
 * The sender can be get with the method getSender 
 * @author rakotoarivony
 *
 */
public interface Message extends Serializable {
	/**
	 * Returns the contents of this message
	 * @return
	 */
	public String getContent();
	
	/**
	 * Return the initiator of this message
	 * @return
	 */
	public Site getInitiator();
	
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
