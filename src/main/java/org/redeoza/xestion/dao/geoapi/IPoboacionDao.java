package org.redeoza.xestion.dao.geoapi;

import org.redeoza.xestion.model.geoapi.Poboacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IPoboacionDao extends JpaRepository<Poboacion, String> {

    @Query(value = "select * from Poboacion p where p.cun = :cun and p.cmum = :cmum", nativeQuery = true)
    Poboacion findPobByCmumAndCun(@Param("cun") String cun, @Param("cmum") String cmum);
}
