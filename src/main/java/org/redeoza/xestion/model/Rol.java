package org.redeoza.xestion.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * <b>RoleData.java<b>
 * 
 * @author Daniel Isasi
 * @since 12 ene. 2020
 */
@Entity
@Table(name = "rol")
public class Rol implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rol_id")
	private int rolID;

	@NotEmpty
	@Size(min = 5, max = 50)
	@Column(name = "rol_nome")
	private String rolNome;

	@NotEmpty
	@Size(min = 5, max = 150)
	@Column(name = "rol_descr")
	private String rolDescr;

	@ManyToMany(mappedBy = "roles")
	@JsonIgnoreProperties(value = { "roles", "hibernateLazyInitializer", "handler" }, allowSetters = true)
	@Cascade({ CascadeType.SAVE_UPDATE, CascadeType.MERGE, CascadeType.REFRESH })
	private Set<Usuario> usuarios;

	@ManyToMany
	@JoinTable(name = "permiso_rol", joinColumns = @JoinColumn(name = "rol_id"), inverseJoinColumns = @JoinColumn(name = "permiso_id"))
	@JsonIgnoreProperties(value = { "roles", "hibernateLazyInitializer", "handler" }, allowSetters = true)
	@Cascade({ CascadeType.REFRESH })
	private Set<Permiso> permisos;

	/**
	 * <p>
	 * GET:
	 * </p>
	 * <b>obtener rolID</b>
	 * 
	 * @return int rolID
	 */
	public int getRolID() {
		return this.rolID;
	}

	/**
	 * <p>
	 * SET: <b>Establece rolID</b>
	 * </p>
	 * 
	 * @param int
	 *            rolID
	 */
	public void setRolID(int rolID) {
		this.rolID = rolID;
	}

	/**
	 * <p>
	 * GET:
	 * </p>
	 * <b>obtener rolNome</b>
	 * 
	 * @return String rolNome
	 */
	public String getRolNome() {
		return this.rolNome;
	}

	/**
	 * <p>
	 * SET: <b>Establece rolNome</b>
	 * </p>
	 * 
	 * @param String
	 *            rolNome
	 */
	public void setRolNome(String rolNome) {
		this.rolNome = rolNome;
	}

	/**
	 * <p>
	 * GET:
	 * </p>
	 * <b>obtener rolDescr</b>
	 * 
	 * @return String rolDescr
	 */
	public String getRolDescr() {
		return this.rolDescr;
	}

	/**
	 * <p>
	 * SET: <b>Establece rolDescr</b>
	 * </p>
	 * 
	 * @param String
	 *            rolDescr
	 */
	public void setRolDescr(String rolDescr) {
		this.rolDescr = rolDescr;
	}

	/**
	 * <p>
	 * GET:
	 * </p>
	 * <b>obtener usersData</b>
	 * 
	 * @return Set<UserData> usersData
	 */
	public Set<Usuario> getUsuarios() {
		return this.usuarios;
	}

	/**
	 * <p>
	 * SET: <b>Establece usersData</b>
	 * </p>
	 * 
	 * @param Set<UserData>
	 *            usersData
	 */
	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	/**
	 * <p>
	 * GET:
	 * </p>
	 * <b>obtener permisos</b>
	 * 
	 * @return Set<Permiso> permisos
	 */
	public Set<Permiso> getPermisos() {
		return this.permisos;
	}

	/**
	 * <p>
	 * SET: <b>Establece permisos</b>
	 * </p>
	 * 
	 * @param Set<Permiso>
	 *            permisos
	 */
	public void setPermisos(Set<Permiso> permisos) {
		this.permisos = permisos;
	}
}
