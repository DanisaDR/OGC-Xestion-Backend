package org.redeoza.xestion.utils;

import java.io.Serializable;

import org.redeoza.xestion.service.IPermisoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * <b>CustomPermissionEvaluator.java<b>
 * 
 * @author Daniel Isasi
 * @since 17 ene. 2020
 */
@Component
public class CustomPermissionEvaluator implements PermissionEvaluator {

	@Autowired
	private IPermisoService permServ;

	@Override
	public boolean hasPermission(Authentication authentication, Object accessType, Object permission) {
		if ((authentication != null) && (accessType instanceof String)) {
			if (UtilConstant.HAS_ACCESS.equalsIgnoreCase(String.valueOf(accessType))) {
				return validateAccess(String.valueOf(permission));
			}

			return false;
		}

		return false;
	}

	/**
	 * <b>validateAccess</b>
	 * <p>
	 * Creado: 17 ene. 2020
	 * <p>
	 * 
	 * @param valueOf
	 * @return
	 */
	private boolean validateAccess(String permission) {
		return permServ.getPermissionByName(permission) != null ? true : false;
	}

	@Override
	public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
			Object permission) {
		return false;
	}

}
