package com.gtm.csims.model;

import com.gtm.csims.model.base.BaseBsAeconclusion;



public class BsAeconclusion extends BaseBsAeconclusion {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public BsAeconclusion () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public BsAeconclusion (java.lang.String id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public BsAeconclusion (
		java.lang.String id,
		java.lang.String aeno,
		java.lang.String aeorgnm,
		java.lang.String aeorgno,
		java.lang.String aeedorgnm,
		java.lang.String aeedorgno,
		java.lang.String aeopnionno) {

		super (
			id,
			aeno,
			aeorgnm,
			aeorgno,
			aeedorgnm,
			aeedorgno,
			aeopnionno);
	}

/*[CONSTRUCTOR MARKER END]*/


}