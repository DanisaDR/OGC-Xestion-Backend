package org.redeoza.xestion.service.imp;

import org.redeoza.xestion.dao.ICotaDao;
import org.redeoza.xestion.model.Cota;
import org.redeoza.xestion.service.ICotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Set;

@Service
public class CotaServiceImp implements ICotaService {

	@Autowired
	ICotaDao cotaDao;

	@Override
	@Transactional(readOnly = true)
	public int sumCotasByYear(int cotaAnual) {
		return cotaDao.sumCotasByYear(cotaAnual);
	}

	@Override
	@Transactional
	public void saveCota(Cota cota) {
		cotaDao.save(cota);
	}

	@Override
	@Transactional
	public void updateCotaActual(Set<Cota> cotas) {
		LocalDate now = LocalDate.now();
		int nowYear = now.getYear();

		for(Cota c : cotas) {
			if(nowYear == c.getCotaAnual()) {
				saveCota(c);
			}
		}
	}
}
