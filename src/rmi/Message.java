package rmi;

import java.io.Serializable;

public interface Message extends Serializable {
	/**
	 * Returns the contents of this message
	 * @return
	 */
	public String getContents();
	
	/**
	 * Return the initiator of this message
	 * @return
	 */
	public Site getInitiator();
}
