package com.gtm.csims.model;

import com.gtm.csims.model.base.BaseBsAeinspection;



public class BsAeinspection extends BaseBsAeinspection {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public BsAeinspection () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public BsAeinspection (java.lang.String id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public BsAeinspection (
		java.lang.String id,
		java.lang.String ino,
		java.lang.String aeno,
		java.lang.String aeorgnm,
		java.lang.String aeorgno,
		java.lang.String aeedorgnm,
		java.lang.String aeedorgno) {

		super (
			id,
			ino,
			aeno,
			aeorgnm,
			aeorgno,
			aeedorgnm,
			aeedorgno);
	}

/*[CONSTRUCTOR MARKER END]*/


}