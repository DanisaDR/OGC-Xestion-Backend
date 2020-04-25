package org.redeoza.xestion.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

/**
 * <b>Cota.java<b>
 * 
 * @author Daniel Isasi
 * @since 16 ene. 2020
 */
@Entity
@Table(name = "cota")
@IdClass(value = CotaPK.class)
public class Cota implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "soc_id")
	private int socID;

	@Id
	@Column(name = "cota_id")
	private int cotaID;

	@NotNull
	@Min(1970)
	@Max(2050)
	@Column(name = "cota_anual")
	private int cotaAnual;

	@NotNull
	@Min(15)
	@Max(15)
	@Column(name = "cota_importe")
	private int cotaImporte;

	@MapsId
	@ManyToOne
	@JoinColumn(name = "soc_id", insertable = false, updatable = false)
	@JsonIgnoreProperties(value = { "cotas", "hibernateLazyInitializer", "handler" }, allowSetters = true)
	private Socio socio;

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
	 * <b>obtener cotaAnual</b>
	 * 
	 * @return int cotaAnual
	 */
	public int getCotaAnual() {
		return this.cotaAnual;
	}

	/**
	 * <p>
	 * SET: <b>Establece cotaAnual</b>
	 * </p>
	 * 
	 * @param int
	 *            cotaAnual
	 */
	public void setCotaAnual(int cotaAnual) {
		this.cotaAnual = cotaAnual;
	}

	/**
	 * <p>
	 * GET:
	 * </p>
	 * <b>obtener cotaImporte</b>
	 * 
	 * @return int cotaImporte
	 */
	public int getCotaImporte() {
		return this.cotaImporte;
	}

	/**
	 * <p>
	 * SET: <b>Establece cotaImporte</b>
	 * </p>
	 * 
	 * @param int
	 *            cotaImporte
	 */
	public void setCotaImporte(int cotaImporte) {
		this.cotaImporte = cotaImporte;
	}

	/**
	 * <p>
	 * GET:
	 * </p>
	 * <b>obtener socio</b>
	 * 
	 * @return Socio socio
	 */
	public Socio getSocio() {
		return this.socio;
	}

	/**
	 * <p>
	 * SET: <b>Establece socio</b>
	 * </p>
	 * 
	 * @param Socio
	 *            socio
	 */
	public void setSocio(Socio socio) {
		this.socio = socio;
	}
}
