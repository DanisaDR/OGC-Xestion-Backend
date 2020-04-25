package org.redeoza.xestion.service.imp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.redeoza.xestion.dao.ILoginDao;
import org.redeoza.xestion.model.Login;
import org.redeoza.xestion.model.Permiso;
import org.redeoza.xestion.model.Rol;
import org.redeoza.xestion.model.Usuario;
import org.redeoza.xestion.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <b>LoginServiceImp.java<b>
 * 
 * @author Daniel Isasi
 * @since 19 ene. 2020
 */
@Service
public class LoginServiceImp implements UserDetailsService, ILoginService {

	@Autowired
	private ILoginDao loginDao;

	@Autowired
	private BCryptPasswordEncoder passEncoder;

	@Override
	@Transactional(readOnly = true)
	public Login getLoginById(Integer loginID) {
		return loginDao.findById(loginID).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public Login getLoginByUserAlias(String usuAlias) {
		return loginDao.findByUsuAlias(usuAlias);
	}

	@Override
	@Transactional
	public void establishLogin(Usuario usuario) {
		usuario.setLogin(new Login(usuario.getUsuAlias(), 3, passEncoder.encode("abc123."), true, usuario));
		saveLogin(usuario.getLogin());
	}

	@Override
	@Transactional
	public Login saveLogin(Login login) {
		return loginDao.save(login);
	}

	@Override
	@Transactional
	public void deleteLogin(Integer loginID) {
		loginDao.deleteById(loginID);
	}

	@Override
	@Transactional
	public Integer attemptLogin(Integer usuID) {
		int numAttempts = 0;

		final Login login = getLoginById(usuID);

		if (login.getUsuIntentos() > 0) {
			numAttempts = login.getUsuIntentos() - 1;
			login.setUsuIntentos(numAttempts);
			loginDao.save(login);
		} else if ((login.getUsuIntentos() == 0) && login.isUsuEstado()) {
			login.setUsuEstado(false);
			loginDao.save(login);
		}

		return numAttempts;
	}

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String usuAlias) throws UsernameNotFoundException {

		final Login login = loginDao.findByUsuAlias(usuAlias);

		if (null == login) {
			throw new UsernameNotFoundException("O alias ou/e contrasinal son incorrectas.");
		}

		final Collection<? extends GrantedAuthority> authorities = getAuthorities(login.getUsuario().getRoles());

		if (login.isUsuEstado()) {
			return new User(login.getUsuAlias(), login.getUsuClave(), true, true, true, true, authorities);
		} else {
			return new User(login.getUsuAlias(), login.getUsuClave(), false, true, true, false, authorities);
		}
	}

	/**
	 * <b>getAuthorities</b>
	 * <p>
	 * Creado: 17 ene. 2020
	 * <p>
	 * 
	 * @param roles
	 * @return
	 */
	private Collection<? extends GrantedAuthority> getAuthorities(Collection<Rol> roles) {
		return getGrantedAuthorities(getPrivileges(roles));
	}

	/**
	 * <b>getGrantedAuthorities</b>
	 * <p>
	 * Creado: 17 ene. 2020
	 * <p>
	 * 
	 * @param privileges
	 * @return
	 */
	private Collection<? extends GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
		final List<GrantedAuthority> authorities = new ArrayList<>();

		privileges.forEach(priv -> authorities.add(new SimpleGrantedAuthority(priv)));

		return authorities;
	}

	/**
	 * <b>getPrivileges</b>
	 * <p>
	 * Creado: 17 ene. 2020
	 * <p>
	 * 
	 * @param roles
	 * @return
	 */
	private List<String> getPrivileges(Collection<Rol> roles) {
		final List<String> privileges = new ArrayList<>();
		final List<Permiso> collection = new ArrayList<>();

		roles.forEach(r -> collection.addAll(r.getPermisos()));

		collection.forEach(p -> privileges.add(p.getPermisoNom()));

		return privileges;
	}
}
