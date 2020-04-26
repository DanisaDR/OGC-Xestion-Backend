package org.redeoza.xestion.service.imp;

import java.util.Set;
import java.util.stream.Collectors;

import org.redeoza.xestion.dao.ISocioDao;
import org.redeoza.xestion.model.Socio;
import org.redeoza.xestion.service.ISocioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <b>SocioServiceImp.java<b>
 * 
 * @author Daniel Isasi
 * @since 16 ene. 2020
 */
@Service
public class SocioServiceImp implements ISocioService {

	@Autowired
	private ISocioDao socDao;

	@Override
	@Transactional(readOnly = true)
	public Set<Socio> getAllSocios() {
		return socDao.findAll().stream().collect(Collectors.toSet());
	}

	@Override
	@Transactional(readOnly = true)
	public Socio getSocioById(Integer socID) {
		return socDao.findById(socID).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Socio> searchAndPagination(int page, int size, String order, boolean ordenationType,
			String searchSocNomComp, String searchSocEnder, Integer searchSocTfnoFx, Integer searchSocTfnoMb,
			String searchSocEmail) {

		PageRequest pageable = null;

		if (ordenationType) {
			pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, order));
		} else {
			pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, order));
		}

		Page<Socio> searchPage = null;

		final Socio tfnoFx = searchSocByTfnoFx(searchSocTfnoFx);
		final Socio tfnoMb = searchSocByTfnoMb(searchSocTfnoMb);

		if ((tfnoFx != null) && (tfnoMb == null)) {
			searchPage = socDao.searchAndPaginationSoc(pageable, tfnoFx.getSocNomComp(), tfnoFx.getSocEnder(),
					tfnoFx.getSocEmail());
		} else if ((tfnoMb != null) && (tfnoFx == null)) {
			searchPage = socDao.searchAndPaginationSoc(pageable, tfnoMb.getSocNomComp(), tfnoMb.getSocEnder(),
					tfnoMb.getSocEmail());
		} else {
			searchPage = socDao.searchAndPaginationSoc(pageable, searchSocNomComp, searchSocEnder, searchSocEmail);
		}

		return searchPage;
	}

	@Override
	@Transactional
	public Socio saveSoc(Socio socio) {
		return socDao.save(socio);
	}

	@Override
	@Transactional
	public void deleteSoc(Socio socio) {
		socDao.delete(socio);
	}

	@Override
	@Transactional(readOnly = true)
	public Socio searchSocByTfnoFx(Integer socTfnoFx) {
		return socDao.findBySocTfnoFx(socTfnoFx);
	}

	@Override
	@Transactional(readOnly = true)
	public Socio searchSocByTfnoMb(Integer socTfnoMb) {
		return socDao.findBySocTfnoMb(socTfnoMb);
	}

	@Override
	@Transactional(readOnly = true)
	public int socActNoActs(boolean socAct) {
		return socDao.socActNoActs(socAct);
	}

	@Override
	public boolean existsTfnoMbSoc(Integer tfnoMb, int socID) {
		return socDao.findBySocTfnoMb(tfnoMb) != null && (socID == 0 || socDao.findBySocTfnoMb(tfnoMb).getSocID() != socID);
	}
}