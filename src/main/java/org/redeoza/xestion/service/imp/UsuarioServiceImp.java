package org.redeoza.xestion.service.imp;

import java.util.HashSet;
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

@Service
public class UsuarioServiceImp implements IUsuarioService {

	@Autowired
	private IUsuarioDao usuDao;

	@Autowired
	private IActividadeService actSrv;

	@Override
	@Transactional(readOnly = true)
	public Set<Usuario> getAllUsers() {
		return new HashSet<>(usuDao.findAll());
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
			String searchUsuTfnoFx, String searchUsuTfnoMb) {

		PageRequest pageable = null;

		if (ordenationType) {
			pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, order));
		} else {
			pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, order));
		}

		Page<Usuario> searchPage = null;

		searchPage = usuDao.searchAndPaginationUser(
				pageable, searchUsuNom, searchUsuApe1,
				searchUsuApe2, searchUsuEnder,
				searchUsuTfnoFx, searchUsuTfnoMb
		);

		return searchPage;
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
	public boolean existsTfnoMbUser(String tfnoMb, int usuID) {
		return usuDao.findByUserTfnoMb(tfnoMb) != null && (usuID == 0 || usuDao.findByUserTfnoMb(tfnoMb).getUsuID() != usuID);
	}
}
