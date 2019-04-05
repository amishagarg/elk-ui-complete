package io.elk.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="MODIFIEDBIZDOC")
@JsonIgnoreProperties(ignoreUnknown=true)
public class ModifiedBizdoc implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="INTERNALID",columnDefinition="CHAR", unique=true, nullable=false, length=24)
	 private String internalid;
	
    @Column(name="TIMESTAMP", nullable = false, length = 3)
	private Timestamp timestamp;
    
    @Column(name="MODIFIED", nullable = true, length = 3)
	private Timestamp modified;
    
    @Column(name="DOCUMENTID", nullable = true, length = 256)
	private String documentid;
    
    @Column(name="SENDERID",columnDefinition="CHAR", nullable = false, length = 24)
	private String senderid;
    
    @Column(name="SENDER", nullable = false, length = 255)
	private String sender;
    
    @Column(name="SENDERORG", nullable = true, length = 255)
	private String senderorg;
    
    @Column(name="RECEIVERID",columnDefinition="CHAR", nullable = false, length = 24)
	private String receiverid;
    
    @Column(name="RECEIVER", nullable = false, length = 255)
	private String receiver;
    
    @Column(name="RECEIVERORG", nullable = true, length = 255)
	private String receiverorg;
    
    @Column(name="DOCUMENTTYPE", nullable = false, length = 128)
	private String documenttype;
    
    @Column(name="SYSTEMSTATUS", nullable = false, length = 30)
	private String systemstatus;
    
    @Column(name="USERSTATUS", nullable = true, length = 255)
   	private String userstatus;

	public String getInternalid() {
		return internalid;
	}

	public void setInternalid(String internalid) {
		this.internalid = internalid;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public Timestamp getModified() {
		return modified;
	}

	public void setModified(Timestamp modified) {
		this.modified = modified;
	}

	public String getDocumentid() {
		return documentid;
	}

	public void setDocumentid(String documentid) {
		this.documentid = documentid;
	}

	public String getSenderid() {
		return senderid;
	}

	public void setSenderid(String senderid) {
		this.senderid = senderid;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getSenderorg() {
		return senderorg;
	}

	public void setSenderorg(String senderorg) {
		this.senderorg = senderorg;
	}

	public String getReceiverid() {
		return receiverid;
	}

	public void setReceiverid(String receiverid) {
		this.receiverid = receiverid;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getReceiverorg() {
		return receiverorg;
	}

	public void setReceiverorg(String receiverorg) {
		this.receiverorg = receiverorg;
	}

	public String getDocumenttype() {
		return documenttype;
	}

	public void setDocumenttype(String documenttype) {
		this.documenttype = documenttype;
	}

	public String getSystemstatus() {
		return systemstatus;
	}

	public void setSystemstatus(String systemstatus) {
		this.systemstatus = systemstatus;
	}

	public String getUserstatus() {
		return userstatus;
	}

	public void setUserstatus(String userstatus) {
		this.userstatus = userstatus;
	}
    
    public ModifiedBizdoc()
    {
    	
    }

	public ModifiedBizdoc(String internalid, Timestamp timestamp, Timestamp modified, String documentid,
			String senderid, String sender, String senderorg, String recieverid, String reciever, String recieverorg,
			String documenttype, String systemstatus, String userstatus) {
		super();
		this.internalid = internalid;
		this.timestamp = timestamp;
		this.modified = modified;
		this.documentid = documentid;
		this.senderid = senderid;
		this.sender = sender;
		this.senderorg = senderorg;
		this.receiverid = recieverid;
		this.receiver = reciever;
		this.receiverorg = recieverorg;
		this.documenttype = documenttype;
		this.systemstatus = systemstatus;
		this.userstatus = userstatus;
	}
    
	

}
