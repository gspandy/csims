package com.gtm.csims.model;

import com.gtm.csims.model.base.BaseBsAerectification;



public class BsAerectification extends BaseBsAerectification {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public BsAerectification () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public BsAerectification (java.lang.String id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public BsAerectification (
		java.lang.String id,
		java.lang.String aeno,
		java.lang.String aeorgnm,
		java.lang.String aeorgno,
		java.lang.String aeedorgnm,
		java.lang.String aeedorgno,
		java.lang.String trackno) {

		super (
			id,
			aeno,
			aeorgnm,
			aeorgno,
			aeedorgnm,
			aeedorgno,
			trackno);
	}

/*[CONSTRUCTOR MARKER END]*/


}