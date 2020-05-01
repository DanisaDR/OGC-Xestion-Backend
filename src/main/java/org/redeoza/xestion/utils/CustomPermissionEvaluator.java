package org.redeoza.xestion.utils;

import java.io.Serializable;

import org.redeoza.xestion.service.IPermisoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class CustomPermissionEvaluator implements PermissionEvaluator {

	@Autowired
	IPermisoService permServ;

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

	private boolean validateAccess(String permission) {
		return permServ.getPermissionByName(permission) != null;
	}

	@Override
	public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
			Object permission) {
		return false;
	}

}
