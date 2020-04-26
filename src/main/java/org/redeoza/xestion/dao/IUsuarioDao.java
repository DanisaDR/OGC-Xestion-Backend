package org.redeoza.xestion.dao;

import org.redeoza.xestion.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * <b>IUserDataDao.java<b>
 * 
 * @author Daniel Isasi
 * @since 12 ene. 2020
 */
@Repository
public interface IUsuarioDao extends JpaRepository<Usuario, Integer> {

	@Query("select u from Usuario u where u.usuNom like %:searchUsuNom% and u.usuApe1 like %:searchUsuApe1% and "
			+ "u.usuApe2 like %:searchUsuApe2% and u.usuEnder like %:searchUsuEnder%")
	Page<Usuario> searchAndPaginationUser(Pageable pageable, @Param("searchUsuNom") String searchUsuNom,
			@Param("searchUsuApe1") String searchUsuApe1, @Param("searchUsuApe2") String searchUsuApe2,
			@Param("searchUsuEnder") String searchUsuEnder);

	@Query("select u from Usuario u where u.usuTfnoMb = :usuTfnoMb")
	public Usuario findByUserTfnoMb(@Param("usuTfnoMb") Integer usuTfnoMb);
}
