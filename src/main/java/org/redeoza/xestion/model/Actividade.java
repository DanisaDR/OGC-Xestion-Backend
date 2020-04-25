package org.redeoza.xestion.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * <b>Actividade.java<b>
 * 
 * @author Daniel Isasi
 * @since 16 ene. 2020
 */
@Entity
@Table(name = "actividade")
public class Actividade implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "act_id")
	private int actID;

	@NotEmpty(message = "{actNom.empty}")
	@Size(min = 3, max = 150, message = "{actNom.size}")
	@Column(name = "act_nom")
	private String actNom;

	@Size(max = 255, message = "{actDescr.size}")
	@Column(name = "act_descr")
	private String actDescr;

	@NotNull(message = "{actAport.notNull}")
	@Positive(message = "{actAport.positive}")
	@Min(value = 10, message = "{actAport.min}")
	@Max(value = 40, message = "{actAport.max}")
	@Column(name = "act_aport")
	private int actAport;

	@NotNull(message = "{actDataComezo.notNull}")
	@Temporal(value = TemporalType.DATE)
	@Column(name = "act_data_comezo")
	private Date actDataComezo;

	@NotNull(message = "{actDataRemate.notNull}")
	@Temporal(value = TemporalType.DATE)
	@Column(name = "act_data_remate")
	private Date actDataRemate;

	@ManyToMany(mappedBy = "actividades")
	@JsonIgnoreProperties(value = { "actividades", "hibernateLazyInitializer", "handler" }, allowSetters = true)
	@Cascade({ CascadeType.SAVE_UPDATE, CascadeType.MERGE, CascadeType.REFRESH })
	private Set<Socio> socios;

	@ManyToOne
	@JoinColumn(name = "usu_id")
	@JsonIgnoreProperties(value = { "actividades", "hibernateLazyInitializer", "handler" }, allowSetters = true)
	@Cascade({ CascadeType.SAVE_UPDATE })
	private Usuario usuario;

	/**
	 * <p>
	 * GET:
	 * </p>
	 * <b>obtener actID</b>
	 * 
	 * @return int actID
	 */
	public int getActID() {
		return this.actID;
	}

	/**
	 * <p>
	 * SET: <b>Establece actID</b>
	 * </p>
	 * 
	 * @param int
	 *            actID
	 */
	public void setActID(int actID) {
		this.actID = actID;
	}

	/**
	 * <p>
	 * GET:
	 * </p>
	 * <b>obtener actNom</b>
	 * 
	 * @return String actNom
	 */
	public String getActNom() {
		return this.actNom;
	}

	/**
	 * <p>
	 * SET: <b>Establece actNom</b>
	 * </p>
	 * 
	 * @param String
	 *            actNom
	 */
	public void setActNom(String actNom) {
		this.actNom = actNom;
	}

	/**
	 * <p>
	 * GET:
	 * </p>
	 * <b>obtener actDescr</b>
	 * 
	 * @return String actDescr
	 */
	public String getActDescr() {
		return this.actDescr;
	}

	/**
	 * <p>
	 * SET: <b>Establece actDescr</b>
	 * </p>
	 * 
	 * @param String
	 *            actDescr
	 */
	public void setActDescr(String actDescr) {
		this.actDescr = actDescr;
	}

	/**
	 * <p>
	 * GET:
	 * </p>
	 * <b>obtener actAport</b>
	 * 
	 * @return int actAport
	 */
	public int getActAport() {
		return this.actAport;
	}

	/**
	 * <p>
	 * SET: <b>Establece actAport</b>
	 * </p>
	 * 
	 * @param int
	 *            actAport
	 */
	public void setActAport(int actAport) {
		this.actAport = actAport;
	}

	/**
	 * <p>
	 * GET:
	 * </p>
	 * <b>obtener actDataComezo</b>
	 * 
	 * @return Date actDataComezo
	 */
	public Date getActDataComezo() {
		return this.actDataComezo;
	}

	/**
	 * <p>
	 * SET: <b>Establece actDataComezo</b>
	 * </p>
	 * 
	 * @param Date
	 *            actDataComezo
	 */
	public void setActDataComezo(Date actDataComezo) {
		this.actDataComezo = actDataComezo;
	}

	/**
	 * <p>
	 * GET:
	 * </p>
	 * <b>obtener actDataRemate</b>
	 * 
	 * @return Date actDataRemate
	 */
	public Date getActDataRemate() {
		return this.actDataRemate;
	}

	/**
	 * <p>
	 * SET: <b>Establece actDataRemate</b>
	 * </p>
	 * 
	 * @param Date
	 *            actDataRemate
	 */
	public void setActDataRemate(Date actDataRemate) {
		this.actDataRemate = actDataRemate;
	}

	/**
	 * <p>
	 * GET:
	 * </p>
	 * <b>obtener socios</b>
	 * 
	 * @return Set<Socio> socios
	 */
	public Set<Socio> getSocios() {
		return this.socios;
	}

	/**
	 * <p>
	 * SET: <b>Establece socios</b>
	 * </p>
	 * 
	 * @param Set<Socio>
	 *            socios
	 */
	public void setSocios(Set<Socio> socios) {
		this.socios = socios;
	}

	/**
	 * <p>
	 * GET:
	 * </p>
	 * <b>obtener usuario</b>
	 * 
	 * @return Usuario usuario
	 */
	public Usuario getUsuario() {
		return this.usuario;
	}

	/**
	 * <p>
	 * SET: <b>Establece usuario</b>
	 * </p>
	 * 
	 * @param Usuario
	 *            usuario
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
