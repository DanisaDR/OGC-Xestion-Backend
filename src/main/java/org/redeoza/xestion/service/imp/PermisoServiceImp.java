package org.redeoza.xestion.service.imp;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.redeoza.xestion.dao.IPermisoDao;
import org.redeoza.xestion.model.Permiso;
import org.redeoza.xestion.service.IPermisoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PermisoServiceImp implements IPermisoService {

	@Autowired
	IPermisoDao permDao;

	@Override
	@Transactional(readOnly = true)
	public Permiso getPermissionById(int permisoID) {
		return permDao.findById(permisoID).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public Set<Permiso> getAllPermissions() {
		return new HashSet<>(permDao.findAll());
	}

	@Override
	@Transactional(readOnly = true)
	public Permiso getPermissionByName(String permiso) {
		return permDao.findByNamePermission(permiso);
	}

	@Override
	@Transactional
	public Permiso savePermission(Permiso permiso) {
		return permDao.save(permiso);
	}

}
