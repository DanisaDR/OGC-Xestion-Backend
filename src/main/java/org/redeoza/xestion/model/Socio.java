package org.redeoza.xestion.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

/**
 * <b>Socio.java<b>
 * 
 * @author Daniel Isasi
 * @since 16 ene. 2020
 */
@Entity
@Table(name = "Socio")
public class Socio implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "soc_id")
	private int socID;

	@NotEmpty
	@Size(min = 10, max = 255)
	@Column(name = "soc_nom_comp")
	private String socNomComp;

	@Transient
	private String socNom;

	@Transient
	private String socApe1;

	@Transient
	private String socApe2;

	@NotEmpty
	@Size(min = 10, max = 255)
	@Column(name = "soc_ender")
	private String socEnder;

	@NotEmpty
	@Size(min = 5, max = 100)
	@Column(name = "soc_pob")
	private String socPob;

	@NotNull
	@Min(15000)
	@Max(15009)
	@Column(name = "soc_cp")
	private int socCP;

	@NotNull
	@Min(880000000)
	@Max(989999999)
	@Column(name = "soc_tfno_fx")
	private String socTfnoFx;

	@NotNull
	@Min(600000000)
	@Max(699999999)
	@Column(name = "soc_tfno_mb")
	private String socTfnoMb;

	@NotNull
	@Column(name = "soc_act")
	private boolean socAct;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "soc_data_alta")
	private Date socDataAlta;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "soc_data_baixa")
	private Date socDataBaixa;

	@Email
	@Nullable
	@Column(name = "soc_email")
	private String socEmail;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "activ_socio", joinColumns = @JoinColumn(name = "soc_id"), inverseJoinColumns = @JoinColumn(name = "act_id"))
	@JsonIgnoreProperties(value = { "socios", "hibernateLazyInitializer", "handler" }, allowSetters = true)
	@Cascade({ CascadeType.SAVE_UPDATE })
	Set<Actividade> actividades;

	@OneToMany(mappedBy = "socio")
	@JsonIgnoreProperties(value = { "cotas", "hibernateLazyInitializer", "handler" }, allowSetters = true)
	private Set<Cota> cotas;

	/**
	 * <p>
	 * GET:
	 * </p>
	 * <b>obtener soc_id</b>
	 * 
	 * @return int soc_id
	 */
	public int getSocID() {
		return this.socID;
	}

	/**
	 * <p>
	 * SET: <b>Establece soc_id</b>
	 * </p>
	 * 
	 * @param int
	 *            soc_id
	 */
	public void setSocID(int socID) {
		this.socID = socID;
	}

	/**
	 * <p>
	 * GET:
	 * </p>
	 * <b>obtener socNomComp</b>
	 * 
	 * @return String socNomComp
	 */
	public String getSocNomComp() {
		return this.socNomComp;
	}

	/**
	 * <p>
	 * SET: <b>Establece socNomComp</b>
	 * </p>
	 * 
	 * @param String
	 *            socNomComp
	 */
	public void setSocNomComp(String socNomComp) {
		this.socNomComp = socNomComp;
	}

	/**
	 * <p>
	 * GET:
	 * </p>
	 * <b>obtener socNom</b>
	 * 
	 * @return String socNom
	 */
	public String getSocNom() {
		return this.socNom;
	}

	/**
	 * <p>
	 * SET: <b>Establece socNom</b>
	 * </p>
	 * 
	 * @param String
	 *            socNom
	 */
	public void setSocNom(String socNom) {
		this.socNom = socNom;
	}

	/**
	 * <p>
	 * GET:
	 * </p>
	 * <b>obtener socApe1</b>
	 * 
	 * @return String socApe1
	 */
	public String getSocApe1() {
		return this.socApe1;
	}

	/**
	 * <p>
	 * SET: <b>Establece socApe1</b>
	 * </p>
	 * 
	 * @param String
	 *            socApe1
	 */
	public void setSocApe1(String socApe1) {
		this.socApe1 = socApe1;
	}

	/**
	 * <p>
	 * GET:
	 * </p>
	 * <b>obtener socApe2</b>
	 * 
	 * @return String socApe2
	 */
	public String getSocApe2() {
		return this.socApe2;
	}

	/**
	 * <p>
	 * SET: <b>Establece socApe2</b>
	 * </p>
	 * 
	 * @param String
	 *            socApe2
	 */
	public void setSocApe2(String socApe2) {
		this.socApe2 = socApe2;
	}

	/**
	 * <p>
	 * GET:
	 * </p>
	 * <b>obtener socEnder</b>
	 * 
	 * @return String socEnder
	 */
	public String getSocEnder() {
		return this.socEnder;
	}

	/**
	 * <p>
	 * SET: <b>Establece socEnder</b>
	 * </p>
	 * 
	 * @param String
	 *            socEnder
	 */
	public void setSocEnder(String socEnder) {
		this.socEnder = socEnder;
	}

	/**
	 * <p>
	 * GET:
	 * </p>
	 * <b>obtener socPob</b>
	 * 
	 * @return String socPob
	 */
	public String getSocPob() {
		return this.socPob;
	}

	/**
	 * <p>
	 * SET: <b>Establece socPob</b>
	 * </p>
	 * 
	 * @param String
	 *            socPob
	 */
	public void setSocPob(String socPob) {
		this.socPob = socPob;
	}

	/**
	 * <p>
	 * GET:
	 * </p>
	 * <b>obtener socCP</b>
	 * 
	 * @return int socCP
	 */
	public int getSocCP() {
		return this.socCP;
	}

	/**
	 * <p>
	 * SET: <b>Establece socCP</b>
	 * </p>
	 * 
	 * @param int
	 *            socCP
	 */
	public void setSocCP(int socCP) {
		this.socCP = socCP;
	}

	/**
	 * <p>
	 * GET:
	 * </p>
	 * <b>obtener socTfnoFx</b>
	 * 
	 * @return String socTfnoFx
	 */
	public String getSocTfnoFx() {
		return this.socTfnoFx;
	}

	/**
	 * <p>
	 * SET: <b>Establece socTfnoFx</b>
	 * </p>
	 * 
	 * @param String
	 *            socTfnoFx
	 */
	public void setSocTfnoFx(String socTfnoFx) {
		this.socTfnoFx = socTfnoFx;
	}

	/**
	 * <p>
	 * GET:
	 * </p>
	 * <b>obtener socTfnoMb</b>
	 * 
	 * @return int socTfnoMb
	 */
	public String getSocTfnoMb() {
		return this.socTfnoMb;
	}

	/**
	 * <p>
	 * SET: <b>Establece socTfnoMb</b>
	 * </p>
	 * 
	 * @param String
	 *            socTfnoMb
	 */
	public void setSocTfnoMb(String socTfnoMb) {
		this.socTfnoMb = socTfnoMb;
	}

	/**
	 * <p>
	 * GET:
	 * </p>
	 * <b>obtener socAct</b>
	 * 
	 * @return boolean socAct
	 */
	public boolean isSocAct() {
		return this.socAct;
	}

	/**
	 * <p>
	 * SET: <b>Establece socAct</b>
	 * </p>
	 * 
	 * @param boolean
	 *            socAct
	 */
	public void setSocAct(boolean socAct) {
		this.socAct = socAct;
	}

	/**
	 * <p>
	 * GET:
	 * </p>
	 * <b>obtener socDataAlta</b>
	 * 
	 * @return Date socDataAlta
	 */
	public Date getSocDataAlta() {
		return this.socDataAlta;
	}

	/**
	 * <p>
	 * SET: <b>Establece socDataAlta</b>
	 * </p>
	 * 
	 * @param Date
	 *            socDataAlta
	 */
	public void setSocDataAlta(Date socDataAlta) {
		this.socDataAlta = socDataAlta;
	}

	/**
	 * <p>
	 * GET:
	 * </p>
	 * <b>obtener socDataBaixa</b>
	 * 
	 * @return Date socDataBaixa
	 */
	public Date getSocDataBaixa() {
		return this.socDataBaixa;
	}

	/**
	 * <p>
	 * SET: <b>Establece socDataBaixa</b>
	 * </p>
	 * 
	 * @param Date
	 *            socDataBaixa
	 */
	public void setSocDataBaixa(Date socDataBaixa) {
		this.socDataBaixa = socDataBaixa;
	}

	/**
	 * <p>
	 * GET:
	 * </p>
	 * <b>obtener socEmail</b>
	 * 
	 * @return String socEmail
	 */
	public String getSocEmail() {
		return this.socEmail;
	}

	/**
	 * <p>
	 * SET: <b>Establece socEmail</b>
	 * </p>
	 * 
	 * @param String
	 *            socEmail
	 */
	public void setSocEmail(String socEmail) {
		this.socEmail = socEmail;
	}

	/**
	 * <p>
	 * GET:
	 * </p>
	 * <b>obtener actividades</b>
	 * 
	 * @return Set<Actividade> actividades
	 */
	public Set<Actividade> getActividades() {
		return this.actividades;
	}

	/**
	 * <p>
	 * SET: <b>Establece actividades</b>
	 * </p>
	 * 
	 * @param Set<Actividade>
	 *            actividades
	 */
	public void setActividades(Set<Actividade> actividades) {
		this.actividades = actividades;
	}

	/**
	 * <p>
	 * GET:
	 * </p>
	 * <b>obtener cotas</b>
	 * 
	 * @return Set<Cota> cotas
	 */
	public Set<Cota> getCotas() {
		return this.cotas;
	}

	/**
	 * <p>
	 * SET: <b>Establece cotas</b>
	 * </p>
	 * 
	 * @param Set<Cota>
	 *            cotas
	 */
	public void setCotas(Set<Cota> cotas) {
		this.cotas = cotas;
	}
}
