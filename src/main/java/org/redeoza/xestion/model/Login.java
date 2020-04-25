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

/**
 * <b>Login.java<b>
 * 
 * @author Daniel Isasi
 * @since 19 ene. 2020
 */
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

	/**
	 * <p>
	 * Creado: 19 ene. 2020
	 * </p>
	 * 
	 * @param usuAlias
	 * @param usuIntentos
	 * @param usuClave
	 * @param usuEstado
	 * @param usuario
	 */
	public Login(String usuAlias, int usuIntentos, String usuClave, boolean usuEstado, Usuario usuario) {
		super();
		this.usuAlias = usuAlias;
		this.usuIntentos = usuIntentos;
		this.usuClave = usuClave;
		this.usuEstado = usuEstado;
		this.usuario = usuario;
	}

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
	 * <b>obtener usuIntentos</b>
	 * 
	 * @return int usuIntentos
	 */
	public int getUsuIntentos() {
		return this.usuIntentos;
	}

	/**
	 * <p>
	 * SET: <b>Establece usuIntentos</b>
	 * </p>
	 * 
	 * @param int
	 *            usuIntentos
	 */
	public void setUsuIntentos(int usuIntentos) {
		this.usuIntentos = usuIntentos;
	}

	/**
	 * <p>
	 * GET:
	 * </p>
	 * <b>obtener usuEstado</b>
	 * 
	 * @return boolean usuEstado
	 */
	public boolean isUsuEstado() {
		return this.usuEstado;
	}

	/**
	 * <p>
	 * SET: <b>Establece usuEstado</b>
	 * </p>
	 * 
	 * @param boolean
	 *            usuEstado
	 */
	public void setUsuEstado(boolean usuEstado) {
		this.usuEstado = usuEstado;
	}

	/**
	 * <p>
	 * GET:
	 * </p>
	 * <b>obtener usuClave</b>
	 * 
	 * @return String usuClave
	 */
	public String getUsuClave() {
		return this.usuClave;
	}

	/**
	 * <p>
	 * SET: <b>Establece usuClave</b>
	 * </p>
	 * 
	 * @param String
	 *            usuClave
	 */
	public void setUsuClave(String usuClave) {
		this.usuClave = usuClave;
	}

	/**
	 * <p>
	 * GET:
	 * </p>
	 * <b>obtener usuario</b>
	 * 
	 * @return Usuario usuario
	 */
	public Usuario getUsuario() {
		return this.usuario;
	}

	/**
	 * <p>
	 * SET: <b>Establece usuario</b>
	 * </p>
	 * 
	 * @param Usuario
	 *            usuario
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
