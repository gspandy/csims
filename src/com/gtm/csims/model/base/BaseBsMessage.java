package com.gtm.csims.model.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the bs_message table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="bs_message"
 */

public abstract class BaseBsMessage  implements Serializable {

	public static String REF = "BsMessage";
	public static String PROP_RECEIVER = "Receiver";
	public static String PROP_FLAG = "Flag";
	public static String PROP_STATUS = "Status";
	public static String PROP_RECEIVEDATE = "Receivedate";
	public static String PROP_SENDER = "Sender";
	public static String PROP_MESSAGE = "Message";
	public static String PROP_CATEGORY = "Category";
	public static String PROP_TITLE = "Title";
	public static String PROP_CREATEDATE = "Createdate";
	public static String PROP_ID = "Id";


	// constructors
	public BaseBsMessage () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseBsMessage (java.lang.String id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String id;

	// fields
	private java.lang.String title;
	private java.lang.String message;
	private java.lang.String receiver;
	private java.lang.String sender;
	private java.util.Date createdate;
	private java.lang.String status;
	private java.lang.String flag;
	private java.lang.String category;
	private java.util.Date receivedate;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="org.hibernate.id.UUIDHexGenerator"
     *  column="id"
     */
	public java.lang.String getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (java.lang.String id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: title
	 */
	public java.lang.String getTitle () {
		return title;
	}

	/**
	 * Set the value related to the column: title
	 * @param title the title value
	 */
	public void setTitle (java.lang.String title) {
		this.title = title;
	}



	/**
	 * Return the value associated with the column: message
	 */
	public java.lang.String getMessage () {
		return message;
	}

	/**
	 * Set the value related to the column: message
	 * @param message the message value
	 */
	public void setMessage (java.lang.String message) {
		this.message = message;
	}



	/**
	 * Return the value associated with the column: receiver
	 */
	public java.lang.String getReceiver () {
		return receiver;
	}

	/**
	 * Set the value related to the column: receiver
	 * @param receiver the receiver value
	 */
	public void setReceiver (java.lang.String receiver) {
		this.receiver = receiver;
	}



	/**
	 * Return the value associated with the column: sender
	 */
	public java.lang.String getSender () {
		return sender;
	}

	/**
	 * Set the value related to the column: sender
	 * @param sender the sender value
	 */
	public void setSender (java.lang.String sender) {
		this.sender = sender;
	}

	public java.util.Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(java.util.Date createdate) {
		this.createdate = createdate;
	}

	public java.lang.String getStatus() {
		return status;
	}

	public void setStatus(java.lang.String status) {
		this.status = status;
	}

	public java.util.Date getReceivedate() {
		return receivedate;
	}

	public void setReceivedate(java.util.Date receivedate) {
		this.receivedate = receivedate;
	}

	/**
	 * Return the value associated with the column: flag
	 */
	public java.lang.String getFlag () {
		return flag;
	}

	/**
	 * Set the value related to the column: flag
	 * @param flag the flag value
	 */
	public void setFlag (java.lang.String flag) {
		this.flag = flag;
	}



	/**
	 * Return the value associated with the column: category
	 */
	public java.lang.String getCategory () {
		return category;
	}

	/**
	 * Set the value related to the column: category
	 * @param category the category value
	 */
	public void setCategory (java.lang.String category) {
		this.category = category;
	}





	@Override
	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.gtm.csims.model.BsMessage)) return false;
		else {
			com.gtm.csims.model.BsMessage bsMessage = (com.gtm.csims.model.BsMessage) obj;
			if (null == this.getId() || null == bsMessage.getId()) return false;
			else return (this.getId().equals(bsMessage.getId()));
		}
	}

	@Override
	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getId()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}


	@Override
	public String toString () {
		return super.toString();
	}


}