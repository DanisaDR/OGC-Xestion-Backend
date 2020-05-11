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
import org.springframework.format.annotation.DateTimeFormat;

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
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/M/yyyy")
	@Column(name = "act_data_comezo")
	private Date actDataComezo;

	@NotNull(message = "{actDataRemate.notNull}")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/M/yyyy")
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

	public int getActID() {
		return this.actID;
	}

	public void setActID(int actID) {
		this.actID = actID;
	}

	public String getActNom() {
		return this.actNom;
	}

	public void setActNom(String actNom) {
		this.actNom = actNom;
	}

	public String getActDescr() {
		return this.actDescr;
	}

	public void setActDescr(String actDescr) {
		this.actDescr = actDescr;
	}

	public int getActAport() {
		return this.actAport;
	}

	public void setActAport(int actAport) {
		this.actAport = actAport;
	}

	public Date getActDataComezo() {
		return this.actDataComezo;
	}

	public void setActDataComezo(Date actDataComezo) {
		this.actDataComezo = actDataComezo;
	}

	public Date getActDataRemate() {
		return this.actDataRemate;
	}

	public void setActDataRemate(Date actDataRemate) {
		this.actDataRemate = actDataRemate;
	}

	public Set<Socio> getSocios() {
		return this.socios;
	}

	public void setSocios(Set<Socio> socios) {
		this.socios = socios;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
