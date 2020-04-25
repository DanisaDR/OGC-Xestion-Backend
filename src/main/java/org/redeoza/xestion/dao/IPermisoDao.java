package org.redeoza.xestion.dao;

import org.redeoza.xestion.model.Permiso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * <b>IPermisionDao.java<b>
 * 
 * @author Daniel Isasi
 * @since 17 ene. 2020
 */
@Repository
public interface IPermisoDao extends JpaRepository<Permiso, Integer> {

	@Query("select p from Permiso p where p.permisoNom = :permisoNom")
	public Permiso findByNamePermission(@Param("permisoNom") String permisoNom);
}
