package rmi;

import java.io.Serializable;
/**
 * Represents the message that is exchanged between sites.
 * A message contains a content and have an initiator which can be respectively be get by the methods getContent and getInitiator 
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
}
