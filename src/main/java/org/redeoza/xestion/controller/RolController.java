package org.redeoza.xestion.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.redeoza.xestion.exception.GenericException;
import org.redeoza.xestion.exception.MissingFieldException;
import org.redeoza.xestion.exception.NotFoundException;
import org.redeoza.xestion.model.Rol;
import org.redeoza.xestion.service.IRolService;
import org.redeoza.xestion.service.IUsuarioService;
import org.redeoza.xestion.utils.UtilConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <b>RolController.java<b>
 * 
 * @author Daniel Isasi
 * @since 15 ene. 2020
 */
@RestController
@RequestMapping(value = "roles")
@CrossOrigin(origins = "*")
public class RolController {

	@Autowired
	private IRolService rolSrv;

	@Autowired
	private IUsuarioService usrSrv;

	@Secured({ "ROLE_ADMIN" })
	@GetMapping(value = "listado")
	public ResponseEntity<Set<Rol>> getAllRoles() {
		try {
			final Set<Rol> colecRoles = rolSrv.getAllRoles();
			return new ResponseEntity<Set<Rol>>(colecRoles, HttpStatus.OK);
		} catch (final Exception ex) {
			ex.printStackTrace();
			throw new GenericException(ex.getMessage());
		}
	}

	@Secured({ "ROLE_ADMIN", "ROLE_DIRECTIVA" })
	@PreAuthorize("hasPermission('hasAccess', 'TODOS_PERMISOS')")
	@GetMapping(value = "ver/{rolID}")
	public ResponseEntity<Rol> showRol(@PathVariable Integer rolID) {
		Rol showRol = null;

		try {
			showRol = rolSrv.findRolById(rolID);

			if (showRol == null) {
				throw new NotFoundException(UtilConstant.NOT_FOUND_ROL);
			}

			return new ResponseEntity<Rol>(showRol, HttpStatus.OK);
		} catch (final Exception ex) {
			ex.printStackTrace();
			throw new GenericException(ex.getMessage());
		}
	}

	@Secured({ "ROLE_ADMIN", "ROLE_DIRECTIVA" })
	@PreAuthorize("hasPermission('hasAccess', 'TODOS_PERMISOS')")
	@PutMapping(value = "modificar/{rolID}")
	public ResponseEntity<Map<String, Object>> updateRol(@Valid @RequestBody Rol rol, BindingResult result,
			@PathVariable int rolID) {

		final Rol rolUpdated = rolSrv.findRolById(rolID);

		final Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {
			final List<String> errors = result.getFieldErrors().stream().map(err -> err.getDefaultMessage())
					.collect(Collectors.toList());

			throw new MissingFieldException(errors.toString());
		}

		if (rolUpdated == null) {
			throw new MissingFieldException(UtilConstant.NOT_FOUND_USER);
		}

		try {
			rolUpdated.setPermisos(rol.getPermisos());

			rolUpdated.getUsuarios().forEach(usr -> usr.getRoles().remove(rolUpdated));

			rol.getUsuarios().forEach(usr -> usrSrv.findUserById(usr.getUsuID()).getRoles().add(rol));

			rolSrv.saveRol(rolUpdated);

			response.put(UtilConstant.MESSAGE, UtilConstant.UPDATED_ROL_USERS
					.concat(UtilConstant.UPDATED_ROL_PERMISSIONS).concat(rolUpdated.getRolNome()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		} catch (final Exception ex) {
			ex.printStackTrace();
			throw new GenericException(ex.getMessage());
		}
	}
}
