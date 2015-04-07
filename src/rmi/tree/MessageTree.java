package rmi.tree;

import rmi.Message;
import rmi.Site;

public interface MessageTree extends Message{
	/**
	 * Returns the site which sent this message
	 * @return
	 */
	public Site getSender();
}
