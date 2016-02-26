package com.gtm.csims.model;

import com.gtm.csims.model.base.BaseBsExamscore;



public class BsExamscore extends BaseBsExamscore {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public BsExamscore () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public BsExamscore (java.lang.String id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public BsExamscore (
		java.lang.String id,
		java.lang.String importno,
		java.lang.String peoid,
		java.lang.String peotype,
		java.lang.String orgno,
		java.math.BigDecimal score) {

		super (
			id,
			importno,
			peoid,
			peotype,
			orgno,
			score);
	}

/*[CONSTRUCTOR MARKER END]*/


}