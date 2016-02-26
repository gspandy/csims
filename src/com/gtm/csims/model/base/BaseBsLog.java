package com.gtm.csims.model.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the bs_log table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="bs_log"
 */

public abstract class BaseBsLog  implements Serializable {

	public static String REF = "BsLog";
	public static String PROP_MESSAGE = "Message";
	public static String PROP_PROCESSER = "Processer";
	public static String PROP_CATEGORY = "Category";
	public static String PROP_PRIORITY = "Priority";
	public static String PROP_CREATEDATE = "Createdate";
	public static String PROP_ID = "Id";
	public static String PROP_THREAD = "Thread";


	// constructors
	public BaseBsLog () {
		initialize();
	}
	
	/**
	 * Constructor for required fields
	 */
	public BaseBsLog (java.lang.String id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String id;

	// fields
	private java.lang.String message;
	private java.util.Date createdate;
	private java.lang.String priority;
	private java.lang.String category;
	private java.lang.String thread;
	private java.lang.String processer;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="org.hibernate.id.IncrementGenerator"
     *  column="Id"
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
	 * Return the value associated with the column: Message
	 */
	public java.lang.String getMessage () {
		return message;
	}

	/**
	 * Set the value related to the column: Message
	 * @param message the Message value
	 */
	public void setMessage (java.lang.String message) {
		this.message = message;
	}



	/**
	 * Return the value associated with the column: CreateDate
	 */

	public java.util.Date getCreatedate() {
		return createdate;
	}

	/**
	 * Set the value related to the column: CreateDate
	 * @param createDate the CreateDate value
	 */
	

	public void setCreatedate(java.util.Date createdate) {
		this.createdate = createdate;
	}


	/**
	 * Return the value associated with the column: Priority
	 */
	public java.lang.String getPriority () {
		return priority;
	}

	/**
	 * Set the value related to the column: Priority
	 * @param priority the Priority value
	 */
	public void setPriority (java.lang.String priority) {
		this.priority = priority;
	}



	/**
	 * Return the value associated with the column: Category
	 */
	public java.lang.String getCategory () {
		return category;
	}

	/**
	 * Set the value related to the column: Category
	 * @param category the Category value
	 */
	public void setCategory (java.lang.String category) {
		this.category = category;
	}



	/**
	 * Return the value associated with the column: Thread
	 */
	public java.lang.String getThread () {
		return thread;
	}

	/**
	 * Set the value related to the column: Thread
	 * @param thread the Thread value
	 */
	public void setThread (java.lang.String thread) {
		this.thread = thread;
	}

	public java.lang.String getProcesser() {
		return processer;
	}

	public void setProcesser(java.lang.String processer) {
		this.processer = processer;
	}

	@Override
	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.gtm.csims.model.BsLog)) return false;
		else {
			com.gtm.csims.model.BsLog bsLog = (com.gtm.csims.model.BsLog) obj;
			if (null == this.getId() || null == bsLog.getId()) return false;
			else return (this.getId().equals(bsLog.getId()));
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