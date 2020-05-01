package org.redeoza.xestion.service;

import java.util.Set;

import org.redeoza.xestion.model.Permiso;

public interface IPermisoService {

	Permiso getPermissionById(int permisoID);

	Set<Permiso> getAllPermissions();

	Permiso getPermissionByName(String permiso);

	Permiso savePermission(Permiso permiso);
}
