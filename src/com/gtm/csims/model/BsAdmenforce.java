package com.gtm.csims.model;

import com.gtm.csims.model.base.BaseBsAdmenforce;



public class BsAdmenforce extends BaseBsAdmenforce {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public BsAdmenforce () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public BsAdmenforce (java.lang.String id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public BsAdmenforce (
		java.lang.String id,
		java.lang.String aeno,
		java.lang.String aeorgnm,
		java.lang.String aeorgno,
		java.lang.String aeedorgnm,
		java.lang.String aeedorgno) {

		super (
			id,
			aeno,
			aeorgnm,
			aeorgno,
			aeedorgnm,
			aeedorgno);
	}

/*[CONSTRUCTOR MARKER END]*/


}