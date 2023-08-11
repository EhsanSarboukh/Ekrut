package entity;

import java.io.Serializable;

public class Message implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Role role;
	private MessageType msgType;
	private Object msgData;
	
	public Message(MessageType messageType, Object messageData) {
	    msgType = messageType;
	    msgData = messageData;
	  }
	
	public Message(Role role,Object messageData) {
		this.role=role;
		msgData=messageData;
	}
	  
	  public MessageType getMessageType() {
	    return msgType;
	  }
	  
	  public Object getMessageData() {
	    return msgData;
	  }
	  
	  public String toString() {
	    return "MESSAGE";
	  }

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
