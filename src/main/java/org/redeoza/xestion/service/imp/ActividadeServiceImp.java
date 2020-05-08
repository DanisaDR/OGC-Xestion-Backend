package org.redeoza.xestion.service.imp;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.redeoza.xestion.dao.IActividadeDao;
import org.redeoza.xestion.model.Actividade;
import org.redeoza.xestion.service.IActividadeService;
import org.redeoza.xestion.service.ISocioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ActividadeServiceImp implements IActividadeService {

	@Autowired
	IActividadeDao actDao;

	@Autowired
	ISocioService socSrv;

	@Override
	@Transactional(readOnly = true)
	public Set<Actividade> getAllActividades() {
		return new HashSet<>(actDao.findAll());
	}

	@Override
	@Transactional(readOnly = true)
	public Actividade findByActID(int actID) {
		return actDao.findById(actID).orElse(null);
	}

	@Override
	@Transactional
	public void saveAct(Actividade actividade) {
		actDao.save(actividade);
	}

	@Override
	@Transactional
	public void deleteAct(Actividade actividade) {
		actDao.delete(actividade);
	}

	@Override
	public void addSociosToAct(Actividade actividade) {
		actividade.getSocios().forEach(soc -> socSrv.getSocioById(soc.getSocID()));
	}

	@Override
	public Page<Actividade> searchAndPagination(int page, int size, String order, boolean ordenationType,
			String searchActNom) {
		PageRequest pageable = null;

		if (ordenationType) {
			pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, order));
		} else {
			pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, order));
		}

		Page<Actividade> searchPage = null;

		searchPage = actDao.searchAndPaginationAct(pageable, searchActNom);

		return searchPage;
	}
}
