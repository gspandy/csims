package com.gtm.csims.model;

import com.gtm.csims.model.base.BaseBsAdmpunish;



public class BsAdmpunish extends BaseBsAdmpunish {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public BsAdmpunish () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public BsAdmpunish (java.lang.String id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public BsAdmpunish (
		java.lang.String id,
		java.lang.String aeno,
		java.lang.String aeorgnm,
		java.lang.String aeorgno,
		java.lang.String aeedorgnm,
		java.lang.String aeedorgno,
		java.lang.String punishno) {

		super (
			id,
			aeno,
			aeorgnm,
			aeorgno,
			aeedorgnm,
			aeedorgno,
			punishno);
	}

/*[CONSTRUCTOR MARKER END]*/


}