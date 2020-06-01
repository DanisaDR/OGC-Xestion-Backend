package org.redeoza.xestion.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

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
	@Min(0)
	@Max(15)
	@Column(name = "cota_importe")
	private int cotaImporte;

	@MapsId
	@ManyToOne
	@JoinColumn(name = "soc_id", insertable = false, updatable = false)
	@JsonIgnoreProperties(value = { "cotas", "hibernateLazyInitializer", "handler" }, allowSetters = true)
	private Socio socio;

	public int getSocID() {
		return this.socID;
	}

	public void setSocID(int socID) {
		this.socID = socID;
	}

	public int getCotaID() {
		return this.cotaID;
	}

	public void setCotaID(int cotaID) {
		this.cotaID = cotaID;
	}

	public int getCotaAnual() {
		return this.cotaAnual;
	}

	public void setCotaAnual(int cotaAnual) {
		this.cotaAnual = cotaAnual;
	}

	public int getCotaImporte() {
		return this.cotaImporte;
	}

	public void setCotaImporte(int cotaImporte) {
		this.cotaImporte = cotaImporte;
	}

	public Socio getSocio() {
		return this.socio;
	}

	public void setSocio(Socio socio) {
		this.socio = socio;
	}
}
