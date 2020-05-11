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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

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

	@Nullable
	@Length(min = 9,max = 9, message = "{usuTfnoMb.length}")
	@Column(name = "usu_tfno_mb")
	private String usuTfnoMb;

	@Nullable
	@Length(min = 9,max = 9, message = "{usuTfnoFx.length}")
	@Column(name = "usu_tfno_fx")
	private String usuTfnoFx;

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

	public int getUsuID() {
		return this.usuID;
	}

	public void setUsuID(int usuID) {
		this.usuID = usuID;
	}

	public String getUsuAlias() {
		return this.usuAlias;
	}

	public void setUsuAlias(String usuAlias) {
		this.usuAlias = usuAlias;
	}

	public String getUsuNom() {
		return this.usuNom;
	}

	public void setUsuNom(String usuNom) {
		this.usuNom = usuNom;
	}

	public String getUsuApe1() {
		return this.usuApe1;
	}

	public void setUsuApe1(String usuApe1) {
		this.usuApe1 = usuApe1;
	}

	public String getUsuApe2() {
		return this.usuApe2;
	}

	public void setUsuApe2(String usuApe2) {
		this.usuApe2 = usuApe2;
	}

	public String getUsuEnder() {
		return this.usuEnder;
	}

	public void setUsuEnder(String usuEnder) {
		this.usuEnder = usuEnder;
	}

	public String getUsuTfnoMb() {
		return this.usuTfnoMb;
	}

	public void setUsuTfnoMb(String usuTfnoMb) {
		this.usuTfnoMb = usuTfnoMb;
	}

	public String getUsuTfnoFx() {
		return this.usuTfnoFx;
	}

	public void setUsuTfnoFx(String usuTfnoFx) {
		this.usuTfnoFx = usuTfnoFx;
	}

	public Set<Rol> getRoles() {
		return this.roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}

	public Set<Actividade> getActividades() {
		return this.actividades;
	}

	public void setActividades(Set<Actividade> actividades) {
		this.actividades = actividades;
	}

	public Login getLogin() {
		return this.login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}
}
