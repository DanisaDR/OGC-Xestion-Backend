package org.redeoza.xestion.dao;

import org.redeoza.xestion.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * <b>IRoleDataDao.java<b>
 * 
 * @author Daniel Isasi
 * @since 12 ene. 2020
 */
@Repository
public interface IRolDao extends JpaRepository<Rol, Integer> {

	@Query("select r from Rol r where r.rolNome = :rolNome")
	public Rol findByNameRol(@Param("rolNome") String rolNome);
}
