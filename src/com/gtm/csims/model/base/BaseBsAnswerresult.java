package com.gtm.csims.model.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the BS_ANSWERRESULT table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="BS_ANSWERRESULT"
 */

public abstract class BaseBsAnswerresult  implements Serializable {

	public static String REF = "BsAnswerresult";
	public static String PROP_UPDATEATE = "Updateate";
	public static String PROP_AN_B = "AnB";
	public static String PROP_STAT = "Stat";
	public static String PROP_ARQUESTIONID = "Arquestionid";
	public static String PROP_ARORG = "Arorg";
	public static String PROP_ARORGNO = "Arorgno";
	public static String PROP_AN_G = "AnG";
	public static String PROP_CREATEDATE = "Createdate";
	public static String PROP_ARAREANO = "Arareano";
	public static String PROP_ARORGTYPENO = "Arorgtypeno";
	public static String PROP_AN_C = "AnC";
	public static String PROP_AN_F = "AnF";
	public static String PROP_ARORGTYPE = "Arorgtype";
	public static String PROP_FLAG = "Flag";
	public static String PROP_AN_D = "AnD";
	public static String PROP_ARQUESTIONAIREID = "Arquestionaireid";
	public static String PROP_ARCREATOR = "Arcreator";
	public static String PROP_ARCRTDATE = "Arcrtdate";
	public static String PROP_ARAREA = "Ararea";
	public static String PROP_AN_E = "AnE";
	public static String PROP_ID = "Id";
	public static String PROP_AN_A = "AnA";


