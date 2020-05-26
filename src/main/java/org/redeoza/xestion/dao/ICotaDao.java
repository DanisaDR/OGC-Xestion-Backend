package org.redeoza.xestion.dao;

import org.redeoza.xestion.model.Cota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ICotaDao extends JpaRepository<Cota, Integer> {

	@Query("select c from Cota c where c.socID = :socID")
	Set<Cota> allCotasBySoc(@Param("socID") int socID);

	@Query("select sum(c.cotaImporte) from Cota c where c.cotaAnual = :cotaAnual order by c.cotaAnual")
	int sumCotasByYear(@Param("cotaAnual") int cotaAnual);
}
