package org.redeoza.xestion.controller;

import org.redeoza.xestion.exception.GenericException;
import org.redeoza.xestion.exception.NotFoundException;
import org.redeoza.xestion.model.Login;
import org.redeoza.xestion.service.ILoginService;
import org.redeoza.xestion.utils.UtilConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class LoginController {

	@Autowired
	ILoginService loginSrv;

	@GetMapping(value = "login-usuario/{usuID}")
	public ResponseEntity<Login> loginByUser(@PathVariable int usuID) {
		Login showLogin = null;

		try {
			showLogin = loginSrv.getLoginById(usuID);

			if (showLogin == null) {
				throw new NotFoundException(UtilConstant.NOT_FOUND_USER);
			}

			return new ResponseEntity<Login>(showLogin, HttpStatus.OK);
		} catch (final Exception ex) {
			throw new GenericException(ex.getMessage());
		}
	}

	@PostMapping(value = "login/intento-sesion/{usuAlias}")
	public int trySessionLogin(@PathVariable String usuAlias) {
		try {
			return loginSrv.attemptLogin(usuAlias);
		} catch (final Exception ex) {
			throw new GenericException(ex.getMessage());
		}
	}
}
