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

	public int getPermisoID() {
		return this.permisoID;
	}

	public void setPermisoID(int permisoID) {
		this.permisoID = permisoID;
	}

	public String getPermisoNom() {
		return this.permisoNom;
	}

	public void setPermisoNom(String permisoNom) {
		this.permisoNom = permisoNom;
	}

	public String getPermisoDescr() {
		return this.permisoDescr;
	}

	public void setPermisoDescr(String permisoDescr) {
		this.permisoDescr = permisoDescr;
	}

	public Set<Rol> getRoles() {
		return this.roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}
}
