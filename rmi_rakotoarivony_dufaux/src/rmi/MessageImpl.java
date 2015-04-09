package rmi;


/**
 * Is the implementation of the interface MessageTree.
 */
public class MessageImpl implements Message{
	
	private static final long serialVersionUID = -854910097411915160L;
	
	private Site initiator;
	
	private String content;
	
	private long timestamp;
	
	/**
	 * Is the site from which the message have been received
	 */
	private Site sender;
	
	
	/**
	 * Create a graph message.
	 * @param initiator : the initiator of the message
	 * @param content : the content of the message
	 */
	public MessageImpl(Site initiator, Site sender, String content,long timestamp){
		this.initiator = initiator;
		this.content = content;
		this.sender = sender;
		this.timestamp = timestamp;
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
	
	
	@Override
	public boolean equals(Object other){
		if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof MessageImpl))return false;
	    
	    MessageImpl otherMess = (MessageImpl) other;
	    
	    return this.initiator.equals(otherMess.getInitiator()) && this.content.equals(otherMess.getContent()) && this.timestamp==otherMess.timestamp;
	}

	@Override
	public long getTimestamp() {
		return this.timestamp;
	}
	
	
}
