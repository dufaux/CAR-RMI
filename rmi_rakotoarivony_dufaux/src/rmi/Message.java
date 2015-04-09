package rmi;

import java.io.Serializable;
/**
 * Represents the message that is exchanged between sites.
 * A message contains a content and have an initiator which can be respectively be get by the methods getContent and getInitiator
 * In addition to the initiator and the content, a message in a tree network contains the sender of the message and the timestamp of its creation which can be obtained by getTimestamp
 * When a site receive a message, the sender is the other site from which it received that message.
 * The sender can be get with the method getSender 
 * @author rakotoarivony
 *
 */
public interface Message extends Serializable {
	/**
	 * Returns the contents of this message
	 * @return the content of the message
	 */
	public String getContent();
	
	/**
	 * Return the initiator of this message
	 * @return the initiator of the message
	 */
	public Site getInitiator();
	
	/**
	 * Returns the site which sent this message
	 * @return the site which sent the message
	 */
	public Site getSender();
	
	/**
	 * Retrurns the time of the creation of this message
	 * @return the time of the creation of this message
	 */
	public long getTimestamp();
	
	
	/**
	 * Set the sender of the message
	 */
	public void setSender(Site newsender);
}
