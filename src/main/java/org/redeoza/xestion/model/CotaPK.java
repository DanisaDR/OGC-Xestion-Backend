package org.redeoza.xestion.model;

import java.io.Serializable;

/**
 * <b>CotaPK.java<b>
 * 
 * @author Daniel Isasi
 * @since 16 ene. 2020
 */
public class CotaPK implements Serializable {

	private static final long serialVersionUID = 1L;

	private int cotaID;
	private int socID;

	/**
	 * <p>
	 * Creado: 16 ene. 2020
	 * </p>
	 */
	public CotaPK() {
		super();
	}

	/**
	 * <p>
	 * Creado: 16 ene. 2020
	 * </p>
	 * 
	 * @param cotaID
	 * @param socID
	 */
	public CotaPK(int cotaID, int socID) {
		super();
		this.cotaID = cotaID;
		this.socID = socID;
	}

	/**
	 * <p>
	 * GET:
	 * </p>
	 * <b>obtener cotaID</b>
	 * 
	 * @return int cotaID
	 */
	public int getCotaID() {
		return this.cotaID;
	}

	/**
	 * <p>
	 * SET: <b>Establece cotaID</b>
	 * </p>
	 * 
	 * @param int
	 *            cotaID
	 */
	public void setCotaID(int cotaID) {
		this.cotaID = cotaID;
	}

	/**
	 * <p>
	 * GET:
	 * </p>
	 * <b>obtener socID</b>
	 * 
	 * @return int socID
	 */
	public int getSocID() {
		return this.socID;
	}

	/**
	 * <p>
	 * SET: <b>Establece socID</b>
	 * </p>
	 * 
	 * @param int
	 *            socID
	 */
	public void setSocID(int socID) {
		this.socID = socID;
	}
}
