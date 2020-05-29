package org.redeoza.xestion.dao;

import org.redeoza.xestion.model.Socio;
import org.redeoza.xestion.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ISocioDao extends JpaRepository<Socio, Integer> {

	@Query("select s from Socio s where s.socNomComp like %:searchSocNomComp% " +
			"and s.socEnder like %:searchSocEnder% " +
			"and s.socEmail like %:searchSocEmail% " +
			"and s.socTfnoFx like %:searchSocTfnoFx% " +
			"and s.socTfnoMb like %:searchSocTfnoMb%")
	Page<Socio> searchAndPaginationSoc(Pageable pageable, @Param("searchSocNomComp") String searchSocNomComp,
			@Param("searchSocEnder") String searchSocEnder, @Param("searchSocEmail") String searchSocEmail,
			@Param("searchSocTfnoFx") String searchSocTfnoFx, @Param("searchSocTfnoMb") String searchSocTfnoMb);

	@Query("select s from Socio s where s.socTfnoMb like %:socTfnoMb%")
	Socio findBySocTfnoMb(@Param("socTfnoMb") String socTfnoMb);

	@Query("select count(s) from Socio s where s.socAct = :socAct")
	int socActNoActs(@Param("socAct") boolean socAct);

	@Query("select s from Socio s where s.socEmail like %:socEmail%")
	Socio findBySocEmail(@Param("socEmail") String socEmail);

	@Query(value = "select top 1 soc_id from Socio order by soc_id desc", nativeQuery = true)
	int findLastSoc();
}
