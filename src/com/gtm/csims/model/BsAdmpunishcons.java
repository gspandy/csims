package com.gtm.csims.model;

import com.gtm.csims.model.base.BaseBsAdmpunishcons;



public class BsAdmpunishcons extends BaseBsAdmpunishcons {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public BsAdmpunishcons () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public BsAdmpunishcons (java.lang.String id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public BsAdmpunishcons (
		java.lang.String id,
		java.lang.String aeno,
		java.lang.String aeorgnm,
		java.lang.String aeorgno,
		java.lang.String aeedorgnm,
		java.lang.String aeedorgno,
		java.lang.String aeopnionno,
		java.lang.String punishno,
		java.lang.String punishbookno) {

		super (
			id,
			aeno,
			aeorgnm,
			aeorgno,
			aeedorgnm,
			aeedorgno,
			aeopnionno,
			punishno,
			punishbookno);
	}

/*[CONSTRUCTOR MARKER END]*/


}