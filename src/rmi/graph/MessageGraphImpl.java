package rmi.graph;

import rmi.Message;
import rmi.Site;

/**
 * Represent a message with an iniator and a content.
 */
public class MessageGraphImpl implements MessageGraph{
	
	private Site initiator;
	private String content;
	
	/**
	 * Create a graph message.
	 * @param initiator : the initiator of the message
	 * @param content : the content of the message
	 */
	public MessageGraphImpl(Site initiator, String content){
		this.initiator = initiator;
		this.content = content;
	}

	/**
	 * return the content of the message
	 */
	public String getContents() {
		return this.content;
	}

	/**
	 * return the initiator of the message
	 */
	public Site getInitiator() {
		return this.initiator;
	}

	@Override
	public boolean equals(Object other){
		if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof MessageGraphImpl))return false;
	    
	    MessageGraphImpl otherMess = (MessageGraphImpl) other;
	    
	    return this.initiator.equals(otherMess.getInitiator()) && this.content.equals(otherMess.getContents());
	}
	
	
}
