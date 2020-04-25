package org.redeoza.xestion.model;

import java.io.Serializable;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * <b>User.java<b>
 * 
 * @author Daniel Isasi
 * @since 12 ene. 2020
 */
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "usu_id")
	private int usuID;

	@Transient
	private String usuAlias;

	@NotEmpty(message = "{usuNom.empty}")
	@Size(min = 3, max = 100, message = "{usuNom.size}")
	@Column(name = "usu_nom")
	private String usuNom;

	@NotEmpty(message = "{usuApe1.empty}")
	@Size(min = 3, max = 150, message = "{usuNom.size}")
	@Column(name = "usu_1apel")
	private String usuApe1;

	@NotEmpty(message = "{usuApe2.empty}")
	@Size(min = 3, max = 150)
	@Column(name = "usu_2apel")
	private String usuApe2;

	@NotEmpty(message = "{usuEnder.empty}")
	@Size(min = 10, max = 255)
	@Column(name = "usu_ender")
	private String usuEnder;

	@Positive(message = "{usuTfnoMb.empty}")
	@Min(value = 600000000, message = "{usuTfnoMb.min}")
	@Max(value = 699999999, message = "{usuTfnoMb.max}")
	@Column(name = "usu_tfno_mb")
	private Integer usuTfnoMb;

	@Positive
	@Min(value = 880000000, message = "{usuTfnoFx.min}")
	@Max(value = 981999999, message = "{usuTfnoFx.max}")
	@Column(name = "usu_tfno_fx")
	private Integer usuTfnoFx;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "usu_id"), inverseJoinColumns = @JoinColumn(name = "rol_id"))
	@JsonIgnoreProperties(value = { "usuarios", "hibernateLazyInitializer", "handler" }, allowSetters = true)
	@Cascade({ CascadeType.SAVE_UPDATE })
	private Set<Rol> roles;

	@OneToMany(mappedBy = "usuario")
	@JsonIgnoreProperties(value = { "usuario", "hibernateLazyInitializer", "handler" }, allowSetters = true)
	@Cascade({ CascadeType.SAVE_UPDATE })
	private Set<Actividade> actividades;

	@OneToOne(mappedBy = "usuario", orphanRemoval = true, fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = { "usuario", "hibernateLazyInitializer", "handler" }, allowSetters = true)
	@Cascade({ CascadeType.ALL })
	private Login login;

	/**
	 * <p>
	 * GET:
	 * </p>
	 * <b>obtener usuID</b>
	 * 
	 * @return int usuID
	 */
	public int getUsuID() {
		return this.usuID;
	}

	/**
	 * <p>
	 * SET: <b>Establece usuID</b>
	 * </p>
	 * 
	 * @param int
	 *            usuID
	 */
	public void setUsuID(int usuID) {
		this.usuID = usuID;
	}

	/**
	 * <p>
	 * GET:
	 * </p>
	 * <b>obtener usuAlias</b>
	 * 
	 * @return String usuAlias
	 */
	public String getUsuAlias() {
		return this.usuAlias;
	}

	/**
	 * <p>
	 * SET: <b>Establece usuAlias</b>
	 * </p>
	 * 
	 * @param String
	 *            usuAlias
	 */
	public void setUsuAlias(String usuAlias) {
		this.usuAlias = usuAlias;
	}

	/**
	 * <p>
	 * GET:
	 * </p>
	 * <b>obtener usuNom</b>
	 * 
	 * @return String usuNom
	 */
	public String getUsuNom() {
		return this.usuNom;
	}

	/**
	 * <p>
	 * SET: <b>Establece usuNom</b>
	 * </p>
	 * 
	 * @param String
	 *            usuNom
	 */
	public void setUsuNom(String usuNom) {
		this.usuNom = usuNom;
	}

	/**
	 * <p>
	 * GET:
	 * </p>
	 * <b>obtener usuApe1</b>
	 * 
	 * @return String usuApe1
	 */
	public String getUsuApe1() {
		return this.usuApe1;
	}

	/**
	 * <p>
	 * SET: <b>Establece usuApe1</b>
	 * </p>
	 * 
	 * @param String
	 *            usuApe1
	 */
	public void setUsuApe1(String usuApe1) {
		this.usuApe1 = usuApe1;
	}

	/**
	 * <p>
	 * GET:
	 * </p>
	 * <b>obtener usuApe2</b>
	 * 
	 * @return String usuApe2
	 */
	public String getUsuApe2() {
		return this.usuApe2;
	}

	/**
	 * <p>
	 * SET: <b>Establece usuApe2</b>
	 * </p>
	 * 
	 * @param String
	 *            usuApe2
	 */
	public void setUsuApe2(String usuApe2) {
		this.usuApe2 = usuApe2;
	}

	/**
	 * <p>
	 * GET:
	 * </p>
	 * <b>obtener usuEnder</b>
	 * 
	 * @return String usuEnder
	 */
	public String getUsuEnder() {
		return this.usuEnder;
	}

	/**
	 * <p>
	 * SET: <b>Establece usuEnder</b>
	 * </p>
	 * 
	 * @param String
	 *            usuEnder
	 */
	public void setUsuEnder(String usuEnder) {
		this.usuEnder = usuEnder;
	}

	/**
	 * <p>
	 * GET:
	 * </p>
	 * <b>obtener usuTfnoMobil</b>
	 * 
	 * @return int usuTfnoMobil
	 */
	public Integer getUsuTfnoMb() {
		return this.usuTfnoMb;
	}

	/**
	 * <p>
	 * SET: <b>Establece usuTfnoMobil</b>
	 * </p>
	 * 
	 * @param int
	 *            usuTfnoMobil
	 */
	public void setUsuTfnoMb(Integer usuTfnoMb) {
		this.usuTfnoMb = usuTfnoMb;
	}

	/**
	 * <p>
	 * GET:
	 * </p>
	 * <b>obtener usuTfnoFx</b>
	 * 
	 * @return int usuTfnoFx
	 */
	public Integer getUsuTfnoFx() {
		return this.usuTfnoFx;
	}

	/**
	 * <p>
	 * SET: <b>Establece usuTfnoFx</b>
	 * </p>
	 * 
	 * @param int
	 *            usuTfnoFx
	 */
	public void setUsuTfnoFx(Integer usuTfnoFx) {
		this.usuTfnoFx = usuTfnoFx;
	}

	/**
	 * <p>
	 * GET:
	 * </p>
	 * <b>obtener rolesData</b>
	 * 
	 * @return Set<RoleData> rolesData
	 */
	public Set<Rol> getRoles() {
		return this.roles;
	}

	/**
	 * <p>
	 * SET: <b>Establece rolesData</b>
	 * </p>
	 * 
	 * @param Set<RoleData>
	 *            rolesData
	 */
	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
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
	 * <b>obtener login</b>
	 * 
	 * @return Login login
	 */
	public Login getLogin() {
		return this.login;
	}

	/**
	 * <p>
	 * SET: <b>Establece login</b>
	 * </p>
	 * 
	 * @param Login
	 *            login
	 */
	public void setLogin(Login login) {
		this.login = login;
	}
}
