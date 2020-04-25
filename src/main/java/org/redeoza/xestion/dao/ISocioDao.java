package org.redeoza.xestion.dao;

import org.redeoza.xestion.model.Socio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * <b>ISocioDao.java<b>
 * 
 * @author Daniel Isasi
 * @since 16 ene. 2020
 */
@Repository
public interface ISocioDao extends JpaRepository<Socio, Integer> {

	@Query("select s from Socio s where s.socNomComp like %:searchSocNomComp% and s.socEnder like %:searchSocEnder% and (s.socEmail like %:searchSocEmail% or s.socEmail = null)")
	Page<Socio> searchAndPaginationSoc(Pageable pageable, @Param("searchSocNomComp") String searchSocNomComp,
			@Param("searchSocEnder") String searchSocEnder, @Param("searchSocEmail") String searchSocEmail);

	@Query("select s from Socio s where s.socTfnoFx = :socTfnoFx")
	public Socio findBySocTfnoFx(@Param("socTfnoFx") Integer socTfnoFx);

	@Query("select s from Socio s where s.socTfnoMb = :socTfnoMb")
	public Socio findBySocTfnoMb(@Param("socTfnoMb") Integer socTfnoMb);

	@Query("select count(s) from Socio s where s.socAct = :socAct")
	public int socActNoActs(@Param("socAct") boolean socAct);
}
