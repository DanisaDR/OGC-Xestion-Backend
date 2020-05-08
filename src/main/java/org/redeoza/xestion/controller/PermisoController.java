package org.redeoza.xestion.controller;

import java.util.Set;

import org.redeoza.xestion.exception.GenericException;
import org.redeoza.xestion.model.Permiso;
import org.redeoza.xestion.service.IPermisoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("permisos")
public class PermisoController {

	@Autowired
	private IPermisoService permSrv;

	@Secured({ "ROLE_ADMIN" })
	@PreAuthorize("hasPermission('hasAccess', 'TODOS_PERMISOS')")
	@GetMapping(value = "listado")
	public ResponseEntity<Set<Permiso>> getAllPermisos() {
		try {
			final Set<Permiso> colecPermisos = permSrv.getAllPermissions();
			return new ResponseEntity<Set<Permiso>>(colecPermisos, HttpStatus.OK);
		} catch (final Exception ex) {
			throw new GenericException(ex.getMessage());
		}
	}
}
