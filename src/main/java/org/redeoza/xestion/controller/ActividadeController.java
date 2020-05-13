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
import org.redeoza.xestion.model.Actividade;
import org.redeoza.xestion.service.IActividadeService;
import org.redeoza.xestion.utils.UtilConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "actividades")
@CrossOrigin(origins = "*")
public class ActividadeController {

	@Autowired
	IActividadeService actSrv;

	@Secured({ "ROLE_ADMIN", "ROLE_DIRECTIVA", "ROLE_MONITOR" })
	@GetMapping(value = "listado")
	public ResponseEntity<Set<Actividade>> getAllActividades() {
		try {
			final Set<Actividade> colecActs = actSrv.getAllActividades();
			return new ResponseEntity<Set<Actividade>>(colecActs, HttpStatus.OK);
		} catch (final Exception ex) {
			throw new GenericException(ex.getMessage());
		}
	}

	@Secured({ "ROLE_ADMIN", "ROLE_DIRECTIVA", "ROLE_MONITOR" })
	@PreAuthorize("hasPermission('hasAccess', 'TODOS_PERMISOS')")
	@GetMapping(value = "paxina/{page}")
	public ResponseEntity<Page<Actividade>> findByParam(
			@RequestParam(value = "searchActNom", required = false) String searchActNom,
			@RequestParam(value = "order", required = false, defaultValue = "actID") String order,
			@RequestParam(defaultValue = "true") boolean ordenationType,
			@PathVariable(value = "page", required = false) int page,
			@RequestParam(value = "size", required = false, defaultValue = "5") int size) {

		Page<Actividade> pages = null;
		try {
			pages = actSrv.searchAndPagination(page, size, order, ordenationType, searchActNom);

			return new ResponseEntity<Page<Actividade>>(pages, HttpStatus.OK);
		} catch (final Exception ex) {
			throw new GenericException(ex.getMessage());
		}
	}

	@Secured({ "ROLE_ADMIN", "ROLE_DIRECTIVA" })
	@PreAuthorize("hasPermission('hasAccess', 'TODOS_PERMISOS')")
	@GetMapping(value = "ver/{actID}")
	public ResponseEntity<Actividade> showAct(@PathVariable int actID) {
		Actividade showAct = null;

		try {
			showAct = actSrv.findByActID(actID);

			if (showAct == null) {
				throw new NotFoundException(UtilConstant.NOT_FOUND_ACTIVIDADE);
			}

			return new ResponseEntity<Actividade>(showAct, HttpStatus.OK);
		} catch (final Exception ex) {
			throw new GenericException(ex.getMessage());
		}
	}

	@Secured({ "ROLE_ADMIN", "ROLE_DIRECTIVA" })
	@PreAuthorize("hasPermission('hasAccess', 'TODOS_PERMISOS')")
	@PostMapping(value = "crear")
	public ResponseEntity<Map<String, Object>> createAct(@Valid @RequestBody Actividade actividade,
			BindingResult result) {

		final Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {
			final List<String> errors = result.getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
					.collect(Collectors.toList());

			throw new MissingFieldException(errors.toString());
		}

		try {
			actSrv.addSociosToAct(actividade);
			actSrv.saveAct(actividade);

			response.put(UtilConstant.MESSAGE,
					UtilConstant.NEW_ACTIVIDADE.concat(actividade.getActNom()).concat(UtilConstant.CREATED));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		} catch (final Exception ex) {
			throw new GenericException(ex.getMessage());
		}
	}

	@Secured({ "ROLE_ADMIN", "ROLE_DIRECTIVA" })
	@PreAuthorize("hasPermission('hasAccess', 'TODOS_PERMISOS')")
	@PutMapping(value = "modificar/{actID}")
	public ResponseEntity<Map<String, Object>> updateAct(@Valid @RequestBody Actividade actividade,
			BindingResult result, @PathVariable int actID) {

		final Actividade actWellUpdate = null;

		final Map<String, Object> response = new HashMap<>();

		final Actividade actToUpdated = actSrv.findByActID(actID);

		if (result.hasErrors()) {
			final List<String> errors = result.getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
					.collect(Collectors.toList());

			throw new MissingFieldException(errors.toString());
		}

		if (actToUpdated == null) {
			throw new MissingFieldException(UtilConstant.NOT_FOUND_ACTIVIDADE);
		}

		try {
			response.put(UtilConstant.MESSAGE, UtilConstant.UPDATED_ACTIVIDADE.concat(actWellUpdate.getActNom()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		} catch (final Exception ex) {
			throw new GenericException(ex.getLocalizedMessage());
		}
	}

	@Secured({ "ROLE_ADMIN", "ROLE_DIRECTIVA" })
	@PreAuthorize("hasPermission('hasAccess', 'TODOS_PERMISOS')")
	@DeleteMapping(value = "borrar/{actID}")
	public ResponseEntity<Map<String, Object>> deleteAct(@PathVariable int actID) {

		final Map<String, Object> response = new HashMap<>();

		final Actividade deleteAct = actSrv.findByActID(actID);

		if (deleteAct == null) {
			throw new NotFoundException(UtilConstant.NOT_FOUND_USER);
		}

		try {
			actSrv.deleteAct(deleteAct);

			response.put(UtilConstant.MESSAGE, UtilConstant.DELETE_ACTIVIDADE);

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (final Exception ex) {
			throw new GenericException(ex.getMessage());
		}
	}
}
