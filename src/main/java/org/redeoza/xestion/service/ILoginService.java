package org.redeoza.xestion.service;

import org.redeoza.xestion.model.Login;
import org.redeoza.xestion.model.Usuario;

/**
 * <b>ILoginService.java<b>
 * 
 * @author Daniel Isasi
 * @since 19 ene. 2020
 */
public interface ILoginService {

	public Login getLoginById(Integer loginID);

	public Login getLoginByUserAlias(String usuAlias);

	public void establishLogin(Usuario usuario);

	public Login saveLogin(Login login);

	public void deleteLogin(Integer loginID);

	public Integer attemptLogin(Integer usuID);
}
