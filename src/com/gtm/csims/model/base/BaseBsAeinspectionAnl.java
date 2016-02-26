package com.gtm.csims.model.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the BS_AEINSPECTION_ANL table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="BS_AEINSPECTION_ANL"
 */

public abstract class BaseBsAeinspectionAnl  implements Serializable {

	public static String REF = "BsAeinspectionAnl";
	public static String PROP_UPDATEATE = "Updateate";
	public static String PROP_B1 = "B1";
	public static String PROP_AEORGNM = "Aeorgnm";
	public static String PROP_STAT = "Stat";
	public static String PROP_AEORGNO = "Aeorgno";
	public static String PROP_J1 = "J1";
	public static String PROP_E1 = "E1";
	public static String PROP_P1 = "P1";
	public static String PROP_I1 = "I1";
	public static String PROP_REPORTER = "Reporter";
	public static String PROP_D1 = "D1";
	public static String PROP_FLAG = "Flag";
	public static String PROP_O1 = "O1";
	public static String PROP_Q1 = "Q1";
	public static String PROP_AENO = "Aeno";
	public static String PROP_AEEDORGNO = "Aeedorgno";
	public static String PROP_L1 = "L1";
	public static String PROP_G1 = "G1";
	public static String PROP_M1 = "M1";
	public static String PROP_N1 = "N1";
	public static String PROP_K1 = "K1";
	public static String PROP_F1 = "F1";
	public static String PROP_A1 = "A1";
	public static String PROP_CREATEDATE = "Createdate";
	public static String PROP_AEEDORGNM = "Aeedorgnm";
	public static String PROP_ANLDATE = "Anldate";
	public static String PROP_H1 = "H1";
	public static String PROP_C1 = "C1";
	public static String PROP_AEINSPECTIONNO = "Aeinspectionno";
	public static String PROP_ID = "Id";


