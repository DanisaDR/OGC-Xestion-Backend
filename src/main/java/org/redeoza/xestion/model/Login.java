package org.redeoza.xestion.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Login")
@JsonIgnoreProperties({ "usuario" })
public class Login implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "usu_id")
	private int usuID;

	@NotEmpty
	@Size(min = 9, max = 9)
	@Column(name = "login_alias")
	private String usuAlias;

	@NotNull
	@PositiveOrZero
	@Column(name = "login_intentos")
	private int usuIntentos;

	@NotEmpty
	@Size(min = 25, max = 255)
	@Column(name = "login_clave")
	private String usuClave;

	@NotNull
	@Column(name = "login_estado")
	private boolean usuEstado;

	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	@JoinColumn(name = "usu_id")
	@Cascade({ CascadeType.ALL })
	private Usuario usuario;

	public Login() {

	}

	public Login(String usuAlias, int usuIntentos, String usuClave, boolean usuEstado, Usuario usuario) {
		super();
		this.usuAlias = usuAlias;
		this.usuIntentos = usuIntentos;
		this.usuClave = usuClave;
		this.usuEstado = usuEstado;
		this.usuario = usuario;
	}

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

	public int getUsuIntentos() {
		return this.usuIntentos;
	}

	public void setUsuIntentos(int usuIntentos) {
		this.usuIntentos = usuIntentos;
	}

	public boolean isUsuEstado() {
		return this.usuEstado;
	}

	public void setUsuEstado(boolean usuEstado) {
		this.usuEstado = usuEstado;
	}

	public String getUsuClave() {
		return this.usuClave;
	}

	public void setUsuClave(String usuClave) {
		this.usuClave = usuClave;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
