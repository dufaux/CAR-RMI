package rmi.graph;

import rmi.Site;

/**
 * Is the implementation of the interface MessageTree.
 */
public class MessageGraphImpl implements MessageGraph{
	
	private static final long serialVersionUID = -854910097411915160L;
	
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
	public String getContent() {
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
	    
	    return this.initiator.equals(otherMess.getInitiator()) && this.content.equals(otherMess.getContent());
	}
	
	
}
