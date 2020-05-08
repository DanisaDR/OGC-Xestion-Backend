package org.redeoza.xestion.service;

import org.redeoza.xestion.model.Login;
import org.redeoza.xestion.model.Usuario;

public interface ILoginService {

	Login getLoginById(Integer loginID);

	Login getLoginByUserAlias(String usuAlias);

	void establishLogin(Usuario usuario);

	Login saveLogin(Login login);

	void deleteLogin(Integer loginID);

	Integer attemptLogin(Integer usuID);
}
