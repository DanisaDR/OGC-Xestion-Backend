package org.redeoza.xestion.dao;

import org.redeoza.xestion.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ILoginDao extends JpaRepository<Login, Integer> {

	@Query("select l from Login l where l.usuAlias = :usuAlias")
	public Login findByUsuAlias(@Param(value = "usuAlias") String usuAlias);
}
