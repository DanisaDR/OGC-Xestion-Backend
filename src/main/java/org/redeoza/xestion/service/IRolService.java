package org.redeoza.xestion.service;

import java.util.Set;

import org.redeoza.xestion.model.Rol;

/**
 * <b>IRoleDataService.java<b>
 * 
 * @author Daniel Isasi
 * @since 12 ene. 2020
 */
public interface IRolService {

	public Set<Rol> getAllRoles();

	public Rol findRolById(Integer rolID);

	public Rol findRolByName(String rolNom);

	public void saveRol(Rol rol);
}
