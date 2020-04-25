package org.redeoza.xestion.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * <b>Permiso.java<b>
 * 
 * @author Daniel Isasi
 * @since 17 ene. 2020
 */
@Entity
@Table(name = "Permiso")
public class Permiso implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "permiso_id")
	private int permisoID;

	@NotEmpty
	@Size(min = 5, max = 125)
	@Column(name = "permiso_nom")
	private String permisoNom;

	@NotEmpty
	@Size(min = 5, max = 125)
	@Column(name = "permiso_descr")
	private String permisoDescr;

	@ManyToMany(mappedBy = "permisos", fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = { "permisos", "hibernateLazyInitializer", "handler" }, allowSetters = true)
	@Cascade({ CascadeType.MERGE, CascadeType.REFRESH })
	private Set<Rol> roles;

	/**
	 * <p>
	 * GET:
	 * </p>
	 * <b>obtener permisoID</b>
	 * 
	 * @return int permisoID
	 */
	public int getPermisoID() {
		return this.permisoID;
	}

	/**
	 * <p>
	 * SET: <b>Establece permisoID</b>
	 * </p>
	 * 
	 * @param int
	 *            permisoID
	 */
	public void setPermisoID(int permisoID) {
		this.permisoID = permisoID;
	}

	/**
	 * <p>
	 * GET:
	 * </p>
	 * <b>obtener permisoNom</b>
	 * 
	 * @return String permisoNom
	 */
	public String getPermisoNom() {
		return this.permisoNom;
	}

	/**
	 * <p>
	 * SET: <b>Establece permisoNom</b>
	 * </p>
	 * 
	 * @param String
	 *            permisoNom
	 */
	public void setPermisoNom(String permisoNom) {
		this.permisoNom = permisoNom;
	}

	/**
	 * <p>
	 * GET:
	 * </p>
	 * <b>obtener permisoDescr</b>
	 * 
	 * @return String permisoDescr
	 */
	public String getPermisoDescr() {
		return this.permisoDescr;
	}

	/**
	 * <p>
	 * SET: <b>Establece permisoDescr</b>
	 * </p>
	 * 
	 * @param String
	 *            permisoDescr
	 */
	public void setPermisoDescr(String permisoDescr) {
		this.permisoDescr = permisoDescr;
	}

	/**
	 * <p>
	 * GET:
	 * </p>
	 * <b>obtener roles</b>
	 * 
	 * @return Set<Rol> roles
	 */
	public Set<Rol> getRoles() {
		return this.roles;
	}

	/**
	 * <p>
	 * SET: <b>Establece roles</b>
	 * </p>
	 * 
	 * @param Set<Rol>
	 *            roles
	 */
	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}
}
