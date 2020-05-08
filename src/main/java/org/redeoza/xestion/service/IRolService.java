package org.redeoza.xestion.service;

import java.util.Set;

import org.redeoza.xestion.model.Rol;

public interface IRolService {

	Set<Rol> getAllRoles();

	Rol findRolById(Integer rolID);

	Rol findRolByName(String rolNom);

	void saveRol(Rol rol);
}
