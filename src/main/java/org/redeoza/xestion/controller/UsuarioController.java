package org.redeoza.xestion.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.redeoza.xestion.exception.GenericException;
import org.redeoza.xestion.exception.MissingFieldException;
import org.redeoza.xestion.exception.NotFoundException;
import org.redeoza.xestion.model.Actividade;
import org.redeoza.xestion.model.Usuario;
import org.redeoza.xestion.service.IActividadeService;
import org.redeoza.xestion.service.ILoginService;
import org.redeoza.xestion.service.IUsuarioService;
import org.redeoza.xestion.utils.UtilConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

/**
 * <b>UserDataController.java<b>
 * 
 * @author Daniel Isasi
 * @since 12 ene. 2020
 */
@RestController
@RequestMapping(value = "usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

	@Autowired
	private IUsuarioService usrSrv;

	@Autowired
	private ILoginService loginSrv;

	@Autowired
	private IActividadeService actSrv;

	@Secured({ "ROLE_ADMIN", "ROLE_DIRECTIVA" })
	@PreAuthorize("hasPermission('hasAccess', 'TODOS_PERMISOS')")
	@GetMapping(value = "listado")
	public ResponseEntity<Set<Usuario>> getAllUsers() {
		try {
			final Set<Usuario> colecUsers = usrSrv.getAllUsers();
			return new ResponseEntity<Set<Usuario>>(colecUsers, HttpStatus.OK);
		} catch (final Exception ex) {
			throw new GenericException(ex.getMessage());
		}
	}

	@Secured({ "ROLE_ADMIN", "ROLE_DIRECTIVA" })
	@PreAuthorize("hasPermission('hasAccess', 'TODOS_PERMISOS')")
	@GetMapping(value = "paxina/{page}")
	public ResponseEntity<Page<Usuario>> findByParam(
			@RequestParam(value = "searchUsuNom", required = false) String searchUsuNom,
			@RequestParam(value = "searchUsuApe1", required = false) String searchUsuApe1,
			@RequestParam(value = "searchUsuApe2", required = false) String searchUsuApe2,
			@RequestParam(value = "searchUsuEnder", required = false) String searchUsuEnder,
			@RequestParam(value = "searchUsuTfnoFx", required = false) Integer searchUsuTfnoFx,
			@RequestParam(value = "searchUsuTfnoMb", required = false) Integer searchUsuTfnoMb,
			@RequestParam(value = "order", required = false, defaultValue = "usuID") String order,
			@RequestParam(defaultValue = "true") boolean ordenationType,
			@PathVariable(value = "page", required = false) int page,
			@RequestParam(value = "size", required = false, defaultValue = "5") int size) {

		Page<Usuario> pages = null;
		try {
			pages = usrSrv.searchAndPagination(page, size, order, ordenationType, searchUsuNom, searchUsuApe1,
					searchUsuApe2, searchUsuEnder, searchUsuTfnoFx, searchUsuTfnoMb);

			return new ResponseEntity<Page<Usuario>>(pages, HttpStatus.OK);
		} catch (final Exception ex) {
			throw new GenericException(ex.getMessage());
		}
	}

	@Secured({ "ROLE_ADMIN", "ROLE_DIRECTIVA" })
	@PreAuthorize("hasPermission('hasAccess', 'TODOS_PERMISOS')")
	@GetMapping(value = "ver/{usuID}")
	public ResponseEntity<Usuario> showUser(@PathVariable int usuID) {
		Usuario showUser = null;

		try {
			showUser = usrSrv.findUserById(usuID);

			if (showUser == null) {
				throw new NotFoundException(UtilConstant.NOT_FOUND_USER);
			}

			return new ResponseEntity<Usuario>(showUser, HttpStatus.OK);
		} catch (final Exception ex) {
			throw new GenericException(ex.getMessage());
		}
	}

	@Secured({ "ROLE_ADMIN", "ROLE_DIRECTIVA" })
	@PreAuthorize("hasPermission('hasAccess', 'TODOS_PERMISOS')")
	@PostMapping(value = "crear")
	public ResponseEntity<Map<String, Object>> createUser(@Valid @RequestBody Usuario usuario, BindingResult result) {

		final Map<String, Object> response = new HashMap<>();

		Usuario newUser;

		if (result.hasErrors()) {
			final List<String> errors = result.getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
					.collect(Collectors.toList());

			throw new MissingFieldException(errors.toString());
		}

		if ((usuario.getUsuTfnoFx() == null) && (usuario.getUsuTfnoMb() == null)) {
			throw new MissingFieldException(UtilConstant.ONE_PHONE_FILL);
		}

		if ((usuario.getRoles() == null) || usuario.getRoles().isEmpty()) {
			throw new MissingFieldException(UtilConstant.NOT_ROLE_DETECT);
		}

		try {
			newUser = usrSrv.saveUser(usuario);
			loginSrv.establishLogin(newUser);
			usrSrv.establishMonForAct(newUser);

			response.put(UtilConstant.MESSAGE,
					UtilConstant.NEW_USER.concat(newUser.getLogin().getUsuAlias()).concat(UtilConstant.CREATED));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		} catch (final Exception ex) {
			throw new GenericException(ex.getMessage());
		}
	}

	@Secured({ "ROLE_ADMIN", "ROLE_DIRECTIVA" })
	@PreAuthorize("hasPermission('hasAccess', 'TODOS_PERMISOS')")
	@PutMapping(value = "modificar/{usuID}")
	public ResponseEntity<Map<String, Object>> updateUser(@Valid @RequestBody Usuario usuario, BindingResult result,
			@PathVariable int usuID) {

		final Usuario userUpdated = usrSrv.findUserById(usuID);

		final Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {
			final List<String> errors = result.getFieldErrors().stream().map(err -> err.getDefaultMessage())
					.collect(Collectors.toList());

			throw new MissingFieldException(errors.toString());
		}

		if (userUpdated == null) {
			throw new MissingFieldException(UtilConstant.NOT_FOUND_USER);
		}

		if ((usuario.getUsuTfnoFx() == null) && (usuario.getUsuTfnoMb() == null)) {
			throw new MissingFieldException(UtilConstant.ONE_PHONE_FILL);
		}

		try {
			userUpdated.setUsuNom(usuario.getUsuNom());
			userUpdated.setUsuApe1(usuario.getUsuApe1());
			userUpdated.setUsuApe2(usuario.getUsuApe2());
			userUpdated.setUsuEnder(usuario.getUsuEnder());
			userUpdated.setUsuTfnoFx(usuario.getUsuTfnoFx());
			userUpdated.setUsuTfnoMb(usuario.getUsuTfnoMb());

			userUpdated.getLogin().setUsuAlias(usuario.getUsuAlias());

			usuario.getRoles().forEach(rol -> rol.setUsuarios(new HashSet<Usuario>()));
			userUpdated.setRoles(usuario.getRoles());

			usuario.getActividades().forEach(act -> act.setUsuario(new Usuario()));
			userUpdated.setActividades(usuario.getActividades());

			for (final Actividade actividade : userUpdated.getActividades()) {
				actividade.setUsuario(usuario);
				actSrv.saveAct(actividade);
			}

			usrSrv.saveUser(userUpdated);

			response.put(UtilConstant.MESSAGE, UtilConstant.UPDATED_USER.concat(userUpdated.getLogin().getUsuAlias()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		} catch (final Exception ex) {
			ex.printStackTrace();
			throw new GenericException(ex.getLocalizedMessage());
		}
	}

	@Secured({ "ROLE_ADMIN", "ROLE_DIRECTIVA" })
	@PreAuthorize("hasPermission('hasAccess', 'TODOS_PERMISOS')")
	@DeleteMapping(value = "borrar/{usuID}")
	public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable int usuID) {

		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		final Map<String, Object> response = new HashMap<>();

		final Usuario deleteUser = usrSrv.findUserById(usuID);

		if (deleteUser == null) {
			throw new NotFoundException(UtilConstant.NOT_FOUND_USER);
		}

		if (auth.getName().equals(deleteUser.getLogin().getUsuAlias())) {
			throw new MissingFieldException(UtilConstant.NOT_DELETE_MYSELF);
		}

		if ((deleteUser.getUsuID() == 1) || (deleteUser.getUsuID() == 2)) {
			throw new MissingFieldException(UtilConstant.NOT_DELETE_NECESARY_USERS);
		}

		try {
			usrSrv.deleteUser(deleteUser);

			response.put(UtilConstant.MESSAGE, UtilConstant.DELETE_USER);

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (final Exception ex) {
			throw new GenericException(ex.getMessage());
		}
	}

	@Secured({ "ROLE_ADMIN", "ROLE_DIRECTIVA" })
	@PreAuthorize("hasPermission('hasAccess', 'TODOS_PERMISOS')")
	@PutMapping(value = "cambiar-estado")
	public boolean changeUserStatus(@Valid @RequestBody Usuario usuario, BindingResult result) {

		final Usuario userUpdated = usrSrv.findUserById(usuario.getUsuID());

		boolean doChange = false;

		if (result.hasErrors()) {
			final List<String> errors = result.getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
					.collect(Collectors.toList());

			throw new MissingFieldException(errors.toString());
		}

		if (userUpdated == null) {
			throw new MissingFieldException(UtilConstant.NOT_FOUND_USER);
		}

		if ((usuario.getUsuTfnoFx() == null) && (usuario.getUsuTfnoMb() == null)) {
			throw new MissingFieldException(UtilConstant.ONE_PHONE_FILL);
		}

		try {
			if (userUpdated.getLogin().isUsuEstado()) {
				userUpdated.getLogin().setUsuEstado(false);
			} else {
				userUpdated.getLogin().setUsuEstado(true);
				doChange = true;
			}

			usrSrv.saveUser(userUpdated);

			return doChange;

		} catch (final Exception ex) {
			throw new GenericException(ex.getLocalizedMessage());
		}
	}

	@Secured({ "ROLE_ADMIN", "ROLE_DIRECTIVA" })
	@PreAuthorize("hasPermission('hasAccess', 'TODOS_PERMISOS')")
	@PostMapping(value = "existe-mb/{usuID}/{usuTfnoMb}")
	public boolean existsPhoneMbUser(@PathVariable(required = false) Integer usuTfnoMb, @PathVariable int usuID) {
		if(usuTfnoMb == null) {
			usuTfnoMb = 0;
		}
		try {
			return usrSrv.existsTfnoMbUser(usuTfnoMb, usuID);
		} catch (Exception ex) {
			throw new GenericException(ex.getLocalizedMessage());
		}
	}
}
