package org.redeoza.xestion.service.imp;

import org.redeoza.xestion.dao.ICotaDao;
import org.redeoza.xestion.model.Cota;
import org.redeoza.xestion.service.ICotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

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
	public Set<Cota> updateCotasBySoc(Set<Cota> cotas) {
		cotas.forEach(this::saveCota);
		return cotas;
	}

	@Override
	@Transactional
	public void saveCota(Cota cota) {
		cotaDao.save(cota);
	}

	@Override
	@Transactional(readOnly = true)
	public Set<Cota> allCotasBySoc(int socID) {
		return cotaDao.allCotasBySoc(socID);
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
