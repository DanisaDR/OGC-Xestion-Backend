package org.redeoza.xestion.service;

import java.util.Set;

import org.redeoza.xestion.model.Permiso;

/**
 * <b>IPermisoService.java<b>
 * 
 * @author Daniel Isasi
 * @since 17 ene. 2020
 */
public interface IPermisoService {

	public Permiso getPermissionById(int permisoID);

	public Set<Permiso> getAllPermissions();

	public Permiso getPermissionByName(String permiso);

	public Permiso savePermission(Permiso permiso);
}
