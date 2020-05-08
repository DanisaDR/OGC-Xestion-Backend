package org.redeoza.xestion.dao;

import org.redeoza.xestion.model.Actividade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IActividadeDao extends JpaRepository<Actividade, Integer> {

	@Query("select a from Actividade a where a.actNom like %:searchActNom%")
	Page<Actividade> searchAndPaginationAct(Pageable pageable, @Param("searchActNom") String searchActNom);
}
