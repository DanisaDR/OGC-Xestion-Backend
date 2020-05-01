package org.redeoza.xestion.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.redeoza.xestion.exception.GenericException;
import org.redeoza.xestion.exception.MissingFieldException;
import org.redeoza.xestion.exception.NotFoundException;
import org.redeoza.xestion.model.Socio;
import org.redeoza.xestion.service.ISocioService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <b>SocioController.java<b>
 * 
 * @author Daniel Isasi
 * @since 16 ene. 2020
 */
@RestController
@RequestMapping(value = "socios")
@CrossOrigin(origins = "*")
public class SocioController {

	@Autowired
	private ISocioService socSrv;

	@Secured({ "ROLE_ADMIN", "ROLE_DIRECTIVA", "ROLE_MONITOR" })
	@PreAuthorize("hasPermission('hasAccess', 'TODOS_PERMISOS') or hasPermission('hasAccess', 'VER_SOCIOS')")
	@GetMapping(value = "listado")
	public ResponseEntity<Set<Socio>> getAllSocios() {
		try {
			final Set<Socio> colecSocio = socSrv.getAllSocios();
			return new ResponseEntity<Set<Socio>>(colecSocio, HttpStatus.OK);
		} catch (final Exception ex) {
			throw new GenericException(ex.getMessage());
		}
	}

	@Secured({ "ROLE_ADMIN", "ROLE_DIRECTIVA", "ROLE_MONITOR" })
	@PreAuthorize("hasPermission('hasAccess', 'TODOS_PERMISOS') or hasPermission('hasAccess', 'VER_SOCIOS')")
	@GetMapping(value = "paxina/{page}")
	public ResponseEntity<Page<Socio>> findByParam(
			@RequestParam(value = "searchSocNomComp", required = false) String searchSocNomComp,
			@RequestParam(value = "searchSocEnder", required = false) String searchSocEnder,
			@RequestParam(value = "searchSocTfnoFx", required = false) Integer searchSocTfnoFx,
			@RequestParam(value = "searchSocTfnoMb", required = false) Integer searchSocTfnoMb,
			@RequestParam(value = "searchSocEmail", required = false) String searchSocEmail,
			@RequestParam(value = "order", required = false, defaultValue = "socID") String order,
			@RequestParam(defaultValue = "true") boolean ordenationType,
			@PathVariable(value = "page", required = false) int page,
			@RequestParam(value = "size", required = false, defaultValue = "5") int size) {

		try {
			Page<Socio> pages = null;

			pages = socSrv.searchAndPagination(page, size, order, ordenationType, searchSocNomComp, searchSocEnder,
					searchSocTfnoFx, searchSocTfnoMb, searchSocEmail);

			return new ResponseEntity<Page<Socio>>(pages, HttpStatus.OK);
		} catch (final Exception ex) {
			throw new GenericException(ex.getMessage());
		}
	}

	@Secured({ "ROLE_ADMIN", "ROLE_DIRECTIVA" })
	@PreAuthorize("hasPermission('hasAccess', 'TODOS_PERMISOS')")
	@GetMapping(value = "ver/{socID}")
	public ResponseEntity<Socio> showSoc(@PathVariable Integer socID) {
		Socio showSoc = null;

		try {
			showSoc = socSrv.getSocioById(socID);

			if (showSoc == null) {
				throw new NotFoundException(UtilConstant.NOT_FOUND_SOCIO);
			}

			return new ResponseEntity<Socio>(showSoc, HttpStatus.OK);
		} catch (final Exception ex) {
			throw new GenericException(ex.getMessage());
		}
	}

	@Secured({ "ROLE_ADMIN", "ROLE_DIRECTIVA" })
	@PreAuthorize("hasPermission('hasAccess', 'TODOS_PERMISOS')")
	@PostMapping(value = "crear")
	public ResponseEntity<Map<String, Object>> createUser(@Valid @RequestBody Socio socio, BindingResult result) {

		final Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {
			final List<String> errors = result.getFieldErrors().stream().map(err -> err.getDefaultMessage())
					.collect(Collectors.toList());

			throw new MissingFieldException(errors.toString());
		}

		try {
			socSrv.saveSoc(socio);

			response.put(UtilConstant.MESSAGE,
					UtilConstant.NEW_SOCIO.concat(socio.getSocNomComp()).concat(UtilConstant.CREATED));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		} catch (final Exception ex) {
			throw new GenericException(ex.getMessage());
		}
	}

	@Secured({ "ROLE_ADMIN", "ROLE_DIRECTIVA" })
	@PreAuthorize("hasPermission('hasAccess', 'TODOS_PERMISOS')")
	@PutMapping(value = "modificar/{socID}")
	public ResponseEntity<Map<String, Object>> updateUser(@Valid @RequestBody Socio socio, BindingResult result,
			@PathVariable Integer socID) {

		final Socio socUpdated = socSrv.getSocioById(socID);

		final Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {
			final List<String> errors = result.getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
					.collect(Collectors.toList());

			throw new MissingFieldException(errors.toString());
		}

		if (socUpdated == null) {
			throw new MissingFieldException(UtilConstant.NOT_FOUND_SOCIO);
		}

		try {
			socUpdated.setSocNomComp(socio.getSocNomComp());
			socUpdated.setSocEnder(socio.getSocEnder());
			socUpdated.setSocPob(socio.getSocPob());
			socUpdated.setSocCP(socio.getSocCP());
			socUpdated.setSocTfnoFx(socio.getSocTfnoFx());
			socUpdated.setSocTfnoMb(socio.getSocTfnoMb());
			socUpdated.setSocDataBaixa(socio.getSocDataBaixa());
			socUpdated.setSocEmail(socio.getSocEmail());

			socUpdated.setCotas(socio.getCotas());
			socUpdated.setActividades(socio.getActividades());

			response.put(UtilConstant.MESSAGE, UtilConstant.UPDATED_SOCIO.concat(socUpdated.getSocNomComp()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

		} catch (final Exception ex) {
			throw new GenericException(ex.getMessage());
		}
	}

	@Secured({ "ROLE_ADMIN", "ROLE_DIRECTIVA" })
	@PreAuthorize("hasPermission('hasAccess', 'TODOS_PERMISOS')")
	@PostMapping(value = "existe-mb/{socID}/{socTfnoMb}")
	public ResponseEntity<Map<String, Object>> existsPhoneMbUser(@PathVariable Integer socTfnoMb, @PathVariable int socID) {
		boolean exists = socSrv.existsTfnoMbSoc(socTfnoMb, socID);

		try {
			final Map<String, Object> response = new HashMap<>();
			response.put("exists", exists);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (Exception ex) {
			throw new GenericException(ex.getLocalizedMessage());
		}
	}

	@Secured({ "ROLE_ADMIN", "ROLE_DIRECTIVA" })
	@PreAuthorize("hasPermission('hasAccess', 'TODOS_PERMISOS')")
	@PostMapping(value = {"existe-email/{socID}/{socEmail}", "existe-email/{socID}"})
	public boolean existsEmailSoc(@PathVariable(required = false) String socEmail, @PathVariable int socID) {
		if(socEmail == null) {
			socEmail = StringUtils.EMPTY;
		}
		try {
			return socSrv.existsEmailSoc(socEmail, socID);
		} catch (Exception ex) {
			throw new GenericException(ex.getLocalizedMessage());
		}
	}
}
