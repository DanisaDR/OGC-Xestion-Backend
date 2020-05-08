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

	public int getRolID() {
		return this.rolID;
	}

	public void setRolID(int rolID) {
		this.rolID = rolID;
	}

	public String getRolNome() {
		return this.rolNome;
	}

	public void setRolNome(String rolNome) {
		this.rolNome = rolNome;
	}

	public String getRolDescr() {
		return this.rolDescr;
	}

	public void setRolDescr(String rolDescr) {
		this.rolDescr = rolDescr;
	}

	public Set<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Set<Permiso> getPermisos() {
		return this.permisos;
	}

	public void setPermisos(Set<Permiso> permisos) {
		this.permisos = permisos;
	}
}
