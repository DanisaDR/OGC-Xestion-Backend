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
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

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
	@Max(15011)
	@Column(name = "soc_cp")
	private int socCP;

	@Nullable
	@Length(min = 9, max = 9)
	@Column(name = "soc_tfno_fx")
	private String socTfnoFx;

	@Nullable
	@Length(min = 9, max = 9)
	@Column(name = "soc_tfno_mb")
	private String socTfnoMb;

	@NotNull
	@Column(name = "soc_act")
	private boolean socAct;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/M/yyyy")
	@Column(name = "soc_data_alta")
	private Date socDataAlta;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/M/yyyy")
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
	private Set<Actividade> actividades;

	@OneToMany(mappedBy = "socio")
	@JsonIgnoreProperties(value = { "cotas", "hibernateLazyInitializer", "handler" }, allowSetters = true)
	private Set<Cota> cotas;

	public int getSocID() {
		return this.socID;
	}

	public void setSocID(int socID) {
		this.socID = socID;
	}

	public String getSocNomComp() {
		return this.socNomComp;
	}

	public void setSocNomComp(String socNomComp) {
		this.socNomComp = socNomComp;
	}

	public String getSocNom() {
		return this.socNom;
	}

	public void setSocNom(String socNom) {
		this.socNom = socNom;
	}

	public String getSocApe1() {
		return this.socApe1;
	}

	public void setSocApe1(String socApe1) {
		this.socApe1 = socApe1;
	}

	public String getSocApe2() {
		return this.socApe2;
	}

	public void setSocApe2(String socApe2) {
		this.socApe2 = socApe2;
	}

	public String getSocEnder() {
		return this.socEnder;
	}

	public void setSocEnder(String socEnder) {
		this.socEnder = socEnder;
	}

	public String getSocPob() {
		return this.socPob;
	}

	public void setSocPob(String socPob) {
		this.socPob = socPob;
	}

	public int getSocCP() {
		return this.socCP;
	}

	public void setSocCP(int socCP) {
		this.socCP = socCP;
	}

	public String getSocTfnoFx() {
		return this.socTfnoFx;
	}

	public void setSocTfnoFx(String socTfnoFx) {
		this.socTfnoFx = socTfnoFx;
	}

	public String getSocTfnoMb() {
		return this.socTfnoMb;
	}

	public void setSocTfnoMb(String socTfnoMb) {
		this.socTfnoMb = socTfnoMb;
	}

	public boolean isSocAct() {
		return this.socAct;
	}

	public void setSocAct(boolean socAct) {
		this.socAct = socAct;
	}

	public Date getSocDataAlta() {
		return this.socDataAlta;
	}

	public void setSocDataAlta(Date socDataAlta) {
		this.socDataAlta = socDataAlta;
	}

	public Date getSocDataBaixa() {
		return this.socDataBaixa;
	}

	public void setSocDataBaixa(Date socDataBaixa) {
		this.socDataBaixa = socDataBaixa;
	}

	public String getSocEmail() {
		return this.socEmail;
	}

	public void setSocEmail(@Nullable String socEmail) {
		this.socEmail = socEmail;
	}

	public Set<Actividade> getActividades() {
		return this.actividades;
	}

	public void setActividades(Set<Actividade> actividades) {
		this.actividades = actividades;
	}

	public Set<Cota> getCotas() {
		return this.cotas;
	}

	public void setCotas(Set<Cota> cotas) {
		this.cotas = cotas;
	}
}