	// constructors
	public BaseBsAeinspectionAnl () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseBsAeinspectionAnl (java.lang.String id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String id;

	// fields
	private java.lang.String stat;
	private java.lang.String flag;
	private java.util.Date createdate;
	private java.util.Date updateate;
	private java.lang.String a1;
	private java.lang.String b1;
	private java.lang.Long c1;
	private java.lang.Long d1;
	private java.lang.Long e1;
	private java.lang.Long f1;
	private java.lang.Long g1;
	private java.lang.Long h1;
	private java.lang.Long i1;
	private java.lang.Long j1;
	private java.lang.Long k1;
	private java.lang.Long l1;
	private java.lang.Long m1;
	private java.lang.String n1;
	private java.lang.String o1;
	private java.math.BigDecimal p1;
	private java.math.BigDecimal q1;
	private java.util.Date anldate;
	private java.lang.String reporter;
	private java.lang.String aeinspectionno;
	private java.lang.String aeno;
	private java.lang.String aeorgno;
	private java.lang.String aeorgnm;
	private java.lang.String aeedorgno;
	private java.lang.String aeedorgnm;



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



	/**
	 * Return the value associated with the column: A1
	 */
	public java.lang.String getA1 () {
		return a1;
	}

	/**
	 * Set the value related to the column: A1
	 * @param a1 the A1 value
	 */
	public void setA1 (java.lang.String a1) {
		this.a1 = a1;
	}



	/**
	 * Return the value associated with the column: B1
	 */
	public java.lang.String getB1 () {
		return b1;
	}

	/**
	 * Set the value related to the column: B1
	 * @param b1 the B1 value
	 */
	public void setB1 (java.lang.String b1) {
		this.b1 = b1;
	}



	/**
	 * Return the value associated with the column: C1
	 */
	public java.lang.Long getC1 () {
		return c1;
	}

	/**
	 * Set the value related to the column: C1
	 * @param c1 the C1 value
	 */
	public void setC1 (java.lang.Long c1) {
		this.c1 = c1;
	}



	/**
	 * Return the value associated with the column: D1
	 */
	public java.lang.Long getD1 () {
		return d1;
	}

	/**
	 * Set the value related to the column: D1
	 * @param d1 the D1 value
	 */
	public void setD1 (java.lang.Long d1) {
		this.d1 = d1;
	}



	/**
	 * Return the value associated with the column: E1
	 */
	public java.lang.Long getE1 () {
		return e1;
	}

	/**
	 * Set the value related to the column: E1
	 * @param e1 the E1 value
	 */
	public void setE1 (java.lang.Long e1) {
		this.e1 = e1;
	}



	/**
	 * Return the value associated with the column: F1
	 */
	public java.lang.Long getF1 () {
		return f1;
	}

	/**
	 * Set the value related to the column: F1
	 * @param f1 the F1 value
	 */
	public void setF1 (java.lang.Long f1) {
		this.f1 = f1;
	}



	/**
	 * Return the value associated with the column: G1
	 */
	public java.lang.Long getG1 () {
		return g1;
	}

	/**
	 * Set the value related to the column: G1
	 * @param g1 the G1 value
	 */
	public void setG1 (java.lang.Long g1) {
		this.g1 = g1;
	}



	/**
	 * Return the value associated with the column: H1
	 */
	public java.lang.Long getH1 () {
		return h1;
	}

	/**
	 * Set the value related to the column: H1
	 * @param h1 the H1 value
	 */
	public void setH1 (java.lang.Long h1) {
		this.h1 = h1;
	}



	/**
	 * Return the value associated with the column: I1
	 */
	public java.lang.Long getI1 () {
		return i1;
	}

	/**
	 * Set the value related to the column: I1
	 * @param i1 the I1 value
	 */
	public void setI1 (java.lang.Long i1) {
		this.i1 = i1;
	}



	/**
	 * Return the value associated with the column: J1
	 */
	public java.lang.Long getJ1 () {
		return j1;
	}

	/**
	 * Set the value related to the column: J1
	 * @param j1 the J1 value
	 */
	public void setJ1 (java.lang.Long j1) {
		this.j1 = j1;
	}



	/**
	 * Return the value associated with the column: K1
	 */
	public java.lang.Long getK1 () {
		return k1;
	}

	/**
	 * Set the value related to the column: K1
	 * @param k1 the K1 value
	 */
	public void setK1 (java.lang.Long k1) {
		this.k1 = k1;
	}



	/**
	 * Return the value associated with the column: L1
	 */
	public java.lang.Long getL1 () {
		return l1;
	}

	/**
	 * Set the value related to the column: L1
	 * @param l1 the L1 value
	 */
	public void setL1 (java.lang.Long l1) {
		this.l1 = l1;
	}



	/**
	 * Return the value associated with the column: M1
	 */
	public java.lang.Long getM1 () {
		return m1;
	}

	/**
	 * Set the value related to the column: M1
	 * @param m1 the M1 value
	 */
	public void setM1 (java.lang.Long m1) {
		this.m1 = m1;
	}



	/**
	 * Return the value associated with the column: N1
	 */
	public java.lang.String getN1 () {
		return n1;
	}

	/**
	 * Set the value related to the column: N1
	 * @param n1 the N1 value
	 */
	public void setN1 (java.lang.String n1) {
		this.n1 = n1;
	}



	/**
	 * Return the value associated with the column: O1
	 */
	public java.lang.String getO1 () {
		return o1;
	}

	/**
	 * Set the value related to the column: O1
	 * @param o1 the O1 value
	 */
	public void setO1 (java.lang.String o1) {
		this.o1 = o1;
	}



	/**
	 * Return the value associated with the column: P1
	 */
	public java.math.BigDecimal getP1 () {
		return p1;
	}

	/**
	 * Set the value related to the column: P1
	 * @param p1 the P1 value
	 */
	public void setP1 (java.math.BigDecimal p1) {
		this.p1 = p1;
	}



	/**
	 * Return the value associated with the column: Q1
	 */
	public java.math.BigDecimal getQ1 () {
		return q1;
	}

	/**
	 * Set the value related to the column: Q1
	 * @param q1 the Q1 value
	 */
	public void setQ1 (java.math.BigDecimal q1) {
		this.q1 = q1;
	}



	/**
	 * Return the value associated with the column: ANLDATE
	 */
	public java.util.Date getAnldate () {
		return anldate;
	}

	/**
	 * Set the value related to the column: ANLDATE
	 * @param anldate the ANLDATE value
	 */
	public void setAnldate (java.util.Date anldate) {
		this.anldate = anldate;
	}



	/**
	 * Return the value associated with the column: REPORTER
	 */
	public java.lang.String getReporter () {
		return reporter;
	}

	/**
	 * Set the value related to the column: REPORTER
	 * @param reporter the REPORTER value
	 */
	public void setReporter (java.lang.String reporter) {
		this.reporter = reporter;
	}



	/**
	 * Return the value associated with the column: AEINSPECTIONNO
	 */
	public java.lang.String getAeinspectionno () {
		return aeinspectionno;
	}

	/**
	 * Set the value related to the column: AEINSPECTIONNO
	 * @param aeinspectionno the AEINSPECTIONNO value
	 */
	public void setAeinspectionno (java.lang.String aeinspectionno) {
		this.aeinspectionno = aeinspectionno;
	}



	/**
	 * Return the value associated with the column: AENO
	 */
	public java.lang.String getAeno () {
		return aeno;
	}

	/**
	 * Set the value related to the column: AENO
	 * @param aeno the AENO value
	 */
	public void setAeno (java.lang.String aeno) {
		this.aeno = aeno;
	}



	/**
	 * Return the value associated with the column: AEORGNO
	 */
	public java.lang.String getAeorgno () {
		return aeorgno;
	}

	/**
	 * Set the value related to the column: AEORGNO
	 * @param aeorgno the AEORGNO value
	 */
	public void setAeorgno (java.lang.String aeorgno) {
		this.aeorgno = aeorgno;
	}



	/**
	 * Return the value associated with the column: AEORGNM
	 */
	public java.lang.String getAeorgnm () {
		return aeorgnm;
	}

	/**
	 * Set the value related to the column: AEORGNM
	 * @param aeorgnm the AEORGNM value
	 */
	public void setAeorgnm (java.lang.String aeorgnm) {
		this.aeorgnm = aeorgnm;
	}



	/**
	 * Return the value associated with the column: AEEDORGNO
	 */
	public java.lang.String getAeedorgno () {
		return aeedorgno;
	}

	/**
	 * Set the value related to the column: AEEDORGNO
	 * @param aeedorgno the AEEDORGNO value
	 */
	public void setAeedorgno (java.lang.String aeedorgno) {
		this.aeedorgno = aeedorgno;
	}



	/**
	 * Return the value associated with the column: AEEDORGNM
	 */
	public java.lang.String getAeedorgnm () {
		return aeedorgnm;
	}

	/**
	 * Set the value related to the column: AEEDORGNM
	 * @param aeedorgnm the AEEDORGNM value
	 */
	public void setAeedorgnm (java.lang.String aeedorgnm) {
		this.aeedorgnm = aeedorgnm;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.gtm.csims.model.BsAeinspectionAnl)) return false;
		else {
			com.gtm.csims.model.BsAeinspectionAnl bsAeinspectionAnl = (com.gtm.csims.model.BsAeinspectionAnl) obj;
			if (null == this.getId() || null == bsAeinspectionAnl.getId()) return false;
			else return (this.getId().equals(bsAeinspectionAnl.getId()));
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