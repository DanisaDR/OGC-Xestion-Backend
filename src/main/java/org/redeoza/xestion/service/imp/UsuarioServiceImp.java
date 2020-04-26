package org.redeoza.xestion.service.imp;

import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.NotImplementedException;
import org.redeoza.xestion.dao.IUsuarioDao;
import org.redeoza.xestion.model.Actividade;
import org.redeoza.xestion.model.Usuario;
import org.redeoza.xestion.service.IActividadeService;
import org.redeoza.xestion.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <b>UserDataServiceImp.java<b>
 * 
 * @author Daniel Isasi
 * @since 12 ene. 2020
 */
@Service
public class UsuarioServiceImp implements IUsuarioService {

	@Autowired
	private IUsuarioDao usuDao;

	@Autowired
	private IActividadeService actSrv;

	@Override
	@Transactional(readOnly = true)
	public Set<Usuario> getAllUsers() {
		return usuDao.findAll().stream().collect(Collectors.toSet());
	}

	@Override
	@Transactional(noRollbackFor = Exception.class)
	public Usuario saveUser(Usuario usuario) {
		return usuDao.save(usuario);
	}

	@Override
	@Transactional
	public void deleteUser(Usuario usuario) {
		usuDao.delete(usuario);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Usuario> searchAndPagination(int page, int size, String order, boolean ordenationType,
			String searchUsuNom, String searchUsuApe1, String searchUsuApe2, String searchUsuEnder,
			Integer searchUsuTfnoFx, Integer searchUsuTfnoMb) {

		PageRequest pageable = null;

		if (ordenationType) {
			pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, order));
		} else {
			pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, order));
		}

		Page<Usuario> searchPage = null;

		final Usuario tfnoFx = searchByTfnoFx(searchUsuTfnoFx);
		final Usuario tfnoMb = searchByTfnoMb(searchUsuTfnoMb);

		if ((tfnoFx != null) && (tfnoMb == null)) {
			searchPage = usuDao.searchAndPaginationUser(pageable, tfnoFx.getUsuNom(), tfnoFx.getUsuApe1(),
					tfnoFx.getUsuApe2(), tfnoFx.getUsuEnder());
		} else if ((tfnoMb != null) && (tfnoFx == null)) {
			searchPage = usuDao.searchAndPaginationUser(pageable, tfnoMb.getUsuNom(), tfnoMb.getUsuApe1(),
					tfnoMb.getUsuApe2(), tfnoMb.getUsuEnder());
		} else {
			searchPage = usuDao.searchAndPaginationUser(pageable, searchUsuNom, searchUsuApe1, searchUsuApe2,
					searchUsuEnder);
		}

		return searchPage;
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario searchByTfnoMb(Integer usuTfnoMb) {
		return usuDao.findByUserTfnoMb(usuTfnoMb);
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario searchByTfnoFx(Integer usuTfnoFx) {
		return usuDao.findByUserTfnoMb(usuTfnoFx);
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findUserById(int usuID) {
		return usuDao.findById(usuID).orElse(null);
	}

	@Override
	public void establishMonForAct(Usuario newUser) {
		newUser.getActividades().forEach(act -> act.setUsuario(newUser));

		for (final Actividade activ : newUser.getActividades()) {
			actSrv.saveAct(activ);
		}
	}

	@Override
	@Transactional
	public void changeStatusUser(Usuario usuario) {
		throw new NotImplementedException("Iste método ainda está sen implementar.");
	}

	@Override
	public boolean existsTfnoMbUser(Integer tfnoMb, int usuID) {
		return usuDao.findByUserTfnoMb(tfnoMb) != null && (usuID == 0 || usuDao.findByUserTfnoMb(tfnoMb).getUsuID() != usuID);
	}
}
