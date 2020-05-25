package org.redeoza.xestion.dao.geoapi;

import org.redeoza.xestion.model.geoapi.CodigoPostal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ICodigoPostalDao extends JpaRepository<CodigoPostal, String> {

    @Query(value = "select cp from CodigoPostal cp where cp.cmum = :cmum and cp.cun = :cun", nativeQuery = true)
    Set<CodigoPostal> findCPsByCmumAndCun(@Param("cmum") String cmum, @Param("cun") String cun);
}