	// constructors
	public BaseBsAnswerresult () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseBsAnswerresult (java.lang.String id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String id;

	// fields
	private java.lang.String anA;
	private java.lang.String anB;
	private java.lang.String anC;
	private java.lang.String anD;
	private java.lang.String anE;
	private java.lang.String anF;
	private java.lang.String anG;
	private java.lang.String ararea;
	private java.lang.String arareano;
	private java.lang.String arcreator;
	private java.util.Date arcrtdate;
	private java.lang.String arorg;
	private java.lang.String arorgno;
	private java.lang.String arorgtype;
	private java.lang.String arorgtypeno;
	private java.lang.String arquestionaireid;
	private java.lang.String arquestionid;
	private java.util.Date createdate;
	private java.lang.String flag;
	private java.lang.String stat;
	private java.util.Date updateate;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="org.hibernate.id.UUIDHexGenerator"
     *  column="ID"
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
	 * Return the value associated with the column: AN_A
	 */
	public java.lang.String getAnA () {
		return anA;
	}

	/**
	 * Set the value related to the column: AN_A
	 * @param anA the AN_A value
	 */
	public void setAnA (java.lang.String anA) {
		this.anA = anA;
	}



	/**
	 * Return the value associated with the column: AN_B
	 */
	public java.lang.String getAnB () {
		return anB;
	}

	/**
	 * Set the value related to the column: AN_B
	 * @param anB the AN_B value
	 */
	public void setAnB (java.lang.String anB) {
		this.anB = anB;
	}



	/**
	 * Return the value associated with the column: AN_C
	 */
	public java.lang.String getAnC () {
		return anC;
	}

	/**
	 * Set the value related to the column: AN_C
	 * @param anC the AN_C value
	 */
	public void setAnC (java.lang.String anC) {
		this.anC = anC;
	}



	/**
	 * Return the value associated with the column: AN_D
	 */
	public java.lang.String getAnD () {
		return anD;
	}

	/**
	 * Set the value related to the column: AN_D
	 * @param anD the AN_D value
	 */
	public void setAnD (java.lang.String anD) {
		this.anD = anD;
	}



	/**
	 * Return the value associated with the column: AN_E
	 */
	public java.lang.String getAnE () {
		return anE;
	}

	/**
	 * Set the value related to the column: AN_E
	 * @param anE the AN_E value
	 */
	public void setAnE (java.lang.String anE) {
		this.anE = anE;
	}



	/**
	 * Return the value associated with the column: AN_F
	 */
	public java.lang.String getAnF () {
		return anF;
	}

	/**
	 * Set the value related to the column: AN_F
	 * @param anF the AN_F value
	 */
	public void setAnF (java.lang.String anF) {
		this.anF = anF;
	}



	/**
	 * Return the value associated with the column: AN_G
	 */
	public java.lang.String getAnG () {
		return anG;
	}

	/**
	 * Set the value related to the column: AN_G
	 * @param anG the AN_G value
	 */
	public void setAnG (java.lang.String anG) {
		this.anG = anG;
	}



	/**
	 * Return the value associated with the column: ARAREA
	 */
	public java.lang.String getArarea () {
		return ararea;
	}

	/**
	 * Set the value related to the column: ARAREA
	 * @param ararea the ARAREA value
	 */
	public void setArarea (java.lang.String ararea) {
		this.ararea = ararea;
	}



	/**
	 * Return the value associated with the column: ARAREANO
	 */
	public java.lang.String getArareano () {
		return arareano;
	}

	/**
	 * Set the value related to the column: ARAREANO
	 * @param arareano the ARAREANO value
	 */
	public void setArareano (java.lang.String arareano) {
		this.arareano = arareano;
	}



	/**
	 * Return the value associated with the column: ARCREATOR
	 */
	public java.lang.String getArcreator () {
		return arcreator;
	}

	/**
	 * Set the value related to the column: ARCREATOR
	 * @param arcreator the ARCREATOR value
	 */
	public void setArcreator (java.lang.String arcreator) {
		this.arcreator = arcreator;
	}



	/**
	 * Return the value associated with the column: ARCRTDATE
	 */
	public java.util.Date getArcrtdate () {
		return arcrtdate;
	}

	/**
	 * Set the value related to the column: ARCRTDATE
	 * @param arcrtdate the ARCRTDATE value
	 */
	public void setArcrtdate (java.util.Date arcrtdate) {
		this.arcrtdate = arcrtdate;
	}



	/**
	 * Return the value associated with the column: ARORG
	 */
	public java.lang.String getArorg () {
		return arorg;
	}

	/**
	 * Set the value related to the column: ARORG
	 * @param arorg the ARORG value
	 */
	public void setArorg (java.lang.String arorg) {
		this.arorg = arorg;
	}



	/**
	 * Return the value associated with the column: ARORGNO
	 */
	public java.lang.String getArorgno () {
		return arorgno;
	}

	/**
	 * Set the value related to the column: ARORGNO
	 * @param arorgno the ARORGNO value
	 */
	public void setArorgno (java.lang.String arorgno) {
		this.arorgno = arorgno;
	}



	/**
	 * Return the value associated with the column: ARORGTYPE
	 */
	public java.lang.String getArorgtype () {
		return arorgtype;
	}

	/**
	 * Set the value related to the column: ARORGTYPE
	 * @param arorgtype the ARORGTYPE value
	 */
	public void setArorgtype (java.lang.String arorgtype) {
		this.arorgtype = arorgtype;
	}



	/**
	 * Return the value associated with the column: ARORGTYPENO
	 */
	public java.lang.String getArorgtypeno () {
		return arorgtypeno;
	}

	/**
	 * Set the value related to the column: ARORGTYPENO
	 * @param arorgtypeno the ARORGTYPENO value
	 */
	public void setArorgtypeno (java.lang.String arorgtypeno) {
		this.arorgtypeno = arorgtypeno;
	}



	/**
	 * Return the value associated with the column: ARQUESTIONAIREID
	 */
	public java.lang.String getArquestionaireid () {
		return arquestionaireid;
	}

	/**
	 * Set the value related to the column: ARQUESTIONAIREID
	 * @param arquestionaireid the ARQUESTIONAIREID value
	 */
	public void setArquestionaireid (java.lang.String arquestionaireid) {
		this.arquestionaireid = arquestionaireid;
	}



	/**
	 * Return the value associated with the column: ARQUESTIONID
	 */
	public java.lang.String getArquestionid () {
		return arquestionid;
	}

	/**
	 * Set the value related to the column: ARQUESTIONID
	 * @param arquestionid the ARQUESTIONID value
	 */
	public void setArquestionid (java.lang.String arquestionid) {
		this.arquestionid = arquestionid;
	}



	/**
	 * Return the value associated with the column: CREATEDATE
	 */
	public java.util.Date getCreatedate () {
		return createdate;
	}

	/**
	 * Set the value related to the column: CREATEDATE
	 * @param createdate the CREATEDATE value
	 */
	public void setCreatedate (java.util.Date createdate) {
		this.createdate = createdate;
	}



	/**
	 * Return the value associated with the column: FLAG
	 */
	public java.lang.String getFlag () {
		return flag;
	}

	/**
	 * Set the value related to the column: FLAG
	 * @param flag the FLAG value
	 */
	public void setFlag (java.lang.String flag) {
		this.flag = flag;
	}



	/**
	 * Return the value associated with the column: STAT
	 */
	public java.lang.String getStat () {
		return stat;
	}

	/**
	 * Set the value related to the column: STAT
	 * @param stat the STAT value
	 */
	public void setStat (java.lang.String stat) {
		this.stat = stat;
	}



	/**
	 * Return the value associated with the column: UPDATEATE
	 */
	public java.util.Date getUpdateate () {
		return updateate;
	}

	/**
	 * Set the value related to the column: UPDATEATE
	 * @param updateate the UPDATEATE value
	 */
	public void setUpdateate (java.util.Date updateate) {
		this.updateate = updateate;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.gtm.csims.model.BsAnswerresult)) return false;
		else {
			com.gtm.csims.model.BsAnswerresult bsAnswerresult = (com.gtm.csims.model.BsAnswerresult) obj;
			if (null == this.getId() || null == bsAnswerresult.getId()) return false;
			else return (this.getId().equals(bsAnswerresult.getId()));
		}
	}

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


	public String toString () {
		return super.toString();
	}


}