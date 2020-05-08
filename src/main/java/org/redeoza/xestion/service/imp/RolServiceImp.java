package org.redeoza.xestion.service.imp;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.redeoza.xestion.dao.IRolDao;
import org.redeoza.xestion.model.Rol;
import org.redeoza.xestion.service.IRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RolServiceImp implements IRolService {

	@Autowired
	IRolDao rolDao;

	@Override
	@Transactional(readOnly = true)
	public Set<Rol> getAllRoles() {
		return new HashSet<>(rolDao.findAll());
	}

	@Override
	@Transactional(readOnly = true)
	public Rol findRolById(Integer rolID) {
		return rolDao.findById(rolID).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public Rol findRolByName(String rolNom) {
		return rolDao.findByNameRol(rolNom);
	}

	@Override
	@Transactional
	public void saveRol(Rol rol) {
		rolDao.save(rol);
	}
}
