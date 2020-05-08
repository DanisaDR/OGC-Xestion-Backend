package org.redeoza.xestion.service.imp;

import org.redeoza.xestion.dao.ICotaDao;
import org.redeoza.xestion.service.ICotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CotaServiceImp implements ICotaService {

	@Autowired
	ICotaDao cotaDao;

	@Override
	@Transactional(readOnly = true)
	public int sumCotasByYear(int cotaAnual) {
		return cotaDao.sumCotasByYear(cotaAnual);
	}
}
