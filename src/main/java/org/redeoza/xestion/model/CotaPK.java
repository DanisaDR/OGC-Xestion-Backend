package org.redeoza.xestion.model;

import java.io.Serializable;

public class CotaPK implements Serializable {

	private static final long serialVersionUID = 1L;

	private int cotaID;
	private int socID;

	public CotaPK() {
		super();
	}

	public CotaPK(int cotaID, int socID) {
		super();
		this.cotaID = cotaID;
		this.socID = socID;
	}

	public int getCotaID() {
		return this.cotaID;
	}

	public void setCotaID(int cotaID) {
		this.cotaID = cotaID;
	}

	public int getSocID() {
		return this.socID;
	}

	public void setSocID(int socID) {
		this.socID = socID;
	}
}
